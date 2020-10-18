package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenPickaxe extends Pickaxe {

	public MoltenPickaxe() {
		super();
		this.pick = 100;
		this.damage = 12;
		this.knockback = 2;
		this.useTime = 23;
		this.speed = 18;
		this.sellPrice = 5400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("molten_pickaxe");
	}
	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, player, worldIn, handIn);
		if (entity != null) {
			if (random.nextBoolean()) entity.setFire(5);
		}
	}
	
	public void setCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_PICKAXE), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.HELLSTONE_BAR, 20)));
	}

}
