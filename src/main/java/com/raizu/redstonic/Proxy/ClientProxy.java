package com.raizu.redstonic.Proxy;

import com.raizu.redstonic.Blocks.RedBlocks;
import com.raizu.redstonic.Handler.Config;
import com.raizu.redstonic.Items.RedItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class ClientProxy extends CommonProxy{

    public static String newVersion = "0.0";
    public static String newChangelog = "";
    public static boolean gotTold = false;

    public static void checkNewVersion(){
        if(!Config.noticeUpdate)return;
        int timeout = 10000;
        try {
            URL url = new URL("http://status.raizu.mx/redstonic/version");
            URL urlChangelog = new URL("http://status.raizu.mx/redstonic/changelog");
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            Scanner scanner = new Scanner(conn.getInputStream());
            newVersion = scanner.nextLine();
            conn = urlChangelog.openConnection();
            scanner = new Scanner(conn.getInputStream());
            newChangelog = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void registerRenderers() {
        RedItems.registerRenderers();
        RedBlocks.registerRenderers();
    }

    public void registerPreInit() {

    }


}
