package com.raizunne.redstonic;

import com.raizunne.redstonic.Blocks.RedBlocks;
import com.raizunne.redstonic.Handler.GuiHandler;
import com.raizunne.redstonic.Handler.RedstonicEventHandler;
import com.raizunne.redstonic.Items.RedItems;
import com.raizunne.redstonic.Network.CommonProxy;
import com.raizunne.redstonic.Network.Packet.ButcherPacket;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Redstonic.MODID, version = Redstonic.VERSION)
public class Redstonic {
    public static final String MODID = "Redstonic";
    public static final String VERSION = "2.0";

    @Mod.Instance
    public static Redstonic INSTANCE;
    @SidedProxy(clientSide = "com.raizunne.redstonic.Network.ClientProxy", serverSide = "com.raizunne.redstonic.Network.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;

    public static CreativeTabs redTab = new CreativeTabs("Redstonic"){
        @Override
        public Item getTabIconItem() {return Items.apple;}
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        Redstonic.network.registerMessage(ButcherPacket.Handler.class, ButcherPacket.class, 0, Side.SERVER);

        RedBlocks.init();
        RedBlocks.registerTileEntities();

        RedItems.init();

        MinecraftForge.EVENT_BUS.register(new RedstonicEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        proxy.registerRenderers();
    }
}
