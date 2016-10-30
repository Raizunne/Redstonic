package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Client.Button.ButtonArrow;
import com.raizu.redstonic.Client.Button.ButtonMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class GUIManual extends GuiScreen {

    EntityPlayer player;
    ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    int posX, posY;

    public GUIManual(EntityPlayer player){
        this.player = player;
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
    }

    @Override
    public void drawBackground(int tint) {
    }

    @Override
    public void initGui() {
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        buttonList = new ArrayList<GuiButton>();
        ButtonArrow prev = new ButtonArrow(-2, posX+15, posY+150, ButtonArrow.Direction.LEFT);
        ButtonArrow next = new ButtonArrow(-1, posX+226, posY+150, ButtonArrow.Direction.RIGHT);
        List<ButtonMenu> mainMenu = new ArrayList<ButtonMenu>();
        int i=0;
        for (ManualCategory cat : ManualEntryRegistry.getCategories()){
            mainMenu.add(new ButtonMenu(3000+i, posX+140, posY+20+(i*12), cat.getName(), 0x990000, 0x660000));
            i++;
        }
        buttonList.addAll(mainMenu);
        buttonList.add(prev);
        buttonList.add(next);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id>=3000){
            System.out.println(ManualEntryRegistry.getCategories().get(button.id-3000).getName());
            ManualEntryRegistry.getCategories().get(button.id-3000).renderCategory(this);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        mc.getTextureManager().bindTexture(tex);
        drawTexturedModalRect(posX, posY, 0, 0, 256, 170);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
