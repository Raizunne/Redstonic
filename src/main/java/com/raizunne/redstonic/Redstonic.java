package com.raizunne.redstonic;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:47 PM.
 */

import com.raizunne.redstonic.Handler.GUIHandler;
import com.raizunne.redstonic.Handler.RedstonicEventHandler;
import com.raizunne.redstonic.Network.PacketDrill;
import com.raizunne.redstonic.Proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Redstonic.MODID, version = Redstonic.VERSION, dependencies = "required-after:CoFHCore;required-after:ThermalExpansion")
public class Redstonic {

    public static final String MODID = "Redstonic";
    public static final String VERSION = "1.01";

    @Mod.Instance
    public static Redstonic instance;
    @SidedProxy(clientSide = "com.raizunne.redstonic.Proxy.ClientProxy", serverSide = "com.raizunne.redstonic.Proxy.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;

    public static CreativeTabs redTab = new CreativeTabs("Redstonic"){
        @Override
        public Item getTabIconItem() {return RedstonicItems.RedDrill;}
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        network = NetworkRegistry.INSTANCE.newSimpleChannel("redstonic");
        Redstonic.network.registerMessage(PacketDrill.Handler.class, PacketDrill.class, 0, Side.SERVER);

        MinecraftForge.EVENT_BUS.register(new RedstonicEventHandler());

        RedstonicItems.init();
        RedstonicBlocks.init();
        RedstonicRecipes.init();
        proxy.initRenderers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
    }

    public void load(FMLInitializationEvent event){
        new GUIHandler();
    }

}
