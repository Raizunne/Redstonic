package com.raizu.redstonic.Client.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.HoverChecker;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ButtonPage extends GuiButton {

    int id, x, y; String direction;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");

    public ButtonPage(int id, int x, int y, int w, String text){
        super(id, x, y, w, 14, text);
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = 14;
        this.displayString = text;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        int xTexture = 56;
        int yTexture = 170;
        boolean hover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + 2*(this.width/3) && mouseY < this.yPosition + this.height;
        int l = 0x997A5C;
        mc.getTextureManager().bindTexture(texture);

        if (hover){
            xTexture = 56;
            yTexture = 185;
            l = 0x7D6457;
        }
        this.drawTexturedModalRect(this.x, this.y, xTexture, yTexture, this.width / 3, this.height);
        this.drawTexturedModalRect(this.x + this.width / 3, this.y, 256 - this.width / 3, yTexture, this.width /3, this.height);
        this.drawCenteredString(mc.fontRendererObj, this.displayString, this.x + this.width / 3, this.y + (this.height - 8) / 2, l);
    }

    @Override
    protected int getHoverState(boolean mouseOver) {
        return super.getHoverState(mouseOver);
    }

    //    public void func_146113_a(SoundHandler p_146113_1_){
//        String[] pages = {"pageflip1", "pageflip2", "pageflip3", "pageflip4"};
//        Random random = new Random();
//        int i = random.nextInt(pages.length);
//        p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("redstonic:" + pages[i]), 1.0F));
//    }
}
