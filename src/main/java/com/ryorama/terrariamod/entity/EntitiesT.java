package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.entity.hostile.EntityDemon;
import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entity.hostile.EntityEaterOfSouls;
import com.ryorama.terrariamod.entity.hostile.EntityGranityElemental;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import com.ryorama.terrariamod.entity.hostile.projectiles.DemonScythProjectile;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;
import com.ryorama.terrariamod.entity.model.RenderBlueSlime;
import com.ryorama.terrariamod.entity.model.RenderDemon;
import com.ryorama.terrariamod.entity.model.RenderDemonEye;
import com.ryorama.terrariamod.entity.model.RenderDemonSycth;
import com.ryorama.terrariamod.entity.model.RenderEaterOfSouls;
import com.ryorama.terrariamod.entity.model.RenderGraniteElemental;
import com.ryorama.terrariamod.entity.model.RenderGreenSlime;
import com.ryorama.terrariamod.entity.model.bosses.RenderEyeOfCthulhu;
import com.ryorama.terrariamod.entity.model.bosses.RenderKingSlime;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class EntitiesT {

	  public static final EntityType<EntityGreenSlime> GREEN_SLIME = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "green_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityGreenSlime::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityBlueSlime> BLUE_SLIME = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "blue_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityBlueSlime::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityDemonEye> DEMON_EYE = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon_eye"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityDemonEye::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityKingSlime> KING_SLIME = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "king_slime"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<EntityKingSlime>) EntityKingSlime::new).dimensions(EntityDimensions.fixed(10, 10)).build()
	    );
	  
	  public static final EntityType<EntityDemon> DEMON = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityDemon::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<DemonScythProjectile> DEMON_SYCTH = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "demon_sycth"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DemonScythProjectile::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityEaterOfSouls> EATER_OF_SOULS = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "eater_of_souls"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityEaterOfSouls::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityEyeOfCthulhu> EOC = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "eoc"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityEyeOfCthulhu::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static final EntityType<EntityGranityElemental> GRANITE_ELEMETAL = Registry.register(
	            Registry.ENTITY_TYPE,
	            new Identifier("terrariamod", "granite_elemental"),
	            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EntityGranityElemental::new).dimensions(EntityDimensions.fixed(1, 1)).build()
	    );
	  
	  public static void init() {
		  FabricDefaultAttributeRegistry.register(GREEN_SLIME, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(BLUE_SLIME, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON_EYE, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(KING_SLIME, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON_SYCTH, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(EATER_OF_SOULS, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(EOC, MobEntity.createMobAttributes());
		  FabricDefaultAttributeRegistry.register(GRANITE_ELEMETAL, MobEntity.createMobAttributes());

		  registerModels();
	  }
	  
	  public static void registerModels() {
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.GREEN_SLIME,
					(context) -> {
						return new RenderGreenSlime(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.BLUE_SLIME,
					(context) -> {
						return new RenderBlueSlime(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.DEMON_EYE,
					(context) -> {
						return new RenderDemonEye(context);
			});
	  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.KING_SLIME,
					(context) -> {
						return new RenderKingSlime(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.DEMON,
					(context) -> {
						return new RenderDemon(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.DEMON_SYCTH,
					(context) -> {
						return new RenderDemonSycth(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.EATER_OF_SOULS,
					(context) -> {
						return new RenderEaterOfSouls(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.EOC,
					(context) -> {
						return new RenderEyeOfCthulhu(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.GRANITE_ELEMETAL,
					(context) -> {
						return new RenderGraniteElemental(context);
			});
	  }
}
