package com.raizu.redstonic.Handler;

import com.raizu.redstonic.Items.Materials;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Redstonic;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

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
    public static boolean redstonicSword = true;

    //BALANCE
    public static int ironDrillSpeed = 10;
    public static int goldDrillSpeed = 25;
    public static int diamondDrillSpeed = 15;
    public static int heavyDrillSpeed = 5;
    public static int fortuitousDrillSpeed = 5;
    public static int silkyDrillSpeed = 8;
    public static int blazerDrillSpeed = 10;

    public static int[] swordDamages = {3, 4, 5, 7, 5, 7};
    public static boolean noticeUpdate = true;

    public static List<Integer> disabledHeads;
    public static List<Integer> disabledBodies;
    public static List<Integer> disabledBatteries;
    public static List<Integer> disabledBlades;
    public static List<Integer> disabledHandles;
    public static List<Integer> disabledDrillAugments;
    public static List<Integer> disabledSwordAugments;
    public static List<Integer> disabledMaterials;

    public static void load(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        reloadConfig();
    }

    private static void reloadConfig() {
        disabledHeads = new ArrayList<Integer>();
        disabledBodies = new ArrayList<Integer>();
        disabledBatteries = new ArrayList<Integer>();
        disabledBlades = new ArrayList<Integer>();
        disabledHandles= new ArrayList<Integer>();
        disabledDrillAugments = new ArrayList<Integer>();
        disabledSwordAugments = new ArrayList<Integer>();
        disabledMaterials = new ArrayList<Integer>();
        //MACHINES
        RedstonicModifier = config.get("Machines", "Redstonic Modifier", RedstonicModifier).getBoolean(RedstonicModifier);

        //ITEMS
        Manual = config.get("Items", "Redstonic Manual", Manual).getBoolean(Manual);

        //BLOCKS
        Manual = config.get("Blocks", "Dozer Cone", DozerCone).getBoolean(DozerCone);

        //DRILL SPEEDS
        ironDrillSpeed = config.get("Drill Base Speeds", "Iron", ironDrillSpeed).getInt(ironDrillSpeed);
        goldDrillSpeed = config.get("Drill Base Speeds", "Gold", goldDrillSpeed).getInt(goldDrillSpeed);
        diamondDrillSpeed = config.get("Drill Base Speeds", "Diamond", diamondDrillSpeed).getInt(diamondDrillSpeed);
        heavyDrillSpeed = config.get("Drill Base Speeds", "Heavy", heavyDrillSpeed).getInt(heavyDrillSpeed);
        fortuitousDrillSpeed = config.get("Drill Base Speeds", "Fortuitous", fortuitousDrillSpeed).getInt(fortuitousDrillSpeed);
        silkyDrillSpeed = config.get("Drill Base Speeds", "Silky", silkyDrillSpeed).getInt(silkyDrillSpeed);
        blazerDrillSpeed = config.get("Drill Base Speeds", "Blazer", blazerDrillSpeed).getInt(blazerDrillSpeed);

        redstonicDrill = config.get("RedstonicDrill", "Enable Redstonic Drill", redstonicDrill).getBoolean(redstonicDrill);
        redstonicSword = config.get("RedstonicSword", "Enable Redstonic Sword", redstonicSword).getBoolean(redstonicSword);

        noticeUpdate = config.get("Config", "Notify new update in chat", noticeUpdate).getBoolean(noticeUpdate);

        for (int i = 0; i < RedItems.drillHead.heads.length; i++) {
            if(config.get("RedstonicDrill", RedItems.drillHead.heads[i]+"Head", true).getBoolean(true))disabledHeads.add(i);
        }
        for (int i = 0; i < RedItems.drillBody.bodies.length; i++) {
            if(config.get("RedstonicDrill", RedItems.drillBody.bodies[i]+"Body", true).getBoolean(true))disabledBodies.add(i);
        }
        for (int i = 0; i < RedItems.drillAugment.augments.length; i++) {
            if(config.get("Augments", RedItems.drillAugment.augments[i]+"Augment", true).getBoolean(true))disabledDrillAugments.add(i);
        }

        for (int i = 0; i < RedItems.swordBlade.blades.length; i++) {
            if(config.get("RedstonicSword", RedItems.swordBlade.blades[i]+"Blade", true).getBoolean(true))disabledBlades.add(i);
            swordDamages[i] = config.get("Sword Blades Damages", RedItems.swordBlade.blades[i]+"Blade", swordDamages[i]).getInt(swordDamages[i]);
        }
        for (int i = 0; i < RedItems.swordHandle.handles.length; i++) {
            if(config.get("RedstonicSword", RedItems.swordHandle.handles[i]+"Handle", true).getBoolean(true))disabledHandles.add(i);
        }
        for (int i = 0; i < RedItems.swordAugment.augments.length; i++) {
            if(config.get("Augments", RedItems.swordAugment.augments[i]+"Augment", true).getBoolean(true))disabledSwordAugments.add(i);
        }

        for (int i = 0; i < RedItems.battery.batteries.length; i++) {
            if(config.get("Batteries", RedItems.battery.batteries[i]+"Battery", true).getBoolean(true))disabledBatteries.add(i);
        }
        for (int i = 0; i < Materials.values().length; i++) {
            if(config.get("Materials", Materials.values()[i].name(), true).getBoolean(true))disabledMaterials.add(i);
        }

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
