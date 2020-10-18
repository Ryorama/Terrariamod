package kmerrill285.trewrite.entities;

import java.util.ArrayList;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityRope extends MobEntity {
	
	boolean body = false;
	
	public ArrayList<Vec3d> lastPos = new ArrayList<Vec3d>();
	
	public EntityRope(EntityType<EntityRope> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntityRope(World worldIn) {
		super(EntitiesT.ROPE, worldIn);
	}
		
	public EntityRope(World worldIn, double x, double y, double z) {
		super(EntitiesT.ROPE, worldIn);
		this.setPosition(x, y, z);
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
		//look towards movement
//		float f = MathHelper.sqrt(func_213296_b(getMotion()));
//		this.rotationYaw = (float)(MathHelper.atan2(getMotion().x, getMotion().z) * (double)(180F / (float)Math.PI));
//	    this.rotationPitch = (float)(MathHelper.atan2(getMotion().y, (double)f) * (double)(180F / (float)Math.PI));
//	    this.prevRotationYaw = this.rotationYaw;
//	    this.prevRotationPitch = this.rotationPitch;
		
		super.tick();
		this.setNoGravity(false);
		this.noClip = true;
		age++;
		if (world.isRemote)
		if (age%1==0) {
			lastPos.add(new Vec3d(posX, posY, posZ));
			if (lastPos.size() > 10) {
				lastPos.remove(0);
			}
		}
		//1 rope coil = 10 ropes
		if (world.getBlockState(getPosition()).getMaterial().blocksMovement() || world.getBlockState(getPosition()).getBlock() == BlocksT.ROPE) {
			BlockPos p2 = new BlockPos(posX - getMotion().x, posY - getMotion().y, posZ - getMotion().z);
			if (world.getBlockState(getPosition()).getBlock() == BlocksT.ROPE) {
				p2 = getPosition().down(1);
			}
			if (world.getBlockState(p2).getMaterial().isReplaceable()) {
				int ropesBack = 10;
				for (int i = 0; i < 10; i++) {
					BlockPos p3 = new BlockPos(p2.getX(), p2.getY() - i, p2.getZ());
					if (world.getBlockState(p3).getMaterial().isReplaceable()) {
						world.setBlockState(p3, BlocksT.ROPE.getDefaultState());
						ropesBack--;
					} else {
						break;
					}
				}
				if (ropesBack > 0) {
					EntityItemT.spawnItem(world, getPosition(), new ItemStackT(ItemsT.ROPE, ropesBack));
				}
			} else {
				this.remove();
				EntityItemT.spawnItem(world, getPosition(), new ItemStackT(ItemsT.ROPE_COIL, 1));
			}
		}
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
	}

	public float lerp(float a, float b, float f) 
	{
	    return (a * (1.0f - f)) + (b * f);
	}
	
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
	      Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale((double)velocity);
	      this.setMotion(vec3d);
	      float f = MathHelper.sqrt(func_213296_b(vec3d));
	      this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
	      this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
	      this.prevRotationYaw = this.rotationYaw;
	      this.prevRotationPitch = this.rotationPitch;
	   }
	
	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
	      float f = -MathHelper.sin(rotationYawIn * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitchIn * ((float)Math.PI / 180F));
	      float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * ((float)Math.PI / 180F));
	      float f2 = MathHelper.cos(rotationYawIn * ((float)Math.PI / 180F)) * MathHelper.cos(rotationPitchIn * ((float)Math.PI / 180F));
	      this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
	      Vec3d vec3d = entityThrower.getMotion();
	      this.setMotion(this.getMotion().add(vec3d.x, entityThrower.onGround ? 0.0D : vec3d.y, vec3d.z));
	   }
	

	public static EntityRope spawnRope(World worldIn, BlockPos pos) {
		EntityRope rope = EntitiesT.ROPE.create(worldIn, null, null, null, pos, SpawnReason.EVENT, false, false);
		worldIn.addEntity(rope);
		return rope;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerData() {
		super.registerData();
	}

}
