package kmerrill285.trewrite.items.basic;

import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.ItemGroup;

public class BasicItem extends ItemT {

	/***
	 * 
	 * @param properties
	 * @param sellPrice
	 * @param id
	 * @param material
	 */
	public BasicItem(Properties properties, int sellPrice, String id, boolean material) {
		super(new Properties().group(ItemGroup.MATERIALS));
		this.material = material;
		this.sellPrice = sellPrice;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation(id);
	}

}
