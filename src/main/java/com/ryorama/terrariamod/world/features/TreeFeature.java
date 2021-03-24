package com.ryorama.terrariamod.world.features;

import com.mojang.serialization.Codec;
import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class TreeFeature extends Feature {

	public TreeFeature(Codec configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(FeatureContext context) {
		if (context.getRandom().nextInt(20) == 0) {
			context.getWorld().setBlockState(new BlockPos(context.getOrigin().getX(), context.getOrigin().getY(), context.getOrigin().getZ()), BlocksT.FOREST_STUMP.getDefaultState(), 0);
			
			int height = context.getRandom().nextInt(7);
			
			for (int y = context.getOrigin().getY() + 1; y <= context.getOrigin().getY() + height; y++) {
				context.getWorld().setBlockState(new BlockPos(context.getOrigin().getX(), y, context.getOrigin().getZ()), BlocksT.FOREST_STEM.getDefaultState(), 0);
			}
			context.getWorld().setBlockState(new BlockPos(context.getOrigin().getX(), height + 1, context.getOrigin().getZ()), BlocksT.FOREST_STUMP.getDefaultState(), 0);
			return true;
		} else {
			return false;
		}
	}
}
