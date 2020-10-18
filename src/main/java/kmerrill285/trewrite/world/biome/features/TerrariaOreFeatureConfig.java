package kmerrill285.trewrite.world.biome.features;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class TerrariaOreFeatureConfig implements IFeatureConfig {
	   public final BlockState target;
	   public final int size;
	   public final BlockState state;
	   

	   public TerrariaOreFeatureConfig(BlockState target, BlockState state, int size) {
	      this.size = size;
	      this.state = state;
	      this.target = target;
	   }

	   public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
	      return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("size"), ops.createInt(this.size), ops.createString("target"), BlockState.serialize(ops, this.target).getValue(), ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
	   }
	   
	   public static TerrariaOreFeatureConfig deserialize(Dynamic<?> p_214641_0_) {
		      int i = p_214641_0_.get("size").asInt(0);
		      BlockState blockstate = p_214641_0_.get("state").map(BlockState::deserialize).orElse(BlocksT.AIR_BLOCK.getDefaultState());
		      BlockState target = p_214641_0_.get("target").map(BlockState::deserialize).orElse(BlocksT.AIR_BLOCK.getDefaultState());
		      return new TerrariaOreFeatureConfig(target, blockstate, i);
		   }

	   
}
