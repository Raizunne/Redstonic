package com.raizunne.redstonic.TileEntity;

import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.RedstonicItems;
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
        tag.setInteger("head", getHead());
        tag.setInteger("body", getBody());
        tag.setInteger("maxEnergy", getEnergy());

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
            applyAug(getAug1());
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug1", getAug1());
            setInventorySlotContents(5, null);
        }

        if(getAug2()!=0 && maxAug!=0){
            if(getAug2()==3 && getStackInSlot(6).stackTagCompound!=null){
                hotswapHead=getStackInSlot(6).stackTagCompound.getInteger("hotswapHead");
            }
            applyAug(getAug2());
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug2", getAug2());
            setInventorySlotContents(6, null);
        }

        if(getAug3()!=0 && maxAug!=0){
            if(getAug3()==3 && getStackInSlot(7).stackTagCompound!=null){
                hotswapHead=getStackInSlot(7).stackTagCompound.getInteger("hotswapHead");
            }
            applyAug(getAug3());
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

        setHead(head);
        setBody(body);
        setEnergy(maxEnergy);
        setAug(getStackInSlot(0));
        setInventorySlotContents(0, null);
    }

    public int getHead(){
        if(getStackInSlot(1).getItem() == RedstonicItems.IronHead){
            return 0;
        }else if(getStackInSlot(1).getItem() == RedstonicItems.GoldHead){
            return 1;
        }else if(getStackInSlot(1).getItem() == RedstonicItems.DiamondHead){
            return 2;
        }else if(getStackInSlot(1).getItem() == RedstonicItems.HeavyHead){
            return 3;
        }else if(getStackInSlot(1).getItem() == RedstonicItems.FortuitousHead){
            return 4;
        }else if(getStackInSlot(1).getItem() == RedstonicItems.SilkyHead) {
            return 5;
        }else{
            return 0;
        }
    }

    public int getBody(){
        if(getStackInSlot(2).getItem() == RedstonicItems.IronBody){
            return 0;
        }else if(getStackInSlot(2).getItem() == RedstonicItems.ElectrumBody){
            return 1;
        }else if(getStackInSlot(2).getItem() == RedstonicItems.EnderiumBody){
            return 2;
        }else{
            return 0;
        }
    }

    public void setHead(int i){
        switch(i){
            case 0: setInventorySlotContents(1, new ItemStack(RedstonicItems.IronHead));
                break;
            case 1: setInventorySlotContents(1, new ItemStack(RedstonicItems.GoldHead));
                break;
            case 2: setInventorySlotContents(1, new ItemStack(RedstonicItems.DiamondHead));
                break;
            case 3: setInventorySlotContents(1, new ItemStack(RedstonicItems.HeavyHead));
                break;
            case 4: setInventorySlotContents(1, new ItemStack(RedstonicItems.FortuitousHead));
                break;
            case 5: setInventorySlotContents(1, new ItemStack(RedstonicItems.SilkyHead));
                break;
        }
    }

    public void setBody(int i){
        switch(i){
            case 0: setInventorySlotContents(2, new ItemStack(RedstonicItems.IronBody));
                break;
            case 1: setInventorySlotContents(2, new ItemStack(RedstonicItems.ElectrumBody));
                break;
            case 2: setInventorySlotContents(2, new ItemStack(RedstonicItems.EnderiumBody));
                break;
        }
    }

    public void setEnergy(int i){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        float multiplier = getStackInSlot(0).stackTagCompound.getFloat("energyMulti");
        if(multiplier==0){multiplier = 1;}
        int total = (int)(i / multiplier);
        switch(total){
            case 480000:
                setInventorySlotContents(3, hardened);
                if(getStackInSlot(3).stackTagCompound==null){
                    getStackInSlot(3).stackTagCompound = new NBTTagCompound();
                }
                getStackInSlot(3).stackTagCompound.setInteger("Energy", getStackInSlot(0).stackTagCompound.getInteger("energy"));
                break;
            case 640000:
                setInventorySlotContents(3, reinforced);
                if(getStackInSlot(3).stackTagCompound==null){
                    getStackInSlot(3).stackTagCompound = new NBTTagCompound();
                }
                getStackInSlot(3).stackTagCompound.setInteger("Energy", getStackInSlot(0).stackTagCompound.getInteger("energy"));
                break;
            case 1000000:
                setInventorySlotContents(3, resonant);
                if(getStackInSlot(3).stackTagCompound==null){
                    getStackInSlot(3).stackTagCompound = new NBTTagCompound();
                }
                getStackInSlot(3).stackTagCompound.setInteger("Energy", getStackInSlot(0).stackTagCompound.getInteger("energy"));
                break;
        }
    }

    public int getEnergy(){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        if(getStackInSlot(3).isItemEqual(hardened)){
            return 480000;
        }else if(getStackInSlot(3).isItemEqual(reinforced)){
            return 640000;
        }else if(getStackInSlot(3).isItemEqual(resonant)){
            return 1000000;
        }
        return 0;
    }

    public boolean checkParts(){
        if(getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2).getItem() instanceof DrillBody && checkCapacitor(3)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkCapacitor(int i){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        if(getStackInSlot(i).isItemEqual(hardened) || getStackInSlot(i).isItemEqual(reinforced) || getStackInSlot(i).isItemEqual(resonant)){
            return true;
        }
        return false;
    }

    public boolean checkForAssemble(){
        if(getStackInSlot(0)==null && getStackInSlot(1)!=null && getStackInSlot(2)!=null && getStackInSlot(3)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkForDisassemble(){
        if(getStackInSlot(0)!=null && getStackInSlot(0).getItem() instanceof RedstonicDrill && getStackInSlot(1)==null && getStackInSlot(2)==null && getStackInSlot(3)==null && getStackInSlot(4)==null && getStackInSlot(5)==null&& getStackInSlot(6)==null){
            return true;
        }else{
            return false;
        }
    }

    public void setAug(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        if(tag.getInteger("aug1")!=0){
            switch(tag.getInteger("aug1")){
                case 1: setInventorySlotContents(5, new ItemStack(RedstonicItems.SpeedAugment)); break;
                case 2: setInventorySlotContents(5, new ItemStack(RedstonicItems.EnergyAugment)); break;
                case 3:
                    int hot = getStackInSlot(0).stackTagCompound.getInteger("hotswapHead");
                    ItemStack aug = new ItemStack(RedstonicItems.HotswapAugment);
                    aug.stackTagCompound = new NBTTagCompound();
                    aug.stackTagCompound.setInteger("hotswapHead", getStackInSlot(0).stackTagCompound.getInteger("hotswapHead"));
                    setInventorySlotContents(5, aug);
                    break;
            }
        }
        if(tag.getInteger("aug2")!=0){
            switch(tag.getInteger("aug2")){
                case 1: setInventorySlotContents(6, new ItemStack(RedstonicItems.SpeedAugment)); break;
                case 2: setInventorySlotContents(6, new ItemStack(RedstonicItems.EnergyAugment)); break;
                case 3:
                    int hot = getStackInSlot(0).stackTagCompound.getInteger("hotswapHead");
                    ItemStack aug = new ItemStack(RedstonicItems.HotswapAugment);
                    aug.stackTagCompound = new NBTTagCompound();
                    aug.stackTagCompound.setInteger("hotswapHead", getStackInSlot(0).stackTagCompound.getInteger("hotswapHead"));
                    setInventorySlotContents(6, aug);
                    break;
            }
        }
        if(tag.getInteger("aug3")!=0){
            switch(tag.getInteger("aug3")){
                case 1: setInventorySlotContents(7, new ItemStack(RedstonicItems.SpeedAugment)); break;
                case 2: setInventorySlotContents(7, new ItemStack(RedstonicItems.EnergyAugment)); break;
                case 3:
                    int hot = getStackInSlot(0).stackTagCompound.getInteger("hotswapHead");
                    ItemStack aug = new ItemStack(RedstonicItems.HotswapAugment);
                    aug.stackTagCompound = new NBTTagCompound();
                    aug.stackTagCompound.setInteger("hotswapHead", getStackInSlot(0).stackTagCompound.getInteger("hotswapHead"));
                    setInventorySlotContents(7, aug);
                    break;
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

    public void applyAug(int aug){
        switch(aug){
            case 1: getStackInSlot(0).stackTagCompound.setFloat("speedMulti", 1.5F); break;
            case 2: getStackInSlot(0).stackTagCompound.setFloat("energyMulti", 2.5F); break;
            case 3: getStackInSlot(0).stackTagCompound.setInteger("hotswapHead", hotswapHead); break;
            case 0: return;
            default: return;
        }
    }

    public int augmenterino(Item i){
        if(i==RedstonicItems.SpeedAugment){
            return 1;
        }else if(i==RedstonicItems.EnergyAugment){
            return 2;
        }else if(i==RedstonicItems.HotswapAugment){
            return 3;
        }else{
            return 0;
        }
    }

    public int getAug1() {
        if(getStackInSlot(5)!=null){
            Item item = getStackInSlot(5).getItem();
            aug1 = augmenterino(item);
        }
        return aug1;
    }

    public int getAug2() {
        if(getStackInSlot(6)!=null){
            Item item = getStackInSlot(6).getItem();
            aug2 = augmenterino(item);
        }
        return aug2;
    }

    public int getAug3() {
        if(getStackInSlot(7)!=null){
            Item item = getStackInSlot(7).getItem();
            aug3 = augmenterino(item);
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