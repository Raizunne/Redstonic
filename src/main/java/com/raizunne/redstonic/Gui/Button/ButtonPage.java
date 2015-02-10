package com.raizunne.redstonic.Gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 08/02/2015, 01:12 AM.
 */
public class ButtonPage extends GuiButton {

    int id; int x; int y; String direction;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");

    public ButtonPage(int id, int x, int y, int w, int h, String direction){
        super(id, x, y, w, h, direction);
        this.id = id;
        this.x = x;
        this.y = y;
        this.displayString = direction;
        this.height = h;
        this.width = w;
    }

    @Override
    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
        int xTexture = 56;
        int yTexture = 170;

        FontRenderer fontrenderer = p_146112_1_.fontRenderer;
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        this.field_146123_n = p_146112_2_ >= this.x && p_146112_3_ >= this.y && p_146112_2_ < this.x + this.width && p_146112_3_ < this.y + this.height;
        int hover = this.getHoverState(this.field_146123_n);
        int l = 0x997A5C;


        if (hover==2){
            xTexture = 56;
            yTexture = 185;
            l = 0x7D6457;
        }

        this.drawTexturedModalRect(this.x, this.y, xTexture, yTexture, this.width / 3, this.height);
        this.drawTexturedModalRect(this.x + this.width / 3, this.y, 256 - this.width / 3, yTexture, this.width / 3, this.height);
        this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 3, this.y + (this.height - 8) / 2, l);
    }

    public void func_146113_a(SoundHandler p_146113_1_){
        String[] pages = {"pageflip1", "pageflip2", "pageflip3", "pageflip4"};
        Random random = new Random();
        int i = random.nextInt(pages.length);
        p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("redstonic:" + pages[i]), 1.0F));
    }
}
