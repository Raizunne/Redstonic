package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Container.ContainerHyperSmelter;
import com.raizunne.redstonic.TileEntity.TEHyperSmelter;
import com.raizunne.redstonic.Util.Lang;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 20/05/2015, 06:50 PM.
 */
public class GuiHyperSmelter extends GuiContainer{

    TEHyperSmelter tile;

    public GuiHyperSmelter(InventoryPlayer invplayer, TEHyperSmelter tile){
        super(new ContainerHyperSmelter(invplayer, tile));
        this.tile = tile;

    }

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/HyperSmelter.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 171);
        drawTexturedModalRect(guiLeft+79, guiTop+33, 176, 0, this.tile.getScaledProgress(24),22);
        drawTexturedModalRect(guiLeft+8, guiTop+12+51-tile.getPowerScaledProgress(51), 176, 43, 16, tile.getPowerScaledProgress(51));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        fontRendererObj.drawString("Redstonic Hyper Smelter", 32, 6, 0x404040);
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
        int posX = (width - 176) / 2;
        int posY = (height - 166) / 2;
        mc.renderEngine.bindTexture(texture);
        if(x>posX+79 && x<posX+103 && y>posY+33 && y<posY+55){
            List list = new ArrayList<String>();
            list.add("Progress: " + tile.getScaledProgress(100) + "%");
            GL11.glPushMatrix();
            drawHoveringText(list, x, y, fontRendererObj);
            GL11.glPopMatrix();
        }
        if(x>posX+8 && x<posX+24 && y>posY+12 && y<posY+63){
            List list = new ArrayList<String>();
            list.add(Lang.addComas(tile.getEnergyStored(null)) + "/" + Lang.addComas(tile.getMaxEnergyStored(null)) + " RF");
            GL11.glPushMatrix();
            drawHoveringText(list, x, y, fontRendererObj);
            GL11.glPopMatrix();
        }
    }
}
