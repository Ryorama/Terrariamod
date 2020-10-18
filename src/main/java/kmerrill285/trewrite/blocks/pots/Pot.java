package kmerrill285.trewrite.blocks.pots;

import java.util.Random;

import kmerrill285.trewrite.blocks.CrossedBlock;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityCoinPortal;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.EntityStar;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.world.WorldStateHolder;
import kmerrill285.trewrite.world.dimension.DimensionRegistry;
import kmerrill285.trewrite.world.dimension.Dimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class Pot extends CrossedBlock implements IWaterLoggable  {

	protected ItemT[] surface_potions = new ItemT[] {
			ItemsT.BUILDER_POTION, ItemsT.CALMING_POTION, ItemsT.IRONSKIN_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.SHINE_POTION, ItemsT.SWIFTNESS_POTION, ItemsT.MINING_POTION
		};
	protected ItemT[] underground_potions = new ItemT[] {
		ItemsT.ARCHERY_POTION, ItemsT.GILLS_POTION, ItemsT.HUNTER_POTION, ItemsT.MINING_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.RECALL_POTION,
		ItemsT.REGENERATION_POTION, ItemsT.SHINE_POTION, ItemsT.SWIFTNESS_POTION
	};
	protected ItemT[] cavern_potions = new ItemT[] {
		ItemsT.ARCHERY_POTION, ItemsT.FEATHERFALL_POTION, ItemsT.FLIPPER_POTION, ItemsT.GRAVITATION_POTION, ItemsT.HEARTREACH_POTION, ItemsT.HUNTER_POTION,
		ItemsT.INVISIBILITY_POTION, ItemsT.MINING_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.RECALL_POTION, ItemsT.THORNS_POTION, ItemsT.WATER_WALKING_POTION
	};
	protected ItemT[] underworld_potions = new ItemT[] {
		ItemsT.BATTLE_POTION, ItemsT.FEATHERFALL_POTION, ItemsT.HEARTREACH_POTION, ItemsT.HUNTER_POTION, ItemsT.INVISIBILITY_POTION, ItemsT.MAGIC_POWER_POTION, ItemsT.MANA_REGENERATION_POTION, ItemsT.OBSIDIAN_SKIN_POTION, ItemsT.HUNTER_POTION,
		ItemsT.THORNS_POTION, ItemsT.TITAN_POTION, ItemsT.WATER_WALKING_POTION
	};
	
	public Pot(Properties properties, String name) {
		super(properties, 0, 0, true, true, true, false, name, "none");
	}
	
	
	
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		 ItemT[] surface_potions = new ItemT[] {
				ItemsT.BUILDER_POTION, ItemsT.CALMING_POTION, ItemsT.IRONSKIN_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.SHINE_POTION, ItemsT.SWIFTNESS_POTION, ItemsT.MINING_POTION
			};
		 ItemT[] underground_potions = new ItemT[] {
			ItemsT.ARCHERY_POTION, ItemsT.GILLS_POTION, ItemsT.HUNTER_POTION, ItemsT.MINING_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.RECALL_POTION,
			ItemsT.REGENERATION_POTION, ItemsT.SHINE_POTION, ItemsT.SWIFTNESS_POTION
		};
		 ItemT[] cavern_potions = new ItemT[] {
			ItemsT.ARCHERY_POTION, ItemsT.FEATHERFALL_POTION, ItemsT.FLIPPER_POTION, ItemsT.GRAVITATION_POTION, ItemsT.HEARTREACH_POTION, ItemsT.HUNTER_POTION,
			ItemsT.INVISIBILITY_POTION, ItemsT.MINING_POTION, ItemsT.NIGHT_OWL_POTION, ItemsT.RECALL_POTION, ItemsT.THORNS_POTION, ItemsT.WATER_WALKING_POTION
		};
		 ItemT[] underworld_potions = new ItemT[] {
			ItemsT.BATTLE_POTION, ItemsT.FEATHERFALL_POTION, ItemsT.HEARTREACH_POTION, ItemsT.HUNTER_POTION, ItemsT.INVISIBILITY_POTION, ItemsT.MAGIC_POWER_POTION, ItemsT.MANA_REGENERATION_POTION, ItemsT.OBSIDIAN_SKIN_POTION, ItemsT.HUNTER_POTION,
			ItemsT.THORNS_POTION, ItemsT.TITAN_POTION, ItemsT.WATER_WALKING_POTION
		};
		
		Random random = worldIn.rand;
		DimensionType underground = DimensionManager.registerOrGetDimension(Dimensions.undergroundLocation, DimensionRegistry.undergroundDimension, null, true);
		DimensionType underworld = DimensionManager.registerOrGetDimension(Dimensions.underworldLocation, DimensionRegistry.underworldDimension, null, true);
		DimensionType overworld = DimensionType.OVERWORLD;
		
		DimensionType dim = worldIn.getDimension().getType();
		
		if (random.nextDouble() <= 1.0/250.0) {
			EntityCoinPortal.spawnCoinPortal(worldIn, worldIn.getBlockState(pos.up()).getMaterial().blocksMovement() ? pos : pos.up());
			return;
		}
		if (random.nextInt(45) <= 1) {
			ItemT[] potions = null;
			
			
			if (dim == overworld && pos.getY() >= 50) {
				potions = surface_potions;
			}
			if (dim == overworld && pos.getY() < 50) {
				potions = underground_potions;
			}
			if (dim == underground) {
				potions = cavern_potions;
			}
			if (dim == underworld) {
				potions = underworld_potions;
			}
			if (potions != null) {
				ItemT potion = potions[random.nextInt(potions.length)];
				System.out.println(potion);
				EntityItemT.spawnItem(worldIn, pos, new ItemStackT(potion, 1));
			}
			return;
		}
		if (worldIn.getServer().isSinglePlayer() == false)
		if (random.nextDouble() <= 1.0/30.0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.WORMHOLE_POTION, 1));
			return;
		}
		int n = random.nextInt(8) + 1;
		
		
		PlayerEntity closest = null;
		double dist = Double.MAX_VALUE;
		for (int i = 0; i < worldIn.getPlayers().size(); i++) {
			PlayerEntity player = worldIn.getPlayers().get(i);
			if (player != null) {
				double d = player.getPositionVec().distanceTo(new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
				if (d <= dist) {
					dist = d;
					closest = player;
				}
			}
		}
		switch (n) {
		case 1:
			if (closest != null) {
				if (closest.getHealth() < closest.getMaxHealth()) {
					EntityHeart.spawnHeart(worldIn, pos);
					if (random.nextBoolean())
						EntityHeart.spawnHeart(worldIn, pos);
					if (random.nextBoolean())
						EntityHeart.spawnHeart(worldIn, pos);
					break;
				} else {
					n++;
				}
			} else {
				n++;
			}
		case 2:
			if (closest != null) {
				if (ScoreboardEvents.getScore(closest.getWorldScoreboard(), closest, ScoreboardEvents.MANA).getScorePoints() < ScoreboardEvents.getScore(closest.getWorldScoreboard(), closest, ScoreboardEvents.MAX_MANA).getScorePoints()) {
					EntityStar.spawnStar(worldIn, pos);
					break;
				} else {
					n++;
				}
			} else {
				n++;
			}
		case 3:
			if (worldIn.getBlockState(pos).has(WATERLOGGED))
				if (worldIn.getBlockState(pos).get(WATERLOGGED).booleanValue()) {
					EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.GLOWSTICK, random.nextInt(9) + 3));
				} else {
					EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.TORCH, random.nextInt(9) + 3));
				}
			else
				EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.TORCH, random.nextInt(9) + 3));
			break;
		case 4:
			ItemT drop = ItemsT.WOODEN_ARROW;
			if (random.nextBoolean()) {
				if (WorldStateHolder.get(worldIn).hardmode) {
					drop = ItemsT.GRENADE;
				} else {
					drop = ItemsT.SHURIKEN;
				}
			}
			if (dim == underworld) {
				if (WorldStateHolder.get(worldIn).hardmode) {
					drop = ItemsT.UNHOLY_ARROW;
					//or silver bullet
				} else {
					drop = ItemsT.HELLFIRE_ARROW;
				}
			}
				
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, random.nextInt(20) + 20));
			break;
		case 6:
			if (dim == underground || dim == underworld) {
				EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.BOMB, random.nextInt(4 + 3) + 1));
				return;
			}
			if (dim != underworld && WorldStateHolder.get(worldIn).hardmode) {
				EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.ROPE, random.nextInt(20) + 20));
				return;
			}
			break;
		case 7:
			if (dim != underworld && WorldStateHolder.get(worldIn).hardmode) {
				EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.ROPE, random.nextInt(20) + 20));
				return;
			}
		case 8:
			EntityCoin.spawnCoin(worldIn, pos, EntityCoin.COPPER, random.nextInt(100));
			EntityCoin.spawnCoin(worldIn, pos, EntityCoin.SILVER, random.nextInt(5));

		}
		
	}
	
	public IFluidState getFluidState(BlockState state) {
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	   }

}
