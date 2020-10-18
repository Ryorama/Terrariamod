package kmerrill285.trewrite.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class Recipes {
   public static HashMap recipes = new HashMap();

   public static void addRecipe(CraftingRecipe recipe) {
      CraftingRecipe recipe2;
      if (recipe.block == BlocksT.FURNACE) {
         recipe2 = new CraftingRecipe(recipe.output, BlocksT.HELLFORGE, recipe.input);
         addRecipe(recipe2);
      }

      if (recipe.block == BlocksT.WORKBENCH) {
         recipe2 = new CraftingRecipe(recipe.output, BlocksT.OBSIDIAN_WORKBENCH, recipe.input);
         addRecipe(recipe2);
      }

      for(int i = 0; i < recipe.input.length; ++i) {
         if (recipe.input[i] != null) {
            Item item = recipe.input[i].item;
            ItemT[] types;
            Item[] types2;
            int t;
            ItemStackT[] items;
            int j;
            if (item == ItemsT.ANY_IRON) {
               types = new ItemT[]{ItemsT.IRON_BAR};

               for(t = 0; t < types.length; ++t) {
                  items = new ItemStackT[recipe.input.length];

                  for(j = 0; j < items.length; ++j) {
                     items[j] = recipe.input[j];
                     if (items[j].item == ItemsT.ANY_IRON) {
                        items[j] = new ItemStackT(types[t], items[j].size);
                     }
                  }

                  addRecipe(new CraftingRecipe(recipe.output, recipe.block, items));
               }

               return;
            }

            if (item == ItemsT.ANY_SAND) {
               types = new ItemT[]{ItemsT.SAND, ItemsT.EBONSAND};

               for(t = 0; t < types.length; ++t) {
                  items = new ItemStackT[recipe.input.length];

                  for(j = 0; j < items.length; ++j) {
                     items[j] = recipe.input[j];
                     if (items[j].item == ItemsT.ANY_SAND) {
                        items[j] = new ItemStackT(types[t], items[j].size);
                     }
                  }

                  addRecipe(new CraftingRecipe(recipe.output, recipe.block, items));
               }
               
               types2 = new Item[]{Items.SAND};
               
               for(t = 0; t < types2.length; ++t) {
                   items = new ItemStackT[recipe.input.length];

                   for(j = 0; j < items.length; ++j) {
                      items[j] = recipe.input[j];
                      if (items[j].item == ItemsT.ANY_SAND) {
                         items[j] = new ItemStackT(types2[t], items[j].size);
                      }
                   }

                   addRecipe(new CraftingRecipe(recipe.output, recipe.block, items));
                }

               return;
            }

            if (item == ItemsT.ANY_WOOD) {
               types = new ItemT[]{ItemsT.WOOD, ItemsT.SHADEWOOD, ItemsT.EBONWOOD, ItemsT.BOREAL_WOOD, ItemsT.PALM_WOOD, ItemsT.RICH_MAHOGANY, ItemsT.PEARLWOOD};

               for(t = 0; t < types.length; ++t) {
                  items = new ItemStackT[recipe.input.length];

                  for(j = 0; j < items.length; ++j) {
                     items[j] = recipe.input[j];
                     if (items[j].item == ItemsT.ANY_WOOD) {
                        items[j] = new ItemStackT(types[t], items[j].size);
                     }
                  }

                  addRecipe(new CraftingRecipe(recipe.output, recipe.block, items));
               }

               return;
            }
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
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_ORE, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLADIUM_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_ORE, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MYTHRIL_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.MYTHRIL_ORE, 4)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.ORICHALCUM_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.ORICHALCUM_ORE, 4)}));
      addRecipe(new CraftingRecipe(new ItemStackT(Items.APPLE, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.LEAVES, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.HELLSTONE_BAR, 1), BlocksT.HELLFORGE, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE, 3), new ItemStackT(ItemsT.OBSIDIAN, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOOD_HELMET, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOOD_CHESTPLATE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOOD_LEGGINGS, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 25)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CACTUS_HELMET, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.CACTUS_BLOCK, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CACTUS_CHESTPLATE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.CACTUS_BLOCK, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CACTUS_LEGGINGS, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.CACTUS_BLOCK, 25)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_LEGGINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 25)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_LEGGINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 25)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.WOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WORKBENCH, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.TORCH, 3), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.ANY_WOOD, 1), new ItemStackT(ItemsT.GEL, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CHEST, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 8), new ItemStackT(ItemsT.ANY_IRON, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CHAIR, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 4)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DOOR, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 6)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOODEN_SWORD, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.TABLE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.WOOD, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.FURNACE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.ANY_WOOD, 4), new ItemStackT(ItemsT.TORCH, 3), new ItemStackT(ItemsT.STONE_BLOCK, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_ANVIL, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 5)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_ORE, 4)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_ORE, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_ORE, 4)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.IRON_ORE, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_BROADSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 12), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_HAMMER, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 10), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_SHORTSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRON_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 9), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 9), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_BROADSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 12), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_HAMMER, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 10), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_SHORTSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 9), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_BROADSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 12), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_HAMMER, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 10), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_SHORTSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 9), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_BROADSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 12), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_HAMMER, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 10), new ItemStackT(ItemsT.ANY_WOOD, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_SHORTSWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_BULLET, 70), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 1), new ItemStackT(ItemsT.MUSKET_BALL, 70)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CACTUS_PICKAXE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.CACTUS_BLOCK, 15)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CACTUS_SWORD, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.CACTUS_BLOCK, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOLD_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 7)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DEPTH_METER, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 10), new ItemStackT(ItemsT.SILVER_BAR, 8), new ItemStackT(ItemsT.GOLD_BAR, 6)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.LESSER_HEALING_POTION, 2), BlocksT.BOTTLE, new ItemStackT[]{new ItemStackT(ItemsT.MUSHROOM, 1), new ItemStackT(ItemsT.GEL, 2), new ItemStackT(ItemsT.BOTTLE, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.EBONWOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.EBONWOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.EBONWOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.EBONWOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SHADEWOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.SHADEWOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SHADEWOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.SHADEWOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PEARLWOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.PEARLWOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PEARLWOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.PEARLWOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.RICH_MAHOGANY_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.RICH_MAHOGANY, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.RICH_MAHOGANY, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.RICH_MAHOGANY_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.BOREAL_WOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.BOREAL_WOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.BOREAL_WOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.BOREAL_WOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALM_WOOD_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.PALM_WOOD, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALM_WOOD, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.PALM_WOOD_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MUD, 1), Blocks.WATER, new ItemStackT[]{new ItemStackT(ItemsT.DIRT_BLOCK, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOODEN_BOW, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.ANY_WOOD, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GOGGLES, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.LENS, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WOODEN_ARROW, 25), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.ANY_WOOD, 1), new ItemStackT(ItemsT.STONE_BLOCK, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.FLAMING_ARROW, 10), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.WOODEN_ARROW, 10), new ItemStackT(ItemsT.TORCH, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SUSPICIOUS_LOOKING_EYE, 1), BlocksT.DEMON_ALTAR, new ItemStackT[]{new ItemStackT(ItemsT.LENS, 6)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DEMONITE_BAR, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_ORE, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DEMON_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 8)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WAR_AXE_OF_THE_NIGHT, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.LIGHTS_BANE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CHAIN, 10), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.IRON_BAR, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COPPER_WATCH, 1), BlocksT.TABLE, new ItemStackT[]{new ItemStackT(ItemsT.COPPER_BAR, 10), new ItemStackT(ItemsT.CHAIN, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SILVER_WATCH, 1), BlocksT.TABLE, new ItemStackT[]{new ItemStackT(ItemsT.SILVER_BAR, 10), new ItemStackT(ItemsT.CHAIN, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.UNHOLY_ARROW, 5), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.WOODEN_ARROW, 5), new ItemStackT(ItemsT.WORM_TOOTH, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.JESTERS_ARROW, 20), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.WOODEN_ARROW, 20), new ItemStackT(ItemsT.FALLEN_STAR, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GLASS, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.ANY_SAND, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GLASS, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.GLASS_PLATFORM, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.GLASS_PLATFORM, 2), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.GLASS, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IRIDESCENT_BRICK, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.STONE_BLOCK, 1), new ItemStackT(ItemsT.ASH_BLOCK, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.HELLSTONE_BRICKS, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.STONE_BLOCK, 1), new ItemStackT(ItemsT.HELLSTONE, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CORRUPT_TORCH, 3), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.TORCH, 3), new ItemStackT(ItemsT.OBSIDIAN, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.OBSIDIAN_SKULL, 1), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.OBSIDIAN, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.OBSIDIAN_CHEST, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.OBSIDIAN, 6), new ItemStackT(ItemsT.HELLSTONE, 2), new ItemStackT(ItemsT.ANY_IRON, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.OBSIDIAN_PLATFORM, 2), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.OBSIDIAN_BRICK, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.OBSIDIAN_BRICK, 1), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.OBSIDIAN_BRICK, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(Items.BUCKET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.ANY_IRON, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.ROPE_COIL), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.ROPE, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MANA_CRYSTAL), (Block)null, new ItemStackT[]{new ItemStackT(ItemsT.FALLEN_STAR, 3)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.FIERY_GREATSWORD), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE_BAR, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.HELLSTONE_BAR), BlocksT.HELLFORGE, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE, 3), new ItemStackT(ItemsT.OBSIDIAN, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.HELLFIRE_ARROW, 100), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE_BAR, 1), new ItemStackT(ItemsT.WOODEN_ARROW, 100)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE_BAR, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE_BAR, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MOLTEN_LEGGINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HELLSTONE_BAR, 25)}));
      addRecipe(new CraftingRecipe(new ItemStackT(Items.GOLDEN_APPLE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_ORE, 8), new ItemStackT(Items.APPLE, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(Items.ENCHANTED_GOLDEN_APPLE, 1), BlocksT.WORKBENCH, new ItemStackT[]{new ItemStackT(ItemsT.GOLD_BAR, 8), new ItemStackT(Items.APPLE, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.METEORITE_BAR, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.METEORITE, 3)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_LEGGINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 15)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 15)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.COBALT_SWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.COBALT_BAR, 10)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.VILE_POWDER, 5), BlocksT.BOTTLE, new ItemStackT[]{new ItemStackT(ItemsT.VILE_MUSHROOM, 1)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.WORM_FOOD, 1), BlocksT.DEMON_ALTAR, new ItemStackT[]{new ItemStackT(ItemsT.ROTTEN_CHUNK, 15), new ItemStackT(ItemsT.VILE_POWDER, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MECHANICAL_WORM, 1), BlocksT.DEMON_ALTAR, new ItemStackT[]{new ItemStackT(ItemsT.ROTTEN_CHUNK, 6), new ItemStackT(ItemsT.IRON_BAR, 5), new ItemStackT(ItemsT.NIGHT_SOUL, 6)}));

      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.ANGEL_WINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HARPY_FEATHER, 10), new ItemStackT(ItemsT.LIGHT_SOUL, 25), new ItemStackT(ItemsT.FLIGHT_SOUL, 30)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DEMON_WINGS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HARPY_FEATHER, 10), new ItemStackT(ItemsT.NIGHT_SOUL, 25), new ItemStackT(ItemsT.FLIGHT_SOUL, 30)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.DEMONITE_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 12), new ItemStackT(ItemsT.SHADOW_SCALE, 6)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SHADOW_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 20), new ItemStackT(ItemsT.SHADOW_SCALE, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SHADOW_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 25), new ItemStackT(ItemsT.SHADOW_SCALE, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.SHADOW_BOOTS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.DEMONITE_BAR, 15), new ItemStackT(ItemsT.SHADOW_SCALE, 15)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.BOTTLE, 2), BlocksT.FURNACE, new ItemStackT[]{new ItemStackT(ItemsT.GLASS, 1)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.LESSER_HEALING_POTION, 2), BlocksT.BOTTLE, new ItemStackT[]{new ItemStackT(ItemsT.MUSHROOM, 1), new ItemStackT(ItemsT.BOTTLE, 2), new ItemStackT(ItemsT.GEL, 2)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.HEALING_POTION, 1), BlocksT.BOTTLE, new ItemStackT[]{new ItemStackT(ItemsT.LESSER_HEALING_POTION, 2), new ItemStackT(ItemsT.GLOWING_MUSHROOM)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 10), new ItemStackT(ItemsT.TISSUE_SAMPLES, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 20), new ItemStackT(ItemsT.TISSUE_SAMPLES, 20)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_BOOTS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 15), new ItemStackT(ItemsT.TISSUE_SAMPLES, 15)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 12), new ItemStackT(ItemsT.TISSUE_SAMPLES, 6)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_SWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.CRIMTANE_BOW, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.CRIMTANE_BAR, 8)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_HELMET, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 12)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_CHESTPLATE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 24)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_BOOTS, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 18)}));
      
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_PICKAXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 18)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_AXE, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 12)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.PALLIDUM_SWORD, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.PALLADIUM_BAR, 12)}));

      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.EXCALIBUR, 1), BlocksT.MYTHRIL_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HALLOW_BAR, 12)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.EXCALIBUR, 1), BlocksT.ORICHALCUM_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.HALLOW_BAR, 12)}));

      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.MYTHRIL_ANVIL, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.MYTHRIL_BAR, 10)}));
      addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.ORICHALCUM_ANVIL, 1), BlocksT.IRON_ANVIL, new ItemStackT[]{new ItemStackT(ItemsT.ORICHALCUM_BAR, 10)}));

      Iterator var0 = ItemsT.items.keySet().iterator();

      while(var0.hasNext()) {
         String str = (String)var0.next();
         ((ItemT)ItemsT.items.get(str)).setCraftingRecipes();
      }

   }
}
