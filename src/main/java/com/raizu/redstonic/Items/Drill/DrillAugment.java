package com.raizu.redstonic.Items.Drill;

import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Items.RedItems;
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
            if(Config.disabledDrillAugments.contains(i))subItems.add(new ItemStack(itemIn, 1, i));
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
            case 1: tooltip.add("x1.3 "+ StringUtils.localize("redstonic.drill.augment.speedmult")); break;
            case 2: tooltip.add("x1.4 "+ StringUtils.localize("redstonic.drill.augment.speedmult")); break;
            case 3: {
                tooltip.add(StringUtils.localize("redstonic.drill.augment.hotswap1"));
                tooltip.add("1500 RF " + StringUtils.localize("redstonic.drill.augment.hotswap2") + ".");
                String headName = stack.getTagCompound()==null ? StringUtils.localize("redstonic.drill.none") : stack.getTagCompound().getInteger("hotswapHead")==-1 ? StringUtils.localize("redstonic.drill.none") : StringUtils.localize(RedItems.drillHead.heads[stack.getTagCompound().getInteger("hotswapHead")]+"Head.name");
                tooltip.add(StringUtils.localize("redstonic.drill.head")+": " + TextFormatting.YELLOW+headName);
            }
            case 4: tooltip.add("3x3 "+StringUtils.localize("redstonic.drill.mining"));
        }
    }

    public static ItemStack getAugment(DrillPart part){
        return new ItemStack(RedItems.drillAugment, 1, part.part);
    }
}
