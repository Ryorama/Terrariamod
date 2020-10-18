package kmerrill285.trewrite.items.terraria.boss_summon;

import java.util.List;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuspiciousLookingEye extends ItemT {
	public SuspiciousLookingEye(Properties properties, String name) {
		super(properties, name);
		this.tooltip = "Summons the Eye of Cthulhu";
		
		this.animation = ItemT.STAFF_ANIMATION;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		  player.getCooldownTracker().setCooldown(this, 20);

		if (worldIn.getDayTime() % 24000L > 15000 && worldIn.getDayTime() % 24000L < 22000) {
			
			List<EntityEyeOfCthulhu> eocs = worldIn.getEntitiesWithinAABB(EntityEyeOfCthulhu.class, new AxisAlignedBB(new BlockPos(player.posX - 150, player.posY - 150, player.posZ - 150), new BlockPos(player.posX + 150, player.posY + 150, player.posZ + 150)));
			if (eocs.size() > 0) {
				return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
			}
			
			 worldIn.playSound((PlayerEntity)null, player.posX, player.posY, player.posZ, SoundsT.ROAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			    InventoryTerraria inv = null;
			    if (!worldIn.isRemote()) {
					inv = WorldEvents.getOrLoadInventory(player);
				} else {
					inv = ContainerTerrariaInventory.inventory;
				}
			    if (inv.hotbar[inv.hotbarSelected].stack != null) {
			    	if (inv.hotbar[inv.hotbarSelected].stack.item == this) {
			    		inv.hotbar[inv.hotbarSelected].decrementStack(1);
			    		
			    		float posX = 0, posY = worldIn.rand.nextInt(20) - 10, posZ = 0;
			    		float rad = 20;
			    		
			    		float rotation = worldIn.rand.nextInt(360);
			    		posX = (float) (Math.cos(Math.toDegrees(rotation)) * rad);
			    		posZ = (float) (Math.sin(Math.toDegrees(rotation)) * rad);
			    		
			    		EntityEyeOfCthulhu eye = EntitiesT.EYE_OF_CTHULHU.create(worldIn, null, null, null, player.getPosition(), SpawnReason.EVENT, false, false);
						eye.setPosition(player.getPosition().getX() + posX, player.getPosition().getY() + posY, player.getPosition().getZ() + posZ);
						worldIn.addEntity(eye);
			    		
			    	}
			    }
		}
	   
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}
}
