package com.raizu.redstonic;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Handler.EventHandler;
import com.raizu.redstonic.Handler.GUIHandler;
import com.raizu.redstonic.Items.Manual.ManualData;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Packet.PacketModifier;
import com.raizu.redstonic.Proxy.ClientProxy;
import com.raizu.redstonic.Proxy.CommonProxy;
import com.raizu.redstonic.Recipe.RedRecipes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/

@Mod(modid = Redstonic.MODID, version = Redstonic.VERSION, dependencies = "after:EnderIO")
public class Redstonic {
    public static final String MODID = "redstonic";
    public static final String VERSION = "1.5.2";

    @Mod.Instance
    public static Redstonic INSTANCE;
    @SidedProxy(clientSide = "com.raizu.redstonic.Proxy.ClientProxy", serverSide = "com.raizu.redstonic.Proxy.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;

    public static CreativeTabs redTab = new CreativeTabs("Redstonic") {
        @Override
        public Item getTabIconItem() {return RedItems.drill;}
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        Redstonic.network.registerMessage(PacketModifier.Handler.class, PacketModifier.class, 0, Side.SERVER);

        MinecraftForge.EVENT_BUS.register(new EventHandler());

        RedItems.init();
        RedBlocks.init();
        RedRecipes.init();
        RedBlocks.registerTileEntities();
        proxy.registerPreInit();

        Config.load(e);
        ClientProxy.checkNewVersion();

        if(!ClientProxy.newVersion.equals("0.0") && !ClientProxy.newVersion.equals(Redstonic.VERSION)){
            FMLLog.info("[REDSTONIC]: Redstonic is out of date!");
            FMLLog.info("[REDSTONIC]: What's new? - "+ClientProxy.newChangelog);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.registerRenderers();
        ManualData.initData();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
    }

    @Mod.EventHandler
    public void load(FMLPostInitializationEvent e){

    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e){

    }
}
