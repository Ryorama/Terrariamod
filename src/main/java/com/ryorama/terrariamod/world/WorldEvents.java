/*
CLASS Commented out by PugzAreCute. Reason: Incomplete code. Commenting to get CI to work again

package com.ryorama.terrariamod.world;

import java.util.HashMap;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class WorldEvents {

	public static HashMap<String, Entity> summoningTargets = new HashMap<String, Entity>();
	public static HashMap<String, List<Entity>> summons = new HashMap<String, List<Entity>>();
	public static HashMap<String, Double> summon_number = new HashMap<String, Double>();

	public static HashMap<String, Entity> pets = new HashMap<String, Entity>();
	public static HashMap<String, Entity> light_pets = new HashMap<String, Entity>();

	public static void worldSaveEvent() {		
		WorldStateHolder.get(event.getWorld()).markDirty();
	}
	
	@Environment(EnvType.CLIENT)
	public static void worldUnloadEventClient() {
		Util.refreshDimensionRenderer = true;
	}
	
	public static void worldUnloadEvent() {
		//Entity //PUGZARECUTE: Looks like uncompleted code?
		WorldStateHolder.get(event.getWorld()).markDirty();
	}
	
	public static void onEntityJoinWorld() {
		 if (event.getEntity() instanceof PlayerEntity) {
			 if (WorldStateHolder.get(event.getWorld()).firstJoin == false) { 
	             WorldStateHolder.get(event.getWorld()).firstJoin = true;
			 }
		 }
	}
	
}*/
