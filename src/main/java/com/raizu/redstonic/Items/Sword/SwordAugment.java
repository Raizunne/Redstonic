package com.raizu.redstonic.Items.Sword;

import com.raizu.redstonic.Items.Material;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 16/10/2016.
 * as a part of Redstonic
 **/
public class SwordAugment extends Item {

    public String augments[] = {"Berserk", "BerserkII", "Blazer", "Fortuitous"};

    public SwordAugment() {
        setRegistryName("SwordAugment");
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
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        switch(stack.getMetadata()){
            case 0: tooltip.add("+1 " + StringUtils.localize("redstonic.sword.heartdamage")); break;
            case 1: tooltip.add("+2 " + StringUtils.localize("redstonic.sword.heartdamage")); break;
            case 2: tooltip.add(StringUtils.localize("enchantment.fire") + " II"); break;
            case 3: tooltip.add(StringUtils.localize("enchantment.lootBonus") + " III"); break;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return augments[stack.getMetadata()]+"SwordAugment";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < augments.length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    public static ItemStack getAugment(SwordPart part){
        return new ItemStack(RedItems.swordAugment, 1, part.part);
    }
}
