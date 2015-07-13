package com.raizunne.redstonic.Gui.Container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;


/**
 * Created by Raizunne as a part of Redstonic
 * on 09/07/2015, 04:18 PM.
 */
public class ContainerMessenger extends Container {

    ItemStack stack;

    public ContainerMessenger(InventoryPlayer invplayer, ItemStack stack){
        this.stack = stack;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
