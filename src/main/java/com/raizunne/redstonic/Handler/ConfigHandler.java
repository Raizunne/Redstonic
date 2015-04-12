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

    public static void RedstonicConfig(Configuration config){
        containerMax = config.getInt("Redstonic Container Max Capacity", "Redstonic Container", containerMax, 0, 2560, "Max number of items in the Redstonic Container");
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
