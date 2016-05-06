package com.raizunne.redstonic.Blocks.Butcher;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 05/05/2016, 08:28 PM.
 */
public class ItemButcher extends ItemBlock {

    public ItemButcher(Block block) {
        super(block);
        setRegistryName("itemButcher");
        setUnlocalizedName("itemButcher");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
