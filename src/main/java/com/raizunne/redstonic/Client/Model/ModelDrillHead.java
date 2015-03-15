package com.raizunne.redstonic.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * DrillHead - Raizunne
 * Created using Tabula 4.0.2
 */
public class ModelDrillHead extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;

    public ModelDrillHead() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_2 = new ModelRenderer(this, 20, 5);
        this.shape1_2.setRotationPoint(-4.5F, 18.0F, -4.5F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 9, 3, 9);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-3.5F, 16.0F, -3.5F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 7, 2, 7);
        this.shape1_1 = new ModelRenderer(this, 0, 18);
        this.shape1_1.setRotationPoint(-5.5F, 21.0F, -5.5F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 11, 3, 11);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_2.render(f5);
        this.shape1.render(f5);
        this.shape1_1.render(f5);
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
        this.shape1_2.render(f5);
        this.shape1.render(f5);
        this.shape1_1.render(f5);
    }
}
