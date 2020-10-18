package kmerrill285.trewrite.entities.monsters.bosses.wof;

import java.util.List;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWallOfFleshEye extends FlyingEntity implements IEntityAdditionalSpawnData {

	public boolean ALREADY_SPAWNED = false;
	public boolean REMOVED = false;
	
	
	public EntityWallOfFlesh owner;
	
	public boolean upper = false;
	
	public final int SHOOT_TIME = 15 * 20;
	public int timeUntilShoot = 15 * 20;
	public int lasers = 0;
	public int timeUntilNextLaser = 10;
	
	public EntityWallOfFleshEye(EntityType<EntityWallOfFleshEye> type, World worldIn) {
		super(type, worldIn);
		init();
	}
	
	public EntityWallOfFleshEye(World worldIn) {
		super(EntitiesT.WALL_OF_FLESH_EYE, worldIn);
		init();
	}
	
	 public SoundEvent getHurtSound(DamageSource source) {
		   return SoundsT.HIT13;
	 }
	   
	
	public void init() {
		
	}
	
	public boolean canDespawn(double distanceToClosestPlayer) {
		return REMOVED;
	}
	
	@Override
	public void dropLoot(DamageSource source, boolean b) {
		System.out.println("FUCH");
        EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PWNHAMMER, 1, (ItemModifier)null));
        EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.BREAKER_BLADE, 1, (ItemModifier)null));
		if (REMOVED) return;
	}
	 @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
      	return true;
    }
	 
	 public int getBrightnessForRender() {
		 return 15728688;
	 }
	 
	 public boolean isInRangeToRender3d(double x, double y, double z) {
		 return true;
	 }
	 
	 
	 protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
	      return 25;
	   }
	 
	 public AxisAlignedBB getBoundingBox() {
		 float size = 25;
		 return new AxisAlignedBB(posX - size, posY - size + size, posZ - size, posX + size, posY + size + size, posZ + size);
	 }
	 
	 @Override
	 public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		 
	 }
	 
	 public void tick() {
		 this.setNoGravity(true);
		 this.noClip = true;
		 
		 if (this.hurtTime > 0) this.hurtTime --;
		 if (this.getHealth() <= 0)
		 super.tick();
		 
		 if (!world.isRemote()) {
			 if (owner == null) {
				 remove();
				 EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PWNHAMMER, 1, (ItemModifier)null));
				 EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.BREAKER_BLADE, 1, (ItemModifier)null));
				 return;
			 }
			 if (owner.bossHealth <= 0) {
				 remove();
				 EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PWNHAMMER, 1, (ItemModifier)null));
				 EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.BREAKER_BLADE, 1, (ItemModifier)null));
				 return;
			 }
			 if (owner.getHealth() <= 0) {
				EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PWNHAMMER, 1, (ItemModifier)null));
				EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.BREAKER_BLADE, 1, (ItemModifier)null));
				remove();
				return;
			 }
		 } 
		 else {
			 List<Entity> entities = world.getEntitiesWithinAABB(EntityWallOfFlesh.class, new AxisAlignedBB(getPosition().add(-100, -200, -100), getPosition().add(100, 200, 100)));
			 if (entities.size() > 0) {
				 this.owner = (EntityWallOfFlesh) entities.get(0);
			 }
		 }
		 
		 if (this.getHealth() <= 0)  {
			EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PWNHAMMER, 1, (ItemModifier)null));
			EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.BREAKER_BLADE, 1, (ItemModifier)null));
		 }
		 
		 this.ignoreFrustumCheck = true;
		 
		 if (!world.isRemote()) {
				ALREADY_SPAWNED = true;
				
				if (this.getHealth() <= 0) {
					return;
				}
				this.preventDespawn();
		    	if (!world.isRemote) {
					boolean despawn = true;
			    	for (int i = 0; i < world.getPlayers().size(); i++) {
			    		if (world.getPlayers().get(i).getHealth() > 0) {
			    			despawn = false;
			    			break;
			    		}
			    	}
			    	
			    	if (despawn) {REMOVED = true; remove();}
				} else {
					return;
				}
			    	
			    this.hurtResistantTime = 0;
			    this.hurtTime = 0;
		 }
		 
		 if (owner != null) {
			 this.posZ = owner.posZ + 25 * Math.cos(Math.toRadians(owner.renderYawOffset));
			 this.posX = owner.posX + 25 * Math.sin(Math.toRadians(owner.renderYawOffset + 180));
			 
			 this.setRenderYawOffset(owner.getYaw(0));
			 this.prevRenderYawOffset = renderYawOffset;
			 this.rotationYaw = owner.rotationYaw;
			 this.prevRotationYaw = owner.rotationYaw;
		 }
		 
		 int dir = 0;
		 double speed = 0.25f;
		 upper = posY > 128;
		 if (upper && posY < 150) posY = 150;
		 if (!upper && posY > 128 - 30) posY = 128 - 30;
		 if (this.upper == true) {
			 dir = 1;
			 for (int i = 0; i < 35; i++) {
				 if (world.getBlockState(getPosition().up(i)).getMaterial().blocksMovement()) {
					 dir = -1;
				 }
			 }
		 } else {
			 dir = -1;
			 for (int i = 0; i < 10; i++) {
				 if (world.getBlockState(getPosition().down(i)).getMaterial().blocksMovement()) {
					 dir = 1;
				 }
			 }			 
		 }
		 this.setMotion(0, dir * speed, 0);
		 posY += getMotion().y;
		 
		 List<? extends PlayerEntity> players = world.getPlayers();
		 double distance = 1000000;
		 PlayerEntity target = null;
		 for (int i = 0; i < players.size(); i++) {
			 double d = players.get(i).getPositionVec().distanceTo(getPositionVec());
			 if (d < distance) {
				 target = players.get(i);
				 distance = d;
			 }
		 }
		 if (posY <= 20) {
			 posY = 20;
		 }
		 if (posY >= 180) {
			 posY = 180;
		 }
		 if (target!= null) {
			 if (!target.isAlive()) {
				 EntityWallOfFlesh.isWoFA = false;
			 }
				this.lookAt(EntityAnchorArgument.Type.EYES, target.getPositionVec());
				if (this.timeUntilShoot > 0) {
					this.timeUntilShoot--;
				} else {
					if (this.lasers < 4) {
						if (this.timeUntilNextLaser > 0) {
							this.timeUntilNextLaser--;
						} else {
							this.timeUntilNextLaser = 10;
							this.shootAtTarget(target);
							lasers++;
						}
					} else {
						lasers = 0;
						this.timeUntilShoot = this.SHOOT_TIME;
						this.timeUntilNextLaser = 10;
					}
				}
		 }
		 
		
//		 
//		 this.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
//		 
//		 
//		 this.hurtTime = 0;
//		 this.hurtResistantTime = 0;
		 
	 }
	 
	 public void shootAtTarget(PlayerEntity target) {
		EntityEyeLaser spit = EntitiesT.EYE_LASER.create(world);
		spit.setPosition(posX, posY+25, posZ);
		
		Vec3d point = new Vec3d(posX - target.posX, (posY + 1+25) - target.posY, posZ - target.posZ).normalize();
		point = point.mul(2, 2, 2);
		
		spit.setMotion(-point.x, -point.y, -point.z);
		
		world.addEntity(spit);
	 }
	 
	 
	 public void onCollideWithPlayer(PlayerEntity player) {
			player.attackEntityFrom(DamageSource.causeMobDamage(this), 50);
		}
	 
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD)
			return super.attackEntityFrom(source, amount);
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING || source == DamageSource.IN_FIRE || source == DamageSource.LAVA || source == DamageSource.ON_FIRE) return false;
    	
    	if (owner != null) {
    		owner.bossHealth -= amount;
    		if (!world.isRemote)
    		System.out.println("HIT! " + owner.bossHealth);
    	}
    	this.performHurtAnimation();
    	super.attackEntityFrom(source, 0);
    	return false;

    }
	
	@Override
	protected void registerData() {
		super.registerData();
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.getBoolean("spawned")) {
			REMOVED = true; if (!world.isRemote()) remove();
			}
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		if (compound.getBoolean("spawned")) {
			REMOVED = true; if (!world.isRemote()) remove();
			}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		compound.putBoolean("spawned", ALREADY_SPAWNED);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		buffer.writeBoolean(ALREADY_SPAWNED);
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		if (additionalData.readBoolean()) {
			REMOVED = true; if (!world.isRemote()) remove();
			}
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
