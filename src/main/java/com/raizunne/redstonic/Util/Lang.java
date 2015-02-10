package com.raizunne.redstonic.Util;

import net.minecraft.util.StatCollector;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:37 PM.
 */
public class Lang {

    public static String translate(String string){
        return StatCollector.translateToLocal(string);
    }
}
