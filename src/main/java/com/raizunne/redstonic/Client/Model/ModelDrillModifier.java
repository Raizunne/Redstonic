package com.raizunne.redstonic.Client.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * DrillModifier - Raizunne
 * Created using Tabula 4.0.2
 */
public class ModelDrillModifier extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;

    public ModelDrillModifier() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape1_1 = new ModelRenderer(this, 0, 40);
        this.shape1_1.setRotationPoint(-4.0F, 14.0F, -5.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 1, 8, 10);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-7.0F, 10.0F, -7.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14);
        this.shape1_3 = new ModelRenderer(this, 0, 19);
        this.shape1_3.setRotationPoint(-7.0F, 22.0F, -7.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 14, 2, 14);
        this.shape1_2 = new ModelRenderer(this, 23, 49);
        this.shape1_2.setRotationPoint(-6.0F, 14.0F, -6.0F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 12, 8, 1);
        this.shape1_4 = new ModelRenderer(this, 23, 49);
        this.shape1_4.setRotationPoint(-6.0F, 14.0F, 5.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 12, 8, 1);
        this.shape1_5 = new ModelRenderer(this, 0, 40);
        this.shape1_5.setRotationPoint(3.0F, 14.0F, -5.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 10);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_1.render(f5);
        this.shape1.render(f5);
        this.shape1_3.render(f5);
        this.shape1_2.render(f5);
        this.shape1_4.render(f5);
        this.shape1_5.render(f5);
    }

    public void renderModel(float f5){
        this.shape1_1.render(f5);
        this.shape1.render(f5);
        this.shape1_3.render(f5);
        this.shape1_2.render(f5);
        this.shape1_4.render(f5);
        this.shape1_5.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
