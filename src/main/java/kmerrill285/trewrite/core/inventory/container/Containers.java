package kmerrill285.trewrite.core.inventory.container;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.ScreenManager.IScreenFactory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.StonecutterContainer;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class Containers {
	
	 public static ContainerType<ContainerTerrariaInventory> INVENTORY;
	 public static ContainerType<ContainerTerrariaChest> CHEST;

	 @SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
		 Containers.INVENTORY = register("trewrite:inventory", ContainerTerrariaInventory::new);
		 Containers.CHEST = register("trewrite:chest", ContainerTerrariaChest::new);
	 }
	 
	 private static <T extends Container> ContainerType<T> register(String key, ContainerType.IFactory<T> factory) {
	      return Registry.register(Registry.MENU, key, new ContainerType<>(factory));
	   }
	 
//	 static {
//		 
//	 }
}
