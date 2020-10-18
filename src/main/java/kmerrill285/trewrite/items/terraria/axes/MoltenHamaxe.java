package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenHamaxe extends Axe {

	public MoltenHamaxe() {
		super();
		this.axe = 150;
		this.hammer = 70;
		this.damage = 20;
		this.knockback = 7;
		this.useTime = 27;
		this.speed = 14;
		this.sellPrice = 3000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("molten_hamaxe");
	}
	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, player, worldIn, handIn);
		if (entity != null) {
			if (random.nextBoolean()) entity.setFire(5);
		}
	}
	
	public void setCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_HAMAXE), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.HELLSTONE_BAR, 15)));
	}

}
