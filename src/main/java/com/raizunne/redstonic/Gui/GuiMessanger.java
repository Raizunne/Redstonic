package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Button.ButtonDirectional;
import com.raizunne.redstonic.Gui.Button.ButtonGive;
import com.raizunne.redstonic.Gui.Button.ButtonMenu;
import com.raizunne.redstonic.Gui.Container.ContainerMessenger;
import com.raizunne.redstonic.Item.RedstonicMessanger;
import com.raizunne.redstonic.Util.Util;
import com.raizunne.redstonic.Util.XML;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import static com.raizunne.redstonic.Gui.GuiMessanger.Page.*;

import java.util.*;

/**
 * Created by Raizunne as a part of Redstonic
 * on 09/07/2015, 02:12 PM.
 */
public class GuiMessanger extends GuiContainer {

    public enum Page{MENU, SEND_PACKAGE, PACKAGES, OPTIONS, HELP};

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/MessangerGUI.png");
    GuiTextField receiver; GuiTextField search;
    String receiverTxt; String searchTxt;
    public final int xSizeofTexture = 186;
    public final int ySizeofTexture = 180;
    HashMap<Integer, List<String>> hovering;
    Page page;
    Page prevPage;
    EntityPlayer player; World world;

    public GuiMessanger(EntityPlayer player, World world) {
        super(new ContainerMessenger(player.inventory, player.inventory.getCurrentItem()));
        this.player = player; this.world = world;
        this.page = MENU;
        receiverTxt ="";
        hovering = new HashMap<Integer, List<String>>();
        searchTxt="";
    }

    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int x, int y) {
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        drawDefaultBackground();
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(posX, posY, 0, 0, xSizeofTexture, ySizeofTexture);
        fontRendererObj.drawString("Redstonic Messenger", posX+((xSizeofTexture/2)-(fontRendererObj.getStringWidth("Redstonic Messenger"))/2), posY+10, 0x990000, false);
        if(page==SEND_PACKAGE){
            fontRendererObj.drawString("Send To:", posX+20, posY+28, 0x990000, false);
            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(posX+11, posY+46, 0, 191, 56, 56);
            this.receiver.drawTextBox();
        }else if(page==PACKAGES) {
            this.search.drawTextBox();
        }
        drawHover(x,y);
    }

    @Override
    protected void mouseClicked(int x, int y, int m) {
        super.mouseClicked(x,y,m);
        if(m==1 && page!=MENU){
            page=prevPage;
            prevPage=MENU;
            this.player.playSound("gui.button.press", 0.8F, 1F);
            searchTxt="";
            receiverTxt="";
        }
        this.initGui();
        this.receiver.mouseClicked(x, y, m);
        this.receiver.setText(receiverTxt);
        this.search.mouseClicked(x,y,m);
        this.search.setText(searchTxt);
    }

    @Override
    protected void keyTyped(char c, int i) {
        super.keyTyped(c,i);
        if(receiver.isFocused())this.receiver.textboxKeyTyped(c, i);
        if(search.isFocused())this.search.textboxKeyTyped(c, i);
        this.receiverTxt = receiver.getText();
        this.searchTxt = search.getText();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.receiver.updateCursorCounter();
        this.search.updateCursorCounter();
    }

    public void changePage(Page shit){
        if(!(player.inventory.getCurrentItem().getItem() instanceof RedstonicMessanger)){
            player.closeScreen();
        }
        prevPage=page;
        page=shit;
        if(player.inventory.getCurrentItem().stackTagCompound==null){
            player.inventory.getCurrentItem().stackTagCompound = new NBTTagCompound();
        }
        player.inventory.getCurrentItem().stackTagCompound.setString("page", shit.toString());
        System.out.println(player.inventory.getCurrentItem().stackTagCompound);
    }

    @Override
    public void initGui() {
        super.initGui();
        int color1 = 0x990000;
        int color2 = 0x660000;
        buttonList = new ArrayList();
        int posX = (width - xSizeofTexture) / 2;
        int posY = (height - ySizeofTexture) / 2;
        String[] menusT = XML.getTable("menu", "messenger");
        ButtonMenu[] menus = new ButtonMenu[menusT.length];

        receiver = new GuiTextField(fontRendererObj, posX+65, posY+25, 80, 12);
        search = new GuiTextField(fontRendererObj, posX+50, posY+25, 80, 12);
        if(page!=MENU){
            ButtonDirectional dir = new ButtonDirectional(-3, posX+16, posY+9, ButtonDirectional.Type.LEFT_STRAIGHT);
            buttonList.add(dir);
            hovering.put(-3, Util.quickLore("Menu"));
        }

        if(page==MENU){
            for(int i=0; i<menusT.length; i++){
                menus[i] = new ButtonMenu(i, posX+((xSizeofTexture/2)-(fontRendererObj.getStringWidth(menusT[i]))/2), (posY+25)+i*12, menusT[i], color1, color2, false);
                buttonList.add(menus[i]);
            }
        }else if(page==SEND_PACKAGE){

        }else if(page==PACKAGES){
            List<ItemStack> contents = RedstonicMessanger.getCratesFromPlayer(player, world);
            ItemStack[] crates = contents.toArray(new ItemStack[contents.size()]);
            ButtonGive[] messages = new ButtonGive[contents.size()];
            int btnY = 0;
            for(int i=0; i<crates.length; i++){
                ItemStack head = new ItemStack(Items.apple);
                List<String> lore = new ArrayList<String>();
                lore.add("Sent by; ");
                if(i>4){
                    btnY=(int)((long)(i/4))*25;
                }
                messages[i] = new ButtonGive(10000+i, posX+10+(i*22), posY+50+btnY, player, head, crates[i]);
                buttonList.add(messages[i]);
                hovering.put(10000+i, lore);
            }
        }
        if(page!=MENU){

        }
    }

    public void drawHover(int x, int y){
        for(Object obj : buttonList) {
            GuiButton btn = (GuiButton)obj;
            if(hovering.containsKey(btn.id)) {
                if (x > btn.xPosition && x < btn.xPosition + btn.width && y > btn.yPosition && y < btn.yPosition + btn.height) {
                    drawHoveringText(hovering.get(btn.id), x, y, fontRendererObj);
                }
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton btn) {
        super.actionPerformed(btn);
        switch(btn.id){
            case -3: changePage(MENU); break;
            case 0: changePage(PACKAGES);break;
            case 1: changePage(SEND_PACKAGE);break;
            case 2: changePage(OPTIONS);break;
            case 3: changePage(HELP);break;
        }
        if(btn.id>9999 && btn.id<11000){
            removeButton(btn);
        }
    }

    public void removeButton(GuiButton btn){
        for(int i=0; i<buttonList.size(); i++){
            if(buttonList.get(i).equals(btn)){
                buttonList.remove(i);
                initGui();
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}