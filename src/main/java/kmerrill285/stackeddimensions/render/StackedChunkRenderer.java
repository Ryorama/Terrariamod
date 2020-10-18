package kmerrill285.stackeddimensions.render;

import java.lang.reflect.Field;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import kmerrill285.stackeddimensions.networking.CPacketRequestChunks;
import kmerrill285.stackeddimensions.networking.CPacketRequestEntities;
import kmerrill285.stackeddimensions.networking.CPacketSendBlockClick;
import kmerrill285.stackeddimensions.networking.NetworkHandler;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;

@OnlyIn(Dist.CLIENT)
public class StackedChunkRenderer {
   public static int NO_DIMENSION = -2;
   public static boolean rendering = false;
   public static boolean resetWorldRenderer = false;
   private static int cx;
   private static int cy;
   private static int cz;
   private static int lcx;
   private static int lcy;
   private static int lcz;

   public static void update(float partialTicks, ResourceLocation dimensionType, int height, boolean up) {
      Entity entity = Minecraft.getInstance().getRenderViewEntity();
      if (!rendering) {
         rendering = true;
         if (StackedDimensions.gameRenderer == null) {
            StackedDimensions.gameRenderer = new GameRenderer(Minecraft.getInstance(), Minecraft.getInstance().getResourceManager());
         }

         if (StackedDimensions.renderInfo == null) {
            StackedDimensions.renderInfo = new StackedRenderInfo();
         }

         if (StackedDimensions.renderWorld == null) {
            StackedDimensions.renderWorld = new RenderWorld(Minecraft.getInstance().getConnection(), new WorldSettings(0L, GameType.CREATIVE, false, false, WorldType.FLAT), DimensionType.OVERWORLD, 3, Minecraft.getInstance().getProfiler(), StackedDimensions.worldRenderer);
            ((RenderWorld)StackedDimensions.renderWorld).setProfile(Minecraft.getInstance().getProfiler());
         }

         if (StackedDimensions.worldRenderer == null) {
            StackedDimensions.worldRenderer = new StackedWorldRenderer(Minecraft.getInstance());
            StackedDimensions.worldRenderer.setWorldAndLoadRenderers((ClientWorld)StackedDimensions.renderWorld);
         }

         ((RenderWorld)StackedDimensions.renderWorld).setProfile(Minecraft.getInstance().getProfiler());
         ClippingHelper clip2 = new ClippingHelper() {
            public boolean isBoxInFrustum(double a, double b, double c, double d, double e, double f) {
               return true;
            }
         };
         if (StackedDimensions.renderWorld != null) {
            StackedDimensions.camera = (ArmorStandEntity)EntityType.ARMOR_STAND.create(entity.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, new BlockPos(0, 0, 0), SpawnReason.EVENT, false, false);
         }

         if (StackedDimensions.camera == null) {
            GlStateManager.popMatrix();
            rendering = false;
         } else {
            StackedDimensions.camera.rotationPitch = 0.0F;
            StackedDimensions.camera.rotationYaw = 0.0F;
            StackedDimensions.camera.rotationYawHead = 0.0F;
            if (StackedDimensions.worldRenderer == null) {
               StackedDimensions.worldRenderer = new StackedWorldRenderer(Minecraft.getInstance());
            }

            if (up) {
               if (entity.posY <= 100.0D) {
                  StackedDimensions.renderWorld = null;
                  StackedDimensions.reloadOverworld = false;
                  rendering = false;
                  return;
               }

               if (!StackedDimensions.reloadOverworld) {
                  StackedDimensions.reloadOverworld = true;
                  StackedDimensions.worldRenderer.setWorldAndLoadRenderers((RenderWorld)StackedDimensions.renderWorld);
               }
            }

            if (!up) {
               if (entity.posY >= 100.0D) {
                  StackedDimensions.renderWorld = null;
                  StackedDimensions.reloadOverworld = false;
                  rendering = false;
                  return;
               }

               if (!StackedDimensions.reloadOverworld) {
                  StackedDimensions.reloadOverworld = true;
                  StackedDimensions.worldRenderer.setWorldAndLoadRenderers((RenderWorld)StackedDimensions.renderWorld);
               }
            }

            if (StackedDimensions.loadRenderers) {
               StackedDimensions.worldRenderer.setWorldAndLoadRenderers((RenderWorld)StackedDimensions.renderWorld);
               StackedDimensions.loadRenderers = false;
            }

            if (!StackedDimensions.setupWorld) {
               StackedDimensions.setupWorld = true;
               (new Thread() {
                  public void run() {
                     try {
                        Thread.sleep(10000L);
                     } catch (InterruptedException var2) {
                        var2.printStackTrace();
                     }

                     StackedChunkRenderer.resetWorldRenderer = true;
                  }
               }).start();
            }

            if (resetWorldRenderer) {
               StackedDimensions.worldRenderer.setWorldAndLoadRenderers((RenderWorld)StackedDimensions.renderWorld);
               resetWorldRenderer = false;
            }

            ICamera icamera = new Frustum(clip2);
            Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            StackedDimensions.renderInfo.update(StackedDimensions.camera.world, StackedDimensions.camera, true, true, partialTicks);
            Vec3d eyePos = StackedDimensions.gameRenderer.getActiveRenderInfo().getProjectedView();
            StackedDimensions.renderInfo.setPosition(new Vec3d(eyePos.x, eyePos.y - height, eyePos.z));
            Vec3d f;
            double zoom;
            if (Minecraft.getInstance().gameSettings.thirdPersonView == 1) {
               f = entity.getForward();
               zoom = 4.0D;
               zoom = 0.0D;
               StackedDimensions.renderInfo.setPosition(new Vec3d(eyePos.x - f.x * zoom, eyePos.y - height - f.y * zoom, eyePos.z - f.z * zoom));
            }

            if (Minecraft.getInstance().gameSettings.thirdPersonView == 2) {
               f = entity.getForward();
               zoom = -4.0D;
               zoom = 0.0D;
               StackedDimensions.renderInfo.setPosition(new Vec3d(eyePos.x - f.x * zoom, eyePos.y - height - f.y * zoom, eyePos.z - f.z * zoom));
            }

            StackedDimensions.renderInfo.setDirection(0.0F, 0.0F);
            double d0 = StackedDimensions.renderInfo.getProjectedView().x;
            double d1 = StackedDimensions.renderInfo.getProjectedView().y;
            double d2 = StackedDimensions.renderInfo.getProjectedView().z;
            icamera.setPosition(d0, d1, d2);
            Entity e;
            if (StackedDimensions.renderEntities.size() > 0) {
               ((RenderWorld)StackedDimensions.renderWorld).refreshEntities();

               for(int i = 0; i < StackedDimensions.renderEntities.size(); ++i) {
                  e = (Entity)StackedDimensions.renderEntities.get(i);
                  ((RenderWorld)StackedDimensions.renderWorld).addEntity(i, e);
               }

               StackedDimensions.renderEntities.clear();
            }

            StackedDimensions.worldRenderer.func_224745_a(StackedDimensions.renderInfo);
            Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            StackedDimensions.renderWorld.getChunkProvider().getLightManager().tick(Integer.MAX_VALUE, true, true);
            StackedDimensions.worldRenderer.setupTerrain(StackedDimensions.renderInfo, icamera, StackedDimensions.frameCount++, true);
            GlStateManager.matrixMode(5888);
            GlStateManager.pushMatrix();
            GlStateManager.disableAlphaTest();
            StackedDimensions.worldRenderer.renderBlockLayer(BlockRenderLayer.SOLID, StackedDimensions.renderInfo);
            GlStateManager.enableAlphaTest();
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, Minecraft.getInstance().gameSettings.mipmapLevels > 0);
            StackedDimensions.worldRenderer.renderBlockLayer(BlockRenderLayer.CUTOUT_MIPPED, StackedDimensions.renderInfo);
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            StackedDimensions.worldRenderer.renderBlockLayer(BlockRenderLayer.CUTOUT, StackedDimensions.renderInfo);
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            GlStateManager.shadeModel(7424);
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.matrixMode(5888);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            RenderHelper.enableStandardItemLighting();
            StackedDimensions.worldRenderer.renderEntities(StackedDimensions.renderInfo, icamera, partialTicks);
            StackedDimensions.gameRenderer.disableLightmap();
            GlStateManager.matrixMode(5888);
            GlStateManager.popMatrix();
            boolean edit = false;
            e = null;
            double rd = Minecraft.getInstance().playerController.getBlockReachDistance();
            Vec3d vec3d = entity.getEyePosition(partialTicks).subtract(0.0D, height, 0.0D);
            Vec3d vec3d1 = entity.getLook(partialTicks);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * rd, vec3d1.y * rd, vec3d1.z * rd);
            RayTraceResult result = StackedDimensions.renderWorld.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, BlockMode.OUTLINE, FluidMode.NONE, entity));
            if (result != null && result.getHitVec() != null) {
               GlStateManager.disableAlphaTest();
               Minecraft.getInstance().getProfiler().endStartSection("outline");
               if (!ForgeHooksClient.onDrawBlockHighlight(StackedDimensions.worldRenderer, StackedDimensions.renderInfo, result, 0, partialTicks)) {
                  StackedDimensions.worldRenderer.drawSelectionBox(StackedDimensions.renderInfo, result, 0);
               }

               GlStateManager.enableAlphaTest();
            }

            if (result != null) {
               label293: {
                  if (Minecraft.getInstance().objectMouseOver != null) {
                     RayTraceResult o = Minecraft.getInstance().objectMouseOver;
                     if (o.getHitVec() != null && o.getHitVec().distanceTo(entity.getPositionVec()) < result.getHitVec().add(0.0D, height, 0.0D).distanceTo(entity.getPositionVec())) {
                        break label293;
                     }
                  }

                  StackedDimensions.blockHit = result;
                  if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown() && result.getType() == Type.BLOCK) {
                     BlockRayTraceResult r = (BlockRayTraceResult)result;
                     StackedDimensions.renderWorld.setBlockState(r.getPos().add(-16, 0, 0), StackedDimensions.renderWorld.getBlockState(r.getPos()));
                     if (Minecraft.getInstance().player.swingProgressInt == 3) {
                        Minecraft.getInstance().world.playSound(r.getPos().add(0, height, 0), StackedDimensions.renderWorld.getBlockState(r.getPos()).getSoundType().getHitSound(), SoundCategory.PLAYERS, 1.0F, 1.0F, true);
                     }

                     Minecraft.getInstance().player.swingArm(Hand.MAIN_HAND);
                     BlockParticleData particles = new BlockParticleData(ParticleTypes.BLOCK, StackedDimensions.renderWorld.getBlockState(r.getPos()));
                     Minecraft.getInstance().world.addParticle(particles, r.getPos().getX() + 0.5D, (double)(r.getPos().getY() + height) + 0.5D, r.getPos().getZ() + 0.5D, r.getFace().getXOffset() * 0.5D, r.getFace().getYOffset() * 0.5D, r.getFace().getZOffset() * 0.5D);
                     NetworkHandler.INSTANCE.sendToServer(new CPacketSendBlockClick(r.getPos().getX(), r.getPos().getY(), r.getPos().getZ(), r.getFace().getXOffset(), r.getFace().getYOffset(), r.getFace().getZOffset(), dimensionType, 0));
                     edit = true;
                  }

                  if (Minecraft.getInstance().gameSettings.keyBindUseItem.isKeyDown() && entity instanceof PlayerEntity) {
                     Vec3d vec = new Vec3d(result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
                     BlockPos p = new BlockPos(vec);
                     vec.subtract(p.getX(), p.getY(), p.getZ());
                     if (!Minecraft.getInstance().player.isSwingInProgress && result.getType() == Type.BLOCK) {
                        BlockRayTraceResult r = (BlockRayTraceResult)result;
                        StackedDimensions.renderWorld.setBlockState(r.getPos(), StackedDimensions.renderWorld.getBlockState(r.getPos()));
                        Minecraft.getInstance().player.swingArm(Hand.MAIN_HAND);
                        NetworkHandler.INSTANCE.sendToServer(new CPacketSendBlockClick(r.getPos().getX(), r.getPos().getY(), r.getPos().getZ(), r.getFace().getXOffset(), r.getFace().getYOffset(), r.getFace().getZOffset(), dimensionType, 1));
                        edit = true;
                     }
                  }
               }
            }

            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE, SourceFactor.ONE, DestFactor.ZERO);
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            Minecraft.getInstance().getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            GlStateManager.disableBlend();
            GlStateManager.enableCull();
            GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            GlStateManager.depthMask(false);
            Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.shadeModel(7425);
            StackedDimensions.worldRenderer.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, StackedDimensions.renderInfo);
            GlStateManager.shadeModel(7424);
            GlStateManager.depthMask(true);
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            if (StackedDimensions.renderWorld != null) {
               World world = StackedDimensions.renderWorld;

               for(int i = 0; i < 2; ++i) {
                  double wall = 0.25D;
                  BlockPos pos;
                  BlockState state;
                  if (entity.getMotion().x < 0.0D) {
                     pos = entity.getPosition().up(i).add(entity.getMotion().x + 0.5D, 0.0D, 0.0D).subtract(new Vec3i(0, height, 0));
                     if (world.isAreaLoaded(pos, pos)) {
                        state = world.getBlockState(pos);
                        if (state.getMaterial().blocksMovement() && entity.posX < (pos.getX() + 1) && entity.posZ > pos.getZ() + wall && entity.posZ < (pos.getZ() + 1) - wall) {
                           if (entity.posY >= (pos.getY() + height) && entity.posY < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d((pos.getX() + 1) - entity.posX, 0.0D, 0.0D));
                              entity.setMotion(0.0D, entity.getMotion().y, entity.getMotion().z);
                           }

                           if (entity.posY + 1.0D >= (pos.getY() + height) && entity.posY + 1.0D < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d((pos.getX() + 1) - entity.posX, 0.0D, 0.0D));
                              entity.setMotion(0.0D, entity.getMotion().y, entity.getMotion().z);
                           }
                        }
                     }
                  }

                  if (entity.getMotion().x > 0.0D) {
                     pos = entity.getPosition().up(i).add(entity.getMotion().x, 0.0D, 0.0D).subtract(new Vec3i(0, height, 0));
                     if (world.isAreaLoaded(pos, pos)) {
                        state = world.getBlockState(pos);
                        if (state.getMaterial().blocksMovement() && entity.posX > pos.getX() && entity.posZ > pos.getZ() + wall && entity.posZ < (double)(pos.getZ() + 1) - wall) {
                           if (entity.posY >= (double)(pos.getY() + height) && entity.posY < (double)(pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(pos.getX() - entity.posX, 0.0D, 0.0D));
                              entity.setMotion(0.0D, entity.getMotion().y, entity.getMotion().z);
                           }

                           if (entity.posY + 1.0D >= (pos.getY() + height) && entity.posY + 1.0D < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(pos.getX() - entity.posX, 0.0D, 0.0D));
                              entity.setMotion(0.0D, entity.getMotion().y, entity.getMotion().z);
                           }
                        }
                     }
                  }

                  if (entity.getMotion().z < 0.0D) {
                     pos = entity.getPosition().up(i).add(0.0D, 0.0D, entity.getMotion().z + 0.5D).subtract(new Vec3i(0, height, 0));
                     if (world.isAreaLoaded(pos, pos)) {
                        state = world.getBlockState(pos);
                        if (state.getMaterial().blocksMovement() && entity.posZ < (pos.getZ() + 1) && entity.posX > pos.getX() + wall && entity.posX < (pos.getX() + 1) - wall) {
                           if (entity.posY >= (pos.getY() + height) && entity.posY < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(0.0D, 0.0D, (pos.getZ() + 1) - entity.posZ));
                              entity.setMotion(entity.getMotion().x, entity.getMotion().y, 0.0D);
                           }

                           if (entity.posY + 1.0D >= (pos.getY() + height) && entity.posY + 1.0D < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(0.0D, 0.0D, (pos.getZ() + 1) - entity.posZ));
                              entity.setMotion(entity.getMotion().x, entity.getMotion().y, 0.0D);
                           }
                        }
                     }
                  }

                  if (entity.getMotion().z > 0.0D) {
                     pos = entity.getPosition().up(i).add(0.0D, 0.0D, entity.getMotion().z).subtract(new Vec3i(0, height, 0));
                     if (world.isAreaLoaded(pos, pos)) {
                        state = world.getBlockState(pos);
                        if (state.getMaterial().blocksMovement() && entity.posZ > pos.getZ() && entity.posX > pos.getX() + wall && entity.posX < (pos.getX() + 1) - wall) {
                           if (entity.posY >= (pos.getY() + height) && entity.posY < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(0.0D, 0.0D, pos.getZ() - entity.posZ));
                              entity.setMotion(entity.getMotion().x, entity.getMotion().y, 0.0D);
                           }

                           if (entity.posY + 1.0D >= (pos.getY() + height) && entity.posY + 1.0D < (pos.getY() + height) + 0.5D) {
                              entity.move(MoverType.SELF, new Vec3d(0.0D, 0.0D, pos.getZ() - entity.posZ));
                              entity.setMotion(entity.getMotion().x, entity.getMotion().y, 0.0D);
                           }
                        }
                     }
                  }

                  if (entity.getMotion().y > 0.0D) {
                     pos = entity.getPosition().up(i).add(0.0D, entity.getMotion().y, 0.0D).subtract(new Vec3i(0, height, 0));
                     if (world.isAreaLoaded(pos, pos)) {
                        state = world.getBlockState(pos);
                        if (state.getMaterial().blocksMovement() && entity.posY + 1.0D > (pos.getY() + height - 1) && entity.posX > pos.getX() && entity.posX < (pos.getX() + 1) && entity.posZ > pos.getZ() && entity.posZ < (pos.getZ() + 1)) {
                           entity.move(MoverType.SELF, new Vec3d(0.0D, (pos.getY() + height - 1) - (entity.posY + 1.0D), 0.0D));
                           entity.setMotion(entity.getMotion().x, 0.0D, entity.getMotion().z);
                        }
                     }
                  }
               }
            }

            ++StackedDimensions.ticks;
            if (StackedDimensions.renderWorld != null) {
               if (StackedDimensions.worldRenderer == null) {
                  StackedDimensions.worldRenderer = new StackedWorldRenderer(Minecraft.getInstance());
               }

               ((RenderWorld)StackedDimensions.renderWorld).worldRenderer = StackedDimensions.worldRenderer;
            }

            if (StackedDimensions.ticks % 2 == 0) {
               NetworkHandler.INSTANCE.sendToServer(new CPacketRequestEntities(entity.posX, entity.posY - (double)height, entity.posZ, dimensionType));
            }

            if (cx != lcx || cy != lcy || cz != lcz || edit || StackedDimensions.needsToReset) {
               TryLoadChunks(entity, dimensionType);
            }

            rendering = false;
            if (entity != null) {
               lcx = cx;
               lcy = cy;
               lcz = cz;
               cx = entity.getPosition().getX() / 16;
               cy = entity.getPosition().getY() / 16;
               cz = entity.getPosition().getZ() / 16;
            }

         }
      }
   }

   private static void TryLoadChunks(Entity entity, ResourceLocation dimensionType) {
      int distmin = 3;
      int dist = distmin + 4;

      for(int x = -dist; x < dist; ++x) {
         for(int z = -dist; z < dist; ++z) {
            int X = entity.chunkCoordX + x;
            int Z = entity.chunkCoordZ + z;
            if (Math.abs(x) < distmin && Math.abs(z) < distmin) {
               if (StackedDimensions.renderWorld == null || StackedDimensions.loadRenderers) {
                  StackedDimensions.loadingChunks = false;
                  return;
               }

               if (!StackedDimensions.renderWorld.chunkExists(X, Z) || StackedDimensions.renderWorld.getChunk(X, Z) != null && StackedDimensions.renderWorld.getChunk(X, Z) instanceof EmptyChunk) {
                  if (entity != null) {
                     NetworkHandler.INSTANCE.sendToServer(new CPacketRequestChunks(X, Z, dimensionType));
                  }
               } else {
                  Field d;
                  try {
                     d = Chunk.class.getDeclaredField(StackedDimensions.DEBUG ? "dirty" : "field_76643_l");
                     Util.makeFieldAccessible(d);
                  } catch (Exception var11) {
                  }

                  if (StackedDimensions.renderWorld.getChunk(X, Z) != null) {
                     if (entity != null) {
                        NetworkHandler.INSTANCE.sendToServer(new CPacketRequestChunks(X, Z, dimensionType));
                     }

                     try {
                        d = Chunk.class.getDeclaredField(StackedDimensions.DEBUG ? "dirty" : "field_76643_l");
                        Util.makeFieldAccessible(d);
                        d.set(StackedDimensions.renderWorld.getChunk(X, Z), false);
                     } catch (Exception var10) {
                     }
                  }
               }
            } else if (StackedDimensions.renderWorld != null && ((RenderWorld)StackedDimensions.renderWorld).chunkProvider != null && ((RenderWorld)StackedDimensions.renderWorld).chunkProvider.chunkExists(X, Z)) {
               ((RenderWorld)StackedDimensions.renderWorld).chunkProvider.unloadChunk(X, Z);
            }
         }
      }

      StackedDimensions.loadingChunks = false;
   }
}
