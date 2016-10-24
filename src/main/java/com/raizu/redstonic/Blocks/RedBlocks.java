package com.raizu.redstonic.Blocks;

import com.raizu.redstonic.Blocks.Cone.Cone;
import com.raizu.redstonic.Blocks.Cone.ConeItem;
import com.raizu.redstonic.Blocks.Modifier.Modifier;
import com.raizu.redstonic.Blocks.Modifier.ModifierItem;
import com.raizu.redstonic.Blocks.Modifier.TEModifier;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class RedBlocks {

    public static Block cone = new Cone();
    public static ItemBlock coneItem = new ConeItem(cone);
    public static Block modifier = new Modifier();
    public static ItemBlock modifierItem = new ModifierItem(modifier);


    public static void init(){
        GameRegistry.register(cone);
        GameRegistry.register(coneItem);
        GameRegistry.register(modifier);
        GameRegistry.register(modifierItem);
    }


    public static void registerRenderers(){
        registerRender(cone);
        registerRender(modifier);
    }

    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TEModifier.class, "TEModifier");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Block block){
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        ModelLoader.setCustomStateMapper(block, new DefaultStateMapper());
    }

}
