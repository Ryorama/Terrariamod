package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import kmerrill285.trewrite.items.Bow;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.terraria.arrows.WoodenArrow;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;

public class TendonBow extends Bow {
	public TendonBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "tendon_bow", 0);
		this.knockback = 1;
		this.useTime = 30;
		this.velocity = 6.7f;
		this.damage = 19;
		this.setBuySell(5400);
		
	}
	
	public void setCraftingRecipes() {
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.CRIMTANE_BAR, 8)));
	}
}
