package com.ryorama.terrariamod.mixins;

import net.minecraft.util.registry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.biomes.BiomeCorruption;
import com.ryorama.terrariamod.biomes.BiomeDesert;
import com.ryorama.terrariamod.biomes.BiomeJungle;
import com.ryorama.terrariamod.biomes.BiomePurity;
import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.biomes.BiomeSnow;

import net.minecraft.client.option.HotbarStorage;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;

@Mixin(BiomeAccess.class)
public class BiomeAccessMixin {
	
	private Biome biome;
	
	@Inject(at = @At("HEAD"), method = "getBiome", cancellable = true)
	public void getBiome(BlockPos pos, CallbackInfoReturnable<RegistryEntry<Biome>> info) {
		if (pos.getX() > 200 && pos.getX() < 400) {
			biome = BiomeCorruption.CORRUPTION;
		} else if (pos.getX() > 401 && pos.getX() < 602) {
			biome = BiomeDesert.DESERT;
		} else if (pos.getX() > 603 && pos.getX() < 804) {
			biome = BiomeSnow.SNOW;
		} else if (pos.getX() > -200 && pos.getX() < -400) {
			biome = BiomeCorruption.CORRUPTION;
		} else if (pos.getX() > -401 && pos.getX() < -602) {
			biome = BiomeDesert.DESERT;
		} else if (pos.getX() > -603 && pos.getX() < -804) {
			biome = BiomeSnow.SNOW;
		} else if (pos.getX() > -1005 && pos.getX() < -1206) {
			biome = BiomeJungle.JUNGLE;
		} else {
			biome = BiomePurity.PUTITY;
		}
		info.setReturnValue(RegistryEntry.of(biome));
	}
}
