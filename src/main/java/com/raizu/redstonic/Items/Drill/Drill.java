package com.raizu.redstonic.Items.Drill;

import cofh.api.energy.IEnergyContainerItem;
import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import javax.xml.soap.Text;
import java.security.Key;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class Drill extends ItemPickaxe implements IEnergyContainerItem {

    public Drill(final int head, final int body) {
        super(ToolMaterial.DIAMOND);
        setUnlocalizedName("RedstonicDrill");
        setRegistryName("RedstonicDrill");
        setMaxStackSize(1);
        setCreativeTab(Redstonic.redTab);
        addPropertyOverride(new ResourceLocation("head"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack==null ? head : stack.getTagCompound()==null ? head : stack.getTagCompound().getInteger("head");
            }
        });
        addPropertyOverride(new ResourceLocation("body"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack==null ? body : stack.getTagCompound()==null ? body : stack.getTagCompound().getInteger("body");
            }
        });
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null){
            tag = new NBTTagCompound();
            tag.setInteger("head", 0);
            tag.setInteger("body", 0);
        }
        int head = tag.getInteger("head");
        tag.setInteger("Energy", tag.getInteger("Energy")-(tag.getInteger("Energy")<getEnergyCost(stack)? tag.getInteger("Energy") : getEnergyCost(stack)));
        if(head!=3)tag.setInteger("blocks", tag.getInteger("blocks")+1);
        stack.setTagCompound(tag);
        switch(head){
            case 3: //HEAVY
                threebythree((EntityPlayer)entityLiving, worldIn, state, pos, stack);
                break;
            case 6: //BLAZER
                worldIn.playSound((EntityPlayer)entityLiving, pos, new SoundEvent(new ResourceLocation("entity.ghast.shoot")), SoundCategory.BLOCKS, 0.5F, 2F);
                worldIn.spawnParticle(EnumParticleTypes.FLAME, entityLiving.posX, entityLiving.posY+1, entityLiving.posZ,1, 1, 1);
                break;
        }
        if(head!=3 && hasAugment(4, stack)){
            if(head==5 || head==6)threebythree((EntityPlayer)entityLiving, worldIn, state, pos, stack, true);
            else threebythree((EntityPlayer)entityLiving, worldIn, state, pos, stack);
        }
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getTagCompound()!=null){
            if(stack.getTagCompound().getInteger("head")==4){
                if(!stack.isItemEnchanted())stack.addEnchantment(Enchantment.getEnchantmentByLocation("fortune"), 4);
            }else if(stack.getTagCompound().getInteger("head")==5){
                if(!stack.isItemEnchanted())stack.addEnchantment(Enchantment.getEnchantmentByLocation("silk_touch"), 1);
            }else{
                NBTTagCompound tag = stack.getTagCompound();
                tag.removeTag("ench");
                stack.setTagCompound(tag);
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if(stack.getTagCompound()!=null && stack.getTagCompound().getInteger("maxEnergy")==-1)return false;
        return (getDurabilityForDisplay(stack)!=0);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    public int getEnergyCost(ItemStack stack){
        if(stack.getTagCompound().getInteger("maxEnergy")==-1)return 0;
        int baseCost = RedItems.drillHead.energyCost[stack.getTagCompound().getInteger("head")];
        int cost = RedItems.drillHead.energyCost[stack.getTagCompound().getInteger("head")];
        for(int i=0; i<3; i++){
            if(!stack.getTagCompound().hasKey("aug"+i))continue;
            int aug = stack.getTagCompound().getInteger("aug"+i);
            switch(aug){
                case 1: {
                    cost += baseCost * 0.1;
                }
                case 2:{
                    cost+=baseCost*0.2;
                }
                case 4:{
                    cost += baseCost*2;
                }
            }
        }
        return cost;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if(stack.getTagCompound()==null)return 0;
        return 1-((double)stack.getTagCompound().getInteger("Energy")/(double)stack.getTagCompound().getInteger("maxEnergy"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(stack.getTagCompound()==null){
            tooltip.add(TextFormatting.RED+StringUtils.localize("redstonic.drill.craftedinmodifier"));
        }else{
            NBTTagCompound tag = stack.getTagCompound();
            if(!tag.hasKey("head") || !tag.hasKey("body") || !tag.hasKey("maxEnergy") || !tag.hasKey("Energy")){
                tooltip.add(TextFormatting.YELLOW+"Drill was cheated in, doesnt work.");
                tooltip.add(TextFormatting.YELLOW+"Make a working drill in the Redstonic Modifier.");
                return;
            }
            int head = tag.getInteger("head");
            int energyStored = tag.getInteger("Energy"), maxEnergy = tag.getInteger("maxEnergy");
            if(maxEnergy==-1){
                tooltip.add(StringUtils.localize("redstonic.energy.charge")+": [" + TextFormatting.LIGHT_PURPLE +  "==/==/==/==" + TextFormatting.GRAY + "] " + "101%");
                tooltip.add("-   §k10000§r§7 / §k10000§r§7 RF");
            }else{
                int percent = ((energyStored/100)*100)/(maxEnergy/100);
                tooltip.add(StringUtils.localize("redstonic.energy.charge")+": " + StringUtils.progressBar(energyStored, maxEnergy, 30) + " " + (percent)+"%");
                tooltip.add("-   " + TextFormatting.YELLOW + NumberFormat.getInstance().format(energyStored) + TextFormatting.GRAY +"/" + TextFormatting.YELLOW + NumberFormat.getInstance().format(maxEnergy) + TextFormatting.GRAY + " RF");
            }
            tooltip.add("-   "+ TextFormatting.YELLOW + getEnergyCost(stack) + TextFormatting.GRAY + " RF" + (head==3 ? "/9 "+StringUtils.localize("stat.blocksButton") : "/block"));
            tooltip.add(StringUtils.localize("redstonic.drill.speed")+": " + TextFormatting.RED + getStrVsBlock(stack, null));
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
                int body = tag.getInteger("body"), battery = tag.getInteger("battery");
                tooltip.add("--------------------");
                tooltip.add(TextFormatting.RED+""+TextFormatting.BOLD+StringUtils.localize("stat.blocksButton")+ TextFormatting.RESET + TextFormatting.GRAY+ ": " +tag.getInteger("blocks") +" " + StringUtils.localize("redstonic.drill.blocksmined"));
                tooltip.add(TextFormatting.BOLD+StringUtils.localize("redstonic.drill.head")+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+StringUtils.localize(RedItems.drillHead.heads[head]+"Head.name"));
                tooltip.add(TextFormatting.BOLD+StringUtils.localize("redstonic.drill.body")+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+StringUtils.localize(RedItems.drillBody.bodies[body]+"Body.name"));
                tooltip.add(TextFormatting.BOLD+StringUtils.localize("redstonic.energy.battery")+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+StringUtils.localize(RedItems.battery.names[battery]+"Battery.name"));
            }else{
                tooltip.add(TextFormatting.GRAY+StringUtils.localize("redstonic.info.press", TextFormatting.BLUE+""+TextFormatting.ITALIC+"SHIFT"+TextFormatting.RESET+TextFormatting.GRAY));
//                tooltip.add(TextFormatting.GRAY+String.format(StringUtils.localize("redstonic.info.press"), (TextFormatting.BLUE+""+TextFormatting.ITALIC+"SHIFT"+TextFormatting.RESET+TextFormatting.GRAY)));
            }
            if(hasAugments(stack)){
                if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
                    for (int i = 0; i < 3; i++) {
                        if(stack.getTagCompound().hasKey("aug"+(i))){
                            tooltip.add(TextFormatting.BOLD+StringUtils.localize("redstonic.drill.augment")+" "+(i+1)+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+StringUtils.localize(RedItems.drillAugment.augments[stack.getTagCompound().getInteger("aug"+(i))]+"DrillAugment.name"));
                            if(stack.getTagCompound().getInteger("aug"+i)==3){
                                String hotswapHead = "No head selected.";
                                if(stack.getTagCompound()!=null && stack.getTagCompound().hasKey("hotswapHead") && stack.getTagCompound().getInteger("hotswapHead")>-1) hotswapHead = RedItems.drillHead.heads[stack.getTagCompound().getInteger("hotswapHead")]+" Hotswap Head";
                                tooltip.add("-  "+TextFormatting.DARK_GRAY+""+TextFormatting.ITALIC+hotswapHead);
                            }
                        }
                    }
                }else{
                    tooltip.add(TextFormatting.GRAY+StringUtils.localize("redstonic.info.press", TextFormatting.RED+""+TextFormatting.ITALIC+"CTRL"+TextFormatting.RESET+TextFormatting.GRAY));
                }
            }
        }
    }

    public boolean hasAugments(ItemStack stack){
        for (int i=0; i<3; i++){
            if(stack.getTagCompound().hasKey("aug"+i))return true;
        }
        return false;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if(stack.getTagCompound()==null){
            stack.setTagCompound(new NBTTagCompound());
        }
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(playerIn.isSneaking() && itemStackIn!=null && itemStackIn.getItem() instanceof Drill && itemStackIn.getTagCompound()!=null){
            if(hasAugment(3, itemStackIn)){
                int headNow = itemStackIn.getTagCompound().getInteger("head"), headHot = itemStackIn.getTagCompound().getInteger("hotswapHead");
                NBTTagCompound tag = itemStackIn.getTagCompound();
                tag.setInteger("head", headHot);
                tag.setInteger("hotswapHead", headNow);
                itemStackIn.setTagCompound(tag);
            }
        }
        System.out.println(itemStackIn.getTagCompound());
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        float speed = 0F;
        NBTTagCompound tag = stack.getTagCompound();

        if(tag==null)return 0.1F;
        if(tag.getInteger("Energy")<getEnergyCost(stack) && tag.getInteger("maxEnergy")!=-1)return 0.5F;
        List<Block> list = Arrays.asList(new Block[]{Blocks.GRASS, Blocks.DIRT, Blocks.LEAVES, Blocks.LEAVES2});
        int head = tag.getInteger("head");
        switch(head){
            case 0: speed = (float) Config.ironDrillSpeed; break;
            case 1: speed = (float) Config.goldDrillSpeed; break;
            case 2: speed = (float) Config.diamondDrillSpeed; break;
            case 3: speed = (float) Config.heavyDrillSpeed; break;
            case 4: speed = (float) Config.fortuitousDrillSpeed; break;
            case 5: speed = (float) Config.silkyDrillSpeed; break;
            case 6: speed = (float) Config.blazerDrillSpeed; break;
            case 7: speed = 200F; break;
        }
        float baseSpeed = speed;
        float add = 0;
        for(int i=0; i<3; i++){
            if(!stack.getTagCompound().hasKey("aug"+i))continue;
            int aug = stack.getTagCompound().getInteger("aug"+i);
            switch(aug){
                case 1:
                    add += baseSpeed * 0.3F;
                    break;
                case 2:
                    add += baseSpeed * 0.4F;
                    break;
                case 4:
                    add -= baseSpeed/2;
                    break;
            }
        }
        return speed+add;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    public void threebythree(EntityPlayer player, World world, IBlockState state, BlockPos pos, ItemStack stack){
        threebythree(player, world, state, pos, stack, false);
    }

    public void threebythree(EntityPlayer player, World world, IBlockState state, BlockPos pos, ItemStack stack, boolean silk){
        int ogx = pos.getX(); int ogy = pos.getY(); int ogz = pos.getZ();
        NBTTagCompound tag = stack.getTagCompound();
        RayTraceResult mop = this.rayTrace(world, player, false);
        int x = pos.getX(), y = pos.getY(), z = pos.getZ(), head = stack.getTagCompound().getInteger("head");
        if(mop !=null && !world.isRemote){
            if(mop.sideHit== EnumFacing.UP || mop.sideHit==EnumFacing.DOWN){
                x = x-1; z = z-1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, state.getBlock(), ogx, ogy, ogz, x+i, y, z+r)) {
                        BlockPos currPos = new BlockPos(x + i, y, z + r);
                        IBlockState currBlock = world.getBlockState(currPos);
                        if(currBlock.getBlock()==Blocks.AIR)continue;
                        tag.setInteger("blocks", tag.getInteger("blocks") + 1);
                        boolean shouldDrop = !silk;
                        if(head==5){
                            currBlock.getBlock().spawnAsEntity(world, currPos, new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                        }else if(head==6){
                            final ItemStack drop = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                            if(drop==null)shouldDrop = true;
                            else Block.spawnAsEntity(world, currPos, drop.copy());
                        }
                        world.destroyBlock(currPos, shouldDrop);
                    }
                }}
            }else if(mop.sideHit==EnumFacing.NORTH || mop.sideHit==EnumFacing.SOUTH){
                x=x+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, state.getBlock(), ogx, ogy, ogz, x-i, y-r, z)) {
                        BlockPos currPos = new BlockPos(x - i, y - r, z);
                        IBlockState currBlock = world.getBlockState(currPos);
                        if(currBlock.getBlock()==Blocks.AIR)continue;
                        tag.setInteger("blocks", tag.getInteger("blocks") + 1);
                        boolean shouldDrop = !silk;
                        if(head==5){
                            currBlock.getBlock().spawnAsEntity(world, currPos, new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                        }else if(head==6){
                            final ItemStack drop = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                            if(drop==null)shouldDrop = true;
                            else Block.spawnAsEntity(world, currPos, drop.copy());
                        }
                        world.destroyBlock(currPos, shouldDrop);
                    }
                }}
            }else if(mop.sideHit==EnumFacing.WEST || mop.sideHit== EnumFacing.EAST){
                z=z+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, state.getBlock(), ogx, ogy, ogz, x, y-r, z-i)) {
                        BlockPos currPos = new BlockPos(x, y - r, z - i);
                        IBlockState currBlock = world.getBlockState(currPos);
                        if(currBlock.getBlock()==Blocks.AIR)continue;
                        tag.setInteger("blocks", tag.getInteger("blocks") + 1);
                        boolean shouldDrop = !silk;
                        if(head==5){
                            currBlock.getBlock().spawnAsEntity(world, currPos, new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                        }else if(head==6){
                            final ItemStack drop = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(currBlock.getBlock(), 1, currBlock.getBlock().getMetaFromState(currBlock)));
                            if(drop==null)shouldDrop = true;
                            else Block.spawnAsEntity(world, currPos, drop.copy());
                        }
                        world.destroyBlock(currPos, shouldDrop);
                    }
                }}
            }
        }
        stack.setTagCompound(tag);
    }

    public static boolean hasAugment(int i, ItemStack stack){
        NBTTagCompound nbt = stack.getTagCompound();
        for (int j = 0; j < 3; j++) {
            if(nbt.getInteger("aug"+j)==i)return true;
        }
        return false;
    }

    public boolean checkHeavy(World world, Block block, int x, int y, int z, int bx, int by, int bz){
        Block breakingBlock = world.getBlockState(new BlockPos(bx,by,bz)).getBlock();
        boolean checkHardness = breakingBlock.getBlockHardness(breakingBlock.getDefaultState(), world, new BlockPos(bx,by,bz))<=block.getBlockHardness(block.getDefaultState(), world, new BlockPos(x,y,z))+10F;
        boolean checkTE = !breakingBlock.hasTileEntity(breakingBlock.getDefaultState());
        return checkHardness && checkTE;
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        NBTTagCompound tag = stack.getTagCompound();
        int energyStored = tag.getInteger("Energy");
        int energyReceived = Math.min(stack.getTagCompound().getInteger("maxEnergy")  - energyStored, Math.min(RedItems.battery.maxReceive[stack.getTagCompound().getInteger("battery")] , maxReceive));
        if (!simulate) {
            energyStored += energyReceived;
            tag.setInteger("Energy", energyStored);
            stack.setTagCompound(tag);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        if(stack.getTagCompound()==null || !stack.getTagCompound().hasKey("Energy")){
            return 0;
        }
        return stack.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return stack.getTagCompound().getInteger("maxEnergy");
    }
}
