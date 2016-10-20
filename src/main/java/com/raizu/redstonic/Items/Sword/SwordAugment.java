package com.raizu.redstonic.Items.Sword;

import com.raizu.redstonic.Redstonic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
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
    public String getUnlocalizedName(ItemStack stack) {
        return augments[stack.getMetadata()]+"SwordAugment";
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < augments.length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
