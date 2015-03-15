package com.raizunne.redstonic.Client.Render;

import com.raizunne.redstonic.Client.Model.ModelDrillHead;
import com.raizunne.redstonic.Client.Model.ModelDriller;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Raizunne as a part of Redstonic
 * on 15/03/2015, 11:06 AM.
 */
public class RenderDriller extends TileEntitySpecialRenderer {

    private ModelDriller driller;
    private ModelDrillHead head;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
            driller.renderModel(0.0625F);
        GL11.glPopMatrix();
    }
}
