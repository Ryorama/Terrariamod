package com.ryorama.terrariamod.world.features;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class CopperOreFeature {

	 public static ConfiguredFeature<?, ?> COPPER_ORE_FEATURE = Feature.ORE
			    .configure(new OreFeatureConfig(
			      new BlockMatchRuleTest(BlocksT.STONE_BLOCK),
			      BlocksT.COPPER_ORE.getDefaultState(),
			      9)) // vein size
			    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(YOffset.aboveBottom(-255), YOffset.belowTop(80))))
			    .spreadHorizontally()
			    .repeat(20); // number of veins per chunk

}
