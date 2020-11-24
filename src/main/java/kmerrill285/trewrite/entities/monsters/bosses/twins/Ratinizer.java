package kmerrill285.trewrite.entities.monsters.bosses.twins;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class Ratinizer extends FlyingEntity implements IEntityAdditionalSpawnData, IHostile {

	public TwinsBinding owner;
	public static boolean isAlive = false;
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public static int phase = 1;
	
	public PlayerEntity target;
	
	public int SHOOT_TIME = 15 * 20;
	public int timeUntilShoot = 50;
	public int lasers = 0;
	public int timeUntilNextLaser = 10;
	public boolean transformed = false;
	
	public Ratinizer(EntityType<Ratinizer> type, World world) {
		super(type, world);
	    isAlive = true;
	    phase = 1;
	    if (!this.world.isRemote()) {
	         this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("Retinizer has awoken")).applyTextStyles(new TextFormatting[]{TextFormatting.BLUE, TextFormatting.BOLD}));
	     }
	}

	public Ratinizer(World world) {
	   super(EntitiesT.RATINIZER, world);
	}
		
	 @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20000);
	      this.setHealth(20000);
	      this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1000.0D);
	      return spawnDataIn;
	   }
	 
	 
	 public void dropLoot(DamageSource source, boolean b) {
		   WorldStateHolder.get(world).mechBossesDowned += 1;
		   isAlive = false;
		   if (!this.world.isRemote()) {
		         this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("Retinizer has been defeated!")).applyTextStyles(new TextFormatting[]{TextFormatting.BLUE, TextFormatting.BOLD}));
		   }
	   }
	
	public void tick() {
		super.tick();
		
		if (owner != null) {
			this.posX = owner.posX;
			this.posZ = owner.posZ;
			this.posY = owner.posY + 2;
		}
		
		this.rotationPitch = 0;
		
		PlayerEntity target = null;
        double distance = 1000.0D;

        for(int i = 0; i < this.world.getPlayers().size(); ++i) {
           double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
           if (dist < distance) {
              distance = dist;
              target = (PlayerEntity)this.world.getPlayers().get(i);
           }
        }
        
        if (this.getHealth() <= 10000 && transformed == false) {
        	transformed = true;
        	world.playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundsT.ROAR, SoundCategory.PLAYERS, 100, 1);
            phase = 2;
            timeUntilShoot = 5;
            SHOOT_TIME = 3;
            
         }
        
        if (!world.isRemote) {
        	if (this.getHealth() <= 0) {
    			if (TwinsBinding.eyes == 2) {
    				TwinsBinding.eyes = 1;
    			}
    			isAlive = false;
    			remove();
    			return;
    		}
        }
        
       if (owner == null) {
	       double motionX = this.motionX;
	 	   double motionY = this.motionY;
	 	   double motionZ = this.motionZ;
	       
	        if (target != null) {
	        	if (!world.isRemote) {
	        		
	        		if (target.posX < this.posX) {
	        			this.motionX = -0.2f;
	        		}
	        		else {
	        			this.motionX = 0.2f;
	        		}
	        		
	        		if (target.posZ < this.posZ) {
	        			this.motionZ = -0.2f;
	        		}
	        		else {
	        			this.motionZ = 0.2f;
	        		}
	        		
	        		if (target.posY < this.posY) {
	        			this.motionY = -0.2f;
	        		}
	        		else {
	        			this.motionY = 0.2f;
	        		}
	        	}
	        	this.setMotion(motionX, motionY, motionZ);
	        }
       }
        
        if (target != null) {
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
	      }
	
	 public void shootAtTarget(PlayerEntity target) {
			EntityEyeLaser spit = EntitiesT.EYE_LASER.create(world);
			spit.setPosition(posX, posY, posZ);
			
			Vec3d point = new Vec3d(posX - target.posX, (posY + 1+25) - target.posY, posZ - target.posZ).normalize();
			point = point.mul(2, 2, 2);
			
			spit.setMotion(-point.x, -point.y, -point.z);
			
			world.addEntity(spit);
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		// TODO Auto-generated method stub
		
	}

}
