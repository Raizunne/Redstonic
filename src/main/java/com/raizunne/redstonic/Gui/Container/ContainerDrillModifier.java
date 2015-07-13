package com.raizunne.redstonic.Gui.Container;

import com.raizunne.redstonic.Gui.Slot.SlotAugment;
import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import com.raizunne.redstonic.Item.Sword.SwordBlade;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:17 PM.
 */
public class ContainerDrillModifier extends Container {

    private TEDrillModifier tileentity;

    public ContainerDrillModifier(InventoryPlayer invplayer, TEDrillModifier te) {
        this.tileentity = te;

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

        addSlotToContainer(new Slot(te, 0, 33, 42));
        addSlotToContainer(new Slot(te, 1, 67, 19));
        addSlotToContainer(new Slot(te, 2, 67, 42));
        addSlotToContainer(new Slot(te, 3, 67, 65));

        addSlotToContainer(new SlotAugment(te, 5, 91, 32));
        addSlotToContainer(new SlotAugment(te, 6, 109, 32));
        addSlotToContainer(new SlotAugment(te, 7, 127, 32));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileentity.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        ItemStack creative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(par2 < 35){
                if(itemstack1.getItem() instanceof RedstonicDrill || itemstack1.getItem() instanceof RedstonicSword){
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
                }else if(itemstack1.getItem() instanceof ItemBattery) {
                    if (!mergeItemStack(itemstack1, 39, 40, false)) {
                        if (!mergeItemStack(itemstack1, 9, 35, false)) {
                            return null;
                        }
                    }
                }else if(Loader.isModLoaded("ThermalExpansion")){
                    if(itemstack1.isItemEqual(hardened) || itemstack1.isItemEqual(reinforced) || itemstack1.isItemEqual(resonant) || itemstack1.isItemEqual(creative)){
                        if (!mergeItemStack(itemstack1, 39, 40, false)) {
                            if (!mergeItemStack(itemstack1, 9, 35, false)) {
                                return null;
                            }
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
            }else if(par2<9){
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
            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}
