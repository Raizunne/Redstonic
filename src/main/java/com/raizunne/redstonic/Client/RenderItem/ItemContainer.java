package com.raizunne.redstonic.Client.RenderItem;

import com.raizunne.redstonic.Client.Model.ModelContainer;
import com.raizunne.redstonic.Item.RedstonicContainer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/04/2015, 09:49 PM.
 */
public class ItemContainer implements IItemRenderer {

    RenderItem itemRender = new RenderItem();
    ModelContainer model;

    private static final ResourceLocation texture = new ResourceLocation("redstonic:textures/model/Container.png");

    public ItemContainer(){
        model = new ModelContainer();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type != ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        switch(type){
            case INVENTORY: renderInventory(stack);
                break;
            case EQUIPPED_FIRST_PERSON: renderEquipped(stack);
                break;
            case EQUIPPED: renderHand(stack);
                break;
            case ENTITY: renderEntity(stack);
        }
    }

    public void renderEntity(ItemStack stack){
        ItemStack block = new ItemStack(Block.getBlockById(stack.stackTagCompound.getInteger("block")), 1, stack.stackTagCompound.getInteger("blockMeta"));

        GL11.glTranslatef(-0.2F, -0.1F, -0.3F);

        GL11.glPushMatrix();
        GL11.glScalef(1, 1, 1);
        GL11.glTranslatef(0.3F, 0.2F, 0.3F);
//        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL); //Credits to BloodMagic for code example!
//        GL11.glRotatef(rotationAngle, 0F, 1F, 0F);
        EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, block);
        entItem.hoverStart = 0.0F;
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();

        GL11.glScalef(0.1F, 0.1F, 0.1F);
        float yAxis = stack.stackTagCompound.getInteger("block")==-1 ? 18F : 15;
        GL11.glTranslatef(3F, yAxis, 3F);
        GL11.glRotatef(-180F, 10.0F, 0F, 0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderModel(0.625F);
    }

    public void renderEquipped(ItemStack stack){
        ItemStack block = new ItemStack(Block.getBlockById(stack.stackTagCompound.getInteger("block")), 1, stack.stackTagCompound.getInteger("blockMeta"));

        GL11.glPushMatrix();
        GL11.glScalef(2, 2, 2);
        GL11.glTranslatef(0.2F, 0.2F, 0.2F);
        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL); //Credits to BloodMagic for code example!
        GL11.glRotatef(rotationAngle, 0F, 1F, 0F);
        EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, block);
        entItem.hoverStart = 0.0F;
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();

        GL11.glScalef(0.15F, 0.15F, 0.15F);
        float yAxis = stack.stackTagCompound.getInteger("block")==-1 ? 18F : 15;
        GL11.glTranslatef(3F, yAxis, 3F);
        GL11.glRotatef(-180F, 10.0F, 0F, 0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderModel(0.625F);
    }

    public void renderHand(ItemStack stack){
        ItemStack block = new ItemStack(Block.getBlockById(stack.stackTagCompound.getInteger("block")), 1, stack.stackTagCompound.getInteger("blockMeta"));

        GL11.glPushMatrix();
        GL11.glScalef(2, 2, 2);
        GL11.glTranslatef(0.2F, 0.4F, 0.2F);
        float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL); //Credits to BloodMagic for code example!
        GL11.glRotatef(rotationAngle, 0F, 1F, 0F);
        EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, block);
        entItem.hoverStart = 0.0F;
        RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();

        GL11.glScalef(0.15F, 0.15F, 0.15F);
        GL11.glTranslatef(3F, 18F, 3F);
        GL11.glRotatef(-180F, 10.0F, 0F, 0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderModel(0.625F);
    }

    public void renderInventory(ItemStack stack){
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager textureManager = mc.getTextureManager();
        FontRenderer fontRenderer = RenderManager.instance.getFontRenderer();

        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);

        IIcon iconerino = RedstonicContainer.Icon.icon;
        IIcon disabled = RedstonicContainer.Icon.xOverlay;
        itemRender.renderIcon(0,0, iconerino, 16,16);

        if(stack.stackTagCompound!=null && stack.stackTagCompound.getInteger("block")!=-1 && fontRenderer!=null && textureManager!=null){
            int number = stack.stackTagCompound.getInteger("number");
            ItemStack blockerino = new ItemStack(Block.getBlockById(stack.stackTagCompound.getInteger("block")), 1, stack.stackTagCompound.getInteger("blockMeta"));
            GL11.glPushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glTranslatef(1.2F, 0.1F, 0.1F);
            itemRender.renderItemAndEffectIntoGUI(fontRenderer, textureManager, blockerino, 1, 1);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            GL11.glTranslatef(2F, 2F, 2F);
            String dis = stack.stackTagCompound.getBoolean("disabled")?""+EnumChatFormatting.STRIKETHROUGH : "";
            itemRender.renderItemOverlayIntoGUI(fontRenderer, textureManager, blockerino, 0, 0, dis  + number);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
