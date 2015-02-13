package com.raizunne.redstonic.Client.RenderItem;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 11/02/2015, 04:01 PM.
 */
public class ItemDrillModifier implements IItemRenderer{

    TileEntitySpecialRenderer render;
    private TileEntity entity;

    public ItemDrillModifier(TileEntitySpecialRenderer render, TileEntity entity){
        this.entity = entity;
        this.render = render;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        switch(type){
            case EQUIPPED:
                GL11.glTranslatef(0F, 0.3F, 0F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glScalef(2F, 2F, 2F);
                GL11.glTranslatef(0.5F, -0.5F, 0.6F);
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glRotatef(270, 0, 1, 0);
                break;
            case ENTITY:
                GL11.glTranslatef(-0.5F, -0.3F, -0.5F);
                break;
            case INVENTORY:
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glRotatef(180, 0F, 1F, 0F);
                GL11.glTranslatef(-0.05F, -0.8F, 0F);
                break;
            default:
                break;

        }
        this.render.renderTileEntityAt(this.entity, 0.0D, -0.1D, 0.0D, 0.0F);
    }

}
