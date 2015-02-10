package com.raizunne.redstonic.Item;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.Lang;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class RedstonicDrill extends ItemPickaxe implements IEnergyContainerItem {

    public RedstonicDrill(ToolMaterial material) {
        super(material);
        setCreativeTab(Redstonic.redTab);
        setUnlocalizedName("RedstonicDrill");
        setNoRepair();
        setMaxDamage(80);
        setTextureName("redstonic:Drill/PlaceHolder");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean yowtf) {
        if(stack.stackTagCompound==null){
            list.add("Will not work.");
            list.add("Crafted in the" + EnumChatFormatting.YELLOW + " Drill Modifier");
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                list.add("Needed to craft: " + EnumChatFormatting.YELLOW + "Drill Head, ");
                list.add(EnumChatFormatting.YELLOW + "Drill Body, Enery Capacitor(TE)");
            }else{
                list.add(Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + " Shift " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo"));
            }
        }else{
            NBTTagCompound tag = stack.stackTagCompound;
            list.clear();
            String prefix = "";
            String sufix = "";
            String head = "", body = "", capacitor="", speed="";

            float multi = tag.getFloat("energyMulti");
            if(multi==0){
                multi=1;
            }

            float multiplier = tag.getFloat("speedMulti");
            if(multiplier==0){
                multiplier=1;
            }

            switch((int)(tag.getInteger("maxEnergy")/multi)){
                case 16000: capacitor = "Hardened Capacitor"; break;
                case 24000: capacitor = "Reinforced Capacitor"; break;
                case 32000: capacitor = "Resonant Capacitor"; break;
            }

            switch(tag.getInteger("head")){
                case 0: prefix = Lang.translate("drill.iron");        head =  Lang.translate("drill.iron") + " Head";       speed = "" + 10F * multiplier + "F"; break;
                case 1: prefix = Lang.translate("drill.gold");        head =  Lang.translate("drill.gold") + " Head";       speed = "" + 20F * multiplier + "F"; break;
                case 2: prefix = Lang.translate("drill.diamond");     head =  Lang.translate("drill.diamond") + " Head";    speed = "" + 15F * multiplier + "F"; break;
                case 3: prefix = Lang.translate("drill.heavy");       head =  Lang.translate("drill.heavy") + " Head";      speed = "" + 10F * multiplier + "F - 3x3"; break;
                case 4: prefix = Lang.translate("drill.fortuitous");  head =  Lang.translate("drill.fortuitous") + " Head"; speed = "" + 5F * multiplier + "F - Fortune"; break;
                case 5: prefix = Lang.translate("drill.silky");       head =  Lang.translate("drill.silky") + " Head";      speed = "" + 8F * multiplier + "F - Silk"; break;
                default: prefix = Lang.translate("drill.unknown");    head =  Lang.translate("drill.unknown") + " Head";    speed = "" + 1F * multiplier + "F"; break;
            }
            switch(tag.getInteger("body")){
                case 0: sufix = Lang.translate("drill.iron");       body = Lang.translate("drill.iron") + " Body"; break;
                case 1: sufix = Lang.translate("drill.electrum");   body = Lang.translate("drill.electrum") + " Body"; break;
                case 2: sufix = Lang.translate("drill.enderium");   body = Lang.translate("drill.enderium") + " Body"; break;
                default: sufix = Lang.translate("drill.unknown");   body = Lang.translate("drill.unknown") + " Body"; break;
            }
            list.add(prefix+ " " + sufix + EnumChatFormatting.WHITE+" Redstonic Drill");
            list.add("Energy: " + tag.getInteger("energy") + "/" + tag.getInteger("maxEnergy") + " RF");
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                switch(tag.getInteger("aug1")){
                    case 1: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x1.5 Dig Speed Multiplier"); break;
                    case 2: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x2.5 Energy Multiplier"); break;
                    case 3: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "Hotswap"); break;
                }
                switch(tag.getInteger("aug2")){
                    case 1: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x1.5 Dig Speed Multiplier"); break;
                    case 2: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x2.5 Energy Multiplier"); break;
                    case 3: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "Hotswap"); break;
                }
                switch(tag.getInteger("aug3")){
                    case 1: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x1.5 Dig Speed Multiplier"); break;
                    case 2: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "x2.5 Energy Multiplier"); break;
                    case 3: list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + "Hotswap"); break;
                }
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.head") + ": " + EnumChatFormatting.DARK_GRAY + head);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.body") + ": " + EnumChatFormatting.DARK_GRAY + body);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.battery") + ": " + EnumChatFormatting.DARK_GRAY + capacitor);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.digspeed") + ": " + EnumChatFormatting.DARK_GRAY + speed);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.energyusage") + ": " + EnumChatFormatting.YELLOW + calcEnergy(stack) + " RF");
            }else{
                list.add(Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + " Shift " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo"));
            }
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(nbt==null){
            stack.stackTagCompound = new NBTTagCompound();
        }else{
            if(nbt.getInteger("head")==4 && !stack.isItemEnchanted()){
                stack.addEnchantment(Enchantment.fortune, 4);
            }
            if(nbt.getInteger("head")==5 && !stack.isItemEnchanted()){
                stack.addEnchantment(Enchantment.silkTouch, 1);
            }
            if(nbt.getInteger("head")!=4 && nbt.getInteger("head")!=5){
                stack.stackTagCompound.removeTag("ench");
            }
        }
    }

    IIcon[] heads;
    IIcon[] body;
    IIcon[] augments;

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(player.isSneaking()){
            if(((nbt.getInteger("aug1")==3 || nbt.getInteger("aug2")==3 || nbt.getInteger("aug3")==3)) && nbt.getInteger("energy")>=1500){
                if(nbt.getInteger("hotswapHead")!=-1){
                    int temp = nbt.getInteger("head");
                    nbt.setInteger("head", nbt.getInteger("hotswapHead"));
                    nbt.setInteger("hotswapHead", temp);
                    takeEnergy(stack, 1500);
                    player.playSound("random.pop", 1.0F, 1.0F);
                }
            }
        }
        return stack;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        if(entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)entityLiving;
            if(stack.stackTagCompound.getInteger("head")==3 && stack.stackTagCompound.getInteger("energy")>=800  && block!= Blocks.dirt && block!=Blocks.sand && block!=Blocks.grass){
                threebythree(player, world, block, x, y, z);
            }
        }
        takeEnergy(stack, calcEnergy(stack));
        int energy = stack.stackTagCompound.getInteger("energy");
        int maxEnergy = stack.stackTagCompound.getInteger("maxEnergy");
        double modifier = (double)80/maxEnergy;
        stack.setItemDamage((int)(80 - energy*modifier));
        return true;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(tag.getInteger("aug1")==0 || tag.getInteger("aug2")==0 || tag.getInteger("aug3")==0){
            multiplier = multiplier + 0.5F;
        }

        if(tag.getInteger("energy")<100){
            return 0.1F;
        }

        int head = tag.getInteger("head");
        switch(head){
            case 0: return 10F * multiplier;
            case 1: return 20F * multiplier;
            case 2: return 15F * multiplier;
            case 3: return 5F * multiplier;
            case 4: return 5F * multiplier;
            case 5: return 8F * multiplier;
            default: return 1F;
        }
    }

    public void threebythree(EntityPlayer player, World world, Block block, int x, int y, int z){
        int ogx = x; int ogy = y; int ogz = z;

        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(mop !=null && !world.isRemote){
            if(mop.sideHit==0 || mop.sideHit==1){
                x = x-1; z = z-1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x+i, y, z+r)) {
                        world.func_147480_a(x + i, y, z + r, true);
                    }
                }}
            }else if(mop.sideHit==2 || mop.sideHit==3){
                x=x+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x-i, y-r, z)) {
                        world.func_147480_a(x - i, y - r, z, true);
                    }
                }}
            }else if(mop.sideHit==4 || mop.sideHit==5){
                z=z+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x, y-r, z-i)) {
                        world.func_147480_a(x, y - r, z - i, true);
                    }
                }}
            }
        }
    }

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        heads = new IIcon[6];
        body = new IIcon[3];
        augments = new IIcon[3];

        heads[0] = i.registerIcon("redstonic:Drill/Heads/Render/Iron");
        heads[1] = i.registerIcon("redstonic:Drill/Heads/Render/Gold");
        heads[2] = i.registerIcon("redstonic:Drill/Heads/Render/Diamond");
        heads[3] = i.registerIcon("redstonic:Drill/Heads/Render/Heavy");
        heads[4] = i.registerIcon("redstonic:Drill/Heads/Render/Fortuitous");
        heads[5] = i.registerIcon("redstonic:Drill/Heads/Render/Silky");

        body[0] = i.registerIcon("redstonic:Drill/Bodies/Render/Iron");
        body[1] = i.registerIcon("redstonic:Drill/Bodies/Render/Electrum");
        body[2] = i.registerIcon("redstonic:Drill/Bodies/Render/Enderium");

        augments[0] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[1] = i.registerIcon("redstonic:Drill/Augment/Render/Speed");
        augments[2] = i.registerIcon("redstonic:Drill/Augment/Render/Energy");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        NBTTagCompound nbt = stack.stackTagCompound;
        if(nbt!=null){
            if(pass==0){
                switch(nbt.getInteger("head")){
                    case 0: return heads[0];
                    case 1: return heads[1];
                    case 2: return heads[2];
                    case 3: return heads[3];
                    case 4: return heads[4];
                    case 5: return heads[5];
                    default: return heads[0];
                }
            }
            if(pass==1){
                switch(nbt.getInteger("body")){
                    case 0: return body[0];
                    case 1: return body[1];
                    case 2: return body[2];
                    default: return body[0];
                }
            }
            if(pass==2){
                switch(nbt.getInteger("aug1")){
                    case 0: return augments[0];
                    case 1: return augments[1];
                    case 2: return augments[2];
                    default: return augments[0];
                }
            }
            if(pass==3){
                switch(nbt.getInteger("aug2")){
                    case 0: return augments[0];
                    case 1: return augments[1];
                    case 2: return augments[2];
                    default: return augments[0];
                }
            }
            if(pass==4){
                switch(nbt.getInteger("aug3")){
                    case 0: return augments[0];
                    case 1: return augments[1];
                    case 2: return augments[2];
                    default: return augments[0];
                }
            }
        }else if(nbt==null){
            if(pass==0){
                return heads[0];
            }
            if(pass==1){
                return body[0];
            }
            if(pass==2){
                return augments[0];
            }
            if(pass==3){
                return augments[0];
            }
            if(pass==4){
                return augments[0];
            }
        }
        return null;
    }

    public boolean checkHeavy(World world, Block block, int x, int y, int z, int bx, int by, int bz){
        Block blocktwo = world.getBlock(bx,by,bz);
        if(blocktwo==Blocks.bedrock || blocktwo==Blocks.dirt || blocktwo==Blocks.grass || blocktwo==Blocks.glass || blocktwo==Blocks.mob_spawner){
            return false;
        }
        if((world.getTileEntity(bx, by, bz)==null || block==Blocks.redstone_ore)
                && world.getBlock(x, y, z).getBlockHardness(world, x, y, z) <= block.getBlockHardness(world, bx, by, bz)+2F && !world.getBlock(bx, by, bz).isFlammable(world, bx, by, bz, null) && !(world.getBlock(bx, by, bz) instanceof BlockCrops && world.getBlock(bx, by, bz)!=Blocks.bedrock)){
            return true;
        }
        return false;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 5;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public int calcEnergy(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(tag.getInteger("aug1")==0 || tag.getInteger("aug2")==0 || tag.getInteger("aug3")==0){
            multiplier = multiplier + 0.2F;
        }
        if(tag.getInteger("aug1")==1 || tag.getInteger("aug2")==1 || tag.getInteger("aug3")==1){
            multiplier = multiplier + 0.2F;
        }
        int head = tag.getInteger("head");
        switch(head){
            case 0:	return (int)(200 * multiplier);
            case 1: return (int)(400 * multiplier);
            case 2: return (int)(300 * multiplier);
            case 3: return (int)(800 * multiplier);
            case 4: return (int)(800 * multiplier);
            case 5: return (int)(500 * multiplier);
            default: return 0;
        }
    }

    public void takeEnergy(ItemStack stack, int i){
        stack.stackTagCompound.setInteger("energy", stack.stackTagCompound.getInteger("energy")-i);
        double modifier = (double)80/stack.stackTagCompound.getInteger("maxEnergy");
        stack.setItemDamage((int)(80 - stack.stackTagCompound.getInteger("energy")*modifier));
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("energy");
        int maxEnergy = container.stackTagCompound.getInteger("maxEnergy");
        if(container.stackTagCompound.getInteger("energy")>=container.stackTagCompound.getInteger("maxEnergy")){
            energy += 0;
        }else{
            energy += 800;
        }
        container.stackTagCompound.setInteger("energy", energy);
        double modifier = (double)80/maxEnergy;
        container.setItemDamage((int)(80 - energy*modifier));
        return 800;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int available = container.stackTagCompound.getInteger("energy");
        int removed;
        if (maxExtract < available) {
            if (!simulate)
                container.stackTagCompound.setInteger("energy", available - maxExtract);
            removed = maxExtract;
        } else {
            if (!simulate) {
                container.stackTagCompound.setInteger("energy", 0);
            }
            removed = available;
        }
        if (!simulate) {
            container.setItemDamage(container.stackTagCompound.getInteger("maxEnergy") - container.stackTagCompound.getInteger("energy"));
        }

        return removed;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return false;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("maxEnergy");
    }
}