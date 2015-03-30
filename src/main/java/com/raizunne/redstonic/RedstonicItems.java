package com.raizunne.redstonic;

import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Item.ItemMaterial;
import com.raizunne.redstonic.Item.Manual;
import com.raizunne.redstonic.Item.RedstonicDrill;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:20 PM.
 */
public class RedstonicItems {

    public static Item IronHead = new DrillHead(1);
    public static Item GoldHead = new DrillHead(2);
    public static Item DiamondHead = new DrillHead(3);
    public static Item HeavyHead = new DrillHead(4);
    public static Item FortuitousHead = new DrillHead(5);
    public static Item SilkyHead = new DrillHead(6);
    public static Item BlazerHead = new DrillHead(7);
    public static Item EndHead = new DrillHead(8);

    public static Item IronBody = new DrillBody(1);
    public static Item ElectrumBody = new DrillBody(2);
    public static Item EnderiumBody = new DrillBody(3);
    public static Item UltimateBody = new DrillBody(4);
    public static Item EnergeticBody = new DrillBody(5);
    public static Item VibrantBody = new DrillBody(6);

    public static Item SpeedAugment = new DrillAugment(0);
    public static Item EnergyAugment = new DrillAugment(1);
    public static Item HotswapAugment = new DrillAugment(2);
    public static Item BlockAugment = new DrillAugment(3);

    public static Item RedDrill = new RedstonicDrill();
    public static Item ManualBook = new Manual();

    public static Item Energizer = new ItemMaterial(0);
    public static Item EnergizerFull = new ItemMaterial(1);
    public static Item ingotVibrantium = new ItemMaterial(2);
    public static Item gearIron = new ItemMaterial(3);
    public static Item gearEnergized = new ItemMaterial(4);
    public static Item gearVibrant = new ItemMaterial(5);
    public static Item ingotGlowSteel = new ItemMaterial(6);
    public static Item capacitor = new ItemMaterial(7);

    public static Item basicBattery = new ItemBattery(0);
    public static Item energizedBattery = new ItemBattery(1);
    public static Item greatBattery = new ItemBattery(2);
    public static Item infiniteBattery = new ItemBattery(3);

    public static void init() {
        GameRegistry.registerItem(IronHead, IronHead.getUnlocalizedName());
        GameRegistry.registerItem(GoldHead, GoldHead.getUnlocalizedName());
        GameRegistry.registerItem(DiamondHead, DiamondHead.getUnlocalizedName());
        GameRegistry.registerItem(HeavyHead, HeavyHead.getUnlocalizedName());
        GameRegistry.registerItem(FortuitousHead, FortuitousHead.getUnlocalizedName());
        GameRegistry.registerItem(SilkyHead, SilkyHead.getUnlocalizedName());
        GameRegistry.registerItem(BlazerHead, BlazerHead.getUnlocalizedName());
        GameRegistry.registerItem(EndHead, EndHead.getUnlocalizedName());

        GameRegistry.registerItem(IronBody, IronBody.getUnlocalizedName());
        GameRegistry.registerItem(EnderiumBody, EnderiumBody.getUnlocalizedName());
        GameRegistry.registerItem(ElectrumBody, ElectrumBody.getUnlocalizedName());
        GameRegistry.registerItem(UltimateBody, UltimateBody.getUnlocalizedName());
        GameRegistry.registerItem(EnergeticBody, EnergeticBody.getUnlocalizedName());
        GameRegistry.registerItem(VibrantBody, VibrantBody.getUnlocalizedName());

        if(Loader.isModLoaded("ThermalFoundation")) {
        }

        if (Loader.isModLoaded("EnderIO")) {
            GameRegistry.registerItem(ingotVibrantium, ingotVibrantium.getUnlocalizedName());
            GameRegistry.registerItem(gearIron, gearIron.getUnlocalizedName());
            GameRegistry.registerItem(gearEnergized, gearEnergized.getUnlocalizedName());
            GameRegistry.registerItem(gearVibrant, gearVibrant.getUnlocalizedName());
            GameRegistry.registerItem(ingotGlowSteel, ingotGlowSteel.getUnlocalizedName());
            OreDictionary.registerOre("gearIron", gearIron);
            OreDictionary.registerOre("gearEnergized", gearEnergized);
            OreDictionary.registerOre("gearVibrant", gearVibrant);
            OreDictionary.registerOre("ingotVibrantium", ingotVibrantium);
            OreDictionary.registerOre("ingotLumium", ingotGlowSteel);
        }

        GameRegistry.registerItem(SpeedAugment, SpeedAugment.getUnlocalizedName());
        GameRegistry.registerItem(EnergyAugment, EnergyAugment.getUnlocalizedName());
        GameRegistry.registerItem(HotswapAugment, HotswapAugment.getUnlocalizedName());
        GameRegistry.registerItem(BlockAugment, BlockAugment.getUnlocalizedName());

        GameRegistry.registerItem(RedDrill, RedDrill.getUnlocalizedName());
        GameRegistry.registerItem(ManualBook, ManualBook.getUnlocalizedName());

        GameRegistry.registerItem(Energizer, Energizer.getUnlocalizedName());
        GameRegistry.registerItem(EnergizerFull, EnergizerFull.getUnlocalizedName());
        GameRegistry.registerItem(capacitor, capacitor.getUnlocalizedName());

        GameRegistry.registerItem(basicBattery, basicBattery.getUnlocalizedName());
        GameRegistry.registerItem(energizedBattery, energizedBattery.getUnlocalizedName());
        GameRegistry.registerItem(greatBattery, greatBattery.getUnlocalizedName());
        GameRegistry.registerItem(infiniteBattery, infiniteBattery.getUnlocalizedName());
    }

}
