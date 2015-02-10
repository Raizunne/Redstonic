package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:51 PM.
 */
public class DrillModifier extends BlockContainer {

    public DrillModifier(Material material){
        super(material);
        setCreativeTab(Redstonic.redTab);
        setBlockName("DrillModifier");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(!world.isRemote){
            if(player.isSneaking()==false){
                FMLNetworkHandler.openGui(player, Redstonic.instance, 0, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEDrillModifier();
    }
}
