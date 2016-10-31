package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Items.Manual.Pages.PageBase;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
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
    PageBase[] pages;

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

    public ManualEntry setPages(PageBase... pages){
        for (int i = 0; i < pages.length; i++) {
            pages[i].setParentEntry(this);
        }
        this.pages = pages;
        return this;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public PageBase[] getPages() {
        return pages;
    }

    public List<PageBase> getPagesList(){
        return Arrays.asList(pages);
    }
}
