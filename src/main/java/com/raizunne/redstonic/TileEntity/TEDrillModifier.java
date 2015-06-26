package com.raizunne.redstonic.TileEntity;

import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import com.raizunne.redstonic.Item.Sword.SwordBlade;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.SwordUtil;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:51 PM.
 */
public class TEDrillModifier extends TileEntity implements IInventory {

    ItemStack[] items;
    private int augments;
    private int aug1, aug2, aug3, hotswapHead;
    private String rename;
    String dissName;
    int mode;

    public TEDrillModifier(){
        items = new ItemStack[8];
        this.mode=0;
        this.dissName="";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        aug1=0; aug2=0; aug3=0;
        if(getStackInSlot(0)!=null){
//            System.out.println(getStackInSlot(0).stackTagCompound);
        }
        if(getStackInSlot(2)!=null){
            if(getStackInSlot(2).getItem() instanceof SwordHandle){
                this.augments = SwordUtil.getMaxAugments(getStackInSlot(2));
            }else if(getStackInSlot(2).getItem() instanceof DrillBody){
                this.augments = DrillUtil.checkMaxAugments(getStackInSlot(2));
            }else{
                this.augments=0;
            }
        }else {
            this.augments=0;
        }

        if(mode==2) /** ASSEMBLE **/{
            assemble();
            mode =0;
        }else if(mode==1) /** DISSASSEMBLE **/{
            disassemble();
            mode = 0;
        }
    }

    public void disassemble(){
        if(checkForDisassemble()){
            disassembleDrill();
        }else if(checkForDisassembleSword()){
            disassembleSword();
        }
    }

    public void assemble(){
        if(isDrill()){
            if(checkForAssembleDrill() && checkUltimateDrill()) {
                assembleDrill();
            }
        }else if(isSword()){
            if(checkForAssembleSword()){
                assembleSword();
            }
        }
    }

    public boolean isDrill(){
        return getStackInSlot(1)!=null && getStackInSlot(2)!=null && getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2).getItem() instanceof DrillBody;
    }

    public boolean isSword(){
        return getStackInSlot(1)!=null && getStackInSlot(2)!=null && getStackInSlot(1).getItem() instanceof SwordBlade && getStackInSlot(2).getItem() instanceof SwordHandle && getStackInSlot(3)!=null && checkCapacitor(3);
    }

    public boolean checkForDisassembleSword(){
        return getStackInSlot(0)!=null && getStackInSlot(1)==null && getStackInSlot(2)==null && getStackInSlot(3)==null && getStackInSlot(4)==null && getStackInSlot(5)==null && getStackInSlot(6)==null && getStackInSlot(0).getItem() instanceof RedstonicSword;
    }

    public void assembleSword(){
        ItemStack newSword = new ItemStack(RedstonicItems.RedSword);
        setInventorySlotContents(0, newSword);
        this.augments =0;
        aug1=0; aug2=0; aug3=0;
        ItemStack sword = getStackInSlot(0);
        sword.stackTagCompound = new NBTTagCompound();
        NBTTagCompound nbt = sword.stackTagCompound;
        nbt.setInteger("blade", SwordUtil.getBladeNumber(getStackInSlot(1)));
        nbt.setInteger("handle", SwordUtil.getHandleNumber(getStackInSlot(2)));
        nbt.setInteger("MaxEnergy", DrillUtil.getEnergyAmount(getStackInSlot(3), sword));
        nbt.setInteger("battery", DrillUtil.getBatteryNumber(getStackInSlot(3)));
        nbt.setInteger("damage", SwordUtil.getDamage(getStackInSlot(1)));
        if(getStackInSlot(1).stackTagCompound!=null)nbt.setInteger("totalKills", getStackInSlot(1).stackTagCompound.getInteger("totalKills"));

        this.augments = SwordUtil.getMaxAugments(getStackInSlot(2));
        int maxAug = this.augments;

        if(getSwordAug1()!=0 && maxAug!=0){
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug1", getSwordAug1());
            setInventorySlotContents(5, null);
        }

        if(getSwordAug2()!=0 && maxAug!=0){
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug2", getSwordAug2());
            setInventorySlotContents(6, null);
        }

        if(getSwordAug3()!=0 && maxAug!=0){
            getStackInSlot(0).stackTagCompound.setInteger("aug3", getSwordAug3());
            setInventorySlotContents(7, null);
        }

        int energy = 0;
        if(getStackInSlot(3).stackTagCompound!=null) {
            energy = getStackInSlot(3).stackTagCompound.getInteger("Energy");
        }
        int maxEnergy = getStackInSlot(0).stackTagCompound.getInteger("MaxEnergy");

        if(getStackInSlot(0).stackTagCompound.getFloat("energyMulti")!=0){
            maxEnergy = (int)(maxEnergy*getStackInSlot(0).stackTagCompound.getFloat("energyMulti"));
        }

        if(energy>maxEnergy){
            energy = maxEnergy;
        }

        if(rename!="")getStackInSlot(0).setStackDisplayName("" + EnumChatFormatting.RESET + EnumChatFormatting.GOLD + rename);

        getStackInSlot(0).stackTagCompound.setInteger("MaxEnergy", maxEnergy);
        getStackInSlot(0).stackTagCompound.setInteger("Energy", energy);
        double modifier = (double)80/maxEnergy;
        getStackInSlot(0).setItemDamage((int)(80 - energy*modifier));

        setInventorySlotContents(1, null);
        setInventorySlotContents(2, null);
        setInventorySlotContents(3, null);
        this.rename="";
    }

    public void assembleDrill(){
        ItemStack newDrill = new ItemStack(RedstonicItems.RedDrill);
        setInventorySlotContents(0, newDrill);
        this.augments = 0;
        aug1=0; aug2=0; aug3=0;
        ItemStack drill = getStackInSlot(0);
        drill.stackTagCompound = new NBTTagCompound();
        NBTTagCompound tag = drill.stackTagCompound;
        tag.setInteger("head", DrillUtil.getHeadNumber(getStackInSlot(1)));
        tag.setInteger("body", DrillUtil.getBodyNumber(getStackInSlot(2)));
        tag.setInteger("maxEnergy", DrillUtil.getEnergyAmount(getStackInSlot(3), drill));
        tag.setInteger("blocks", getStackInSlot(1).stackTagCompound.getInteger("blocks"));
        tag.setInteger("milestone", getStackInSlot(1).stackTagCompound.getInteger("milestone"));
        tag.setInteger("battery", DrillUtil.getBatteryNumber(getStackInSlot(3)));

        this.augments = DrillUtil.checkMaxAugments(getStackInSlot(2));

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
            DrillUtil.applyAug(getAug1(), drill, hotswapHead);
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug1", getAug1());
            setInventorySlotContents(5, null);
        }

        if(getAug2()!=0 && maxAug!=0){
            if(getAug2()==3 && getStackInSlot(6).stackTagCompound!=null){
                hotswapHead=getStackInSlot(6).stackTagCompound.getInteger("hotswapHead");
            }
            DrillUtil.applyAug(getAug2(), drill, hotswapHead);
            maxAug-=1;
            getStackInSlot(0).stackTagCompound.setInteger("aug2", getAug2());
            setInventorySlotContents(6, null);
        }

        if(getAug3()!=0 && maxAug!=0){
            if(getAug3()==3 && getStackInSlot(7).stackTagCompound!=null){
                hotswapHead=getStackInSlot(7).stackTagCompound.getInteger("hotswapHead");
            }
            DrillUtil.applyAug(getAug3(), drill, hotswapHead);
            getStackInSlot(0).stackTagCompound.setInteger("aug3", getAug3());
            setInventorySlotContents(7, null);
        }

        int energy = 0;
        if(getStackInSlot(3).stackTagCompound!=null) {
            energy = getStackInSlot(3).stackTagCompound.getInteger("Energy");
        }
        int maxEnergy = getStackInSlot(0).stackTagCompound.getInteger("maxEnergy");

        if(getStackInSlot(0).stackTagCompound.getFloat("energyMulti")!=0){
            maxEnergy = (int)(maxEnergy*getStackInSlot(0).stackTagCompound.getFloat("energyMulti"));
        }

        if(energy>maxEnergy){
            energy = maxEnergy;
        }

        if(rename!="")getStackInSlot(0).setStackDisplayName("" + EnumChatFormatting.RESET + EnumChatFormatting.RED + rename);

        getStackInSlot(0).stackTagCompound.setInteger("maxEnergy", maxEnergy);
        getStackInSlot(0).stackTagCompound.setInteger("energy", energy);
        double modifier = (double)80/maxEnergy;
        getStackInSlot(0).setItemDamage((int)(80 - energy*modifier));
        setInventorySlotContents(1, null);
        setInventorySlotContents(2, null);
        setInventorySlotContents(3, null);
        this.rename="";
    }

    public void disassembleSword() {
        int blade = getStackInSlot(0).stackTagCompound.getInteger("blade");
        int handle = getStackInSlot(0).stackTagCompound.getInteger("handle");
        int battery = getStackInSlot(0).stackTagCompound.getInteger("battery");
        int totalKills = getStackInSlot(0).stackTagCompound.getInteger("totalKills");

        setInventorySlotContents(1, SwordUtil.getBlade(blade));
        setInventorySlotContents(2, SwordUtil.getHandle(handle));
        setInventorySlotContents(3, DrillUtil.getDrillBattery(battery, getStackInSlot(0)));

        if(getStackInSlot(1).stackTagCompound==null) {
            getStackInSlot(1).stackTagCompound = new NBTTagCompound();
            getStackInSlot(1).stackTagCompound.setInteger("totalKills", totalKills);
        }

        if(getStackInSlot(3).getItem() instanceof ItemBattery && getStackInSlot(3).getItem()!=RedstonicItems.infiniteBattery){
            double modifier = (double)80/getStackInSlot(3).stackTagCompound.getInteger("maxEnergy");
            getStackInSlot(3).setItemDamage((int)(80 - getStackInSlot(3).stackTagCompound.getInteger("Energy")*modifier));
        }
        dissName=!getStackInSlot(0).getDisplayName().equals("§6Redstonic Sword") ? getStackInSlot(0).getDisplayName().replace("§6", "") : "";
        setSwordAug(getStackInSlot(0));
        setInventorySlotContents(0, null);
    }

    public void disassembleDrill(){
        int head = getStackInSlot(0).stackTagCompound.getInteger("head");
        int body = getStackInSlot(0).stackTagCompound.getInteger("body");
        int blocks = getStackInSlot(0).stackTagCompound.getInteger("blocks");
        int milestone = getStackInSlot(0).stackTagCompound.getInteger("milestone");
        int battery = getStackInSlot(0).stackTagCompound.getInteger("battery");

        setInventorySlotContents(1, DrillUtil.getDrillHead(head));
        if(getStackInSlot(1).stackTagCompound==null){
            getStackInSlot(1).stackTagCompound=new NBTTagCompound();
            getStackInSlot(1).stackTagCompound.setInteger("blocks", blocks);
            getStackInSlot(1).stackTagCompound.setInteger("milestone", milestone);
        }else{
            getStackInSlot(1).stackTagCompound.setInteger("blocks", blocks);
            getStackInSlot(1).stackTagCompound.setInteger("milestone", milestone);
        }
        setInventorySlotContents(2, DrillUtil.getDrillBody(body));
        setInventorySlotContents(3, DrillUtil.getDrillBattery(battery, getStackInSlot(0)));
        if(getStackInSlot(3)!=null && getStackInSlot(3).getItem() instanceof ItemBattery && getStackInSlot(3).getItem()!=RedstonicItems.infiniteBattery){
            double modifier = (double)80/getStackInSlot(3).stackTagCompound.getInteger("maxEnergy");
            getStackInSlot(3).setItemDamage((int)(80 - getStackInSlot(3).stackTagCompound.getInteger("Energy")*modifier));
        }

        dissName=!getStackInSlot(0).getDisplayName().equals("§cRedstonic Drill") ? getStackInSlot(0).getDisplayName().replace("§c", "") : "";
        setAug(getStackInSlot(0));
        setInventorySlotContents(0, null);
    }

    public boolean checkPartsDrill(){
        return getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2).getItem() instanceof DrillBody && checkCapacitor(3);
    }

    public boolean checkCapacitor(int i){
        if(Loader.isModLoaded("ThermalExpansion")) {
            ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
            ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
            ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
            ItemStack creative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
            return getStackInSlot(i).isItemEqual(hardened) || getStackInSlot(i).isItemEqual(reinforced) || getStackInSlot(i).isItemEqual(resonant) || getStackInSlot(i).isItemEqual(creative) || getStackInSlot(i).getItem() instanceof ItemBattery;
        }
        else{
            return getStackInSlot(i).getItem() instanceof ItemBattery;
        }
    }

    public boolean checkForAssembleDrill(){
        return getStackInSlot(0)==null && getStackInSlot(1)!=null && getStackInSlot(1).getItem() instanceof DrillHead && getStackInSlot(2)!=null && getStackInSlot(2).getItem() instanceof DrillBody &&
                getStackInSlot(3)!=null && checkCapacitor(3) && checkPartsDrill() && inspectAugments();
    }

    public boolean checkForAssembleSword(){
        return getStackInSlot(0)==null && getStackInSlot(1)!=null && getStackInSlot(1).getItem() instanceof SwordBlade && getStackInSlot(2)!=null && getStackInSlot(2).getItem() instanceof SwordHandle &&
                getStackInSlot(3)!=null && checkCapacitor(3) && inspectSwordAugments();
    }

    public boolean checkUltimateDrill(){
        if(getStackInSlot(1).getItem()==RedstonicItems.EndHead){
            if(getStackInSlot(2).getItem()!= RedstonicItems.UltimateBody){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }

    public boolean checkForDisassemble(){
        return getStackInSlot(0)!=null && getStackInSlot(0).getItem() instanceof RedstonicDrill && getStackInSlot(1)==null && getStackInSlot(2)==null && getStackInSlot(3)==null && getStackInSlot(4)==null && getStackInSlot(5)==null&& getStackInSlot(6)==null;
    }

    public void setAug(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        int hot = tag.getInteger("hotswapHead");

        for(int i=1; i<4; i++){
            if(tag.getInteger("aug" + i)!=0){
                setInventorySlotContents(4+i, DrillUtil.getAugments(tag.getInteger("aug" + i), hot));
            }
        }
    }

    public void setSwordAug(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        for(int i=1; i<4; i++){
            if(tag.getInteger("aug" + i)!=0){
                setInventorySlotContents(4+i, SwordUtil.getAugments(tag.getInteger("aug" + i)));
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

    public boolean inspectSwordAugments(){
        boolean check1 = true, check2 = true, check3 = true;
        if(getSwordAug1()!=0 && getSwordAug2()!=0) {
            check1 = getSwordAug1() != getSwordAug2();
        }
        if(getSwordAug1()!=0 && getSwordAug3()!=0){
            check2=getSwordAug1()!=getSwordAug3();
        }
        if(getSwordAug2()!=0 && getSwordAug3()!=0){
            check3=getSwordAug2() !=getSwordAug3();
        }

        return check1 && check2 && check3;
    }

    public int getAug1() {
        if(getStackInSlot(5)!=null){
            ItemStack item = getStackInSlot(5);
            aug1 = DrillUtil.getAugNumber(item);
        }
        return aug1;
    }

    public int getAug2() {
        if(getStackInSlot(6)!=null){
            ItemStack item = getStackInSlot(6);
            aug2 = DrillUtil.getAugNumber(item);
//            System.out.println(aug2);
        }
        return aug2;
    }

    public int getAug3() {
        if(getStackInSlot(7)!=null){
            ItemStack item = getStackInSlot(7);
            aug3 = DrillUtil.getAugNumber(item);
        }
        return aug3;
    }

    public int getSwordAug1() {
        if(getStackInSlot(5)!=null){
            ItemStack item = getStackInSlot(5);
            aug1 = SwordUtil.getAugNumber(item);
        }
        return aug1;
    }

    public int getSwordAug2() {
        if(getStackInSlot(6)!=null){
            ItemStack item = getStackInSlot(6);
            aug2 = SwordUtil.getAugNumber(item);
//            System.out.println(aug2);
        }
        return aug2;
    }

    public int getSwordAug3() {
        if(getStackInSlot(7)!=null){
            ItemStack item = getStackInSlot(7);
            aug3 = SwordUtil.getAugNumber(item);
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

    public String getDisName(){
        return dissName;
    }

    public void setMode(int i, String rename){
        this.mode = i;
        this.rename = rename;
    }

    public void reset(){
        dissName="";
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

    public void setState(String state){
        if(state=="Assemble"){
            state = "assemble";
        }else if(state=="Dissassemble"){
            state = "dissassemble";
        }
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
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    public int getAugments() {
        return augments;
    }
}