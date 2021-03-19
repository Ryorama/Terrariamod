package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.block.Block;
import net.minecraft.data.server.AbstractTagProvider;
import net.minecraft.data.server.BlockTagsProvider;

@Mixin(BlockTagsProvider.class)
public class SpawnBlockMixin {
	
	@Inject(at = @At("HEAD"), method = "configure")
	protected void configure() {
		
	}
}
