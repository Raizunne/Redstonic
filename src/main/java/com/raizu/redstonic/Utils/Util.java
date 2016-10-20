package com.raizu.redstonic.Utils;

import com.raizu.redstonic.Blocks.Modifier.GUIModifier;
import com.raizu.redstonic.Blocks.Modifier.TEModifier;
import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class Util {

    public static boolean hasEnchantment(ItemStack stack, String enchantment){
        int id = Enchantment.getEnchantmentID(Enchantment.getEnchantmentByLocation(enchantment));
        for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
            if(stack.getEnchantmentTagList().getCompoundTagAt(i).getInteger("id")==id)return true;
        }
        return false;
    }

    public static boolean hasEnchantment(ItemStack stack, String enchantment, int lvl){
        int id = Enchantment.getEnchantmentID(Enchantment.getEnchantmentByLocation(enchantment));
        for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
            if(stack.getEnchantmentTagList().getCompoundTagAt(i).getInteger("id")==id && stack.getEnchantmentTagList().getCompoundTagAt(i).getInteger("lvl")==lvl)return true;
        }
        return false;
    }

    public static boolean hasDuplicateAugments(ItemStack[] augs){
        List<Integer> goodAugs = new ArrayList<Integer>(augs.length);
        int nullCount=0;
        TEModifier.CraftType craftType = TEModifier.CraftType.DRILL;
        for(ItemStack aug : augs) {
            if (aug == null)nullCount++;
            else craftType = aug.getItem() instanceof DrillAugment ? TEModifier.CraftType.DRILL : TEModifier.CraftType.SWORD;
        }
        if(nullCount>=3)return false;
        for(ItemStack aug : augs){
            if(aug!=null){
                if(craftType == TEModifier.CraftType.DRILL){
                    if(!(aug.getItem() instanceof DrillAugment))return true;
                }else{
                    //TODO: ADD SWORD CHECKING
//                    if(aug.getItem() instanceof )
                }
                if(goodAugs.contains(aug.getMetadata())){
                    return true;
                }else{
                    goodAugs.add(aug.getMetadata());
                }
            }
        }
        return false;
    }

}
