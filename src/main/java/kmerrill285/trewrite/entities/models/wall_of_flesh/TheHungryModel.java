package kmerrill285.trewrite.entities.models.wall_of_flesh;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.monsters.bosses.wof.TheHungryEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.Vec3d;

public class TheHungryModel extends EntityModel<TheHungryEntity> {
   private final RendererModel bb_main;
   private TheHungryAttachmentModel attachment;

   public TheHungryModel() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -16.0F, -32.0F, -16.0F, 32, 32, 32, 0.0F, false));
      this.attachment = new TheHungryAttachmentModel();
   }

   public void render(TheHungryEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.bb_main.render(f5);
      GlStateManager.pushMatrix();
      GlStateManager.rotated((double)(-entity.rotationYaw), 0.0D, 1.0D, 0.0D);
      if (entity.owner != null && entity.firstPos != null) {
         Vec3d pos1 = entity.getPositionVec();
         if (entity.owner != null) {
            Vec3d pos2 = entity.owner.getPositionVec().add(entity.firstPos).subtract(entity.owner.firstPos);
            double dist = pos1.distanceTo(pos2);
            double xx = pos2.x - pos1.x;
            double zz = pos1.z - pos2.z;
            double yy = pos1.y - pos2.y;
            Vec3d pos3 = (new Vec3d(xx, yy, zz)).normalize();
            double scale = 1.0D;
            if (dist > 0.0D) {
               for(double i = 0.0D; i < dist; i += 0.25D) {
                  if (i > 0.0D) {
                     GlStateManager.pushMatrix();
                     GlStateManager.translated(pos3.x * i, pos3.y * i - (scale + 0.5D) + 1.0D * ((dist - i) / dist), pos3.z * i);
                     GlStateManager.scaled(scale, scale, scale);
                     this.attachment.render((MobEntity)entity, f, f1, f2, f3, f4, f5);
                     GlStateManager.popMatrix();
                  }
               }
            }
         }
      }

      GlStateManager.popMatrix();
   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
