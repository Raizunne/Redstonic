package com.raizu.redstonic.Blocks.Cone;

import com.raizu.redstonic.Redstonic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class Cone extends Block {

    public Cone() {
        super(Material.CIRCUITS);
        setRegistryName("Cone");
        setUnlocalizedName("Cone");
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.8125, 0.8125);
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }
}
