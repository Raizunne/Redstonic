package com.raizu.redstonic.Handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

/**
 * Created by Raizu on 25/10/2016.
 * as a part of Redstonic
 **/
public class EnergyCapabilityProvider implements ICapabilityProvider {

    ItemStack stack;
    boolean canExtract;

    public EnergyCapabilityProvider(ItemStack stack, boolean canExtract){
        this.stack = stack;
        this.canExtract = canExtract;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability== CapabilityEnergy.ENERGY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability==CapabilityEnergy.ENERGY){
            return (T) new TeslaItemHandler(stack,canExtract);
        }
        return null;
    }
}
