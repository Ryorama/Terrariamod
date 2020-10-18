package kmerrill285.trewrite.entities.monsters.bosses.twins;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class TwinsBinding extends MobEntity implements IEntityAdditionalSpawnData {

	public boolean summonedTwins = false;
	public static boolean isAlive = false;
	
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public static int eyes = 2;
	
	public PlayerEntity target;
	
	public TwinsBinding(EntityType<TwinsBinding> type, World worldIn) {
		super(type, worldIn);
		isAlive = true;
		eyes = 2;
	}
	
	public TwinsBinding(World world) {
		super(EntitiesT.TWINS_BINDING, world);
	}
	
	public boolean isInvulnerable() {
		return false;
	}

	public void tick() {
		super.tick();
		
		if (summonedTwins == false) {

			Ratinizer eye = (Ratinizer)EntitiesT.RATINIZER.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
            eye.setPosition(this.posX, this.posY + 2, this.posZ);
            this.world.addEntity(eye);
            eye.noClip = true;
            eye.owner = this;
            
            Spazmatism eye2 = (Spazmatism)EntitiesT.SPAZMATISM.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
            eye2.setPosition(this.posX, this.posY + 10, this.posZ);
            this.world.addEntity(eye2);
            eye2.noClip = true;
            eye2.owner = this;
            
            summonedTwins = true;
		}
		
		PlayerEntity target = null;
        double distance = 1000.0D;

        for(int i = 0; i < this.world.getPlayers().size(); ++i) {
           double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
           if (dist < distance) {
              distance = dist;
              target = (PlayerEntity)this.world.getPlayers().get(i);
           }
        }
		
		double motionX = this.motionX;
	 	double motionY = this.motionY;
	 	double motionZ = this.motionZ;
		
		if (target != null) {
        	if (!world.isRemote) {
        		
        		if (eyes < 2) {
        			remove();
        			return;
        		}
        		
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
	
	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		// TODO Auto-generated method stub
		
	}

}
