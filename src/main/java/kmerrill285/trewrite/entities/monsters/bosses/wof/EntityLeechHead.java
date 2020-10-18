package kmerrill285.trewrite.entities.monsters.bosses.wof;

import java.util.Random;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityLeechHead extends MobEntity implements IEntityAdditionalSpawnData, IHostile {

	public float velX, velY, velZ;
	public float oldVelX, oldVelY, oldVelZ;
	
	public int life = 60;
	
	public int[] ai = new int[5];
	public PlayerEntity target = null;
	public float rx, ry, rz;
	
	public int money;
	
	public double motionX, motionY, motionZ;
	
	public int segments;
	
	public EntityWallOfFlesh owner;
	
	public EntityLeechHead(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntityLeechHead(World worldIn) {
		super(EntitiesT.LEECH_HEAD, worldIn);
	}
	
	
	@Override
	public void dropLoot(DamageSource source, boolean b) {
		
//		EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.DEMONITE_ORE, this.rand.nextInt(87 - 30) + 30, null));
		if (Util.isChristmas()) {
			if (rand.nextDouble() <= 0.0769) {
				EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, null));
			}
		}
		
		if (source.getImmediateSource() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)source.getImmediateSource();
			if (player.getHealth() <= player.getMaxHealth()) {
				if (rand.nextInt(12) == 0) {
					EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
				}
			}
		}
		
		EntityCoin.spawnCoin(world, getPosition(), EntityCoin.COPPER, money);
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
		player.attackEntityFrom(DamageSource.causeMobDamage(this), 26);
	}
	
	int dirX = 0;
	int dirY = 0;
	int dirZ = 0;
	
	public void tick() {
		super.tick();		
		this.noClip = true;
		this.setNoGravity(true);
		
		
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
		
		if (world.getBlockState(getPosition()).getMaterial().blocksMovement() == false) {
			this.isAirBorne = true;
		} else {
			this.isAirBorne = false;
		}
		
		if (world.isRemote) { 
			return;
		}
		
		
		if (segments == 0) {
			segments = new Random().nextInt(3) + 6;
			ai[0] = 0;
		}
		
		 
			
		if (!world.isRemote) {
			
			if (ai[0] == 0) {
				EntityLeechBody[] bodies = new EntityLeechBody[segments];
				
				{
					EntityLeechBody wormBody = EntityLeechBody.spawnWormBody(world, getPosition(), getMaxHealth(), this);
					wormBody.setHealth(getMaxHealth());
					bodies[0] = wormBody;
				}
				for (int i = 1; i < segments; i++) {
					EntityLeechBody wormBody = EntityLeechBody.spawnWormBody(world, getPosition(), getMaxHealth(), bodies[i - 1]);
					wormBody.setHealth(getMaxHealth());
					bodies[i] = wormBody;
					bodies[i].owner = bodies[i - 1];
				}
				EntityLeechTail.spawnWormBody(world, getPosition(), getMaxHealth(), bodies[segments - 1]).setHealth(getMaxHealth());
				ai[0] = 1;
			}
			
			boolean collision = false;
			
			if (world.getBlockState(getPosition()).getMaterial().blocksMovement() == true) {
				collision = true;
			}
			if (world.getBlockState(getPosition()).getBlock() == Blocks.LAVA || posY <= 0) {
				collision = true;
			}
			
			
			double distance = 1000;
			
			for (int i = 0; i < world.getPlayers().size(); i++) {
				double dist = world.getPlayers().get(i).getPositionVector().distanceTo(this.getPositionVector());
				if (dist < distance) {
					distance = dist;
					target = world.getPlayers().get(i);
				}
			}
			
			if (target != null) {
				float mul = 2.0f;
				float speed = 3f * mul;
				
				float acceleration = 0.2f * mul;
				
				float absVelX = (float)Math.abs(velX);
				float absVelY = (float)Math.abs(velY);
				float absVelZ = (float)Math.abs(velZ);
				
				if (collision == false) {

					if (this.getPositionVector().distanceTo(target.getPositionVector()) <= 1) {
						
					}
					
					if (velY > -speed) {
						velY -= 0.08f * mul;
					}
					

					if (absVelZ > 0 || dirZ == 0) {
						if (posZ < target.posZ) { 
							dirZ = 1;
						} else {
							dirZ = -1;
						}
					}
					
					if (absVelX > 0 || dirX == 0) {
						if (posX < target.posX) { 
							dirX = 1;
						} else {
							dirX = -1;
						}
					}
					
					
					if (dirZ == 1) { 
						if (velZ < speed) {
							velZ += acceleration * 0.1f;
						}
					} else {
						if (velZ > -speed) {
							velZ -= acceleration * 0.1f;
						}
					}
					
					if (dirX == 1) { 
						if (velX < speed) {
							velX += acceleration * 0.1f;
						}
					} else {
						if (velX > -speed) {
							velX -= acceleration * 0.1f;
						}
					}
					
					
					
				} else {
					if (System.currentTimeMillis() % 250 <= 25) {
						world.playSound(posX, posY, posZ, SoundEvents.BLOCK_WOOL_STEP, SoundCategory.HOSTILE, 1.0f, 1.0f, true);
					}
					
					if (absVelZ > 0 || dirZ == 0) {
						if (posZ < target.posZ) { 
							dirZ = 1;
						} else {
							dirZ = -1;
						}
					}
					
					if (absVelX > 0 || dirX == 0) {
						if (posX < target.posX) { 
							dirX = 1;
						} else {
							dirX = -1;
						}
					}
					
					if (absVelY > 0 || dirY == 0) {
						if (posY < target.posY) { 
							dirY = 1;
						} else {
							dirY = -1;
						}
					}
					
					if (dirZ == 1) { 
						if (velZ < speed) {
							velZ += acceleration;
						}
					} else {
						if (velZ > -speed) {
							velZ -= acceleration;
						}
					}
					
					if (dirX == 1) { 
						if (velX < speed) {
							velX += acceleration;
						}
					} else {
						if (velX > -speed) {
							velX -= acceleration;
						}
					}
					
					
					if (dirY == 1) { 
						if (velY < speed) {
							velY += acceleration;
						}
					} else {
						if (velY > -speed) {
							velY -= acceleration;
						}
					}
					
					if (absVelZ > speed * 0.5f) {
						velZ -= acceleration * dirZ;
						velY += acceleration * dirY;
					}
					
					if (absVelX > speed * 0.5f) {
						velX -= acceleration * dirX;
						velY += acceleration * dirY;
					}
					if (absVelY > speed * 0.5f) {
						velY -= acceleration * dirY;
						velX += acceleration * dirX;
						velZ += acceleration * dirZ;
					}
					
					
					
				}
				
				
				
				
//				if (collision == false) {
//					if (velY > -speed) {
//						velY -= acceleration;
//					}
//				} else {
//					
//				}
			}
			
//			this.rz = (float)Math.toDegrees(Math.atan2(velY,  velX));
//			this.rx = (float)Math.toDegrees(Math.atan2(velY,  velZ));
//			this.ry += 10;
			
			this.rotationYaw = (float)Math.toDegrees(Math.atan2(velZ, velX)) - 90;
			
			this.oldVelX = velX + 0;
			this.oldVelY = velY + 0;
			this.oldVelZ = velZ + 0;
			
			this.motionX = velX * 0.1f;
			this.motionY = velY * 0.1f;
			this.motionZ = velZ * 0.1f;
			this.posX += motionX;
			this.posY += motionY;
			this.posZ += motionZ;
			this.move(MoverType.PISTON, new Vec3d(motionX, motionY, motionZ));
			this.setMotion(0, 0, 0);
			
		}
		
	}
	public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING) return false;
    	return super.attackEntityFrom(source, amount);
    }
	public static EntityLeechHead spawnWormHead(World worldIn, BlockPos pos, float life) {
		EntityLeechHead head = EntitiesT.LEECH_HEAD.create(worldIn, null, null, null, pos, SpawnReason.EVENT, false, false);
		head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(life);
		head.setHealth(life);
		head.money = 0;
		head.segments = new Random().nextInt(3) + 6;
		worldIn.addEntity(head);
		return head;
	}
	
	
	@Override
	protected void registerData() {
		super.registerData();
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		buffer.writeInt(segments);
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		segments = additionalData.readInt();
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
