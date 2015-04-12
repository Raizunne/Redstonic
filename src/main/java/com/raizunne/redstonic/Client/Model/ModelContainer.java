package com.raizunne.redstonic.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Container - 
 * Created using Tabula 4.0.2
 */
public class ModelContainer extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;

    public ModelContainer() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_1 = new ModelRenderer(this, 0, 10);
        this.shape1_1.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.shape1_1.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(1.0F, 22.0F, 1.0F);
        this.shape1.addBox(-5.0F, 0.0F, -5.0F, 8, 1, 8);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_1.render(f5);
        this.shape1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void renderModel(float f5){
        this.shape1_1.render(f5);
        this.shape1.render(f5);
    }
}
