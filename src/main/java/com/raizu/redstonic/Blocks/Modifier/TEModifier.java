package com.raizu.redstonic.Blocks.Modifier;

import cofh.api.energy.IEnergyContainerItem;
import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Items.Sword.Sword;
import com.raizu.redstonic.Items.Sword.SwordAugment;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import com.raizu.redstonic.Utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class TEModifier extends TileEntity implements IInventory {

    ItemStack[] items;
    private int augments;
//    private Augment aug1, aug2, aug3;
    private String customName;

    public TEModifier(){
        items = new ItemStack[8];
        this.customName = "";
    }

    public enum CraftType{SWORD,DRILL;}

    public void assemble(String name){
        CraftType craftType;
        if(getStackInSlot(0)==null && getStackInSlot(1)!=null && getStackInSlot(2)!=null && getStackInSlot(3)!=null){
            ItemStack[] augmentSlots = {getStackInSlot(4), getStackInSlot(5), getStackInSlot(6)};
            craftType = getStackInSlot(1).getItem() instanceof DrillHead ? CraftType.DRILL : CraftType.SWORD;
            if(craftType==CraftType.DRILL){
                ItemStack[] augments = getAddedDrillAugments();
                int allowedAugments = RedItems.drillBody.augmentSlots[getStackInSlot(2).getMetadata()];
                if(getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2).getItem() instanceof DrillBody && augments.length<=allowedAugments && getStackInSlot(3).getItem() instanceof IEnergyContainerItem && !Util.hasDuplicateAugments(augmentSlots) && areAugmentsValid(GUIModifier.CraftType.DRILL)){
                    int head = getStackInSlot(1).getMetadata();
                    int body = getStackInSlot(2).getMetadata();
                    if(head==7 && body!=5)return;
                    int battery = getStackInSlot(3).getMetadata();
                    NBTTagCompound tag = new NBTTagCompound();
                    for (int i = 0; i < augments.length; i++) {
                        tag.setInteger("aug" + i, augments[i].getMetadata());
                        if(augments[i].getMetadata()==3){
                            if(augments[i].getTagCompound()!=null)tag.setInteger("hotswapHead", augments[i].getTagCompound().getInteger("hotswapHead"));
                            else tag.setInteger("hotswapHead", -1);
                        }
                    }
                    tag.setInteger("head", head);
                    tag.setInteger("body", body);
                    tag.setInteger("battery", battery);
                    tag.setInteger("blocks", getStackInSlot(1).getTagCompound().getInteger("blocks"));
                    tag.setInteger("maxEnergy", RedItems.battery.maxEnergy[battery]);
                    if(getStackInSlot(3).getTagCompound()!=null && getStackInSlot(3).getTagCompound().hasKey("Energy"))
                        tag.setInteger("Energy", getStackInSlot(3).getTagCompound().getInteger("Energy"));
                    else
                        tag.setInteger("Energy", 0);
                    ItemStack newDrill = new ItemStack(RedItems.drill, 1);
                    newDrill.setTagCompound(tag);
                    newDrill.setStackDisplayName(name=="" ? "§rRedstonic Drill" : "§r"+name);
                    setInventorySlotContents(0, newDrill);
                    for (int i = 0; i < 6; i++) {
                        setInventorySlotContents(i+1, null);
                    }
                }
            }else{
                ItemStack[] augments = getAddedSword();
                int allowedAugments = RedItems.swordHandle.augmentSlots[getStackInSlot(2).getMetadata()];
                if(getStackInSlot(1).getItem() instanceof SwordBlade && getStackInSlot(2).getItem() instanceof SwordHandle && getStackInSlot(3).getItem() instanceof IEnergyContainerItem && augments.length<=allowedAugments && !Util.hasDuplicateAugments(augmentSlots)){
                    int blade = getStackInSlot(1).getMetadata(), handle = getStackInSlot(2).getMetadata(), battery = getStackInSlot(3).getMetadata();
                    NBTTagCompound tag = new NBTTagCompound();
                    for (int i = 0; i < augments.length; i++)
                        tag.setInteger("aug"+i, augments[i].getMetadata());
                    tag.setInteger("blade", blade);
                    tag.setInteger("handle", handle);
                    tag.setInteger("battery", battery);
                    tag.setInteger("maxEnergy", RedItems.battery.maxEnergy[battery]);
                    if(getStackInSlot(3).getTagCompound()!=null && getStackInSlot(3).getTagCompound().hasKey("Energy"))
                        tag.setInteger("Energy", getStackInSlot(3).getTagCompound().getInteger("Energy"));
                    else
                        tag.setInteger("Energy", 0);
                    ItemStack newSword = new ItemStack(RedItems.sword, 1);
                    newSword.setTagCompound(tag);
                    newSword.setStackDisplayName(name=="" ? "§rRedstonic Sword" : "§r"+name);
                    setInventorySlotContents(0, newSword);
                    for (int i = 0; i < 6; i++) {
                        setInventorySlotContents(i+1, null);
                    }
                }
            }
        }
    }

    public void disassemble(String name){
        if(getStackInSlot(0)!=null && getStackInSlot(1)==null && getStackInSlot(2)==null && getStackInSlot(3)==null && getStackInSlot(4)==null && getStackInSlot(5)==null && getStackInSlot(6)==null && (getStackInSlot(0).getItem() instanceof Drill || getStackInSlot(0).getItem() instanceof Sword) ){
            if(getStackInSlot(0).getItem() instanceof Drill){
                int head = getStackInSlot(0).getTagCompound().getInteger("head"), body = getStackInSlot(0).getTagCompound().getInteger("body"), battery = getStackInSlot(0).getTagCompound().getInteger("battery"), energy = getStackInSlot(0).getTagCompound().getInteger("Energy");
                for (int i = 0; i < 3; i++) {
                    if(getStackInSlot(0).getTagCompound().hasKey("aug"+i)){
                        setInventorySlotContents(4+i, new ItemStack(RedItems.drillAugment, 1, getStackInSlot(0).getTagCompound().getInteger("aug"+i)));
                        if(getStackInSlot(0).getTagCompound().getInteger("aug"+i)==3){
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setInteger("hotswapHead", getStackInSlot(0).getTagCompound().getInteger("hotswapHead"));
                            getStackInSlot(4+i).setTagCompound(tag);
                        }
                    }
                }
                setInventorySlotContents(1, new ItemStack(RedItems.drillHead, 1, head));
                NBTTagCompound headTag = new NBTTagCompound();
                headTag.setInteger("blocks", getStackInSlot(0).getTagCompound().getInteger("blocks"));
                getStackInSlot(1).setTagCompound(headTag);
                setInventorySlotContents(2, new ItemStack(RedItems.drillBody, 1, body));
                setInventorySlotContents(3, new ItemStack(RedItems.battery, 1, battery));
                NBTTagCompound batTag = new NBTTagCompound();
                batTag.setInteger("Energy", energy);
                getStackInSlot(3).setTagCompound(batTag);
                setInventorySlotContents(0, null);
            }else if(getStackInSlot(0).getItem() instanceof Sword){
                int blade = getStackInSlot(0).getTagCompound().getInteger("blade"), handle = getStackInSlot(0).getTagCompound().getInteger("handle"), battery = getStackInSlot(0).getTagCompound().getInteger("battery"), energy = getStackInSlot(0).getTagCompound().getInteger("Energy");
                for (int i = 0; i < 3; i++)
                    if(getStackInSlot(0).getTagCompound().hasKey("aug"+i))
                        setInventorySlotContents(4+i, new ItemStack(RedItems.swordAugment, 1, getStackInSlot(0).getTagCompound().getInteger("aug"+i)));
                setInventorySlotContents(1, new ItemStack(RedItems.swordBlade, 1, blade));
                NBTTagCompound bladeTag = new NBTTagCompound();
                bladeTag.setInteger("kills", getStackInSlot(0).getTagCompound().getInteger("kills"));
                getStackInSlot(1).setTagCompound(bladeTag);
                setInventorySlotContents(2, new ItemStack(RedItems.swordHandle, 1, handle));
                setInventorySlotContents(3, new ItemStack(RedItems.battery, 1, battery));
                NBTTagCompound batTag = new NBTTagCompound();
                batTag.setInteger("Energy", energy);
                getStackInSlot(3).setTagCompound(batTag);
                setInventorySlotContents(0, null);
            }
        }
    }

    public ItemStack[] getAddedDrillAugments(){
        List<ItemStack> augments = Arrays.asList(new ItemStack[]{getStackInSlot(4), getStackInSlot(5), getStackInSlot(6)});
        List<ItemStack> toRemove = new ArrayList<ItemStack>();
        for (int i = 0; i < augments.size(); i++) {
            if(augments.get(i)!=null && (augments.get(i).getItem() instanceof DrillAugment))toRemove.add(augments.get(i));
        }
        return toRemove.toArray(new ItemStack[toRemove.size()]);
    }

    // TODO: 16/10/2016 CHANGE THIS NAME
    public ItemStack[] getAddedSword(){
        List<ItemStack> augments = Arrays.asList(new ItemStack[]{getStackInSlot(4), getStackInSlot(5), getStackInSlot(6)});
        List<ItemStack> toRemove = new ArrayList<ItemStack>();
        for (int i = 0; i < augments.size(); i++) {
            if(augments.get(i)!=null && (augments.get(i).getItem() instanceof SwordAugment))toRemove.add(augments.get(i));
        }
        return toRemove.toArray(new ItemStack[toRemove.size()]);
    }

    public boolean areAugmentsValid(GUIModifier.CraftType craftType){
        List<ItemStack> augments = Arrays.asList(new ItemStack[]{getStackInSlot(4), getStackInSlot(5), getStackInSlot(6)});
        if(craftType == GUIModifier.CraftType.DRILL){
            for (ItemStack stack : augments){
                if(stack!=null && !(stack.getItem() instanceof DrillAugment))return false;
            }
        }else if(craftType == GUIModifier.CraftType.SWORD){
            for (ItemStack stack : augments){
                if(stack!=null && !(stack.getItem() instanceof SwordAugment))return false;
            }
        }
        return true;
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return items[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = getStackInSlot(index);

        if(itemstack != null){
            if(itemstack.stackSize <= count){
                setInventorySlotContents(index, null);
            }else{
                itemstack = itemstack.splitStack(count);
                markDirty();
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (this.items[index] != null){
            ItemStack itemstack = this.items[index];
            this.items[index] = null;
            return itemstack;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        items[index] = stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
    }

    @Override
    public String getName() {
        return "Redstonic Modifier";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
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
        return compound;
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
    }

}
