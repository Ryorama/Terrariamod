package com.ryorama.terrariamod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntitiesT {
	
	public static EntityType<EntityItemT> ITEM;
	
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	   public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
	      EntitiesT.ITEM = register("trewrite:entityitemt", Builder.<EntityItemT>create(EntityItemT::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
	    	  	return new EntityItemT(world);
	      }));
		}
	
	private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
	      return Registry.register(Registry.ENTITY_TYPE, id, builder.build(id));
 }

}
