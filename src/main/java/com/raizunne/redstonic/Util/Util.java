package com.raizunne.redstonic.Util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizunne as a part of Redstonic
 * on 23/02/2015, 03:18 PM.
 */
public class Util {

   public static final String ItemShiftInfo = Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + " Shift " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo");
   public static final String ItemCtrlInfo = Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.YELLOW + " Ctrl " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreauginfo");

    public static String[] getMaterialInfo(int i){
        switch(i){
            case 0: return new String[]{"Crafting Material", "Requires " + EnumChatFormatting.RED + "64000" + EnumChatFormatting.GRAY + " Destabilized Redstone to fill.", "or 72 Redstone Blocks"};
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
            case 8: return new String[]{"Instant Mining Speed", "Requires Ultimate Drill Body"};
            default: return null;
        }
    }

    public static String[] getBodyInfo(int i){
        switch(i){
            case 1: return new String[]{"1 Augment Slot"};
            case 2: return new String[]{"2 Augment Slot"};
            case 3: return new String[]{"3 Augment Slot"};
            case 4: return new String[]{"0 Augment Slot", "x3 Energy Storage Multiplier"};
            case 5: return new String[]{"2 Augment Slot"};
            case 6: return new String[]{"3 Augment Slot"};
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
            case 3: return new String[]{"Place block with a right click"};
            default: return null;
        }
    }

    public static boolean getCurrentItem(Item stack){
        return Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem()!=null && Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem().getItem()==stack;
    }

    public static ItemStack toStack(Object ItemORBlock){
        if(ItemORBlock instanceof Block){
            return new ItemStack((Block)ItemORBlock);
        }else if(ItemORBlock instanceof Item){
            return new ItemStack((Item)ItemORBlock);
        }
        return null;
    }

    public static ItemStack toStack(Object item, int size, int meta){
        if(item instanceof Block){
            return new ItemStack((Block)item, size, meta);
        }else if(item instanceof Item){
            return new ItemStack((Item)item, size, meta);
        }
        return null;
    }

    public static ItemStack stack(ItemStack stack, int size, int meta){
        ItemStack stackerino = stack;
        stack.stackSize = size;
        stack.setItemDamage(meta);
        return stackerino;
    }

    public static ItemStack oreStack(String stack, int size, int meta){
        ItemStack stackerino = (ItemStack)OreDictionary.getOres(stack).toArray()[0];
        stackerino.stackSize = size;
        stackerino.setItemDamage(meta);
        return stackerino;
    }

    public static ItemStack sizedStack(ItemStack stack, int stacksize){
        ItemStack stackerino = stack.copy();
        stackerino.stackSize = stacksize;
        return stackerino;
    }

}
