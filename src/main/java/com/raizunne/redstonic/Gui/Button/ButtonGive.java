package com.raizunne.redstonic.Gui.Button;

import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Raizunne as a part of Redstonic
 * on 10/07/2015, 06:39 PM.
 */
public class ButtonGive extends GuiButton {

    int id;
    int x;
    int y;
    EntityPlayer player;
    ItemStack stack; ItemStack stackDisplay;

    public ButtonGive(int id, int x, int y, EntityPlayer player, ItemStack stackDisplay, ItemStack stackGive) {
        super(id, x, y, "btn");
        this.id = id;
        this.x = x;
        this.y = y;
        this.player = player;
        this.stackDisplay = stackDisplay;
        this.stack = stackGive;
        this.width = 18;
        this.height = 18;
    }

    @Override
    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_) {if(this.visible) {
        super.drawButton(mc, p_146112_2_, p_146112_3_);
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
        IIcon icon = stackDisplay.getIconIndex();
        drawTexturedModelRectFromIcon(this.x + 1, this.y + 1, icon, 16, 16);
    }}

    @Override
    public boolean mousePressed(Minecraft p_146116_1_, int x, int y) {
        if(x>this.x && x<this.x+18 && y>this.y && y<this.y+18){
            player.inventory.addItemStackToInventory(stack);
        }
        return super.mousePressed(p_146116_1_, x, y);
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemStack getStackDisplay() {
        return stackDisplay;
    }
}
