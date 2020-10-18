package kmerrill285.trewrite.entities.monsters;

import javax.annotation.Nullable;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.EntityStar;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityLavaSlime extends SlimeEntity {
   public EntityLavaSlime(EntityType type, World worldIn) {
      super(type, worldIn);
      this.init();
   }

   public EntityLavaSlime(World world) {
      super(EntitiesT.ICE_SLIME, world);
      this.init();
   }

   public void init() {
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
      this.setHealth(25.0F);
   }

   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(0.0D);
      return spawnDataIn;
   }

   protected void setSlimeSize(int size, boolean resetHealth) {
      this.setPosition(this.posX, this.posY, this.posZ);
      this.recalculateSize();
      if (resetHealth) {
         this.setHealth(this.getMaxHealth());
      }

      this.experienceValue = 0;
   }

   public void dropLoot(DamageSource source, boolean b) {
      EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.GEL, Util.randomValue(1, 3, this.rand), (ItemModifier)null));
      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
      }

      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, 25);
      if (source.getImmediateSource() instanceof PlayerEntity) {
         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
         }

         if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MANA).getScorePoints() < ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.MAX_MANA).getScorePoints() && this.rand.nextBoolean()) {
            EntityStar.spawnStar(this.getEntityWorld(), this.getPosition());
         }
      }

   }

   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 64.0D * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public void remove() {
      super.remove();
   }

   public int getSlimeSize() {
	      return this.getHealth() > 0.0F ? 3 : 1;
   }
   
   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   protected int getJumpDelay() {
      return this.rand.nextInt(40) + 10;
   }

   public void applyEntityCollision(Entity entityIn) {
      super.applyEntityCollision(entityIn);
   }

   public void onCollideWithPlayer(PlayerEntity entityIn) {
      if (entityIn != null && this.canDamagePlayer()) {
         this.dealDamage(entityIn);
      }

   }

   protected void dealDamage(LivingEntity entityIn) {
      if (this.isAlive()) {
         int i = this.getSlimeSize();
         if (this.getDistanceSq(entityIn) < 0.6D * (double)i * 0.6D * (double)i && this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
            this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            this.applyEnchantments(this, entityIn);
         }
      }

   }

   protected boolean canDamagePlayer() {
      return true;
   }

   protected int getAttackStrength() {
      return 7;
   }

   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   public int getVerticalFaceSpeed() {
      return 0;
   }

   protected boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   protected boolean makesSoundOnLand() {
      return this.getSlimeSize() > 2;
   }
}
