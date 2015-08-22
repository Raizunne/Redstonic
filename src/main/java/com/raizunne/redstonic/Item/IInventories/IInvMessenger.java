package com.raizunne.redstonic.Item.IInventories;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

/**
 * Created by Raizunne as a part of Redstonic
 * on 8/13/15, 4:02 PM.
 */
public class IInvMessenger implements IInventory {

    String name = "RedstonicMessenger";
    private int invsize = 9;
    ItemStack[] items;
    private String UUID;

    public IInvMessenger(ItemStack stack){
        UUID = "";
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            UUID = java.util.UUID.randomUUID().toString();
        }
        items = new ItemStack[invsize];
        readFromNBT(stack.stackTagCompound);
    }

    public void readFromNBT(NBTTagCompound compound){
        if(UUID.equals("")){
            UUID = java.util.UUID.randomUUID().toString();
        }
        NBTTagList tags = compound.getTagList("RedInv", 10);
        for (int i = 0; i < tags.tagCount(); ++i){
            NBTTagCompound comp = tags.getCompoundTagAt(i);
            int b0 = comp.getInteger("Slot");
            if (b0 >= 0 && b0 < this.getSizeInventory()){
                this.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(comp));
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList tags = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i){
            if (this.getStackInSlot(i) != null){
                NBTTagCompound comp = new NBTTagCompound();
                comp.setInteger("Slot", i);
                this.getStackInSlot(i).writeToNBT(comp);
                tags.appendTag(comp);
            }
        }
        if(UUID.equals("")){
            UUID = java.util.UUID.randomUUID().toString();
        }
        compound.setTag("RedInv", tags);
        compound.setString("UUID", UUID);
    }

    @Override
    public int getSizeInventory() {
        return invsize;
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
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items[i] = itemstack;
    }


    @Override
    public String getInventoryName() {
        return name;
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
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
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
}
