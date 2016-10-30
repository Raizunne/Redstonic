package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Redstonic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class Manual extends Item{

    public Manual(){
        setUnlocalizedName("RedstonicManual");
        setRegistryName("RedstonicManual");
        setCreativeTab(Redstonic.redTab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(!playerIn.isSneaking()){
            playerIn.openGui(Redstonic.INSTANCE, 1, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
