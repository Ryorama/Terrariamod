package kmerrill285.trewrite.entities.summoning;

import java.util.List;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.projectiles.EntitySummoningImpFireball;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntitySummoningImp extends MobEntity {

	public Entity owner;
	public long created;
	
	public EntitySummoningImp(EntityType<EntitySummoningImp> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntitySummoningImp(World worldIn) {
		super(EntitiesT.SUMMONING_IMP, worldIn);
	}
		
	public EntitySummoningImp(World worldIn, double x, double y, double z) {
		super(EntitiesT.SUMMONING_IMP, worldIn);
	}

	/**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
       double d0 = 64.0D * getRenderDistanceWeight();
       return distance < d0 * d0;
    }
	
	public EntitySize getSize(Pose pose) {
		return EntitySize.fixed(0.5f, 0.5f);
	}
	
	public boolean canUpdate() {
		return true;
	}
	
	public void canUpdate(boolean value) {
		
	}
	
	@Override
	public void collideWithEntity(Entity e) {
	}
	
	@Override
	protected void collideWithNearbyEntities() {
	}
	
	public AxisAlignedBB getCollisionBoundingBox() {
	      return null;
	   }
	
	private int age = 0;
	public float hoverStart = 0.1f;
	
	public boolean dead = false;
	public int getAge() {
		return age;
	}
	
	public boolean canDespawn() {
		return true;
	}
	public boolean isInvulnerable() {
		return true;
	}
	
	public void playHurtSound(DamageSource source) {
		
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}
	
	
	
	public boolean grabbed = false;
	@Override
	
	public void tick() {
		super.tick();
		this.noClip = true;
		this.setNoGravity(true);
		if (world.isRemote) return;
		
		
		if (this.owner == null) {
			remove();
			return;
		}
		
		if (this.owner instanceof LivingEntity) {
			LivingEntity e = (LivingEntity)owner;
			if (e.getHealth() <= 0) {
				remove();
				return;
			}
		}
		
		Vec3d targetPos = owner.getPositionVec();
		Entity target = null;
		if (WorldEvents.summoningTargets.get(owner.getScoreboardName()) != null) {
			target = WorldEvents.summoningTargets.get(owner.getScoreboardName());
			if (target instanceof LivingEntity) {
				LivingEntity entity = (LivingEntity)target;
				if (entity.getHealth() <= 0) {
					target = null;
					WorldEvents.summoningTargets.put(owner.getScoreboardName(), null);
				}
			}
		} else {
			List<Entity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(owner.getPositionVec().add(-25, -25, -25), owner.getPositionVec().add(25, 25, 25)));
			double dist = 1000;
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				if (entity instanceof IHostile) {
					double d = entity.getPositionVec().distanceTo(getPositionVec());
					if (d < dist) {
						dist = d;
						target = entity;
					}
				}
			}
		}
		
		if (target != null) {
			targetPos = target.getPositionVec();
			
			if (this.ticksExisted % (20 * 4) >= 20 * 4 - 10 * 3) {
				if (this.ticksExisted % 10 == 0) {
					EntitySummoningImpFireball spit = EntitiesT.SUMMONING_IMP_FIREBALL.create(world);
					spit.setPosition(posX, posY, posZ);
					
					Vec3d point = new Vec3d(posX - target.posX, (posY + 1) - target.posY, posZ - target.posZ).normalize();
					point = point.mul(2, 2, 2);
					
					spit.setMotion(-point.x, -point.y, -point.z);
					
					world.addEntity(spit);
				}
				
			}
		}
		
		if (getPositionVec().distanceTo(owner.getPositionVec().add(0, 4, 0)) > 25) {
			targetPos = owner.getPositionVec();
			WorldEvents.summoningTargets.put(owner.getScoreboardName(), null);
		}
		
		
		if (!world.isRemote()) {
			float speed = 2f;
			float acceleration = 0.08f;
			int dirX = posX < (targetPos.x + (rand.nextDouble() * 2 - 1) * 3) ? 1 : -1, dirY = posY < (targetPos.y + 4 + rand.nextDouble() * 2 - 1) ? 1 : -1, dirZ = posZ < (targetPos.z + (rand.nextDouble() * 2 - 1) * 3) ? 1 : -1;

			setMotion(getMotion().add(acceleration * dirX, acceleration * dirY, acceleration * dirZ));
			
			if (Math.abs(getMotion().x) >= speed) {
				getMotion().add(-acceleration * dirX, 0, 0);
			}
			if (Math.abs(getMotion().y) >= speed * 0.5) {
				getMotion().add(0, -acceleration * dirY * 0.5, 0);
			}
			if (Math.abs(getMotion().z) >= speed) {
				getMotion().add(0, 0, -acceleration * dirZ);
			}

			if (getPositionVec().distanceTo(targetPos.add(0, 4, 0)) > 4) {
				posX = lerp(posX, targetPos.x, 0.2);
				posY = lerp(posY, targetPos.y + 4, 0.5);
				posZ = lerp(posZ, targetPos.z, 0.2);
			}
		}
		
		setPosition(posX, posY, posZ);


		float f = MathHelper.sqrt(func_213296_b(getMotion()));
		this.rotationYaw = (float)(MathHelper.atan2(getMotion().z, getMotion().x) * (double)(180F / (float)Math.PI));
	    this.prevRotationYaw = this.rotationYaw;
//		
//		
//		setPosition(posX, posY, posZ);
//		setMotion(0, 0, 0);
	}

	public double lerp(double a, double b, double f) 
	{
	    return (a * (1.0 - f)) + (b * f);
	}
	
	public static EntitySummoningImp spawnOrb(World worldIn, BlockPos pos, Entity owner) {
		EntitySummoningImp orb = EntitiesT.SUMMONING_IMP.create(worldIn, null, null, null, pos, SpawnReason.EVENT, false, false);
		orb.owner = owner;
		orb.created = System.currentTimeMillis();
		
		int summons = 0;
		
		List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(orb, new AxisAlignedBB(orb.posX - 5, orb.posY - 5, orb.posZ - 5, orb.posX + 5, orb.posY + 5, orb.posZ + 5));
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof EntitySummoningImp) {
				if (((EntitySummoningImp)e).created < orb.created && ((EntitySummoningImp)e).owner == owner) {
					((EntitySummoningImp)e).setHealth(0);
					entities.remove(i);
					continue;
				}
			}
		}
		worldIn.addEntity(orb);
		return orb;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}