package com.ryorama.terrariamod.entity;

import java.util.Random;

import com.ryorama.terrariamod.utils.math.ImprovedRandom;
import com.ryorama.terrariamod.world.WorldDataT;

import net.minecraft.entity.Entity;

public abstract class EntityMainData {
	
	public boolean dayTime;
	
	public boolean eclipse;
	
	public boolean expertMode;
	
	public boolean masterMode;
	
	public boolean hardMode;
	
	public float enemyStrengthMultiplier;
	
	public boolean postPlantera;
	
	public ImprovedRandom rand;
	
	public int netMode;
	
	public void update(Entity ent) {
	  this.eclipse = WorldDataT.solarEclipse;
	  this.expertMode = WorldDataT.expert;
	  this.masterMode = WorldDataT.master;
	  this.enemyStrengthMultiplier = WorldDataT.enemyDifficulty;
	  this.hardMode = WorldDataT.hardmode;
	  this.netMode = ent.world.isClient ? 1 : 2;
	  this.postPlantera = WorldDataT.defeatedBosses.contains("terraria:plantera");
	  if (this.rand == null) {
		  new ImprovedRandom(new Random());
	  }
	}
}