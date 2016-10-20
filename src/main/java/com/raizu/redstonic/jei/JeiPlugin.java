package com.raizu.redstonic.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;

/**
 * Created by Raizu on 18/10/2016.
 * as a part of Redstonic
 **/
@JEIPlugin
public class JeiPlugin extends BlankModPlugin{

    private static IJeiRuntime runtime = null;

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
        super.register(registry);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JeiPlugin.runtime = jeiRuntime;
    }
}
