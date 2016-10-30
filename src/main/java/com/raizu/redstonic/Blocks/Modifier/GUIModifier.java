package com.raizu.redstonic.Blocks.Modifier;

import cofh.api.energy.IEnergyContainerItem;
import com.raizu.redstonic.Client.Button.ButtonArrow;
import com.raizu.redstonic.Client.Button.ButtonIcon;
import com.raizu.redstonic.Client.Button.ButtonWidget;
import com.raizu.redstonic.Items.Battery;
import com.raizu.redstonic.Items.Drill.Drill;
import com.raizu.redstonic.Items.Drill.DrillAugment;
import com.raizu.redstonic.Items.Drill.DrillBody;
import com.raizu.redstonic.Items.Drill.DrillHead;
import com.raizu.redstonic.Items.RedItems;
import com.raizu.redstonic.Items.Sword.Sword;
import com.raizu.redstonic.Items.Sword.SwordBlade;
import com.raizu.redstonic.Items.Sword.SwordHandle;
import com.raizu.redstonic.Packet.PacketModifier;
import com.raizu.redstonic.Redstonic;
import com.raizu.redstonic.Utils.StringUtils;
import com.raizu.redstonic.Utils.Util;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Raizu on 15/10/2016.
 * as a part of Redstonic
 **/
public class GUIModifier extends GuiContainer{

    TEModifier modifier;
    GuiTextField rename;
    ButtonIcon buildButton;
    String renameText;

    public GUIModifier(InventoryPlayer inventoryPlayer, TEModifier modifier){
        super(new ContainerModifier(inventoryPlayer, modifier));
        this.modifier = modifier;
        xSize = 176;
        ySize = 171;
        this.renameText = "";
    }

    enum CraftType{SWORD,DRILL,NONE;}

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        int posX = (width - 176) / 2;
        int posY = (height - 171) / 2;
        fontRendererObj.drawString(StringUtils.localize("gui.redstonic.modifier"), 45, 6, 0x404040);
        this.rename.drawTextBox();
        int allowedAugments = 0;
        if(this.buildButton.visible){
            if(mouseX>posX+25 && mouseX<posX+43 && mouseY>posY+64 && mouseY<posY+82){
                drawHoveringText(Arrays.asList(new String[]{buildButton.getStatus()== ButtonIcon.Status.BUILD ? StringUtils.localize("redstonic.gui.build"): StringUtils.localize("redstonic.gui.deconstruct")}), mouseX-posX, mouseY-posY);
            }
            if(mouseX>posX-22 && mouseX<posX && mouseY>posY+10 && mouseY<posY+30){
                List<String> list = new ArrayList<String>();
                list.add("Think you can do");
                list.add("a better model?");
                list.add("Contact me @ github!");
                drawHoveringText(list, mouseX-posX, mouseY-posY);
            }
        }
        if(modifier.getStackInSlot(0)==null && modifier.getStackInSlot(1)!=null && modifier.getStackInSlot(2)!=null && modifier.getStackInSlot(3)!=null && modifier.getStackInSlot(3).getItem() instanceof IEnergyContainerItem){ // ASSEMBLING
            CraftType craftType = modifier.getStackInSlot(1).getItem() instanceof DrillHead ? CraftType.DRILL : modifier.getStackInSlot(1).getItem() instanceof SwordBlade ? CraftType.SWORD : CraftType.NONE;
            ItemStack[] augmentSltos = new ItemStack[]{modifier.getStackInSlot(4), modifier.getStackInSlot(5), modifier.getStackInSlot(6)};
            if(craftType == CraftType.DRILL){ // BUILDING DRILL
                if(modifier.getStackInSlot(2).getItem() instanceof DrillBody && modifier.areAugmentsValid(CraftType.DRILL) && !Util.hasDuplicateAugments(augmentSltos)){
                    ItemStack[] augmentsAdded = modifier.getAddedDrillAugments();
                    allowedAugments = RedItems.drillBody.augmentSlots[modifier.getStackInSlot(2).getMetadata()];
                    if(augmentsAdded.length<=allowedAugments){
                        if(modifier.getStackInSlot(1).getMetadata()==7){
                            if(modifier.getStackInSlot(2).getMetadata()==5){
                                this.buildButton.visible=true;
                                this.buildButton.setIcon(1,1);
                                this.buildButton.setStatus(ButtonIcon.Status.BUILD);
                            }else{
                                this.buildButton.visible=false;
                            }
                        }else{
                            this.buildButton.visible=true;
                            this.buildButton.setIcon(1,1);
                            this.buildButton.setStatus(ButtonIcon.Status.BUILD);
                        }
                    }else{
                        this.buildButton.visible=false;
                    }
                }else{
                    this.buildButton.visible=false;
                }
            }else if(craftType == CraftType.SWORD){ // BUILDING SWORD
                if(modifier.getStackInSlot(2).getItem() instanceof SwordHandle && modifier.areAugmentsValid(CraftType.SWORD) && !Util.hasDuplicateAugments(augmentSltos)){
                    this.buildButton.enabled = true;
                    //TODO: CHANGE THIS NAME
                    ItemStack[] augmentsAdded = modifier.getAddedSword();
                    allowedAugments = RedItems.swordHandle.augmentSlots[modifier.getStackInSlot(2).getMetadata()];
                    if(augmentsAdded.length<=allowedAugments){
                        this.buildButton.visible=true;
                        this.buildButton.setIcon(3,1);
                        this.buildButton.setStatus(ButtonIcon.Status.BUILD);
                    }else{
                        this.buildButton.visible = false;
                    }
                }else{
                    this.buildButton.visible = false;
                }
            }else{
                this.buildButton.visible = false;
            }
        }else if(modifier.getStackInSlot(0)!=null && isClear(new int[]{1,2,3,4,5,6}) && (modifier.getStackInSlot(0).getItem() instanceof Drill || modifier.getStackInSlot(0).getItem() instanceof Sword)){ //DISASSEMBLE
            this.buildButton.visible = true;
            this.buildButton.setIcon(modifier.getStackInSlot(0).getItem() instanceof Drill ? 2 : 4,1);
            this.buildButton.setStatus(ButtonIcon.Status.DECONSTRUCT);
        }else{
            this.buildButton.visible = false;
        }
    }

    public boolean isClear(int[] clearNums){
        for (int i = 0; i < clearNums.length; i++) {
            if(modifier.getStackInSlot(clearNums[i])!=null){
                return false;
            }
        }
        return true;
    }

    public static final ResourceLocation texture = new ResourceLocation("redstonic", "textures/gui/RedstonicModifier.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderHelper.disableStandardItemLighting();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        if(modifier.getStackInSlot(3)==null){
            drawTexturedModalRect(guiLeft+67, guiTop+65, 176, 34, 16, 16);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        int posX = (width - 176) / 2;
        int posY = (height - 171) / 2;
        buttonList = new ArrayList<GuiButton>();
        this.rename = new GuiTextField(0, fontRendererObj, 91, 20, 67, 11);
        this.rename.setMaxStringLength(35);
        this.rename.setEnableBackgroundDrawing(false);
        this.rename.setText(renameText);
        this.rename.setFocused(true);

        buildButton = new ButtonIcon(1, posX+25, posY+64, 2,1);
        buildButton.visible=false;

        ButtonWidget textures = new ButtonWidget(100, ButtonWidget.Direction.LEFT, posX-21, posY+10, 1,2);
        ButtonArrow arrpw = new ButtonArrow(102, posX+20, posY+40, ButtonArrow.Direction.UP);

        buttonList.add(arrpw);
        buttonList.add(textures);
        buttonList.add(buildButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id){
            case 1:
                if(this.buildButton.getStatus()== ButtonIcon.Status.BUILD){
                    modifier.assemble(renameText);
                    Redstonic.network.sendToServer(new PacketModifier(this.modifier, this.renameText, 0));
                }else if(this.buildButton.getStatus()== ButtonIcon.Status.DECONSTRUCT){
                    rename.setText(modifier.getStackInSlot(0).getDisplayName()=="Redstonic Drill" ? modifier.getStackInSlot(0).getDisplayName() : "");
                    modifier.disassemble(renameText);
                    Redstonic.network.sendToServer(new PacketModifier(this.modifier, this.renameText, 1));
                }
                break;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.rename.textboxKeyTyped(typedChar, keyCode);
        this.renameText = this.rename.getText();
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.rename.mouseClicked(mouseX-guiLeft, mouseY-guiTop, mouseButton);

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.rename.updateCursorCounter();
    }
}