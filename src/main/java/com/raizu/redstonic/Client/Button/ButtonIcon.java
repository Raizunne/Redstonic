package com.raizu.redstonic.Client.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class ButtonIcon extends GuiButton {

    int id,x,y,u,v;
    public enum Status{BUILD,DECONSTRUCT;}
    Status status;
    ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/widgets.png");

    public ButtonIcon(int buttonId, int x, int y, int u, int v) {
        super(buttonId, x, y, "ButtonIcon");
        this.id = buttonId;
        this.x=x;
        this.y=y;
        this.u=u;
        this.v=v;
        this.height=18;
        this.width=18;
        status=Status.BUILD;
    }

    @Override
    protected int getHoverState(boolean mouseOver) {
        return super.getHoverState(mouseOver);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(!this.visible)return;
        mc.getTextureManager().bindTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        boolean hover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        drawTexturedModalRect(x, y, hover?21 : 0, 21, 18,18);
        drawTexturedModalRect(x+2, y+2, 1+(15*(u-1)), 226+(15*(v-1)), 14, 14);
    }

    public void setIcon(int u, int v){
        this.u = u;
        this.v = v;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
    }
}
