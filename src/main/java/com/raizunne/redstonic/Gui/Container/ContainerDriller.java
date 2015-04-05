package com.raizunne.redstonic.Gui.Container;

import com.raizunne.redstonic.TileEntity.TEDriller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizunne as a part of Redstonic
 * on 31/03/2015, 11:58 AM.
 */
public class ContainerDriller extends Container {

    public TEDriller tileentity;

    public ContainerDriller(InventoryPlayer invplayer, TEDriller te){
        this.tileentity = te;

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 120));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 62 + y * 18));
            }
        }
        addSlotToContainer(new Slot(te, 0, 61, 24));
        addSlotToContainer(new Slot(te, 1, 79, 24));
        addSlotToContainer(new Slot(te, 2, 97, 24));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileentity.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }

}

