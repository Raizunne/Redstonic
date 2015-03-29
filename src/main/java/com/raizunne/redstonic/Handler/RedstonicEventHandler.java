package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Proxy.ClientProxy;
import com.raizunne.redstonic.Redstonic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.lwjgl.input.Mouse;

import java.net.InetAddress;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 01:34 PM.
 */
public class RedstonicEventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event){
//        HUDDisplay.init(event);
    }

    @SubscribeEvent
    public void BlazerBlaze(BlockEvent.HarvestDropsEvent event){
//        System.out.println(event.block);
        if (event.harvester != null) {
            if (event.harvester.getHeldItem() != null && event.harvester.getHeldItem().getItem() instanceof RedstonicDrill && event.harvester.getHeldItem().stackTagCompound.getInteger("head")==6 &&
                    !(event.block instanceof BlockLog)) {
                ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(event.block, 1, event.blockMetadata));
                if(event.block == Blocks.stone){
                    stack = new ItemStack(Blocks.stone, 1, event.blockMetadata);
                }
                if (stack != null) {
                    event.drops.clear();
                    event.drops.add(stack.copy());
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void LoginEvent(EntityJoinWorldEvent event) {
        if (event.world.isRemote && event.entity == Minecraft.getMinecraft().thePlayer) {
            try {
                ClientProxy.checkVersion();
                ClientProxy.newVersionChangelog();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ClientProxy.version!="" && !ClientProxy.version.equals(Redstonic.VERSION) && ClientProxy.version!="0.0") {
                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Redstonic " + EnumChatFormatting.WHITE + "Outdated! New version is " + EnumChatFormatting.YELLOW + ClientProxy.version));
                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Changes: " + EnumChatFormatting.WHITE + ClientProxy.newChangelog));
            }else {
                System.out.println("[REDSTONIC] Using latest version.");
            }
        }
    }

    @SubscribeEvent
    public void LivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)event.entity;
        }
    }
}
