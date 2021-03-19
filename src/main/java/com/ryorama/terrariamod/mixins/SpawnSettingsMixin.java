package com.ryorama.terrariamod.mixins;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;

@Mixin(SpawnSettings.class)
public class SpawnSettingsMixin {
	
	private static List<SpawnEntry> entitySpawnEntries = new ArrayList<SpawnEntry>();
	
	@Inject(at = @At("HEAD"), method = "getSpawnEntries", cancellable = true)
	public void getSpawnEntries(SpawnGroup spawnGroup, CallbackInfoReturnable<List<SpawnEntry>> info) {
		info.setReturnValue(entitySpawnEntries);
	}
}
