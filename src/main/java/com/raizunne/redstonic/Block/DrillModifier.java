package com.raizunne.redstonic.Block;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:51 PM.
 */
public class DrillModifier extends BlockContainer {

    public DrillModifier(Material material){
        super(material);
        setCreativeTab(Redstonic.redTab);
        setBlockName("DrillModifier");
        setBlockTextureName("redstonic:DrillModifier");
        setBlockBounds(0.0625F, 0, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        setHardness(2F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(!world.isRemote){
            if(!player.isSneaking()){
                FMLNetworkHandler.openGui(player, Redstonic.instance, 0, world, x, y, z);
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        if(entityLiving instanceof EntityPlayer && !(entityLiving instanceof FakePlayer)){
            EntityPlayer player = (EntityPlayer)entityLiving;
            if(!player.getEntityData().getBoolean("hasRedstonicManual")){
                if(!world.isRemote){player.addChatComponentMessage(new ChatComponentText("You were given a " + EnumChatFormatting.RED + "Redstonic Manual" + EnumChatFormatting.WHITE + "!"));}
                player.getEntityData().setBoolean("hasRedstonicManual", true);
                if(!player.inventory.addItemStackToInventory(new ItemStack(RedstonicItems.ManualBook))){
                    EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(RedstonicItems.ManualBook));
                    if(!world.isRemote){
                        world.spawnEntityInWorld(item);
                    }
                }
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TEDrillModifier tile = (TEDrillModifier)world.getTileEntity(x, y, z);
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
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TEDrillModifier();
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
