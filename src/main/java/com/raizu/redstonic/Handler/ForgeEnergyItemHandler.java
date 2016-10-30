package com.raizu.redstonic.Handler;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * Created by Raizu on 25/10/2016.
 * as a part of Redstonic
 **/
public class ForgeEnergyItemHandler implements ICapabilityProvider {

    private final ItemStack stack;
    private final IEnergyContainerItem energyContainer;
    private final boolean canExtract;

    public ForgeEnergyItemHandler(ItemStack stack, IEnergyContainerItem energyContainer, boolean canExtract){
        this.stack=stack;
        this.energyContainer = energyContainer;
        this.canExtract = canExtract;
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability== CapabilityEnergy.ENERGY){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability==CapabilityEnergy.ENERGY){
            return (T) new IEnergyStorage(){
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    return energyContainer.receiveEnergy(stack, maxReceive, simulate);
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    return energyContainer.extractEnergy(stack, maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {
                    return energyContainer.getEnergyStored(stack);
                }

                @Override
                public int getMaxEnergyStored() {
                    return energyContainer.getMaxEnergyStored(stack);
                }

                @Override
                public boolean canExtract() {
                    return false;
                }

                @Override
                public boolean canReceive() {
                    return false;
                }
            };
        }
        return null;
    }

}
