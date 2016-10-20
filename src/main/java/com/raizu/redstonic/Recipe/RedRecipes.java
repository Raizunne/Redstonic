package com.raizu.redstonic.Recipe;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Recipe.IRecipe.HotswapRecipe;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Raizu on 18/10/2016.
 * as a part of Redstonic
 **/
public class RedRecipes {

    public static void init(){
        GameRegistry.addRecipe(new HotswapRecipe());
        GameRegistry.addRecipe(new ShapedOreRecipe(RedBlocks.cone, new Object[]{
                " S ",
                "S S",
                "LLL", 'S', "itemConduitBinder", 'L', "slabStone"
        }));
    }

}
