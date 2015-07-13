package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.Item.RedstonicCrate;
import com.raizunne.redstonic.RedstonicItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Raizunne as a part of Redstonic
 * on 23/02/2015, 03:18 PM.
 */
public class Util {

    public static final String ItemShiftInfo = Lang.translate("drill.hold") + " " + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + Lang.translate("util.Shift") + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " " + Lang.translate("drill.formoreinfo");
    public static final String ItemCtrlInfo = Lang.translate("drill.hold") + " " + EnumChatFormatting.ITALIC + EnumChatFormatting.YELLOW + Lang.translate("util.Control") + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " " + Lang.translate("drill.formoreauginfo");
    public static final String ItemAltInfo = Lang.translate("drill.hold") + " " + EnumChatFormatting.ITALIC + EnumChatFormatting.GREEN + Lang.translate("util.Alt") + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " " + Lang.translate("drill.formorekillinfo");
    public static final String missingTE = "Missing mod" + EnumChatFormatting.DARK_AQUA + " Thermal Expansion";
    public static final String missingEIO = "Missing mod" + EnumChatFormatting.DARK_AQUA + " EnderIO";

    public static String[] getMaterialInfo(int i){
        switch(i){
            case 0: return new String[]{"Crafting Material", "Requires " + EnumChatFormatting.RED + "64000" + EnumChatFormatting.GRAY + " Destabilized Redstone to fill.", "or 72 Redstone Blocks"};
            case 1: return new String[]{"Crafting Material", "Contains " + EnumChatFormatting.RED + "64000" + EnumChatFormatting.GRAY + " Destabilized Redstone."};
            case 2: if(Loader.isModLoaded("EnderIO")){
                return null;
            }else{
                return new String[]{"Needs EnderIO"};
            }
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
            case 1: return new String[]{"Max Augments: 1"};
            case 2: return new String[]{"Max Augments: 2"};
            case 3: return new String[]{"Max Augments: 3"};
            case 4: return new String[]{"Max Augments: 0", "x3 Energy Storage Multiplier"};
            case 5: return new String[]{"Max Augments: 2"};
            case 6: return new String[]{"Max Augments: 3"};
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
            case 4: return new String[]{"Attract items to your being."};
            default: return null;
        }
    }

    public static String[] getSwordAugmentInfo(int i, ItemStack stack){
        switch(i){
            case 1: return new String[]{"Fire Enchantment"};
            case 2: return new String[]{"Looting Enchantment"};
            case 3: return new String[]{"+2 Heart Damage"};
            case 4: return new String[]{"+3 Heart Damage", "Can stack with other levels."};
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

    public static boolean hasEnchantment(ItemStack stack, Enchantment enchantment){
        if(stack.stackTagCompound.getTag("ench")!=null) {
            NBTTagList enchants = (NBTTagList)stack.stackTagCompound.getTag("ench");
            for(int i=0; i<enchants.tagCount(); i++){
                NBTTagCompound enchant = ((NBTTagList)enchants).getCompoundTagAt(i);
                if(enchant.getInteger("id")==enchantment.effectId){
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }

    public static void removeEnchantment(ItemStack stack, Enchantment enchantment){
        if(stack.stackTagCompound.getTag("ench")!=null) {
            NBTTagList enchants = (NBTTagList) stack.stackTagCompound.getTag("ench");
            for (int i = 0; i < enchants.tagCount(); i++) {
                NBTTagCompound enchant = ((NBTTagList) enchants).getCompoundTagAt(i);
                if (enchant.getInteger("id") == enchantment.effectId) {
                    enchants.removeTag(i);
                    return;
                }
            }
        }
    }

    public static boolean hasAugment(int i, ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1") == i || nbt.getInteger("aug2") == i || nbt.getInteger("aug3") == i;
    }

    public static List<String> quickLore(String... lore){
        List<String> loreirnonino = new ArrayList<String>();
        for(String loreino : lore){
            loreirnonino.add(loreino);
        }
        return loreirnonino;
    }

    public static ItemStack newCrate(ItemStack[] contents, String sender){
        ItemStack crate = new ItemStack(RedstonicItems.RedCrate);
        RedstonicCrate.setContents(crate, contents);
        RedstonicCrate.setSender(crate, sender);
        RedstonicCrate.setUUID(crate, UUID.randomUUID().toString());
        return crate;
    }

}
