package com.raizu.redstonic.Items.Manual.Pages;

import com.raizu.redstonic.Client.Button.ButtonArrow;
import com.raizu.redstonic.Items.Manual.GUICategory;
import com.raizu.redstonic.Items.Manual.GUIManual;
import com.raizu.redstonic.Items.Manual.ManualEntry;
import com.raizu.redstonic.Items.Manual.ManualEntryRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualPage extends GuiScreen {

    int posX, posY;
    ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    ManualEntry parentEntry;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        mc.getTextureManager().bindTexture(tex);
        drawTexturedModalRect(posX, posY, 0, 0, 256, 170);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void setParentEntry(ManualEntry parentEntry) {
        this.parentEntry = parentEntry;
    }

    @Override
    public void initGui() {
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        buttonList = new ArrayList<GuiButton>();
        int pageIndex = parentEntry.getPagesList().indexOf(this);

        ButtonArrow ret = new ButtonArrow(-3, posX-2, posY-4, ButtonArrow.Direction.LEFT, "Return");
        ButtonArrow prev = new ButtonArrow(-2, posX+15, posY+150, ButtonArrow.Direction.LEFT, "Prev page");
        ButtonArrow next = new ButtonArrow(-1, posX+226, posY+150, ButtonArrow.Direction.RIGHT, "Next page");
        if(parentEntry.getPages().length<=1 || pageIndex==parentEntry.getPages().length-1){
            next.visible=false;
        }
        if(parentEntry.getPagesList().indexOf(this)==0)prev.visible=false;
        buttonList.add(prev);
        buttonList.add(next);
        buttonList.add(ret);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        int pageIndex = parentEntry.getPagesList().indexOf(this);
        switch (button.id){
            case -3:
                Minecraft.getMinecraft().displayGuiScreen(new GUICategory(ManualEntryRegistry.getEntriesByCategory(parentEntry.getCategory()), new GUIManual(Minecraft.getMinecraft().thePlayer)));
                break;
            case -2:
                if(pageIndex!=0)mc.displayGuiScreen(parentEntry.getPagesList().get(pageIndex-1));
                break;
            case -1:
                if((pageIndex)!=parentEntry.getPages().length-1)mc.displayGuiScreen(parentEntry.getPagesList().get(pageIndex+1));
                break;
        }
        super.actionPerformed(button);
    }
}
