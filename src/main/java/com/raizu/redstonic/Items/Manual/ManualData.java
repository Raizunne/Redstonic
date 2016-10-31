package com.raizu.redstonic.Items.Manual;

import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.Drill.DrillPart;
import com.raizu.redstonic.Items.Manual.Pages.PageRecipeText;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Items.Sword.SwordAugment;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import com.raizu.redstonic.Items.Sword.SwordPart;
import com.raizu.redstonic.Recipe.RedRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class ManualData {

    public static ManualCategory mainMenu;
    public static ManualCategory drill;
    public static ManualCategory sword;
    public static ManualCategory drillHeads;
    public static ManualCategory drillBodies;
    public static ManualCategory drillAugments;
    public static ManualCategory swordBlades;
    public static ManualCategory swordHandles;
    public static ManualCategory swordAugments;
    public static ManualCategory batteries;

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
    public static ManualEntry energeticBody;
    public static ManualEntry vibrantBody;
    public static ManualEntry ultimateBody;

    public static ManualEntry ironBlade;
    public static ManualEntry diamondBlade;
    public static ManualEntry energeticBlade;
    public static ManualEntry vibrantBlade;
    public static ManualEntry electrumBlade;
    public static ManualEntry enderiumBlade;

    public static ManualEntry ironHandle;
    public static ManualEntry woodHandle;
    public static ManualEntry energeticHandle;
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
    public static ManualEntry energeticBattery;
    public static ManualEntry greatBattery;

    public static void initData(){

        mainMenu = new ManualCategory("mainMenu");
        drill = new ManualCategory("drill", mainMenu, new ItemStack(RedItems.drill));
        sword = new ManualCategory("sword", mainMenu, new ItemStack(RedItems.sword));
        batteries = new ManualCategory("batteries", mainMenu, new ItemStack(RedItems.battery, 1, 3));
        drillHeads = new ManualCategory("drillHeads", drill);
        drillBodies = new ManualCategory("drillBodies", drill);
        drillAugments = new ManualCategory("drillAugments", drill);
        swordBlades = new ManualCategory("swordBlades", sword);
        swordHandles = new ManualCategory("swordHandles", sword);
        swordAugments = new ManualCategory("swordAugments", sword);

        basicBattery = new ManualEntry("basicBattery", batteries, Battery.getBattery(Battery.Types.BASIC)).setPages(
                new PageRecipeText(RedRecipes.basicBattery, "basicBattery1")
        );
        energeticBattery = new ManualEntry("energeticBattery", batteries, Battery.getBattery(Battery.Types.ENERGETIC)).setPages(
                new PageRecipeText(RedRecipes.energizedBattery, "energeticBattery1")
        );
        greatBattery = new ManualEntry("greatBattery", batteries, Battery.getBattery(Battery.Types.GREAT)).setPages(
                new PageRecipeText(RedRecipes.greatBattery, "greatBattery1")
        );

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

        ironBody = new ManualEntry("ironBody", drillBodies, DrillBody.getBody(DrillPart.IRON_BODY)).setPages(
                new PageRecipeText(RedRecipes.ironBody, "ironBody1")
        );
        if(OreDictionary.getOres("ingotElectrum").size()>0)electrumBody = new ManualEntry("electrumBody", drillBodies, DrillBody.getBody(DrillPart.ELECTRUM_BODY)).setPages(
                new PageRecipeText(RedRecipes.electrumBody, "electrumBody1")
        );
        if(OreDictionary.getOres("ingotEnderium").size()>0)enderiumBody = new ManualEntry("enderiumBody", drillBodies, DrillBody.getBody(DrillPart.ENDERIUM_BODY)).setPages(
                new PageRecipeText(RedRecipes.enderiumBlade, "enderiumBody1")
        );
        if(OreDictionary.getOres("ingotEnergeticAlloy").size()>0) energeticBody = new ManualEntry("energeticBody", drillBodies, DrillBody.getBody(DrillPart.ENERGETIC_BODY)).setPages(
                new PageRecipeText(RedRecipes.energizedBody, "energeticBody1")
        );
        if(OreDictionary.getOres("ingotVibrantAlloy").size()>0)vibrantBody = new ManualEntry("vibrantBody", drillBodies, DrillBody.getBody(DrillPart.VIBRANT_BODY)).setPages(
                new PageRecipeText(RedRecipes.vibrantBody, "vibrantBody1")
        );
        ultimateBody = new ManualEntry("ultimateBody", drillBodies, DrillBody.getBody(DrillPart.END_BODY)).setPages(
                new PageRecipeText(RedRecipes.ultimateBody, "ultimateBody1")
        );

        ironBlade = new ManualEntry("ironBlade", swordBlades, SwordBlade.getBlade(SwordPart.IRON_BLADE)).setPages(
                new PageRecipeText(RedRecipes.ironBlade, "ironBlade1")
        );
        diamondBlade = new ManualEntry("diamondBlade", swordBlades, SwordBlade.getBlade(SwordPart.DIAMOND_BLADE)).setPages(
                new PageRecipeText(RedRecipes.diamondBlade, "diamondBlade1")
        );
        if(OreDictionary.getOres("ingotEnergeticAlloy").size()>0)energeticBody = new ManualEntry("energeticBlade", swordBlades, SwordBlade.getBlade(SwordPart.ENERGETIC_BLADE)).setPages(
                new PageRecipeText(RedRecipes.energizedBlade, "energeticBlade1")
        );
        if(OreDictionary.getOres("ingotVibrantAlloy").size()>0)vibrantBlade = new ManualEntry("vibrantBlade", swordBlades, SwordBlade.getBlade(SwordPart.VIBRANT_BLADE)).setPages(
                new PageRecipeText(RedRecipes.vibrantBlade, "vibrantBlade1")
        );
        if(OreDictionary.getOres("ingotElectrum").size()>0)electrumBlade = new ManualEntry("electrumBlade", swordBlades, SwordBlade.getBlade(SwordPart.ELECTRUM_BLADE)).setPages(
                new PageRecipeText(RedRecipes.electrumBlade, "electrumBlade1")
        );
        if(OreDictionary.getOres("ingotEnderium").size()>0)enderiumBlade = new ManualEntry("enderiumBlade", swordBlades, SwordBlade.getBlade(SwordPart.ENDERIUM_BLADE)).setPages(
                new PageRecipeText(RedRecipes.enderiumBlade, "enderiumBlade1")
        );

        woodHandle = new ManualEntry("woodHandle", swordHandles, SwordHandle.getHandle(SwordPart.WOOD_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.woodHandle, "woodHandle1")
        );
        ironHandle = new ManualEntry("ironHandle", swordHandles, SwordHandle.getHandle(SwordPart.IRON_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.ironHandle, "ironHandle1")
        );
        if(OreDictionary.getOres("ingotElectrum").size()>0)electrumHandle = new ManualEntry("electrumHandle", swordHandles, SwordHandle.getHandle(SwordPart.ELECTRUM_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.electrumHandle, "electrumHandle1")
        );
        if(OreDictionary.getOres("ingotEnderium").size()>0)enderiumHandle = new ManualEntry("enderiumHandle", swordHandles, SwordHandle.getHandle(SwordPart.ENDERIUM_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.enderiumHandle, "enderiumHandle1")
        );
        if(OreDictionary.getOres("ingotEnergeticAlloy").size()>0)energeticHandle = new ManualEntry("energeticHandle", swordHandles, SwordHandle.getHandle(SwordPart.ENERGETIC_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.energizedHandle, "energeticHandle1")
        );
        if(OreDictionary.getOres("ingotVibrantAlloy").size()>0)vibrantHandle = new ManualEntry("vibrantHandle", swordHandles, SwordHandle.getHandle(SwordPart.VIBRANT_HANDLE)).setPages(
                new PageRecipeText(RedRecipes.vibrantHandle, "vibrantHandle1")
        );

        speedDrillAugment = new ManualEntry("speedDrillAugment", drillAugments, DrillAugment.getAugment(DrillPart.SPEED_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.speedDrillAugment, "speedDrillAugment1")
        );
        speedIIDrillAugment = new ManualEntry("speedIIDrillAugment", drillAugments, DrillAugment.getAugment(DrillPart.SPEED_II_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.speedIIDrillAugment, "speedIIDrillAugment1")
        );
        // TODO: 30/10/2016 ADD PAGE THAT SHOW HOW TO PUT THE DRILL HEAD
        hotswapDrillAugment = new ManualEntry("hotswapDrillAugment", drillAugments, DrillAugment.getAugment(DrillPart.HOTSWAP_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.hotswapDrillAugment, "hotswapDrillAugment1")
        );
        heavyDrillAugmnet = new ManualEntry("heavyDrillAugment", drillAugments, DrillAugment.getAugment(DrillPart.HEAVY_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.heavyHead, "heavyDrillAugment1")
        );

        berserkerSwordAugmnet = new ManualEntry("berserkSwordAugment", swordAugments, SwordAugment.getAugment(SwordPart.BERSERK_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.berserkerSwordAugmnet, "berserkSwordAugment1")
        );
        berserkerIISwordAugment = new ManualEntry("berserkIISwordAugment", swordAugments, SwordAugment.getAugment(SwordPart.BERSERK_II_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.berserkerIISwordAugment, "berserkIISwordAugment1")
        );
        fortuitousSwordAugment =  new ManualEntry("fortuitousSwordAugment", swordAugments, SwordAugment.getAugment(SwordPart.FORTUITOUS_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.fortuitousSwordAugment, "fortuitousSwordAugment1")
        );
        blazerSwordAugment = new ManualEntry("blazerSwordAugment", swordAugments, SwordAugment.getAugment(SwordPart.BLAZER_AUGMENT)).setPages(
                new PageRecipeText(RedRecipes.blazerSwordAugment, "blazerSwordAugment1")
        );

    }

}
