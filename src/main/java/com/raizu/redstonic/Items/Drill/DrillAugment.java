package com.raizu.redstonic.Items.Drill;

import com.raizu.redstonic.Items.RedItems;
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

import javax.annotation.Nullable;
import javax.xml.soap.Text;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class DrillAugment extends Item {

    public String augments[] = {"Energy", "Speed", "SpeedII", "Hotswap", "Heavy"};

    public DrillAugment(){
        setRegistryName("DrillAugment");
        setMaxStackSize(1);
        setCreativeTab(Redstonic.redTab);
        setHasSubtypes(true);
        setMaxDamage(0);
        addPropertyOverride(new ResourceLocation("metadata"), new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return stack.getMetadata();
            }
        });
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return augments[stack.getMetadata()]+"DrillAugment";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 1; i < augments.length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getMetadata()==3 && stack.getTagCompound()==null){
            NBTTagCompound tag = new NBTTagCompound();
            if(!tag.hasKey("hotswapHead"))tag.setInteger("hotswapHead", -1);
        }
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        switch(stack.getMetadata()){
            case 3: {
                tooltip.add("Swap Drill Heads on the go!");
                tooltip.add("1500 RF per swap.");
                String headName = stack.getTagCompound()==null ? "None" : stack.getTagCompound().getInteger("hotswapHead")==-1 ? "None" : RedItems.drillHead.heads[stack.getTagCompound().getInteger("hotswapHead")]+" Drill Head";
                tooltip.add("Head: " + TextFormatting.YELLOW+headName);
            }
        }
    }
}
