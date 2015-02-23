package com.raizunne.redstonic;

import com.raizunne.redstonic.Item.Drill.DrillAugment;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.Drill.DrillHead;
import com.raizunne.redstonic.Item.Manual;
import com.raizunne.redstonic.Item.RedstonicDrill;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Raizunne as a part of Redstonic
 * on 04/02/2015, 06:20 PM.
 */
public class RedstonicItems {

    public static Item IronHead = new DrillHead(1);
    public static Item GoldHead = new DrillHead(2);
    public static Item DiamondHead = new DrillHead(3);
    public static Item HeavyHead = new DrillHead(4);
    public static Item FortuitousHead = new DrillHead(5);
    public static Item SilkyHead = new DrillHead(6);
    public static Item BlazerHead = new DrillHead(7);
    public static Item EndHead = new DrillHead(8);

    public static Item IronBody = new DrillBody(1);
    public static Item ElectrumBody = new DrillBody(2);
    public static Item EnderiumBody = new DrillBody(3);

    public static Item SpeedAugment = new DrillAugment(0);
    public static Item EnergyAugment = new DrillAugment(1);
    public static Item HotswapAugment = new DrillAugment(2);
    public static Item TorchAugment = new DrillAugment(3);

    public static Item RedDrill = new RedstonicDrill(Item.ToolMaterial.EMERALD);
    public static Item ManualBook = new Manual();

    public static void init(){
        GameRegistry.registerItem(IronHead, IronHead.getUnlocalizedName());
        GameRegistry.registerItem(GoldHead, GoldHead.getUnlocalizedName());
        GameRegistry.registerItem(DiamondHead, DiamondHead.getUnlocalizedName());
        GameRegistry.registerItem(HeavyHead, HeavyHead.getUnlocalizedName());
        GameRegistry.registerItem(FortuitousHead, FortuitousHead.getUnlocalizedName());
        GameRegistry.registerItem(SilkyHead, SilkyHead.getUnlocalizedName());
        GameRegistry.registerItem(BlazerHead, BlazerHead.getUnlocalizedName());
        GameRegistry.registerItem(EndHead, EndHead.getUnlocalizedName());

        GameRegistry.registerItem(IronBody, IronBody.getUnlocalizedName());
        GameRegistry.registerItem(ElectrumBody, ElectrumBody.getUnlocalizedName());
        GameRegistry.registerItem(EnderiumBody, EnderiumBody.getUnlocalizedName());

        GameRegistry.registerItem(SpeedAugment, SpeedAugment.getUnlocalizedName());
        GameRegistry.registerItem(EnergyAugment, EnergyAugment.getUnlocalizedName());
        GameRegistry.registerItem(HotswapAugment, HotswapAugment.getUnlocalizedName());

        GameRegistry.registerItem(RedDrill, RedDrill.getUnlocalizedName());
        GameRegistry.registerItem(ManualBook, ManualBook.getUnlocalizedName());
    }

}
