package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Blocks.Butcher.ContainerButcher;
import com.raizunne.redstonic.Blocks.Butcher.GuiButcher;
import com.raizunne.redstonic.Blocks.Butcher.TEButcher;
import com.raizunne.redstonic.Blocks.Shearer.ContainerShearer;
import com.raizunne.redstonic.Blocks.Shearer.GuiShearer;
import com.raizunne.redstonic.Blocks.Shearer.TEShearer;
import com.raizunne.redstonic.Redstonic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Raizu on 04/05/2016.
 */
public class GuiHandler implements IGuiHandler {
    public GuiHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Redstonic.INSTANCE, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID){
            case 0: if(te!=null && te instanceof TEShearer){
                return new ContainerShearer(player.inventory, (TEShearer)te);
            }
            break;
            case 1: if(te!=null && te instanceof TEButcher){
                return new ContainerButcher(player.inventory, (TEButcher)te);
            }
            break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch(ID){
            case 0: if(te!=null && te instanceof TEShearer){
                return new GuiShearer(player.inventory, (TEShearer)te);
            }
            break;
            case 1: if(te!=null && te instanceof TEButcher){
                return new GuiButcher(player.inventory, (TEButcher)te);
            }
            break;
        }
        return null;
    }
}
