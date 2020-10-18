package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.block.Block.Properties;

public class CrimtaneOre extends BlockT {

	public CrimtaneOre(Properties properties) {
		super(properties, BlocksT.ORE_HARDNESS, 55.0f, "crimtane_ore");
		this.pick = true;
		this.setLocation("crimtane_ore");
		this.sell = 800;
		this.buy = Conversions.sellToBuy(sell);
		this.material = true;
	}

}
