package com.raizu.redstonic.Client.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Raizu on 23/10/2016.
 * as a part of Redstonic
 **/
public class ButtonWidget extends GuiButton {

    int id,x,y,u,v;
    public enum Type{TEXTURE,ICON;}
    public enum Direction{RIGHT,LEFT;}
    Type type;
    Direction dir;
    ItemStack stack;
    ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/widgets.png");

    public ButtonWidget(int buttonId, Direction dir, int x, int y, int u, int v) {
        super(buttonId, x, y, "ButtonIcon");
        this.id = buttonId;
        this.x=x;
        this.y=y;
        this.u=u;
        this.v=v;
        this.height=20;
        this.width=22;
        type=Type.TEXTURE;
        this.dir = dir;
    }

    public ButtonWidget(int buttonId, Direction dir, int x, int y, ItemStack stack) {
        super(buttonId, x, y, "ButtonIcon");
        this.id = buttonId;
        this.x=x;
        this.y=y;
        this.height=20;
        this.width=22;
        type=Type.ICON;
        this.dir = dir;
        this.stack = stack;
    }

    @Override
    protected int getHoverState(boolean mouseOver) {
        return super.getHoverState(mouseOver);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(!this.visible)return;
        mc.getTextureManager().bindTexture(texture);
        boolean hover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        int startingX = dir==Direction.RIGHT ? 46 : 0;
        drawTexturedModalRect(x, y, !hover?startingX : startingX+23, 0, 22, 20);
        if(type==Type.TEXTURE)
            drawTexturedModalRect(x+5, y+3, 1+(15*(u-1)), 226+(15*(v-1)), 14, 14);
        else
            mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x+4, y+1);
    }

    public void setIcon(int u, int v){
        this.u = u;
        this.v = v;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
    }

}
