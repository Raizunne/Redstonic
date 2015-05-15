package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.TileEntity.TEToolModifier;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 01/05/2015, 09:00 PM.
 */
public class ToolModifier extends BlockContainer {

    protected ToolModifier(Material material) {
        super(Material.anvil);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEToolModifier();
    }
}
