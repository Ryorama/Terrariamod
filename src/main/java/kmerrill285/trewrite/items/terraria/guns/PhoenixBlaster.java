package kmerrill285.trewrite.items.terraria.guns;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.items.Gun;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.item.ItemGroup;

public class PhoenixBlaster extends Gun {
	public PhoenixBlaster() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "phoenix_blaster", 0);
		this.knockback = 2;
		this.useTime = 11;
		this.velocity = 13;
		this.damage = 24;
		this.setBuySell(10000);
	}
	
	public void setCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PHOENIX_BLASTER), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.HELLSTONE_BAR, 10), new ItemStackT(ItemsT.HANDGUN, 1)));
	}
}
