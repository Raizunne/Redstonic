package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Button.ButtonDirectional;
import com.raizunne.redstonic.Gui.Button.ButtonIcon;
import com.raizunne.redstonic.Gui.Button.ButtonToggle;
import com.raizunne.redstonic.Gui.Button.ButtonWidget;
import com.raizunne.redstonic.Gui.Container.ContainerDrillModifier;
import com.raizunne.redstonic.Item.Drill.DrillBody;
import com.raizunne.redstonic.Item.RedstonicDrill;
import com.raizunne.redstonic.Item.RedstonicSword;
import com.raizunne.redstonic.Item.Sword.SwordHandle;
import com.raizunne.redstonic.Network.PacketDrill;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.SwordUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.RenderDistanceSorter;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:06 PM.
 */
public class GuiDrillModifier extends GuiContainer{

    TEDrillModifier te;
    String buttonText;
    String secondLine;
    GuiTextField rename;
    String renameText;

    public GuiDrillModifier(InventoryPlayer invplayer, TEDrillModifier tile){
        super(new ContainerDrillModifier(invplayer, tile));
        xSize = 176;
        ySize = 171;
        this.te = tile;
        if(tile.getMode()==1){
            this.buttonText = "Assemble";
        }else{
            this.buttonText = "Disassemble";
        }
        this.renameText="";
        this.secondLine="";
    }

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/DrillModifierGUI.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        if(te.getStackInSlot(3)==null){
            drawTexturedModalRect(guiLeft+67, guiTop+65, 176, 34, 16, 16);
        }

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        int posX = (width - 176) / 2;
        int posY = (height - 166) / 2;
        fontRendererObj.drawString("Redstonic Modifier", 45, 6, 0x404040);
        fontRendererObj.drawString("Augments:", 95, 55, 0x404040);
        fontRendererObj.drawString("" + te.getAugments(), 145, 55, 0x404040);
        fontRendererObj.drawString(secondLine, 93, 65, 0x404040);

        RenderHelper.disableStandardItemLighting();
        if(x>posX+31 && x<posX+50 && y>posY+57 && y<posY+76 && this.buttonText!=""){
            List list = new ArrayList<String>();
            list.add(this.buttonText);
            drawHoveringText(list, x-posX-5, y-posY+2, fontRendererObj);
        }

        RenderHelper.enableGUIStandardItemLighting();
        this.rename.drawTextBox();
        this.initGui();
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList = new ArrayList();
        buttonList.removeAll(buttonList);

        ButtonWidget manual = new ButtonWidget(3, guiLeft-21, guiTop+5, "Manual", "left", RedstonicItems.ManualBook, itemRender);
        buttonList.add(manual);

        ButtonIcon assemble = new ButtonIcon(1, guiLeft+31, guiTop+63, "ASSEMBLE", RedstonicItems.RedDrill);
        ButtonIcon dissassemble = new ButtonIcon(2, guiLeft+31, guiTop+63, "DISSASSEMBLE", RedstonicItems.IronHead);

        this.rename = new GuiTextField(fontRendererObj, 91, 20, 67, 11);
        this.rename.setMaxStringLength(35);
        this.rename.setEnableBackgroundDrawing(false);
        this.rename.setText(renameText);
        this.rename.setFocused(true);

        if(te.getDisName()!=""){
//            System.out.println(te.getDisName());
            this.renameText=te.getDisName();
            this.rename.setText(te.getDisName());
            te.reset();
        }

        this.buttonText="";
        this.secondLine="";

        if(te.getStackInSlot(1)!=null && te.getStackInSlot(2)!=null && te.getStackInSlot(3)!=null && te.getStackInSlot(0)==null && (te.checkForAssembleSword() || te.checkForAssembleDrill())) {
            this.buttonText = "Assemble";
            if(te.getStackInSlot(2)!=null && te.getStackInSlot(2).getItem() instanceof DrillBody){
                ItemStack placeDrill = DrillUtil.getPlaceholderDrill(DrillUtil.getHeadNumber(te.getStackInSlot(1)), DrillUtil.getBodyNumber(te.getStackInSlot(2)), DrillUtil.getAugNumber(te.getStackInSlot(5)), DrillUtil.getAugNumber(te.getStackInSlot(6)), DrillUtil.getAugNumber(te.getStackInSlot(7)));
                assemble.changeItem(placeDrill);
                secondLine="Dig Speed: " + RedstonicDrill.getBaseDigSpeed(placeDrill, Blocks.stone);
            }else if(te.getStackInSlot(2)!=null && te.getStackInSlot(2).getItem() instanceof SwordHandle){
                ItemStack placeSword = SwordUtil.getPlaceholderSword(SwordUtil.getBladeNumber(te.getStackInSlot(1)), SwordUtil.getHandleNumber(te.getStackInSlot(2)), SwordUtil.getAugNumber(te.getStackInSlot(5)), SwordUtil.getAugNumber(te.getStackInSlot(6)), SwordUtil.getAugNumber(te.getStackInSlot(7)));
                assemble.changeItem(placeSword);
                secondLine=SwordUtil.getAbsoluteDamage(placeSword)/2+ " Heart Dmg";
            }
            buttonList.add(assemble);
        }else if(te.getStackInSlot(1)==null && te.getStackInSlot(2)==null && te.getStackInSlot(3)==null && te.getStackInSlot(0)!=null && (te.getStackInSlot(0).getItem() instanceof RedstonicDrill || te.getStackInSlot(0).getItem() instanceof RedstonicSword)){
            this.buttonText="Disasssemble";
            if(te.getStackInSlot(0)!=null && te.getStackInSlot(0).getItem() instanceof RedstonicDrill){
                dissassemble.changeIcon(DrillUtil.getDrillBody(te.getStackInSlot(0).stackTagCompound.getInteger("body")));
            }else if(te.getStackInSlot(0)!=null && te.getStackInSlot(0).getItem() instanceof RedstonicSword){
                dissassemble.changeIcon(SwordUtil.getHandle(te.getStackInSlot(0).stackTagCompound.getInteger("handle")));
            }
            buttonList.add(dissassemble);
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        this.rename.textboxKeyTyped(c, i);
        this.renameText = this.rename.getText();
        if(!( (i== Keyboard.KEY_P || i==Keyboard.KEY_0 || i==Keyboard.KEY_X || i==Keyboard.KEY_F || i==Keyboard.KEY_E)  &&  this.rename.isFocused())) super.keyTyped(c, i);
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseId) {
        super.mouseClicked(x, y, mouseId);
        String name = rename.getText();
        this.rename.mouseClicked(x-guiLeft, y-guiTop, mouseId);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.rename.updateCursorCounter();
    }

    public String getName(){
        return renameText;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        switch(button.id){
            case 0:
                if(button instanceof ButtonToggle){
                    ButtonToggle toggle = (ButtonToggle)button;
                    toggle.toggleState();
                    te.toggleMode();
                    if(te.getMode()==1){
                        this.buttonText = "Disassemble";
                    }else{
                        this.buttonText = "Assemble";
                    }
                }
                Redstonic.network.sendToServer(new PacketDrill(this.te, this.getName()));
            break;
            case 1:
                te.setMode(2, renameText);
                this.buttonText="Assemble";
                Redstonic.network.sendToServer(new PacketDrill(this.te, this.getName()));
                this.rename.setText("");
                this.renameText="";
                break;
            case 2:
                te.setMode(1, renameText);
                this.buttonText="Disassemble";
                Redstonic.network.sendToServer(new PacketDrill(this.te, this.getName()));
                this.rename.setText("");
                this.renameText="";
                break;
            case 3:
                Minecraft.getMinecraft().thePlayer.openGui(Redstonic.instance, 1, Minecraft.getMinecraft().theWorld, (int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posY, (int)Minecraft.getMinecraft().thePlayer.posZ);
                break;
        }
    }
}
