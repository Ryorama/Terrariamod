package kmerrill285.trewrite.entities.models.wall_of_flesh;

import com.mojang.blaze3d.platform.GlStateManager;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshMouth;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ModelWallOfFleshMouth extends EntityModel<EntityWallOfFleshMouth> {
   private final RendererModel bb_main;
   private TheHungryAttachmentModel attachment;

   public ModelWallOfFleshMouth() {
      this.textureWidth = 960;
      this.textureHeight = 1155;
      this.bb_main = new RendererModel(this);
      this.bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
      this.bb_main.cubeList.add(new ModelBox(this.bb_main, 0, 0, -480.0F, -577.5F, 0.0F, 960, 1155, 1, 0.0F, false));
      this.attachment = new TheHungryAttachmentModel();
   }

   public void render(EntityWallOfFleshMouth entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GlStateManager.pushMatrix();
      GlStateManager.translatef(0.0F, 0.0F, 0.0F);
      GlStateManager.scalef(0.75F, 0.75F, 0.75F);
      this.bb_main.render(f5);
      GlStateManager.popMatrix();

      for(int d = 0; d < entity.dragging.size(); ++d) {
         GlStateManager.pushMatrix();
         GlStateManager.rotated((double)(-entity.rotationYaw), 0.0D, 1.0D, 0.0D);
         if (entity.owner != null) {
            Vec3d pos1 = entity.getPositionVec();
            if (entity.owner != null) {
               Vec3d pos2 = ((PlayerEntity)entity.dragging.get(d)).getPositionVec();
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

   }

   public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
      RendererModel.rotateAngleX = x;
      RendererModel.rotateAngleY = y;
      RendererModel.rotateAngleZ = z;
   }
}
