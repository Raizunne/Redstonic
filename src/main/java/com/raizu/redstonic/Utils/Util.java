package com.raizu.redstonic.Utils;

import com.raizu.redstonic.Blocks.Modifier.GUIModifier;
import com.raizu.redstonic.Blocks.Modifier.TEModifier;
import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

    public static void addAlloySmelterRecipe(String name, int energy, ItemStack slot1, ItemStack slot2, ItemStack slot3, ItemStack output) {
        StringBuilder sending = new StringBuilder();
        sending.append("<recipeGroup name=\"Redstonic\">");{
            sending.append("<recipe name=\"" + name + "\" energyCost=\"" + energy + "\">");{
                sending.append("<input>");{
                    if(slot1!=null){
                        ResourceLocation item1 = slot1.getItem().getRegistryName();
                        sending.append("<itemStack modID=\"" + item1.getResourceDomain() + "\" itemName=\"" + item1.getResourcePath() + "\" itemMeta=\"" + slot1.getItemDamage() + "\" number=\"" + slot1.stackSize + "\" />");
                    }
                    if(slot2!=null){
                        ResourceLocation item2 = slot2.getItem().getRegistryName();
                        sending.append("<itemStack modID=\"" + item2.getResourceDomain() + "\" itemName=\"" + item2.getResourcePath() + "\" itemMeta=\"" + slot2.getItemDamage() + "\" number=\"" + slot2.stackSize + "\" />");
                    }
                    if(slot3!=null) {
                        ResourceLocation item3 = slot3.getItem().getRegistryName();
                        sending.append("<itemStack modID=\"" + item3.getResourceDomain() + "\" itemName=\"" + item3.getResourcePath() + "\" itemMeta=\"" + slot3.getItemDamage() + "\" number=\"" + slot3.stackSize + "\" />");
                    }
                }
                sending.append("</input>");
                sending.append("<output>");{
                    ResourceLocation outputerino = Item.REGISTRY.getNameForObject(output.getItem());
                    sending.append("<itemStack modID=\"" + outputerino.getResourceDomain() + "\" itemName=\"" + outputerino.getResourcePath() + "\" itemMeta=\"" + output.getItemDamage() + "\" number=\"" + output.stackSize + "\" />");
                }
                sending.append("</output>");
            }
            sending.append("</recipe>");
        }
        sending.append("</recipeGroup>");

        FMLInterModComms.sendMessage("EnderIO", "recipe:alloysmelter", sending.toString());
    }

    public static ItemStack findItemStack(String modid, String name, int amount, int meta){
        return new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(modid+":"+name)), amount, meta);
    }
}
