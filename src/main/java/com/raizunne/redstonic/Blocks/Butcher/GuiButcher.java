package com.raizunne.redstonic.Blocks.Butcher;

import com.raizunne.redstonic.Blocks.Shearer.ContainerShearer;
import com.raizunne.redstonic.Network.Packet.ButcherPacket;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.GuiToggleButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 05/05/2016, 08:28 PM.
 */
public class GuiButcher extends GuiContainer {

    TEButcher te;
    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/Butcher.png");

    public GuiButcher(InventoryPlayer invPlayer, TEButcher butcher) {
        super(new ContainerButcher(invPlayer, butcher));
        this.te = butcher;
        xSize = 176;
        ySize = 171;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRendererObj.drawString("Redstonic Butcher", 45, 6, 0x404040);
        fontRendererObj.drawString("Radius: " + this.te.getRadius(), 100, 38, 0x404040, false);
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
        buttonList = new ArrayList<GuiButton>();
        GuiToggleButton cows = new GuiToggleButton(0, guiLeft+100,guiTop+48, 50, "Kill Cows");
        GuiToggleButton sheep = new GuiToggleButton(1, guiLeft+100,guiTop+61, 50,  "Kill Sheep");
        GuiToggleButton pigs = new GuiToggleButton(2, guiLeft+100,guiTop+74, 50, "Kill Pigs");

        cows.setToggled(this.te.isKillCows());
        sheep.setToggled(this.te.isKillSheep());
        pigs.setToggled(this.te.isKillPig());

        buttonList.add(sheep);
        buttonList.add(pigs);
        buttonList.add(cows);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id){
            case 0:
                te.setKillCows(!this.te.isKillCows());
                Redstonic.network.sendToServer(new ButcherPacket(te, this.te.isKillCows(), this.te.isKillSheep(), this.te.isKillPig()));
                break;
            case 1:
                te.setKillSheep(!this.te.isKillSheep());
                Redstonic.network.sendToServer(new ButcherPacket(te, this.te.isKillCows(), this.te.isKillSheep(), this.te.isKillPig()));
                break;
            case 2:
                te.setKillPig(!this.te.isKillPig());
                Redstonic.network.sendToServer(new ButcherPacket(te, this.te.isKillCows(), this.te.isKillSheep(), this.te.isKillPig()));
                break;
        }
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
