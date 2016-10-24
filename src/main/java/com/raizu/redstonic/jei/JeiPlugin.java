package com.raizu.redstonic.JEI;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Utils.RecipeUtil;
import com.raizu.redstonic.JEI.Modifier.ModifierRecipeCategory;
import com.raizu.redstonic.JEI.Modifier.ModifierRecipeHandler;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;

/**
 * Created by Raizu on 18/10/2016.
 * as a part of Redstonic
 **/
@JEIPlugin
public class JeiPlugin extends BlankModPlugin{

    private static IJeiRuntime runtime = null;
    public static JeiPlugin INSTANCE;

    public JeiPlugin() {
        super();
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        super.registerItemSubtypes(subtypeRegistry);
    }

    @Override
    public void registerIngredients(IModIngredientRegistration ingredientRegistry) {
        super.registerIngredients(ingredientRegistry);
    }

    @Override
    public void register(IModRegistry registry) {
        INSTANCE = this;
        registry.addRecipeCategories(new ModifierRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeHandlers(new ModifierRecipeHandler());
        registry.addRecipes(RecipeUtil.getDrillRecipes());
        registry.addRecipes(RecipeUtil.getSwordRecipes());
        registry.addRecipeCategoryCraftingItem(new ItemStack(RedBlocks.modifier), "redstonic.modifier");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JeiPlugin.runtime = jeiRuntime;
    }

    public IJeiRuntime getRuntime(){
        return runtime;
    }
}
