package com.ryorama.terrariamod.mixins;

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
	public void getBiome(BlockPos pos, CallbackInfoReturnable<Biome> info) {
		//return this.type.getBiome(this.seed, pos.getX(), pos.getY(), pos.getZ(), this.storage);
		
		if (pos.getX() > 200 && pos.getX() < 400) {
			biome = BiomeCorruption.CORRUPTION;
			info.setReturnValue(biome);
		} else if (pos.getX() > 401 && pos.getX() < 602) {
			biome = BiomeDesert.DESERT;
			info.setReturnValue(biome);
		} else if (pos.getX() > 603 && pos.getX() < 804) {
			biome = BiomeSnow.SNOW;
			info.setReturnValue(biome);
		} else if (pos.getX() > -200 && pos.getX() < -400) {
			biome = BiomeCorruption.CORRUPTION;
			info.setReturnValue(biome);
		} else if (pos.getX() > -401 && pos.getX() < -602) {
			biome = BiomeDesert.DESERT;
			info.setReturnValue(biome);
		} else if (pos.getX() > -603 && pos.getX() < -804) {
			biome = BiomeSnow.SNOW;
			info.setReturnValue(biome);
		} else if (pos.getX() > -1005 && pos.getX() < -1206) {
			biome = BiomeJungle.JUNGLE;
			info.setReturnValue(biome);
		} else {
			biome = BiomePurity.PUTITY;
			info.setReturnValue(biome);
		}
	}
}
