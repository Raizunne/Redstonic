package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Redstonic;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/03/2015, 08:31 PM.
 */
public class ConfigHandler {

    public static int containerMax = 256;
    public static boolean redstonicSword = true;
    public static boolean redstonicDrill = true;
    public static boolean redstonicContainer = true;

    public static void RedstonicConfig(Configuration config){
        redstonicDrill = config.get("redstonic", "Enable Redstonic Drill", redstonicDrill).getBoolean(redstonicDrill);
        redstonicSword = config.get("redstonic", "Enable Redstonic Sword", redstonicSword).getBoolean(redstonicSword);
        redstonicContainer = config.get("redstonic", "Enable Redstonic Container", redstonicContainer).getBoolean(redstonicContainer);
        containerMax = config.getInt("Max Capacity", "container", containerMax, 0, 2560, "Max number of items in the Redstonic Container");
        if(config.hasChanged()){
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equals(Redstonic.MODID)){
            RedstonicConfig(Redstonic.configFile);
        }
    }
}
