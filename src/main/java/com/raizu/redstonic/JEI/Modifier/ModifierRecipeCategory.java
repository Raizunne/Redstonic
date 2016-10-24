package com.raizu.redstonic.JEI.Modifier;

import com.raizu.redstonic.Utils.StringUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class ModifierRecipeCategory implements IRecipeCategory {

    IDrawable background;

    public ModifierRecipeCategory(IGuiHelper helper){
        background = helper.createDrawable(new ResourceLocation("redstonic", "textures/gui/JEIModifier.png"), 0, 0, 111, 84);
    }

    @Override
    public String getUid() {
        return "redstonic.modifier";
    }

    @Override
    public String getTitle() {
        return StringUtils.localize("redstonic.gui.modifier");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0, false, 20, 33);
        int x=73, y = 10;
        for (int i = 1; i < 4; i++) {
            stacks.init(i, true, x, y);
            y+=23;
        }
        if(recipeWrapper instanceof ModifierRecipeWrapper){
            stacks.set(0, (ItemStack)recipeWrapper.getOutputs().get(0));
            for (int i = 1; i < 4; i++) {
                stacks.set(i, (ItemStack)recipeWrapper.getInputs().get(i-1));
            }
        }
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0, false, 20, 33);
        int x=73, y = 10;
        for (int i = 1; i < 4; i++) {
            stacks.init(i, true, x, y);
            y+=23;
        }
        if(recipeWrapper instanceof ModifierRecipeWrapper){
            stacks.set(0, ingredients.getOutputs(ItemStack.class).get(0));
            for (int i = 1; i < 4; i++) {
                stacks.set(i, ingredients.getInputs(ItemStack.class).get(i-1));
            }
        }
    }
}
