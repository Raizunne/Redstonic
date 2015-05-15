package com.raizunne.redstonic.Item.IRecipes;

import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizunne as a part of Redstonic
 * on 02/05/2015, 11:34 AM.
 */
public class EnergeticBattery implements IRecipe {

    InventoryCrafting inv;

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        this.inv = inv;
        if(checkNull(new int[]{1,3,4,5,6,7,8}))return false;
        if(slot(0)==null && slot(1)==slot(6) && slot(6)==slot(8) && slot(8)==new ItemStack(Blocks.redstone_block) &&
                slot(3)== OreDictionary.getOres("ingotEnergeticAlloy").get(0) && slot(5)==slot(3) && slot(4)==OreDictionary.getOres("blockPhasedIron").get(0) && slot(7).getItem() instanceof ItemBattery){
            if(slot(7).getItem()== RedstonicItems.basicBattery){
                return true;
            }
        }
        return false;
    }

    public boolean checkNull(int[] i){
        for(int f=0; f<i.length; f++){
            if(slot(i[f])==null){
                return false;
            }
        }
        return true;
    }

    public ItemStack slot(int i){
        return inv.getStackInSlot(i);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
        return new ItemStack(RedstonicItems.energizedBattery);
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
