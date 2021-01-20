package com.ryorama.terrariamod.core.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ryorama.terrariamod.blocks.BlockT;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class Recipes {

	   public static HashMap recipes = new HashMap();

	   public static void addRecipe(CraftingRecipe recipe) {
	      CraftingRecipe recipe2;

	      for(int i = 0; i < recipe.input.length; ++i) {
	         if (recipe.input[i] != null) {
	            Item item = recipe.input[i].item;
	            ItemT[] types;
	            Item[] types2;
	            int t;
	            ItemStackT[] items;
	            int j;
	         }
	      }

	      if (recipes.get(recipe.block) == null) {
	         recipes.put(recipe.block, new ArrayList());
	      }

	      ((List)recipes.get(recipe.block)).add(recipe);
	   }

	   public static List getRecipesForBlock(BlockT block) {
	      return (List)recipes.get(block);
	   }

	   public static void addAllRecipes() {
		      recipes.clear();
		      
		      
		      
		      
		      
		      

		      Iterator var0 = ItemsT.items.keySet().iterator();

		      while(var0.hasNext()) {
		         String str = (String)var0.next();
		         ((ItemT)ItemsT.items.get(str)).setCraftingRecipes();
		      }
	   }
	   
}
