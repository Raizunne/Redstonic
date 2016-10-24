package com.raizu.redstonic.JEI.Modifier;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class ModifierRecipeWrapper extends BlankRecipeWrapper {

    private List<List<ItemStack>> inputs;
    private List<ItemStack> output;

    public ModifierRecipeWrapper(List<List<ItemStack>> inputs, List<ItemStack> output){
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutputs(ItemStack.class, output);
    }

}
