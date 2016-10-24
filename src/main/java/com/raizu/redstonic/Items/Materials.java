package com.raizu.redstonic.Items;

/**
 * Created by Raizu on 22/10/2016.
 * as a part of Redstonic
 **/
public enum Materials {

    INGOT_VIBRANTIUM("IngotVibrantium", "ingotVibrantium", true),
    GEAR_IRON("GearIron", "gearIron"),
    GEAR_ENERGIZED("GearEnergized", "gearEnergeticAlloy"),
    GEAR_VIBRANT("GearVibrant", "gearVibrantAlloy"),
    GEAR_ELECTRUM("GearElectrum", "gearElectrum"),
    INGOT_GLOWSTEEL("IngotGlowsteel", "ingotLumium"),
    REDSTONE_STICK("RedstoneStick"),
    ENERGIZED_CAPSULE_FULL("EnergizedCapsuleFull"),
    ENERGIZED_CAPSULE_EMPTY("EnergizedCapsuleEmpty")

    ;

    String name;
    String oreDic;
    boolean ore;
    boolean glow;

    Materials(String name){
        this.name=name;
        ore = false;
    }

    Materials(String name, String oreDic){
        this.name=name;
        this.oreDic=oreDic;
        ore=true;
    }

    Materials(String name, String oreDic, boolean glow){
        this.name=name;
        this.oreDic=oreDic;
        this.glow = glow;
        ore=true;
    }
}