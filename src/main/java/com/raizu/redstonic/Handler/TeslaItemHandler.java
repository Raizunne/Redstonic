package com.raizu.redstonic.Handler;

import cofh.api.energy.IEnergyContainerItem;
import com.raizu.redstonic.Items.RedItems;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Raizu on 25/10/2016.
 * as a part of Redstonic
 **/
public class TeslaItemHandler extends BaseTeslaContainer {

    private ItemStack stack;
    boolean canExtract;

    public TeslaItemHandler(ItemStack stack, boolean canExtract) {
        this.stack = stack;
        this.canExtract = canExtract;
    }

    @Override
    public long getStoredPower() {
        return stack.getTagCompound()==null ? 0 : stack.getTagCompound().getInteger("Energy");
    }

    @Override
    public long givePower(long Tesla, boolean simulated) {
        final long acceptedTesla = Math.min(this.getCapacity() - this.getStoredPower(), Math.min(this.getInputRate(), Tesla));
        long storedPower = this.getStoredPower();
        if (!simulated)
            storedPower+= acceptedTesla;
        NBTTagCompound tag = stack.getTagCompound();
        stack.getTagCompound().setInteger("Energy", (int)storedPower);
        stack.setTagCompound(tag);
        return acceptedTesla;
    }

    @Override
    public long getCapacity() {
        return stack.getTagCompound()==null ? 0 : stack.getTagCompound().getInteger("maxEnergy");
    }

    @Override
    public long takePower(long Tesla, boolean simulated) {
        if(!canExtract)return 0;
        final long removedPower = Math.min(this.getStoredPower(), Math.min(this.getOutputRate(), Tesla));
        long storedPower = this.getStoredPower();
        if (!simulated)
            storedPower-= removedPower;
        NBTTagCompound tag = stack.getTagCompound();
        stack.getTagCompound().setInteger("Energy", (int)storedPower);
        stack.setTagCompound(tag);
        return removedPower;
    }

    @Override
    public long getInputRate() {
        return stack.getTagCompound()==null ? 0 : RedItems.battery.maxReceive[stack.getTagCompound().getInteger("battery")];
    }

    @Override
    public long getOutputRate() {
        return stack.getTagCompound()==null ? 0 : RedItems.battery.maxReceive[stack.getTagCompound().getInteger("battery")];
    }
}
