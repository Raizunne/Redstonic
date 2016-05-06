package com.raizunne.redstonic.Blocks.Shearer;

import com.raizunne.redstonic.Items.Augment;
import com.raizunne.redstonic.Items.RedItems;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.omg.DynamicAny.DynEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Raizu on 04/05/2016.
 */
public class TEShearer extends TileEntity implements ITickable, IInventory{

    private int timer;
    private int maxTime;
    private int sheepCount;
    private int radius;
    private ItemStack[] inv;
    PropertyDirection FAC = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public TEShearer(){
        timer = 0;
        maxTime = 200;
        inv = new ItemStack[13];
        sheepCount = 0;
        radius = 3;
    }

    @Override
    public void update() {
        IBlockState state = worldObj.getBlockState(pos);
        int direction = state.getValue(FAC).getIndex();
        setRadius(3);
        setMaxTime(200);
        for (int i = 9; i < 12; i++) {
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
            if(getSheepCount()<=0){
                return;
            }
            if(timer<maxTime){
                timer++;
            }else{
                double minX=0, maxX=0, minZ=0, maxZ=0;
                List<EntitySheep> sheeps = new ArrayList<EntitySheep>();
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
                }
                sheeps = this.worldObj.getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ));
                setSheepCount(sheeps.size());
                for (int i = 0; i < sheeps.size(); i++) {
                    EntitySheep sheep = sheeps.get(i);
                    int random = new Random().nextInt(5);
                    if(!sheep.getSheared() && sheep.getGrowingAge()>=0 && random==0){
                        ItemStack newWool = new ItemStack(Blocks.wool, new Random().nextInt(2)+1, sheep.getFleeceColor().getMetadata());
                        addItem(newWool);
                        sheep.setSheared(true);
                        worldObj.playSound(sheep.posX, sheep.posY, sheep.posZ, SoundEvents.entity_sheep_shear, SoundCategory.BLOCKS, 1, 1, false);
                    }
                }
                timer=0;
            }
        }
    }

    public boolean addItem(ItemStack stack){
        for(int i=0; i<getSizeInventory(); i++){
            if(getStackInSlot(i)==null){
                setInventorySlotContents(i, stack);
                return true;
            }else if(getStackInSlot(i).isItemEqual(stack) && getStackInSlot(i).stackSize+stack.stackSize<=64){
                getStackInSlot(i).stackSize+=stack.stackSize;
                return true;
            }
        }
        return false;
    }

    public int getTimer() {
        return timer;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }

    public int getSheepCount() {
        double minX, maxX, minZ, maxZ;
        IBlockState state = worldObj.getBlockState(pos);
        int direction = state.getValue(FAC).getIndex();
        List<EntitySheep> sheeps = new ArrayList<EntitySheep>();
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
        sheeps = this.worldObj.getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(minX, this.pos.getY(), minZ, maxX, this.pos.getY()+4, maxZ));
        return sheeps.size();
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
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
}
