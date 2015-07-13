package com.raizunne.redstonic.Handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

/**
 * Created by Raizunne as a part of Redstonic
 * on 12/07/2015, 06:32 PM.
 */
public class RedstonicWorldData extends WorldSavedData {

    private NBTTagCompound data = new NBTTagCompound();
    public static String id = "Redstonic";

    public RedstonicWorldData(){
        super(id);
    }

    public RedstonicWorldData(String tagName) {
        super(tagName);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        data = compound.getCompoundTag(id);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setTag(id, data);
    }

    public NBTTagCompound getData() {
        return data;
    }

    public void resetData(){
        data = new NBTTagCompound();
    }

    public void setData(NBTTagCompound data) {
        this.data = data;
    }

    public static void set(RedstonicWorldData data, World world){
        world.setItemData(id, data);
    }

    public static RedstonicWorldData get(World world){
        RedstonicWorldData data = ((RedstonicWorldData)world.loadItemData(RedstonicWorldData.class, id));
        if(data==null){
            data = new RedstonicWorldData();
            world.setItemData(id, data);
        }
        return data;
    }

}
