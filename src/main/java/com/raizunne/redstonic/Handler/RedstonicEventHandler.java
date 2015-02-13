package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Client.BlockLooking;
import com.raizunne.redstonic.Item.RedstonicDrill;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.BlockEvent;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 01:34 PM.
 */
public class RedstonicEventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event){
        BlockLooking.init(event);
    }

    @SubscribeEvent
    public void BlazerBlaze(BlockEvent.HarvestDropsEvent event){
        if (event.harvester != null) {
            if (event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() instanceof RedstonicDrill && event.harvester.getHeldItem().stackTagCompound.getInteger("head")==6 &&
                    !(event.block instanceof BlockLog)) {
                ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));
                if (stack != null) {
                    MovingObjectPosition mop = event.harvester.rayTrace(200, 1.0F);
                    event.drops.clear();
                    event.drops.add(stack.copy());
                }
            }
        }
    }
}
