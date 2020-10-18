package kmerrill285.trewrite.entities.models.flails;

import kmerrill285.trewrite.entities.projectiles.flails.EntityBallOHurt;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelBallOHurtChain extends EntityModel {
   private final RendererModel Main;
   private final RendererModel Spikes;
   private final RendererModel bone2;
   private final RendererModel bone;
   private final RendererModel Spikes2;
   private final RendererModel bone3;
   private final RendererModel bone4;

   public ModelBallOHurtChain() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.Main = new RendererModel(this);
      this.Main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.Main.cubeList.add(new ModelBox(this.Main, 0, 35, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));
      this.Spikes = new RendererModel(this);
      this.Spikes.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bone2 = new RendererModel(this);
      this.bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.setRotationAngle(this.bone2, 0.0F, -1.5708F, 0.0F);
      this.Spikes.addChild(this.bone2);
      this.bone = new RendererModel(this);
      this.bone.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Spikes.addChild(this.bone);
      this.Spikes2 = new RendererModel(this);
      this.Spikes2.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.setRotationAngle(this.Spikes2, 3.1416F, 0.0F, 0.0F);
      this.bone3 = new RendererModel(this);
      this.bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.setRotationAngle(this.bone3, 0.0F, -1.5708F, 0.0F);
      this.Spikes2.addChild(this.bone3);
      this.bone4 = new RendererModel(this);
      this.bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Spikes2.addChild(this.bone4);
   }

   public void render(EntityBallOHurt entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.Main.render(f5);
      this.Spikes.render(f5);
      this.Spikes2.render(f5);
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
