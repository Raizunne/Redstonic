package com.raizunne.redstonic.Item;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Raizunne as a part of Redstonic
 * on 01/03/2015, 03:43 PM.
 */
public class ItemBattery extends Item implements IEnergyContainerItem {

    int type;

    public ItemBattery(int type){
        this.type = type;
        setMaxDamage(80);
        setUnlocalizedName(getUnlocalizedName());
        setMaxStackSize(1);
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("Energy", 0);
            stack.stackTagCompound.setInteger("maxEnergy", getMaxEnergy());
            fixDamage(stack);
        }else{
            if(stack.stackTagCompound.getInteger("Energy")>getMaxEnergy()){
                stack.stackTagCompound.setInteger("Energy", getMaxEnergy());
            }
            if(stack.stackTagCompound.getInteger("maxEnergy")==0){
                stack.stackTagCompound.setInteger("maxEnergy", getMaxEnergy());
            }
//            fixDamage(stack);
        }
    }

    @Override
    public void onCreated(ItemStack stack, World p_77622_2_, EntityPlayer p_77622_3_) {
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("Energy", 0);
            stack.stackTagCompound.setInteger("maxEnergy", getMaxEnergy());
            fixDamage(stack);
        }else{
            fixDamage(stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if(nbt!=null) {
            int Energy = nbt.getInteger("Energy");
            list.add(EnumChatFormatting.GREEN + "Energy: " + EnumChatFormatting.GRAY + (getMaxEnergy()==-1?EnumChatFormatting.OBFUSCATED + "9001" : NumberFormat.getNumberInstance(Locale.US).format(Energy)) +
                    EnumChatFormatting.RESET + EnumChatFormatting.GRAY + "/" + (getMaxEnergy()==-1? "Creative" : NumberFormat.getNumberInstance(Locale.US).format(getMaxEnergy())) + " RF");
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add(EnumChatFormatting.DARK_AQUA + "Max Input: " + EnumChatFormatting.GRAY + NumberFormat.getNumberInstance(Locale.US).format(getMaxInput()) + " RF/t");
                list.add(EnumChatFormatting.DARK_AQUA + "Max Output: " + EnumChatFormatting.GRAY + NumberFormat.getNumberInstance(Locale.US).format(getMaxOutput()) + " RF/t");
            }else{
                list.add(Util.ItemShiftInfo);
            }
        }else{
            list.add("Tier " + getTier());
            list.add("Max Energy: " + (getMaxEnergy()==-1?EnumChatFormatting.OBFUSCATED + "9001":NumberFormat.getNumberInstance(Locale.US).format(getMaxEnergy())));
            if(!Loader.isModLoaded("EnderIO") && type!=0){
                list.add("Missing mod" + EnumChatFormatting.DARK_AQUA + " EnderIO");
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        switch(type){
            case 0: return "basicBattery";
            case 1: return "energizedBattery";
            case 2: return "greatBattery";
            case 3: return "infiniteBattery";
            default: return "unknownBattery";
        }
    }

    public int getMaxEnergy(){
        switch(type){
            case 0: return 500000;
            case 1: return 5000000;
            case 2: return 25000000;
            case 3: return -1;
            default: return 0;
        }
    }

    public int getMaxOutput(){
        switch(type){
            case 0: return 8000;
            case 1: return 16000;
            case 2: return 48000;
            case 3: return 64000000;
            default: return 0;
        }
    }

    public int getMaxInput(){
        switch(type){
            case 0: return 8000;
            case 1: return 16000;
            case 2: return 48000;
            case 3: return 64000000;
            default: return 1;
        }
    }

    public int getTier(){
        switch(type){
            case 0: return 1;
            case 1: return 2;
            case 2: return 3;
            case 3: return 9001;
            default: return 0;
        }
    }

    IIcon[] icons;

    @Override
    public void registerIcons(IIconRegister i) {
        icons = new IIcon[4];
        icons[0] = i.registerIcon("redstonic:Material/Battery/BasicBattery");
        icons[1] = i.registerIcon("redstonic:Material/Battery/EnergizedBattery");
        icons[2] = i.registerIcon("redstonic:Material/Battery/GreatBattery");
        icons[3] = i.registerIcon("redstonic:Material/Battery/InfiniteBattery");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return icons[type];
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return icons[type];
    }

    public void fixDamage(ItemStack stack){
        double modifier = (double)80/getMaxEnergy();
        if(getMaxEnergy()!=-1) {
            stack.setItemDamage((int) (80 - stack.stackTagCompound.getInteger("Energy") * modifier));
        }else{
            stack.setItemDamage(0);
        }
    }

    public void takeEnergy(ItemStack stack, int i){
        stack.stackTagCompound.setInteger("Energy", stack.stackTagCompound.getInteger("Energy")-i);
        double modifier = (double)80/stack.stackTagCompound.getInteger("maxEnergy");
        stack.setItemDamage((int)(80 - stack.stackTagCompound.getInteger("Energy")*modifier));
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int maxEnergy = container.stackTagCompound.getInteger("maxEnergy");
        int energyReceived = Math.min(maxEnergy - energy, Math.min(getMaxInput(), maxReceive));

        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        fixDamage(container);
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(getMaxEnergy(), maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        fixDamage(container);
        return energyExtracted;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return false;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("maxEnergy");
    }
}
