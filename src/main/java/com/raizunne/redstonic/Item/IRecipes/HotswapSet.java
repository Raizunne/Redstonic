package com.raizunne.redstonic.Item.IRecipes;

import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.RedstonicItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/02/2015, 03:42 PM.
 */
public class HotswapSet implements IRecipe {
    InventoryCrafting inv;

    @Override
    public boolean matches(InventoryCrafting inv, World p_77569_2_) {
        this.inv = inv;
        if(slot(1)!=null && slot(4)!=null){
            if(item(4)== RedstonicItems.HotswapAugment && item(1) instanceof DrillHead && slot(4).stackTagCompound.getInteger("hotswapHead")==-1 && item(1)!=RedstonicItems.EndHead){
                return true;
            }
        }
        return false;
    }

    public ItemStack slot(int slot){
        return inv.getStackInSlot(slot);
    }

    public Item item(int slot){
        return inv.getStackInSlot(slot).getItem();
    }

    public boolean checkSlots(InventoryCrafting inv, int slot, Item item){
        if(inv.getStackInSlot(slot)!=null){
            if(inv.getStackInSlot(slot)==new ItemStack(item)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack hotswap = new ItemStack(RedstonicItems.HotswapAugment);
        if(hotswap.stackTagCompound==null){
            hotswap.stackTagCompound = new NBTTagCompound();
            hotswap.stackTagCompound.setInteger("hotswapHead", slot(1).stackTagCompound.getInteger("head"));
        }else{
            hotswap.stackTagCompound.setInteger("hotswapHead", slot(1).stackTagCompound.getInteger("head"));
        }
        return hotswap;
    }

    @Override
    public int getRecipeSize() {
        return 0;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
