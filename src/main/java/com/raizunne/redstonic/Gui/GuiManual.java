package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Button.ButtonDirectional;
import com.raizunne.redstonic.Gui.Button.ButtonMenu;
import com.raizunne.redstonic.Gui.Button.ButtonPage;
import com.raizunne.redstonic.Proxy.ClientProxy;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.XML;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Raizunne as a part of Redstonic
 * on 07/02/2015, 11:26 PM.
 */
public class GuiManual extends GuiScreen {

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    public static final ResourceLocation misc = new ResourceLocation("redstonic", "textures/gui/Misc.png");

    String page;
    String prevPage;
    int maxPages;
    int subPage;
    EntityPlayer player;
    public final int xSizeofTexture = 256;
    public final int ySizeofTexture = 170;

    int posX = (width - xSizeofTexture) / 2;
    int posY = (height - ySizeofTexture) / 2;

    public GuiManual(EntityPlayer player, String page){
        this.page=page;
        this.player=player;
        maxPages=1;
        subPage=1;
        prevPage="index";
    }

    public boolean entryBlacklist(String page){
        String[] blacklist = {"index", "drills", "extras", "Contribute!", "machines", "swords"};
        for(int i=0; i<blacklist.length; i++){
            if(page==blacklist[i]){
                return true;
            }
        }
        return false;
    }

    public void drawScreen(int x, int y, float f) {
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        drawDefaultBackground();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(posX, posY, 0, 0, xSizeofTexture, ySizeofTexture);
        if(entryBlacklist(page)){
            mc.renderEngine.bindTexture(misc);
            drawTexturedModalRect(posX+20, posY+20, 0, 0, 99, 94);
            drawTexturedModalRect(posX+18, posY+120, 0, 95, 102, 23);
        }else{
            if(subPage==1) {
                newEntry(this.page, XML.getEntry(this.page.replaceAll(" ", ""), "bookentries"));
            }
        }
        if(page=="index"){
            if(!ClientProxy.version.equals(Redstonic.VERSION)) {
                drawStatsText("Redstonic is outadated!", "", 0x000000, 0x404040, 140, 140);
            }
        }else if(page=="Heads"){
            drawStatsText("Iron Head", "Medium Speed", 0x404040, 0x009933, 160, 15); drawStatsText("Gold Head", "Very Fast Speed", 0x404040, 0x009933, 160, 35);
            drawStatsText("Diamond Head", "Fast Speed", 0x404040, 0x009933, 160, 55); drawStatsText("Fortuitous Head", "Slow - Fortune Mining", 0x404040, 0x009933, 160, 75);
            drawStatsText("Heavy Head", "Medium - 3x3 Mining", 0x404040, 0x009933, 160, 95); drawStatsText("Silky Head", "Slow - Silk Touch I", 0x404040, 0x009933, 160, 115);
            drawStatsText("End Drill Head", "Instant Speed",0x404040, 0x009933, 160, 135);
            drawItem(RedstonicItems.IronHead, 140, 15, x, y); drawItem(RedstonicItems.GoldHead, 140, 35, x, y); drawItem(RedstonicItems.DiamondHead, 140, 55, x, y);
            drawItem(RedstonicItems.FortuitousHead, 140, 75, x, y); drawItem(RedstonicItems.HeavyHead, 140, 95, x, y); drawItem(RedstonicItems.SilkyHead, 140, 115, x, y);
            drawItem(RedstonicItems.EndHead, 140, 135, x, y);
        }else if(page=="Bodies"){
            drawStatsText("Iron Body", "1 Augment Slot", 0x404040, 0x009933, 160, 20); drawStatsText("Electrum Body", "2 Augment Slot", 0x404040, 0x009933, 160, 40);
            drawStatsText("Enderium Body", "3 Augment Slot", 0x404040, 0x009933, 160, 60); drawStatsText("Energetic Body", "2 Augment Slot", 0x404040, 0x009933, 160, 80);
            drawStatsText("Vibrant Body", "3 Augment Slot", 0x404040, 0x009933, 160, 100); drawStatsText("Ultimate Body", "0 Augment Slot", 0x404040, 0x009933, 160, 120);
            drawItem(RedstonicItems.IronBody, 140, 20, x, y); drawItem(RedstonicItems.ElectrumBody, 140, 40, x, y); drawItem(RedstonicItems.EnderiumBody, 140, 60, x, y);
            drawItem(RedstonicItems.EnergeticBody, 140, 80, x, y); drawItem(RedstonicItems.VibrantBody, 140, 100, x, y); drawItem(RedstonicItems.UltimateBody, 140, 120, x, y);
        }else if(page=="Energy") {
            if(Loader.isModLoaded("ThermalExpansion")) {
                drawStatsText("Hardened Capacitor", "320,000 RF Storage", 0x404040, 0x009933, 160, 20);
                drawStatsText("Reinforced Capacitor", "1,280,000 RF Storage", 0x404040, 0x009933, 160, 40);
                drawStatsText("Resonant Capacitor", "3,200,000 RF Storage", 0x404040, 0x009933, 160, 60);
                drawStatsText("Creative Capacitor", "Infinite RF Storage", 0x404040, 0x009933, 160, 80);
                drawItem(GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1), 140, 20, x, y);
                drawItem(GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1), 140, 40, x, y);
                drawItem(GameRegistry.findItemStack("ThermalExpansion", "capacitorResonant", 1), 140, 60, x, y);
                drawItem(GameRegistry.findItemStack("ThermalExpansion", "capacitorCreative", 1), 140, 80, x, y);
            }

            drawStatsText("Basic Battery", "320,000 RF Storage", 0x404040, 0x009933, 40, 90);
            drawStatsText("Energized Battery", "1,280,000 RF Storage", 0x404040, 0x009933, 40, 110);
            drawStatsText("Great Battery", "3,200,000 RF Storage", 0x404040, 0x009933, 40, 130);
            drawItem(RedstonicItems.basicBattery, 20, 90, x, y);
            drawItem(RedstonicItems.energizedBattery, 20, 110, x, y);
            drawItem(RedstonicItems.greatBattery, 20, 130, x, y);
        }else if(page=="Contribute!"){
            drawText("Redstonic is made completely by Raizunne, but he is also limited in his capabilities!. If you wish to contribute with Redstonic, Raizunne (Me), is looking for some work done for Redstonic!", 135, 135, 20);
            drawStatsText("Model for Drill Modifier", "(Dont like current!)", 0x990000, 0x990000, 135, 85);
            drawStatsText("Model for Drills", "(Make them 3D!)", 0x990000, 0x990000, 135, 105);
            drawText("Contact: @Raizunne on Twitter", 135, 135, 135);
        }else if(page=="Augments"){
            if(subPage==1){
                drawStatsText("Speed Augment I", "x1.5 Dig Speed Mutli", 0x404040, 0x009933, 160, 20); drawStatsText("Energy Augment I", "x1.5 Max Energy Multi", 0x404040, 0x009933, 160, 40);
                drawStatsText("Hotswap Augment", "See next page", 0x404040, 0x009933, 160, 60); drawStatsText("Block Placer Augment", "Place blocks", 0x404040, 0x009933, 160, 80);
                drawStatsText("Magnetization Augment", "Attacts item entities.", 0x404040, 0x009933, 160, 100);
                drawItem(RedstonicItems.SpeedAugment, 140, 20, x, y); drawItem(RedstonicItems.EnergyAugment, 140, 40, x, y);
                drawItem(RedstonicItems.HotswapAugment, 140, 60, x, y); drawItem(RedstonicItems.BlockAugment, 140, 80, x, y); drawItem(RedstonicItems.MagnetAugment, 140, 100, x, y);
            }else if(subPage==2){
                drawText("Replace the drill ontop with the drill of your choice.", 135, 140, 20);
                ItemStack augmenterino = new ItemStack(RedstonicItems.HotswapAugment);
                augmenterino.stackTagCompound = new NBTTagCompound();
                augmenterino.stackTagCompound.setInteger("head", 0);
                newEntry("Hotswap Augment", XML.getEntry("HotswapAugment", "bookentries"));
                drawCrafting(null, RedstonicItems.IronHead, null, null, RedstonicItems.HotswapAugment, null, null,null,null, augmenterino, 150, 45, x, y);
            }else if(subPage==3){
                newEntry("Hotswap Augment", XML.getEntry("MagnetizationAugment", "bookentries"));
                ItemStack drillOFF = new ItemStack(RedstonicItems.RedDrill);
                ItemStack drillON = new ItemStack(RedstonicItems.RedDrill);
                drillOFF.stackTagCompound = new NBTTagCompound();
                NBTTagCompound nbt1 = drillOFF.stackTagCompound;
                nbt1.setInteger("head", 1);
                nbt1.setInteger("body", 5);
                nbt1.setInteger("aug1", 5);
                drillON = drillOFF.copy();
                nbt1.setBoolean("aug1Deactivated", true);
                drillON.stackTagCompound.setBoolean("aug1Deactivated", false);
                drawStatsText("", "Deactivated", 0x404040, 0x990000, 20, 115);
                drawStatsText("", "Activated", 0x404040, 0x009933, 75, 115);
                drawItem(drillON, 85, 130, x, y);
                drawItem(drillOFF, 30, 130, x, y);
            }
        }else if(page=="Driller"){
        }else if(page=="Blades"){
            drawStatsText("Iron Blade", "4 Heart damage", 0x404040, 0x009933, 160, 15); drawStatsText("Diamond Blade", "6 Heart damage", 0x404040, 0x009933, 160, 35);
            drawStatsText("Electrum Blade", "9 Heart damage", 0x404040, 0x009933, 160, 55); drawStatsText("Enderium Blade", "11 Heart damage", 0x404040, 0x009933, 160, 75);
            drawStatsText("Energetic Blade", "9 Heart damage", 0x404040, 0x009933, 160, 95); drawStatsText("Vibrant Blade", "11 Heart damage", 0x404040, 0x009933, 160, 115);
            drawItem(RedstonicItems.IronBlade, 140, 15, x, y); drawItem(RedstonicItems.DiamondBlade, 140, 35, x, y); drawItem(RedstonicItems.ElectrumBlade, 140, 55, x, y);
            drawItem(RedstonicItems.EnderiumBlade, 140, 75, x, y); drawItem(RedstonicItems.EnergizedBlade, 140, 95, x, y); drawItem(RedstonicItems.VibrantBlade, 140, 115, x, y);
        }else if(page=="Handles"){
            drawStatsText("Wood Handle", "0 Augment Slots", 0x404040, 0x009933, 160, 15); drawStatsText("Iron Handle", "1 Augment Slot", 0x404040, 0x009933, 160, 35);
            drawStatsText("Electrum Handle", "2 Augment Slots", 0x404040, 0x009933, 160, 55); drawStatsText("Enderium Handle", "3 Augment Slots", 0x404040, 0x009933, 160, 75);
            drawStatsText("Energetic Handle", "2 Augment Slots", 0x404040, 0x009933, 160, 95); drawStatsText("Vibrant Handle", "3 Augment Slots", 0x404040, 0x009933, 160, 115);
            drawItem(RedstonicItems.WoodHandle, 140, 15, x, y); drawItem(RedstonicItems.IronHandle, 140, 35, x, y); drawItem(RedstonicItems.ElectrumHandle, 140, 55, x, y);
            drawItem(RedstonicItems.EnderiumHandle, 140, 75, x, y); drawItem(RedstonicItems.EnergizedHandle, 140, 95, x, y); drawItem(RedstonicItems.VibrantHandle, 140, 115, x, y);
        }else if(page=="Sword Augments") {
            drawStatsText("Berserk II Augment", "+2 Heart Damage", 0x404040, 0x009933, 155, 20); drawStatsText("Berserk II Augment", "+3 Heart Damage", 0x404040, 0x009933, 155, 40);
            drawStatsText("Blazer Augment", "Fire Aspect II", 0x404040, 0x009933, 155, 60); drawStatsText("Fortuitous Augment", "Looting", 0x404040, 0x009933, 155, 80);
            drawStatsText("Magnetization Augment", "Attacts item entities.", 0x404040, 0x009933, 155, 100);
            drawItem(RedstonicItems.SpeedAugment, 135, 20, x, y); drawItem(RedstonicItems.EnergyAugment, 135, 40, x, y);
            drawItem(RedstonicItems.HotswapAugment, 135, 60, x, y); drawItem(RedstonicItems.BlockAugment, 135, 80, x, y); drawItem(RedstonicItems.MagnetAugment, 135, 100, x, y);
        }
        super.drawScreen(x, y, f);
    }

    @Override
    public void initGui() {
        buttonList = new ArrayList();
        int color1 = 0x990000;
        int color2 = 0x660000;
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;

        String[] indexTitles = XML.getTable("indexTitles", "bookentries");
        String[] drillsTitles = XML.getTable("drillsTitles", "bookentries");
        String[] swordsTitles = XML.getTable("swordsTitles", "bookentries");
        String[] machinesTitles = XML.getTable("machinesTitles", "bookentries");
        String[] extrasTitles = XML.getTable("extrasTitles", "bookentries");

        ButtonMenu[] index = new ButtonMenu[indexTitles.length];
        ButtonMenu[] drills = new ButtonMenu[drillsTitles.length];
        ButtonMenu[] swords = new ButtonMenu[swordsTitles.length];
        ButtonMenu[] machines = new ButtonMenu[machinesTitles.length];
        ButtonMenu[] extras = new ButtonMenu[extrasTitles.length];
        ButtonDirectional right = new ButtonDirectional(-2, posX + 232, posY + 171, ButtonDirectional.Type.RIGHT_CURVED);
        ButtonDirectional left = new ButtonDirectional(-1, posX + 6, posY + 171, ButtonDirectional.Type.LEFT_CURVED);
        ButtonPage ret = new ButtonPage(-3, posX + 95, posY + 171, 100, 14, "Return");
        ButtonMenu changelog = new ButtonMenu(10000, posX + 160, posY + 145, "Changelog", 0x3366FF, 0x2447B2, true);

        if(page=="index") {
            for (int i = 0; i < index.length; i++) {
                index[i] = new ButtonMenu(i, posX + 140, posY + 15 + i * 12, indexTitles[i], color1, color2, true);
                buttonList.add(index[i]);
            }
            if(!ClientProxy.version.equals(Redstonic.VERSION)) {
                buttonList.add(changelog);
            }
        }else if(page=="drills" || page=="Getting Started") {
            for (int i = 0; i < drills.length; i++) {
                drills[i] = new ButtonMenu(100 + i, posX + 140, posY + 15 + i * 12, drillsTitles[i], color1, color2, true);
                buttonList.add(drills[i]);
            }
        }else if(page=="swords"){
            for (int i = 0; i < swords.length; i++) {
                swords[i] = new ButtonMenu(300 + i, posX + 140, posY + 15 + i * 12, swordsTitles[i], color1, color2, true);
                buttonList.add(swords[i]);
            }
        }else if(page=="extras") {
            for (int i = 0; i < extras.length; i++) {
                extras[i] = new ButtonMenu(900 + i, posX + 140, posY + 15 + i * 12, extrasTitles[i], color1, color2, true);
                buttonList.add(extras[i]);
            }
        }else if(page=="machines"){
            for(int i=0; i<machines.length; i++){
                machines[i] = new ButtonMenu(200+i, posX+140, posY+15+i*12, machinesTitles[i], color1, color2, true);
                buttonList.add(machines[i]);
            }
        }
        if(page!="index"){
            if(maxPages>1 && subPage!=maxPages){
                buttonList.add(right);
            }
            if(subPage>1){
                buttonList.add(left);
            }
            buttonList.add(ret);
        }
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        switch(button.id){
            case -3: if(page=="drills" || page=="extras" || page=="Contribute!"){page="index"; maxPages=1; subPage=1;}else{page=prevPage; maxPages=1; subPage=1;} break;
            case -2: subPage+=1; break;
            case -1: subPage-=1; break;
            case 10000: try {Desktop.getDesktop().browse(URI.create("http://raizunne.github.io/Mods/Redstonic/Changelog.html#" + ClientProxy.version)); } catch(Exception e) {e.printStackTrace();} break;
            case 0: page="drills"; prevPage="index"; break;
            case 1: page="swords"; prevPage="index"; break;
            case 2: page="machines"; prevPage="index"; break;
            case 3: page="extras"; prevPage="index"; break;
            case 4: page="Contribute!"; prevPage="index"; break;

            case 100: page="Getting Started"; prevPage="drills"; break;
            case 101: page="Heads"; prevPage="drills"; break;
            case 102: page="Bodies"; prevPage="drills"; break;
            case 103: page="Energy"; prevPage="drills"; break;
            case 104: page="Augments"; prevPage="drills"; maxPages=3; break;

            case 200: page="Driller"; prevPage="machines"; break;

            case 300: page="Blades"; prevPage="swords"; break;
            case 301: page="Handles"; prevPage="swords"; break;
            case 302: page="Energy"; prevPage="swords"; break;
            case 303: page="Sword Augments"; prevPage="swords"; break;

            case 900: try {Desktop.getDesktop().browse(URI.create("https://github.com/Raizunne/Redstonic/issues")); } catch(Exception e) {e.printStackTrace();} break;
            case 901: try {Desktop.getDesktop().browse(URI.create("http://raizunne.github.io")); } catch(Exception e) {e.printStackTrace();} break;
            case 902: try {Desktop.getDesktop().browse(URI.create("https://www.patreon.com/raizunne")); } catch(Exception e) {e.printStackTrace();} break;
        }
    }

    public void newEntry(String title, String translate){
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        GL11.glPushMatrix();
        this.fontRendererObj.drawString(title, posX+18, posY+12, 0x990000, false);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        this.fontRendererObj.drawSplitString(translate, (int)((posX+18)/0.8), (int)((posY+25)/0.8), 135, 0);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseId) {
        super.mouseClicked(x, y, mouseId);
        if(mouseId==1){
            if(page=="drills" || page=="swords" || page=="extras" || page=="machines" && page!="index"){
                page="index";
                this.player.playSound(randomSound(), 1F, 1F);
                maxPages=1;
                subPage=1;
            }else{
                if(page!="index") {
                    page = prevPage;
                    this.player.playSound(randomSound(), 1F, 1F);
                    maxPages=1;
                    subPage=1;
                }
            }
        }
        this.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void drawText(String text, int w, int x, int y){
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        GL11.glPushMatrix();
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        this.fontRendererObj.drawSplitString(text, (int)((posX+x)/0.8), (int)((posY+y)/0.8), w, 0);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix();
    }

    public void drawStatsText(String text1, String text2, int color1, int color2, int x, int y){
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        GL11.glPushMatrix();
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        this.fontRendererObj.drawString(text1, (int)((posX+x)/0.8), (int)((posY+y)/0.8), color1, false);
        this.fontRendererObj.drawString(text2, (int)((posX+x)/0.8), (int)((posY+y+8)/0.8), color2, false);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix();
    }

    public void drawItem(Object item, int x, int y, int mousex, int mousey){
        ItemStack i = null;
        if(item instanceof Item){
            i = new ItemStack((Item)item);
        }else if(item instanceof Block){
            i = new ItemStack((Block)item);
        }else if(item instanceof ItemStack) {
            i = (ItemStack) item;
        }else{
            return;
        }
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;

        itemRender.renderItemAndEffectIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), i, posX + x, posY + y);
        GL11.glDisable(GL11.GL_LIGHTING);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX+x-1, posY+y-1, 0, 192, 18, 18);
        if(mousex>posX+x && mousex<posX+x+16 && mousey>posY + y && mousey<posY + y+16){
            renderToolTip(i, mousex, mousey);
        }
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void drawRandomItem(ItemStack[] item, int x, int y, int mousex, int mousey){
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        Random random = new Random();
        ItemStack i = item[random.nextInt(item.length)];

        itemRender.renderItemAndEffectIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), i, posX + x, posY + y);
        GL11.glDisable(GL11.GL_LIGHTING);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX+x-1, posY+y-1, 0, 192, 18, 18);
        if(mousex>posX+x && mousex<posX+x+16 && mousey>posY + y && mousey<posY + y+16){
            renderToolTip(i, mousex, mousey);
        }
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public ItemStack stack(Object item){
        ItemStack i = null;
        if(item instanceof Item){
            i = new ItemStack((Item)item);
        }else if(item instanceof Block){
            i = new ItemStack((Block)item);
        }else if(item instanceof ItemStack) {
            i = (ItemStack) item;
        }else{
            return null;
        }
        return i;
    }

    public String randomSound(){
        String[] pages = {"pageflip1", "pageflip2", "pageflip3", "pageflip4"};
        Random random = new Random();
        int i = random.nextInt(pages.length);
        return "redstonic:" + pages[i];
    }

    public ItemStack getModStack(String modid, String item, int meta){
        ItemStack stack = GameRegistry.findItemStack(modid, item, meta);
        return stack;
    }

    public void drawCrafting(Object i1, Object i2, Object i3, Object i4, Object i5, Object i6, Object i7, Object i8, Object i9, Object pro,int yPos, int xPos, int mousex, int mousey){
        FontRenderer itemsInGrid = Minecraft.getMinecraft().fontRenderer;
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        int posX = (this.width - xSizeofTexture) / 2;
        int posY = (this.height - ySizeofTexture) / 2;
        int xPosCrafting = posX + yPos;
        int yPosCrafting = posY + xPos;

        ItemStack slot1 = null, slot2 = null, slot3 = null, slot4 = null, slot5 = null, slot6 = null, slot7 = null, slot8 = null, slot9 = null, product = null;
        if(i1 instanceof Item){
            slot1 = new ItemStack((Item)i1);
        }else if(i1 instanceof Block){
            slot1 = new ItemStack((Block)i1);
        }else if(i1 instanceof ItemStack){
            slot1=(ItemStack)i1;
        }

        if(i2 instanceof Item){
            slot2 = new ItemStack((Item)i2);
        }else if(i2 instanceof Block){
            slot2 = new ItemStack((Block)i2);
        }else if(i2 instanceof ItemStack){
            slot2=(ItemStack)i2;
        }

        if(i3 instanceof Item){
            slot3 = new ItemStack((Item)i3);
        }else if(i3 instanceof Block){
            slot3 = new ItemStack((Block)i3);
        }else if(i3 instanceof ItemStack){
            slot3=(ItemStack)i3;
        }

        if(i4 instanceof Item){
            slot4 = new ItemStack((Item)i4);
        }else if(i4 instanceof Block){
            slot4 = new ItemStack((Block)i4);
        }else if(i4 instanceof ItemStack){
            slot4=(ItemStack)i4;
        }

        if(i5 instanceof Item){
            slot5 = new ItemStack((Item)i5);
        }else if(i5 instanceof Block){
            slot5 = new ItemStack((Block)i5);
        }else if(i5 instanceof ItemStack){
            slot5=(ItemStack)i5;
        }

        if(i6 instanceof Item){
            slot6 = new ItemStack((Item)i6);
        }else if(i6 instanceof Block){
            slot6 = new ItemStack((Block)i6);
        }else if(i6 instanceof ItemStack){
            slot6=(ItemStack)i6;
        }

        if(i7 instanceof Item){
            slot7 = new ItemStack((Item)i7);
        }else if(i7 instanceof Block){
            slot7 = new ItemStack((Block)i7);
        }else if(i7 instanceof ItemStack){
            slot7=(ItemStack)i7;
        }

        if(i8 instanceof Item){
            slot8 = new ItemStack((Item)i8);
        }else if(i8 instanceof Block){
            slot8 = new ItemStack((Block)i8);
        }else if(i8 instanceof ItemStack){
            slot8=(ItemStack)i8;
        }

        if(i9 instanceof Item){
            slot9 = new ItemStack((Item)i9);
        }else if(i9 instanceof Block){
            slot9 = new ItemStack((Block)i9);
        }else if(i9 instanceof ItemStack){
            slot9=(ItemStack)i9;
        }

        if(pro instanceof Item){
            product = new ItemStack((Item)pro);
        }else if(pro instanceof Block){
            product = new ItemStack((Block)pro);
        }else if(pro instanceof ItemStack){
            product=(ItemStack)pro;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(misc);
        drawTexturedModalRect(xPosCrafting, yPosCrafting, 0, 119, 76, 56);

        if(slot1!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot1, xPosCrafting + 1, yPosCrafting + 1);
        }
        if(slot2!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot2, xPosCrafting + 20, yPosCrafting + 1);
        }
        if(slot3!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot3, xPosCrafting + 39, yPosCrafting + 1);
        }
        if(slot4!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot4, xPosCrafting + 1, yPosCrafting + 20);
        }
        if(slot5!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot5, xPosCrafting + 20, yPosCrafting + 20);
        }
        if(slot6!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot6, xPosCrafting + 39, yPosCrafting + 20);
        }
        if(slot7!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot7, xPosCrafting + 1, yPosCrafting + 39);
        }
        if(slot8!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot8, xPosCrafting + 20, yPosCrafting + 39);
        }
        if(slot9!=null){
            RenderHelper.disableStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                    slot9, xPosCrafting + 39, yPosCrafting + 39);
        }

        RenderHelper.disableStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(itemsInGrid, Minecraft.getMinecraft().getTextureManager(),
                product, xPosCrafting + 59, yPosCrafting + 8);
        RenderHelper.disableStandardItemLighting();

        if(slot1!=null){
            if(mousex>xPosCrafting+2 && mousex<xPosCrafting+16 && mousey>yPosCrafting+2 && mousey<yPosCrafting+16){
                renderToolTip(slot1, mousex, mousey);
            }
        }
        if(slot2!=null){
            if(mousex>xPosCrafting+21 && mousex<xPosCrafting+35 && mousey>yPosCrafting+2 && mousey<yPosCrafting+16){
                renderToolTip(slot2, mousex, mousey);
            }
        }
        if(slot3!=null){
            if(mousex>xPosCrafting+40 && mousex<xPosCrafting+54 && mousey>yPosCrafting+2 && mousey<yPosCrafting+16){
                renderToolTip(slot3, mousex, mousey);
            }
        }
        if(slot4!=null){
            if(mousex>xPosCrafting+2 && mousex<xPosCrafting+16 && mousey>yPosCrafting+21 && mousey<yPosCrafting+35){
                renderToolTip(slot4, mousex, mousey);
            }
        }
        if(slot5!=null){
            if(mousex>xPosCrafting+21 && mousex<xPosCrafting+35 && mousey>yPosCrafting+21 && mousey<yPosCrafting+35){
                renderToolTip(slot5, mousex, mousey);
            }
        }
        if(slot6!=null){
            if(mousex>xPosCrafting+40 && mousex<xPosCrafting+54 && mousey>yPosCrafting+21 && mousey<yPosCrafting+35){
                renderToolTip(slot6, mousex, mousey);
            }
        }
        if(slot7!=null){
            if(mousex>xPosCrafting+2 && mousex<xPosCrafting+16 && mousey>yPosCrafting+40 && mousey<yPosCrafting+54){
                renderToolTip(slot7, mousex, mousey);

            }
        }
        if(slot8!=null){
            if(mousex>xPosCrafting+21 && mousex<xPosCrafting+35 && mousey>yPosCrafting+40 && mousey<yPosCrafting+54){
                renderToolTip(slot8, mousex, mousey);
            }
        }
        if(slot9!=null){
            if(mousex>xPosCrafting+40 && mousex<xPosCrafting+54 && mousey>yPosCrafting+40 && mousey<yPosCrafting+54){
                renderToolTip(slot9, mousex, mousey);
            }
        }

        if(mousex>xPosCrafting+58 && mousex<xPosCrafting+72 && mousey>yPosCrafting+26 && mousey<yPosCrafting+42){
            String[] desc = { "Crafting" };
            @SuppressWarnings("rawtypes")
            List temp = Arrays.asList(desc);
            drawHoveringText(temp, mousex, mousey, itemsInGrid);
        }
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        if(mousex>xPosCrafting+60 && mousex<xPosCrafting+74 && mousey>yPosCrafting+9 && mousey<yPosCrafting+23){
            renderToolTip(product, mousex, mousey);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
        }
    }
}
