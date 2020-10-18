package kmerrill285.trewrite.items.terraria.boss_summon;

import java.util.List;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.core.sounds.TAudio;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowHead;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class MechanicalWorm extends ItemT {
   public MechanicalWorm(Properties properties, String name) {
      super(properties, name);
      this.tooltip = "Summons the Destroyer";
   }

   public ActionResult onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
      ItemStack itemstack = player.getHeldItem(handIn);
      player.getCooldownTracker().setCooldown(this, 20);
      
		if (worldIn.getDayTime() % 24000L > 15000 && worldIn.getDayTime() % 24000L < 22000) {
			
	      List eocs = worldIn.getEntitiesWithinAABB(EntityEowHead.class, new AxisAlignedBB(new BlockPos(player.posX - 150.0D, player.posY - 150.0D, player.posZ - 150.0D), new BlockPos(player.posX + 150.0D, player.posY + 150.0D, player.posZ + 150.0D)));
	      if (eocs.size() > 0) {
	         return new ActionResult(ActionResultType.SUCCESS, itemstack);
	      } else {
	         worldIn.playSound((PlayerEntity)null, player.posX, player.posY, player.posZ, SoundsT.ROAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
	         InventoryTerraria inv = null;
	         if (!worldIn.isRemote()) {
	            inv = WorldEvents.getOrLoadInventory(player);
	         } else {
	            inv = ContainerTerrariaInventory.inventory;
	         }
	
	         if (inv.hotbar[inv.hotbarSelected].stack != null && inv.hotbar[inv.hotbarSelected].stack.item == this) {
	            inv.hotbar[inv.hotbarSelected].decrementStack(1);
	            float posX = 0.0F;
	            float posY = (float)(worldIn.rand.nextInt(20) - 10);
	            float posZ = 0.0F;
	            float rad = 20.0F;
	            float rotation = (float)worldIn.rand.nextInt(360);
	            posX = (float)(Math.cos(Math.toDegrees((double)rotation)) * (double)rad);
	            posZ = (float)(Math.sin(Math.toDegrees((double)rotation)) * (double)rad);
	            EntityDestroyerHead eye = (EntityDestroyerHead)EntitiesT.DESTROYER_HEAD.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, player.getPosition(), SpawnReason.EVENT, false, false);
	            eye.setPosition((double)((float)player.getPosition().getX() + posX), (double)((float)player.getPosition().getY() + posY), (double)((float)player.getPosition().getZ() + posZ));
	            worldIn.addEntity(eye);
	         }
	      }
	   }
		return new ActionResult(ActionResultType.SUCCESS, itemstack);
   }
}
