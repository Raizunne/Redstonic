package com.raizunne.redstonic.Proxy;

import com.google.gson.Gson;
import com.raizunne.redstonic.Client.Render.RenderDrillModifier;
import com.raizunne.redstonic.Client.Render.RenderDriller;
import com.raizunne.redstonic.Client.RenderItem.ItemContainer;
import com.raizunne.redstonic.Client.RenderItem.ItemDrillModifier;
import com.raizunne.redstonic.Client.RenderItem.ItemDriller;
import com.raizunne.redstonic.RedstonicBlocks;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:50 PM.
 */
public class ClientProxy extends CommonProxy {

    public static String version = "0.0";
    public static String newChangelog = "0.0";
    public static String downloadLink = "http://minecraft.curseforge.com/mc-mods/227716-redstonic";

    public void initRenderers(){
        //MODIFIER
        TileEntitySpecialRenderer modifier = new RenderDrillModifier();
        ClientRegistry.bindTileEntitySpecialRenderer(TEDrillModifier.class, modifier);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedstonicBlocks.Modifier), new ItemDrillModifier(modifier, new TEDrillModifier()));

        //DRILLER
        TileEntitySpecialRenderer driller = new RenderDriller();
        ClientRegistry.bindTileEntitySpecialRenderer(TEDriller.class, driller);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedstonicBlocks.Driller), new ItemDriller(driller, new TEDriller()));

        //CONTAINER
        MinecraftForgeClient.registerItemRenderer(RedstonicItems.RedContainer, new ItemContainer());
    }

    public static void checkVersion() throws Exception{
        int timeout = 10000;
        URL url = new URL("https://raw.githubusercontent.com/Raizunne/Redstonic/master/src/versions/latest.txt");
        URLConnection text = url.openConnection();
        text.setConnectTimeout(timeout);
        text.setReadTimeout(timeout);

        @SuppressWarnings("resource")
        Scanner scannerino = new Scanner(text.getInputStream());
        version = scannerino.nextLine();
    }

    public static void newVersionChangelog() throws Exception{
        int timeout = 10000;
        URL url = new URL("https://raw.githubusercontent.com/Raizunne/Redstonic/master/src/versions/changelog.txt");
        URLConnection text = url.openConnection();
        text.setConnectTimeout(timeout);
        text.setReadTimeout(timeout);

        @SuppressWarnings("resource")
        Scanner scannerino = new Scanner(text.getInputStream());
        newChangelog = scannerino.nextLine();
    }

    public static void checkLink() throws Exception{
        int timeout = 10000;
        URL url = new URL("https://raw.githubusercontent.com/Raizunne/Redstonic/master/src/versions/link.txt");
        URLConnection text = url.openConnection();
        text.setConnectTimeout(timeout);
        text.setReadTimeout(timeout);

        @SuppressWarnings("resource")
        Scanner scannerino = new Scanner(text.getInputStream());
        downloadLink = scannerino.nextLine();
    }
}