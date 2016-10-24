package com.raizu.redstonic.Utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class StringUtils {

    public static String progressBar(int cap, int max, int size){
        String progress = "["+ TextFormatting.GREEN;
        int curr = (cap*size)/max;
        for(int i=0; i<curr; i++){
            progress += "|";
        }
        progress+=TextFormatting.DARK_GRAY;
        for(int i=0; i<size-curr; i++){
            progress += "|";
        }
        progress+=TextFormatting.GRAY;
        progress +="]";
        return progress;
    }

    public static String progressBar(int cap, int max, int size, boolean color){
        String progress = "["+ (color ? TextFormatting.GREEN : "");
        int curr = (cap*size)/max;
        for(int i=0; i<curr; i++){
            progress += "|";
        }
        if(color)progress+=TextFormatting.DARK_GRAY;
        for(int i=0; i<size-curr; i++){
            progress += "/";
        }
        if(color)progress+=TextFormatting.GRAY;
        progress +="]";
        return progress;
    }

    public static String localize(String input, Object ... format) {
        return I18n.format(input, format);
    }
}
