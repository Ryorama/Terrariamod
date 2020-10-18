package kmerrill285.trewrite.items;

import kmerrill285.trewrite.items.basic.BasicItem;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.ItemGroup;

public class MetalBar extends BasicItem {

	/***
	 * 
	 * @param sellPrice
	 * @param id
	 */
	public MetalBar(int sellPrice, String id) {
		super(new Properties().group(ItemGroup.MATERIALS), sellPrice, id, true);
		this.maxStack = 99;
	}

}
