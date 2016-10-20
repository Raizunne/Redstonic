package com.raizu.redstonic.Items.Sword;

import com.raizu.redstonic.Redstonic;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 16/10/2016.
 * as a part of Redstonic
 **/
public class SwordBlade extends Item {
    public String[] blades = {"Iron", "Diamond", "Electrum", "Enderium", "Energized", "Vibrant"};
    public int[] energyCost = {300, 1000, 1500, 3000, 1500, 3000};
    public float[] damageBase = {3, 4, 6, 8, 6, 8};
    public String[] oreDic = {"bladeLowTier", "baseLowMidTier", "bladeMidTier", "bladeHighTier", "bladeMidTier", "bladeHighTier"};


    public SwordBlade() {
        setRegistryName("SwordBlade");
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

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        tooltip.add(TextFormatting.RED + "" + damageBase[stack.getMetadata()] + " Heart " + TextFormatting.GRAY + "Base Damage.");
        if(stack.getTagCompound()!=null){
            tooltip.add("Kills: "+stack.getTagCompound().getInteger("kills"));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getTagCompound() == null){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("kills", 0);
            stack.setTagCompound(tag);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return blades[stack.getMetadata()]+"Blade";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < blades.length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
            OreDictionary.registerOre(oreDic[i], new ItemStack(itemIn, 1, i));
        }
    }
}
