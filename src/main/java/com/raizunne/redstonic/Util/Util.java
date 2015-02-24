package com.raizunne.redstonic.Util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by Raizunne as a part of Redstonic
 * on 23/02/2015, 03:18 PM.
 */
public class Util {

   public static final String ItemShiftInfo = Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + " Shift " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo");

    public static String[] getMaterialInfo(int i){
        switch(i){
            case 0: return new String[]{"Crafting Material", "Requires " + EnumChatFormatting.RED + "64000" + EnumChatFormatting.GRAY + " Destabilized Redstone to fill."};
            case 1: return new String[]{"Crafting Material", "Contains " + EnumChatFormatting.RED + "64000" + EnumChatFormatting.GRAY + " Destabilized Redstone."};
            default: return null;
        }
    }

    public static String[] getHeadInfo(int i){
        switch(i){
            case 1: return new String[]{"Medium Mining Speed"};
            case 2: return new String[]{"Very Fast Mining Speed"};
            case 3: return new String[]{"Fast Mining Speed"};
            case 4: return new String[]{"3x3 Mining"};
            case 5: return new String[]{"Fortune IV Mining"};
            case 6: return new String[]{"Silk Touch Mining"};
            case 7: return new String[]{"Auto-Smelt Mining"};
            case 8: return new String[]{"Instant Mining Speed", "Requires Ultimate Drill Head"};
            default: return null;
        }
    }

    public static String[] getBodyInfo(int i){
        switch(i){
            case 1: return new String[]{"1 Augment Slot"};
            case 2: return new String[]{"2 Augment Slot"};
            case 3: return new String[]{"3 Augment Slot"};
            case 4: return new String[]{"0 Augment Slot", "x3 Energy Storage Multiplier"};
            default: return null;
        }
    }

    public static String[] getAugmentInfo(int i, ItemStack stack){
        String hotswapHead = "Empty";
        if(stack.stackTagCompound!=null) {
            hotswapHead = DrillUtil.getDrillHeadName(stack.stackTagCompound.getInteger("hotswapHead"));
        }
        switch(i){
            case 0: return new String[]{"x1.5 Drill Speed Multiplier", "x1.1 Energy Usage"};
            case 1: return new String[]{"x2.5 Drill Energy Multiplier", "x1.1 Energy Usage"};
            case 2: return new String[]{"Quick exchange of Drill Heads", "1500 RF per change.", EnumChatFormatting.YELLOW + "Head: " + EnumChatFormatting.RED + hotswapHead + EnumChatFormatting.GRAY + " Head", EnumChatFormatting.GRAY + "Hold" + EnumChatFormatting.BLUE + " Right Click" + EnumChatFormatting.GRAY + " to empty."};
            default: return null;
        }
    }

}
