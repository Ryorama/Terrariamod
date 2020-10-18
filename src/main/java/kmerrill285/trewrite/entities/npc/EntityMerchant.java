package kmerrill285.trewrite.entities.npc;

import kmerrill285.trewrite.core.inventory.GuideGUI;
import kmerrill285.trewrite.core.inventory.npc.MerchantGui;
import kmerrill285.trewrite.core.inventory.npc.MerchantShopGui;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityMerchant extends CreatureEntity implements IEntityAdditionalSpawnData {
	   public static final int COPPER = 0;
	   public static final int SILVER = 1;
	   public static final int GOLD = 2;
	   public static final int PLATINUM = 3;
	   public int coin = 0;
	   public int amount = 1;
	   private int age = 0;
	   public float hoverStart = 0.1F;
	   public boolean dead = false;
	   public boolean grabbed = false;
	   
	   public static boolean lhp = false;
	   
	   GuideGUI gui;

	   public EntityMerchant(EntityType type, World worldIn) {
	      super(type, worldIn);
	      this.setHealth(200);
	   }

	   public EntityMerchant(World worldIn) {
	      super(EntitiesT.GUIDE, worldIn);
	   }

	   public EntityMerchant(World worldIn, double x, double y, double z) {
	      super(EntitiesT.GUIDE, worldIn);
	      this.setPosition(x, y, z);
	   }

	   protected void registerGoals() {
	      this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
	      this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
	      this.goalSelector.addGoal(9, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 3.0F, 1.0F));
	      this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	   }

	   @OnlyIn(Dist.CLIENT)
	   public boolean isInRangeToRenderDist(double distance) {
	      double d0 = 64.0D * getRenderDistanceWeight();
	      return distance < d0 * d0;
	   }

	   public EntitySize getSize(Pose pose) {
	      return new EntitySize(0.5F, 2.0F, true);
	   }

	   public boolean canUpdate() {
	      return true;
	   }

	   public void canUpdate(boolean value) {
	   }

	   public int getAge() {
	      return this.age;
	   }

	   public boolean canDespawn() {
	      return false;
	   }

	   public boolean isInvulnerable() {
	      return false;
	   }

	   public void playHurtSound(DamageSource source) {
	   }

	   public boolean attackEntityFrom(DamageSource source, float amount) {
	      return source.getTrueSource() instanceof PlayerEntity ? false : super.attackEntityFrom(source, amount);
	   }

	   public void tick() {
	      super.tick();
	      
	      if (lhp == true) {
		      if (ScoreboardEvents.getScore(Minecraft.getInstance().player.getWorldScoreboard(), Minecraft.getInstance().player, ScoreboardEvents.COINS).getScorePoints() >= 300) {
		    	  	EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.LESSER_HEALING_POTION, 1, (ItemModifier)null));
			   		ScoreboardEvents.getScore(Minecraft.getInstance().player.getWorldScoreboard(), Minecraft.getInstance().player, ScoreboardEvents.COINS).setScorePoints(ScoreboardEvents.getScore(Minecraft.getInstance().player.getWorldScoreboard(), Minecraft.getInstance().player, ScoreboardEvents.COINS).getScorePoints() - 300);
			   		lhp = false;
		      } else {
					System.out.println("Not enough money!");
			   		lhp = false;
				}
	      }
	   }
	   
	   public float lerp(float a, float b, float f) {
	      return a * (1.0F - f) + b * f;
	   }

	   protected void registerData() {
	      super.registerData();
	   }

	   public void read(CompoundNBT compound) {
	      super.read(compound);
	   }

	   public boolean processInteract(PlayerEntity player, Hand hand) {
		   
		   if (world.isRemote) {
			   MerchantShopGui.openGui();
		   }
		   
		   return true;	   
	   }
	   
	   public void readAdditional(CompoundNBT compound) {
	   }

	   public void writeAdditional(CompoundNBT compound) {
	   }

	   public void writeSpawnData(PacketBuffer buffer) {
	   }

	   public void readSpawnData(PacketBuffer additionalData) {
	   }

	   public static EntityMerchant spawnMerchan(World worldIn, BlockPos pos, int type, int value) {
		   EntityMerchant guide = (EntityMerchant)EntitiesT.MERCHANT.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
	      worldIn.addEntity(guide);
	      return guide;
	   }

	   public IPacket createSpawnPacket() {
	      return NetworkHooks.getEntitySpawningPacket(this);
	   }
	}
