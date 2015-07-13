package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEArmorModifier;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 26/06/2015, 07:39 PM.
 */
public class ArmorModifier extends BlockContainer {

    public ArmorModifier(){
        super(Material.piston);
        setBlockName("ArmorModifier");
        setHardness(2F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEArmorModifier();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        FMLNetworkHandler.openGui(player, Redstonic.instance, 6, world, x, y, z);
        return true;
    }
}