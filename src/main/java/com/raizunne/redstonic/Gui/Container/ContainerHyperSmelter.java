package com.raizunne.redstonic.Gui.Container;

import com.raizunne.redstonic.Gui.Slot.SlotAugment;
import com.raizunne.redstonic.Gui.Slot.SlotFurnace;
import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import com.raizunne.redstonic.Item.Sword.SwordBlade;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
import com.raizunne.redstonic.TileEntity.TEHyperSmelter;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Raizunne as a part of Redstonic
 * on 20/05/2015, 06:52 PM.
 */
public class ContainerHyperSmelter extends Container {

    private TEHyperSmelter tileentity;
    int lastEnergy;

    public ContainerHyperSmelter(InventoryPlayer invplayer, TEHyperSmelter te) {
        this.tileentity = te;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 8 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 8 + 18 * x, 89 + y * 18));
            }
        }

        addSlotToContainer(new SlotFurnace(te, 0, 52, 25));
        addSlotToContainer(new SlotFurnace(te, 1, 52, 47));
        addSlotToContainer(new SlotFurnace(te, 2, 116, 36));
        addSlotToContainer(new SlotFurnace(te, 3, 136, 36));
        addSlotToContainer(new SlotAugment(te, 4, 8, 67));

    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tileentity.getEnergyStored(null));
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i){
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if(this.lastEnergy!=this.tileentity.getEnergyStored(null)){
                icrafting.sendProgressBarUpdate(this, 0, this.tileentity.getEnergyStored(null));
            }

        }
        this.lastEnergy = this.tileentity.getEnergyStored(ForgeDirection.UP);
    }

    @Override
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        if(par1==0){
            this.tileentity.setEnergy(par2);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileentity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(par2 < 35){
                if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1)!=null) {
                    if (!mergeItemStack(itemstack1, 36, 38, false)) {
                        if (!mergeItemStack(itemstack1, 9, 35, false)) {
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
