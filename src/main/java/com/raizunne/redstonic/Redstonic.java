package com.raizunne.redstonic;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:47 PM.
 */

import com.raizunne.redstonic.Handler.ConfigHandler;
import com.raizunne.redstonic.Handler.GUIHandler;
import com.raizunne.redstonic.Handler.RedstonicEventHandler;
import com.raizunne.redstonic.Item.RedstonicContainer;
import com.raizunne.redstonic.Network.PacketDrill;
import com.raizunne.redstonic.Network.PacketDriller;
import com.raizunne.redstonic.Proxy.CommonProxy;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import com.raizunne.redstonic.TileEntity.TEHyperSmelter;
import com.raizunne.redstonic.Util.EIOHelper;
import com.raizunne.redstonic.Util.XML;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Redstonic.MODID, version = Redstonic.VERSION, dependencies = "after:ThermalExpansion;after:EnderIO")
public class Redstonic {

    public static final String MODID = "Redstonic";
    public static final String VERSION = "1.4.7";

    @Mod.Instance
    public static Redstonic instance;
    @SidedProxy(clientSide = "com.raizunne.redstonic.Proxy.ClientProxy", serverSide = "com.raizunne.redstonic.Proxy.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Configuration configFile;

    public static CreativeTabs redTab = new CreativeTabs("Redstonic"){
        @Override
        public Item getTabIconItem() {return RedstonicItems.RedDrill;}
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){

        network = NetworkRegistry.INSTANCE.newSimpleChannel("redstonic");
        Redstonic.network.registerMessage(PacketDrill.Handler.class, PacketDrill.class, 0, Side.SERVER);
        Redstonic.network.registerMessage(PacketDriller.Handler.class, PacketDriller.class, 1, Side.SERVER);

        MinecraftForge.EVENT_BUS.register(new RedstonicEventHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new RedstonicContainer());

        if(Loader.isModLoaded("EnderIO")){
            EIOHelper.init();
        }

        RedstonicItems.init();
        RedstonicBlocks.init();
        RedstonicRecipes.init();
        proxy.initRenderers();

        GameRegistry.registerTileEntity(TEDrillModifier.class, "TEDrillModifier");
        GameRegistry.registerTileEntity(TEDriller.class, "TEDriller");
        GameRegistry.registerTileEntity(TEHyperSmelter.class, "TEHyperSmelter");

        configFile = new Configuration(e.getSuggestedConfigurationFile());
        configFile.load();
        ConfigHandler.RedstonicConfig(configFile);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
    }

    public void load(FMLInitializationEvent event){
        new GUIHandler();

    }

}
