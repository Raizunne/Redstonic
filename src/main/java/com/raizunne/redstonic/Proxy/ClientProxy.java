package com.raizunne.redstonic.Proxy;

import com.raizunne.redstonic.Client.Render.RenderDrillModifier;
import com.raizunne.redstonic.Client.RenderItem.ItemDrillModifier;
import com.raizunne.redstonic.RedstonicBlocks;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
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

    public static String version;

    public void initRenderers(){
        TileEntitySpecialRenderer modifier = new RenderDrillModifier();
        ClientRegistry.bindTileEntitySpecialRenderer(TEDrillModifier.class, modifier);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedstonicBlocks.Modifier), new ItemDrillModifier(modifier, new TEDrillModifier()));
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
        URL url = new URL("https://raw.githubusercontent.com/Raizunne/Redstonic/master/src/versions/latest.txt");
        URLConnection text = url.openConnection();
        text.setConnectTimeout(timeout);
        text.setReadTimeout(timeout);

        @SuppressWarnings("resource")
        Scanner scannerino = new Scanner(text.getInputStream());
        version = scannerino.nextLine();
    }

}
