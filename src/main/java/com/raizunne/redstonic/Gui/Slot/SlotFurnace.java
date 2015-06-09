package com.raizunne.redstonic.Gui.Slot;

import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Created by Raizunne as a part of Redstonic
 * on 19/05/2015, 10:40 PM.
 */
public class SlotFurnace extends Slot {

    public SlotFurnace(IInventory IInventory, int id, int x, int y) {
        super(IInventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return FurnaceRecipes.smelting().getSmeltingResult(itemstack)!=null;
    }
}
