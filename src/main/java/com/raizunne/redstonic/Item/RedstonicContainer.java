package com.raizunne.redstonic.Item;

import com.google.common.eventbus.Subscribe;
import com.raizunne.redstonic.Handler.ConfigHandler;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/04/2015, 01:21 PM.
 */
public class RedstonicContainer extends Item{

    public RedstonicContainer(){
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setNoRepair();
        setUnlocalizedName("RedstonicContainer");
        setTextureName("redstonic:RedstonicContainer");
        setMaxDamage(80);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("block", -1);
            stack.stackTagCompound.setInteger("blockMeta", 0);
            stack.stackTagCompound.setInteger("number", 0);
            stack.stackTagCompound.setBoolean("disabled", false);
        }else{
            if(entity instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer)entity;
                Block block = Block.getBlockById(stack.stackTagCompound.getInteger("block"));
                ItemStack theBlock = new ItemStack(block, 1, stack.stackTagCompound.getInteger("blockMeta"));
                if(player.inventory.hasItemStack(theBlock) && nbt.getInteger("number")< ConfigHandler.containerMax && !stack.stackTagCompound.getBoolean("disabled")){
                    player.inventory.decrStackSize(getInvNumber(player, theBlock), 1);
                    nbt.setInteger("number", nbt.getInteger("number")+1);
                }
                if(stack.stackTagCompound.getInteger("block")!=-1 && stack.stackTagCompound.getInteger("number")==0){
                    stack.stackTagCompound.setInteger("block", -1);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPickUpItem(EntityItemPickupEvent event){
        EntityPlayer player = event.entityPlayer;
        ItemStack picked = event.item.getEntityItem();
        boolean fuckingPicked = false;
        int number = picked.stackSize;
        if(picked.getItem() instanceof ItemBlock){
            List<ItemStack> allContainers = getAllInPlayer(player, new ItemStack(RedstonicItems.RedContainer));
            System.out.println(allContainers);
            Block blockerino = Block.getBlockFromItem(picked.getItem());
            System.out.println(allContainers);
            for(int i=0; i<allContainers.size(); i++){
                Block blockInContainer = Block.getBlockById(allContainers.get(i).stackTagCompound.getInteger("block"));
                int quantity = allContainers.get(i).stackTagCompound.getInteger("number");
                if(blockInContainer.equals(blockerino) && !allContainers.get(i).stackTagCompound.getBoolean("disabled") && allContainers.get(i).stackTagCompound.getInteger("number")<=ConfigHandler.containerMax){
                    if(quantity<ConfigHandler.containerMax){
                        if(quantity+number <= ConfigHandler.containerMax){
                            allContainers.get(i).stackTagCompound.setInteger("number", quantity + number);
                            number = 0;
                        }else if(quantity+number>ConfigHandler.containerMax){
                            allContainers.get(i).stackTagCompound.setInteger("number", ConfigHandler.containerMax);
                            number = (quantity+number)-ConfigHandler.containerMax;
                        }
                    }
                    fuckingPicked = true;
                    break;
                }
            }
        }
        if(fuckingPicked)event.item.getEntityItem().stackSize = 0;
    }

    public int getInvNumber(EntityPlayer player, ItemStack stack){
        for(int i=0; i<player.inventory.getSizeInventory(); i++){
            if(player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).isItemEqual(stack)){
                return i;
            }
        }
        return 0;
    }

    public List<ItemStack> getAllInPlayer(EntityPlayer player, ItemStack stack){
        List<ItemStack> all = new ArrayList<ItemStack>();
        for(int i=0; i<player.inventory.getSizeInventory(); i++){
            if(player.inventory.getStackInSlot(i)!=null && player.inventory.getStackInSlot(i).isItemEqual(stack)){
                all.add(player.inventory.getStackInSlot(i));
            }
        }
        return all;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(entityLiving.worldObj, (EntityPlayer)entityLiving, true);
        if(pos!=null){return false;}
        if(entityLiving.isSneaking()){
            if(stack.stackTagCompound.getBoolean("disabled")){
                stack.stackTagCompound.setBoolean("disabled", false);
                entityLiving.playSound("note.harp", 1F, 1F);
            }else{
                stack.stackTagCompound.setBoolean("disabled", true);
                entityLiving.playSound("note.bass", 0.5F, 1F);
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(stack.stackTagCompound!=null){
            String name = nbt.getInteger("block")==-1?"Empty" : new ItemStack(Item.getItemById(nbt.getInteger("block"))).getDisplayName();
            if(nbt.getBoolean("disabled"))list.add(EnumChatFormatting.BLUE + "Disabled ");
            list.add(EnumChatFormatting.YELLOW + "Contains: " + EnumChatFormatting.GRAY + name);
            list.add(EnumChatFormatting.YELLOW + "Quantity: " + EnumChatFormatting.GRAY + nbt.getInteger("number") + "/" + ConfigHandler.containerMax);
            list.add("Will void extra items when full.");
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                if(nbt.getBoolean("disabled")){
                    list.add(EnumChatFormatting.GRAY + "Disabled: " + EnumChatFormatting.DARK_GRAY + "Will not suck items.");
                }else{
                    list.add(EnumChatFormatting.GRAY + "Sneak left-click to disable.");
                    list.add(EnumChatFormatting.GRAY + "Right click while disabled to retrieve items.");
                }
            }else{
                list.add(Util.ItemShiftInfo);
            }
        }else{
            list.add("Stores up to " + ConfigHandler.containerMax + " items.");
            list.add("Set item by placing it in a crafting table");
            list.add("with the selected item.");
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
        if(stack.stackTagCompound.getBoolean("disabled") && stack.stackTagCompound.getInteger("number")>0){
            Block block = Block.getBlockById(stack.stackTagCompound.getInteger("block"));
            int number = stack.stackTagCompound.getInteger("number");
            if(number>=64){
                if(player.inventory.addItemStackToInventory(new ItemStack(block, 64, stack.stackTagCompound.getInteger("blockMeta")))) {
                    stack.stackTagCompound.setInteger("number", number - 64);
                    player.playSound("random.burp", 0.5F, 2F);
                }
            }else{
                if(player.inventory.addItemStackToInventory(new ItemStack(block, number, stack.stackTagCompound.getInteger("blockMeta")))){
                    stack.stackTagCompound.setInteger("number", 0);
                    player.playSound("random.burp", 0.5F, 2F);
                }
            }
            return stack;
        }
        if(stack.stackTagCompound.getInteger("number")>0) {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
            if(pos==null){return stack;}
            placeBlock(Block.getBlockById(stack.stackTagCompound.getInteger("block")), stack.stackTagCompound.getInteger("blockMeta"), world, player);
            stack.stackTagCompound.setInteger("number", stack.stackTagCompound.getInteger("number") - 1);
        }
        return stack;
    }

    public void returnStack(ItemStack stack, EntityPlayer player){
        Block block = Block.getBlockById(stack.stackTagCompound.getInteger("block"));
        player.inventory.addItemStackToInventory(new ItemStack(block, stack.stackTagCompound.getInteger("blockMeta"), 64));
    }

    public static class Icon{
        public static IIcon icon;
        public static IIcon xOverlay;
    }

    public void placeBlock(Block block, int meta, World world, EntityPlayer player){
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(pos==null){return;}
        String sound = block.stepSound.func_150496_b();
        if(pos!=null && pos.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK){
            int posX = pos.blockX; int posY = pos.blockY; int posZ = pos.blockZ;
            switch (pos.sideHit) {
                case 0:
                    if(world.getBlock(posX, posY-1, posZ)== Blocks.air || world.getBlock(posX, posY-1, posZ) instanceof BlockLiquid) {
                        if (!world.isRemote) {
                            world.setBlock(posX, posY - 1, posZ, block, meta, 2);
                            world.scheduleBlockUpdate(posX, posY - 1, posZ, block, meta);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
                case 1:
                    if(world.getBlock(posX, posY+1, posZ)==Blocks.air || world.getBlock(posX, posY+1, posZ) instanceof BlockLiquid) {
                        if (!world.isRemote) {
                            world.setBlock(posX, posY + 1, posZ, block, meta, 2);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
                case 2:
                    if(world.getBlock(posX, posY, posZ-1)==Blocks.air || world.getBlock(posX, posY, posZ-1) instanceof BlockLiquid) {
                        if (!world.isRemote) {
                            world.setBlock(posX, posY, posZ - 1, block, meta, 2);
                            world.scheduleBlockUpdate(posX, posY, posZ - 1, block, meta);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
                case 3:
                    if(world.getBlock(posX, posY, posZ+1)==Blocks.air || world.getBlock(posX, posY, posZ+1) instanceof BlockLiquid){
                        if (!world.isRemote) {
                            world.setBlock(posX, posY, posZ + 1, block, meta, 2);
                            world.scheduleBlockUpdate(posX, posY, posZ + 1, block, meta);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
                case 4:
                    if(world.getBlock(posX-1, posY, posZ)==Blocks.air || world.getBlock(posX-1, posY, posZ) instanceof BlockLiquid) {
                        if (!world.isRemote) {
                            world.setBlock(posX - 1, posY, posZ, block, meta, 2);
                            world.scheduleBlockUpdate(posX - 1, posY, posZ, block, meta);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
                case 5:
                    if(world.getBlock(posX+1, posY, posZ)==Blocks.air || world.getBlock(posX+1, posY, posZ) instanceof BlockLiquid) {
                        if (!world.isRemote) {
                            world.setBlock(posX + 1, posY, posZ, block, meta, 2);
                            world.scheduleBlockUpdate(posX + 1, posY, posZ, block, meta);
                        }
                        player.playSound(sound, 1F, 1F);
                        player.swingItem();
                    }
                    break;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister i) {
        Icon.icon = i.registerIcon("redstonic:Container/RedstonicContainer");
        Icon.xOverlay = i.registerIcon("redstonic:Container/XOverlay");
    }
}
