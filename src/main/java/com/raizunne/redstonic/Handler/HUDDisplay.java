package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Block.DrillModifier;
import com.raizunne.redstonic.Item.Manual;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 09:59 PM.
 */
public class HUDDisplay {

    public static void init(RenderGameOverlayEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        ItemStack currentItem = player.inventory.getCurrentItem();
        if (player.worldObj.isRemote) {
            if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT) {
                return;
            }
            MovingObjectPosition mop = player.rayTrace(200, 1.0F);
            Block block = player.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);

            //MANUAL RENDER TO BLOCK
            if(player.getDistanceSq(mop.blockX + 0.5, mop.blockY + 0.5, mop.blockZ + 0.5) <= 25 && Util.getCurrentItem(RedstonicItems.ManualBook)) {
                if (block instanceof DrillModifier) {
                    int posx = (int)((event.resolution.getScaledWidth()/2)/0.8);
                    int posy = (int)((event.resolution.getScaledHeight()/2)/0.8);
                    GL11.glPushMatrix();
                    RenderItem.getInstance().renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, new ItemStack(RedstonicItems.ManualBook), (int)((posx+10)*0.8), (int)((posy-8)*0.8));
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glScalef(0.8F, 0.8F, 0.8F);
                    minecraft.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + "Manual Entry:" + EnumChatFormatting.RESET, posx + 33, posy - 8, 0x404040);
                    minecraft.fontRenderer.drawStringWithShadow(EnumChatFormatting.GREEN + "Drill Modifier" + EnumChatFormatting.RESET, posx+33, posy+2, 0x404040);
                    GL11.glScalef(1F, 1F, 1F);
                    GL11.glPopMatrix();
                }
            }
            if(Util.getCurrentItem(RedstonicItems.RedDrill)){
                if(player.isSneaking()) {
                    int posx = (int)((event.resolution.getScaledWidth()/2)/0.8);
                    int posy = (int)((event.resolution.getScaledHeight()/2)/0.8);
                    NBTTagCompound nbt = currentItem.stackTagCompound;
                    if((nbt.getInteger("aug1")==4 || nbt.getInteger("aug2")==4 || nbt.getInteger("aug3")==4) && nbt.getInteger("placeBlock")!=0){
                        RenderItem.getInstance().renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, new ItemStack(Block.getBlockById(currentItem.stackTagCompound.getInteger("placeBlock")), 0, currentItem.stackTagCompound.getInteger("placeBlockMeta")), (event.resolution.getScaledWidth() / 2) + 93, event.resolution.getScaledHeight() - 20);
                    }
                }
            }
        }
    }

}
