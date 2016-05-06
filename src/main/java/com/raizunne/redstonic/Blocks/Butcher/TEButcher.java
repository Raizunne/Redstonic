package com.raizunne.redstonic.Blocks.Butcher;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.raizunne.redstonic.Items.Augment;
import com.raizunne.redstonic.Items.RedItems;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Raizu on 05/05/2016, 08:28 PM.
 */
public class TEButcher extends TileEntity implements IInventory, IEnergyReceiver, ITickable {

    private int radius, timer, maxTime, maxEnergy;
    private EnergyStorage storage;
    PropertyDirection FAC = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    private boolean leave2, killCows, killSheep, killPig;

    ItemStack[] inv;

    public TEButcher(){
        leave2=killCows=killPig=killSheep=true;
        this.radius = 3;
        this.timer = 0;
        this.maxTime = 200;
        inv = new ItemStack[3];
    }

    @Override
    public void update() {
        setRadius(3);
        setMaxTime(200);
        for (int i = 0; i < 3; i++) {
            if(getStackInSlot(i)!=null && getStackInSlot(i).getItem() instanceof Augment){
                if(getStackInSlot(i).getItem() == RedItems.rangeAugment){
                    setRadius(getRadius()+1);
                }
                if(getStackInSlot(i).getItem() == RedItems.efficiencyAugment){
                    setMaxTime(getMaxTime()-60);
                }
            }
        }
        if(!worldObj.isRemote){
            List<Entity> butcheroos = new ArrayList<Entity>();
            if(isKillCows())butcheroos.addAll(getEntities(EntityCow.class));
            if(isKillSheep())butcheroos.addAll(getEntities(EntitySheep.class));
            if(isKillPig())butcheroos.addAll(getEntities(EntityPig.class));
//            System.out.println(butcheroos);
            for (int i=0; i<butcheroos.size(); i++){
                Entity en = butcheroos.get(i);
            }
        }
    }

    public List<? extends Entity> getEntities(Class entityType){
        double minX, maxX, minZ, maxZ;
        IBlockState state = worldObj.getBlockState(pos);
        int direction = state.getValue(FAC).getIndex();
        switch(direction){
            case 3:
                minX = this.pos.getX()+radius+1;
                maxX = this.pos.getX()-radius;
                minZ = this.pos.getZ()+1;
                maxZ = this.pos.getZ()+2+(radius*2);
                break;
            case 5:
                minX = this.pos.getX()+1;
                maxX = this.pos.getX()+2+(radius*2);
                minZ = this.pos.getZ()+radius+1;
                maxZ = this.pos.getZ()-radius;
                break;
            case 4:
                minX = this.pos.getX();
                maxX = this.pos.getX()-1-(radius*2);
                minZ = this.pos.getZ()-radius;
                maxZ = this.pos.getZ()+1+radius;
                break;
            case 2:
                minX = this.pos.getX()-1-radius;
                maxX = this.pos.getX()+1+radius;
                minZ = this.pos.getZ();
                maxZ = this.pos.getZ()-1-radius*2;
                break;
            default: return null;
        }
        return this.worldObj.getEntitiesWithinAABB(entityType, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ));
    }

    public int getAnimalCount(){
        double minX, maxX, minZ, maxZ;
        IBlockState state = worldObj.getBlockState(pos);
        int direction = state.getValue(FAC).getIndex();
        int count = 0;
        switch(direction){
            case 3:
                minX = this.pos.getX()+radius+1;
                maxX = this.pos.getX()-radius;
                minZ = this.pos.getZ()+1;
                maxZ = this.pos.getZ()+2+(radius*2);
                break;
            case 5:
                minX = this.pos.getX()+1;
                maxX = this.pos.getX()+2+(radius*2);
                minZ = this.pos.getZ()+radius+1;
                maxZ = this.pos.getZ()-radius;
                break;
            case 4:
                minX = this.pos.getX();
                maxX = this.pos.getX()-1-(radius*2);
                minZ = this.pos.getZ()-radius;
                maxZ = this.pos.getZ()+1+radius;
                break;
            case 2:
                minX = this.pos.getX()-1-radius;
                maxX = this.pos.getX()+1+radius;
                minZ = this.pos.getZ();
                maxZ = this.pos.getZ()-1-radius*2;
                break;
            default: return 0;
        }
        count += this.killSheep ? this.worldObj.getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ)).size() : 0;
        count += this.killCows ? this.worldObj.getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ)).size() : 0;
        count += this.killPig ? this.worldObj.getEntitiesWithinAABB(EntityPig.class, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ)).size() : 0;
        return count;
    }

    public boolean isKillCows() {
        return killCows;
    }

    public boolean isKillPig() {
        return killPig;
    }

    public boolean isKillSheep() {
        return killSheep;
    }

    public void setKillCows(boolean killCows) {
        this.killCows = killCows;
    }

    public void setKillPig(boolean killPig) {
        this.killPig = killPig;
    }

    public void setKillSheep(boolean killSheep) {
        this.killSheep = killSheep;
    }

    public int getTimer() {
        return timer;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inv[index];
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
        if (this.inv[index] != null){
            ItemStack itemstack = this.inv[index];
            this.inv[index] = null;
            return itemstack;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inv[index] = stack;
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
        for (int i = 0; i < inv.length; i++) {
            inv[i] = null;
        }
    }

    @Override
    public String getName() {
        return "Redstonic Shearer";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Redstonic Shearer");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);

        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inv.length; ++i) {
            if (this.inv[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setInteger("SlotID", i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbtTag.setTag("Items", nbttaglist);
        nbtTag.setBoolean("cows", isKillCows());
        nbtTag.setBoolean("sheep", isKillSheep());
        nbtTag.setBoolean("pig", isKillPig());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);

        int invSize = getSizeInventory();
        this.inv = new ItemStack[invSize];

        NBTTagList nbttaglist = nbtTag.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getInteger("SlotID");

            if (j >= 0 && j < this.inv.length) {
                this.inv[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        setKillPig(nbtTag.getBoolean("pig"));
        setKillCows(nbtTag.getBoolean("cows"));
        setKillSheep(nbtTag.getBoolean("sheep"));
    }

    @Override
    public Packet<?> getDescriptionPacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(pos, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return maxEnergy;
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        int used = storage.receiveEnergy(maxReceive, simulate);
        if (used > 0 && !simulate) {
            this.markDirty();
        }
        return used;
    }

}
