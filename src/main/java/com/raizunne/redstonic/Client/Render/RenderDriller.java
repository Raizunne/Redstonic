package com.raizunne.redstonic.Client.Render;

import com.raizunne.redstonic.Client.Model.ModelDrillHead;
import com.raizunne.redstonic.Client.Model.ModelDriller;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 11:06 AM.
 */
public class RenderDriller extends TileEntitySpecialRenderer {

    private static final ResourceLocation drillerTexture = new ResourceLocation("redstonic:textures/model/Driller.png");
    private static final ResourceLocation headTexture = new ResourceLocation("redstonic:textures/model/DrillHead.png");

    private ModelDriller driller;
    private ModelDrillHead head;

    public RenderDriller(){
        this.driller = new ModelDriller();
        this.head = new ModelDrillHead();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        float baseX; float baseY; float baseZ; float baseRotX; float baseRotZ; float rotZ;

        switch(tileEntity.getWorldObj().getBlockMetadata((int)x,(int)y,(int)z)){
            case 0:
        }

        GL11.glTranslatef((float)x + 0.5F, (float)y +1.5F, (float)z + 0.5F);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glRotatef(180, 0F, 0F, 1F);
        this.bindTexture(drillerTexture);
        this.driller.renderModel(0.0625F);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+1.935F, (float)y+0.4F, (float)z+0.5F);
        GL11.glScalef(1F, 1F, 1F);
        GL11.glRotatef(90, 0F, 0F, 1F);
        this.bindTexture(headTexture);
        this.head.renderModel(0.0625F);
        GL11.glPopMatrix();

    }
}
