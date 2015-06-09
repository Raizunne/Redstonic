package com.raizunne.redstonic.Handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

/**
 * Created by Raizunne as a part of Redstonic
 * on 09/06/2015, 01:17 PM.
 */
public class WorldSaveHandler extends WorldSavedData {

    private static final String IDENTIFIER = "redstonic";

    public WorldSaveHandler(){
        super(IDENTIFIER);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_76184_1_) {

    }

    @Override
    public void writeToNBT(NBTTagCompound p_76187_1_) {

    }
}
