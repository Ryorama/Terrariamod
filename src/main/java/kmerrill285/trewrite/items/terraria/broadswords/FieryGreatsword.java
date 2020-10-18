package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.EntityTekhairaProjectile;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FieryGreatsword extends Broadsword {	
	public FieryGreatsword() {
		super();
		this.damage = 36;
		this.knockback = 6.5f;
		this.useTime = 34;
		this.sellPrice = 5400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("fiery_greatsword");
		this.setTooltip("It's made out of fire!");
		this.setMaterial();
		this.scale = 2.0f;
	}
	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, player, worldIn, handIn);
		if (entity != null) {
			if (random.nextBoolean()) entity.setFire(5);
		}
	}
	
}
