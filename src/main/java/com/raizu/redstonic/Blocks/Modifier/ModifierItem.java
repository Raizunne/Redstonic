package com.raizu.redstonic.Blocks.Modifier;

import com.raizu.redstonic.Redstonic;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class ModifierItem extends ItemBlock {

    public ModifierItem(Block block) {
        super(block);
        setRegistryName("modifierItem");
        setUnlocalizedName("modifierItem");
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Used to craft Redstonic");
        tooltip.add("drills and swords.");
    }
}
