package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Network.PacketDriller;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import com.raizunne.redstonic.Util.DrillUtil;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 01:02 AM.
 */
public class Driller extends BlockContainer {

    TEDriller driller;

    public Driller(Material material){
        super(material);
        setBlockName("driller");
        setCreativeTab(Redstonic.redTab);
        setBlockTextureName("redstonic:Driller");
        setHardness(2F);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_) {
        TEDriller tileentity = (TEDriller)world.getTileEntity(x, y, z);
        this.driller = tileentity;
        tileentity.getWorldObj().scheduleBlockUpdate(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, tileentity.getWorldObj().getBlock(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord), 400);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemstack)	 {
        int direction = MathHelper.floor_double((double) (entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction, 0);
//		System.out.println(world.getBlockMetadata(x, y, z));
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        TEDriller tile = (TEDriller)blockAccess.getTileEntity(x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);
        switch(meta){
            case 0:
                this.setBlockBounds(0.125F, 0, 0, 0.875F, 0.75F, 0.9375F);
                break;
            case 1:
                this.setBlockBounds(0.0625F, 0, 0.125F, 1F, 0.75F, 0.875F);
                break;
            case 2:
                this.setBlockBounds(0.125F, 0, 0.0625F, 0.875F, 0.75F, 1F);
                break;
            case 3:
                this.setBlockBounds(0, 0, 0.125F, 0.9375F, 0.75F, 0.875F);
                break;
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
        TEDriller tile = (TEDriller)world.getTileEntity(x, y, z);
        if(tile.getHead()!=999) {
            ItemStack stack = DrillUtil.getDrillHead(tile.getHead());
            EntityItem item = new EntityItem(world, x, y, z, stack);
            world.spawnEntityInWorld(item);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TEDriller tile = (TEDriller)world.getTileEntity(x, y, z);
        if(player.getCurrentEquippedItem()!=null && player.getCurrentEquippedItem().getItem() instanceof DrillHead && tile.getHead()==999){
            tile.setHead(DrillUtil.getHeadNumber(player.getCurrentEquippedItem()));
            player.destroyCurrentEquippedItem();
        }else if(player.getCurrentEquippedItem()==null && tile.getHead()!=999){
            tile.removeHead(player);
        }
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
