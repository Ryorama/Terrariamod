package kmerrill285.stackeddimensions.networking;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Supplier;
import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketSendBlockClick {
   int x;
   int y;
   int z;
   int fx;
   int fy;
   int fz;
   int button;
   ResourceLocation dimension;
   private static HashMap mining = new HashMap();

   public CPacketSendBlockClick(int x, int y, int z, int fx, int fy, int fz, ResourceLocation dimension, int button) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.fx = fx;
      this.fy = fy;
      this.fz = fz;
      this.dimension = dimension;
      this.button = button;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeInt(this.fx);
      buf.writeInt(this.fy);
      buf.writeInt(this.fz);
      buf.writeResourceLocation(this.dimension);
      buf.writeInt(this.button);
      (new Thread() {
         public void run() {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var3) {
               var3.printStackTrace();
            }

            StackedDimensions.loadRenderers = true;

            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            StackedDimensions.loadRenderers = true;
         }
      }).start();
   }

   public CPacketSendBlockClick(PacketBuffer buf) {
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.fx = buf.readInt();
      this.fy = buf.readInt();
      this.fz = buf.readInt();
      this.dimension = buf.readResourceLocation();
      this.button = buf.readInt();
   }

   private void sendRenderBlock(BlockPos pos, BlockState state, ServerPlayerEntity player, DimensionConfiguration config) {
      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return player;
      }), new SPacketSendRenderBlock(pos, state));
      if (config != null) {
         Iterator var5 = player.getServer().getPlayerList().getPlayers().iterator();

         while(var5.hasNext()) {
            ServerPlayerEntity sp = (ServerPlayerEntity)var5.next();
            if (sp != player && config.getDimension() != null && sp.dimension == config.getDimension() && sp.posY <= 30.0D) {
               NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
                  return sp;
               }), new SPacketSendRenderBlock(pos, state));
            }
         }
      }

   }

   private void sendChunk(int x, int z, DimensionType t, ServerPlayerEntity player, DimensionConfiguration config) {
      DimensionManager.keepLoaded(t, true);
      ServerWorld dim = DimensionManager.getWorld(player.getServer(), t, true, true);
      DimensionManager.keepLoaded(t, true);
      Chunk chunk = dim.getChunk(x, z);
      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return player;
      }), new SPacketSendChunk(chunk, x, z, true));
      if (config != null) {
         Iterator var8 = player.getServer().getPlayerList().getPlayers().iterator();

         while(var8.hasNext()) {
            ServerPlayerEntity sp = (ServerPlayerEntity)var8.next();
            if (sp != player) {
               if (config.getAbove() != null && sp.dimension == config.getAbove() && sp.posY <= 30.0D) {
                  NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
                     return sp;
                  }), new SPacketSendChunk(chunk, x, z, true));
               }

               if (config.getBelow() != null && sp.dimension == config.getBelow() && sp.posY >= 225.0D) {
                  NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
                     return sp;
                  }), new SPacketSendChunk(chunk, x, z, true));
               }
            }
         }
      }

   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity player = ((Context)ctx.get()).getSender();
         DimensionConfiguration config;
         if (this.button == 1) {
            ForgeHooks.onRightClickBlock(player, Hand.MAIN_HAND, new BlockPos(this.x, this.y, this.z), Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz));
            config = DimensionConfigs.getConfig(player.dimension.getRegistryName());
            DimensionConfiguration c2 = null;
            if (player.posY < 100.0D && config != null && config.below != null) {
               c2 = DimensionConfigs.getConfig(config.below);
            }

            if (player.posY > 100.0D && config != null && config.above != null) {
               c2 = DimensionConfigs.getConfig(config.above);
            }

            if (c2 == null) {
               c2 = config;
            }

            Vec3d hitVec;
            Direction faceIn;
            BlockPos posIn;
            boolean isInside;
            BlockRayTraceResult result;
            ItemUseContext context;
            ItemStack stack;
            DimensionType typex;
            ServerWorld w;
            Field worldxx;
            if (player.posY < 100.0D) {
               if (this.y + this.fy > c2.getMax()) {
                  if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BlockItem) {
                     hitVec = new Vec3d((double)this.x, (double)(this.y - c2.getMax()), (double)this.z);
                     faceIn = Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz);
                     posIn = new BlockPos(this.x, this.y - c2.getMax(), this.z);
                     isInside = false;
                     result = new BlockRayTraceResult(hitVec, faceIn, posIn, isInside);
                     context = new ItemUseContext(player, Hand.MAIN_HAND, result);
                     if (!player.isCreative()) {
                        player.getHeldItemMainhand().getItem().onItemUse(context);
                        this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                     } else {
                        stack = new ItemStack(player.getHeldItemMainhand().getItem(), player.getHeldItemMainhand().getCount() + 0);
                        player.getHeldItemMainhand().getItem().onItemUse(context);
                        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
                        this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                     }
                  }
               } else if (config != null && config.getBelow() != null && player.getHeldItemMainhand().getItem() instanceof BlockItem) {
                  hitVec = new Vec3d((double)this.x, (double)this.y, (double)this.z);
                  faceIn = Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz);
                  posIn = new BlockPos(this.x, this.y, this.z);
                  isInside = false;
                  result = new BlockRayTraceResult(hitVec, faceIn, posIn, isInside);
                  context = new ItemUseContext(player, Hand.MAIN_HAND, result);

                  try {
                     worldxx = context.getClass().getDeclaredField(StackedDimensions.DEBUG ? "world" : "field_196006_g");

                     try {
                        Util.makeFieldAccessible(worldxx);
                     } catch (Exception var23) {
                        var23.printStackTrace();
                     }

                     typex = config.getBelow();
                     if (typex != null) {
                        w = player.getEntityWorld().getServer().getWorld(typex);

                        try {
                           worldxx.set(context, w);
                        } catch (IllegalArgumentException var21) {
                           var21.printStackTrace();
                        } catch (IllegalAccessException var22) {
                           var22.printStackTrace();
                        }
                     }
                  } catch (NoSuchFieldException var24) {
                     var24.printStackTrace();
                  } catch (SecurityException var25) {
                     var25.printStackTrace();
                  }

                  if (!player.isCreative()) {
                     player.getHeldItemMainhand().getItem().onItemUse(context);
                     this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getBelow(), player, config);
                     this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                  } else {
                     stack = new ItemStack(player.getHeldItemMainhand().getItem(), player.getHeldItemMainhand().getCount() + 0);
                     player.getHeldItemMainhand().getItem().onItemUse(context);
                     player.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
                     this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getBelow(), player, config);
                     this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                  }
               }
            }

            if (player.posY > 100.0D) {
               System.out.println(this.y + this.fy);
               if (this.y + this.fy < c2.getMin() + 256) {
                  if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof BlockItem) {
                     hitVec = new Vec3d((double)this.x, (double)(this.y + config.getMax()), (double)this.z);
                     faceIn = Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz);
                     posIn = new BlockPos(this.x, this.y + config.getMax(), this.z);
                     isInside = false;
                     result = new BlockRayTraceResult(hitVec, faceIn, posIn, isInside);
                     context = new ItemUseContext(player, Hand.MAIN_HAND, result);
                     if (!player.isCreative()) {
                        player.getHeldItemMainhand().getItem().onItemUse(context);
                        this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getAbove(), player, config);
                        this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                     } else {
                        stack = new ItemStack(player.getHeldItemMainhand().getItem(), player.getHeldItemMainhand().getCount() + 0);
                        player.getHeldItemMainhand().getItem().onItemUse(context);
                        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
                        this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getAbove(), player, config);
                        this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                     }
                  }
               } else if (config != null && config.getAbove() != null && player.getHeldItemMainhand().getItem() instanceof BlockItem) {
                  hitVec = new Vec3d((double)this.x, (double)this.y, (double)this.z);
                  faceIn = Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz);
                  posIn = new BlockPos(this.x, this.y, this.z);
                  isInside = false;
                  result = new BlockRayTraceResult(hitVec, faceIn, posIn, isInside);
                  context = new ItemUseContext(player, Hand.MAIN_HAND, result);

                  try {
                     worldxx = context.getClass().getDeclaredField(StackedDimensions.DEBUG ? "world" : "field_196006_g");

                     try {
                        Util.makeFieldAccessible(worldxx);
                     } catch (Exception var18) {
                        var18.printStackTrace();
                     }

                     typex = config.getAbove();
                     if (typex != null) {
                        w = player.getEntityWorld().getServer().getWorld(typex);

                        try {
                           worldxx.set(context, w);
                        } catch (IllegalArgumentException var16) {
                           var16.printStackTrace();
                        } catch (IllegalAccessException var17) {
                           var17.printStackTrace();
                        }
                     }
                  } catch (NoSuchFieldException var19) {
                     var19.printStackTrace();
                  } catch (SecurityException var20) {
                     var20.printStackTrace();
                  }

                  if (!player.isCreative()) {
                     player.getHeldItemMainhand().getItem().onItemUse(context);
                     this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getAbove(), player, config);
                     this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                  } else {
                     stack = new ItemStack(player.getHeldItemMainhand().getItem(), player.getHeldItemMainhand().getCount() + 0);
                     player.getHeldItemMainhand().getItem().onItemUse(context);
                     player.setItemStackToSlot(EquipmentSlotType.MAINHAND, stack);
                     this.sendChunk(context.getPos().getX() / 16, context.getPos().getZ() / 16, config.getAbove(), player, config);
                     this.sendRenderBlock(posIn, context.getWorld().getBlockState(posIn), player, config);
                  }
               }
            }
         }

         if (this.button == 0) {
            ForgeHooks.onLeftClickBlock(player, new BlockPos(this.x, this.y, this.z), Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz));
            config = DimensionConfigs.getConfig(player.dimension.getRegistryName());
            if (config != null) {
               DimensionType type;
               BlockPos bpos;
               ServerWorld world;
               CPacketSendBlockClick.MiningPos pos;
               boolean hasPos;
               Iterator var31;
               ServerWorld worldx;
               CPacketSendBlockClick.MiningPos p;
               BlockState state;
               float f;
               LeftClickBlock event;
               if (player.posY < 100.0D) {
                  type = config.getBelow();
                  if (type != null) {
                     if (player.isCreative()) {
                        world = player.getEntityWorld().getServer().getWorld(type);
                        world.setBlockState(new BlockPos(this.x, this.y, this.z), Blocks.AIR.getDefaultState());
                        this.sendChunk(this.x / 16, this.z / 16, config.getBelow(), player, config);
                        this.sendRenderBlock(new BlockPos(this.x, this.y, this.z), player.getEntityWorld().getBlockState(new BlockPos(this.x, this.y, this.z)), player, config);
                     } else {
                        bpos = new BlockPos(this.x, this.y, this.z);
                        pos = new CPacketSendBlockClick.MiningPos(bpos, type);
                        hasPos = false;
                        var31 = mining.keySet().iterator();

                        while(var31.hasNext()) {
                           p = (CPacketSendBlockClick.MiningPos)var31.next();
                           if (p.equals(pos)) {
                              pos = p;
                              hasPos = true;
                              break;
                           }
                        }

                        if (!hasPos) {
                           mining.put(pos, 0.0F);
                        }

                        worldx = player.getEntityWorld().getServer().getWorld(type);
                        state = worldx.getBlockState(bpos);
                        f = state.getPlayerRelativeBlockHardness(player, worldx, bpos);
                        event = ForgeHooks.onLeftClickBlock(player, bpos, Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz));
                        if (!event.isCanceled() && (player.isCreative() || event.getUseItem() != Result.DENY)) {
                           mining.put(pos, (Float)mining.get(pos) + f * 0.5F);
                           if ((Float)mining.get(pos) > 1.0F) {
                              worldx.getBlockState(bpos).onReplaced(worldx, bpos, Blocks.AIR.getDefaultState(), false);
                              Block.spawnDrops(worldx.getBlockState(bpos), worldx, bpos);
                              worldx.setBlockState(new BlockPos(this.x, this.y, this.z), Blocks.AIR.getDefaultState());
                              mining.remove(pos);
                              this.sendChunk(this.x / 16, this.z / 16, config.getBelow(), player, config);
                              this.sendRenderBlock(new BlockPos(this.x, this.y, this.z), player.getEntityWorld().getBlockState(new BlockPos(this.x, this.y, this.z)), player, config);
                           }
                        }
                     }
                  }
               }

               if (player.posY > 100.0D) {
                  type = config.getAbove();
                  if (type != null) {
                     if (player.isCreative()) {
                        world = player.getEntityWorld().getServer().getWorld(type);
                        world.setBlockState(new BlockPos(this.x, this.y, this.z), Blocks.AIR.getDefaultState());
                        this.sendChunk(this.x / 16, this.z / 16, config.getAbove(), player, config);
                        this.sendRenderBlock(new BlockPos(this.x, this.y, this.z), player.getEntityWorld().getBlockState(new BlockPos(this.x, this.y, this.z)), player, config);
                     } else {
                        bpos = new BlockPos(this.x, this.y, this.z);
                        pos = new CPacketSendBlockClick.MiningPos(bpos, type);
                        hasPos = false;
                        var31 = mining.keySet().iterator();

                        while(var31.hasNext()) {
                           p = (CPacketSendBlockClick.MiningPos)var31.next();
                           if (p.equals(pos)) {
                              pos = p;
                              hasPos = true;
                              break;
                           }
                        }

                        if (!hasPos) {
                           mining.put(pos, 0.0F);
                        }

                        worldx = player.getEntityWorld().getServer().getWorld(type);
                        state = worldx.getBlockState(bpos);
                        f = state.getPlayerRelativeBlockHardness(player, worldx, bpos);
                        event = ForgeHooks.onLeftClickBlock(player, bpos, Direction.getFacingFromVector((float)this.fx, (float)this.fy, (float)this.fz));
                        if (!event.isCanceled() && (player.isCreative() || event.getUseItem() != Result.DENY)) {
                           mining.put(pos, (Float)mining.get(pos) + f * 0.5F);
                           if ((Float)mining.get(pos) > 1.0F) {
                              worldx.getBlockState(bpos).onReplaced(worldx, bpos, Blocks.AIR.getDefaultState(), false);
                              Block.spawnDrops(worldx.getBlockState(bpos), worldx, bpos);
                              worldx.setBlockState(new BlockPos(this.x, this.y, this.z), Blocks.AIR.getDefaultState());
                              mining.remove(pos);
                              this.sendChunk(this.x / 16, this.z / 16, config.getAbove(), player, config);
                              this.sendRenderBlock(new BlockPos(this.x, this.y, this.z), player.getEntityWorld().getBlockState(new BlockPos(this.x, this.y, this.z)), player, config);
                           }
                        }
                     }
                  }
               }
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }

   private class MiningPos {
      private BlockPos pos;
      private DimensionType type;

      public MiningPos(BlockPos pos, DimensionType type) {
         this.pos = pos;
         this.type = type;
      }

      public boolean equals(Object p) {
         if (!(p instanceof CPacketSendBlockClick.MiningPos)) {
            return false;
         } else {
            return this.type.equals(((CPacketSendBlockClick.MiningPos)p).type) && this.pos.equals(((CPacketSendBlockClick.MiningPos)p).pos);
         }
      }
   }
}
