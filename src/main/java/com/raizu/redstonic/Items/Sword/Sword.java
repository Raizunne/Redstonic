package com.raizu.redstonic.Items.Sword;

import cofh.api.energy.IEnergyContainerItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import com.raizu.redstonic.Utils.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import javax.xml.soap.Text;
import java.lang.reflect.Field;
import java.security.Key;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static jdk.nashorn.internal.parser.TokenKind.IR;

/**
 * Created by Raizu on 16/10/2016.
 * as a part of Redstonic
 **/
public class Sword extends Item implements IEnergyContainerItem {

    public Sword(final int blade, final int handle) {
        setUnlocalizedName("RedstonicSword");
        setRegistryName("RedstonicSword");
        setMaxStackSize(1);
        setCreativeTab(Redstonic.redTab);
        addPropertyOverride(new ResourceLocation("blade"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack==null ? blade : stack.getTagCompound()==null ? blade : stack.getTagCompound().getInteger("blade");
            }
        });
        addPropertyOverride(new ResourceLocation("handle"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack==null ? handle : stack.getTagCompound()==null ? handle : stack.getTagCompound().getInteger("handle");
            }
        });
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        return false;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return super.onEntitySwing(entityLiving, stack);
    }

    public static float getBasicSwordDamage(ItemStack stack){
        float damage = (float)RedItems.swordBlade.damageBase[stack.getTagCompound().getInteger("blade")];
        for (int i = 0; i < 3; i++) {
            if(stack.getTagCompound().hasKey("aug"+i)){
                switch(stack.getTagCompound().getInteger("aug"+i)){
                    case 0: damage+=1; break;
                    case 1: damage+=2; break;
                }
            }
        }
        return (damage);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getTagCompound()!=null){
            if(hasAugment(2, stack) || hasAugment(3, stack)){
                if(!Util.hasEnchantment(stack, "looting", 3))stack.addEnchantment(Enchantment.getEnchantmentByLocation("looting"), 3);
                if(!Util.hasEnchantment(stack, "fire_aspect", 2))stack.addEnchantment(Enchantment.getEnchantmentByLocation("fire_aspect"), 2);
            }else{
                NBTTagCompound tag = stack.getTagCompound();
                tag.removeTag("ench");
                stack.setTagCompound(tag);
            }
        }
    }

    public static boolean hasAugment(int i, ItemStack stack){
        NBTTagCompound nbt = stack.getTagCompound();
        for (int j = 0; j < 3; j++) {
            if(nbt.getInteger("aug"+j)==i)return true;
        }
        return false;
    }

    public static int getEnergyCost(ItemStack stack){
        if(stack==null || stack.getTagCompound()==null)return 0;
        return RedItems.swordBlade.energyCost[stack.getTagCompound().getInteger("blade")];
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        System.out.println(itemStackIn.getTagCompound());
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if(slot==EntityEquipmentSlot.MAINHAND){
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)-2.5, 0));
        }
        return multimap;
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        if(stack.getTagCompound()==null){
            tooltip.add(TextFormatting.RED+"Crafted in Redstonic Modifier");
        }else{
            NBTTagCompound tag = stack.getTagCompound();
            if(!tag.hasKey("blade") || !tag.hasKey("handle") || !tag.hasKey("maxEnergy") || !tag.hasKey("Energy")){
                tooltip.add(TextFormatting.YELLOW+"Sword was cheated in, doesnt work.");
                tooltip.add(TextFormatting.YELLOW+"Make a working sword in the Redstonic Modifier.");
                return;
            }
            int blade = tag.getInteger("blade");
            int energyStored = tag.getInteger("Energy"), maxEnergy = tag.getInteger("maxEnergy");
            String damageTip = "Damage: " + TextFormatting.RED + (getBasicSwordDamage(stack))+ " Hearts";
            if(maxEnergy==-1){
                tooltip.add("Charge: [" + TextFormatting.LIGHT_PURPLE +  "==/==/==/==" + TextFormatting.GRAY + "] " + "101%");
                tooltip.add(damageTip);
                tooltip.add("-   §k10000§r§7 / §k10000§r§7 RF");
            }else{
                tooltip.add("Charge: " + StringUtils.progressBar(energyStored, maxEnergy, 30) + " " + ((energyStored*100)/maxEnergy)+"%");
                tooltip.add(damageTip);
                tooltip.add("-   " + TextFormatting.YELLOW + NumberFormat.getInstance().format(energyStored) + TextFormatting.GRAY +"/" + TextFormatting.YELLOW + NumberFormat.getInstance().format(maxEnergy) + TextFormatting.GRAY + " RF");
            }
            tooltip.add("-   "+ TextFormatting.YELLOW + getEnergyCost(stack) + TextFormatting.GRAY + " RF per swing");
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
                int handle = tag.getInteger("handle"), battery = tag.getInteger("battery");
                tooltip.add("--------------------");
                tooltip.add(TextFormatting.BOLD+""+TextFormatting.RED+"Kills"+ TextFormatting.RESET + TextFormatting.GRAY+ ": " +tag.getInteger("kills"));
                tooltip.add(TextFormatting.BOLD+"Head"+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+RedItems.swordBlade.blades[blade]+" Blade");
                tooltip.add(TextFormatting.BOLD+"Body"+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+RedItems.swordHandle.handles[handle]+" Handle");
                tooltip.add(TextFormatting.BOLD+"Battery"+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+RedItems.battery.names[battery]+" Battery");
            }else{
                tooltip.add(TextFormatting.GRAY+"Press "+TextFormatting.BLUE+TextFormatting.ITALIC+"SHIFT"+TextFormatting.RESET+TextFormatting.GRAY+" for info.");
            }
            if(hasAugments(stack)){
                if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
                    for (int i = 0; i < 3; i++) {
                        if(stack.getTagCompound().hasKey("aug"+(i))){
                            tooltip.add(TextFormatting.BOLD+"Augment "+(i+1)+ TextFormatting.RESET + TextFormatting.GRAY+ ": " + TextFormatting.DARK_GRAY+RedItems.swordAugment.augments[stack.getTagCompound().getInteger("aug"+(i))]+" Augment");
                        }
                    }
                }else{
                    tooltip.add(TextFormatting.GRAY+"Press "+TextFormatting.RED+TextFormatting.ITALIC+"CTRL"+TextFormatting.RESET+TextFormatting.GRAY+" for augment info.");
                }
            }
        }
    }

    public boolean hasAugments(ItemStack stack){
        for (int i=0; i<3; i++){
            if(stack.getTagCompound().hasKey("aug"+i))return true;
        }
        return false;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        NBTTagCompound tag = stack.getTagCompound();
        int energyStored = tag.getInteger("Energy");
        int energyReceived = Math.min(stack.getTagCompound().getInteger("maxEnergy")  - energyStored, Math.min(stack.getTagCompound().getInteger("maxEnergy") , maxReceive));
        if (!simulate) {
            energyStored += energyReceived;
            tag.setInteger("Energy", energyStored);
            stack.setTagCompound(tag);
        }
        return energyReceived;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if(stack.getTagCompound()!=null && stack.getTagCompound().getInteger("maxEnergy")==-1)return false;
        return (getDurabilityForDisplay(stack)!=0);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if(stack.getTagCompound()==null)return 0;
        return 1-((double)stack.getTagCompound().getInteger("Energy")/(double)stack.getTagCompound().getInteger("maxEnergy"));
    }

    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        if(stack.getTagCompound()==null || !stack.getTagCompound().hasKey("Energy")){
            return 0;
        }
        return stack.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return stack.getTagCompound().getInteger("maxEnergy");
    }
}
