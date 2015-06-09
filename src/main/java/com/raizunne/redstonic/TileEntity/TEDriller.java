package com.raizunne.redstonic.TileEntity;

import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import com.raizunne.redstonic.Util.DrillUtil;
import net.minecraft.block.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import scala.Array;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 01:04 AM.
 */
public class TEDriller extends TileEntity implements IInventory, IEnergyReceiver{

    int head;
    ItemStack[] items;
    int meta;
    int timer;


    public TEDriller(){
        this.head = 999;
    }

    @Override
    public void updateEntity() {
        meta = this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        if(head!=999){
            switch(head){
                case 0: normalBreak();
                    break;
                case 1: normalBreak();
                    break;
                case 2: normalBreak();
                    break;
                case 3: threeBreak();
                    break;
                case 4: fortuneBreak();
                    break;
                case 5: silkyBreak();
                    break;
                case 6: blazingBreak();
                    break;
                case 7: normalBreak();
                    break;
            }
        }
    }

    public void normalBreak(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; Block block = getBlock();
        if(!world.isRemote && blacklist(block)) {
            ArrayList<ItemStack> drops;
            if(timer==DrillUtil.getCooldown(head)) {
                switch(meta){
                    case 0:
                        world.func_147480_a(x, y, z + 1, false);
                        drops = block.getDrops(world, x, y, z+1, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 1:
                        world.func_147480_a(x - 1, y, z, false);
                        drops = block.getDrops(world, x-1, y, z, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 2:
                        world.func_147480_a(x, y, z - 1, false);
                        drops = block.getDrops(world, x, y, z-1, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 3:
                        world.func_147480_a(x+1, y, z, false);
                        drops = block.getDrops(world, x+1, y, z, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                }
                timer=0;
            }else{
                timer++;
            }
        }
    }

    public void threeBreak(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; Block block = getBlock();  Block blockerino;
        int blockerinoMetadata;
        if(!world.isRemote && blacklist(block)) {
            ArrayList<ItemStack> drops;
            if(timer==DrillUtil.getCooldown(head)) {
                switch(meta){
                    case 0:
                        x = x+1; y = y+1; z = z+1;
                        for(int i=0;i<3;i++) {
                            for(int r=0; r<3; r++) {
                                blockerino = world.getBlock(x-i, y-r, z);
                                if(blacklist(blockerino)) {
                                    blockerinoMetadata = world.getBlockMetadata(x-i, y-r, z);
                                    world.func_147480_a(x - i, y - r, z, false);
                                    drops = blockerino.getDrops(world, x - i, y - r, z, blockerinoMetadata, 0);
                                    for (int p = 0; p < drops.size(); p++) {
                                        putStack(drops.get(p));
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        x = x-1; y = y+1; z = z+1;
                        for(int i=0;i<3;i++) {
                            for(int r=0; r<3; r++) {
                                blockerino = world.getBlock(x, y-r, z-i);
                                if(blacklist(blockerino)) {
                                    blockerinoMetadata = world.getBlockMetadata(x, y-r, z-i);
                                    world.func_147480_a(x, y - r, z - i, false);
                                    drops = blockerino.getDrops(world, x, y - r, z - i, blockerinoMetadata, 0);
                                    for (int p = 0; p < drops.size(); p++) {
                                        putStack(drops.get(p));
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        x = x-1; y = y+1; z = z-1;
                        for(int i=0;i<3;i++) {
                            for(int r=0; r<3; r++) {
                                blockerino = world.getBlock(x+i, y-r, z);
                                if(blacklist(blockerino)) {
                                    blockerinoMetadata = world.getBlockMetadata(x+i, y-r, z);
                                    world.func_147480_a(x + i, y - r, z, false);
                                    drops = blockerino.getDrops(world, x + i, y - r, z, blockerinoMetadata, 0);
                                    for (int p = 0; p < drops.size(); p++) {
                                        putStack(drops.get(p));
                                    }
                                }
                            }
                        }
                        break;
                    case 3:
                        x = x+1; y = y+1; z = z-1;
                        for(int i=0;i<3;i++) {
                            for(int r=0; r<3; r++) {
                                blockerino = world.getBlock(x, y-r, z+i);
                                if(blacklist(blockerino)) {
                                    blockerinoMetadata = world.getBlockMetadata(x, y-r, z+i);
                                    world.func_147480_a(x, y - r, z + i, false);
                                    drops = blockerino.getDrops(world, x, y - r, z + i, blockerinoMetadata, 0);
                                    for (int p = 0; p < drops.size(); p++) {
                                        putStack(drops.get(p));
                                    }
                                }
                            }
                        }
                        break;
                }
                timer=0;
                drops = null;
            }else{
                timer++;
            }
        }
    }

    public void fortuneBreak(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; Block block = getBlock(); int blockMeta = getBlockMetadata();
        if(!world.isRemote && blacklist(block)) {
            ArrayList<ItemStack> drops;
            if(timer==DrillUtil.getCooldown(head)) {
                switch(meta){
                    case 0:
                        world.func_147480_a(x, y, z + 1, false);
                        drops = block.getDrops(world, x, y, z+1, blockMetadata, 4);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 1:
                        world.func_147480_a(x - 1, y, z, false);
                        drops = block.getDrops(world, x-1, y, z, blockMetadata, 4);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 2:
                        world.func_147480_a(x, y, z - 1, false);
                        drops = block.getDrops(world, x, y, z-1, blockMetadata, 4);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                    case 3:
                        world.func_147480_a(x+1, y, z, false);
                        drops = block.getDrops(world, x+1, y, z, blockMetadata, 4);
                        for(int i=0; i<drops.size();i++) {
                            putStack(drops.get(i));
                        }
                        break;
                }
                timer=0;
            }else{
                timer++;
            }
        }
    }

    public void silkyBreak(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; Block block = getBlock();
        if(!world.isRemote && blacklist(block) && silkyBlacklist(block)) {
            if(timer==DrillUtil.getCooldown(head)) {
                switch(meta){
                    case 0:
                        world.func_147480_a(x, y, z + 1, false);
                        putStack(new ItemStack(Item.getItemFromBlock(block)));
                        break;
                    case 1:
                        world.func_147480_a(x - 1, y, z, false);
                        putStack(new ItemStack(Item.getItemFromBlock(block)));
                        break;
                    case 2:
                        world.func_147480_a(x, y, z - 1, false);
                        putStack(new ItemStack(Item.getItemFromBlock(block)));
                        break;
                    case 3:
                        world.func_147480_a(x+1, y, z, false);
                        putStack(new ItemStack(Item.getItemFromBlock(block)));
                        break;
                }
                timer=0;
            }else{
                timer++;
            }
        }
    }

    public void blazingBreak(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; Block block = getBlock();
        if(!world.isRemote && blacklist(block) && blazerBlacklist(block)) {
            ArrayList<ItemStack> drops;
            if(timer==DrillUtil.getCooldown(head)) {
                switch(meta){
                    case 0:
                        world.func_147480_a(x, y, z + 1, false);
                        drops = block.getDrops(world, x, y, z+1, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
//                            System.out.println(drops.get(i) + "DROPS.GET");
                            if(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i))!=null){
                                putStack(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i)));
                            }else{
                                putStack(drops.get(i));
                            }
                        }
                        break;
                    case 1:
                        world.func_147480_a(x - 1, y, z, false);
                        drops = block.getDrops(world, x-1, y, z, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            if(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i))!=null){
                                putStack(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i)));
                            }else{
                                putStack(drops.get(i));
                            }
                        }
                        break;
                    case 2:
                        world.func_147480_a(x, y, z - 1, false);
                        drops = block.getDrops(world, x, y, z-1, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            if(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i))!=null){
                                putStack(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i)));
                            }else{
                                putStack(drops.get(i));
                            }
                        }
                        break;
                    case 3:
                        world.func_147480_a(x+1, y, z, false);
                        drops = block.getDrops(world, x+1, y, z, getBlockMetadata(), 0);
                        for(int i=0; i<drops.size();i++) {
                            if(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i))!=null){
                                putStack(FurnaceRecipes.smelting().getSmeltingResult(drops.get(i)));
                            }else{
                                putStack(drops.get(i));
                            }
                        }
                        break;
                }
                timer=0;
            }else{
                timer++;
            }
        }
    }

    public boolean blacklist(Block block) {
        Block[] blacklist = {Blocks.air, Blocks.bedrock};
        for(int i=0;i<blacklist.length;i++){
            if(block==blacklist[i] || block instanceof BlockCrops || block instanceof IPlantable || block instanceof BlockBush || block instanceof BlockLiquid || block instanceof IEnergyStorage || block.hasTileEntity(0)){
                return false;
            }
        }
        return true;
    }

    public boolean blazerBlacklist(Block block){
        Block[] blacklist = {Blocks.log};
        for(int i=0; i<blacklist.length;i++){
            if(block==blacklist[i] || block instanceof BlockCrops || block instanceof IPlantable || block instanceof BlockBush || block instanceof BlockLiquid || block instanceof IEnergyStorage){
                return false;
            }
        }
        return true;
    }

    public boolean silkyBlacklist(Block block){
        Block[] blacklist = {Blocks.mob_spawner, Blocks.farmland, Blocks.fire, Blocks.reeds, Blocks.snow, Blocks.snow_layer, Blocks.daylight_detector};
        for(int i=0; i<blacklist.length;i++){
            if(block==blacklist[i] || block instanceof BlockCrops || block instanceof IPlantable || block instanceof BlockBush || block instanceof BlockLiquid || block instanceof IEnergyStorage){
                return false;
            }
        }
        return true;
    }

    public int[] backCoords(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj;
        switch(meta){
            case 0: return new int[]{x, y, z-1};
            case 1: return new int[]{x+1, y, z};
            case 2: return new int[]{x, y, z+1};
            case 3: return new int[]{x-1, y, z};
            default: return new int[]{x, y, z};
        }
    }

    public Block getBlock(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj;
        switch(meta){
            case 0: return world.getBlock(x, y, z+1);
            case 1: return world.getBlock(x-1, y, z);
            case 2: return world.getBlock(x, y, z-1);
            case 3: return world.getBlock(x+1, y, z);
            default: return Blocks.air;
        }
    }

    public int getBlockMetadata(){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj;
        switch(meta){
            case 0: return world.getBlockMetadata(x, y, z + 1);
            case 1: return world.getBlockMetadata(x - 1, y, z);
            case 2: return world.getBlockMetadata(x, y, z - 1);
            case 3: return world.getBlockMetadata(x + 1, y, z);
            default: return 0;
        }
    }

    public void putStack(ItemStack stack){
        int x = this.xCoord; int y = this.yCoord; int z = this.zCoord; World world = this.worldObj; int[] lol = backCoords();
        int backX = lol[0]; int backY = lol[1]; int backZ = lol[2]; IInventory te = (IInventory)world.getTileEntity(backX, backY, backZ);
        if(te!=null && te instanceof IInventory) {
            for (int i = 0; i < te.getSizeInventory(); i++) {
                if (te.getStackInSlot(i) != null) {
                    if (te.getStackInSlot(i).isItemEqual(stack)) {
                        te.getStackInSlot(i).stackSize = te.getStackInSlot(i).stackSize + stack.stackSize;
                        return;
                    }
                } else {
                    te.setInventorySlotContents(i, stack);
                    return;
                }
            }
        }else{
            EntityItem item = new EntityItem(world, x+0.5, y+0.5, z+0.5, stack);
            world.spawnEntityInWorld(item);
        }
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return false;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int number){
        head = number;
    }

    public void removeHead(EntityPlayer player){
        player.inventory.addItemStackToInventory(DrillUtil.getDrillHead(getHead()));
        head = 999;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        this.readFromNBT(tag);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.head = compound.getInteger("head");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("head", this.head);
    }
}
