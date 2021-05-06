package com.ryorama.terrariamod.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityProps {

		public boolean noGravity;
		  
		  public float lifeMax;
		  
		  public float defense;
		  
		  public float width;
		  
		  public float height;
		  
		  public int value;
		  
		  public SoundEvent hitSound;
		  
		  public SoundEvent deathSound;
		  
		  public float knockBackResist;
		  
		  public float damage;
		  
		  public int maxAI = 5;
		  
		  public boolean overrideVel = true;
		  
		  public void loadEntityData(PlayerEntity entity) {
			  NbtCompound tag = new NbtCompound();
			 tag.putBoolean("NoGravity", this.noGravity);
			 tag.putBoolean("OverrideVel", this.overrideVel);
			 tag.putFloat("LifeMax", this.lifeMax);
			 tag.putFloat("Defense", this.defense);
			 tag.putFloat("Width", this.width);
			 tag.putFloat("Height", this.height);
			 tag.putInt("Value", this.value);
			 tag.putFloat("KBResist", this.knockBackResist);
			 tag.putFloat("Damage", this.damage);
			 tag.putInt("MaxAI", this.maxAI);
			 tag.putString("HitSound", (this.hitSound != null) ? this.hitSound.getId().toString() : "");
			 tag.putString("DeathSound", (this.deathSound != null) ? this.deathSound.getId().toString() : "");
		  }
		  
		  public void saveEntityData(PlayerEntity entity) {
			  NbtCompound nbt = new NbtCompound();
			  this.noGravity = nbt.getBoolean("NoGravity");
			  this.overrideVel = nbt.getBoolean("OverrideVel");
			  this.lifeMax = nbt.getFloat("LifeMax");
			  this.defense = nbt.getFloat("Defense");
			  this.width = nbt.getFloat("Width");
			  this.height = nbt.getFloat("Height");
			  this.value = nbt.getInt("Value");
			  this.damage = nbt.getFloat("Damage");
			  this.maxAI = nbt.getInt("MaxAI");
			  this.knockBackResist = nbt.getFloat("KBResist");
			  this.hitSound = (SoundEvent)Registry.SOUND_EVENT.get(new Identifier(nbt.getString("HitSound")));
			  this.deathSound = (SoundEvent)Registry.SOUND_EVENT.get(new Identifier(nbt.getString("DeathSound")));
		  }
}
