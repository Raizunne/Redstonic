package com.raizunne.redstonic.Proxy;

import com.raizunne.redstonic.Client.Render.RenderDrillModifier;
import com.raizunne.redstonic.Client.RenderItem.ItemDrillModifier;
import com.raizunne.redstonic.RedstonicBlocks;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:50 PM.
 */
public class ClientProxy extends CommonProxy {

    public void initRenderers(){
        TileEntitySpecialRenderer modifier = new RenderDrillModifier();
        ClientRegistry.bindTileEntitySpecialRenderer(TEDrillModifier.class, modifier);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedstonicBlocks.Modifier), new ItemDrillModifier(modifier, new TEDrillModifier()));
    }

}
