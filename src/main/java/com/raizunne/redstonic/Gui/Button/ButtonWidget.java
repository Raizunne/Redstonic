package com.raizunne.redstonic.Gui.Button;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/06/2015, 11:41 PM.
 */
public class ButtonWidget extends GuiButton {

    int x;
    int y;
    int id;
    String text;
    String direction;
    boolean enabled;
    RenderItem itemRender;
    ItemStack item;

    public ButtonWidget(int id, int x, int y, String text, String direction, Object item, RenderItem itemRender) {
        super(id, x, y, text);
        this.x = x;
        this.y = y;
        this.id = id;
        this.text = text;
        this.direction = direction;
        this.enabled = true;
        this.itemRender = itemRender;
        this.width = 22;
        this.height = 20;
        if(item instanceof Item){
            Item i = (Item)item;
            this.item = new ItemStack(i);
        }else if(item instanceof Block){
            Block i = (Block)item;
            this.item = new ItemStack(i);
        }
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

    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_){
        if (this.visible){
            int xTexture;
            int yTexture;
            int xItem;
            int yItem;
            ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/widgets.png");
            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            this.field_146123_n = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
            int hover = this.getHoverState(this.field_146123_n);
            Minecraft.getMinecraft().renderEngine.bindTexture(tex);
            boolean hovering = false;
            if (hover==2){
                if(direction.equals("left")){
                    xTexture = 0;
                    yTexture = 0;

                }else{
                    xTexture = 69;
                    yTexture = 0;
                }
            }else{
                if(direction.equals("left")){
                    xTexture = 23;
                    yTexture = 0;
                }else{
                    xTexture = 46;
                    yTexture = 0;
                }
            }

            if(direction.equals("left")){xItem = 4; yItem = 2;}else{xItem = 2; yItem = 2;}
            drawTexturedModalRect(this.x, this.y, xTexture, yTexture, 22, 20);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
            this.drawTexturedModelRectFromIcon(this.x + 4, this.y + 2, this.item.getIconIndex(), 16, 16);
        }
    }

}
