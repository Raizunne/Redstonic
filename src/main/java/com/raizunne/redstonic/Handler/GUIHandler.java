package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Gui.*;
import com.raizunne.redstonic.Gui.Container.ContainerDrillModifier;
import com.raizunne.redstonic.Gui.Container.ContainerDriller;
import com.raizunne.redstonic.Gui.Container.ContainerHyperSmelter;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import com.raizunne.redstonic.TileEntity.TEHyperSmelter;
import com.raizunne.redstonic.Util.DrillUtil;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:15 PM.
 */
public class GUIHandler implements IGuiHandler{

    public GUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Redstonic.instance, this);
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0: if(te!=null && te instanceof TEDrillModifier){
                return new ContainerDrillModifier(player.inventory, (TEDrillModifier)te);
            }
            break;
            case 1: return null;
            case 2: return null;
            case 3: if(te!=null && te instanceof TEDriller){
                return new ContainerDriller(player.inventory, (TEDriller)te);
            }
            break;
            case 4: if(te!=null && te instanceof TEHyperSmelter){
                return new ContainerHyperSmelter(player.inventory, (TEHyperSmelter)te);
            }
            break;
            default: return null;

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0: if(te!=null && te instanceof TEDrillModifier){
                return new GuiDrillModifier(player.inventory, (TEDrillModifier)te);
            }
            break;
            case 1: return new GuiManual(player, "index");
            case 2: return new GuiManual(player, "Getting Started");
            case 3: if(te!=null && te instanceof TEDriller){
                return new GuiDriller(player.inventory, (TEDriller)te);
            }
            break;
            case 4: if(te!=null && te instanceof TEHyperSmelter){
                return new GuiHyperSmelter(player.inventory, (TEHyperSmelter)te);
            }
            break;
            case 5: if(player.getCurrentEquippedItem().getItem().equals(RedstonicItems.RedDrill)){
                return new GuiEndDrill(player);
            }
            default: return null;
        }
        return null;
    }
}
