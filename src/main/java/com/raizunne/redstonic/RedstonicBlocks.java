package com.raizunne.redstonic;

import com.raizunne.redstonic.Block.DrillModifier;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 06:15 AM.
 */
public class RedstonicBlocks {

    public static Block Modifier = new DrillModifier(Material.ground);

    public static void init(){
        GameRegistry.registerBlock(Modifier, Modifier.getUnlocalizedName());

        GameRegistry.registerTileEntity(TEDrillModifier.class, "TEDrillModifier");
    }

}