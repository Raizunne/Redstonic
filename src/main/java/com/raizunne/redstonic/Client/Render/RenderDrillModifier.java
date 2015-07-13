package com.raizunne.redstonic.Client.Render;

import com.raizunne.redstonic.Client.Model.ModelDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 11/02/2015, 03:55 PM.
 */
public class RenderDrillModifier extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("redstonic:textures/model/DrillModifier.png");

    private ModelDrillModifier model;

    public RenderDrillModifier() {
        this.model = new ModelDrillModifier();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y +1.5F, (float)z + 0.5F);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glRotatef(180, 0F, 0F, 1F);
        this.bindTexture(texture);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        TEDrillModifier drill = (TEDrillModifier)tileentity;
        ItemStack stack1 = drill.getStackInSlot(0);
        if(stack1!=null){
            GL11.glPushMatrix();
            EntityItem entItem = new EntityItem(Minecraft.getMinecraft().thePlayer.getEntityWorld(), 0D, 0D, 0D, stack1);
            entItem.hoverStart = 0.0F;
            GL11.glTranslatef((float)x + 0.5F, (float)y+1F, (float)z + 0.5F);
//			GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(0.9F, 0.9F, 0.9F);
            float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL); //Credits to BloodMagic for code example!
            GL11.glRotatef(rotationAngle, 0F, 1F, 0F);

            RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = true;
            GL11.glPopMatrix();
        }
    }
}
