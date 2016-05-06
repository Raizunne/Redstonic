package com.raizunne.redstonic.Items;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Raizu on 05/05/2016, 12:47 AM.
 */
public class RedItems {

    public static Item rangeAugment = new Augment("Range", " - Adds +1 to the machine radius.", " - Adds energy usage.");
    public static Item efficiencyAugment = new Augment("Efficiency", " - Lowers machine's timer.", " - Adds energy usage.");

    public static void init(){
        GameRegistry.register(rangeAugment);
        GameRegistry.register(efficiencyAugment);
    }

    public static void registerRenderers(){
        registerRender(efficiencyAugment);
        registerRender(rangeAugment);

    }

    public static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
