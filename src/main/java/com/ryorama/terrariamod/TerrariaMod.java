package com.ryorama.terrariamod;

import java.lang.reflect.Field;
import java.util.OptionalLong;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.projectiles.TerrariaInventoryScreen;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.world.EntitySpawner;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer, ClientModInitializer {

	public static String modid = "terrariamod";
	
	private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, 
			-256,
			256, 
			256, 
			HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(),
			DimensionType.OVERWORLD_ID, 0.0F);
	
	
	@Override
	public void onInitialize() {
		BiomeRegistry.RegisterBiomes();
		BlocksT.init();
		ItemsT.init();
		
		ModifyWorldHeight();
		GeckoLib.initialize();
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		EntitiesT.init();

		ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
		onTick();
		addCutouts();
	}

	public void addCutouts() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE, RenderLayer.getCutout());
	}
	
 
	public void onTick() {
		
		TerrariaUIRenderer.renderTerrariaHealth();
		TerrariaUIRenderer.renderTerrariaDefense();
		TerrariaUIRenderer.renderTerrariaMana();
		
		TMusicTicker.musicChanged = true;
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
		
			TMusicTicker.onTickUpdate();
			
		});
		
		/*
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			if (!player.isCreative()) {
				if (world.getBlockState(pos).getBlock() instanceof BlockT) {
					BlockT block = (BlockT)world.getBlockState(pos).getBlock();
									
					if (player.getInventory().getMainHandStack().getItem() instanceof ItemT) {
						ItemT item = (ItemT)player.getInventory().getMainHandStack().getItem();
						if (block.difficulty > 0) {
							if (block.pick && item.pick >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							if (block.axe && item.axe >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							if (block.hammer && item.hammer >= block.difficulty) {
				                return ActionResult.SUCCESS;
							}
							return ActionResult.FAIL;
						}
						return ActionResult.FAIL;
					}
					return ActionResult.FAIL;
				}
				return ActionResult.FAIL;
			} else {
				return ActionResult.SUCCESS;
			}
		});
		*/
		
		WorldTickCallback.EVENT.register(world -> {
			
			if (world.getRandom().nextInt(700) <= 10)
			if (world.getPlayers().size() > 0) {
				PlayerEntity player = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
				double x = player.getPos().x + world.random.nextInt(80) - 40, y = player.getPos().y + world.random.nextInt(80) - 40, z = player.getPos().z + world.random.nextInt(80) - 40;
				
				for (PlayerEntity p2 : world.getPlayers()) {
						if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
							new Thread () {
								public void run() {
									EntitySpawner.spawnEntities(player, x, y, z);
								}
							}.start();
							
							break;
						}
					}
				}
		});
		
	}
	
	private static void ModifyWorldHeight() {
		//OVERWORLD: 14
		Field[] dimension_fields = DimensionType.class.getDeclaredFields();
		for (int i = 0; i < dimension_fields.length; i++) {
			try {
				Resources.makeFieldAccessible(dimension_fields[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(dimension_fields[i].getName() + ", " + i);
		}
		int overworld_num = 14;
		Field overworld_field = dimension_fields[overworld_num];
		
		try {
			Resources.makeFieldAccessible(overworld_field);
			overworld_field.set(null, MODIFIED_OVERWORLD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * OVERWORLD = create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -64,
				384, 384, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(),
				OVERWORLD_ID, 0.0F);
		 */
	}	
}