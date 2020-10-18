package kmerrill285.trewrite.entities.monsters.bosses;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.pillars.NebulaPillar;
import kmerrill285.trewrite.entities.pillars.SolarPillar;
import kmerrill285.trewrite.entities.pillars.StardustPillar;
import kmerrill285.trewrite.entities.pillars.VortexPillar;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class LunaticCultist extends MobEntity implements IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;

	public static boolean isAlive = false;
	
	public LunaticCultist(EntityType<LunaticCultist> type, World worldIn) {
		super(type, worldIn);
		isAlive = true;
	}

	public LunaticCultist(World worldIn) {
      super(EntitiesT.LUNATIC_CULSTIST, worldIn);
	}
	
	public void dropLoot(DamageSource source, boolean b) {
		   isAlive = false;
		   
		   StardustPillar eye = (StardustPillar)EntitiesT.STARDUST_PILLAR.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
           eye.setPosition(this.posX + 50, this.posY, this.posZ);
           this.world.addEntity(eye);
           
           VortexPillar eye1 = (VortexPillar)EntitiesT.VORTEX_PILLAR.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
           eye1.setPosition(this.posX - 50, this.posY, this.posZ);
           this.world.addEntity(eye1);
           
           NebulaPillar eye2 = (NebulaPillar)EntitiesT.NEBULA_PILLAR.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
           eye2.setPosition(this.posX, this.posY, this.posZ + 50);
           this.world.addEntity(eye2);
           
           SolarPillar eye3 = (SolarPillar)EntitiesT.SOLAR_PILLAR.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
           eye3.setPosition(this.posX, this.posY, this.posZ - 50);
           this.world.addEntity(eye3);
           
           this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("Celestial creatures are invading!")).applyTextStyles(new TextFormatting[]{TextFormatting.GREEN, TextFormatting.BOLD}));
	}
	
	public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 50F);
	}
	
	 @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32000);
	      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(42);
	      this.setHealth(32000);
	      return spawnDataIn;
	   }
	   
	   public void tick() {
		   super.tick();
	   }
	
	
}
