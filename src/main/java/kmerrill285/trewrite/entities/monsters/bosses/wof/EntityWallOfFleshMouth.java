package kmerrill285.trewrite.entities.monsters.bosses.wof;

import java.util.ArrayList;
import java.util.List;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWallOfFleshMouth extends FlyingEntity implements IEntityAdditionalSpawnData {

	public boolean ALREADY_SPAWNED = false;
	public boolean REMOVED = false;
	
	public ArrayList<PlayerEntity> dragging = new ArrayList<PlayerEntity>();
	public EntityWallOfFlesh owner;
	
	public EntityWallOfFleshMouth(EntityType<EntityWallOfFleshMouth> type, World worldIn) {
		super(type, worldIn);
		init();
	}
	
	public EntityWallOfFleshMouth(World worldIn) {
		super(EntitiesT.WALL_OF_FLESH_MOUTH, worldIn);
		init();
	}
	
	 public SoundEvent getHurtSound(DamageSource source) {
		   return SoundsT.HIT13;
	 }
	
	public void init() {
		int players = world.getPlayers().size();
	}
	
	public boolean canDespawn(double distanceToClosestPlayer) {
		return REMOVED;
	}
	
	@Override
	public void dropLoot(DamageSource source, boolean b) {
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
	 
	 public void checkDespawn() {
		 return;
	 }
	 
	 public void livingTick() {
		 super.livingTick();
		 this.ignoreFrustumCheck = true;
		 this.preventDespawn();
		 
	 }
	 
	 @Override
	 public void doBlockCollisions() {
		 
	 }
	 
	 
	 public float getCollisionBorderSize() {
		 return super.getCollisionBorderSize();
	 }
	 
	 @Override
	 public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		 
	 }
	 public AxisAlignedBB getBoundingBox() {
		 int size = 25;
		 return new AxisAlignedBB(getPositionVec().add(-size / 2, -size / 2, -size / 2), getPositionVec().add(size / 2, size / 2, size / 2));
	 }
	 
	 public AxisAlignedBB getBoundingBox(Pose pose) {
		 int size = 2;
		 return new AxisAlignedBB(getPositionVec().add(-size / 2, -size / 2, -size / 2), getPositionVec().add(size / 2, size / 2, size / 2));
	 }
		public int timeBetweenLeeches = 30 * 20;

	 public void tick() {
		
		 this.preventDespawn();
		 super.tick();
		 this.noClip = false;
		 
		 
		 List<? extends PlayerEntity> players = world.getPlayers();
		 for (int i = 0; i < players.size(); i++) {
			 if (players.get(i).posY > 200 || players.get(i).getPositionVec().distanceTo(getPositionVec()) >= 800) {
				 if (!this.dragging.contains(players.get(i))) {
					 this.dragging.add(players.get(i));
				 }
				 
			 }
		 }
		 
		 if (!world.isRemote()) {
			 if (owner == null) {
				 remove();
				 return;
			 }
			 if (owner.bossHealth <= 0) {
				 remove();
				 return;
			 }
			 if (owner.getHealth() <= 0) {
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
			 this.posZ = owner.posZ + 35 * Math.cos(Math.toRadians(owner.renderYawOffset));
			 this.posX = owner.posX + 35 * Math.sin(Math.toRadians(owner.renderYawOffset + 180));
			 
			 this.setRenderYawOffset(owner.renderYawOffset);
			 this.prevRenderYawOffset = renderYawOffset;
			 this.rotationYaw = owner.renderYawOffset;
			 this.prevRotationYaw = owner.renderYawOffset;
		 }
		 
		 List<Entity> eyes = world.getEntitiesWithinAABB(EntityWallOfFleshEye.class, new AxisAlignedBB(getPositionVec().add(-100, -200, -100), getPositionVec().add(100, 200, 100)));
		 posY = 0;
		 for (int i = 0; i < eyes.size(); i++) {
			 posY += eyes.get(i).posY;
			
		 }

		 if (!world.isRemote) {
			 if (this.ticksExisted % this.timeBetweenLeeches == 0 && this.ticksExisted > 0) {
				 int leechGroup = 3 + rand.nextInt(2);
				 for (int i = 0; i < leechGroup; i++) {
					 this.shootAtTarget(world.getPlayers().get(rand.nextInt(world.getPlayers().size())));

				 }
			 }
		 }
		 
		 posY /= 1.65;
		 
		 this.setPositionAndUpdate(posX, posY, posZ);
	 }
	 
	 
	 public void shootAtTarget(PlayerEntity target) {
		 int height = -100 + rand.nextInt(15);
			EntityLeechHead spit = EntityLeechHead.spawnWormHead(world, getPosition().add(0, height, 0), 60);
			spit.setPosition(posX, posY+height, posZ);
			
			Vec3d point = new Vec3d(posX - target.posX, (posY + 1+height) - target.posY, posZ - target.posZ).normalize();
			point = point.mul(2, 2, 2);
			
			spit.setMotion(-point.x, -point.y, -point.z);
		 }
	 
	 public void onCollideWithPlayer(PlayerEntity player) {
			player.attackEntityFrom(DamageSource.causeMobDamage(this), 50);
		}
	 
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD)
			return super.attackEntityFrom(source, amount);
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING || source == DamageSource.IN_FIRE || source == DamageSource.LAVA || source == DamageSource.ON_FIRE) return false;
    	if (owner != null) {
    		amount -= 12;
    		if (amount < 1) amount = 1;
    		owner.bossHealth -= amount;
    		if (!world.isRemote)
    		System.out.println("HIT MOUTH! " + owner.bossHealth);

    	}
    	this.performHurtAnimation();
    	return false;
//    	return super.attackEntityFrom(source, amount);
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
