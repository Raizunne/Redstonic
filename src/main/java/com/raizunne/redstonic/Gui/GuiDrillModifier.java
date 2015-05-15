package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Button.ButtonToggle;
import com.raizunne.redstonic.Gui.Container.ContainerDrillModifier;
import com.raizunne.redstonic.Network.PacketDrill;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.RedstonicItems;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:06 PM.
 */
public class GuiDrillModifier extends GuiContainer{

    TEDrillModifier te;
    String buttonText;

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
    }

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/DrillModifierGUI.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        int posX = (width - 176) / 2;
        int posY = (height - 166) / 2;
        fontRendererObj.drawString("Redstonic Modifier", 45, 6, 0x404040);
        fontRendererObj.drawString("Mode:", 90, 62, 0x00000);
        fontRendererObj.drawString(this.buttonText, 90, 72, 0x00000);
        fontRendererObj.drawString("Augments:", 90, 40, 0x00000);
        fontRendererObj.drawString("" + te.getAugments(), 90, 50, 0x00000);
        RenderHelper.disableStandardItemLighting();
        if(x>posX+31 && x<posX+50 && y>posY+57 && y<posY+76){
            List list = new ArrayList<String>();
            list.add(this.buttonText);
            drawHoveringText(list, x-posX-5, y-posY+2, fontRendererObj);
        }
        RenderHelper.enableGUIStandardItemLighting();
    }



    @Override
    public void initGui() {
        super.initGui();
        ButtonToggle button = new ButtonToggle(0, guiLeft+31, guiTop+63, "hello", "hello", RedstonicItems.IronHead, RedstonicItems.RedDrill);
        button.setState(te.getMode());
        buttonList.add(button);
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
                Redstonic.network.sendToServer(new PacketDrill(this.te));
            break;
        }
    }
}
