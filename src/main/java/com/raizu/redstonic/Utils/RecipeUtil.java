package com.raizu.redstonic.Utils;

import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.JEI.Modifier.ModifierRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class RecipeUtil {

    public static List<ModifierRecipeWrapper> getDrillRecipes(){
        List<ModifierRecipeWrapper> recipes = new ArrayList<ModifierRecipeWrapper>();
        List<ItemStack> drillHeads = new ArrayList<ItemStack>();
        List<ItemStack> drillBodies = new ArrayList<ItemStack>();
        List<ItemStack> batteries = new ArrayList<ItemStack>();
        List<ItemStack> outputs = new ArrayList<ItemStack>();
        List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
        for (int i = 0; i < RedItems.drillHead.heads.length; i++) {
            drillHeads.add(new ItemStack(RedItems.drillHead, 1, i));
            for (int j = 0; j < RedItems.drillBody.bodies.length; j++) {
                drillBodies.add(new ItemStack(RedItems.drillBody, 1, j));
                for (int k = 0; k < RedItems.battery.names.length-1; k++) {
                    batteries.add(new ItemStack(RedItems.battery, 1, k));
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setInteger("head", i);
                    tag.setInteger("body", j);
                    tag.setInteger("battery", k);
                    tag.setInteger("maxEnergy", RedItems.battery.maxEnergy[k]);
                    tag.setInteger("Energy", RedItems.battery.maxEnergy[k]);

                    ItemStack newDrill = new ItemStack(RedItems.drill, 1, 0);
                    newDrill.setTagCompound(tag);
                    outputs.add(newDrill);
                }
            }
        }
        Collections.shuffle(drillHeads, new Random(System.nanoTime()));
        Collections.shuffle(drillBodies, new Random(System.nanoTime()));
        Collections.shuffle(batteries, new Random(System.nanoTime()));
        Collections.shuffle(outputs, new Random(System.nanoTime()));
        inputs.add(drillHeads);
        inputs.add(drillBodies);
        inputs.add(batteries);
        recipes.add(new ModifierRecipeWrapper(inputs, outputs));
        return recipes;

    }

    public static List<ModifierRecipeWrapper> getSwordRecipes(){
        List<ModifierRecipeWrapper> recipes = new ArrayList<ModifierRecipeWrapper>();
        List<ItemStack> swordBlades = new ArrayList<ItemStack>();
        List<ItemStack> swordHandles = new ArrayList<ItemStack>();
        List<ItemStack> batteries = new ArrayList<ItemStack>();
        List<ItemStack> outputs = new ArrayList<ItemStack>();
        List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
        for (int i = 0; i < RedItems.swordBlade.blades.length; i++) {
            swordBlades.add(new ItemStack(RedItems.swordBlade, 1, i));
            for (int j = 0; j < RedItems.swordHandle.handles.length; j++) {
                swordHandles.add(new ItemStack(RedItems.swordHandle, 1, j));
                for (int k = 0; k < RedItems.battery.names.length-1; k++) {
                    batteries.add(new ItemStack(RedItems.battery, 1, k));
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setInteger("blade", i);
                    tag.setInteger("handle", j);
                    tag.setInteger("battery", k);
                    tag.setInteger("maxEnergy", RedItems.battery.maxEnergy[k]);
                    tag.setInteger("Energy", RedItems.battery.maxEnergy[k]);

                    ItemStack newSword = new ItemStack(RedItems.sword, 1, 0);
                    newSword.setTagCompound(tag);
                    outputs.add(newSword);
                }
            }
        }
        Collections.shuffle(swordBlades, new Random(System.nanoTime()));
        Collections.shuffle(swordHandles, new Random(System.nanoTime()));
        Collections.shuffle(batteries, new Random(System.nanoTime()));
        Collections.shuffle(outputs, new Random(System.nanoTime()));

        inputs.add(swordBlades);
        inputs.add(swordHandles);
        inputs.add(batteries);
        recipes.add(new ModifierRecipeWrapper(inputs, outputs));
        return recipes;
    }
}
