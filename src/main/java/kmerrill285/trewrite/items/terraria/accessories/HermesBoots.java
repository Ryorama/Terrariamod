package kmerrill285.trewrite.items.terraria.accessories;

import java.util.HashMap;
import java.util.Random;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class HermesBoots extends Accessory {

	public HermesBoots() {
		super(new Properties().group(ItemGroup.MISC), "hermes_boots");
		this.tooltip = "The wearer can run super fast";
		this.setBuySell(10000);
	}
	public final double maxSpeed = 0.8f;
	public HashMap<String, Double> speeds = new HashMap<String, Double>();
	
	public double baseSpeed = 0.0f;
	
	public void accessoryTick(PlayerEntity player) {
		double maxSpeed = 0.7f;
		if (baseSpeed == 0.0f) {
			baseSpeed = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
		}
		if (!speeds.containsKey(player.getScoreboardName())) {
			speeds.put(player.getScoreboardName(), 0.0D);
		}
		if (player.distanceWalkedModified > player.prevDistanceWalkedModified) {
			if (getSpeed(player) < maxSpeed) {
				setSpeed(player, getSpeed(player) + 0.025f);
			}
		} else {
			if (getSpeed(player) > 0.0f) {
				setSpeed(player, getSpeed(player) - 0.025f);
			}
		}
		World world = player.world;
		if (!world.isRemote) {
			player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((float) (baseSpeed + baseSpeed * getSpeed(player)));
		} else {
			double mul = (maxSpeed * 10 + 1) / (getSpeed(player) * 10 + 1);
			if (player.ticksExisted % ((int)(5 * mul)) == 0 && getSpeed(player) > 0 && player.onGround)
				if (player.distanceWalkedModified > player.prevDistanceWalkedModified)
					player.getEntityWorld().addParticle(ParticleTypes.CLOUD, player.posX, player.posY, player.posZ, 0, 0, 0);
		}
		
	}

	public double getSpeed(PlayerEntity player) {
		return speeds.get(player.getScoreboardName());
	}
	
	public void setSpeed(PlayerEntity player, double speed) {
		speeds.put(player.getScoreboardName(), speed);
	}
}
