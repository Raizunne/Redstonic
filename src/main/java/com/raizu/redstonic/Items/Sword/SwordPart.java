package com.raizu.redstonic.Items.Sword;

/**
 * Created by Raizu on 22/10/2016.
 * as a part of Redstonic
 **/
public enum SwordPart {

    IRON_BLADE(0),
    DIAMOND_BLADE(1),
    ELECTRUM_BLADE(2),
    ENDERIUM_BLADE(3),
    ENERGETIC_BLADE(4),
    VIBRANT_BLADE(5),

    WOOD_HANDLE(0),
    IRON_HANDLE(1),
    ELECTRUM_HANDLE(2),
    ENDERIUM_HANDLE(3),
    ENERGETIC_HANDLE(4),
    VIBRANT_HANDLE(5),

    BERSERK_AUGMENT(0),
    BERSERK_II_AUGMENT(1),
    BLAZER_AUGMENT(2),
    FORTUITOUS_AUGMENT(3),

    ;

    int part;
    SwordPart(int part){
        this.part = part;
    }

}
