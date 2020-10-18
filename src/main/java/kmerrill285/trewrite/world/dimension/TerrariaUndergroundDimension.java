package kmerrill285.trewrite.world.dimension;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.biome.provider.UndergroundBiomeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TerrariaUndergroundDimension extends OverworldDimension {

	public TerrariaUndergroundDimension(World worldIn, DimensionType typeIn) {
		super(worldIn, typeIn);
	}
	
	public Vec3d currentColor = new Vec3d(0, 0, 0);
	public Vec3d newColor = new Vec3d(0, 0, 0);
	
	@Override

	public ChunkGenerator<?> createChunkGenerator()

	{
			OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
	        
//	        SingleBiomeProviderSettings biomeProviderSettings = new SingleBiomeProviderSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
	        biomeProviderSettings.setWorldInfo(world.getWorldInfo());

	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);	
//	        biomeProviderSettings.setBiome(BiomeT.LOWLANDS);
	        
	        return new TerrariaUndergroundChunkGenerator(world, new UndergroundBiomeProvider(biomeProviderSettings), overworldGenSettings);
	}
	
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		
		if (Minecraft.getInstance().player != null) {
			if (Minecraft.getInstance().player.posY < Util.caveLevel) {
				return currentColor.mul(1.5f, 1.5f, 1.5f);
			}
		}
		
		if (this.world.getDayTime() % 24000 > 15000 && this.world.getDayTime() < 22000) {
			return new Vec3d(0, 0, 0);
		}
		
		Vec3d mix = currentColor.add(super.getFogColor(celestialAngle, partialTicks)).mul(1.5f, 1.5f, 1.5f);
		return new Vec3d(mix.x / 2.0, mix.y / 2.0, mix.z / 2.0);
	}
	
	public boolean isNether() {
		return true;
	}
	
	public MusicType getMusicType() {
		
		return MusicType.CREATIVE;
	}
	
	public double getVoidFogFactor() {
		return 0.0;
	}
	
	public float getCloudHeight() {
		return 99999f;
	}
	
	public Vec3d getSkyColor(BlockPos cameraPos, float partialTicks) {
		
		
		World world = this.getWorld();
		int corruption = 0;
		int highlands = 0;
		int dark = 0;
		int desert = 0;
		int mushroom = 0;
		int jungle = 0;
		int snow = 0;
		int beach = 0;
		
		if (world != null) {
			
			
			
			for (int x = -15; x < 15; x++) {
				for (int y = -15; y < 15; y++) {
					for (int z = -15; z < 15; z++) {
						BlockPos pos2 = new BlockPos(cameraPos.getX() + x, cameraPos.getY() + y, cameraPos.getZ() + z);
						Block block = world.getBlockState(pos2).getBlock();
						if (block == BlocksT.HIGHLANDS_GRASS) {
							highlands++;
						}
						if (block == BlocksT.SNOW || block == BlocksT.ICE) {
							snow++;
						}
						if (block == BlocksT.PODZOL || block == BlocksT.DEEP_MUD || block == BlocksT.BOG_GRASS) {
							dark++;
						}
						if (block == BlocksT.CORRUPT_GRASS || block == BlocksT.EBONSTONE || block == BlocksT.EBONSAND || block == BlocksT.PURPLE_ICE) {
							corruption++;
						}
						if (block == BlocksT.JUNGLE_GRASS) {
							jungle++;
						}
						if (block == BlocksT.MUSHROOM_GRASS) {
							mushroom++;
						}
						if (block == BlocksT.SAND && new Vec3d(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ()).distanceTo(new Vec3d(0, cameraPos.getY(), 0)) < 4500) {
							desert++;
						} else {
							beach++;
						}
					}
				}
			}
		}
		
		newColor = super.getSkyColor(cameraPos, partialTicks);
		
		
		
		if (desert > 15) {
			newColor = new Vec3d(0.75f, 0.75f, 0.5f);	
		}
		
		if (highlands > 15) {
			newColor = new Vec3d(0.5f, 0.5f, 0.7f);
		}
		
		if (mushroom > 15) {
			newColor = new Vec3d(0.25f, 0.25f, 0.75f);
		}
		
		if (jungle > 15) {
			newColor = new Vec3d(0.5f, 0.5f, 0.25f);
		}
		
		if (dark > 15) {
			newColor = new Vec3d(0.5f * 0.5f, 0.5f * 0.5f, 0.25f * 0.5f);
		}
		
		
		
		
		if (snow > 15) {
			newColor = new Vec3d(1.0f, 1.0f, 1.0f);
			if (desert < 15)
			if (world.rainingStrength < 1.0f) {
				world.setRainStrength(world.rainingStrength + 0.01f);
			}
		} else {
			if (world.rainingStrength > 0.0f) {
				world.setRainStrength(world.rainingStrength - 0.01f);
			}
		}
		
		if (corruption > 15) {
			newColor = new Vec3d(0.25, 0, 0.25);
		}
		
		if (this.world.getDayTime() % 24000 > 15000 && this.world.getDayTime() < 22000) {
			newColor = new Vec3d(0, 0, 0);
		}
		
		newColor = newColor.mul(0.2f, 0.2f, 0.2f);

		
		BlockPos rand = cameraPos.add(world.rand.nextInt(100) - 50, -30, world.rand.nextInt(100) - 50);
		long e = world.getGameTime() % 5;
		
		if (Minecraft.getInstance().isGamePaused() == false)
		if (world.rand.nextInt(10) == 0 && e == 0)
		{
			for (int i = 0; i < 35; i++) {
				BlockPos pos2 = new BlockPos(rand.getX(), rand.getY() + i, rand.getZ());
				
				float speed = 0.25f;
				
				if (world.getBlockState(new BlockPos(pos2.getX(), pos2.getY() + 1, pos2.getZ())).getBlock() == BlocksT.AIR_BLOCK) {
					if (world.getBlockState(pos2).getBlock() == BlocksT.CORRUPT_GRASS ||
							world.getBlockState(pos2).getBlock() == BlocksT.EBONSTONE ||
							world.getBlockState(pos2).getBlock() == BlocksT.EBONSAND) {
						for (int a = 0; a < world.rand.nextInt(10); a++)
						world.addParticle(ParticleTypes.PORTAL, pos2.getX() + world.rand.nextDouble() - 0.5f, pos2.up().getY(), pos2.getZ() + world.rand.nextDouble() - 0.5f, 0, 0.1f, 0);
					}
					
					if (world.getBlockState(pos2).getBlock() == BlocksT.SAND||
							world.getBlockState(pos2).getBlock() == BlocksT.RED_SAND||
							world.getBlockState(pos2).getBlock() == BlocksT.EBONSAND) {
						for (int a = 0; a < world.rand.nextInt(10); a++) {
							world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos2.getX(), pos2.up().getY(), pos2.getZ(), (world.rand.nextDouble() - 0.5f) * speed, 0.01f, (world.rand.nextDouble()) * speed + ((double)a / 10.0) * speed);
						}
					}
				}
				
				
			}
		}
		
		double mul = 0.02f;
		
		
		
		currentColor = new Vec3d(currentColor.x + (newColor.x - currentColor.x) * mul,
				currentColor.y + (newColor.y - currentColor.y) * mul,
				currentColor.z + (newColor.z - currentColor.z) * mul);
		
		
		return currentColor;
		
	}

}
