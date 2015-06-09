package com.raizunne.redstonic.Gui.Button;

import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/06/2015, 09:31 PM.
 */
public class ButtonIcon extends GuiButton {

    int x;
    int y;
    int id;
    String hovText1;
    IIcon icon;
    ItemStack stack;

    public ButtonIcon(int id, int x, int y, String text, Item item){
        super(id, x, y, text);
        this.x = x;
        this.y = y;
        this.id = id;
        this.hovText1 = text;
        this.icon = item.getIconIndex(new ItemStack(item));
        this.stack = new ItemStack(item);
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

    public void changeIcon(Item item){
        this.icon = item.getIconIndex(new ItemStack(item));
    }

    public void changeIcon(ItemStack stack){
        this.icon = stack.getItem().getIconIndex(stack);
    }

    public void changeItem(ItemStack stack){
        this.stack = stack;
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
        if(stack.getItem() instanceof RedstonicDrill){
            RedstonicDrill drill = (RedstonicDrill)stack.getItem();
            for(int i=0; i<drill.getRenderPasses(0); i++){
                this.drawTexturedModelRectFromIcon(this.x+1, this.y+1, drill.getIcon(stack, i), 16, 16);
            }
        }else if(stack.getItem() instanceof RedstonicSword){
            RedstonicSword drill = (RedstonicSword)stack.getItem();
            for(int i=0; i<drill.getRenderPasses(0); i++){
                this.drawTexturedModelRectFromIcon(this.x+1, this.y+1, drill.getIcon(stack, i), 16, 16);
            }
        }else {
            this.drawTexturedModelRectFromIcon(this.x + 1, this.y + 1, icon, 16, 16);
        }
        GL11.glPopMatrix();
    }}
}
