package com.raizunne.redstonic;

import com.raizunne.redstonic.Handler.ConfigHandler;
import com.raizunne.redstonic.Item.*;
import com.raizunne.redstonic.Item.Armor.RedstonicBoots;
import com.raizunne.redstonic.Item.Armor.RedstonicChestplate;
import com.raizunne.redstonic.Item.Armor.RedstonicHelmet;
import com.raizunne.redstonic.Item.Armor.RedstonicLeggings;
import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.Sword.SwordAugment;
import com.raizunne.redstonic.Item.Sword.SwordBlade;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
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

    public static Item IronBlade = new SwordBlade(0);
    public static Item DiamondBlade = new SwordBlade(1);
    public static Item ElectrumBlade = new SwordBlade(2);
    public static Item EnderiumBlade = new SwordBlade(3);
    public static Item EnergizedBlade = new SwordBlade(4);
    public static Item VibrantBlade = new SwordBlade(5);

    public static Item WoodHandle = new SwordHandle(0);
    public static Item IronHandle = new SwordHandle(1);
    public static Item ElectrumHandle = new SwordHandle(2);
    public static Item EnderiumHandle = new SwordHandle(3);
    public static Item EnergizedHandle = new SwordHandle(4);
    public static Item VibrantHandle = new SwordHandle(5);

    public static Item SpeedAugment = new DrillAugment(0);
    public static Item EnergyAugment = new DrillAugment(1);
    public static Item HotswapAugment = new DrillAugment(2);
    public static Item BlockAugment = new DrillAugment(3);
    public static Item MagnetAugment = new DrillAugment(4);
    public static Item SpeedIIAugment = new DrillAugment(5);

    public static Item BlazerSwordAugment = new SwordAugment(1);
    public static Item FortuitousSwordAugment = new SwordAugment(2);
    public static Item BerserkSwordAugment = new SwordAugment(3);
    public static Item BerserkIISwordAugment = new SwordAugment(4);

    public static Item RedDrill = new RedstonicDrill();
    public static Item RedSword = new RedstonicSword();
    public static Item ManualBook = new Manual();
    public static Item RedContainer = new RedstonicContainer();

    public static Item Energizer = new ItemMaterial(0);
    public static Item EnergizerFull = new ItemMaterial(1);
    public static Item ingotVibrantium = new ItemMaterial(2);
    public static Item gearIron = new ItemMaterial(3);
    public static Item gearEnergized = new ItemMaterial(4);
    public static Item gearVibrant = new ItemMaterial(5);
    public static Item ingotGlowSteel = new ItemMaterial(6);
    public static Item capacitor = new ItemMaterial(7);
    public static Item redstoneStick = new ItemMaterial(8);

    public static Item basicBattery = new ItemBattery(0);
    public static Item energizedBattery = new ItemBattery(1);
    public static Item greatBattery = new ItemBattery(2);
    public static Item infiniteBattery = new ItemBattery(3);

    public static Item RedHelmet = new RedstonicHelmet();
    public static Item RedChestplate = new RedstonicChestplate();
    public static Item RedLeggings = new RedstonicLeggings();
    public static Item RedBoots = new RedstonicBoots();

    public static Item RedCrate = new RedstonicCrate();
    public static Item RedMessanger = new RedstonicMessanger();

    public static void init() {
        //ITEMS
        GameRegistry.registerItem(ManualBook, ManualBook.getUnlocalizedName());
        if(ConfigHandler.redstonicDrill)GameRegistry.registerItem(RedDrill, RedDrill.getUnlocalizedName());
        if(ConfigHandler.redstonicSword)GameRegistry.registerItem(RedSword, RedSword.getUnlocalizedName());
//        if(ConfigHandler.redstonicContainer)GameRegistry.registerItem(RedContainer, RedContainer.getUnlocalizedName());

        GameRegistry.registerItem(RedCrate, RedCrate.getUnlocalizedName());
        GameRegistry.registerItem(RedMessanger, RedMessanger.getUnlocalizedName());

        //ARMOR
//        GameRegistry.registerItem(RedHelmet, RedHelmet.getUnlocalizedName());
//        GameRegistry.registerItem(RedChestplate, RedChestplate.getUnlocalizedName());
//        GameRegistry.registerItem(RedLeggings, RedLeggings.getUnlocalizedName());
//        GameRegistry.registerItem(RedBoots, RedBoots.getUnlocalizedName());

        //DRILL HEADS
        if(ConfigHandler.redstonicDrill) {
            GameRegistry.registerItem(IronHead, IronHead.getUnlocalizedName());
            GameRegistry.registerItem(GoldHead, GoldHead.getUnlocalizedName());
            GameRegistry.registerItem(DiamondHead, DiamondHead.getUnlocalizedName());
            GameRegistry.registerItem(HeavyHead, HeavyHead.getUnlocalizedName());
            GameRegistry.registerItem(FortuitousHead, FortuitousHead.getUnlocalizedName());
            GameRegistry.registerItem(SilkyHead, SilkyHead.getUnlocalizedName());
            GameRegistry.registerItem(BlazerHead, BlazerHead.getUnlocalizedName());
            GameRegistry.registerItem(EndHead, EndHead.getUnlocalizedName());
        }

        //DRILL BODIES
        if(ConfigHandler.redstonicDrill) {
            GameRegistry.registerItem(IronBody, IronBody.getUnlocalizedName());
            GameRegistry.registerItem(EnderiumBody, EnderiumBody.getUnlocalizedName());
            GameRegistry.registerItem(ElectrumBody, ElectrumBody.getUnlocalizedName());
            GameRegistry.registerItem(UltimateBody, UltimateBody.getUnlocalizedName());
            GameRegistry.registerItem(EnergeticBody, EnergeticBody.getUnlocalizedName());
            GameRegistry.registerItem(VibrantBody, VibrantBody.getUnlocalizedName());
        }

        //SWORD BLADES
        if(ConfigHandler.redstonicSword) {
            GameRegistry.registerItem(IronBlade, IronBlade.getUnlocalizedName());
            GameRegistry.registerItem(DiamondBlade, DiamondBlade.getUnlocalizedName());
            GameRegistry.registerItem(ElectrumBlade, ElectrumBlade.getUnlocalizedName());
            GameRegistry.registerItem(EnderiumBlade, EnderiumBlade.getUnlocalizedName());
            GameRegistry.registerItem(EnergizedBlade, EnergizedBlade.getUnlocalizedName());
            GameRegistry.registerItem(VibrantBlade, VibrantBlade.getUnlocalizedName());
        }

        //SWORD HANDLES
        if(ConfigHandler.redstonicSword) {
            GameRegistry.registerItem(IronHandle, IronHandle.getUnlocalizedName());
            GameRegistry.registerItem(WoodHandle, WoodHandle.getUnlocalizedName());
            GameRegistry.registerItem(ElectrumHandle, ElectrumHandle.getUnlocalizedName());
            GameRegistry.registerItem(EnderiumHandle, EnderiumHandle.getUnlocalizedName());
            GameRegistry.registerItem(EnergizedHandle, EnergizedHandle.getUnlocalizedName());
            GameRegistry.registerItem(VibrantHandle, VibrantHandle.getUnlocalizedName());
        }

        //SWORD AUGMENTS
        if(ConfigHandler.redstonicSword) {
            GameRegistry.registerItem(BlazerSwordAugment, BlazerSwordAugment.getUnlocalizedName());
            GameRegistry.registerItem(FortuitousSwordAugment, FortuitousSwordAugment.getUnlocalizedName());
            GameRegistry.registerItem(BerserkSwordAugment, BerserkSwordAugment.getUnlocalizedName());
            GameRegistry.registerItem(BerserkIISwordAugment, BerserkIISwordAugment.getUnlocalizedName());
        }

        //ORE DICTIONARY
        if(ConfigHandler.redstonicDrill) {
            OreDictionary.registerOre("redstonicMidTierBody", ElectrumBody);
            OreDictionary.registerOre("redstonicMidTierBody", EnergeticBody);
            OreDictionary.registerOre("redstonicHighTierBody", EnderiumBody);
            OreDictionary.registerOre("redstonicHighTierBody", VibrantBody);
        }

        if(ConfigHandler.redstonicSword) {
            OreDictionary.registerOre("redstonicMidTierBlade", ElectrumBlade);
            OreDictionary.registerOre("redstonicMidTierBlade", EnergizedBlade);
            OreDictionary.registerOre("redstonicHighTierBlade", EnderiumBlade);
            OreDictionary.registerOre("redstonicHighTierBlade", VibrantBlade);
        }

        if(ConfigHandler.redstonicSword) {
            OreDictionary.registerOre("redstonicMidTierHandle", ElectrumHandle);
            OreDictionary.registerOre("redstonicMidTierHandle", EnergizedHandle);
            OreDictionary.registerOre("redstonicHighTierHandle", EnderiumHandle);
            OreDictionary.registerOre("redstonicHighTierHandle", VibrantHandle);
        }

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

        //DRILL AUGMENTS
        if(ConfigHandler.redstonicDrill) {
            GameRegistry.registerItem(SpeedAugment, SpeedAugment.getUnlocalizedName());
            GameRegistry.registerItem(EnergyAugment, EnergyAugment.getUnlocalizedName());
            GameRegistry.registerItem(HotswapAugment, HotswapAugment.getUnlocalizedName());
            GameRegistry.registerItem(BlockAugment, BlockAugment.getUnlocalizedName());
            GameRegistry.registerItem(MagnetAugment, MagnetAugment.getUnlocalizedName());
            GameRegistry.registerItem(SpeedIIAugment, SpeedIIAugment.getUnlocalizedName());
        }

        //MATERIAL
        GameRegistry.registerItem(Energizer, Energizer.getUnlocalizedName());
        GameRegistry.registerItem(EnergizerFull, EnergizerFull.getUnlocalizedName());
        GameRegistry.registerItem(capacitor, capacitor.getUnlocalizedName());
        GameRegistry.registerItem(redstoneStick, redstoneStick.getUnlocalizedName());

        //BATTERY
        GameRegistry.registerItem(basicBattery, basicBattery.getUnlocalizedName());
        GameRegistry.registerItem(energizedBattery, energizedBattery.getUnlocalizedName());
        GameRegistry.registerItem(greatBattery, greatBattery.getUnlocalizedName());
        GameRegistry.registerItem(infiniteBattery, infiniteBattery.getUnlocalizedName());

    }

}
