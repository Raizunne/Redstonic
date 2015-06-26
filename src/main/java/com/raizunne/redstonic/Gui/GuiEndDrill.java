package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.DrillUtil;
import cpw.mods.fml.client.config.GuiSlider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of GuiEndDrill
 * on 25/06/2015, 11:49 PM.
 */
public class GuiEndDrill extends GuiScreen {

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/EndDrillGUI.png");
    EntityPlayer player;
    double speed;

    public final int xSizeofTexture = 176;
    public final int ySizeofTexture = 65;

    public GuiEndDrill(EntityPlayer player){
        this.player = player;
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        drawDefaultBackground();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(posX, posY, 0, 0, xSizeofTexture, ySizeofTexture);
        fontRendererObj.drawString("Drill Speed Modifier", posX + 40, posY + 16, 0x404040, false);
        super.drawScreen(x, y, f);
    }

    @Override
    public void initGui() {
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        GuiSlider slider = new GuiSlider(0, posX+18, posY + 30, 140, 20, "Speed ", "%", 0, 100, speed, true, true);
        buttonList.add(slider);
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        if(player.getHeldItem().getItem().equals(RedstonicItems.RedDrill) && DrillUtil.getHead(player.getHeldItem())==7) {
            speed = ((GuiSlider) buttonList.get(0)).getValue();
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Drill speed set to: " + Math.floor(speed) + "%"));
            player.getCurrentEquippedItem().stackTagCompound.setInteger("multiplier", (int)speed);
            System.out.println(player.getCurrentEquippedItem().stackTagCompound.getInteger("multiplier"));
        }
    }
}
