package com.raizu.redstonic.Handler;

import com.raizu.redstonic.Redstonic;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class Config {

    static Configuration config;

    //ITEMS
    public static boolean Manual = true;
    //MACHINES
    public static boolean RedstonicModifier = true;
    //BLOCKS
    public static boolean DozerCone = true;
    //DRILL
    public static boolean redstonicDrill = true;
    public static boolean IronHead = true;
    public static boolean GoldHead = true;
    public static boolean DiamondHead = true;
    public static boolean HeavyHead = true;
    public static boolean FortuitousHead = true;
    public static boolean SilkyHead = true;
    public static boolean BlazerHead = true;
    public static boolean EndHead = true;

    public static boolean IronBody = true;
    public static boolean ElectrumBody = true;
    public static boolean EnderiumBody = true;
    public static boolean EnergeticBody = true;
    public static boolean VibrantBody = true;
    public static boolean UltimateBody = true;
    //SWORD
    public static boolean redstonicSword = true;
    public static boolean IronBlade = true;
    public static boolean DiamondBlade = true;
    public static boolean ElectrumBlade = true;
    public static boolean EnderiumBlade = true;
    public static boolean EnergeticBlade = true;
    public static boolean VibrantBlade = true;
    public static boolean IronHandle = true;
    public static boolean WoodHandle = true;
    public static boolean ElectrumHandle = true;
    public static boolean EnderiumHandle = true;
    public static boolean EnergeticHandle = true;
    public static boolean VibrantHandle = true;
    //BATTERY
    public static boolean BasicBattery = true;
    public static boolean EnergizedBattery = true;
    public static boolean GreatBattery = true;
    public static boolean InfiniteBattery = true;
    //AUGMENT
    public static boolean SpeedAugment = true;
    public static boolean EnergyAugment = true;
    public static boolean HotswapAugment = true;
    public static boolean PlaceBlockAugment = true;
    public static boolean MagnetizationAugment = true;
    public static boolean SpeedIIAugment = true;
    public static boolean BerserkAugment = true;
    public static boolean BerserkIIAugment = true;
    public static boolean FortuitousAugment = true;
    public static boolean BlazerAugment = true;
    //BALANCE
    public static int ironDrillSpeed = 10;
    public static int goldDrillSpeed = 25;
    public static int diamondDrillSpeed = 15;
    public static int heavyDrillSpeed = 5;
    public static int fortuitousDrillSpeed = 5;
    public static int silkyDrillSpeed = 8;
    public static int blazerDrillSpeed = 10;

    public static void load(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        reloadConfig();
    }

    private static void reloadConfig() {
        //MACHINES
        RedstonicModifier = config.get("Machines", "Redstonic Modifier", RedstonicModifier).getBoolean(RedstonicModifier);

        //ITEMS
        Manual = config.get("Items", "Redstonic Manual", Manual).getBoolean(Manual);

        //BLOCKS
        Manual = config.get("Blocks", "Dozer Cone", DozerCone).getBoolean(DozerCone);

        //BATTERIES
        BasicBattery = config.get("Batteries", "BasicBattery", BasicBattery).getBoolean(BasicBattery);
        EnergizedBattery = config.get("Batteries", "EnergizedBattery", EnergizedBattery).getBoolean(EnergizedBattery);
        GreatBattery = config.get("Batteries", "GreatBattery", GreatBattery).getBoolean(GreatBattery);
        InfiniteBattery = config.get("Batteries", "InfiniteBattery", InfiniteBattery).getBoolean(InfiniteBattery);

        //DRILL SPEEDS
        ironDrillSpeed = config.get("Drill Base Speeds", "Iron", ironDrillSpeed).getInt(ironDrillSpeed);
        goldDrillSpeed = config.get("Drill Base Speeds", "Gold", goldDrillSpeed).getInt(goldDrillSpeed);
        diamondDrillSpeed = config.get("Drill Base Speeds", "Diamond", diamondDrillSpeed).getInt(diamondDrillSpeed);
        heavyDrillSpeed = config.get("Drill Base Speeds", "Heavy", heavyDrillSpeed).getInt(heavyDrillSpeed);
        fortuitousDrillSpeed = config.get("Drill Base Speeds", "Fortuitous", fortuitousDrillSpeed).getInt(fortuitousDrillSpeed);
        silkyDrillSpeed = config.get("Drill Base Speeds", "Silky", silkyDrillSpeed).getInt(silkyDrillSpeed);
        blazerDrillSpeed = config.get("Drill Base Speeds", "Blazer", blazerDrillSpeed).getInt(blazerDrillSpeed);

        //REDSTONIC DRILL
        redstonicDrill = config.get("RedstonicDrill", "Enable Redstonic Drill", redstonicDrill).getBoolean(redstonicDrill);
        IronHead = config.get("RedstonicDrill", "IronHead", IronHead).getBoolean(IronHead);
        GoldHead = config.get("RedstonicDrill", "GoldHead", GoldHead).getBoolean(GoldHead);
        DiamondHead = config.get("RedstonicDrill", "DiamondHead", DiamondHead).getBoolean(DiamondHead);
        HeavyHead = config.get("RedstonicDrill", "HeavyHead", HeavyHead).getBoolean(HeavyHead);
        FortuitousHead = config.get("RedstonicDrill", "FortuitousHead", FortuitousHead).getBoolean(FortuitousHead);
        BlazerHead = config.get("RedstonicDrill", "BlazerHead", BlazerHead).getBoolean(BlazerHead);
        SilkyHead = config.get("RedstonicDrill", "SilkyHead", SilkyHead).getBoolean(SilkyHead);
        EndHead = config.get("RedstonicDrill", "EndHead", EndHead).getBoolean(EndHead);
        IronBody = config.get("RedstonicDrill", "IronBody", IronBody).getBoolean(IronBody);
        ElectrumBody = config.get("RedstonicDrill", "ElectrumBody", ElectrumBody).getBoolean(ElectrumBody);
        EnderiumBody = config.get("RedstonicDrill", "EnderiumBody", EnderiumBody).getBoolean(EnderiumBody);
        EnergeticBody = config.get("RedstonicDrill", "EnergeticBody", EnergeticBody).getBoolean(EnergeticBody);
        VibrantBody = config.get("RedstonicDrill", "VibrantBody", VibrantBody).getBoolean(VibrantBody);
        UltimateBody = config.get("RedstonicDrill", "UltimateBody", UltimateBody).getBoolean(UltimateBody);

        //REDSTONIC SWORD
        redstonicSword = config.get("RedstonicSword", "Enable Redstonic Sword", redstonicSword).getBoolean(redstonicSword);
        IronHandle = config.get("RedstonicSword", "IronHandle", IronHandle).getBoolean(IronHandle);
        WoodHandle = config.get("RedstonicSword", "WoodHandle", WoodHandle).getBoolean(WoodHandle);
        ElectrumHandle = config.get("RedstonicSword", "ElectrumHandle", ElectrumHandle).getBoolean(ElectrumHandle);
        EnderiumHandle = config.get("RedstonicSword", "EnderiumHandle", EnderiumHandle).getBoolean(EnderiumHandle);
        EnergeticHandle = config.get("RedstonicSword", "EnergeticHandle", EnergeticHandle).getBoolean(EnergeticHandle);
        VibrantHandle = config.get("RedstonicSword", "VibrantHandle", VibrantHandle).getBoolean(VibrantHandle);
        IronBlade = config.get("RedstonicSword", "IronBlade", IronBlade).getBoolean(IronBlade);
        DiamondBlade = config.get("RedstonicSword", "DiamondBlade", DiamondBlade).getBoolean(DiamondBlade);
        ElectrumBlade = config.get("RedstonicSword", "ElectrumBlade", ElectrumBlade).getBoolean(ElectrumBlade);
        EnderiumBlade = config.get("RedstonicSword", "EnderiumBlade", EnderiumBlade).getBoolean(EnderiumBlade);
        EnergeticBlade = config.get("RedstonicSword", "EnergeticBlade", EnergeticBlade).getBoolean(EnergeticBlade);
        VibrantBlade = config.get("RedstonicSword", "VibrantBlade", VibrantBlade).getBoolean(VibrantBlade);

        //AUGMENTS
        SpeedAugment = config.get("Augments", "SpeedAugment", SpeedAugment).getBoolean(SpeedAugment);
        EnergyAugment = config.get("Augments", "EnergyAugment", EnergyAugment).getBoolean(EnergyAugment);
        HotswapAugment = config.get("Augments", "HotswapAugment", HotswapAugment).getBoolean(HotswapAugment);
        PlaceBlockAugment = config.get("Augments", "PlaceBlockAugment", PlaceBlockAugment).getBoolean(PlaceBlockAugment);
        MagnetizationAugment = config.get("Augments", "MagnetizationAugment", MagnetizationAugment).getBoolean(MagnetizationAugment);
        SpeedIIAugment = config.get("Augments", "SpeedIIAugment", SpeedIIAugment).getBoolean(SpeedIIAugment);
        BerserkAugment = config.get("Augments", "BerserkAugment", BerserkAugment).getBoolean(BerserkAugment);
        BerserkIIAugment = config.get("Augments", "BerserkIIAugment", BerserkIIAugment).getBoolean(BerserkIIAugment);
        FortuitousAugment = config.get("Augments", "FortuitousAugment", FortuitousAugment).getBoolean(FortuitousAugment);
        BlazerAugment = config.get("Augments", "BlazerAugment", BlazerAugment).getBoolean(BlazerAugment);
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.getModID().equals(Redstonic.MODID)){
            reloadConfig();
        }
    }

}
