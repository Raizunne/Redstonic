package com.raizu.redstonic.Items.Drill;

import net.minecraft.item.ItemStack;

/**
 * Created by Raizu on 22/10/2016.
 * as a part of Redstonic
 **/
public enum DrillPart {

    IRON_HEAD(0),
    GOLD_HEAD(1),
    DIAMOND_HEAD(2),
    HEAVY_HEAD(3),
    FORTUITOUS_HEAD(4),
    SILKY_HEAD(5),
    BLAZER_HEAD(6),
    ULTIMATE_HEAD(7),

    IRON_BODY(0),
    ELECTRUM_BODY(1),
    ENDERIUM_BODY(2),
    ENERGETIC_BODY(3),
    VIBRANT_BODY(4),
    END_BODY(5),

    ENERGY_AUGMENT(0),
    SPEED_AUGMENT(1),
    SPEED_II_AUGMENT(2),
    HOTSWAP_AUGMENT(3),
    HEAVY_AUGMENT(4),

    ;


    int part;
    DrillPart(int part){
        this.part = part;
    }
}
