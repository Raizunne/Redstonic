package com.raizunne.redstonic.Items.Drill;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Raizu on 05/05/2016, 06:38 PM.
 */
public class RedstonicDrill extends ItemPickaxe{

    PropertyInteger HEAD = PropertyInteger.create("head", 0, 7);
    PropertyInteger BODY = PropertyInteger.create("body", 0, 7);

    protected RedstonicDrill() {
        super(ToolMaterial.DIAMOND);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
    }
}
