package com.raizunne.redstonic.Handler;

import com.google.common.eventbus.Subscribe;
import com.raizunne.redstonic.Block.BlockLooking;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

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
}
