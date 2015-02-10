package com.raizunne.redstonic.Gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/02/2015, 12:18 PM.
 */
public class ButtonToggle extends GuiButton{

    int x;
    int y;
    int id;
    int state;
    String hovText1;
    String hovText2;
    IIcon icon1;
    IIcon icon2;
    IIcon currentIcon;

    public ButtonToggle(int id, int x, int y, String text1, String text2, Item item1, Item item2){
        super(id, x, y, text1);
        this.x = x;
        this.y = y;
        this.id = id;
        this.hovText1 = text1;
        this.hovText2 = text2;
        this.icon1 = item1.getIconIndex(new ItemStack(item1));
        this.icon2 = item2.getIconIndex(new ItemStack(item2));
        this.width = 18;
        this.height = 18;
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

    @Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_) {if(this.visible) {
        int yTex; int xTex;
        ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/widgets.png");
        this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
        int hover = this.getHoverState(this.field_146123_n);
        mc.renderEngine.bindTexture(texture);
        if (hover==2){
            xTex = 21;
            yTex = 21;
        }else{
            xTex = 0;
            yTex = 21;
        }
        drawTexturedModalRect(this.x, this.y, xTex, yTex, 18, 18);
        GL11.glPushMatrix();
        mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        if(state==1){
            this.drawTexturedModelRectFromIcon(this.x+1, this.y+1, icon1, 16, 16);
        }else{
            this.drawTexturedModelRectFromIcon(this.x+1, this.y+1, icon2, 16, 16);
        }
        GL11.glPopMatrix();
    }}

    public void setState(int i){
        if(i>0 && i<3){
            this.state=i;
        }else{
            this.state=1;
        }

    }

    public void toggleState(){
        if(this.state==1){
            this.state=2;
        }else{
            this.state=1;
        }
    }

    public int getState(){
        return state;
    }
}
