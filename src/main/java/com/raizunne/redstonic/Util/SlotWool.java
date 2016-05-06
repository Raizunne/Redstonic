package com.raizunne.redstonic.Util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizu on 04/05/2016.
 */
public class SlotWool extends Slot {
    ItemStack stack;

    public SlotWool(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack1) {
        return stack1.getItem() instanceof ItemBlock && stack1.getItem()== Item.getItemFromBlock(Blocks.wool);
    }
}
