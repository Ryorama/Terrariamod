package com.ryorama.terrariamod.entities;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entities.terraria.block.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class EntitiesT {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);


    public static final RegistrySupplier<BlockEntityType<WoodChestBlockEntity>> WOOD_CHEST = registerBlockEntity("wood_chest", () -> BlockEntityType.Builder.create(WoodChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<GoldChestBlockEntity>> GOLD_CHEST = registerBlockEntity("gold_chest", () -> BlockEntityType.Builder.create(GoldChestBlockEntity::new, BlocksT.GOLD_CHEST.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<FrozenChestBlockEntity>> FROZEN_CHEST = registerBlockEntity("frozen_chest", () -> BlockEntityType.Builder.create(FrozenChestBlockEntity::new, BlocksT.FROZEN_CHEST.get()).build(null));
   public static final RegistrySupplier<BlockEntityType<IvyChestBlockEntity>> IVY_CHEST = registerBlockEntity("ivy_chest", () -> BlockEntityType.Builder.create(IvyChestBlockEntity::new, BlocksT.IVY_CHEST.get()).build(null));
   public static final RegistrySupplier<BlockEntityType<SandstoneChestBlockEntity>> SANDSTONE_CHEST = registerBlockEntity("sandstone_chest", () -> BlockEntityType.Builder.create(SandstoneChestBlockEntity::new, BlocksT.SANDSTONE_CHEST.get()).build(null));
   public static final RegistrySupplier<BlockEntityType<WaterChestBlockEntity>> WATER_CHEST = registerBlockEntity("water_chest", () -> BlockEntityType.Builder.create(WaterChestBlockEntity::new, BlocksT.WATER_CHEST.get()).build(null));
   public static final RegistrySupplier<BlockEntityType<SkywareChestBlockEntity>> SKYWARE_CHEST = registerBlockEntity("skyware_chest", () -> BlockEntityType.Builder.create(SkywareChestBlockEntity::new, BlocksT.SKYWARE_CHEST.get()).build(null));

    public static void init() {
        BLOCK_ENTITY_TYPES.register();
    }

    static <T extends BlockEntityType> RegistrySupplier<T> registerBlockEntity(String name, Supplier<T> entity) {
        Identifier id = new Identifier(TerrariaMod.MOD_ID, name);
        return BLOCK_ENTITY_TYPES.register(name, entity);
    }
}
