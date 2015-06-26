package com.raizunne.redstonic.Item;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import scala.actors.threadpool.Arrays;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class RedstonicDrill extends ItemPickaxe implements IEnergyContainerItem {

    int head; int boderino; int maxEnergy; int receive; int timer;

    public RedstonicDrill() {
        super(Item.ToolMaterial.EMERALD);
        setCreativeTab(Redstonic.redTab);
        setUnlocalizedName("RedstonicDrill");
        setNoRepair();
        setMaxDamage(80);
        setTextureName("redstonic:Drill/PlaceHolder");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean yowtf) {
        if (stack.stackTagCompound == null) {
            list.add("Crafted in the" + EnumChatFormatting.YELLOW + " Drill Modifier");
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add("Needed to craft: " + EnumChatFormatting.YELLOW + "Drill Head, ");
                list.add(EnumChatFormatting.YELLOW + "Drill Body, Battery");
                list.add(EnumChatFormatting.YELLOW + "(Flux Capacitor or Battery)");
            } else {
                list.add(Lang.translate("drill.hold") + " " + EnumChatFormatting.RED + Lang.translate("util.Shift") + EnumChatFormatting.RESET + " " + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo"));
            }
        } else {
            NBTTagCompound tag = stack.stackTagCompound;
            String prefix; String sufix; String head, body; String speed; boolean creative=false;

            float multi = tag.getFloat("energyMulti");
            if (multi == 0) {
                multi = 1;
            }
            if(tag.getInteger("body")==3){
                multi=3;
            }

            float multiplier = 1;
            if (hasAugment(2, stack)) {
                multiplier += 1.5;
            }
            if(tag.getInteger("maxEnergy")==-1 || tag.getInteger("maxEnergy")==-2 || tag.getInteger("maxEnergy")==-5){
                creative = true;
            }
            prefix = DrillUtil.getDrillHeadName(tag.getInteger("head"));
            head = DrillUtil.getDrillHeadName(tag.getInteger("head")) + " Head";
            sufix = DrillUtil.getDrillBodyName(tag.getInteger("body"));
            body = DrillUtil.getDrillBodyName(tag.getInteger("body")) + " Body";
            speed = "" + this.getDigSpeed(stack, Blocks.stone, 0) + "F";

            list.add(EnumChatFormatting.GREEN + "Energy: " + EnumChatFormatting.GRAY + (creative?EnumChatFormatting.OBFUSCATED + "Creative" +
                    EnumChatFormatting.RESET + EnumChatFormatting.GRAY: Lang.addComas(tag.getInteger("energy"))) + "/" + (creative?"Creative":Lang.addComas(tag.getInteger("maxEnergy"))) + " RF");
            if(tag.getBoolean("mode")){
                list.add(EnumChatFormatting.DARK_AQUA + "Low Power Mode");
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.head") + ": " + EnumChatFormatting.DARK_GRAY + head);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.body") + ": " + EnumChatFormatting.DARK_GRAY + body);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.battery") + ": " + EnumChatFormatting.DARK_GRAY + (creative ? "Creative":DrillUtil.getBatteryName(tag.getInteger("battery"))));
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.digspeed") + ": " + EnumChatFormatting.DARK_GRAY + (tag.getInteger("head")==7 ? EnumChatFormatting.OBFUSCATED + "100F":speed));
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.energyusage") + ": " + EnumChatFormatting.YELLOW + calcEnergy(stack) + " RF");
                list.add(EnumChatFormatting.GRAY + "Blocks Destroyed: " + EnumChatFormatting.RED + Lang.addComas(tag.getInteger("blocks")));
                if(tag.getInteger("head")==7){
                    list.add(EnumChatFormatting.DARK_GRAY + "Sneak right click to");
                    list.add(EnumChatFormatting.DARK_GRAY + "toggle low power mode.");
                }
            }else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !hasNOAugment(stack)) {
                if(tag.getInteger("aug1")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug1"))); }
                if(tag.getInteger("aug2")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug2"))); }
                if(tag.getInteger("aug3")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug3"))); }
                if(hasAugment(3, stack)){ list.add(EnumChatFormatting.RED + "Hotswap: " + EnumChatFormatting.GRAY + DrillUtil.getDrillHeadName(tag.getInteger("hotswapHead")) + " Drill Head");}
            }else{
                list.add(Util.ItemShiftInfo);
                if(!hasNOAugment(stack)){list.add(Util.ItemCtrlInfo);}
            }
        }
    }

    public void gratzMessage(EntityPlayer player, int i, ItemStack stack){
        if(!player.worldObj.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Redstonic Drill: " + EnumChatFormatting.WHITE + "You've reached " + i + " mined blocks! Way to go!"));
            stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if (nbt == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("head", head);
            stack.stackTagCompound.setInteger("body", boderino);
            stack.stackTagCompound.setInteger("maxEnergy", maxEnergy);
            stack.stackTagCompound.setBoolean("magnet", false);
        } else {
            EntityPlayer player = null;
            if(entity instanceof EntityPlayer){
                player = (EntityPlayer)entity;
            }
            if(hasAugment(5, stack) && !nbt.getBoolean("aug" + getAugNumber(5, stack) + "Deactivated")){
                AxisAlignedBB abb = AxisAlignedBB.getBoundingBox(player.posX-4, player.posY-5, player.posZ-4, player.posX+4, player.posY+5, player.posZ+4);
                List items = world.getEntitiesWithinAABB(EntityItem.class, abb);
                for(int i=0;i<items.size();i++){
                    EntityItem iEntity = (EntityItem) items.get(i);
                    world.spawnParticle("reddust", iEntity.posX, iEntity.posY, iEntity.posZ, 0F, 0F, 0F);
                    if(iEntity.getDistanceSqToEntity(entity)>1D) {
                        iEntity.setLocationAndAngles(player.posX, player.posY - 1, player.posZ, 1F, 1F);
                    }
                }
            }

            if(nbt.getInteger("milestone")==0){
                nbt.setInteger("milestone", 500);
            }
            if(nbt.getInteger("blocks")>=nbt.getInteger("milestone")){
                gratzMessage(player, nbt.getInteger("milestone"), stack);
                nbt.setInteger("milestone", nbt.getInteger("milestone")+500);
            }

            stack.stackTagCompound.removeTag("ench");

            if (nbt.getInteger("head") == 4 && !Util.hasEnchantment(stack, Enchantment.fortune)) {
                stack.addEnchantment(Enchantment.fortune, 5);
            }
            if (nbt.getInteger("head") == 5 && !Util.hasEnchantment(stack, Enchantment.silkTouch)) {
                stack.addEnchantment(Enchantment.silkTouch, 1);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(!player.isSneaking()) {
            if(hasAugment(4, stack)) {
                placeBlock(Block.getBlockById(nbt.getInteger("placeBlock")), nbt.getInteger("placeBlockMeta"), world, player);
            }else{
                placeTorch(world, player);
            }
        }
        if (player.isSneaking()) {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
            if(pos==null && stack.stackTagCompound.getInteger("head")==7){
                if(nbt.getBoolean("mode")){
                    nbt.setBoolean("mode", false);
                    player.playSound("random.pop", 1.0F, 1.0F);
                }else{
                    nbt.setBoolean("mode", true);
                    player.playSound("random.pop", 1.0F, 1.0F);
                }
            }
            if(pos!=null && pos.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK) {
                if(Block.getBlockById(nbt.getInteger("placeBlock"))!=world.getBlock(pos.blockX, pos.blockX, pos.blockZ) && hasAugment(4, stack)){
                    player.playSound("fire.ignite", 0.5F, 1F);
                }
                nbt.setInteger("placeBlock", Block.getIdFromBlock(world.getBlock(pos.blockX, pos.blockY, pos.blockZ)));
                nbt.setInteger("placeBlockMeta", world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ));
            }
            if(hasAugment(5, stack)){
                if(stack.stackTagCompound.getBoolean("aug" + getAugNumber(5, stack) + "Deactivated")){
                    stack.stackTagCompound.setBoolean("aug" + getAugNumber(5, stack) + "Deactivated", false);
                    player.playSound("note.harp", 1F, 1F);
                }else{
                    stack.stackTagCompound.setBoolean("aug" + getAugNumber(5, stack) + "Deactivated", true);
                    player.playSound("note.bass", 1F, 1F);
                }
            }
            if (hasAugment(3, stack) && checkEnergy(stack, 1500) && pos==null) {
                if (nbt.getInteger("hotswapHead") != -1) {
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

    public void placeBlock(Block block, int meta, World world, EntityPlayer player){
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(player.inventory.hasItemStack(new ItemStack(block, 0, meta))){
            String sound = block.stepSound.func_150496_b();
            if(pos!=null && pos.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK){
                int posX = pos.blockX; int posY = pos.blockY; int posZ = pos.blockZ;
                switch (pos.sideHit) {
                    case 0:
                        if(world.getBlock(posX, posY-1, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY - 1, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 1:
                        if(world.getBlock(posX, posY+1, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY + 1, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 2:
                        if(world.getBlock(posX, posY, posZ-1)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY, posZ - 1, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 3:
                        if(world.getBlock(posX, posY, posZ+1)==Blocks.air){
                            if (!world.isRemote) {
                                world.setBlock(posX, posY, posZ + 1, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 4:
                        if(world.getBlock(posX-1, posY, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX - 1, posY, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 5:
                        if(world.getBlock(posX+1, posY, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX + 1, posY, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                }
            }
        }
    }

    public void placeTorch(World world, EntityPlayer player){
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        Block block = null;
        Block torchVanilla = Blocks.torch;
        Block torchTCon = null;
        if(Loader.isModLoaded("TConstruct")){
            torchTCon = GameRegistry.findBlock("TConstruct", "decoration.stonetorch");
            if(player.inventory.hasItemStack(new ItemStack(Blocks.torch))){
                block = Blocks.torch;
            }else if(player.inventory.hasItemStack(GameRegistry.findItemStack("TConstruct", "decoration.stonetorch", 1))){
                block = GameRegistry.findBlock("TConstruct", "decoration.stonetorch");
            }
        }else{
            block = Blocks.torch;
            torchTCon = Blocks.torch;
        }
        if(player.inventory.hasItemStack(new ItemStack(block))){
            if(pos!=null && pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int posX = pos.blockX; int posY = pos.blockY; int posZ = pos.blockZ;
                if(world.getBlock(posX, posY, posZ)!=torchVanilla && world.getBlock(posX, posY, posZ)!=torchTCon && pos.sideHit!=0) {
                    switch (pos.sideHit) {
                        case 1:
                            if(world.getBlock(posX, posY+1, posZ)!=torchVanilla && world.getBlock(posX, posY+1, posZ)!=torchTCon && world.getBlock(posX, posY+1, posZ)==Blocks.air) {
                                if(!world.isRemote) {
                                    world.setBlock(posX, posY+1, posZ, block, 5, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 2:
                            if(world.getBlock(posX, posY, posZ-1)!=torchVanilla && world.getBlock(posX, posY, posZ-1)!=torchTCon && world.getBlock(posX, posY, posZ-1)==Blocks.air) {
                                if(!world.isRemote) {
                                    world.setBlock(posX, posY, posZ-1, block, 4, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 3:
                            if(world.getBlock(posX, posY, posZ+1)!=torchVanilla && world.getBlock(posX, posY, posZ+1)!=torchTCon && world.getBlock(posX, posY, posZ+1)==Blocks.air) {
                                if (!world.isRemote) {
                                    world.setBlock(posX, posY, posZ+1, block, 3, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 4:
                            if(world.getBlock(posX-1, posY, posZ)!=torchVanilla && world.getBlock(posX-1, posY, posZ)!=torchTCon && world.getBlock(posX-1, posY, posZ)==Blocks.air) {
                                if (!world.isRemote) {
                                    world.setBlock(posX - 1, posY, posZ, block, 2, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 5:
                            if(world.getBlock(posX+1, posY, posZ)!=torchVanilla && world.getBlock(posX+1, posY, posZ)!=torchTCon && world.getBlock(posX+1, posY, posZ)==Blocks.air) {
                                if (!world.isRemote) {
                                    world.setBlock(posX+1, posY, posZ, block, 1, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                    }

                }
            }
        }
    }

    public static boolean hasAugment(int i, ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1") == i || nbt.getInteger("aug2") == i || nbt.getInteger("aug3") == i;
    }

    public static int getAugNumber(int i, ItemStack drill){
        int[] augments = {drill.stackTagCompound.getInteger("aug1"), drill.stackTagCompound.getInteger("aug2"), drill.stackTagCompound.getInteger("aug3")};
        for(int interino=0; interino<augments.length; interino++){
            if(augments[interino]==i){
                return interino+1;
            }
        }
        return 0;
    }

    public boolean hasNOAugment(ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1")==0 && nbt.getInteger("aug2")==0 && nbt.getInteger("aug3")==0;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (stack.stackTagCompound.getInteger("head") == 3 && checkEnergy(stack, calcEnergy(stack))) {
                threebythree(player, world, block, x, y, z, stack);
            }
            if(stack.stackTagCompound.getInteger("head")==6){
                player.playSound("mob.ghast.fireball", 0.5F, 2F);
                world.spawnParticle("flame", player.posX, player.posY+1, player.posZ,1, 1, 1);
            }
        }
        takeEnergy(stack, calcEnergy(stack));
        if(stack.stackTagCompound.getInteger("head")!=3){stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);}
        int energy = stack.stackTagCompound.getInteger("energy");
        int maxEnergy = stack.stackTagCompound.getInteger("maxEnergy");
        double modifier = (double) 80 / maxEnergy;
        stack.setItemDamage((int) (80 - energy * modifier));
        return true;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if(!checkEnergy(stack, calcEnergy(stack))){
            return 0.1F;
        }

        if(stack.stackTagCompound.getInteger("head")==6 && block==Blocks.netherrack){
            return 5F;
        }else{
            return getBaseDigSpeed(stack, block);
        }
    }

    public static float getBaseDigSpeed(ItemStack stack, Block block){
        NBTTagCompound tag = stack.stackTagCompound;
        List<Block> list = Arrays.asList(new Block[]{Blocks.grass, Blocks.dirt, Blocks.leaves});
        float multiplier = 1;
        if(hasAugment(1, stack)){
            multiplier += 0.3F;
        }

        if(hasAugment(6, stack)){
            multiplier += 0.45F;
        }

        if(stack.stackTagCompound.getBoolean("mode")){
            multiplier=0.2F;
            if(list.contains(block)){
                multiplier=0.05F;
            }
        }

        int head = tag.getInteger("head");
        switch(head){
            case 0: return 10F * multiplier;
            case 1: return 25F * multiplier;
            case 2: return 15F * multiplier;
            case 3: return 8F * multiplier;
            case 4: return 5F * multiplier;
            case 5: return 8F * multiplier;
            case 6: return 10F * multiplier;
            case 7: return 200F * multiplier;
            default: return 1F;
        }
    }

    public void threebythree(EntityPlayer player, World world, Block block, int x, int y, int z, ItemStack stack){
        int ogx = x; int ogy = y; int ogz = z;

        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(mop !=null && !world.isRemote){
            if(mop.sideHit==0 || mop.sideHit==1){
                x = x-1; z = z-1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x+i, y, z+r)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x + i, y, z + r, true);
                    }
                }}
            }else if(mop.sideHit==2 || mop.sideHit==3){
                x=x+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x-i, y-r, z)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x - i, y - r, z, true);
                    }
                }}
            }else if(mop.sideHit==4 || mop.sideHit==5){
                z=z+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x, y-r, z-i)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x, y - r, z - i, true);
                    }
                }}
            }
        }
    }

    IIcon[] heads;
    IIcon[] body;
    IIcon[] augments;
    IIcon[] augmentsALT;
    IIcon[] extras;

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        heads = new IIcon[8];
        body = new IIcon[6];
        augments = new IIcon[7];
        augmentsALT = new IIcon[7];
        extras = new IIcon[1];

        heads[0] = i.registerIcon("redstonic:Drill/Heads/Render/Iron");
        heads[1] = i.registerIcon("redstonic:Drill/Heads/Render/Gold");
        heads[2] = i.registerIcon("redstonic:Drill/Heads/Render/Diamond");
        heads[3] = i.registerIcon("redstonic:Drill/Heads/Render/Heavy");
        heads[4] = i.registerIcon("redstonic:Drill/Heads/Render/Fortuitous");
        heads[5] = i.registerIcon("redstonic:Drill/Heads/Render/Silky");
        heads[6] = i.registerIcon("redstonic:Drill/Heads/Render/Blazer");
        heads[7] = i.registerIcon("redstonic:Drill/Heads/Render/Ultimate");

        body[0] = i.registerIcon("redstonic:Drill/Bodies/Render/Iron");
        body[1] = i.registerIcon("redstonic:Drill/Bodies/Render/Electrum");
        body[2] = i.registerIcon("redstonic:Drill/Bodies/Render/Enderium");
        body[3] = i.registerIcon("redstonic:Drill/Bodies/Render/End");
        body[4] = i.registerIcon("redstonic:Drill/Bodies/Render/Energetic");
        body[5] = i.registerIcon("redstonic:Drill/Bodies/Render/Vibrant");

        augments[0] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[1] = i.registerIcon("redstonic:Drill/Augment/Render/Speed");
        augments[2] = i.registerIcon("redstonic:Drill/Augment/Render/Energy");
        augments[3] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[4] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[5] = i.registerIcon("redstonic:Drill/Augment/Render/MagnetON");
        augments[6] = i.registerIcon("redstonic:Drill/Augment/Render/Null");

        augmentsALT[0] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augmentsALT[1] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augmentsALT[2] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augmentsALT[3] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augmentsALT[4] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augmentsALT[5] = i.registerIcon("redstonic:Drill/Augment/Render/MagnetOFF");
        augmentsALT[6] = i.registerIcon("redstonic:Drill/Augment/Render/Null");

        extras[0] = i.registerIcon("redstonic:Drill/Extras/ModeOn");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        NBTTagCompound nbt = stack.stackTagCompound;
        if(nbt!=null){
            if(pass==0){
                return heads[nbt.getInteger("head")];
            }
            if(pass==1){
                return body[nbt.getInteger("body")];
            }
            if(pass==2){
                return !nbt.getBoolean("aug1Deactivated")?augments[nbt.getInteger("aug1")] : augmentsALT[nbt.getInteger("aug1")];
            }
            if(pass==3){
                return !nbt.getBoolean("aug2Deactivated")?augments[nbt.getInteger("aug2")] : augmentsALT[nbt.getInteger("aug2")];
            }
            if(pass==4){
                return !nbt.getBoolean("aug3Deactivated")?augments[nbt.getInteger("aug3")] : augmentsALT[nbt.getInteger("aug3")];
            }
            if(pass==5){
                if(nbt.getBoolean("mode")){
                    return extras[0];
                }else{
                    return augmentsALT[0];
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
            if(pass==5){
                return augmentsALT[0];
            }
        }
        return null;
    }

    public boolean checkHeavy(World world, Block block, int x, int y, int z, int bx, int by, int bz){
        Block blocktwo = world.getBlock(bx,by,bz);
        if(blocktwo==Blocks.bedrock || blocktwo==Blocks.mob_spawner){
            return false;
        }
        if((world.getTileEntity(bx, by, bz)==null || block==Blocks.redstone_ore)
                && world.getBlock(x, y, z).getBlockHardness(world, x, y, z) <= block.getBlockHardness(world, bx, by, bz)+5F && !world.getBlock(bx, by, bz).isFlammable(world, bx, by, bz, null) &&
                !(world.getBlock(bx, by, bz) instanceof BlockCrops && world.getBlock(bx, by, bz)!=Blocks.bedrock) && block.getRenderType()==0 && world.getBlock(bx,by,bz).getRenderType()==0){
            return true;
        }
        return false;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 6;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public boolean checkEnergy(ItemStack stack, int i){
        if(stack.stackTagCompound.getInteger("maxEnergy")==-1 || stack.stackTagCompound.getInteger("maxEnergy")==-2 || stack.stackTagCompound.getInteger("maxEnergy")==-5){
            return true;
        }else if(stack.stackTagCompound.getInteger("energy")>=i){
            return true;
        }else{
            return false;
        }
    }

    public int calcEnergy(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(hasAugment(1, stack)){
            multiplier = multiplier + 0.2F;
        }
        if(hasAugment(2, stack)){
            multiplier = multiplier + 0.2F;
        }
        int head = tag.getInteger("head");
        switch(head){
            case 0:	return (int)(400 * multiplier);
            case 1: return (int)(600 * multiplier);
            case 2: return (int)(500 * multiplier);
            case 3: return (int)(1800 * multiplier);
            case 4: return (int)(1000 * multiplier);
            case 5: return (int)(700 * multiplier);
            case 6: return (int)(100 * multiplier);
            case 7: return (int)(3500 * multiplier);
            default: return 0;
        }
    }

    public int getInput(ItemStack stack){
        int body = (stack.stackTagCompound.getInteger("body"));
        switch(body){
            case 0: return 1000;
            case 1:case 4: return 10000;
            case 2:case 5: return 32000;
            case 3: return 50000;
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
        int maxEnergy = container.stackTagCompound.getInteger("maxEnergy");
        int energy = container.stackTagCompound.getInteger("energy");
        int energyReceived = Math.min(maxEnergy - energy, Math.min(getInput(container), maxReceive));
        if(energy>=maxEnergy){
            this.receive = 0;
        }

        if (!simulate) {
            energy += energyReceived;
            this.receive = energyReceived;
            container.stackTagCompound.setInteger("energy", energy);
        }
        double modifier = (double)80/maxEnergy;
        container.setItemDamage((int)(80 - energy*modifier));
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("maxEnergy");
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return false;
    }
}