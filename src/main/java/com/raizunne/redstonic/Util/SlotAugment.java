package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.Items.Augment;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizu on 05/05/2016, 12:45 AM.
 */
public class SlotAugment extends Slot {

    public SlotAugment(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof Augment;
    }
}
