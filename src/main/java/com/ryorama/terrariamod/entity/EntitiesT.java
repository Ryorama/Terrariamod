package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entity.block.*;
import com.ryorama.terrariamod.entity.collectables.HeartEntity;
import com.ryorama.terrariamod.entity.collectables.ManaStarEntity;
import com.ryorama.terrariamod.entity.hostile.*;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import com.ryorama.terrariamod.entity.hostile.projectiles.DemonScythProjectile;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;
import com.ryorama.terrariamod.entity.model.*;
import com.ryorama.terrariamod.entity.model.bosses.RenderEyeOfCthulhu;
import com.ryorama.terrariamod.entity.model.bosses.RenderKingSlime;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntitiesT {
	  public static final EntityType<EntityGreenSlime> GREEN_SLIME = Registry.register(
	            Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "green_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityGreenSlime::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityBlueSlime> BLUE_SLIME = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "blue_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityBlueSlime::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityDemonEye> DEMON_EYE = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon_eye"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityDemonEye::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityKingSlime> KING_SLIME = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "king_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<EntityKingSlime>) EntityKingSlime::new).dimensions(EntityDimensions.fixed(10, 10)).build()
	    );
	  
	  public static final EntityType<EntityDemon> DEMON = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityDemon::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<DemonScythProjectile> DEMON_SYCTH = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon_sycth"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DemonScythProjectile::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityEaterOfSouls> EATER_OF_SOULS = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "eater_of_souls"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityEaterOfSouls::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityEyeOfCthulhu> EOC = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "eoc"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityEyeOfCthulhu::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityGranityElemental> GRANITE_ELEMETAL = Registry.register(
			  Registries.ENTITY_TYPE,
	            new Identifier("terrariamod", "granite_elemental"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityGranityElemental::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );

	public static final EntityType<EntityDrippler> DRIPPLER = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("terrariamod", "drippler"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityDrippler::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	);

	public static final EntityType<EntitySporeZombie> SPORE_ZOMBIE = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("terrariamod", "spore_zombie"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<EntitySporeZombie>) EntitySporeZombie::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	);

	public static final EntityType<EntitySporeSkeleton> SPORE_SKELETON = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("terrariamod", "spore_skeleton"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<EntitySporeSkeleton>) EntitySporeSkeleton::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	);

	public static final EntityType<HeartEntity> HEART = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("terrariamod", "heart"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HeartEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	);

	public static final EntityType<ManaStarEntity> MANA_STAR = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("terrariamod", "mana_star"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ManaStarEntity::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	);

	public static final BlockEntityType<WoodChestBlockEntity> WOOD_CHEST = FabricBlockEntityTypeBuilder.create(WoodChestBlockEntity::new, BlocksT.WOOD_CHEST).build();
	public static final BlockEntityType<GoldChestBlockEntity> GOLD_CHEST = FabricBlockEntityTypeBuilder.create(GoldChestBlockEntity::new, BlocksT.GOLD_CHEST).build();
	public static final BlockEntityType<FrozenChestBlockEntity> FROZEN_CHEST = FabricBlockEntityTypeBuilder.create(FrozenChestBlockEntity::new, BlocksT.FROZEN_CHEST).build();
	public static final BlockEntityType<IvyChestBlockEntity> IVY_CHEST = FabricBlockEntityTypeBuilder.create(IvyChestBlockEntity::new, BlocksT.IVY_CHEST).build();
	public static final BlockEntityType<SandstoneChestBlockEntity> SANDSTONE_CHEST = FabricBlockEntityTypeBuilder.create(SandstoneChestBlockEntity::new, BlocksT.SANDSTONE_CHEST).build();
	public static final BlockEntityType<WaterChestBlockEntity> WATER_CHEST = FabricBlockEntityTypeBuilder.create(WaterChestBlockEntity::new, BlocksT.WATER_CHEST).build();
	public static final BlockEntityType<SkywareChestBlockEntity> SKYWARE_CHEST = FabricBlockEntityTypeBuilder.create(SkywareChestBlockEntity::new, BlocksT.SKYWARE_CHEST).build();

	public static void init() {
		  FabricDefaultAttributeRegistry.register(GREEN_SLIME, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(BLUE_SLIME, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON_EYE, MobEntity.createMobAttributes());
		  //FabricDefaultAttributeRegistry.register(DEMON, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(SPORE_ZOMBIE, EntitySporeZombie.createZombieAttributes());
		  FabricDefaultAttributeRegistry.register(SPORE_SKELETON, EntitySporeSkeleton.createZombieAttributes());
		  FabricDefaultAttributeRegistry.register(DRIPPLER, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(KING_SLIME, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON_SYCTH, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(EATER_OF_SOULS, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(EOC, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(GRANITE_ELEMETAL, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(HEART, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(MANA_STAR, MobEntity.createMobAttributes());

		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "wood_chest"), WOOD_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "gold_chest"), GOLD_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "ivy_chest"), IVY_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "skyware_chest"), SKYWARE_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "water_chest"), WATER_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "sandstone_chest"), SANDSTONE_CHEST);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TerrariaMod.MODID, "frozen_chest"), FROZEN_CHEST);
	}

	public static void initClient() {
		ModelRegistry.registerModels();
	}
 
         @Environment(EnvType.CLIENT)
         public class ModelRegistry {
            @Environment(EnvType.CLIENT)
	  public static void registerModels() {
				EntityRendererRegistry.register(EntitiesT.GREEN_SLIME,
						(context) -> {
							return new RenderGreenSlime(context);
						});

				EntityRendererRegistry.register(EntitiesT.BLUE_SLIME,
						(context) -> {
							return new RenderBlueSlime(context);
						});

				EntityRendererRegistry.register(EntitiesT.DEMON_EYE,
						(context) -> {
							return new RenderDemonEye(context);
						});

				EntityRendererRegistry.register(EntitiesT.KING_SLIME,
						(context) -> {
							return new RenderKingSlime(context);
						});

				EntityRendererRegistry.register(EntitiesT.DEMON,
						(context) -> {
							return new RenderDemon(context);
						});

				EntityRendererRegistry.register(EntitiesT.DEMON_SYCTH,
						(context) -> {
							return new RenderDemonSycth(context);
						});

				EntityRendererRegistry.register(EntitiesT.EATER_OF_SOULS,
						(context) -> {
							return new RenderEaterOfSouls(context);
						});

				EntityRendererRegistry.register(EntitiesT.EOC,
						(context) -> {
							return new RenderEyeOfCthulhu(context);
						});

				EntityRendererRegistry.register(EntitiesT.GRANITE_ELEMETAL,
						(context) -> {
							return new RenderGraniteElemental(context);
						});

				EntityRendererRegistry.register(EntitiesT.DRIPPLER,
						(context) -> {
							return new RenderDrippler(context);
						});
				EntityRendererRegistry.register(EntitiesT.DRIPPLER,
						(context) -> {
							return new RenderDrippler(context);
						});
				EntityRendererRegistry.register(EntitiesT.SPORE_ZOMBIE,
						(context) -> {
							return new RenderSporeZombie(context);
						});
				EntityRendererRegistry.register(EntitiesT.SPORE_SKELETON,
						(context) -> {
							return new RenderSporeSkeleton(context);
						});
				EntityRendererRegistry.register(EntitiesT.HEART,
						(context) -> {
							return new RenderHeart(context);
						});
				EntityRendererRegistry.register(EntitiesT.MANA_STAR,
						(context) -> {
							return new RenderManaStar(context);
						});


				BlockEntityRendererRegistry.register(GOLD_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(FROZEN_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(IVY_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(SANDSTONE_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(WATER_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(SKYWARE_CHEST, ChestBlockEntityRendererT::new);
				BlockEntityRendererRegistry.register(WOOD_CHEST, ChestBlockEntityRendererT::new);

				ClientSpriteRegistryCallback.event(TexturedRenderLayers.CHEST_ATLAS_TEXTURE).register((texture, registry) -> {
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/gold_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/frozen_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/ivy_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/sandstone_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/water_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/skyware_chest"));
					registry.register(new Identifier(TerrariaMod.MODID, "block/chests/wood_chest"));
				});
			}
	  }
}
