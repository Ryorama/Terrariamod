package com.ryorama.terrariamod.blocks.chests;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.api.ChestT;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class WoodChest extends ChestT {

    public WoodChest(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    public Identifier getTexture() {
        return new Identifier(TerrariaMod.MODID, "textures/block/chests/wood_chest.png");
    }
}
