package com.raizunne.redstonic.Client.Render;

import com.raizunne.redstonic.Client.Model.ModelDrillHead;
import com.raizunne.redstonic.Client.Model.ModelDriller;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import com.raizunne.redstonic.TileEntity.TEDriller;
import com.raizunne.redstonic.Util.DrillUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 11:06 AM.
 */
public class RenderDriller extends TileEntitySpecialRenderer {

    private static final ResourceLocation drillerTexture = new ResourceLocation("redstonic:textures/model/Driller/Driller.png");
    private static final ResourceLocation iron = new ResourceLocation("redstonic:textures/model/Driller/Heads/Iron.png");

    int meta;
    int header;

    private ModelDriller driller;
    private ModelDrillHead head;

    public RenderDriller(){
        this.driller = new ModelDriller();
        this.head = new ModelDrillHead();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        if(tileEntity.getWorldObj()!=null) {
            meta = tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
            TEDriller driller = (TEDriller)tileEntity;
            header = driller.getHead();
            //BASE
            switch(meta) {
                case 0:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                    GL11.glRotatef(180, 0, 0F, 1F);
                    GL11.glRotatef(90, 0, 1, 0);
                    break;
                case 1:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                    GL11.glRotatef(180, 0, 0F, 1F);
                    GL11.glRotatef(180, 0, 1, 0);
                    break;
                case 2:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                    GL11.glRotatef(180, 0, 0F, 1F);
                    GL11.glRotatef(270, 0, 1, 0);
                    break;
                case 3:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                    GL11.glRotatef(180, 0, 0F, 1F);
                    break;
            }
            this.bindTexture(drillerTexture);
            this.driller.renderModel(0.0625F);
            GL11.glPopMatrix();

            //DRILL
            GL11.glPushMatrix();
            switch(meta) {
                case 0:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z + 1.935F);
                    GL11.glRotatef(90, 0F, 0F, 1F);
                    GL11.glRotatef(270, 1, 0, 0);
                    break;
                case 1:
                    GL11.glTranslatef((float) x - 0.935F, (float) y + 0.4F, (float) z + 0.5f);
                    GL11.glRotatef(90, 0F, 0F, 1F);
                    GL11.glRotatef(180, 1, 0, 0);
                    break;
                case 2:
                    GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z - 0.935F);
                    GL11.glRotatef(90, 0F, 0F, 1F);
                    GL11.glRotatef(90, 1, 0, 0);
                    break;
                case 3:
                    GL11.glTranslatef((float) x + 1.935F, (float) y + 0.4F, (float) z + 0.5F);
                    GL11.glRotatef(90, 0F, 0F, 1F);
                    break;
            }
            if(header!=999) {
                this.bindTexture(new ResourceLocation("redstonic:textures/model/Driller/Heads/" + DrillUtil.getDrillHeadName(header) + ".png"));
                this.head.renderModel(0.0625F);
            }
            GL11.glPopMatrix();
        }else{
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glRotatef(180, 0, 0F, 1F);
            GL11.glRotatef(270, 0, 1, 0);
            this.bindTexture(drillerTexture);
            this.driller.renderModel(0.0625F);
            GL11.glPopMatrix();

            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z - 0.935F);
            GL11.glRotatef(90, 0F, 0F, 1F);
            GL11.glRotatef(90, 1, 0, 0);
            this.bindTexture(new ResourceLocation("redstonic:textures/model/Driller/Heads/Iron.png"));
            this.head.renderModel(0.0625F);
        }
    }
}
