package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Conversions;

public class GoldOre extends BlockT {

	public GoldOre(Properties properties) {
		super(properties, BlocksT.ORE_HARDNESS, 20.0f, "gold_ore");
		this.pick = true;
		this.setLocation("gold_ore");
		this.sell = 400;
		this.buy = Conversions.sellToBuy(sell);
		this.material = true;
	}

}
