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
import static com.raizunne.redstonic.Handler.ConfigHandler.*;
import static com.raizunne.redstonic.Handler.ConfigHandler.GoldHead;

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
        if(ConfigHandler.Manual)GameRegistry.registerItem(ManualBook, ManualBook.getUnlocalizedName());
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
            if(ConfigHandler.IronHead)GameRegistry.registerItem(IronHead, IronHead.getUnlocalizedName());
            if(ConfigHandler.GoldHead)GameRegistry.registerItem(GoldHead, GoldHead.getUnlocalizedName());
            if(ConfigHandler.DiamondHead)GameRegistry.registerItem(DiamondHead, DiamondHead.getUnlocalizedName());
            if(ConfigHandler.HeavyHead)GameRegistry.registerItem(HeavyHead, HeavyHead.getUnlocalizedName());
            if(ConfigHandler.FortuitousHead)GameRegistry.registerItem(FortuitousHead, FortuitousHead.getUnlocalizedName());
            if(ConfigHandler.SilkyHead)GameRegistry.registerItem(SilkyHead, SilkyHead.getUnlocalizedName());
            if(ConfigHandler.BlazerHead)GameRegistry.registerItem(BlazerHead, BlazerHead.getUnlocalizedName());
            if(ConfigHandler.EndHead)GameRegistry.registerItem(EndHead, EndHead.getUnlocalizedName());
        }

        //DRILL BODIES
        if(ConfigHandler.redstonicDrill) {
            if(ConfigHandler.IronBody)GameRegistry.registerItem(IronBody, IronBody.getUnlocalizedName());
            if(ConfigHandler.EnderiumBody)GameRegistry.registerItem(EnderiumBody, EnderiumBody.getUnlocalizedName());
            if(ConfigHandler.ElectrumBody)GameRegistry.registerItem(ElectrumBody, ElectrumBody.getUnlocalizedName());
            if(ConfigHandler.UltimateBody)GameRegistry.registerItem(UltimateBody, UltimateBody.getUnlocalizedName());
            if(ConfigHandler.EnergeticBody)GameRegistry.registerItem(EnergeticBody, EnergeticBody.getUnlocalizedName());
            if(ConfigHandler.VibrantBody)GameRegistry.registerItem(VibrantBody, VibrantBody.getUnlocalizedName());
        }

        //SWORD BLADES
        if(ConfigHandler.redstonicSword) {
            if(ConfigHandler.IronBlade)GameRegistry.registerItem(IronBlade, IronBlade.getUnlocalizedName());
            if(ConfigHandler.DiamondBlade)GameRegistry.registerItem(DiamondBlade, DiamondBlade.getUnlocalizedName());
            if(ConfigHandler.ElectrumBlade)GameRegistry.registerItem(ElectrumBlade, ElectrumBlade.getUnlocalizedName());
            if(ConfigHandler.EnderiumBlade)GameRegistry.registerItem(EnderiumBlade, EnderiumBlade.getUnlocalizedName());
            if(ConfigHandler.EnergeticBlade)GameRegistry.registerItem(EnergizedBlade, EnergizedBlade.getUnlocalizedName());
            if(ConfigHandler.VibrantBlade)GameRegistry.registerItem(VibrantBlade, VibrantBlade.getUnlocalizedName());
        }

        //SWORD HANDLES
        if(ConfigHandler.redstonicSword) {
            if(ConfigHandler.IronHandle)GameRegistry.registerItem(IronHandle, IronHandle.getUnlocalizedName());
            if(ConfigHandler.WoodHandle)GameRegistry.registerItem(WoodHandle, WoodHandle.getUnlocalizedName());
            if(ConfigHandler.ElectrumHandle)GameRegistry.registerItem(ElectrumHandle, ElectrumHandle.getUnlocalizedName());
            if(ConfigHandler.EnderiumHandle)GameRegistry.registerItem(EnderiumHandle, EnderiumHandle.getUnlocalizedName());
            if(ConfigHandler.EnergeticHandle)GameRegistry.registerItem(EnergizedHandle, EnergizedHandle.getUnlocalizedName());
            if(ConfigHandler.VibrantHandle)GameRegistry.registerItem(VibrantHandle, VibrantHandle.getUnlocalizedName());
        }

        //SWORD AUGMENTS
        if(ConfigHandler.redstonicSword) {
            if(ConfigHandler.BlazerAugment)GameRegistry.registerItem(BlazerSwordAugment, BlazerSwordAugment.getUnlocalizedName());
            if(ConfigHandler.FortuitousAugment)GameRegistry.registerItem(FortuitousSwordAugment, FortuitousSwordAugment.getUnlocalizedName());
            if(ConfigHandler.BerserkAugment)GameRegistry.registerItem(BerserkSwordAugment, BerserkSwordAugment.getUnlocalizedName());
            if(ConfigHandler.BerserkIIAugment)GameRegistry.registerItem(BerserkIISwordAugment, BerserkIISwordAugment.getUnlocalizedName());
        }

        //ORE DICTIONARY
        if(ConfigHandler.redstonicDrill) {
            if(ConfigHandler.ElectrumBody)OreDictionary.registerOre("redstonicMidTierBody", ElectrumBody);
            if(ConfigHandler.EnergeticBody)OreDictionary.registerOre("redstonicMidTierBody", EnergeticBody);
            if(ConfigHandler.EnderiumBody)OreDictionary.registerOre("redstonicHighTierBody", EnderiumBody);
            if(ConfigHandler.VibrantBody)OreDictionary.registerOre("redstonicHighTierBody", VibrantBody);
        }

        if(ConfigHandler.redstonicSword) {
            if(ConfigHandler.ElectrumBlade)OreDictionary.registerOre("redstonicMidTierBlade", ElectrumBlade);
            if(ConfigHandler.EnergeticBlade)OreDictionary.registerOre("redstonicMidTierBlade", EnergizedBlade);
            if(ConfigHandler.EnderiumBlade)OreDictionary.registerOre("redstonicHighTierBlade", EnderiumBlade);
            if(ConfigHandler.VibrantBlade)OreDictionary.registerOre("redstonicHighTierBlade", VibrantBlade);
        }

        if(ConfigHandler.redstonicSword) {
            if(ConfigHandler.ElectrumHandle)OreDictionary.registerOre("redstonicMidTierHandle", ElectrumHandle);
            if(ConfigHandler.EnergeticHandle)OreDictionary.registerOre("redstonicMidTierHandle", EnergizedHandle);
            if(ConfigHandler.EnderiumHandle)OreDictionary.registerOre("redstonicHighTierHandle", EnderiumHandle);
            if(ConfigHandler.VibrantHandle)OreDictionary.registerOre("redstonicHighTierHandle", VibrantHandle);
        }

        if(Loader.isModLoaded("ThermalFoundation")) {
        }

        if (Loader.isModLoaded("EnderIO")) {
            if(ConfigHandler.Vibrantium){
                GameRegistry.registerItem(ingotVibrantium, ingotVibrantium.getUnlocalizedName());
                OreDictionary.registerOre("ingotVibrantium", ingotVibrantium);
            }
            if(ConfigHandler.IronGear){
                GameRegistry.registerItem(gearIron, gearIron.getUnlocalizedName());
                OreDictionary.registerOre("gearIron", gearIron);
            }
            if(ConfigHandler.EnergeticGear){
                GameRegistry.registerItem(gearEnergized, gearEnergized.getUnlocalizedName());
                OreDictionary.registerOre("gearEnergized", gearEnergized);
            }
            if(ConfigHandler.VibrantGear){
                GameRegistry.registerItem(gearVibrant, gearVibrant.getUnlocalizedName());
                OreDictionary.registerOre("gearVibrant", gearVibrant);
            }
            if(ConfigHandler.GlowstoneSteel){
                GameRegistry.registerItem(ingotGlowSteel, ingotGlowSteel.getUnlocalizedName());
                OreDictionary.registerOre("ingotLumium", ingotGlowSteel);
            }
        }

        //DRILL AUGMENTS
        if(ConfigHandler.redstonicDrill) {
            if(ConfigHandler.SpeedAugment)GameRegistry.registerItem(SpeedAugment, SpeedAugment.getUnlocalizedName());
            if(ConfigHandler.EnergyAugment)GameRegistry.registerItem(EnergyAugment, EnergyAugment.getUnlocalizedName());
            if(ConfigHandler.HotswapAugment)GameRegistry.registerItem(HotswapAugment, HotswapAugment.getUnlocalizedName());
            if(ConfigHandler.PlaceBlockAugment)GameRegistry.registerItem(BlockAugment, BlockAugment.getUnlocalizedName());
            if(ConfigHandler.MagnetizationAugment)GameRegistry.registerItem(MagnetAugment, MagnetAugment.getUnlocalizedName());
            if(ConfigHandler.SpeedIIAugment)GameRegistry.registerItem(SpeedIIAugment, SpeedIIAugment.getUnlocalizedName());
        }

        //MATERIAL
        if(ConfigHandler.Energizer)GameRegistry.registerItem(Energizer, Energizer.getUnlocalizedName());
        if(ConfigHandler.Energizer)GameRegistry.registerItem(EnergizerFull, EnergizerFull.getUnlocalizedName());
        if(ConfigHandler.Capacitor)GameRegistry.registerItem(capacitor, capacitor.getUnlocalizedName());
        if(ConfigHandler.RedstoneInfusedStick)GameRegistry.registerItem(redstoneStick, redstoneStick.getUnlocalizedName());

        //BATTERY
        if(ConfigHandler.BasicBattery)GameRegistry.registerItem(basicBattery, basicBattery.getUnlocalizedName());
        if(ConfigHandler.EnergizedBattery)GameRegistry.registerItem(energizedBattery, energizedBattery.getUnlocalizedName());
        if(ConfigHandler.GreatBattery)GameRegistry.registerItem(greatBattery, greatBattery.getUnlocalizedName());
        if(ConfigHandler.InfiniteBattery)GameRegistry.registerItem(infiniteBattery, infiniteBattery.getUnlocalizedName());

    }

}
