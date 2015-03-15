package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/03/2015, 09:40 PM.
 */
public class BlockCompressed extends Block {

    int type;

    public BlockCompressed(Material material){
        super(material);
    }

    public BlockCompressed(int type){
        super(Material.iron);
        this.type = type;
        setBlockTextureName(getTexture());
        setBlockName(getBlockName());
        setHardness(4F);
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(this));
        return list;
    }

    public String getBlockName(){
        switch(type){
            case 0: return "blockGlowSteel";
            case 1: return "blockVibrantium";
        }
        return "unknownCompressedBlock";
    }

    public String getTexture(){
        switch(type){
            case 0: return "redstonic:blockGlowSteel";
            case 1: return "redstonic:blockVibrantium";
        }
        return "false";
    }
}
