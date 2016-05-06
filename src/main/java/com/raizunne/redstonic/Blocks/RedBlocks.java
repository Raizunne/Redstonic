package com.raizunne.redstonic.Blocks;

import com.raizunne.redstonic.Blocks.Butcher.Butcher;
import com.raizunne.redstonic.Blocks.Butcher.ItemButcher;
import com.raizunne.redstonic.Blocks.Butcher.TEButcher;
import com.raizunne.redstonic.Blocks.Shearer.Shearer;
import com.raizunne.redstonic.Blocks.Shearer.ShearerItem;
import com.raizunne.redstonic.Blocks.Shearer.TEShearer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Raizu on 03/05/2016.
 */
public class RedBlocks {

    public static Block shearer = new Shearer();
    public static ItemBlock shearerItem = new ShearerItem(shearer);
    public static Block butcher = new Butcher();
    public static ItemBlock butcherItem = new ItemButcher(butcher);

    public static void init() {
        GameRegistry.register(shearer);
        GameRegistry.register(shearerItem);
        GameRegistry.register(butcher);
        GameRegistry.register(butcherItem);
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TEShearer.class, "shearer");
        GameRegistry.registerTileEntity(TEButcher.class, "butcher");
    }

    public static void registerRenderers(){
        registerRender(shearer);
        registerRender(butcher);
    }

    public static void registerRender(Block block){
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
