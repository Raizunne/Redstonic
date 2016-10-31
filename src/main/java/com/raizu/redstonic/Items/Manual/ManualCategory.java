package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Items.Manual.Pages.PageCategory;
import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualCategory {

    String unlocalName;
    List entries;
    ManualCategory cat;
    boolean inCat;
    ItemStack stack;

    public ManualCategory(String unlocalName){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        inCat = false;
        this.stack = null;
    }

    public ManualCategory(String unlocalName, ItemStack stack){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        inCat = false;
        this.stack = stack;
    }

    public ManualCategory(String unlocalName, ManualCategory cat){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        this.cat = cat;
        inCat = true;
        this.stack = null;
    }

    public ManualCategory(String unlocalName, ManualCategory cat, ItemStack stack){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        this.cat = cat;
        inCat = true;
        this.stack=stack;
    }

    public ManualCategory getCat() {
        return cat;
    }

    public boolean isInCat() {
        return inCat;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public String getName(){
        return StringUtils.localize("redstonic.category."+unlocalName);
    }
}
