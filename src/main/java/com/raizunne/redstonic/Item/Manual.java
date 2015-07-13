package com.raizunne.redstonic.Item;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicBlocks;
import com.raizunne.redstonic.Util.XML;
import cpw.mods.fml.client.GuiScrollingList;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/02/2015, 11:10 PM.
 */
public class Manual extends Item {

    public Manual(){
        setTextureName("redstonic:Manual");
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName("Manual");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(mop!=null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            player.playSound(randomSound(), 1F, 1F);
            if (block == RedstonicBlocks.Modifier) {
                player.openGui(Redstonic.instance, 2, world, (int) player.posX, (int) player.posY, (int) player.posZ);
            } else {
                player.openGui(Redstonic.instance, 1, world, (int) player.posX, (int) player.posY, (int) player.posZ);
            }
        }else{
            player.playSound(randomSound(), 1, 1);
            player.openGui(Redstonic.instance, 1, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
        return stack;
    }

    public String randomSound(){
        String[] pages = {"pageflip1", "pageflip2", "pageflip3", "pageflip4"};
        Random random = new Random();
        int i = random.nextInt(pages.length);
        return "redstonic:" + pages[i];
    }

}
