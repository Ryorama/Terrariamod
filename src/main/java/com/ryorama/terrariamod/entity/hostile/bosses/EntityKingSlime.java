package com.ryorama.terrariamod.entity.hostile.bosses;

import java.util.ArrayList;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.entity.IBoss;
import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityKingSlime extends LivingEntity implements IBoss, IAnimatable {
	//PugzAreCute: Looks like constructor missing, Error: Error:(39, 14) All entities must have a constructor that takes one net.minecraft.world.World parameter.
	
	public EntityKingSlime(EntityType<? extends LivingEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		if (world.isClient) {
			this.setBossIcon();
			this.activateBoss();
		}
                if (!world.isClient()) {
	    	     for (int i = 0; i <= this.world.getServer().getPlayerManager().getPlayerList().size() - 1; i++) {
		    	this.world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("The Eye of Cthulhu has awoken!").formatted(Formatting.BOLD).formatted(Formatting.LIGHT_PURPLE), false);
	    	     }
                }
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(2000);
		setHealth(getMaxHealth());
		this.getDataTracker().startTracking(EntityKingSlime.TELEPORTING, false);
		this.getDataTracker().startTracking(EntityKingSlime.TICKS_BEFORE_JUMP, 0);

	}

        public EntityKingSlime(World worldIn) {
		super(EntitiesT.KING_SLIME, worldIn);
		if (world.isClient) {
			this.setBossIcon();
			this.activateBoss();
		}
                if (!world.isClient()) {
	    	     for (int i = 0; i <= this.world.getServer().getPlayerManager().getPlayerList().size() - 1; i++) {
		    	this.world.getServer().getPlayerManager().getPlayerList().get(i).sendMessage(new TranslatableText("The Eye of Cthulhu has awoken!").formatted(Formatting.BOLD).formatted(Formatting.LIGHT_PURPLE), false);
	    	     }
                }
		this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(2000);
		setHealth(getMaxHealth());
		this.getDataTracker().startTracking(EntityKingSlime.TELEPORTING, false);
		this.getDataTracker().startTracking(EntityKingSlime.TICKS_BEFORE_JUMP, 0);

	}
	
	 
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return TAudio.NPC_HIT1;
	}
		  
	protected SoundEvent getDeathSound() {
		return TAudio.NPC_KILL1;
	}
	

	@Override
	public void onPlayerCollision(PlayerEntity playerIn) {
		super.onPlayerCollision(playerIn);
		
		playerIn.damage(DamageSource.mob(this), 40);
	}
	
	private AnimationFactory factory = new AnimationFactory(this);

	public float velX;
	public float velY;
	public float velZ;
		
	public boolean prevOnGround;
	public int teleportationTimer;
	public boolean teleport2Void;
	
	public float prevScale = 1.0F;
	  
	public float scale = 1.0F;
	
	public PlayerEntity target;
	
	public double prevDist2Player;
	  
	public int failedAttempts;
	
	public boolean playShrinkAnim;
	
	public boolean playGrowAnim;
	  
	public static final TrackedData<Integer> TICKS_BEFORE_JUMP = DataTracker.registerData(EntityDemonEye.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Boolean> TELEPORTING = DataTracker.registerData(EntityDemonEye.class, TrackedDataHandlerRegistry.BOOLEAN);
	
	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public EntityProps props2;
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (playShrinkAnim == true) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kingslime.shrink", false));
			playShrinkAnim = false;
		}
		
		if (playGrowAnim == true) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kingslime.grow", false));
			playGrowAnim = false;	
		}
		
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}


 	public void tick() { 	
 		
 		 boolean startTeleport = false;
 		
 		 double distance = 1000.0D;
		   
		 for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPos().distanceTo(this.getPos());
	              if (dist < distance) {
	                 distance = dist;
	                 target = (PlayerEntity)this.world.getPlayers().get(i);
	              }
		   }
 		 	
 		if (target != null) {
 			if (target.getHealth() <= 0) {
 				this.remove(RemovalReason.DISCARDED);
				this.defeatedBoss();
 			}
 		}
 		
 		boolean onGround = this.onGround && this.verticalCollision;
 		
 	    if (!this.world.isClient) {
 		
	 	    if (this.prevOnGround != onGround && onGround) {
	 	      onLand();
	 	    }
	 	    
	 	    if (this.teleportationTimer > 0 && target != null) {
	 	    	setTicksBeforeJump(5);
	 	    	if (--this.teleportationTimer == 0) {
	 	    		if (this.teleport2Void) {
	 	    			return;
	 	    		} else {
	 	    			this.fallDistance = 0;
	 	    			startTeleport = true;
	 	    			this.setPosition(target.getPos().x, target.getPos().y, target.getPos().z);
	 	    			this.updatePosition(target.getPos().x, target.getPos().y, target.getPos().z);
	 	    			this.dataTracker.set(EntityKingSlime.TELEPORTING, Boolean.valueOf(false));
	 	    		}
	 	    	}
	 	    } else {
	 	    	this.teleportationTimer = 0;
	 	    }
	 	    
	 	    if (onGround && this.teleportationTimer <= 0) {
	 	    	setTicksBeforeJump(getTicksBeforeJump() - 1); 
	 	    }
	 	    
	 	    if (getTicksBeforeJump() <= 0 && onGround) {
	 	    	if (!world.isClient && this.isAlive()) {
	 	    		hop(); 
	 	    	}
	 	    }
	 	    
	 	   this.prevOnGround = onGround;
	 	   this.prevScale = this.scale;
	 	   
	 	   boolean tp = this.dataTracker.get(EntityKingSlime.TELEPORTING).booleanValue();
	 	   this.scale = MathHelper.clamp(this.scale + (tp ? -0.05F : 0.1F), 0.0F, 1.0F);
	 	  
	 	   if (random.nextInt(30) == 0) {
	 			EntityBlueSlime bs = new EntityBlueSlime(EntitiesT.BLUE_SLIME, world);
	 			bs.setPosition(this.getPos().x, this.getPos().y, this.getPos().z);
	 			world.spawnEntity(bs);
	 		}
				 		
			if (!this.onGround && jumping) {
				jumping = false;
			}
 	    }
			
		this.updateBossHealthBar();
		
		if (this.getDataTracker().get(EntityKingSlime.TELEPORTING).booleanValue() == true && startTeleport == true) {
 			playShrinkAnim = true;
 			startTeleport = false;
		}
		
		if (this.getDataTracker().get(EntityKingSlime.TELEPORTING).booleanValue() == false) {
 			playGrowAnim = true;
		}
		
 		super.tick();
	}

	private void setTicksBeforeJump(int ticks) {
		this.dataTracker.set(EntityKingSlime.TICKS_BEFORE_JUMP, Integer.valueOf(ticks));		
	}


	private void hop() {
		setTicksBeforeJump(30);
		this.velY = 1.1f;
	    if (this.getPos().x >= target.getPos().x) {
		    this.velX = -1;
	    } else {
		    this.velX = 1;
	    }
	    
	    if (this.getPos().x >= target.getPos().x) {
		    this.velZ = -1;
	    } else {
		    this.velZ = 1;
	    }
		this.setVelocity(velX, velY, velZ);
	    
	    //Might need this: this.onGround = false;
	}


	private int getTicksBeforeJump() {
		return this.dataTracker.get(EntityKingSlime.TICKS_BEFORE_JUMP).intValue();
	}
	
	 
	public void initiateTeleportTo() {
	    this.teleportationTimer = 120;
	    this.dataTracker.set(EntityKingSlime.TELEPORTING, Boolean.valueOf(true));
	}

	private void onLand() {
		 if (this.target != null && this.teleportationTimer <= 0) {
		      double dist = this.distanceTo(target);
		      if (this.prevDist2Player - dist > 0.5D && dist < 48.0D) {
		        this.failedAttempts = 0;
		      } else if (++this.failedAttempts > 3) {
		    	initiateTeleportTo();
		        this.failedAttempts = 0;
		        this.prevDist2Player = Double.MAX_VALUE;
		      } 
		      this.prevDist2Player = Math.min(this.prevDist2Player, dist);
		 } 
	}
	
	public void dropLoot(DamageSource source, boolean b) {
		 if (this.getHealth() <= 0) {
				this.defeatedBoss();
				return;
		 }
	 }

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return armorItems;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		
	}

	@Override
	public Arm getMainArm() {
		return Arm.LEFT;
	}
	
	@Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		double d = this.getBoundingBox().getAverageSideLength() * 4.0D;
		if (Double.isNaN(d) || d == 0.0D) {
			d = 4.0D;
		}
		d *= 64.0D;
		return distance < d * d;
	}
	
	@Environment(EnvType.CLIENT)
	public Box getVisibilityBoundingBox() {
		return this.getBoundingBox();
	}

	@Override
	public SoundEvent setBossMusic() {
		return TAudio.BOSS1;
	}

	@Override
	public Identifier bossIcon() {
		return new Identifier(TerrariaMod.MODID, "textures/ui/boss_icons/kingslime_icon.png");
	}

	@Override
	public float getBossHealth() {
		return this.getHealth();
	}

	@Override
	public float getBossMaxHealth() {
		return this.getMaxHealth();
	}
}
