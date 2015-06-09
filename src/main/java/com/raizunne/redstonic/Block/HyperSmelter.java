package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEHyperSmelter;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 19/05/2015, 09:00 PM.
 */
public class HyperSmelter extends BlockContainer {

    TEHyperSmelter te;

    public HyperSmelter() {
        super(Material.anvil);
        setCreativeTab(Redstonic.redTab);
        setBlockName("HyperSmelter");
        setBlockTextureName("redstonic:Driller");
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        TEHyperSmelter tileentity = (TEHyperSmelter) world.getTileEntity(x, y, z);
        this.te = tileentity;
        tileentity.getWorldObj().scheduleBlockUpdate(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, tileentity.getWorldObj().getBlock(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord), 400);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEHyperSmelter();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TEHyperSmelter tile = (TEHyperSmelter)world.getTileEntity(x, y, z);
        EntityItem item;
        for(int i=0; i<tile.getSizeInventory(); i++){
            if(tile.getStackInSlot(i)!=null){
                item = new EntityItem(world, x, y, z, tile.getStackInSlot(i));
                world.spawnEntityInWorld(item);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(!world.isRemote){
            if(!player.isSneaking()){
                FMLNetworkHandler.openGui(player, Redstonic.instance, 4, world, x, y, z);
            }
        }
        return true;
    }

}
