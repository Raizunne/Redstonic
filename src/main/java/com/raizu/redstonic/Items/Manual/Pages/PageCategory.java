package com.raizu.redstonic.Items.Manual.Pages;

import com.raizu.redstonic.Client.Button.ButtonArrow;
import com.raizu.redstonic.Client.Button.ButtonMenu;
import com.raizu.redstonic.Client.Button.ButtonPage;
import com.raizu.redstonic.Items.Manual.ManualCategory;
import com.raizu.redstonic.Items.Manual.ManualData;
import com.raizu.redstonic.Items.Manual.ManualEntry;
import com.raizu.redstonic.Items.Manual.ManualEntryRegistry;
import com.sun.istack.internal.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemMap;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class PageCategory extends GuiScreen {

    int posX, posY;
    ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    ResourceLocation misc = new ResourceLocation("redstonic", "textures/gui/Misc.png");
    List items;
    ManualCategory cat;

    public PageCategory(ManualCategory cat){
        this.items = ManualEntryRegistry.getEntriesByCategory(cat);
        this.cat = cat;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        mc.getTextureManager().bindTexture(tex);
        drawTexturedModalRect(posX, posY, 0, 0, 256, 170);
        mc.getTextureManager().bindTexture(misc);
        drawTexturedModalRect(posX+20, posY+10, 0,0, 101, 117);
        GL11.glPushMatrix();
        GL11.glScaled(1.2F, 1.2F, 1.2F);
        mc.fontRendererObj.drawString(cat.getName(), (int)((posX+140)/1.2), (int)((posY+18)/1.2), 0x470101, false);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glScaled(1F, 1F, 1F);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        posX = (width - 256) / 2;
        posY = (height - 170) / 2;
        ButtonArrow ret = new ButtonArrow(-3, posX-2, posY-4, ButtonArrow.Direction.LEFT, "Return");
        ButtonPage fac = new ButtonPage(-2, posX + 95, posY + 100, 50, "Return");
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) instanceof ManualCategory) {
                buttonList.add(new ButtonMenu(1337+i, posX+140, posY+30+(i*12), ((ManualCategory)items.get(i)).getName(), 0x990000, 0x660000, ((ManualCategory)items.get(i)).getStack()));
            }
            else {
                buttonList.add(new ButtonMenu(1337+i, posX+140, posY+30+(i*12), ((ManualEntry)items.get(i)).getName(), 0x990000, 0x660000, ((ManualEntry)items.get(i)).getStack()));
            }
        }
        if(cat.isInCat())buttonList.add(ret);
        buttonList.add(fac);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton==1)actionPerformed(new GuiButton(-3, 10, 10,"back"));
        super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id==-3){
            GuiScreen toRender = cat.isInCat() ? new PageCategory(cat.getCat()) : new PageCategory(ManualData.mainMenu);
            mc.displayGuiScreen(toRender);
        }
        if(button.id==-2){
            ManualEntryRegistry.refreshData();
        }
        if(button.id>=1337){
            try{
                if(items.get(button.id-1337) instanceof ManualEntry) {
                    Minecraft.getMinecraft().displayGuiScreen(((ManualEntry) items.get(button.id-1337)).getPages()[0]);
                }else{
                    Minecraft.getMinecraft().displayGuiScreen(new PageCategory(((ManualCategory) items.get(button.id-1337))));
                }
            }catch(Exception e){
                System.err.print(e);
            }

        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
