package com.ryorama.terrariamod.crafting;

import com.ryorama.terrariamod.blocks.api.BlockT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipes {

    public static HashMap recipes = new HashMap();

    public static void addRecipe(CraftingRecipe recipe) {
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


    }
}
