package com.raizunne.redstonic.TileEntity;

import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:51 PM.
 */
public class TEDrillModifier extends TileEntity implements IInventory {

    ItemStack[] items;
    private int augments;
    private int aug1, aug2, aug3, hotswapHead;
    int mode;

    public TEDrillModifier(){
        items = new ItemStack[8];
        this.mode=1;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(getStackInSlot(0)!=null){
            System.out.println(getStackInSlot(0).stackTagCompound);
        }
        if(checkForAssemble() && mode==1){
            assemble();
        }else if(checkForDisassemble() && mode==2){
            disassemble();
        }
    }

    public void assemble(){
        ItemStack newDrill = new ItemStack(RedstonicItems.RedDrill);
        setInventorySlotContents(0, newDrill);
        aug1=0; aug2=0; aug3=0;
        ItemStack drill = getStackInSlot(0);
        drill.stackTagCompound = new NBTTagCompound();
        NBTTagCompound tag = drill.stackTagCompound;
        tag.setInteger("head", Util.getHeadNumber(getStackInSlot(1)));
        tag.setInteger("body", Util.getBodyNumber(getStackInSlot(2)));
        tag.setInteger("maxEnergy", Util.getEnergyAmount(getStackInSlot(3)));

        checkMaxAugments();

        int maxAug = this.augments;

        if(tag.getInteger("head")==4){
            drill.addEnchantment(Enchantment.fortune, 4);
        }else if(tag.getInteger("head")==5){
            drill.addEnchantment(Enchantment.silkTouch, 1);
        }

        if(getAug1()!=0 && maxAug!=0){
            if(getAug1()==3 && getStackInSlot(5).stackTagCompound!=null){
                hotswapHead=getStackInSlot(5).stackTagCompound.getInteger("hotswapHead");
            }
            Util.applyAug(getAug1(), drill, hotswapHead);
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug1", getAug1());
            setInventorySlotContents(5, null);
        }

        if(getAug2()!=0 && maxAug!=0){
            if(getAug2()==3 && getStackInSlot(6).stackTagCompound!=null){
                hotswapHead=getStackInSlot(6).stackTagCompound.getInteger("hotswapHead");
            }
            Util.applyAug(getAug2(), drill, hotswapHead);
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug2", getAug2());
            setInventorySlotContents(6, null);
        }

        if(getAug3()!=0 && maxAug!=0){
            if(getAug3()==3 && getStackInSlot(7).stackTagCompound!=null){
                hotswapHead=getStackInSlot(7).stackTagCompound.getInteger("hotswapHead");
            }
            Util.applyAug(getAug3(), drill, hotswapHead);
            getStackInSlot(0).stackTagCompound.setInteger("aug3", getAug3());
            setInventorySlotContents(7, null);
        }

        int energy = getStackInSlot(3).stackTagCompound.getInteger("Energy");
        int maxEnergy = getStackInSlot(0).stackTagCompound.getInteger("maxEnergy");

        if(getStackInSlot(0).stackTagCompound.getFloat("energyMulti")!=0){
            maxEnergy = (int)(maxEnergy*getStackInSlot(0).stackTagCompound.getFloat("energyMulti"));
        }

        if(energy>maxEnergy){
            energy = maxEnergy;
        }

        getStackInSlot(0).stackTagCompound.setInteger("maxEnergy", maxEnergy);
        getStackInSlot(0).stackTagCompound.setInteger("energy", energy);
        double modifier = (double)80/maxEnergy;
        getStackInSlot(0).setItemDamage((int)(80 - energy*modifier));

        setInventorySlotContents(1, null);
        setInventorySlotContents(2, null);
        setInventorySlotContents(3, null);
    }

    public void disassemble(){
        int head = getStackInSlot(0).stackTagCompound.getInteger("head");
        int body = getStackInSlot(0).stackTagCompound.getInteger("body");
        int maxEnergy = getStackInSlot(0).stackTagCompound.getInteger("maxEnergy");

        setInventorySlotContents(1, Util.getDrillHead(head));
        setInventorySlotContents(2, Util.getDrillBody(body));
        setInventorySlotContents(3, Util.getEnergy(maxEnergy, getStackInSlot(0), getStackInSlot(3)));
        setAug(getStackInSlot(0));
        setInventorySlotContents(0, null);
    }

    public boolean checkParts(){
        return getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2).getItem() instanceof DrillBody && checkCapacitor(3);
    }

    public boolean checkCapacitor(int i){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        return getStackInSlot(i).isItemEqual(hardened) || getStackInSlot(i).isItemEqual(reinforced) || getStackInSlot(i).isItemEqual(resonant);
    }

    public boolean checkForAssemble(){
        return getStackInSlot(0)==null && getStackInSlot(1)!=null && getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2)!=null && getStackInSlot(2).getItem() instanceof DrillBody && getStackInSlot(3)!=null && checkCapacitor(3) && checkParts() && inspectAugments();
    }

    public boolean checkForDisassemble(){
        return getStackInSlot(0)!=null && getStackInSlot(0).getItem() instanceof RedstonicDrill && getStackInSlot(1)==null && getStackInSlot(2)==null && getStackInSlot(3)==null && getStackInSlot(4)==null && getStackInSlot(5)==null&& getStackInSlot(6)==null;
    }

    public void setAug(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        int hot = tag.getInteger("hotswapHead");

        for(int i=1; i<4; i++){
            if(tag.getInteger("aug" + i)!=0){
                setInventorySlotContents(4+i, Util.getAugments(tag.getInteger("aug"+i), hot));
            }
        }
    }

    public boolean inspectAugments(){
        boolean check1 = true, check2 = true, check3 = true;
        if(getAug1()!=0 && getAug2()!=0) {
            check1 = getAug1() != getAug2();
        }
        if(getAug1()!=0 && getAug3()!=0){
            check2=getAug1()!=getAug3();
        }
        if(getAug2()!=0 && getAug3()!=0){
            check3=getAug2() !=getAug3();
        }

        return check1 && check2 && check3;
    }

    public void checkMaxAugments(){
        if(getStackInSlot(2)!=null){
            if(getStackInSlot(2).getItem() == RedstonicItems.IronBody){
                this.augments = 1;
            }else if(getStackInSlot(2).getItem() == RedstonicItems.ElectrumBody){
                this.augments = 2;
            }else if(getStackInSlot(2).getItem() == RedstonicItems.EnderiumBody){
                this.augments = 3;
            }
        }else{
            this.augments = 0;
        }
    }

    public int getAug1() {
        if(getStackInSlot(5)!=null){
            Item item = getStackInSlot(5).getItem();
            aug1 = Util.getAugNumber(item);
        }
        return aug1;
    }

    public int getAug2() {
        if(getStackInSlot(6)!=null){
            Item item = getStackInSlot(6).getItem();
            aug2 = Util.getAugNumber(item);
//            System.out.println(aug2);
        }
        return aug2;
    }

    public int getAug3() {
        if(getStackInSlot(7)!=null){
            Item item = getStackInSlot(7).getItem();
            aug3 = Util.getAugNumber(item);
        }
        return aug3;
    }
    public void toggleMode(){
        if(this.mode==1){
            this.mode=2;
        }else{
            this.mode=1;
        }
    }

    public void setMode(int i){
        this.mode = i;
    }

    public int getMode(){
        return this.mode;
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
        compound.setInteger("mode", this.mode);
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
        this.mode = compound.getInteger("mode");
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
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        items[i] = itemstack;
    }

    @Override
    public String getInventoryName() {
        return "FoodPackager";
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

    public boolean canInsertItem(int i, ItemStack itemstack, int j){
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j){
        return false;
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

    public int getAugments() {
        return augments;
    }
}