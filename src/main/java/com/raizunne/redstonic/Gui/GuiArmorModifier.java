package com.raizunne.redstonic.Gui;

import com.raizunne.redstonic.Gui.Container.ContainerArmorModifier;
import com.raizunne.redstonic.TileEntity.TEArmorModifier;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.Util.Lang;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 26/06/2015, 10:06 PM.
 */
public class GuiArmorModifier extends GuiContainer {

    TEArmorModifier tile;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/ArmorModifierGUI.png");

    public GuiArmorModifier(EntityPlayer player, InventoryPlayer invplayer, TEArmorModifier tile) {
        super(new ContainerArmorModifier(player, invplayer, tile));
        xSize = 199;
        ySize = 171;
        this.tile = tile;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawTexturedModalRect(guiLeft+31, guiTop+12+51-tile.getPowerScaledProgress(51), 199, 0, 16, tile.getPowerScaledProgress(51));
//        drawTexturedModalRect(guiLeft-23, guiTop+15, 176, 0, 26, 89);
    }

    @Override
    public void initGui() {
        super.initGui();
        xSize = 199;
        ySize = 171;
        guiLeft = guiLeft-23;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        fontRendererObj.drawString("Redstonic Armor Modifier", 52, 6, 0x404040);
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
        int posX = (width - xSize) / 2;
        int posY = (height - ySize) / 2;
        if(x>posX+8 && x<posX+24 && y>posY+12 && y<posY+63){
            List list = new ArrayList<String>();
            list.add(Lang.addComas(tile.getEnergyStored(null)) + EnumChatFormatting.GRAY + " /");
            list.add(Lang.addComas(tile.getMaxEnergyStored(null)) + EnumChatFormatting.GRAY + " RF");
            list.add(EnumChatFormatting.GRAY + "Max In: " + tile.getMaxIn());
            list.add(EnumChatFormatting.GRAY + "Max Out: " + tile.getMaxOut());
            GL11.glPushMatrix();
            drawHoveringText(list, x, y, fontRendererObj);
            GL11.glPopMatrix();
        }
    }
}
