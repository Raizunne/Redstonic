package com.raizunne.redstonic;

import com.raizunne.redstonic.Item.IRecipes.HotswapSet;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 * Created by Raizunne as a part of Redstonic
 * on 06/02/2015, 09:56 PM.
 */
public class RedstonicRecipes {

    public static void init(){
        GameRegistry.addRecipe(new ItemStack(RedstonicItems.IronHead), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', Items.iron_ingot, 'B', Blocks.iron_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.GoldHead), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', Items.gold_ingot, 'B', Blocks.gold_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.DiamondHead), new Object[]{
                " I ",
                "III",
                "IBI", 'I', Items.diamond, 'B', Blocks.diamond_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.HeavyHead), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', RedstonicItems.DiamondHead, 'G', RedstonicItems.GoldHead, 'H', RedstonicItems.IronHead, 'I', Items.iron_ingot, 'B', Blocks.iron_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.FortuitousHead), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', RedstonicItems.DiamondHead, 'G', RedstonicItems.GoldHead, 'H', RedstonicItems.IronHead, 'I', Items.iron_ingot, 'B', Blocks.lapis_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.SpeedAugment), new Object[]{
                "III",
                "IBI",
                "III", 'I', Items.iron_ingot, 'B', RedstonicItems.GoldHead});

        if(Loader.isModLoaded("ThermalExpansion") && Loader.isModLoaded("ThermalFoundation")){
            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EnergyAugment), new Object[]{
                    "III",
                    "IBI",
                    "III", 'I', Items.iron_ingot, 'B', new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 4)});

            GameRegistry.addRecipe(new ItemStack(RedstonicItems.IronBody), new Object[]{
                    " G ",
                    "IBG",
                    "II ", 'I', Items.iron_ingot, 'B', new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 3), 'G', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 12)});

            GameRegistry.addRecipe(new ItemStack(RedstonicItems.ElectrumBody), new Object[]{
                    " G ",
                    "IBG",
                    "II ", 'I', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 71), 'B', new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 3), 'G', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 135)});

            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EnderiumBody), new Object[]{
                    " G ",
                    "IBG",
                    "II ", 'I', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 76), 'B', new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 3), 'G', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 140)});

            GameRegistry.addRecipe(new ItemStack(RedstonicBlocks.Modifier), new Object[]{
                    "WIW",
                    "IGI",
                    "WIW", 'W', Blocks.log, 'I', Items.iron_ingot, 'G', new ItemStack(GameRegistry.findItem("ThermalFoundation", "material"), 1, 136)});
        }

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.HotswapAugment), new Object[]{
                "III",
                "DCD",
                "III", 'C', Blocks.chest, 'D', RedstonicItems.IronHead, 'I', Items.iron_ingot});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.SilkyHead), new Object[]{
                " E ",
                "EGE",
                "GDG", 'E', Items.emerald, 'G', Items.gold_ingot, 'D', RedstonicItems.DiamondHead
        });

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.BlazerHead), new Object[]{
                " C ",
                "CGC",
                "EDE", 'C', Blocks.coal_block, 'E', Items.emerald, 'G', RedstonicItems.DiamondHead, 'D', RedstonicItems.GoldHead
        });

        IRecipe hotswap = new HotswapSet();
        GameRegistry.addRecipe(hotswap);
    }

}