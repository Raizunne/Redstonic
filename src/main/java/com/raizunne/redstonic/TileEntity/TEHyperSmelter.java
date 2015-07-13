package com.raizunne.redstonic.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Raizunne as a part of Redstonic
 * on 19/05/2015, 09:04 PM.
 */
public class TEHyperSmelter extends TileEntity implements IInventory, ISidedInventory, IEnergyHandler {

    ItemStack[] items;
    private int timer;
    EnergyStorage storage;
    private int maxTimer;

    public TEHyperSmelter(){
        items = new ItemStack[7];
        storage = new EnergyStorage(25600);
        maxTimer = 6;
    }

    @Override
    public void updateEntity() {
        if(getStackInSlot(0)!=null || getStackInSlot(1)!=null && getEnergyStored(null)>=2000) {
            if (timer != maxTimer) {
                timer++;
            } else {
                burn();
                timer = 0;
//                this.worldObj.playSound((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "portal.trigger", 1F, 1F, true);
            }
        }
    }

    public void burn(){
        if(getStackInSlot(0)!=null && FurnaceRecipes.smelting().getSmeltingResult(getStackInSlot(0))!=null) {
            ItemStack burned = FurnaceRecipes.smelting().getSmeltingResult(getStackInSlot(0));
            if(getStackInSlot(0).stackSize==1){
                int burnedStack = burned.stackSize;
                ItemStack newBurned = burned.copy();
                newBurned.stackSize = burnedStack;
                if(!checkFits(burnedStack, burned)){
                    timer = 0;
                    return;
                }
                placeBurned(newBurned, 1);
                storage.setEnergyStored(storage.getEnergyStored()-1000);
                decrStackSize(0, 1);
            }else if(getStackInSlot(0).stackSize>1){
                int burnedStack = burned.stackSize;
                ItemStack newBurned = burned.copy();
                newBurned.stackSize = burnedStack*2;
                if(!checkFits(burnedStack*2, burned)){
                    timer = 0;
                    return;
                }
                placeBurned(newBurned, 2);
                storage.setEnergyStored(storage.getEnergyStored()-2000);
                decrStackSize(0, 2);
            }
        }else if(getStackInSlot(1)!=null && FurnaceRecipes.smelting().getSmeltingResult(getStackInSlot(1))!=null){
            ItemStack burned = FurnaceRecipes.smelting().getSmeltingResult(getStackInSlot(1));
            if(getStackInSlot(1).stackSize==1){
                int burnedStack = burned.stackSize;
                ItemStack newBurned = burned.copy();
                newBurned.stackSize = burnedStack;
                if(!checkFits(burnedStack, burned)){
                    timer = 0;
                    return;
                }
                placeBurned(newBurned, 1);
                storage.setEnergyStored(storage.getEnergyStored()-1000);
                decrStackSize(1, 1);
            }else if(getStackInSlot(1).stackSize>1) {
                int burnedStack = burned.stackSize;
                ItemStack newBurned = burned.copy();
                newBurned.stackSize = burnedStack * 2;
                if (!checkFits(burnedStack * 2, burned)){
                    timer = 0;
                    return;
                }
                placeBurned(newBurned, 2);
                storage.setEnergyStored(storage.getEnergyStored()-2000);
                decrStackSize(1, 2);
            }
        }
    }

    public void placeBurned(ItemStack stack, int count){
        if(getStackInSlot(2)==null){
            setInventorySlotContents(2, stack);
            return;
        }else if(getStackInSlot(2)!=null && getStackInSlot(2).isItemEqual(stack)){
           if(getStackInSlot(2).stackSize+count<=64){
               decrStackSize(2, -count);
               return;
           }
        }
        if(getStackInSlot(3)==null){
            setInventorySlotContents(3, stack);
        }else if(getStackInSlot(3)!=null && getStackInSlot(3).isItemEqual(stack)){
            if(getStackInSlot(3).stackSize+count<=64){
                decrStackSize(3, -count);
            }
        }
    }

    public boolean canBurn(){
        if(getStackInSlot(0)!=null && getStackInSlot(2)!=null && getStackInSlot(3)!=null){
            return true;
        }
        return false;
    }

    public boolean checkFits(int count, ItemStack stack){
        return getStackInSlot(2)==null || getStackInSlot(3)==null || (getStackInSlot(2).isItemEqual(stack) && getStackInSlot(2).stackSize+count<=64) || (getStackInSlot(3).isItemEqual(stack) && getStackInSlot(3).stackSize+count<=64);
    }

    public int getProgress(){
        return timer;
    }

    public int getScaledProgress(int i){
        if(getProgress()!=0){
            return getProgress() * i / maxTimer;
        }else{
            return 0;
        }
    }

    public int getPowerScaledProgress(int i){
        return getEnergyStored(null) * i / getMaxEnergyStored(null);
    }

    public ItemStack furnace(ItemStack stack){
        return FurnaceRecipes.smelting().getSmeltingResult(stack);
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemstack = getStackInSlot(i);

        if(itemstack != null){
            if(itemstack.stackSize <= count){
                setInventorySlotContents(i, null);
            }else{
                itemstack = itemstack.splitStack(count);
                markDirty();
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack item = getStackInSlot(i);
        setInventorySlotContents(i, null);
        return item;

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList items = new NBTTagList();

        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack stack = getStackInSlot(i);

            if (stack != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte)i);
                stack.writeToNBT(item);
                items.appendTag(item);
            }
        }
        compound.setTag("Items", items);
        compound.setInteger("timer", timer);
        storage.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList items = compound.getTagList("Items", 10);

        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
            int slot = item.getByte("Slot");

            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
        timer = compound.getInteger("timer");
        storage.readFromNBT(compound);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items[i] = itemstack;
    }

    @Override
    public String getInventoryName() {
        return "HyperSmelter";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j){
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j){
        return false;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(500, simulate);
    }

    public void setEnergy(int i){
        storage.setEnergyStored(i);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}
