package com.raizu.redstonic.Items;

import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.Sword.Sword;
import com.raizu.redstonic.Items.Sword.SwordAugment;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Raizu on 14/10/2016.
 * as a part of Redstonic
 **/
public class RedItems {

    public static Drill drill;
    public static DrillHead drillHead;
    public static DrillBody drillBody;
    public static Battery battery;
    public static DrillAugment drillAugment;
    public static Sword sword;
    public static SwordBlade swordBlade;
    public static SwordHandle swordHandle;
    public static SwordAugment swordAugment;

    public static void init(){
        Random random = new Random();
        drillHead = new DrillHead();
        drillBody = new DrillBody();
        battery = new Battery();
        drillAugment = new DrillAugment();
        drill = new Drill(random.nextInt(drillHead.heads.length), random.nextInt(drillBody.bodies.length));
        GameRegistry.register(drill);
        GameRegistry.register(drillHead);
        GameRegistry.register(drillBody);
        GameRegistry.register(battery);
        GameRegistry.register(drillAugment);

        swordBlade = new SwordBlade();
        swordHandle = new SwordHandle();
        swordAugment = new SwordAugment();
        sword = new Sword(random.nextInt(swordBlade.blades.length), swordHandle.handles.length);
        GameRegistry.register(sword);
        GameRegistry.register(swordBlade);
        GameRegistry.register(swordHandle);
        GameRegistry.register(swordAugment);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenderers(){
        registerRender(drill);
        registerRender(sword);
        for (int i = 0; i < drillHead.headCount; i++) {
            registerRender(drillHead, i);
        }
        for (int i = 0; i < drillBody.bodyCount; i++) {
            registerRender(drillBody, i);
        }
        for (int i = 0; i < battery.batteryCount; i++) {
            registerRender(battery, i);
        }
        for (int i = 0; i < drillAugment.augments.length; i++) {
            registerRender(drillAugment, i);
        }
        for (int i = 0; i < swordBlade.blades.length; i++) {
            registerRender(swordBlade, i);
        }
        for (int i = 0; i < swordHandle.handles.length; i++) {
            registerRender(swordHandle, i);
        }
        for (int i = 0; i < swordAugment.augments.length; i++) {
            registerRender(swordAugment, i);
        }
    }

    public static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerRender(Item item, int metadata){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
