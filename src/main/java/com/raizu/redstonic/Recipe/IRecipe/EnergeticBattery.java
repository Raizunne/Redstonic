package com.raizu.redstonic.Recipe.IRecipe;

import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Utils.Util;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nullable;

/**
 * Created by Raizu on 22/10/2016.
 * as a part of Redstonic
 **/
public class EnergeticBattery extends ShapedOreRecipe {

    public EnergeticBattery(ItemStack result, Object... recipe) {
        super(result, recipe);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack newBat = Battery.getBattery(Battery.Types.ENERGETIC);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("Energy", inv.getStackInSlot(7).getTagCompound().getInteger("Energy"));
        newBat.setTagCompound(tag);
        return newBat;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return super.getRecipeOutput();
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        // HARD CODED CUZ I DONT PLAN ON USING THIS AGAIN LMAO
        ItemStack[] grid = {
                null, new ItemStack(Blocks.REDSTONE_BLOCK, 1, 0), null,
                Util.findItemStack("EnderIO", "itemAlloy", 1, 1), Util.findItemStack("EnderIO", "blockIngotStorage", 1, 1), Util.findItemStack("EnderIO", "itemAlloy", 1, 1),
                new ItemStack(Blocks.REDSTONE_BLOCK, 1, 0), Battery.getBattery(Battery.Types.BASIC), new ItemStack(Blocks.REDSTONE_BLOCK, 1, 0)
        };
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if(!ItemStack.areItemsEqualIgnoreDurability(inv.getStackInSlot(i), grid[i]))return false;
        }
        return true;
    }
}
