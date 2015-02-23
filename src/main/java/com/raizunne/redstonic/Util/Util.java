package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/02/2015, 01:31 PM.
 */
public class Util {

    public static ItemStack getDrillHead(int i){
        switch (i) {
            case 0: return new ItemStack(RedstonicItems.IronHead);
            case 1: return new ItemStack(RedstonicItems.GoldHead);
            case 2: return new ItemStack(RedstonicItems.DiamondHead);
            case 3: return new ItemStack(RedstonicItems.HeavyHead);
            case 4: return new ItemStack(RedstonicItems.FortuitousHead);
            case 5: return new ItemStack(RedstonicItems.SilkyHead);
            case 6: return new ItemStack(RedstonicItems.BlazerHead);
            case 7: return new ItemStack(RedstonicItems.EndHead);
            default: return null;
        }
    }

    public static ItemStack getDrillBody(int i){
        switch (i) {
            case 0: return new ItemStack(RedstonicItems.IronBody);
            case 1: return new ItemStack(RedstonicItems.ElectrumBody);
            case 2: return new ItemStack(RedstonicItems.EnderiumBody);
            default: return null;
        }
    }

    public static ItemStack getAugments(int i, int h){
        switch(i){
            case 1: return new ItemStack(RedstonicItems.SpeedAugment);
            case 2: return new ItemStack(RedstonicItems.EnergyAugment);
            case 3:
                ItemStack aug = new ItemStack(RedstonicItems.HotswapAugment);
                aug.stackTagCompound = new NBTTagCompound();
                aug.stackTagCompound.setInteger("hotswapHead", h);
                return aug;
            case 4: return new ItemStack(RedstonicItems.TorchAugment);
            default: return null;
        }
    }

    public static String getDrillHeadName(int i){
        String name;
        switch (i) {
            case 0: name = "Iron"; break;
            case 1: name = "Gold"; break;
            case 2: name = "Diamond"; break;
            case 3: name = "Heavy"; break;
            case 4: name = "Fortuitous"; break;
            case 5: name = "Silky"; break;
            case 6: name = "Blazer"; break;
            case 7: name = "End"; break;
            case -1: name = "Empty"; break;
            default: name = "Unknown"; break;
        }
        return name;
    }

    public static String getDrillBodyName(int i){
        String name;
        switch(i){
            case 0: name = "Iron"; break;
            case 1: name = "Electrum"; break;
            case 2: name = "Enderium"; break;
            default: name = "Unknown"; break;
        }
        return name;
    }

    public static String getAugName(int i){
        String name;
        switch(i){
            case 1: name = "x1.5 Dig Speed Multiplier"; break;
            case 2: name = "x2.5 Energy Multiplier"; break;
            case 3: name = "Hotswap"; break;
            default: name = "Unknown"; break;
        }
        return name;
    }

    public static int getHeadNumber(ItemStack i){
        Item item = i.getItem();
        if(item == RedstonicItems.IronHead){
            return 0;
        }else if(item == RedstonicItems.GoldHead){
            return 1;
        }else if(item == RedstonicItems.DiamondHead){
            return 2;
        }else if(item == RedstonicItems.HeavyHead){
            return 3;
        }else if(item == RedstonicItems.FortuitousHead){
            return 4;
        }else if(item == RedstonicItems.SilkyHead){
            return 5;
        }else if(item == RedstonicItems.BlazerHead) {
            return 6;
        }else if(item==RedstonicItems.EndHead){
            return 7;
        }else{
            return 0;
        }
    }

    public static int getBodyNumber(ItemStack i){
        if(i.getItem() == RedstonicItems.IronBody){
            return 0;
        }else if(i.getItem() == RedstonicItems.ElectrumBody){
            return 1;
        }else if(i.getItem() == RedstonicItems.EnderiumBody){
            return 2;
        }else{
            return 0;
        }
    }

    public static int getEnergyAmount(ItemStack i){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        if(i.isItemEqual(hardened)){
            return 480000;
        }else if(i.isItemEqual(reinforced)){
            return 640000;
        }else if(i.isItemEqual(resonant)){
            return 1000000;
        }
        return 0;
    }


    public static ItemStack getEnergy(int i, ItemStack drill, ItemStack slot){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        float multiplier = drill.stackTagCompound.getFloat("energyMulti");
        if(multiplier==0){multiplier = 1;}
        int total = (int)(i / multiplier);
        ItemStack temp;
        switch(total){
            case 480000:
                temp = hardened;
                if(temp.stackTagCompound==null){
                    temp.stackTagCompound = new NBTTagCompound();
                }
                temp.stackTagCompound.setInteger("Energy", drill.stackTagCompound.getInteger("energy"));
                return temp;
            case 640000:
                temp = reinforced;
                if(temp.stackTagCompound==null){
                    temp.stackTagCompound = new NBTTagCompound();
                }
                temp.stackTagCompound.setInteger("Energy", drill.stackTagCompound.getInteger("energy"));
                return temp;
            case 1000000:
                temp = resonant;
                if(temp.stackTagCompound==null){
                    temp.stackTagCompound = new NBTTagCompound();
                }
                temp.stackTagCompound.setInteger("Energy", drill.stackTagCompound.getInteger("energy"));
                return temp;
            default: return null;
        }
    }

    public static ItemStack applyAug(int aug, ItemStack drill, int hotswapHead){
        switch(aug){
            case 1: drill.stackTagCompound.setFloat("speedMulti", 1.5F); return drill;
            case 2: drill.stackTagCompound.setFloat("energyMulti", 2.5F); return drill;
            case 3: drill.stackTagCompound.setInteger("hotswapHead", hotswapHead); return drill;
            case 0: return drill;
            default: return drill;
        }
    }

    public static int getAugNumber(Item i){
        if(i==RedstonicItems.SpeedAugment){
            return 1;
        }else if(i==RedstonicItems.EnergyAugment){
            return 2;
        }else if(i==RedstonicItems.HotswapAugment) {
            return 3;
        }else if(i==RedstonicItems.TorchAugment){
            return 4;
        }else{
            return 0;
        }
    }
}
