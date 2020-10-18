package kmerrill285.trewrite.entities.wyvern;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowBody;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowHead;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowTail;
import kmerrill285.trewrite.entities.monsters.worms.EntityWormBody;
import kmerrill285.trewrite.entities.projectiles.EntityVileSpit;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWyvernBody extends MobEntity implements IHostile {
	   public float velX;
	   public float velY;
	   public float velZ;
	   public float oldVelX;
	   public float oldVelY;
	   public float oldVelZ;
	   public int life = 8000;
	   public int[] ai = new int[5];
	   public PlayerEntity target = null;
	   public float rx;
	   public float ry;
	   public float rz;
	   public int money;
	   public double motionX;
	   public double motionY;
	   public double motionZ;
	   public MobEntity owner;
	   int dirX = 0;
	   int dirY = 0;
	   int dirZ = 0;

	   public EntityWyvernBody(EntityType type, World worldIn) {
	      super(type, worldIn);
	   }

	   public EntityWyvernBody(World worldIn) {
	      super(EntitiesT.WYVERN_BODY, worldIn);
	   }

	   @OnlyIn(Dist.CLIENT)
	   public boolean isInRangeToRenderDist(double distance) {
	      double d0 = 64.0D * getRenderDistanceWeight();
	      return distance < d0 * d0;
	   }

	   public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 40F);
	   }
	   
	   public void dropLoot(DamageSource source, boolean b) {
		   EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.FLIGHT_SOUL, 20 + rand.nextInt(15), (ItemModifier)null));
	      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
	         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
	      }

	      if (source.getImmediateSource() instanceof PlayerEntity) {
	         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
	         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
	            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
	         }
	      }

	      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
	   }
	   
	   @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8000);
	      this.setHealth(8000);
	      return spawnDataIn;
	   }

	   public void tick() {
	      this.noClip = true;
	      this.setNoGravity(true);
	      super.tick();
	      if (this.owner == null) {
	         if (this.ticksExisted > 20 && !this.world.isRemote) {
	            this.remove();
	         }
	      } else {
	         if (!this.world.isRemote) {
	            if (this.owner.getHealth() <= 0.0F) {
	               this.remove();
	            }

	            if (this.owner.getHealth() < this.getHealth()) {
	               this.setHealth(this.owner.getHealth());
	            }

	            if (this.getHealth() < this.owner.getHealth()) {
	               this.owner.setHealth(this.getHealth());
	            }
	         }

	         float dirX = (float)(this.owner.posX + 0.5D - (this.posX + 0.5D));
	         float dirY = (float)(this.owner.posY + 0.5D - (this.posY + 0.5D));
	         float dirZ = (float)(this.owner.posZ + 0.5D - (this.posZ + 0.5D));
	         this.rotationYaw = (float)Math.toDegrees(Math.atan2((double)dirZ, (double)dirX)) - 90.0F;
	         float length = (float)Math.sqrt((double)(dirX * dirX + dirY * dirY + dirZ * dirZ)) * 2.0F;
	         float dist = (length - 1.0F) / length;
	         float posX = dirX * dist;
	         float posY = dirY * dist;
	         float posZ = dirZ * dist;
	         this.velX = 0.0F;
	         this.velY = 0.0F;
	         this.velZ = 0.0F;
	         this.posX += (double)posX;
	         this.posY += (double)posY;
	         this.posZ += (double)posZ;
	         this.setMotion((double)posX, (double)posY, (double)posZ);
	      }

	      this.oldVelX = this.velX + 0.0F;
	      this.oldVelY = this.velY + 0.0F;
	      this.oldVelZ = this.velZ + 0.0F;
	      this.motionX = (double)(this.velX * 0.01F);
	      this.motionY = (double)(this.velY * 0.01F);
	      this.motionZ = (double)(this.velZ * 0.01F);
	   }

	   public boolean attackEntityFrom(DamageSource source, float amount) {
	      return source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING ? super.attackEntityFrom(source, amount) : false;
	   }

	   public static EntityWyvernBody spawnWormBody(World worldIn, BlockPos pos, float life, MobEntity owner) {
	      EntityWyvernBody head = (EntityWyvernBody)EntitiesT.WYVERN_BODY.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
	      head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)life);
	      head.setHealth(life);
	      head.money = 40;
	      head.owner = owner;
	      worldIn.addEntity(head);
	      return head;
	   }

	   protected void registerData() {
	      super.registerData();
	   }

	   public IPacket createSpawnPacket() {
	      return NetworkHooks.getEntitySpawningPacket(this);
	   }
	}
