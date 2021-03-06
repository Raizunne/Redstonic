package com.raizu.redstonic.Recipe;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.Material;
import com.raizu.redstonic.Items.Materials;
import com.raizu.redstonic.Items.Sword.SwordAugment;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import com.raizu.redstonic.Recipe.IRecipe.EnergeticBattery;
import com.raizu.redstonic.Recipe.IRecipe.GreatBattery;
import com.raizu.redstonic.Recipe.IRecipe.HotswapRecipe;
import com.raizu.redstonic.Utils.Util;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import static com.raizu.redstonic.Items.Drill.DrillPart.*;
import static com.raizu.redstonic.Items.Sword.SwordPart.*;
import static com.raizu.redstonic.Items.Materials.*;

/**
 * Created by Raizu on 18/10/2016.
 * as a part of Redstonic
 **/
public class RedRecipes {

    public static IRecipe ironHead;
    public static IRecipe goldHead;
    public static IRecipe diamondHead;
    public static IRecipe fortuitousHead;
    public static IRecipe blazerHead;
    public static IRecipe silkyHead;
    public static IRecipe heavyHead;
    public static IRecipe endHead;

    public static IRecipe ironBody;
    public static IRecipe electrumBody;
    public static IRecipe enderiumBody;
    public static IRecipe energizedBody;
    public static IRecipe vibrantBody;
    public static IRecipe ultimateBody;

    public static IRecipe ironBlade;
    public static IRecipe diamondBlade;
    public static IRecipe energizedBlade;
    public static IRecipe vibrantBlade;
    public static IRecipe electrumBlade;
    public static IRecipe enderiumBlade;

    public static IRecipe ironHandle;
    public static IRecipe woodHandle;
    public static IRecipe energizedHandle;
    public static IRecipe vibrantHandle;
    public static IRecipe electrumHandle;
    public static IRecipe enderiumHandle;

    public static IRecipe speedDrillAugment;
    public static IRecipe speedIIDrillAugment;
    public static IRecipe hotswapDrillAugment;
    public static IRecipe heavyDrillAugmnet;

    public static IRecipe berserkerSwordAugmnet;
    public static IRecipe berserkerIISwordAugment;
    public static IRecipe blazerSwordAugment;
    public static IRecipe fortuitousSwordAugment;

    public static IRecipe basicBattery;
    public static IRecipe energizedBattery;
    public static IRecipe greatBattery;

    public static IRecipe modifier;
    public static IRecipe cone;

    public static void init(){
        GameRegistry.addRecipe(new HotswapRecipe());

        //IRON DRILL HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(IRON_HEAD), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', "ingotIron", 'B', "blockIron"}));
        ironHead = getAddedRecipe();

        //GOLD DRILL HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(GOLD_HEAD), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', "ingotGold", 'B', "blockGold"}));
        goldHead = getAddedRecipe();

        //DIAMOND DRIL HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(DIAMOND_HEAD), new Object[]{
                " I ",
                "III",
                "IBI", 'I', "gemDiamond", 'B', "blockDiamond"}));
        diamondHead = getAddedRecipe();

        //HEAVY DRILL HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(HEAVY_HEAD), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', DrillHead.getHead(IRON_HEAD), 'G', DrillHead.getHead(GOLD_HEAD), 'H', DrillHead.getHead(DIAMOND_HEAD), 'I', "ingotIron", 'B', "blockIron"}));
        heavyHead = getAddedRecipe();

        //FORTUITOUS HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(FORTUITOUS_HEAD), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', DrillHead.getHead(IRON_HEAD), 'G', DrillHead.getHead(GOLD_HEAD), 'H', DrillHead.getHead(DIAMOND_HEAD), 'I', "ingotIron", 'B', "blockLapis"}));
        fortuitousHead = getAddedRecipe();

        //SILKY HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(SILKY_HEAD), new Object[]{
                " E ",
                "EGE",
                "GDG", 'E', "gemEmerald", 'G', "ingotGold", 'D', DrillHead.getHead(DIAMOND_HEAD)}));
        silkyHead = getAddedRecipe();

        //BLAZER HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(BLAZER_HEAD),
                " C ","CGC","EDE",
                'C', "blockCoal", 'E', "gemEmerald", 'G', DrillHead.getHead(DIAMOND_HEAD), 'D', DrillHead.getHead(GOLD_HEAD)));
        blazerHead = getAddedRecipe();

        //IRON DRILL BODY
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(IRON_BODY),
                " G ","IBG","II ",
                'I', "ingotIron", 'B', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 1), 'G', "gearIron"));
        ironBody = getAddedRecipe();

        //END DRILL BODY
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(END_BODY),
                " G ",
                "IWG",
                "II ", 'W', Items.NETHER_STAR, 'I', "ingotVibrantium", 'G', Material.getMaterial(ENERGIZED_CAPSULE_FULL)));
        ultimateBody = getAddedRecipe();

        //ULTIMATE DRILL HEAD
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillHead.getHead(ULTIMATE_HEAD),
                " D ",
                "IWG",
                "ECE", 'D', DrillHead.getHead(DIAMOND_HEAD), 'I', DrillHead.getHead(IRON_HEAD), 'W', Items.NETHER_STAR, 'G', DrillHead.getHead(GOLD_HEAD), 'E', Material.getMaterial(ENERGIZED_CAPSULE_FULL), 'C', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 2)));
        endHead = getAddedRecipe();

        //IRON BLADE
        GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(IRON_BLADE),
                "  I",
                " B ",
                "B  ", 'I', "ingotIron", 'B', "blockIron"));
        ironBlade = getAddedRecipe();

        //DIAMOND BLADE
        GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(DIAMOND_BLADE),
                "  I",
                " B ",
                "B  ", 'I', "gemDiamond", 'B', "blockDiamond"));
        diamondBlade = getAddedRecipe();

        //WOOD SWORD HANDLE
        GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(WOOD_HANDLE),
                " I ",
                "ISI",
                "II ", 'I', "plankWood", 'S', "stickWood"));
        woodHandle = getAddedRecipe();

        //IRON SWORD HANDLE
        GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(IRON_HANDLE),
                " I ",
                "ISI",
                "II ", 'I', "ingotIron", 'S', Material.getMaterial(REDSTONE_STICK)));
        ironHandle = getAddedRecipe();

        /** ELECTRUM RECIPES */
        if(OreDictionary.getOres("ingotElectrum").size()>0){
            //ELECTRUM GEAR
            GameRegistry.addRecipe(new ShapedOreRecipe(Material.getMaterial(Materials.GEAR_ELECTRUM),
                    " I ","IGI"," I ",
                    'I', "ingotElectrum", 'G', "gearIron"));

            //ELECTRUM DRILL BODY
            GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(ELECTRUM_BODY),
                    " G ","IBG","II ",
                    'I', "ingotElectrum", 'B', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 2), 'G', "gearElectrum"));
            electrumBody = getAddedRecipe();

            //ELECTRUM HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(ELECTRUM_HANDLE),
                    " I ",
                    "ISI",
                    "II ", 'I', "ingotElectrum", 'S', Material.getMaterial(REDSTONE_STICK)));
            electrumHandle = getAddedRecipe();

            //ELECTRUM BLADE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(ELECTRUM_BLADE),
                    "  I",
                    " B ",
                    "B  ", 'I', "ingotElectrum", 'B', "blockElectrum"));
            electrumBlade = getAddedRecipe();
        }

        /** ENDERIUM RECIPES */
        if(OreDictionary.getOres("ingotEnderium").size()>0){
            //ENDERIUM DRILL BODY
            GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(ENDERIUM_BODY),
                    " G ","IBG","II ",
                    'I', "ingotEnderium", 'B', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 2), 'G', "gearEnderium"));
            enderiumBody = getAddedRecipe();

            //ENDERIUM HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(ENDERIUM_HANDLE),
                    " I ",
                    "ISI",
                    "II ", 'I', "ingotEnderium", 'S', Material.getMaterial(REDSTONE_STICK)));
            enderiumHandle = getAddedRecipe();

            //ENDERIUM BLADE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(ENDERIUM_BLADE),
                    "  I",
                    " B ",
                    "B  ", 'I', "netherStar", 'B', "blockEnderium"));
            enderiumBlade = getAddedRecipe();
        }

        /** ENERGETIC ALLOY RECIPES */
        if(OreDictionary.getOres("ingotEnergeticAlloy").size()>0){
            //ENERGETIC GEAR
            GameRegistry.addRecipe(new ShapedOreRecipe(Material.getMaterial(GEAR_ENERGIZED),
                    " I ","IGI"," I ",
                    'I', "ingotEnergeticAlloy", 'G', "gearIron"));

            //ENERGETIC DRILL BODY
            GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(ENERGETIC_BODY),
                    " G ","IBG","II ",
                    'I', "ingotEnergeticAlloy", 'B', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 2), 'G', "gearEnergeticAlloy"));
            energizedBody = getAddedRecipe();

            //ENERGETIC HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(ENERGETIC_HANDLE),
                    " I ",
                    "ISI",
                    "II ", 'I', "ingotEnergeticAlloy", 'S', Material.getMaterial(REDSTONE_STICK)));
            energizedHandle = getAddedRecipe();

            //ENERGETIC HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(ENERGETIC_BLADE),
                    "  I",
                    " B ",
                    "B  ", 'I', "ingotEnergeticAlloy", 'B', "blockEnergeticAlloy"));
            energizedBlade = getAddedRecipe();
        }

        /** VIBRANT ALLOY RECIPES */
        if(OreDictionary.getOres("ingotVibrantAlloy").size()>0){
            //VIBRANT GEAR
            GameRegistry.addRecipe(new ShapedOreRecipe(Material.getMaterial(Materials.GEAR_VIBRANT),
                    " I ","IGI"," I ",
                    'I', "ingotVibrantAlloy", 'G', "gearEnergeticAlloy"));

            //VIBRANT DRILL BODY
            GameRegistry.addRecipe(new ShapedOreRecipe(DrillBody.getBody(VIBRANT_BODY),
                    " G ","IBG","II ",
                    'I', "ingotVibrantAlloy", 'B', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 2), 'G', "gearVibrantAlloy"));
            vibrantBody = getAddedRecipe();

            //VIBRANT HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordHandle.getHandle(VIBRANT_HANDLE),
                    " I ",
                    "ISI",
                    "II ", 'I', "ingotVibrantAlloy", 'S', Material.getMaterial(REDSTONE_STICK)));
            vibrantHandle = getAddedRecipe();

            //VIBRANT HANDLE
            GameRegistry.addRecipe(new ShapedOreRecipe(SwordBlade.getBlade(VIBRANT_BLADE),
                    "  I",
                    " B ",
                    "B  ", 'I', "netherStar", 'B', "blockVibrantAlloy"));
            vibrantBlade = getAddedRecipe();
        }

        //SPEED DRILL AUGMENT
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillAugment.getAugment(SPEED_AUGMENT),
                "III","IGI","III",
                'I', "ingotIron", 'G', DrillHead.getHead(GOLD_HEAD)));
        speedDrillAugment = getAddedRecipe();

        //SPEED II DRILL AUGMENT
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillAugment.getAugment(SPEED_II_AUGMENT),
                "I I", "BAB", "I I",
                'I', "ingotIron", 'A', DrillAugment.getAugment(SPEED_AUGMENT), 'B', Blocks.IRON_BLOCK));
        speedIIDrillAugment = getAddedRecipe();

        //HOTSWAP DRILL AUGMENT
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillAugment.getAugment(HOTSWAP_AUGMENT),
                "III","DCD","III",
                'C', Blocks.CHEST, 'D', DrillHead.getHead(IRON_HEAD), 'I', "ingotIron"));
        hotswapDrillAugment = getAddedRecipe();

        //HEAVY DRILL AUGMENT
        GameRegistry.addRecipe(new ShapedOreRecipe(DrillAugment.getAugment(HEAVY_AUGMENT),
                "EEE", "BHB", "EEE",
                'E', Items.EMERALD, 'H', DrillHead.getHead(HEAVY_HEAD), 'B', Blocks.IRON_BLOCK));
        heavyDrillAugmnet = getAddedRecipe();

        //MODIFIER
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedBlocks.modifier),
                "WIW",
                "IGI",
                "WIW", 'W', Blocks.LOG, 'I', "ingotIron", 'G', "gearIron"));
        modifier = getAddedRecipe();

        //DOZER CONE
        GameRegistry.addRecipe(new ShapedOreRecipe(RedBlocks.cone, new Object[]{
                " S ",
                "S S",
                "LLL", 'S', "itemConduitBinder", 'L', "slabStone"
        }));
        cone = getAddedRecipe();

        //IRON GEAR
        GameRegistry.addRecipe(new ShapedOreRecipe(Material.getMaterial(GEAR_IRON),
                " I ","IGI"," I ",
                'I', "ingotIron", 'G', "gearStone"));

        //ENERGIZER EMPTY
        GameRegistry.addRecipe(new ShapedOreRecipe(Material.getMaterial(ENERGIZED_CAPSULE_EMPTY),
                "LUL",
                "UHU",
                "LUL", 'L', "ingotConductiveIron", 'U', "ingotLumium", 'H', "blockGlassHardened"));

        GameRegistry.addRecipe(new ShapedOreRecipe(SwordAugment.getAugment(BERSERK_AUGMENT),
                "III",
                "KBK",
                "III", 'I', "ingotIron", 'B', SwordBlade.getBlade(IRON_BLADE), 'K', "blockIron"));
        berserkerSwordAugmnet = getAddedRecipe();

        GameRegistry.addRecipe(new ShapedOreRecipe(SwordAugment.getAugment(BERSERK_II_AUGMENT),
                "IKI",
                "IBI",
                "III", 'I', "ingotIron", 'B', "bladeMidTier", 'K', SwordAugment.getAugment(BERSERK_AUGMENT)));
        berserkerIISwordAugment = getAddedRecipe();

        GameRegistry.addRecipe(new ShapedOreRecipe(SwordAugment.getAugment(BLAZER_AUGMENT),
                "CIC",
                "IBI",
                "CIC", 'I', "ingotIron", 'B', "bladeMidTier", 'C', Blocks.COAL_BLOCK));
        blazerSwordAugment = getAddedRecipe();

        GameRegistry.addRecipe(new ShapedOreRecipe(SwordAugment.getAugment(FORTUITOUS_AUGMENT),
                "CIC",
                "IBI",
                "CIC", 'I', "ingotIron", 'B', "gemLapis", 'C', "blockLapis"));
        fortuitousSwordAugment = getAddedRecipe();

        GameRegistry.addRecipe(new ShapedOreRecipe(Battery.getBattery(Battery.Types.BASIC),
                " R ",
                "SCS",
                "RAR", 'R', Blocks.REDSTONE_BLOCK, 'S', "ingotElectricalSteel", 'C', "ingotConductiveIron", 'A', new ItemStack(GameRegistry.findItem("enderio", "itemBasicCapacitor"), 1, 1)));
        basicBattery = getAddedRecipe();

        GameRegistry.addRecipe(new EnergeticBattery(Battery.getBattery(Battery.Types.ENERGETIC),
                " R ",
                "SCS",
                "RAR", 'R', "blockRedstone", 'S', "ingotEnergeticAlloy", 'C', "blockEnergeticAlloy", 'A', Battery.getBattery(Battery.Types.BASIC)));
        energizedBattery = getAddedRecipe();

        GameRegistry.addRecipe(new GreatBattery(Battery.getBattery(Battery.Types.GREAT),
                " R ",
                "SCS",
                "RAR", 'R', "blockRedstone", 'S', "ingotVibrantium", 'C', "blockVibrantAlloy", 'A', Battery.getBattery(Battery.Types.ENERGETIC)));
        greatBattery = getAddedRecipe();

        if(Loader.isModLoaded("EnderIO")){
            Util.addAlloySmelterRecipe("Energizer Filling", 32000, new ItemStack(Blocks.REDSTONE_BLOCK, 20, 0), Material.getMaterial(ENERGIZED_CAPSULE_EMPTY), new ItemStack(Blocks.REDSTONE_BLOCK, 20, 0), Material.getMaterial(Materials.ENERGIZED_CAPSULE_FULL));
            Util.addAlloySmelterRecipe("Vibrantium", 16000, Util.findItemStack("enderio", "itemAlloy", 8, 2), Util.findItemStack("enderio", "itemMaterial", 2, 8), Util.findItemStack("enderio", "itemAlloy", 32, 7), Material.getMaterial(INGOT_VIBRANTIUM));
            Util.addAlloySmelterRecipe("Glowstone Infused Steel", 8000, Util.findItemStack("enderio", "itemAlloy", 2, 0), new ItemStack(Items.GLOWSTONE_DUST, 8, 0), null, Material.getMaterial(INGOT_GLOWSTEEL));
            Util.addAlloySmelterRecipe("Redstone Stick", 2000, new ItemStack(Items.REDSTONE, 20, 0), new ItemStack(Items.STICK, 1, 0), null, Material.getMaterial(REDSTONE_STICK));
        }

    }

    public static IRecipe getAddedRecipe(){
        return CraftingManager.getInstance().getRecipeList().get(CraftingManager.getInstance().getRecipeList().size()-1);
    }

}
