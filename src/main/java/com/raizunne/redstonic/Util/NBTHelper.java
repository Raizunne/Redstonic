package com.raizunne.redstonic.Util;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraftforge.common.DimensionManager;

import java.io.*;

/**
 * Created by Raizunne as a part of Redstonic
 * on 09/07/2015, 05:36 PM.
 */
public class NBTHelper {

    public static NBTTagCompound getNBTFromUUID(String UUID){
        SaveHandler saveHandler = (SaveHandler) MinecraftServer.getServer().worldServers[0].getSaveHandler();
        try{
            File playerDir = new File(saveHandler.getWorldDirectory(), "playerdata");
            File playerFile = new File(playerDir, UUID+".dat");
            return CompressedStreamTools.readCompressed(new FileInputStream(playerFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeNBTFromUUID(NBTTagCompound tag, String UUID){
        ISaveHandler saveHandler = MinecraftServer.getServer().worldServers[0].getSaveHandler();
        try{
            File playerDir = new File(saveHandler.getWorldDirectory(), "playerdata");
            File temp = new File(playerDir, UUID+".dat.tmp");
            File playerFile = new File(playerDir, UUID+".dat");
            CompressedStreamTools.safeWrite(tag, temp);
            if(playerFile.exists()){
                playerFile.delete();
            }
            temp.renameTo(playerFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
