package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Conversions;

public class SilverOre extends BlockT {

	public SilverOre(Properties properties) {
		super(properties, BlocksT.ORE_HARDNESS, 20.0f, "silver_ore");
		this.pick = true;
		this.setLocation("silver_ore");
		this.sell = 200;
		this.buy = Conversions.sellToBuy(sell);
		this.material = true;
	}

}
