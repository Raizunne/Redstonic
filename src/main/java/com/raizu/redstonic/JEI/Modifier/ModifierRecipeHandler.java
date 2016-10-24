package com.raizu.redstonic.JEI.Modifier;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class ModifierRecipeHandler implements IRecipeHandler<ModifierRecipeWrapper>{

    @Override
    public Class<ModifierRecipeWrapper> getRecipeClass() {
        return ModifierRecipeWrapper.class;
    }

    @Override
    public String getRecipeCategoryUid() {
        return "redstonic.modifier";
    }

    @Override
    public String getRecipeCategoryUid(ModifierRecipeWrapper recipe) {
        return "redstonic.modifier";
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(ModifierRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(ModifierRecipeWrapper recipe) {
        return true;
    }

    public void getRecipes(){

    }
}
