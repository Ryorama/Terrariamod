package kmerrill285.trewrite.entities.monsters.bosses.wof;

import java.util.List;

import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWallOfFlesh extends FlyingEntity implements IEntityAdditionalSpawnData {

	public boolean ALREADY_SPAWNED = false;
	public boolean REMOVED = false;
	
	public int bossHealth = 4500;
	public int maxHealth;
	public Direction direction = null;
	public boolean setDirection = false;
	
	public static boolean isWoFA = false;
	public static boolean isWoFA2 = false;
	
	public EntityWallOfFlesh(EntityType<EntityWallOfFlesh> type, World worldIn) {
		super(type, worldIn);
		isWoFA = true;
		init();
	}
	
	public EntityWallOfFlesh(World worldIn) {
		super(EntitiesT.WALL_OF_FLESH, worldIn);
		init();
	}
	
	public void init() {
		int players = world.getPlayers().size();
		bossHealth += 500 * (players - 1);
		maxHealth = bossHealth + 0;
	}
	
	public SoundEvent getDeathSound(DamageSource source) {
		   return SoundsT.KILLED10;
	}
	
	public boolean isInvulnerable() {
		return true;
	}
	
	public boolean canDespawn(double distanceToClosestPlayer) {
		return REMOVED;
	}
	
	@Override
	public void dropLoot(DamageSource source, boolean b) {
		isWoFA = false;
		isWoFA2 = isWoFA;
		if (WorldStateHolder.get(world).hardmode == false) {
            this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("The ancient spirits of light and dark have been released.")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
            WorldStateHolder.get(world).hardmode = true;
		}
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
	 
	 boolean spawnThings = false;
	public Vec3d firstPos;
	 
	 public void tick() {
		 isWoFA2 = isWoFA;
		 if (firstPos == null) {
	    		firstPos = new Vec3d(posX + 0, posY + 0, posZ + 0);
	    	}
		 this.preventDespawn();
		 super.tick();
		 //north = -z
		 //east = +x
		 //south = +z
		 //west = -x;
		 if (this.setDirection == false) {
			 this.setDirection = true;
			 if (Math.abs(posX) > Math.abs(posZ)) {
				 if (posX > 0) {
					 this.direction = Direction.WEST;
				 } else {
					 this.direction = Direction.EAST;
				 }
			 } else {
				 if (posZ > 0) {
					 this.direction = Direction.SOUTH;
				 } else {
					 this.direction = Direction.NORTH;
				 }
			 }
		 }
		 if (this.direction != null) {
			 this.lookAt(Type.EYES, getPositionVec().add(direction.getDirectionVec().getX(), direction.getDirectionVec().getY(), -direction.getDirectionVec().getZ()));
		 }
		 
		 this.noClip = true;
		 this.ignoreFrustumCheck = true;
		 
		 if (!world.isRemote()) {
			 
		 	if (!spawnThings) {
		 		spawnThings = true;
		 		EntityWallOfFleshEye eye1 = EntitiesT.WALL_OF_FLESH_EYE.create(world, null, null, null, new BlockPos(getPosition().getX(), 150, getPosition().getZ()), SpawnReason.EVENT, false, false);
		 		eye1.posX = posX;
		 		eye1.posY = 150;
		 		eye1.posZ = posZ;
		 		eye1.upper = true;
		 		eye1.owner = this;
		 		world.addEntity(eye1);
		 		
		 		EntityWallOfFleshEye eye2 = EntitiesT.WALL_OF_FLESH_EYE.create(world, null, null, null, new BlockPos(getPosition().getX(), 40, getPosition().getZ()), SpawnReason.EVENT, false, false);
		 		eye2.posX = posX;
		 		eye2.posY = 40;
		 		eye2.posZ = posZ;
		 		eye2.owner = this;
		 		world.addEntity(eye2);
		 		
		 		EntityWallOfFleshMouth mouth = EntitiesT.WALL_OF_FLESH_MOUTH.create(world, null, null, null, getPosition(), SpawnReason.EVENT, false, false);
		 		mouth.posX = posX;
		 		mouth.posY = 256 / 2;
		 		mouth.posZ = posZ;
		 		mouth.owner = this;
		 		world.addEntity(mouth);
		 		
		 		for (int i = 0; i < rand.nextInt(5) + 15; i++) {
					 TheHungryEntity mouth1 = EntitiesT.THE_HUNGRY.create(world, null, null, null, getPosition().add(0, rand.nextInt(256), 0), SpawnReason.EVENT, false, false);
				 	 mouth1.posX = posX + rand.nextInt(40) - 20;
				 	 mouth1.posY = rand.nextInt(256);
				 	 mouth1.posZ = posZ + rand.nextInt(40) - 20;
				 	 mouth1.owner = this;
				 	 int damage = 30;
				 	 int defense = 10;
				 	 
				 	 if (bossHealth <= maxHealth * 0.75) {
				 		 damage = 45;
				 		 defense = 20;
				 	 }
				 	if (bossHealth <= maxHealth * 0.5) {
				 		 damage = 60;
				 		 defense = 30;
				 	 }
				 	if (bossHealth <= maxHealth * 0.25) {
				 		 damage = 75;
				 		 defense = 40;
				 	 }
				 	
				 	 mouth1.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(240);
				 	mouth1.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(defense);
				 	mouth1.damage = damage;
				 	 world.addEntity(mouth1);
				 }
		 	}
			 
				ALREADY_SPAWNED = true;
				
				if (this.getHealth() <= 0) {
					isWoFA = false;
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
			 this.setMotion(0, -this.getMotion().y, 0);
			 
			 double speed = 0.1f;
			 double mul = 1;
			 double SPEEDMUL = 0.05;
			 if (this.bossHealth <= maxHealth * 0.75) {
				 mul = 2;
			 }
			 if (this.bossHealth <= maxHealth * 0.5) {
				 mul = 3;
			 }
			 if (this.bossHealth <= maxHealth * 0.25) {
				 mul = 4;
			 }
			 speed += speed * SPEEDMUL * mul;
			 
			 this.setMotion(-Math.cos(Math.toRadians(rotationYaw - 90)) * speed, -this.getMotion().y, Math.sin(Math.toRadians(rotationYaw + 90)) * speed);
			 
			 posY = 0;
			 
			 this.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
		 }
		 
		 if (this.ticksExisted % (20 * 60) == 0) {
			 for (int i = 0; i < rand.nextInt(5) + 3; i++) {
				 TheHungryEntity mouth = EntitiesT.THE_HUNGRY.create(world, null, null, null, getPosition().add(0, rand.nextInt(256), 0), SpawnReason.EVENT, false, false);
			 	 mouth.posX = posX + rand.nextInt(40) - 20;
			 	 mouth.posY = rand.nextInt(256);
			 	 mouth.posZ = posZ + rand.nextInt(40) - 20;
			 	 mouth.owner = this;
			 	 int damage = 30;
			 	 int defense = 10;
			 	 
			 	 if (bossHealth <= maxHealth * 0.75) {
			 		 damage = 45;
			 		 defense = 20;
			 	 }
			 	if (bossHealth <= maxHealth * 0.5) {
			 		 damage = 60;
			 		 defense = 30;
			 	 }
			 	if (bossHealth <= maxHealth * 0.25) {
			 		 damage = 75;
			 		 defense = 40;
			 	 }
			 	
			 	 mouth.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(240);
			 	mouth.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(defense);
			 	mouth.damage = damage;
			 	 world.addEntity(mouth);
			 }
			 
		 }
		 
		 if (this.bossHealth <= 0) {
			 this.setHealth(0);
		 }
		 
		 List<? extends PlayerEntity> players = world.getPlayers();
		 for (int i = 0; i < players.size(); i++) {
			 PlayerEntity player = players.get(i);
			 if (player.posX >= posX && player.posZ >= posZ && player.posX <= posX + 80 && player.posZ <= posZ + 80) {
				 onCollideWithPlayer(player);
			 }
		 }
		 
	 }
	 
	 
	 public void onCollideWithPlayer(PlayerEntity player) {
			player.attackEntityFrom(DamageSource.causeMobDamage(this), 50);
		}
	 
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD)
			return super.attackEntityFrom(source, amount);
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING || source == DamageSource.IN_FIRE || source == DamageSource.LAVA || source == DamageSource.ON_FIRE) return false;
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
