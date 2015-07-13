package com.raizunne.redstonic.Gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.raizunne.redstonic.Gui.Button.ButtonDirectional.Type.*;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 01:12 AM.
 */
public class ButtonDirectional extends GuiButton{

    public static enum Type{
        LEFT_CURVED(18,10,0,51,19,51), RIGHT_CURVED(18,10,0,40,19,40), LEFT_STRAIGHT(13,9,38,40,52,40), RIGHT_STRAIGHT(13,9,38,50,52,50),
        UP_STRAIGHT(9,13,66,40,76,40), DOWN_STRAIGHT(9,13,86,40,96,40);
        int width; int height;
        int x1; int y1;
        int x2; int y2;
        Type(int width, int height, int x1, int y1, int x2, int y2){
            this.width=width; this.height=height;
            this.x1=x1; this.x2 = x2;
            this.y1=y1; this.y2=y2;
        }
    }

    int id; int x; int y; Type direction;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/widgets.png");

    public ButtonDirectional(int id, int x, int y, Type direction){
        super(id, x, y, "btn");
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.height = direction.height;
        this.width = direction.width;
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

        if (hover==2){ //HOVERING
            xTex = direction.x2;
            yTex = direction.y2;
        }else{ //NOT HOVERING
            xTex = direction.x1;
            yTex = direction.y1;
        }
        drawTexturedModalRect(this.x, this.y, xTex, yTex, direction.width, direction.height);
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
