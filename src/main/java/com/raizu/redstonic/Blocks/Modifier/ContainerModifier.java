package com.raizu.redstonic.Blocks.Modifier;

import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.Sword.Sword;
import com.raizu.redstonic.Items.Sword.SwordAugment;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;


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

        addSlotToContainer(new Slot(tileentity, 0, 26, 42));
        addSlotToContainer(new Slot(tileentity, 1, 67, 19));
        addSlotToContainer(new Slot(tileentity, 2, 67, 42));
        addSlotToContainer(new Slot(tileentity, 3, 67, 65));
        addSlotToContainer(new Slot(tileentity, 4, 91, 32));
        addSlotToContainer(new Slot(tileentity, 5, 109, 32));
        addSlotToContainer(new Slot(tileentity, 6, 127, 32));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(index < 35){
                if(itemstack1.getItem() instanceof Drill || itemstack1.getItem() instanceof Sword){
                    if(!mergeItemStack(itemstack1, 36, 37, false)){
                        if(!mergeItemStack(itemstack1, 9, 35, false)){
                            return null;
                        }
                    }
                }else if(itemstack1.getItem() instanceof DrillHead || itemstack1.getItem() instanceof SwordBlade){
                    if(!mergeItemStack(itemstack1, 37, 38, false)){
                        if(!mergeItemStack(itemstack1, 9, 35, false)){
                            return null;
                        }
                    }
                }else if(itemstack1.getItem() instanceof DrillBody || itemstack1.getItem() instanceof SwordHandle){
                    if(!mergeItemStack(itemstack1, 38, 39, false)){
                        if(!mergeItemStack(itemstack1, 9, 35, false)){
                            return null;
                        }
                    }
                }else if(itemstack1.getItem() instanceof Battery) {
                    if (!mergeItemStack(itemstack1, 39, 40, false)) {
                        if (!mergeItemStack(itemstack1, 9, 35, false)) {
                            return null;
                        }
                    }
                }else if(itemstack1.getItem() instanceof DrillAugment || itemstack1.getItem() instanceof SwordAugment){
                    if (!mergeItemStack(itemstack1, 40, 43, false)) {
                        if (!mergeItemStack(itemstack1, 9, 35, false)) {
                            return null;
                        }
                    }
                }else{
                    if(!mergeItemStack(itemstack1, 36, 43, false)){
                        if(!mergeItemStack(itemstack1, 9, 35, false)){
                            return null;
                        }
                    }
                }
            }else if(index<9){
                if(!mergeItemStack(itemstack1, 0, 9, false)){
                    return null;
                }
            }else if(!mergeItemStack(itemstack1, 0, 35, false)){
                return null;
            }

            if(itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileentity.isUseableByPlayer(playerIn);
    }

}
