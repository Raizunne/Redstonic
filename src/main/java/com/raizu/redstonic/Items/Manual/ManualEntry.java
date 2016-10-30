package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Items.Manual.Pages.ManualPage;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualEntry {

    String unlocalName;
    ManualCategory category;
    ItemStack stack;
    boolean hasIcon;
    ManualPage[] pages;

    public ManualEntry(String unloclName, ManualCategory cat, ItemStack stack){
        this.unlocalName = unloclName;
        this.category = cat;
        ManualEntryRegistry.addEntry(this);
        hasIcon = false;
        this.stack = stack;
    }

    public String getName() {
        return StringUtils.localize("redstonic.entry."+unlocalName+".name");
    }

    public ManualCategory getCategory() {
        return category;
    }

    public ManualEntry setPages(ManualPage... pages){
        for (int i = 0; i < pages.length; i++) {
            pages[i].setParentEntry(this);
        }
        this.pages = pages;
        return this;
    }

    public ManualPage[] getPages() {
        return pages;
    }

    public List<ManualPage> getPagesList(){
        return Arrays.asList(pages);
    }

    public void renderEntry(GUIManual screen){
        GL11.glPushMatrix();
        screen.mc.fontRendererObj.drawString(getName(), screen.posX+18, screen.posY+12, 0x990000, false);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        screen.mc.fontRendererObj.drawSplitString(StringUtils.localize("redstonic.entry."+unlocalName), (int)((screen.posX+18)/0.8), (int)((screen.posY+25)/0.8), 135, 0);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix();
    }
}
