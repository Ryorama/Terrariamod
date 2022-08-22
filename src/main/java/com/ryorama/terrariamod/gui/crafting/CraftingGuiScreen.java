package com.ryorama.terrariamod.gui.crafting;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class CraftingGuiScreen extends CottonInventoryScreen<CraftingGuiDescription> {
    public CraftingGuiScreen(CraftingGuiDescription description, PlayerEntity player, Text title) {
        super(description, player);
    }
}
