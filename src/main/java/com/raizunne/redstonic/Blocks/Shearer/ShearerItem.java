package com.raizunne.redstonic.Blocks.Shearer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 04/05/2016.
 */
public class ShearerItem extends ItemBlock {

    public ShearerItem(Block block){
        super(block);
        setUnlocalizedName("itemShearer");
        setRegistryName("itemShearer");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
