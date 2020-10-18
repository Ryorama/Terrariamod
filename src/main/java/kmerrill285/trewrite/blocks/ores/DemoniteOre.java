package kmerrill285.trewrite.blocks.ores;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Conversions;

public class DemoniteOre extends BlockT {

	public DemoniteOre(Properties properties) {
		super(properties, BlocksT.ORE_HARDNESS, 55.0f, "demonite_ore");
		this.pick = true;
		this.setLocation("demonite_ore");
		this.sell = 800;
		this.buy = Conversions.sellToBuy(sell);
		this.material = true;
	}

}
