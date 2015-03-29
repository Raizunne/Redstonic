package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 01:02 AM.
 */
public class Driller extends BlockContainer {

    public Driller(Material material){
        super(material);
        setBlockName("driller");
        setCreativeTab(Redstonic.redTab);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemstack)	 {
        int direction = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction, 0);
//		System.out.println(world.getBlockMetadata(x, y, z));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        System.out.println(world.getBlockMetadata(x, y, z));
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEDriller();
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
}
