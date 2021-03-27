package com.ryorama.terrariamod.entity.hostile;

import java.util.ArrayList;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntityBaseMob;
import com.ryorama.terrariamod.entity.EntityProps;
import com.ryorama.terrariamod.entity.IBoss;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.BossBar;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class EntityDemonEye extends EntityBaseMob implements IBoss {

	public static ArrayList<ItemStack> armorItems = new ArrayList<ItemStack>();
	
	public EntityProps props2;
	
	public EntityDemonEye(EntityType<? extends LivingEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		if (world.isClient) {
			this.setBossIcon();
			this.activateBoss();
		}
	}

	@Override
	public void AI() {
        boolean night = world.getTime() % 24000L > 15000L && world.getTime() % 24000L < 22000L;
		if (!night) {
			
		} else {
			this.velY = 0.5f;
		}
		
		this.updateBossHealthBar();
		
		if (this.getHealth() <= 0 && world.isClient) {
			this.defeatedBoss();
			return;
		}
	}

	public void lookRandomly() {
		this.setRotation(this.headYaw + this.random.nextInt(3), this.pitch);
	}
	
	@Environment(EnvType.CLIENT)
	public boolean shouldRender(double distance) {
		double d = this.getBoundingBox().getAverageSideLength() * 4.0D;
		if (Double.isNaN(d) || d == 0.0D) {
			d = 4.0D;
		}
		d *= 64.0D;
		return distance < d * d;
	}
	
	@Environment(EnvType.CLIENT)
	public Box getVisibilityBoundingBox() {
		return this.getBoundingBox();
	}

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return armorItems;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		
	}

	@Override
	public Arm getMainArm() {
		return Arm.LEFT;
	}


	@Override
	public void drops() {
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().player.getInventory().insertStack(new ItemStack(ItemsT.gel(this.random.nextInt(2), "ffee00").getItem()));
		}
	}
	
	@Override
	public void initProps(EntityProps props) {
		this.props2 = props;
		props.noGravity = true;
		props.damage = 12;
		props.lifeMax = 17;
	}

	@Override
	public CompoundTag saveData(CompoundTag tag) {
		tag.putFloat("lifemax", props2.lifeMax);
		tag.putFloat("damage", props2.damage);
		return tag;
	}

	@Override
	public void loadData(CompoundTag tag) {
		props2.lifeMax = tag.getFloat("lifemax");
		props2.damage = tag.getFloat("damage");
	}

	@Override
	public SoundEvent setBossMusic() {
		return TAudio.NIGHT;
	}

	@Override
	public Identifier bossIcon() {
		return new Identifier(TerrariaMod.modid, "textures/ui/boss_icons/eoc_icon.png");
	}

	@Override
	public float getBossHealth() {
		return this.getHealth();
	}

	@Override
	public float getBossMaxHealth() {
		return this.getMaxHealth();
	}
}
