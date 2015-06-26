package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.Item.ItemBattery;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/02/2015, 01:31 PM.
 */
public class DrillUtil {

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
            case 3: return new ItemStack(RedstonicItems.UltimateBody);
            case 4: return new ItemStack(RedstonicItems.EnergeticBody);
            case 5: return new ItemStack(RedstonicItems.VibrantBody);
            default: return null;
        }
    }

    public static ItemStack getDrillBattery(int i, ItemStack drill){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        ItemStack creative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
        ItemStack temp = null; int battery = drill.stackTagCompound.getInteger("battery");
        switch(i){
            case 0: temp = hardened; break;
            case 1: temp = reinforced; break;
            case 2: temp = resonant; break;
            case 3: temp = Util.toStack(RedstonicItems.basicBattery); break;
            case 4: temp = Util.toStack(RedstonicItems.energizedBattery); break;
            case 5: temp = Util.toStack(RedstonicItems.greatBattery); break;
            case -1: temp = creative; return temp;
            case -2: temp = Util.toStack(RedstonicItems.infiniteBattery); return temp;
        }
        if(temp==null){
            return null;
        }
        if(temp.stackTagCompound==null){
            temp.stackTagCompound = new NBTTagCompound();
        }

        int energyerino = drill.stackTagCompound.hasKey("Energy") ? drill.stackTagCompound.getInteger("Energy") : drill.stackTagCompound.getInteger("energy");

        temp.stackTagCompound.setInteger("Energy", energyerino);
        if(temp.getItem() instanceof ItemBattery){
            temp.stackTagCompound.setInteger("maxEnergy", getMaxBatteryEnergy(battery));
        }
        return temp;
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
            case 4: return new ItemStack(RedstonicItems.BlockAugment);
            case 5: return new ItemStack(RedstonicItems.MagnetAugment);
            case 6: return new ItemStack(RedstonicItems.SpeedIIAugment);
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
            case 7: name = "Ultimate"; break;
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
            case 3: name = "End"; break;
            case 4: name = "Energetic"; break;
            case 5: name = "Vibrant"; break;
            default: name = "Unknown"; break;
        }
        return name;
    }

    public static String getBatteryName(int i){
        switch(i){
            case -2: return "Infinite Battery";
            case -1: return "Creative Capacitor";
            case 0: return "Hardened Capacitor";
            case 1: return "Redstone Capacitor";
            case 2: return "Resonant Capacitor";
            case 3: return "Basic Battery";
            case 4: return "Energized Battery";
            case 5: return "Great Battery";
            case 6: return "Infinite Battery";
        }
        return "Unknown Battery";
    }

    public static String getAugName(int i){
        String name;
        switch(i){
            case 1: name = "x1.5 Dig Speed Multiplier"; break;
            case 2: name = "x2.5 Energy Multiplier"; break;
            case 3: name = "Hotswap"; break;
            case 4: name = "Block Placer"; break;
            case 5: name = "Magnerization Augment"; break;
            case 6: name = "x2.0 Dig Speed Multiplier"; break;
            default: name = "Unknown"; break;
        }
        return name;
    }

    public static int getMaxBatteryEnergy(int type){
        switch(type){
            case 3: return 500000;
            case 4: return 5000000;
            case 5: return 25000000;
            case -1: return -1;
            default: return 0;
        }
    }

    public static int getHeadNumber(ItemStack stack){
        ItemStack[] heads = new ItemStack[]{Util.toStack(RedstonicItems.IronHead), Util.toStack(RedstonicItems.GoldHead), Util.toStack(RedstonicItems.DiamondHead), Util.toStack(RedstonicItems.HeavyHead),
                Util.toStack(RedstonicItems.FortuitousHead), Util.toStack(RedstonicItems.SilkyHead), Util.toStack(RedstonicItems.BlazerHead), Util.toStack(RedstonicItems.EndHead)};
        for(int i=0; i<heads.length; i++){
            if(heads[i].isItemEqual(stack)){
                return i;
            }
        }
        return 0;
    }

    public static int getBodyNumber(ItemStack stack){
        ItemStack[] bodies = new ItemStack[]{Util.toStack(RedstonicItems.IronBody), Util.toStack(RedstonicItems.ElectrumBody), Util.toStack(RedstonicItems.EnderiumBody), Util.toStack(RedstonicItems.UltimateBody),
                Util.toStack(RedstonicItems.EnergeticBody), Util.toStack(RedstonicItems.VibrantBody)};
        for(int i=0; i<bodies.length; i++){
            if(bodies[i].isItemEqual(stack)){
                return i;
            }
        }
        return 0;
    }

    public static int getBatteryNumber(ItemStack stack){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        ItemStack creative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
        Item[] batteries = new Item[]{RedstonicItems.basicBattery, RedstonicItems.energizedBattery, RedstonicItems.greatBattery};
        ItemStack[] capacitor = new ItemStack[]{hardened, reinforced, resonant};
        if(Loader.isModLoaded("ThermalExpansion")){
            if(stack.isItemEqual(creative)){
                return -1;
            }else
            for(int i=0; i<capacitor.length; i++){
                if(capacitor[i].isItemEqual(stack)){
                    return i;
                }
            }
        }
        if(stack.getItem()==RedstonicItems.infiniteBattery){
            return -2;
        }
        for(int i=0; i<batteries.length; i++){
            if(batteries[i]==stack.getItem()){
                return i+3;
            }
        }
        return 0;
    }


    public static int checkMaxAugments(ItemStack stack){
        int augments = 0;
        if(stack!=null){
            if(stack.getItem() == RedstonicItems.IronBody){
                augments = 1;
            }else if(stack.getItem() == RedstonicItems.ElectrumBody){
                augments = 2;
            }else if(stack.getItem() == RedstonicItems.EnderiumBody){
                augments = 3;
            }else if(stack.getItem() == RedstonicItems.UltimateBody){
                augments = 0;
            }else if(stack.getItem() == RedstonicItems.EnergeticBody){
                augments = 2;
            }else if(stack.getItem() == RedstonicItems.VibrantBody){
                augments = 3;
            }
        }else{
            augments = 0;
        }
        return augments;
    }

    public static int getAugNumber(ItemStack item){
        if(item==null)return 0;
        ItemStack[] augments = new ItemStack[]{Util.toStack(RedstonicItems.SpeedAugment), Util.toStack(RedstonicItems.EnergyAugment), Util.toStack(RedstonicItems.HotswapAugment),
                Util.toStack(RedstonicItems.BlockAugment), Util.toStack(RedstonicItems.MagnetAugment), Util.toStack(RedstonicItems.SpeedIIAugment)};
        for(int i=0; i<augments.length; i++){
            if(augments[i].isItemEqual(item)){
                return i+1;
            }
        }
        return 0;
    }

    public static int getEnergyAmount(ItemStack i, ItemStack drill){
        ItemStack hardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        ItemStack reinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        ItemStack resonant = GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1);
        ItemStack creative = GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1);
        int tier1 = 320000; int tier2 = 1280000; int tier3 = 3200000;
        float multiplier=1;
        if(drill.stackTagCompound.getInteger("body")==3){multiplier=3;}
        if(Loader.isModLoaded("ThermalExpansion")) {
            if (i.isItemEqual(hardened)) {
                return (int) (tier1 * multiplier);
            } else if (i.isItemEqual(reinforced)) {
                return (int) (tier2 * multiplier);
            } else if (i.isItemEqual(resonant)) {
                return (int) (tier3 * multiplier);
            }else if(i.isItemEqual(creative)) {
                return -1;
            }
        }
        if(i.getItem()==RedstonicItems.basicBattery){
            return (int)(tier1 * multiplier);
        }else if(i.getItem()==RedstonicItems.energizedBattery){
            return (int)(tier2 * multiplier);
        }else if(i.getItem()==RedstonicItems.greatBattery){
            return (int)(tier3 * multiplier);
        }else if(i.getItem()==RedstonicItems.infiniteBattery){
            return -2;
        }
        return 0;
    }

    public static int id(Item item){return Item.getIdFromItem(item);}

    public static int getHead(ItemStack stack){
        return stack.stackTagCompound.getInteger("head");
    }

    public static int getBody(ItemStack stack){
        return stack.stackTagCompound.getInteger("body");
    }

    public static int getBattery(ItemStack stack){
        return stack.stackTagCompound.getInteger("battery");
    }

    public static ItemStack applyAug(int aug, ItemStack drill, int hotswapHead){
        switch(aug){
            case 1: drill.stackTagCompound.setFloat("speedMulti", 1.5F); return drill;
            case 2: drill.stackTagCompound.setFloat("energyMulti", 2.5F); return drill;
            case 3: drill.stackTagCompound.setInteger("hotswapHead", hotswapHead); return drill;
            case 4: return drill;
            case 5: return drill;
            case 0: return drill;
            default: return drill;
        }
    }

    public static int getCooldown(int head){
        switch(head){
            case 0: return 40;
            case 1: return 10;
            case 2: return 20;
            case 3: return 50;
            case 4: return 20;
            case 5: return 30;
            case 6: return 25;
            case 7: return 1;
            default: return 0;
        }
    }

    public static ItemStack getPlaceholderDrill(int head, int body, int aug1, int aug2, int aug3){
        ItemStack placeDrill = new ItemStack(RedstonicItems.RedDrill);
        placeDrill.stackTagCompound = new NBTTagCompound();
        NBTTagCompound tag = placeDrill.stackTagCompound;
        tag.setInteger("head", head);
        tag.setInteger("body", body);
        tag.setInteger("aug1", aug1);
        tag.setInteger("aug2", aug2);
        tag.setInteger("aug3", aug3);
        return placeDrill;
    }
}
