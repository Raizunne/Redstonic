package com.raizu.redstonic.Items;

import cofh.api.energy.IEnergyContainerItem;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class Battery extends Item implements IEnergyContainerItem {

    public int batteryCount = 4;
    public String[] names = {"Basic", "Energized", "Great", "Absolute"};
    public int[] maxEnergy = {500000, 5000000, 25000000, -1};
    public int[] maxReceive = {1600, 16000, 80000, 100000};

    public Battery(){
        setRegistryName("Battery");
        setCreativeTab(Redstonic.redTab);
        setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
        addPropertyOverride(new ResourceLocation("metadata"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack.getMetadata();
            }
        });
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if(stack.getTagCompound()==null)return 0;
        return 1-((double)stack.getTagCompound().getInteger("Energy")/(double)maxEnergy[stack.getMetadata()]);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if(maxEnergy[stack.getMetadata()]==-1)return false;
        return !(getDurabilityForDisplay(stack)==0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return names[stack.getMetadata()]+"Battery";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < batteryCount; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        setDefaults(stack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int maxEnergy = this.maxEnergy[stack.getMetadata()], maxReceive = this.maxReceive[stack.getMetadata()];
        if(stack.getTagCompound()==null){
            if(maxEnergy==-1){
                tooltip.add(StringUtils.localize("redstonic.energy.maxcharge") + ": §k10000§r§7 RF");
            }else{
                tooltip.add(StringUtils.localize("redstonic.energy.maxcharge") + ": " + NumberFormat.getInstance().format(maxEnergy) + " RF");
            }
            tooltip.add(StringUtils.localize("redstonic.energy.inout") + ": " + NumberFormat.getInstance().format(maxReceive) + " RF/t");
        }else{
            NBTTagCompound tag = stack.getTagCompound();
            int energyStored = tag.getInteger("Energy");
            if(maxEnergy!=-1){
                tooltip.add(StringUtils.localize("redstonic.energy.charge") + ": " + StringUtils.progressBar(energyStored, maxEnergy, 30) + " " + ((energyStored*100)/maxEnergy)+"%");
                tooltip.add("-   " + TextFormatting.YELLOW + NumberFormat.getInstance().format(energyStored) + TextFormatting.GRAY +"/" + TextFormatting.YELLOW + NumberFormat.getInstance().format(maxEnergy) + TextFormatting.GRAY + " RF");
                tooltip.add("-   " + StringUtils.localize("redstonic.energy.inout") +": " + NumberFormat.getInstance().format(maxReceive) + "RF/t");
            }else{
                tooltip.add(StringUtils.localize("redstonic.energy.charge") + ": [" + TextFormatting.LIGHT_PURPLE +  "==/==/==/==" + TextFormatting.GRAY + "] " + "101%");
                tooltip.add("-   §k10000§r§7 / §k10000§r§7 RF");
                tooltip.add("-   " + StringUtils.localize("redstonic.energy.inout") + ": " + NumberFormat.getInstance().format(maxReceive) + "RF/t");
            }
        }
    }

    public void setDefaults(ItemStack stack){
        if(stack.getTagCompound()==null){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("max", maxEnergy[stack.getMetadata()]);
            tag.setInteger("Energy", 0);
            tag.setString("name", names[stack.getMetadata()]);
            tag.setInteger("maxReceive", maxReceive[stack.getMetadata()]);
            stack.setTagCompound(tag);
        }
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        setDefaults(stack);
        NBTTagCompound tag = stack.getTagCompound();
        int energyStored = tag.getInteger("Energy");
        int energyReceived = Math.min(this.maxEnergy[stack.getMetadata()]  - energyStored, Math.min(this.maxReceive[stack.getMetadata()] , maxReceive));
        if (!simulate) {
            energyStored += energyReceived;
            tag.setInteger("Energy", energyStored);
            stack.setTagCompound(tag);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        if(stack.getTagCompound()==null || stack.getTagCompound().hasKey("Energy")){
            return 0;
        }
        return stack.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return this.maxReceive[stack.getMetadata()];
    }

    public enum Types{
        BASIC(0),ENERGETIC(1),GREAT(2);
        int meta;
        Types(int i){
            meta=i;
        }
    }

    public static ItemStack getBattery(Types type){
        return new ItemStack(RedItems.battery, 1, type.meta);
    }
}
