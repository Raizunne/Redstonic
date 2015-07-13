package com.raizunne.redstonic.Item.Armor;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.Lang;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/07/2015, 03:39 PM.
 */
public class RedArmorBase extends ItemArmor implements ISpecialArmor, IEnergyContainerItem {

    int armorPoints = 3;
    int maxEnergy = 320000;
    int maxInput = 3200;

    public RedArmorBase(int armorType, String name){
        this(name, armorType);
    }

    public RedArmorBase(String name, int type){
        super(Redstonic.RedstonicMaterial, 0, type);
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setTextureName("redstonic:Armor/"+name);
        this.setMaxDamage(80);
        this.setCreativeTab(Redstonic.redTab);
    }

    @Override
    public void onUpdate(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("Energy", 0);
            stack.stackTagCompound.setInteger("maxEnergy", getMaxEnergy(stack));
            fixDamage(stack);
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        NBTTagCompound tag = stack.stackTagCompound;
        if(tag!=null){
            int energy = stack.stackTagCompound.getInteger("Energy");
            int maxEnergy = getMaxEnergy(stack);
            list.add(EnumChatFormatting.GREEN+"Energy: " + EnumChatFormatting.GRAY + Lang.addComas(energy) + "/" + Lang.addComas(maxEnergy) + " RF");
        }
    }

    public int getMaxEnergy(ItemStack stack){
        return maxEnergy;
    }

    public int getMaxInput(){
        return maxInput;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return this.armorPoints;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "redstonic:textures/model/armor/RedstonicArmor.png";
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int maxEnergy = getMaxEnergy(container);
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
        int energyExtracted = Math.min(energy, Math.min(getMaxEnergy(container), maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        fixDamage(container);
        return energyExtracted;
    }

    public void fixDamage(ItemStack stack){
        double modifier = (double)80/getMaxEnergy(stack);
        if(getMaxEnergy(stack)!=-1) {
            stack.setItemDamage((int) (80 - stack.stackTagCompound.getInteger("Energy") * modifier));
        }else{
            stack.setItemDamage(0);
        }
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
