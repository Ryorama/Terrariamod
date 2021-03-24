package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;
import com.ryorama.terrariamod.entity.model.RenderBlueSlime;
import com.ryorama.terrariamod.entity.model.RenderDemonEye;
import com.ryorama.terrariamod.entity.model.RenderSlime;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
	  
	  public static void init() {
		  FabricDefaultAttributeRegistry.register(GREEN_SLIME, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(BLUE_SLIME, LivingEntity.createLivingAttributes());
		  FabricDefaultAttributeRegistry.register(DEMON_EYE, LivingEntity.createLivingAttributes());

		  registerModels();
	  }
	  
	  public static void registerModels() {
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.GREEN_SLIME,
					(context) -> {
						return new RenderSlime(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.BLUE_SLIME,
					(context) -> {
						return new RenderBlueSlime(context);
			});
		  
		  EntityRendererRegistry.INSTANCE.register(EntitiesT.DEMON_EYE,
					(context) -> {
						return new RenderDemonEye(context);
			});
	  }
}
