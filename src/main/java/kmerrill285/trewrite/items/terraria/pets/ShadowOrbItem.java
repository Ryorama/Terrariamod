package kmerrill285.trewrite.items.terraria.pets;


import kmerrill285.trewrite.entities.EntityShadowOrb;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShadowOrbItem extends ItemT {
	public ShadowOrbItem() {
		super(new Properties().group(ItemGroup.MISC), "shadow_orb_item");
		this.setMaxStack(1);
		this.setTooltip("A magical orb that emits light");
		this.setBuySell(2000);
		this.useTime = 20;
	}
	
	public int getUseDuration(ItemStack t) {
		return 4;
	}
	
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		  playerIn.getCooldownTracker().setCooldown(this, (int) ((this.useTime) * (30.0 / 60.0)));
		  if (WorldEvents.light_pets.get(playerIn.getScoreboardName()) != null) {
			  WorldEvents.light_pets.get(playerIn.getScoreboardName()).remove();
		  }
	      
		  WorldEvents.light_pets.put(playerIn.getScoreboardName(), EntityShadowOrb.spawnOrb(worldIn, playerIn.getPosition(), playerIn));

	      return new ActionResult<>(ActionResultType.SUCCESS, new ItemStack(this));
	   }
}
