package com.ryorama.terrariamod.entities;

import com.ryorama.terrariamod.TerrariaMod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;

public class EntitiesT {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);


    //public static final RegistrySupplier<BlockEntityType<WoodChestBlockEntity>> WOOD_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(WoodChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));

    //public static final RegistrySupplier<BlockEntityType<GoldChestBlockEntity>> GOLD_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(GoldChestBlockEntity::new, BlocksT.GOLD_CHEST.get()).build(null));
    //public static final RegistrySupplier<BlockEntityType<FrozenChestBlockEntity>> FROZEN_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(FrozenChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));
   // public static final RegistrySupplier<BlockEntityType<IvyChestBlockEntity>> IVY_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(IvyChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));
   // public static final RegistrySupplier<BlockEntityType<SandstoneChestBlockEntity>> SANDSTONE_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(SandstoneChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));
   // public static final RegistrySupplier<BlockEntityType<WaterChestBlockEntity>> WATER_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(WaterChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));
   // public static final RegistrySupplier<BlockEntityType<SkywareChestBlockEntity>> SKYWARE_CHEST = BLOCK_ENTITY_TYPES.register(new Identifier(TerrariaMod.MOD_ID, "wood_chest"), () -> BlockEntityType.Builder.create(SkywareChestBlockEntity::new, BlocksT.WOOD_CHEST.get()).build(null));

    public static void init() {

    }
}
