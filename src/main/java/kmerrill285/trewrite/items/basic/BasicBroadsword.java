package kmerrill285.trewrite.items.basic;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class BasicBroadsword extends Broadsword {
	/***
	 * 
	 * @param damage
	 * @param knockback
	 * @param useTime
	 * @param sellPrice
	 * @param id
	 */
	public BasicBroadsword(int damage, int knockback, int useTime, int sellPrice, String id) {
		super();
		this.damage = damage;
		this.knockback = knockback;
		this.useTime = useTime;
		this.sellPrice = sellPrice;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation(id);
	}
}
