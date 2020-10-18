package kmerrill285.trewrite.entities.models.worms;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.MobEntity;

/**
 * Worm - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelEowHead extends EntityModel<MobEntity> {
    public RendererModel shape1;
    public RendererModel shape1_1;
    public RendererModel shape1_2;

    public ModelEowHead() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1 = new RendererModel(this, 0, 16);
        this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1.addBox(2.0F, 0.0F, -8.0F, 1, 1, 4, 0.0F);
        this.shape1_2 = new RendererModel(this, 0, 16);
        this.shape1_2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_2.addBox(-3.0F, 0.0F, -8.0F, 1, 1, 4, 0.0F);
        this.shape1_1 = new RendererModel(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape1_1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(MobEntity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
        this.shape1_2.render(f5);
        this.shape1_1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
