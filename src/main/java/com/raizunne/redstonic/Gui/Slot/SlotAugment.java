package com.raizunne.redstonic.Gui.Slot;

import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:20 PM.
 */
public class SlotAugment extends Slot{

    public SlotAugment(IInventory IInventory, int id, int x, int y) {
        super(IInventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return itemstack.getItem() instanceof DrillAugment || itemstack.getItem() instanceof SwordAugment;
    }
}
