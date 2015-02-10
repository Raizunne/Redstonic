package com.raizunne.redstonic.Gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 01:12 AM.
 */
public class ButtonDirectional extends GuiButton{

    int id; int x; int y; String direction;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");

    public ButtonDirectional(int id, int x, int y, String direction){
        super(id, x, y, 18, 10, direction);
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.height = 10;
        this.width = 18;
    }

    @Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_) {
        int xTex; int yTex;
        this.field_146123_n = p_146112_2_ >= this.x && p_146112_3_ >= this.y && p_146112_2_ < this.x + this.width && p_146112_3_ < this.y + this.height;
        int hover = this.getHoverState(this.field_146123_n);
        mc.renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (hover==2){
            if(direction=="right"){
                xTex=19; yTex=170;
            }else{
                xTex=19; yTex=181;
            }
        }else{
            if(direction=="right"){
                xTex=0; yTex=170;
            }else{
                xTex=0; yTex=181;
            }
        }
        drawTexturedModalRect(this.x, this.y, xTex, yTex, 18, 10);
    }

    public int getHoverState(boolean bool){
        byte b0 = 1;
        if (!this.enabled){
            b0 = 0;
        }else if (bool){
            b0 = 2;
        }
        return b0;
    }
}
