package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.Drill.DrillPart;
import com.raizu.redstonic.Items.Manual.Pages.PageRecipeText;
import com.raizu.redstonic.Recipe.RedRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualData {

    public static ManualCategory drillHeads;
    public static ManualCategory drillBodies;
    public static ManualCategory swordBlades;
    public static ManualCategory swordHandles;

    public static ManualEntry testEntry;

    public static ManualEntry ironHead;
    public static ManualEntry goldHead;
    public static ManualEntry diamondHead;
    public static ManualEntry fortuitousHead;
    public static ManualEntry blazerHead;
    public static ManualEntry silkyHead;
    public static ManualEntry heavyHead;
    public static ManualEntry endHead;

    public static ManualEntry ironBody;
    public static ManualEntry electrumBody;
    public static ManualEntry enderiumBody;
    public static ManualEntry energizedBody;
    public static ManualEntry vibrantBody;
    public static ManualEntry ultimateBody;

    public static ManualEntry ironBlade;
    public static ManualEntry diamondBlade;
    public static ManualEntry energizedBlade;
    public static ManualEntry vibrantBlade;
    public static ManualEntry electrumBlade;
    public static ManualEntry enderiumBlade;

    public static ManualEntry ironHandle;
    public static ManualEntry woodHandle;
    public static ManualEntry energizedHandle;
    public static ManualEntry vibrantHandle;
    public static ManualEntry electrumHandle;
    public static ManualEntry enderiumHandle;

    public static ManualEntry speedDrillAugment;
    public static ManualEntry speedIIDrillAugment;
    public static ManualEntry hotswapDrillAugment;
    public static ManualEntry heavyDrillAugmnet;

    public static ManualEntry berserkerSwordAugmnet;
    public static ManualEntry berserkerIISwordAugment;
    public static ManualEntry blazerSwordAugment;
    public static ManualEntry fortuitousSwordAugment;

    public static ManualEntry basicBattery;
    public static ManualEntry energizedBattery;
    public static ManualEntry greatBattery;

    public static void initData(){
        drillHeads = new ManualCategory("drillHeads");
        drillBodies = new ManualCategory("drillBodies");
        swordBlades = new ManualCategory("swordBlades");
        swordHandles = new ManualCategory("swordHandles");

        ironHead = new ManualEntry("ironHead", drillHeads, DrillHead.getHead(DrillPart.IRON_HEAD)).setPages(
                new PageRecipeText(RedRecipes.ironHead, "ironHead1")
        );
        goldHead = new ManualEntry("goldHead", drillHeads, DrillHead.getHead(DrillPart.GOLD_HEAD)).setPages(
                new PageRecipeText(RedRecipes.goldHead, "goldHead1")
        );
        diamondHead = new ManualEntry("diamondHead", drillHeads, DrillHead.getHead(DrillPart.DIAMOND_HEAD)).setPages(
                new PageRecipeText(RedRecipes.diamondHead, "diamondHead1")
        );
        heavyHead = new ManualEntry("heavyHead", drillHeads, DrillHead.getHead(DrillPart.HEAVY_HEAD)).setPages(
                new PageRecipeText(RedRecipes.heavyHead, "heavyHead1")
        );
        fortuitousHead = new ManualEntry("fortuitousHead", drillHeads, DrillHead.getHead(DrillPart.FORTUITOUS_HEAD)).setPages(
                new PageRecipeText(RedRecipes.fortuitousHead, "fortuitousHead1")
        );
        silkyHead = new ManualEntry("silkyHead", drillHeads, DrillHead.getHead(DrillPart.SILKY_HEAD)).setPages(
                new PageRecipeText(RedRecipes.silkyHead, "silkyHead1")
        );
        blazerHead = new ManualEntry("blazerHead", drillHeads, DrillHead.getHead(DrillPart.BLAZER_HEAD)).setPages(
                new PageRecipeText(RedRecipes.blazerHead, "blazerHead1")
        );
        endHead = new ManualEntry("endHead", drillHeads, DrillHead.getHead(DrillPart.ULTIMATE_HEAD)).setPages(
                new PageRecipeText(RedRecipes.endHead, "endHead1")
        );

        ironBody = new ManualEntry("ironBody", drillBodies, DrillBody.getBody(DrillPart.IRON_BODY));
        if(OreDictionary.getOres("ingotElectrum").size()>0)electrumBody = new ManualEntry("electrumBody", drillBodies, DrillBody.getBody(DrillPart.ELECTRUM_BODY));
        if(OreDictionary.getOres("ingotEnderium").size()>0)enderiumBody = new ManualEntry("enderiumBody", drillBodies, DrillBody.getBody(DrillPart.ENDERIUM_BODY));
        if(OreDictionary.getOres("ingotEnergeticAlloy").size()>0)energizedBody = new ManualEntry("energizedBody", drillBodies, DrillBody.getBody(DrillPart.ENERGETIC_BODY));
        if(OreDictionary.getOres("ingotVibrantAlloy").size()>0)vibrantBody = new ManualEntry("vibrantBody", drillBodies, DrillBody.getBody(DrillPart.VIBRANT_BODY));
        ultimateBody = new ManualEntry("ultimateBody", drillBodies, DrillBody.getBody(DrillPart.END_BODY));
    }

}
