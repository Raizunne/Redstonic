package com.raizu.redstonic.Blocks;

import com.raizu.redstonic.Redstonic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.List;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class StorageBlock extends Block {

    public final PropertyInteger TYPE = PropertyInteger.create("type", 0, 2);

    public StorageBlock() {
        super(Material.IRON);
        setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{TYPE});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE).intValue();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, meta);
    }


}
