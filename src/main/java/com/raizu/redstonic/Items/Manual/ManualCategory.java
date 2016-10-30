package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.client.gui.GuiScreen;

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

    public ManualCategory(String unlocalName){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        inCat = false;
    }

    public ManualCategory(String unlocalName, ManualCategory cat){
        this.unlocalName = unlocalName;
        ManualEntryRegistry.addCategory(this);
        this.cat = cat;
        inCat = true;
    }

    public String getName(){
        return StringUtils.localize("redstonic.category."+unlocalName);
    }

    public void renderCategory(GUIManual screen){
        screen.mc.displayGuiScreen(new GUICategory(ManualEntryRegistry.getEntriesByCategory(this), screen));
    }
}
