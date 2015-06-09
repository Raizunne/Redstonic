package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.RedstonicItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Raizunne as a part of Redstonic
 * on 01/05/2015, 09:23 PM.
 */
public class SwordUtil {

    public static int getBladeNumber(ItemStack blade){
        Item[] heads = new Item[]{RedstonicItems.IronBlade, RedstonicItems.DiamondBlade, RedstonicItems.ElectrumBlade, RedstonicItems.EnderiumBlade, RedstonicItems.EnergizedBlade, RedstonicItems.VibrantBlade};
        for(int i=0; i<heads.length; i++){
            if(new ItemStack(heads[i]).isItemEqual(blade)){
                return i;
            }
        }
        return 0;
    }

    public static int getHandleNumber(ItemStack handle){
        Item[] heads = new Item[]{RedstonicItems.WoodHandle, RedstonicItems.IronHandle, RedstonicItems.ElectrumHandle, RedstonicItems.EnderiumHandle, RedstonicItems.EnergizedHandle, RedstonicItems.VibrantHandle};
        for(int i=0; i<heads.length; i++){
            if(new ItemStack(heads[i]).isItemEqual(handle)){
                return i;
            }
        }
        return 0;
    }

    public static String getBladeName(int i){
        switch(i){
            case 0: return "Iron";
            case 1: return "Diamond";
            case 2: return "Electrum";
            case 3: return "Enderium";
            case 4: return "Energetic";
            case 5: return "Vibrant";
        }
        return "Unknown";
    }

    public static String getHandleName(int i){
        switch(i){
            case 0: return "Wood";
            case 1: return "Iron";
            case 2: return "Electrum";
            case 3: return "Enderium";
            case 4: return "Energetic";
            case 5: return "Vibrant";
        }
        return "Unknown";
    }

    public static int getDamage(ItemStack i){
        int blade = getBladeNumber(i);
        switch(blade){
            case 0: return 8;
            case 1: return 12;
            case 2: return 18;
            case 3: return 22;
            case 4: return 18;
            case 5: return 22;
        }
        return 1;
    }

    public static int getAbsoluteDamage(ItemStack i){
        int blade = i.stackTagCompound.getInteger("blade");
        int damage = 0;
        int multi = 0;
        switch(blade){
            case 0: damage=8; break;
            case 1: damage=12; break;
            case 2: damage=18; break;
            case 3: damage=22; break;
            case 4: damage=18; break;
            case 5: damage=22; break;
        }
        if(Util.hasAugment(3, i))multi+=4;
        if(Util.hasAugment(4, i))multi+=6;
        return damage+multi;
    }

    public static ItemStack getBlade(int i){
        switch (i) {
            case 0: return new ItemStack(RedstonicItems.IronBlade);
            case 1: return new ItemStack(RedstonicItems.DiamondBlade);
            case 2: return new ItemStack(RedstonicItems.ElectrumBlade);
            case 3: return new ItemStack(RedstonicItems.EnderiumBlade);
            case 4: return new ItemStack(RedstonicItems.EnergizedBlade);
            case 5: return new ItemStack(RedstonicItems.VibrantBlade);
            default: return null;
        }
    }

    public static ItemStack getHandle(int i){
        switch (i) {
            case 0: return new ItemStack(RedstonicItems.WoodHandle);
            case 1: return new ItemStack(RedstonicItems.IronHandle);
            case 2: return new ItemStack(RedstonicItems.ElectrumHandle);
            case 3: return new ItemStack(RedstonicItems.EnderiumHandle);
            case 4: return new ItemStack(RedstonicItems.EnergizedHandle);
            case 5: return new ItemStack(RedstonicItems.VibrantHandle);
            default: return null;
        }
    }

    public static int getAugNumber(ItemStack stack){
        if(stack==null)return 0;
        Item[] augs = new Item[]{RedstonicItems.BlazerSwordAugment, RedstonicItems.FortuitousSwordAugment, RedstonicItems.BerserkSwordAugment, RedstonicItems.BerserkIISwordAugment};
        for(int i=0; i<augs.length; i++){
            if(new ItemStack(augs[i]).isItemEqual(stack)){
                return i+1;
            }
        }
        return 0;
    }

    public static ItemStack getAugments(int i){
        switch(i){
            case 1: return new ItemStack(RedstonicItems.BlazerSwordAugment);
            case 2: return new ItemStack(RedstonicItems.FortuitousSwordAugment);
            case 3: return new ItemStack(RedstonicItems.BerserkSwordAugment);
            case 4: return new ItemStack(RedstonicItems.BerserkIISwordAugment);
            default: return null;
        }
    }

    public static String getAugName(int i){
        String name;
        switch(i){
            case 1: name = "Flame Augment"; break;
            case 2: name = "Fortuitous Augment"; break;
            case 3: name = "Berserk +2 Damage"; break;
            case 4: name = "Berserk II +3 Damage"; break;
            default: name = "Unknown Augment"; break;
        }
        return name;
    }

    public static int getMaxAugments(ItemStack stack){
        int handle = getHandleNumber(stack);
        switch(handle){
            case 0: return 0;
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            case 4: return 2;
            case 5: return 3;
            default: return 0;
        }
    }

    public static ItemStack getPlaceholderSword(int blade, int handle, int aug1, int aug2, int aug3){
        ItemStack placeSword = new ItemStack(RedstonicItems.RedSword);
        placeSword.stackTagCompound = new NBTTagCompound();
        NBTTagCompound tag = placeSword.stackTagCompound;
        tag.setInteger("blade", blade);
        tag.setInteger("handle", handle);
        tag.setInteger("aug1", aug1);
        tag.setInteger("aug2", aug2);
        tag.setInteger("aug3", aug3);
        return placeSword;
    }

}
