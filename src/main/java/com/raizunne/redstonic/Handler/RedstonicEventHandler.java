package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Proxy.ClientProxy;
import com.raizunne.redstonic.Redstonic;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
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
    public void renderTick(TickEvent.RenderTickEvent event){
        Minecraft mc = Minecraft.getMinecraft();
        GuiScreen screen = mc.currentScreen;
        if(mc.theWorld!=null){
            WorldClient world = mc.theWorld;
            if(event.phase == TickEvent.Phase.START){
                if(screen instanceof GuiRepair){

                }
            }
        }
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
                ClientProxy.checkLink();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ClientProxy.version!="" && !ClientProxy.version.equals(Redstonic.VERSION) && ClientProxy.version!="0.0") {
                if(Minecraft.getMinecraft().theWorld.isRemote) {
                    String beg = EnumChatFormatting.RED + "Redstonic " + EnumChatFormatting.WHITE + "Outdated! New version is " + EnumChatFormatting.YELLOW + ClientProxy.version + EnumChatFormatting.WHITE;
                    IChatComponent component = IChatComponent.Serializer.func_150699_a("[\"\",{\"text\":\"Redstonic \",\"color\":\"red\",\"bold\":\"true\"},{\"text\":\"outdated! New version is " + ClientProxy.version + "." + "\",\"color\":\"none\",\"bold\":\"false\"},{\"text\":\" [Download]\",\"color\":\"gold\",\"bold\":\"true\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + ClientProxy.downloadLink + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Open CurseForge page.\"}]}}}]");
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(component);
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Changes: " + EnumChatFormatting.WHITE + ClientProxy.newChangelog));
                }
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
