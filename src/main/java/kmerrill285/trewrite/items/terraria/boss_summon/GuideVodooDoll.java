package kmerrill285.trewrite.items.terraria.boss_summon;

import java.util.List;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFlesh;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
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

public class GuideVodooDoll extends ItemT {
   public GuideVodooDoll(Properties properties, String name) {
      super(properties, name);
      this.tooltip = "Summons the Wall of Flesh";
   }

   public ActionResult onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
      ItemStack itemstack = player.getHeldItem(handIn);
      player.getCooldownTracker().setCooldown(this, 20);
      List eocs = worldIn.getEntitiesWithinAABB(EntityWallOfFlesh.class, new AxisAlignedBB(new BlockPos(player.posX - 150.0D, player.posY - 150.0D, player.posZ - 150.0D), new BlockPos(player.posX + 150.0D, player.posY + 150.0D, player.posZ + 150.0D)));
      if (eocs.size() > 0) {
         return new ActionResult(ActionResultType.SUCCESS, itemstack);
      } else {
         worldIn.playSound((PlayerEntity)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 1.0F, 1.0F);
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
            float var10000 = (float)(Math.cos(Math.toDegrees((double)rotation)) * (double)rad);
            var10000 = (float)(Math.sin(Math.toDegrees((double)rotation)) * (double)rad);
            EntityWallOfFlesh eye = (EntityWallOfFlesh)EntitiesT.WALL_OF_FLESH.create(worldIn, null, null, null, player.getPosition(), SpawnReason.EVENT, false, false);
			eye.setPosition(player.getPosition().getX() + 90, player.getPosition().getY() + posY, player.getPosition().getZ());
            worldIn.addEntity(eye);
         }

         return new ActionResult(ActionResultType.SUCCESS, itemstack);
      }
   }
}
