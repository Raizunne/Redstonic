package com.raizunne.redstonic.Gui.Button;

import cpw.mods.fml.client.GuiScrollingList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of ScrollBar
 * on 26/06/2015, 01:46 PM.
 */
public class ScrollBar extends GuiButton {

    int id; int x; int y;
    int height; String name;
    int maxValue; int value;
    int percentage; boolean hold;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/Scroll.png");

    public ScrollBar(int id, int x, int y, int height, int value, int maxValue, String name) {
        super(id, x, y, 14, height, name);
        this.id = id;
        this.x = x;
        this.y = y;
        this.height = height;
        this.name = name;
        this.value = value;
        this.maxValue = maxValue;
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y) {
        this.field_146123_n = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
        int hover = this.getHoverState(this.field_146123_n);
        mc.renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        drawTexturedModalRect(this.x, this.y, 0,0, 14, 2);
        drawTexturedModalRect(this.x, this.y+2, 15, 0, 14, this.height-4);
        drawTexturedModalRect(this.x, this.y+this.height-2, 0, 3, 14, 2);

        if(this.hold){
            this.value = y-this.y-7;
            if(this.value>=this.height-17){
                this.value=this.height-17;
            }else if(this.value<=0){
                this.value=0;
            }
        }
//        System.out.println(Mouse.getDWheel());
//        if(Mouse.getDWheel()>0){
//            this.value -=3;
//            if(this.value>=this.height-17){
//                this.value=this.height-17;
//            }else if(this.value<=0){
//                this.value=0;
//            }
//        }else if(Mouse.getDWheel()<0){
//            this.value +=3;
//            System.out.println("mousedown");
//            if(this.value>=this.height-17){
//                this.value=this.height-17;
//            }else if(this.value<=0){
//                this.value=0;
//            }
//        }
        drawTexturedModalRect(this.x + 1, this.y + 1 + value, 30, 0, 12, 15);
    }

    public int getValue(){
        int max = this.height-17;
        return (this.maxValue*this.value)/max;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int x, int y) {
        if((x>this.x && x<this.x+14) && (y>this.y && y<this.y+height)){
            this.hold = true;
        }
        return super.mousePressed(mc, x, y);
    }

    @Override
    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {

    }

    @Override
    public void mouseReleased(int x, int y) {
        this.hold = false;
    }

    @Override
    public void func_146113_a(SoundHandler p_146113_1_){

    }
}
