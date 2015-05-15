package com.raizunne.redstonic.Util;

import com.raizunne.redstonic.Redstonic;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizunne as a part of Redstonic
 * on 28/02/2015, 02:46 PM.
 */
public class EIOHelper {

    public static ItemStack ingotVibrant = null;
    public static ItemStack ingotSoularium = null;
    public static ItemStack ingotEnergized = null;
    public static ItemStack ingotElectrical = null;
    public static ItemStack etchingCrystal = null;
    public static ItemStack basicCapacitor = null;
    public static ItemStack doubleCapacitor = null;
    public static ItemStack octadicCapacitor = null;
    public static ItemStack basicGear = null;
    public static ItemStack blockEnergetic = null;
    public static ItemStack blockVibrantium = null;

    public static void init(){
        ingotVibrant = OreDictionary.getOres("ingotPhasedGold").get(0);
        ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0);
        ingotEnergized = OreDictionary.getOres("ingotEnergeticAlloy").get(0);
        ingotElectrical = OreDictionary.getOres("ingotElectricalSteel").get(0);
        etchingCrystal = new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 9);
        basicCapacitor = new ItemStack(GameRegistry.findItem("EnderIO", "itemBasicCapacitor"), 1, 0);
        doubleCapacitor = new ItemStack(GameRegistry.findItem("EnderIO", "itemBasicCapacitor"), 1, 1);
        octadicCapacitor = new ItemStack(GameRegistry.findItem("EnderIO", "itemBasicCapacitor"), 1, 2);
        basicGear = new ItemStack(GameRegistry.findItem("EnderIO", "itemMachinePart"), 1, 1);
        blockEnergetic = OreDictionary.getOres("blockEnergeticAlloy").get(0);
        blockVibrantium = OreDictionary.getOres("blockPhasedGold").get(0);

    }

    public static void addAlloySmelterRecipe(String name, int energy, ItemStack slot1, ItemStack slot2, ItemStack slot3, ItemStack output) {
        StringBuilder sending = new StringBuilder();
        sending.append("<recipeGroup name=\"Redstonic\">");{
            sending.append("<recipe name=\"" + name + "\" energyCost=\"" + energy + "\">");{
                sending.append("<input>");{
                    if(slot1!=null){
                        String[] item1 = Item.itemRegistry.getNameForObject(slot1.getItem()).split(":");
                        sending.append("<itemStack modID=\"" + item1[0] + "\" itemName=\"" + item1[1] + "\" itemMeta=\"" + slot1.getItemDamage() + "\" number=\"" + slot1.stackSize + "\" />");
                    }
                    if(slot2!=null){
                        String[] item2 = Item.itemRegistry.getNameForObject(slot2.getItem()).split(":");
                        sending.append("<itemStack modID=\"" + item2[0] + "\" itemName=\"" + item2[1] + "\" itemMeta=\"" + slot2.getItemDamage() + "\" number=\"" + slot2.stackSize + "\" />");
                    }
                    if(slot3!=null) {
                        String[] item3 = Item.itemRegistry.getNameForObject(slot3.getItem()).split(":");
                        sending.append("<itemStack modID=\"" + item3[0] + "\" itemName=\"" + item3[1] + "\" itemMeta=\"" + slot3.getItemDamage() + "\" number=\"" + slot3.stackSize + "\" />");
                    }
                }
                sending.append("</input>");
                sending.append("<output>");{
                    String[] outputerino = Item.itemRegistry.getNameForObject(output.getItem()).split(":");
                    sending.append("<itemStack modID=\"" + outputerino[0] + "\" itemName=\"" + outputerino[1] + "\" itemMeta=\"" + output.getItemDamage() + "\" number=\"" + output.stackSize + "\" />");
                }
                sending.append("</output>");
            }
            sending.append("</recipe>");
        }
        sending.append("</recipeGroup>");

        FMLInterModComms.sendMessage("EnderIO", "recipe:alloysmelter", sending.toString());
    }
}
