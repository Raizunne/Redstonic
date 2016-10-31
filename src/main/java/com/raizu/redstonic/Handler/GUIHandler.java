package com.raizu.redstonic.Handler;

import com.raizu.redstonic.Blocks.Modifier.ContainerModifier;
import com.raizu.redstonic.Blocks.Modifier.GUIModifier;
import com.raizu.redstonic.Blocks.Modifier.TEModifier;
import com.raizu.redstonic.Items.Manual.Pages.PageCategory;
import com.raizu.redstonic.Items.Manual.ManualData;
import com.raizu.redstonic.Items.Manual.ManualEntryRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class GUIHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch(ID){
            case 0: if(te!=null && te instanceof TEModifier){
                return new ContainerModifier(player.inventory, (TEModifier) te);
            }
            break;
            case 1: return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch(ID){
            case 0: if(te!=null && te instanceof TEModifier){
                return new GUIModifier(player.inventory, (TEModifier) te);
            }
            break;
            case 1: return new PageCategory(ManualData.mainMenu);
        }
        return null;
    }

}
