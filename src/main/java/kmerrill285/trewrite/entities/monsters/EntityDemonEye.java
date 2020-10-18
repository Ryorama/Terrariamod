package kmerrill285.trewrite.entities.monsters;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityDemonEye extends FlyingEntity implements IHostile {
	
	public float rx, ry, rz;
	
	public boolean collision;

	public boolean bounce;
	
	public double velX, velY, velZ;
	public double oldVelX, oldVelY, oldVelZ;
	
	public double speed = 2;
	public double acc = 0.01;
	
	public int money = 75;
	
	public int damage = 18;
	
	public float kbResist = 0.2f;
	
	 public EntityDemonEye(EntityType<? extends EntityDemonEye> type, World worldIn) {
		super(type, worldIn);
		init();
	}

    public EntityDemonEye(World world) {
    	super(EntitiesT.DEMON_EYE, world);
    	init();
    }
    
    public SoundEvent getHurtSound(DamageSource source) {
 	   return SoundsT.HIT1;
    }
    
    public void init() {
    	this.dataManager.set(EntityDemonEye.type_data, rand.nextInt(6));
    }
    
    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
    	
    	 switch (this.dataManager.get(EntityDemonEye.type_data).intValue()) {
    	 case 0:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2);
        	 this.damage = 18;
        	 this.kbResist = 0.2f;
    		 break;
    	 case 2:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2);
        	 this.damage = 18;
        	 this.kbResist = 0.2f;
    		 break;
    	 case 1:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(65);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4);
        	 this.damage = 18;
        	 this.kbResist = 0.3f;
    		 break;
    	 case 3:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4);
        	 this.damage = 20;
        	 this.kbResist = 0.0f;
    		 break;
    	 case 4:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(66);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4);
        	 this.damage = 14;
        	 this.kbResist = 0.28f;
    		 break;
    	 case 5:
    		 this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
        	 this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2);
        	 this.damage = 16;
        	 this.kbResist = 0.15f;
    		 break;
    	 }
         return spawnDataIn;
         
    }
    public AxisAlignedBB getCollisionBoundingBox() {
		return super.getCollisionBoundingBox();
    	
    }
    
    
    public void dropLoot(DamageSource source, boolean b) {
    	if (this.money > 0)
    	if (rand.nextDouble() <= 0.333f)
		EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.LENS, 1, null));
    	if (Util.isChristmas()) {
			if (rand.nextDouble() <= 0.0769) {
				EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, null));
			}
		}
    	if (money > 0) {
			EntityCoin.spawnCoin(world, getPosition(), EntityCoin.COPPER, money);
			
			if (source.getImmediateSource() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)source.getImmediateSource();
				if (player.getHealth() <= player.getMaxHealth()) {
					if (rand.nextInt(12) == 0) {
						EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
					}
				}
			}
    	} else {
    		EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
    	}
    }
    
    
    public void onCollideWithPlayer(PlayerEntity entityIn)
    {
    	if (entityIn != null)
    	if (entityIn.getPositionVector().distanceTo(getPositionVector()) <= 1.5f) {
			velX *= -2f;
			velY *= -2f;
			velZ *= -2f;
            this.dealDamage(entityIn);
    	}
    }
    
    public void tick() {

    	super.tick();
    	this.setNoGravity(true);
    	double motionY = this.getMotion().y;
    	double motionX = this.getMotion().x;
    	double motionZ = this.getMotion().z;
    	
    	if (!world.isRemote) { 
			motionY = 0;
			this.rotationPitch = 0;
			this.rotationYaw = 0;
			this.rotationYawHead = 0;
			World world = this.world;
			PlayerEntity target = null;
			double distance = 1000;
			for (int i = 0; i < world.getPlayers().size(); i++) {
				if (world.getPlayers().get(i).isCreative()) continue;
				double dist = world.getPlayers().get(i).getPositionVector().distanceTo(this.getPositionVector());
				if (dist < distance) {
					distance = dist;
					target = world.getPlayers().get(i);
				}
			}
			if (world.getBlockState(new BlockPos(getPosition().getX(), getPosition().getY() - 1, getPosition().getZ())).getBlock().getDefaultState() == Blocks.WATER.getDefaultState()) {
				if (velY < 0) {
					velY = 3;
				}
			}
			
			if (this.noClip == false) {
				if (world.getBlockState(new BlockPos(posX, posY - 0.5f, posZ)).getMaterial().isSolid()) {
					this.velY = 2.0f;
				}
			}
			
			if (bounce == true) {
				double absX = Math.abs(motionX);
				double absY = Math.abs(motionY);
				double absZ = Math.abs(motionZ);
				
				if (onGround == false) {
	//			if (absX > absY) {
					velX = oldVelX * -0.5;
					if (velX > 0 && velX < 2) {
						velX = 2;
					}
					if (velX < 0 && velX > -2) {
						velX = -2;
					}
	//			}
				
	//			if (absZ > absY) {
					velZ = oldVelZ * -0.5;
					if (velZ > 0 && velZ < 2) {
						velZ = 2;
					}
					if (velZ < 0 && velZ > -2) {
						velZ = -2;
					}
	//			}
				}
				
				if (this.onGround == true) {
					velY = oldVelY * -0.5;
					if (velY > 0 && velY < 1) {
						velY = 1;
					}
					if (velY < 0 && velY > -1) {
						velY = -1;
					}
				}
				
			}
			
			boolean night = world.getDayTime() % 24000L > 15000 && world.getDayTime() % 24000L < 22000;
			if (night == false) {
				velY = 2f;
			}
			else
			if (target != null) {
				if (velX > -4 && this.posX > target.posX + target.getWidth()) {
					velX -= 0.08;
					if (velX > 4) {
						velX -= 0.04;
					}
					else if (velX > 0) {
						velX -= 0.2;
					}
					if (velX < -4) {
						velX = -4;
					}
				} else if (velX < 4 && posX + 1 < target.posX) {
					velX += 0.08;
					if (velX < -4) {
						velX += 0.04;
					}
					else if (velX < 0) {
						velX += 0.2;
					}
					if (velX > 4) {
						velX = 4;
					}
				}
				
				if (velZ > -4 && this.posZ > target.posZ + target.getWidth()) {
					velZ -= 0.08;
					if (velZ > 4) {
						velZ -= 0.04;
					}
					else if (velZ > 0f) {
						velZ -= 0.2;
					}
					if (velZ < -4) {
						velZ = -4;
					}
				} else if (velZ < 4f && posZ + 1 < target.posZ) {
					velZ += 0.08f;
					if (velZ < -4) {
						velZ += 0.04;
					}
					else if (velZ < 0f) {
						velZ += 0.2;
					}
					if (velZ > 4) {
						velZ = 4;
					}
				}
				
				if (velY > -2.5 && posY > target.posY + target.getHeight()) {
					velY -= 0.1f;
					if (velY > 2.5) {
						velY -= 0.05;
					} else if (velY > 0f) {
						velY -= 0.15;
					}
					if (velY < -2.5) {
						velY = -2.5;
					}
				} else if (velY < 2.5 && posY + 1 < target.posY) {
					velY += 0.1f;
					if (velY < -2.5) {
						velY += 0.05;
					}
					else if (velY < 0) {
						velY += 0.15;
					}
					if (velY > 2.5) {
						velY = 2.5;
					}
				}
				
				
			}
			
			
			bounce = false;
			oldVelX = velX + 0;
			oldVelY = velY + 0;
			oldVelZ = velZ + 0;
			motionX = velX * 0.075f;
			motionY = velY * 0.075f;
			motionZ = velZ * 0.075f;
			
			this.rotationYaw = (float)Math.toDegrees(Math.atan2(velZ, velX)) - 90;

			
			if (this.hurtTime > 0) {
				if (this.getRevengeTarget() != null) {
					motionX = Math.cos(Math.toRadians(this.getRevengeTarget().rotationYaw + 90)) * 0.25f * Math.cos(Math.toRadians(-this.getRevengeTarget().rotationPitch));
					motionZ = Math.sin(Math.toRadians(this.getRevengeTarget().rotationYaw + 90)) * 0.25f * Math.cos(Math.toRadians(-this.getRevengeTarget().rotationPitch));
					motionY = Math.sin(Math.toRadians(-this.getRevengeTarget().rotationPitch)) * 0.25f;
					
					motionX *= 1.0f - kbResist;
					motionY *= 1.0f - kbResist;
					motionZ *= 1.0f - kbResist;
				}
			}
		}
    	this.setMotion(motionX, motionY, motionZ);
    }
    
    protected void dealDamage(LivingEntity entityIn) {
        if (this.isAlive()) {
           if (this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
              this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
              this.applyEnchantments(this, entityIn);
           }
        }

     }
    
    protected int getAttackStrength()
    {
        return damage;
    }
    
    public static final DataParameter<Integer> type_data = EntityDataManager.createKey(EntityDemonEye.class, DataSerializers.VARINT);
    
    @Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(type_data, 1);
	}
	
    
}
