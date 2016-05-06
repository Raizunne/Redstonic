package com.raizunne.redstonic.Blocks.Shearer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 04/05/2016.
 */
public class GuiShearer extends GuiContainer {

    TEShearer te;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/Shearer.png");

    public GuiShearer(InventoryPlayer inventoryPlayer, TEShearer te){
        super(new ContainerShearer(inventoryPlayer, te));
        this.te = te;
        xSize = 176;
        ySize = 171;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRendererObj.drawString("Redstonic Shearer", 45, 6, 0x404040);
        fontRendererObj.drawString("Radius: " + this.te.getRadius(), 100, 40, 0x404040, false);
        fontRendererObj.drawString("Sheeps: " + this.te.getSheepCount(), 100, 52, 0x404040, false);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(17, 7+54-getScaledProgress(54), 184, 0, 7,getScaledProgress(54));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        RenderHelper.disableStandardItemLighting();
        if(isMouseInRect(mouseX, mouseY, new Rectangle(17, 7, 7, 54))){
            List<String> text = new ArrayList<String>();
            text.add("Progress: " +getScaledProgress(100)+"%");
            drawHoveringText(text, mouseX-5, mouseY);
        }
    }

    boolean isMouseInRect(int mouseX, int mouseY, Rectangle rect){
        mouseX-=guiLeft;
        mouseY-=guiTop;
        return mouseX>rect.getX() && mouseX<rect.getWidth()+rect.getX() && mouseY>rect.getY() && mouseY<rect.getHeight()+rect.getY();
    }


    public int getScaledProgress(int i){
        if(te.getTimer()!=0){
            return te.getTimer() * i / te.getMaxTime();
        }else{
            return 0;
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
