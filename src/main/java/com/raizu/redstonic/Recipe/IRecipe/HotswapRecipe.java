package com.raizu.redstonic.Recipe.IRecipe;

import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.RedItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 18/10/2016.
 * as a part of Redstonic
 **/
public class HotswapRecipe implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        List<ItemStack> itemsInGrid = new ArrayList<ItemStack>();
        int count = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) != null) {
                if(inv.getStackInSlot(i).getItem() instanceof DrillAugment && inv.getStackInSlot(i).getMetadata()==3){
                    if(inv.getStackInSlot(i).getTagCompound()==null)itemsInGrid.add(inv.getStackInSlot(i));
                    else if(inv.getStackInSlot(i).getTagCompound().getInteger("hotswapAugment")==-1)itemsInGrid.add(inv.getStackInSlot(i));
                }else if (inv.getStackInSlot(i).getItem() instanceof DrillHead) {
                    itemsInGrid.add(inv.getStackInSlot(i));
                }
                count++;
            }
        }
        return itemsInGrid.size()==2 && count==2;
    }

    public List<ItemStack> getItems(InventoryCrafting inv){
        List<ItemStack> retItems = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if(inv.getStackInSlot(i)!=null &&
                    ((inv.getStackInSlot(i).getItem() instanceof DrillAugment && inv.getStackInSlot(i).getMetadata()==3) || (inv.getStackInSlot(i).getItem() instanceof DrillHead))) {
                retItems.add(inv.getStackInSlot(i));
            }
        }
        return retItems;
    }

    @Nullable
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack newAug = new ItemStack(RedItems.drillAugment, 1, 3);
        ItemStack head = null;
        for (ItemStack stack : getItems(inv)){
            if(stack.getItem() instanceof DrillHead)head = stack;
        }
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("hotswapHead", head!=null ? head.getMetadata() : -1);
        newAug.setTagCompound(tag);
        return newAug;
    }

    @Override
    public int getRecipeSize() {
        return 0;
    }

    @Nullable
    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        inv.clear();
        return new ItemStack[]{};
    }
}
