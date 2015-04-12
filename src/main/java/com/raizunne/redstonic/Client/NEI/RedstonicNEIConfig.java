//package com.raizunne.redstonic.Client.NEI;
//
//import codechicken.nei.api.API;
//import codechicken.nei.api.IConfigureNEI;
//import com.raizunne.redstonic.Gui.GuiDrillModifier;
//import com.raizunne.redstonic.Handler.Recipe.RedstonicDrillRecipeHandler;
//import com.raizunne.redstonic.Redstonic;
//
///**
// * Created by Raizunne as a part of Redstonic
// * on 06/04/2015, 04:51 PM.
// */
//public class RedstonicNEIConfig implements IConfigureNEI {
//    @Override
//    public void loadConfig() {
//        API.registerRecipeHandler(new RedstonicDrillRecipeHandler());
//        API.registerUsageHandler(new RedstonicDrillRecipeHandler());
//        API.setGuiOffset(GuiDrillModifier.class, 0, 0);
//    }
//
//    @Override
//    public String getName() {
//        return "Redstonic";
//    }
//
//    @Override
//    public String getVersion() {
//        return Redstonic.VERSION;
//    }
//}
