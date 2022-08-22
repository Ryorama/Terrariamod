package com.ryorama.terrariamod.gui.crafting;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.api.BlockT;
import com.ryorama.terrariamod.crafting.CraftingRecipe;
import com.ryorama.terrariamod.crafting.Recipes;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItem;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class CraftingGuiDescription extends SyncedGuiDescription {

    public PlayerInventory inv;
    public PlayerEntity player;
    public ArrayList<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();
    public ArrayList<CraftingRecipe> availableRecipes = new ArrayList<CraftingRecipe>();

    public CraftingGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(TerrariaMod.CRAFTING_TYPE, syncId, playerInventory);

        this.player = playerInventory.player;
        this.inv = playerInventory;
        WGridPanel panel = new WGridPanel();
        panel.setSize(300, 200);
        panel.setInsets(Insets.ROOT_PANEL);

        resetRecipes();

        int x = 4;
        int y = 1;

        for (int r = 0; r <= availableRecipes.size() - 1; r++) {
            if (availableRecipes.size() > 0) {
                WButton item = new WButton(Text.of(""));
                item.setIcon(new ItemIcon(availableRecipes.get(r).output));
                int recipe = r;
                item.setOnClick(() -> {
                    for (int i = 0; i < availableRecipes.get(recipe).input.length; i++) {
                        for (int i2 = 0; i2 < playerInventory.main.size(); i2++) {
                            if (playerInventory.main.get(i2) == availableRecipes.get(recipe).input[i]) {
                                int removalSize = availableRecipes.get(recipe).input[i].getCount();
                                playerInventory.removeStack(i2, removalSize);
                            }
                        }
                        playerInventory.insertStack(availableRecipes.get(recipe).output);
                    }
                });
                panel.add(item, x, y, 20, 20);

               if (x <= 300) {
                   x = x * 2 + 2;
               } else {
                   x = 4;
                   y = y * 2 + 2;
               }
            }
        }
    }

    public void resetRecipes() {
        ArrayList<BlockT> blocks = new ArrayList<BlockT>();

        if (player != null) {
            if (player.world != null) {
                BlockPos playerpos = player.getBlockPos();
                for (int x = -2; x < 3; x++) {
                    for (int y = -2; y < 3; y++) {
                        for (int z = -2; z < 3; z++) {
                            BlockPos pos2 = new BlockPos(playerpos.getX() + x, playerpos.getY() + y, playerpos.getZ() + z);
                            if (player.world.isChunkLoaded(pos2)) {
                                if (player.world.getBlockState(pos2).getBlock() instanceof BlockT) {
                                    BlockT block = (BlockT)player.world.getBlockState(pos2).getBlock();
                                    if (Recipes.recipes.containsKey(block)) {
                                        blocks.add(block);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        availableRecipes.clear();
        recipes.clear();
        recipes.addAll(Recipes.getRecipesForBlock(null)); //load all crafting recipes that don't require a crafting station
        for (BlockT block : blocks) {
            recipes.addAll(Recipes.getRecipesForBlock(block));
        }

        for (CraftingRecipe recipe : recipes) {
            boolean add = true;
            //System.out.println("checking items for recipe");
            for (ItemStack stack : recipe.input) {
                if (!inv.contains(stack)) {
                    add = false;
                    //System.out.println("you don't have " + stack.item.itemName + " for " + recipe.output.item.itemName);
                    break;
                }
            }
            if (add == true) {
                //System.out.println("recipe added for " + recipe.output.item.itemName);
                boolean ad = true;
                for (CraftingRecipe r : availableRecipes) {
                    if (r.output == recipe.output) {
                        ad = false;
                    }
                }
                if (ad == true)
                    availableRecipes.add(recipe);
            }
        }
    }
}
