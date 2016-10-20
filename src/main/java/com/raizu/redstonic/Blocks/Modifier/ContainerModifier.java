package com.raizu.redstonic.Blocks.Modifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class ContainerModifier extends Container {

    private TEModifier tileentity;

    public ContainerModifier(InventoryPlayer invplayer, TEModifier modifier){
        this.tileentity = modifier;
        for (int x = 0; x < 9; x++) {

            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

        addSlotToContainer(new Slot(tileentity, 0, 33, 42));
        addSlotToContainer(new Slot(tileentity, 1, 67, 19));
        addSlotToContainer(new Slot(tileentity, 2, 67, 42));
        addSlotToContainer(new Slot(tileentity, 3, 67, 65));
        addSlotToContainer(new Slot(tileentity, 4, 91, 32));
        addSlotToContainer(new Slot(tileentity, 5, 109, 32));
        addSlotToContainer(new Slot(tileentity, 6, 127, 32));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileentity.isUseableByPlayer(playerIn);
    }

}
