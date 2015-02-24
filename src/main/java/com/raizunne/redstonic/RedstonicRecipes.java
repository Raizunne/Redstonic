package com.raizunne.redstonic;

import com.raizunne.redstonic.Item.IRecipes.HotswapSet;
import com.raizunne.redstonic.Util.TEHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Raizunne as a part of Redstonic
 * on 06/02/2015, 09:56 PM.
 */
public class RedstonicRecipes {

    public static void init(){
        GameRegistry.addShapelessRecipe(new ItemStack(RedstonicItems.ManualBook), new Object[]{Items.book, Items.stone_pickaxe});

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

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.EnergyAugment), new Object[]{
                "III",
                "IBI",
                "III", 'I', Items.iron_ingot, 'B', TEHelper.capacitorRedstone});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.IronBody), new Object[]{
                " G ",
                "IBG",
                "II ", 'I', Items.iron_ingot, 'B', TEHelper.coilElectrum, 'G', TEHelper.gearIron});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.ElectrumBody),
                " G ",
                "IBG",
                "II ", 'I', "ingotElectrum", 'B', TEHelper.coilElectrum, 'G', TEHelper.gearElectrum));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnderiumBody),
                " G ",
                "IBG",
                "II ", 'I', "ingotEnderium", 'B', TEHelper.coilElectrum, 'G', TEHelper.gearEnderium));

        GameRegistry.addRecipe(new ItemStack(RedstonicBlocks.Modifier), new Object[]{
                "WIW",
                "IGI",
                "WIW", 'W', Blocks.log, 'I', Items.iron_ingot, 'G', TEHelper.gearInvar});

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

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.Energizer),
                "LUL",
                "UHU",
                "LUL", 'L', "ingotLead", 'U', "ingotLumium", 'H', "blockGlassHardened", 'D', RedstonicItems.GoldHead));

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.EndHead), new Object[]{
                " D ",
                "IWG",
                "ECE", 'D', RedstonicItems.DiamondHead, 'I', RedstonicItems.IronHead, 'W', Items.nether_star, 'G', RedstonicItems.GoldHead, 'E', RedstonicItems.EnergizerFull, 'C', TEHelper.coilElectrum});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.UltimateBody),
                " G ",
                "IWG",
                "II ", 'W', Items.nether_star, 'I',  "ingotPlatinum", 'B', TEHelper.coilElectrum, 'G', RedstonicItems.EnergizerFull));

        IRecipe hotswap = new HotswapSet();
        GameRegistry.addRecipe(hotswap);
        TEHelper.addTransposerFill(10000, new ItemStack(RedstonicItems.Energizer), new ItemStack(RedstonicItems.EnergizerFull), new FluidStack(FluidRegistry.getFluid("redstone"), 64000), false);

    }

}