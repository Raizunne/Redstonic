package com.raizunne.redstonic.Util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizu on 05/05/2016, 09:33 PM.
 */
public class GuiToggleButton extends GuiButton {

    int x,y,id;
    boolean toggled;

    public GuiToggleButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(buttonText);
        this.height = 14;
        this.toggled = true;
        this.x = x;
        this.y = y;
        this.displayString = buttonText;
        this.id = buttonId;
    }

    public GuiToggleButton(int buttonId, int x, int y, int width, String buttonText){
        super(buttonId, x, y, buttonText);
        this.width = width;
        this.height = 14;
        this.toggled = true;
        this.x = x;
        this.y = y;
        this.displayString = buttonText;
        this.id = buttonId;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public boolean isToggled() {
        return toggled;
    }

    @Override
    public int getHoverState(boolean bool){
        if(this.enabled){
            return 0;
        }
        return bool ? 1 : 0;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/Widgets.png");
        int texY = this.toggled ? 14 : 28;
        int color = this.toggled ? 0xFFFFFF : 0x999999;
        if(!this.visible)return;
        mc.renderEngine.bindTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        texY = this.hovered ? texY+28 : texY;
        this.drawTexturedModalRect(this.x, this.y, 0, texY, 3, 12);
        this.drawTexturedModalRect(this.x+3, this.y, 2, texY, this.width, 12);
        this.drawTexturedModalRect(this.x+3+this.width, this.y, 197, texY, 3, 12);
        this.drawCenteredString(mc.fontRendererObj, displayString, this.x+3+(this.width/2), this.y+(this.height/2)-5, color);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        if(hovered){
            System.out.println("CLICKED");
            this.toggled=!this.toggled;
            return true;
        }else{
            return false;
        }
    }
}
