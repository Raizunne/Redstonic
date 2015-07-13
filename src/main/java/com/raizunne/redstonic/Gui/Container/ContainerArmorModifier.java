package com.raizunne.redstonic.Gui.Container;

import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import com.raizunne.redstonic.Item.Sword.SwordBlade;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
import com.raizunne.redstonic.TileEntity.TEArmorModifier;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Raizunne as a part of Redstonic
 * on 26/06/2015, 10:15 PM.
 */
public class ContainerArmorModifier extends Container {

    private TEArmorModifier tile;
    int lastEnergy;

    public ContainerArmorModifier(final EntityPlayer player, InventoryPlayer invplayer, TEArmorModifier tile){
        this.tile = tile;

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invplayer, x, 31 + 18 * x, 147));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invplayer, x + y * 9 + 9, 31 + 18 * x, 89 + y * 18));
            }
        }

        for (int i = 0; i < 4; ++i) {
            final int k = i;
            addSlotToContainer(new Slot(invplayer, invplayer.getSizeInventory() - 1 - i, 6, 23 + i * 19) {

                @Override
                public int getSlotStackLimit() {
                    return 1;
                }

                @Override
                public boolean isItemValid(ItemStack par1ItemStack) {
                    if(par1ItemStack == null) {
                        return false;
                    }
                    return par1ItemStack.getItem().isValidArmor(par1ItemStack, k, player);
                }

                @Override
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex() {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }
        addSlotToContainer(new Slot(tile, 0, 60, 38));
        for(int x=0; x<3; x++){
            for(int y=0; y<2; y++){
                addSlotToContainer(new Slot(tile, x+y*3+1, 88+x*18, 29+y*18));
            }
        }

    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tile.getEnergyStored(ForgeDirection.UP));
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i){
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if(this.lastEnergy!=this.tile.getEnergyStored(ForgeDirection.UP)){
                icrafting.sendProgressBarUpdate(this, 0, this.tile.getEnergyStored(ForgeDirection.UP));
            }
        }
        this.lastEnergy = this.tile.getEnergyStored(ForgeDirection.UP);
    }

    @Override
    public void updateProgressBar(int par1, int par2) {
        super.updateProgressBar(par1, par2);
        if(par1==0){
            this.tile.setEnergy(par2);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack stack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        System.out.println(i);
        if(slot!=null && slot.getHasStack()){
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(stack1.stackSize==0){
                slot.putStack((ItemStack)null);
            }else{
                slot.onSlotChanged();
            }
            slot.onSlotChanged();
            if(stack1.stackSize==stack.stackSize){
                return null;
            }
            slot.onPickupFromSlot(player, stack1);
        }
        return stack;
    }

    public boolean mergeArmor(EntityPlayer player, ItemStack stack, int slot){
        if(stack!=null && stack.getItem() instanceof ItemArmor){
            ItemArmor armor = (ItemArmor)stack.getItem();
            int armorType = 3-armor.armorType;
            ItemStack[] playerArmor = player.inventory.armorInventory;
            if(playerArmor[armorType]==null){
                playerArmor[armorType]=stack.copy();
                stack.stackSize=0;
                return true;
            }
            return false;
        }else{
            return false;
        }
    }

}
