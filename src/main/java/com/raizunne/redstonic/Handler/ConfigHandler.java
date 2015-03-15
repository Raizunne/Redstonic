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



    public static void RedstonicConfig(Configuration config){

    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equals(Redstonic.MODID)){
            RedstonicConfig(Redstonic.configFile);
        }
    }
}
