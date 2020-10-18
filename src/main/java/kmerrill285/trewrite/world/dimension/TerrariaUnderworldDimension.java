package kmerrill285.trewrite.world.dimension;

import kmerrill285.trewrite.world.biome.provider.UnderworldBiomeProvider;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TerrariaUnderworldDimension extends OverworldDimension {

	public TerrariaUnderworldDimension(World worldIn, DimensionType typeIn) {
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
	        
	        return new TerrariaUnderworldChunkGenerator(world, new UnderworldBiomeProvider(biomeProviderSettings), overworldGenSettings);
	}
	
	public float getCloudHeight() {
		return 99999f;
	}
	
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return new Vec3d(0.2, 0, 0);
	}
	
	public boolean isNether() {
		return true;
	}
	
	public MusicType getMusicType() {
		
		return MusicType.NETHER;
	}
	
	public double getVoidFogFactor() {
		return 0;
	}
	
	public Vec3d getSkyColor(BlockPos cameraPos, float partialTicks) {
		
		
		World world = this.getWorld();
		
		
		currentColor = new Vec3d(0, 0, 0);
		
		currentColor = new Vec3d(0.3, 0, 0);

		return currentColor;
		
	}

}
