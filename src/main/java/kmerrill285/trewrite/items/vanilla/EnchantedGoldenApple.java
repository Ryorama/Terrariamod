package kmerrill285.trewrite.items.vanilla;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EnchantedGoldenApple extends Item {
   public EnchantedGoldenApple(Properties builder) {
      super(builder);
   }

   public ActionResult onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
      ItemStack itemstack = player.getHeldItem(handIn);
      InventoryTerraria inv = null;
      if (!worldIn.isRemote()) {
         inv = WorldEvents.getOrLoadInventory(player);
      } else {
         inv = ContainerTerrariaInventory.inventory;
      }

      if (inv.hotbar[inv.hotbarSelected].stack != null && inv.hotbar[inv.hotbarSelected].stack.item == this) {
         inv.hotbar[inv.hotbarSelected].decrementStack(1);
         player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 2));
         player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000));
         player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 6000));
      }

      return new ActionResult(ActionResultType.SUCCESS, itemstack);
   }
}
