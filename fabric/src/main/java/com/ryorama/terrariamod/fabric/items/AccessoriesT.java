package com.ryorama.terrariamod.fabric.items;

import com.ryorama.terrariamod.TerrariaMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class AccessoriesT {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.ITEM);

    //public static final RegistrySupplier<Item> AGLET = register("aglet", () -> new Aglet(new Item);

    public static void init() {
        ITEMS.register();
    }

    static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(new Identifier(TerrariaMod.MOD_ID, name), item);
    }

}
