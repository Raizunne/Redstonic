package com.raizunne.redstonic.Item.IRecipes;

import com.raizunne.redstonic.RedstonicItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 06/04/2015, 07:04 PM.
 */
public class ContainerSet implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        return onlyOne(inv, RedstonicItems.RedContainer) && onlyTwoItemsCrafting(inv) && getContainer(inv)!=null && getContainer(inv).stackTagCompound.getInteger("block")==-1 && getOtherStack(inv)!=null && getOtherStack(inv).getItem() instanceof ItemBlock;

    }

    public boolean onlyOne(InventoryCrafting inv, Item item){
        int lol = 0;
        for(int i=0; i<inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).getItem() == item){
                lol++;
            }
            if(lol==2){
                return false;
            }
        }
        return true;
    }

    public boolean onlyTwoItemsCrafting(InventoryCrafting inv){
        int lol = 0;
        for(int i=0; i<inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i)!=null){
                lol++;
            }
            if(lol>=3){
                return false;
            }
        }
        return lol<=2;
    }

    public ItemStack getOtherStack(InventoryCrafting inv){
        ItemStack otherStack;
        for(int i=0; i<inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).getItem()!=RedstonicItems.RedContainer){
                otherStack = inv.getStackInSlot(i);
                return otherStack.copy();
            }
        }
        return null;
    }

    public ItemStack getContainer(InventoryCrafting inv){
        ItemStack container;
        for(int i=0; i<inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i)!=null && inv.getStackInSlot(i).getItem()==RedstonicItems.RedContainer){
                container = inv.getStackInSlot(i);
                return container.copy();
            }
        }
        return null;
    }


    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if(getContainer(inv)!=null){
            ItemStack container = getContainer(inv);
            int blockId = ItemBlock.getIdFromItem(getOtherStack(inv).getItem());
            int blockMeta = getOtherStack(inv).getItemDamage();
            int size = getOtherStack(inv).stackSize;
            container.stackTagCompound.setInteger("block", blockId);
            container.stackTagCompound.setInteger("blockMeta", blockMeta);
            container.stackTagCompound.setInteger("number", 1);
            return container;
        }
        return null;
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
