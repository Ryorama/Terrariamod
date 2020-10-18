package kmerrill285.trewrite.entities;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class RemoveMaxHealthLimit {
	
	public static final IAttribute MAX_HEALTH = (IAttribute)(new RangedAttribute(null, "generic.maxHealth", 20.0D, 1.0D, 100000000.0D)).setDescription("Removes Health Limit").setShouldWatch(true);

}
