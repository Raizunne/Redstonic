package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDriller;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 01:02 AM.
 */
public class Driller extends Block {

    public Driller(Material material){
        super(material);
        setBlockName("driller");
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TEDriller();
    }
}
