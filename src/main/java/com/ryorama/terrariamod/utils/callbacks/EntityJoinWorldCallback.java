package com.ryorama.terrariamod.utils.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;

public interface EntityJoinWorldCallback {
	
	Event<EntityJoinWorldCallback> EVENT = EventFactory.createArrayBacked(EntityJoinWorldCallback.class,
	        (listeners) -> (entity) -> {
	            for (EntityJoinWorldCallback listener : listeners) {
	                ActionResult result = listener.join(entity);
	 
	                if(result != ActionResult.PASS) {
	                    return result;
	                }
	            }
	 
	        return ActionResult.PASS;
	    });
	 
	    ActionResult join(Entity entity);

}
