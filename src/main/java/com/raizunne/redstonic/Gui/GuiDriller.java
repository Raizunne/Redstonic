package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Container.ContainerDriller;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 31/03/2015, 11:58 AM.
 */
public class GuiDriller extends GuiContainer {

    TEDriller te;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/DrillerGUI.png");

    public GuiDriller(InventoryPlayer invplayer, TEDriller tile){
        super(new ContainerDriller(invplayer, tile));
        xSize = 176;
        ySize = 145;
        this.te = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
