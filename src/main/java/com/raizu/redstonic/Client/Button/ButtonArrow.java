package com.raizu.redstonic.Client.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ButtonArrow extends GuiButton {

    public enum Direction{UP,DOWN,LEFT,RIGHT}
    int id,x,y;
    Direction dir;
    String text;

    public ButtonArrow(int id, int x, int y, Direction dir, String text){
        super(id, x, y, "Direction");
        this.id=id;
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.width = ((dir==Direction.UP || dir==Direction.DOWN) ? 10 : 18);
        this.height = ((dir==Direction.UP || dir==Direction.DOWN) ? 18 : 10);
        this.text =text;
    }

    public ButtonArrow(int id, int x, int y, Direction dir){
        this(id, x, y, dir, "");
    }

    ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/widgets.png");

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(!this.visible)return;
        int texX=0, texY=40;
        mc.getTextureManager().bindTexture(texture);

        if(dir==Direction.UP)texY=62;
        else if(dir==Direction.DOWN)texY=81;
        else if(dir==Direction.RIGHT)texY=40;
        else if(dir==Direction.LEFT)texY=51;
        boolean hover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        texX = hover ? width+1 : 0;
        drawTexturedModalRect(x, y, texX, texY, width, height);
        if(hover && !text.equals("")){
            List<String> st = new ArrayList<String>();
            st.add(text);
            GlStateManager.pushMatrix();
            GuiUtils.drawHoveringText(st, mouseX, mouseY, new ScaledResolution(mc).getScaledWidth(),new ScaledResolution(mc).getScaledHeight(), 200, Minecraft.getMinecraft().fontRendererObj);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        super.playPressSound(soundHandlerIn);
    }
}
