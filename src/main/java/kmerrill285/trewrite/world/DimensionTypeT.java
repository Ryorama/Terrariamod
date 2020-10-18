package kmerrill285.trewrite.world;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

public class DimensionTypeT extends DimensionType {

	public DimensionTypeT(int idIn, String suffixIn, String directoryIn,
			BiFunction<World, DimensionType, ? extends Dimension> p_i49935_4_, boolean p_i49935_5_) {
		super(idIn, suffixIn, directoryIn, p_i49935_4_, p_i49935_5_);
	}

}
