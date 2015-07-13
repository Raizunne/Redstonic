package com.raizunne.redstonic.Gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/02/2015, 11:37 PM.
 */
public class ButtonMenu extends GuiButton {

    int id; int x; int y;
    String text;
    int color; int color2;
    boolean page;

    public ButtonMenu(int id, int x, int y, String text, int color, int color2, boolean page){
        super(id, x, y, Minecraft.getMinecraft().fontRenderer.getStringWidth(text), 12, text);
        this.id = id;
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.color2 = color2;
        this.page = page;
        this.width = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        this.height = 12;
    }

    public void drawButton(Minecraft mc, int p_146112_2_, int p_146112_3_) {if(this.visible){
        FontRenderer fontrenderer = mc.fontRenderer;
        int l = this.color;
        this.field_146123_n = p_146112_2_ >= this.x && p_146112_3_ >= this.y && p_146112_2_ < this.x + this.width && p_146112_3_ < this.y + this.height;
        int hover = this.getHoverState(this.field_146123_n);

        if (hover==2){
            l = this.color2;
        }
        fontrenderer.drawString(this.text, this.x + (hover == 2 ? 2 : 0), this.y + (this.height - 8) / 2, l);
    }}

    public int getHoverState(boolean bool){
        byte b0 = 1;
        if (!this.enabled){
            b0 = 0;
        }else if (bool){
            b0 = 2;
        }
        return b0;
    }

    public void func_146113_a(SoundHandler p_146113_1_){
        String[] pages = {"pageflip1", "pageflip2", "pageflip3", "pageflip4"};
        Random random = new Random();
        int i = random.nextInt(pages.length);
        if(page) {
            p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("redstonic:" + pages[i]), 1.0F));
            return;
        }
        p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
    }
}
