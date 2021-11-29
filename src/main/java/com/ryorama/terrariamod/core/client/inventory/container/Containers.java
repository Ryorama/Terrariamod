package com.ryorama.terrariamod.core.client.inventory.container;

import net.minecraft.screen.ScreenHandlerType;

public class Containers {
    public static ScreenHandlerType<ContainerTerrariaInventory> INVENTORY;

    public static void registerContainers() {
        Containers.INVENTORY = ScreenHandlerType.register("trewrite:inventory", ContainerTerrariaInventory::new);
    }
}
