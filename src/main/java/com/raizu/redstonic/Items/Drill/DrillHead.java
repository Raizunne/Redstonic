package com.raizu.redstonic.Items.Drill;

import com.raizu.redstonic.Redstonic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class DrillHead extends Item {

    public int headCount = 8;
    public String[] heads = {"Iron", "Gold", "Diamond", "Heavy", "Fortuitous", "Silky", "Blazer", "Ultimate"};
    public int[] energyCost = {800, 1200, 1000, 2700, 2000, 1400, 800, 7000};

    public DrillHead(){
        setRegistryName("DrillHead");
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
        if(stack.getTagCompound()!=null){
            tooltip.add("Blocks Mined: "+stack.getTagCompound().getInteger("blocks"));
        }
        switch(stack.getMetadata()){
            case 0: tooltip.add("Regular Mining Speed"); break;
            case 1: tooltip.add("Very Fast Mining Speed"); break;
            case 2: tooltip.add("Fast Mining Speed"); break;
            case 3: tooltip.add("3x3 Mining"); break;
            case 4: tooltip.add("Fortune IV Mining"); break;
            case 5: tooltip.add("Silk Touch Mining"); break;
            case 6: tooltip.add("Auto-Smelt Mining"); break;
            case 7: tooltip.add("Instant Mining Speed");tooltip.add("Requires Ultimate Drill Body"); break;
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getTagCompound() == null){
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("blocks", 0);
            stack.setTagCompound(tag);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return heads[stack.getMetadata()]+"Head";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < headCount; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
