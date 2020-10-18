package kmerrill285.trewrite.items.terraria.accessories;

import java.util.HashMap;
import java.util.Random;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class ObsidianSkull extends Accessory {

	public ObsidianSkull() {
		super(new Properties().group(ItemGroup.MISC), "obsidian_skull");
		this.tooltip = "Grants immunity to fire blocks";
		this.setBuySell(5400);
	}
}
