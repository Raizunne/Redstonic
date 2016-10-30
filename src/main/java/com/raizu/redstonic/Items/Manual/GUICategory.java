package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Client.Button.ButtonArrow;
import com.raizu.redstonic.Client.Button.ButtonMenu;
import com.raizu.redstonic.Client.Button.ButtonPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class GUICategory extends GuiScreen {

    int posX, posY;
    ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    List items;
    GuiScreen prevScreen;

    public GUICategory(List items, GuiScreen prevScreen){
        this.items = items;
        this.prevScreen = prevScreen;
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
    public void initGui() {
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        ButtonArrow ret = new ButtonArrow(-3, posX-2, posY-4, ButtonArrow.Direction.LEFT, "Return");
        ButtonPage fac = new ButtonPage(-2, posX + 95, posY + 200, 50, "Return");
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) instanceof ManualCategory) {
                buttonList.add(new ButtonMenu(1337+i, posX+140, posY+20+(i*12), ((ManualCategory)items.get(i)).getName(), 0x990000, 0x660000));
            }
            else {
                buttonList.add(new ButtonMenu(1337+i, posX+140, posY+20+(i*12), ((ManualEntry)items.get(i)).getName(), 0x990000, 0x660000, ((ManualEntry)items.get(i)).stack));
            }
        }
        buttonList.add(ret);
        buttonList.add(fac);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id==-3)mc.displayGuiScreen(prevScreen);
        if(button.id==-2){
            ManualEntryRegistry.refreshData();
            System.out.println("REFRESHED DATA");
        }
        if(button.id>=1337){
            if(items.get(button.id-1337) instanceof ManualEntry) {
                try{
                    Minecraft.getMinecraft().displayGuiScreen(((ManualEntry) items.get(button.id-1337)).pages[0]);
                }catch(Exception e){
                    System.err.print(e);
                }
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
