package com.raizunne.redstonic.Client;

import com.raizunne.redstonic.Block.DrillModifier;
import com.raizunne.redstonic.Item.Manual;
import com.raizunne.redstonic.RedstonicItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import scala.tools.reflect.StdRuntimeTags;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 09:59 PM.
 */
public class BlockLooking {

    public static void init(RenderGameOverlayEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player.worldObj.isRemote) {
            MovingObjectPosition mop = player.rayTrace(200, 1.0F);
            Block block = player.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            if(player.getDistanceSq(mop.blockX + 0.5, mop.blockY + 0.5, mop.blockZ + 0.5) <= 25 && player.inventory.getCurrentItem()!=null && player.inventory.getCurrentItem().getItem() instanceof Manual) {
                if (block instanceof DrillModifier) {
                    int posx = (int)((event.resolution.getScaledWidth()/2)/0.8);
                    int posy = (int)((event.resolution.getScaledHeight()/2)/0.8);
                    if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT) {
                        return;
                    }
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
        }
    }

}
