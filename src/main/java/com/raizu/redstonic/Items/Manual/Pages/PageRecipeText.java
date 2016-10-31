package com.raizu.redstonic.Items.Manual.Pages;

import com.raizu.redstonic.Utils.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raizu on 29/10/2016.
 * as a part of Redstonic
 **/
public class PageRecipeText extends PageBase {

    IRecipe recipe;
    String text;
    ResourceLocation tex = new ResourceLocation("redstonic", "textures/gui/ManualGUI.png");
    ItemStack hoverStack;
    List<String> tooltipStr = new ArrayList<String>();
    boolean stringTooltip;

    public PageRecipeText(IRecipe recipe, String text){
        this.recipe = recipe;
        this.text = text;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(tex);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawTexturedModalRect(posX+38+(i*22), posY+30+(j*22), 0, 192, 18, 18);
            }
        }
        drawTexturedModalRect(posX+63-22, posY+100, 20, 192, 16, 15);
        drawTexturedModalRect(posX+82-22, posY+100, 0, 192, 18, 18);
        if(recipe instanceof ShapedOreRecipe){
            ShapedOreRecipe rec = (ShapedOreRecipe)recipe;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Object currObj = rec.getInput()[i + (j * 3)];
                    if (currObj != null) {

                        // TODO: 30/10/2016 FIX THIS SHIT, ITS GIVING NULL WHEN THE ITEM DOES NOT EXIST IE: ENDER IO IS NOT INSTALLED
                        ItemStack currItem = (currObj instanceof ItemStack ? (ItemStack) currObj : ((List<ItemStack>) currObj).get(0)).copy();
                        try{
                            if (currItem.getItemDamage() == -1 || currItem.getItemDamage() == Short.MAX_VALUE) {
                                currItem.setItemDamage(0);
                            }
                        }catch(NullPointerException e){
                            mc.getTextureManager().bindTexture(tex);
                            drawTexturedModalRect(posX+38+(i*22), posY+30+(j*22), 0, 211, 18, 18);
                            if (mouseX >= posX + 39 + (i * 20) + (i * 2) && mouseX <= posX + 39 + (i * 20) + (i * 2) + 16 && mouseY >= posY + 31 + (j * 20) + (j * 2) && mouseY <= posY + 31 + (j * 20) + (j * 2) + 16) {
                                tooltipStr.add("Item not found.");
                                tooltipStr.add(TextFormatting.GRAY+"Mod missing.");
                                stringTooltip = true;
                            }
                            continue;
                        }
                        GlStateManager.pushMatrix();
                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                        RenderHelper.enableGUIStandardItemLighting();
                        GlStateManager.enableRescaleNormal();
                        GlStateManager.enableDepth();
                        GlStateManager.pushMatrix();
                        itemRender.renderItemAndEffectIntoGUI(currItem, posX + 39 + (i * 20) + (i * 2), posY + 31 + (j * 20) + (j * 2));
                        if (mouseX >= posX + 39 + (i * 20) + (i * 2) && mouseX <= posX + 39 + (i * 20) + (i * 2) + 16 && mouseY >= posY + 31 + (j * 20) + (j * 2) && mouseY <= posY + 31 + (j * 20) + (j * 2) + 16) {
                            hoverStack = currItem;
                        }
                        GlStateManager.popMatrix();
                        RenderHelper.disableStandardItemLighting();
                        GlStateManager.popMatrix();
                    }
                }
                itemRender.renderItemAndEffectIntoGUI(rec.getRecipeOutput(), posX + 83 - 22, posY + 101);
                if (mouseX >= posX + 83 - 22 && mouseX <= posX + 83 + 16 - 22 && mouseY >= posY + 101 && mouseY <= posY + 101 + 16) {
                    hoverStack = rec.getRecipeOutput();
                }
            }
        }else if(recipe instanceof ShapelessOreRecipe){

        }
        GL11.glPushMatrix();
        mc.fontRendererObj.drawString(parentEntry.getName(), posX+18, posY+15, 0x990000, false);
        GL11.glScalef(0.8F, 0.8F, 0.8F);
        mc.fontRendererObj.drawSplitString(StringUtils.localize("redstonic.entry."+text), (int)((posX+140)/0.8), (int)((posY+30)/0.8), 125, 0x404040);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glPopMatrix();
        if(hoverStack!=null){
            renderToolTip(hoverStack, mouseX, mouseY);
        }
        if(stringTooltip){
            GuiUtils.drawHoveringText(tooltipStr, mouseX, mouseY, new ScaledResolution(mc).getScaledWidth(),new ScaledResolution(mc).getScaledHeight(), 200, Minecraft.getMinecraft().fontRendererObj);
        }
        hoverStack=null;
        stringTooltip=false;
        tooltipStr.clear();
    }
}
