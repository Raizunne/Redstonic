package com.raizu.redstonic.Client.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ButtonMenu extends GuiButton {

    int id, x, y, color, color2;
    String text;
    boolean page;
    ItemStack stack;

    public ButtonMenu(int id, int x, int y, String text, int color, int color2, ItemStack stack){
        super(id, x, y, Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)+8, 12, text);
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.color2 = color2;
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)+8;
        this.height = 12;
        this.stack = stack;
    }

    public ButtonMenu(int id, int x, int y, String text, int color, int color2){
        super(id, x, y, Minecraft.getMinecraft().fontRendererObj.getStringWidth(text), 12, text);
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.color2 = color2;
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
        this.height = 12;
        this.stack = null;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        FontRenderer fontrenderer = mc.fontRendererObj;
        int l = this.color;
        boolean hover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        if (hover){
            l = this.color2;
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableDepth();
        GlStateManager.pushMatrix();

        mc.getRenderItem().renderItemAndEffectIntoGUI(stack, (int)(this.xPosition/0.6)-6, (int)(this.yPosition/0.6)+2);

        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
        fontrenderer.drawString(this.text, 8+this.x + (hover ? 2 : 0), this.y + (this.height - 8) / 2, l);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        super.playPressSound(soundHandlerIn);
    }
}
