package com.ryorama.terrariamod.core.inventory.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Containers {
	
	 public static ContainerType<ContainerTerrariaInventory> INVENTORY;

	 @SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
		 Containers.INVENTORY = register("terrariamod:inventory", ContainerTerrariaInventory::new);
	 }
	 
	 private static <T extends Container> ContainerType<T> register(String key, ContainerType.IFactory<T> factory) {
	      return Registry.register(Registry.MENU, key, new ContainerType<>(factory));
	   }
	 
//	 static {
//		 
//	 }
}