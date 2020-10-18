package kmerrill285.trewrite.entities.monsters.bosses.wof;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.projectiles.EntityVileSpit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityLeechBody extends MobEntity implements IHostile {

	public float velX, velY, velZ;
	public float oldVelX, oldVelY, oldVelZ;
	
	public int life = 60;
	
	public int[] ai = new int[5];
	public PlayerEntity target = null;
	public float rx, ry, rz;
	
	public int money;
	
	public double motionX, motionY, motionZ;
	public MobEntity owner;

	public EntityLeechBody(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntityLeechBody(World worldIn) {
		super(EntitiesT.LEECH_BODY, worldIn);
	}
	
	
	
	/**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
       double d0 = 64.0D * getRenderDistanceWeight();
       return distance < d0 * d0;
    }
	
	public void onCollideWithPlayer(PlayerEntity player) {
		player.attackEntityFrom(DamageSource.causeMobDamage(this), 22);
	}
	
	int dirX = 0;
	int dirY = 0;
	int dirZ = 0;
	
	public void tick() {
		
		this.noClip = true;
		this.setNoGravity(true);
		super.tick();
		
		
		if (this.owner == null) {

			if (this.ticksExisted > 20)
			if (!world.isRemote) {
				this.remove();
			}
		} else {
			
			if (!world.isRemote) {
				if (this.owner.getHealth() <= 0) {

					this.remove();
				}
				
				if (owner.getHealth() < getHealth()) {
					setHealth(owner.getHealth());
				}
				
				if (getHealth() < owner.getHealth()) {
					owner.setHealth(getHealth());
				}
			}
			
			
			
			float dirX = (float)(owner.posX + 0.5f - (posX + 0.5f));
			float dirY = (float)(owner.posY + 0.5f - (posY + 0.5f));
			float dirZ = (float)(owner.posZ + 0.5f - (posZ + 0.5f));
			
			this.rotationYaw = (float)Math.toDegrees(Math.atan2(dirZ, dirX)) - 90;
			
			float length = (float)Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ) * 2f;
			
			
			
			float dist = (length - (float)1f) / length;
			float posX = dirX * dist;
			float posY = dirY * dist;
			float posZ = dirZ * dist;
			
			velX = 0;
			velY = 0;
			velZ = 0;
			
			this.posX = this.posX + posX;
			this.posY = this.posY + posY;
			this.posZ = this.posZ + posZ;
			
			this.setMotion(posX, posY, posZ);
			
		}
		
		this.oldVelX = velX + 0;
		this.oldVelY = velY + 0;
		this.oldVelZ = velZ + 0;
		
		this.motionX = velX * 0.01f;
		this.motionY = velY * 0.01f;
		this.motionZ = velZ * 0.01f;
		
	}
	public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING) return false;
    	return super.attackEntityFrom(source, amount);
    }
	public static EntityLeechBody spawnWormBody(World worldIn, BlockPos pos, float life, MobEntity owner) {
		EntityLeechBody head = EntitiesT.LEECH_BODY.create(worldIn, null, null, null, pos, SpawnReason.EVENT, false, false);		
		head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(life);
		head.setHealth(life);
		head.money = 0;
		head.owner = owner;
		worldIn.addEntity(head);
		return head;
	}
	
	@Override
	protected void registerData() {
		super.registerData();
	}
	
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
