package kmerrill285.trewrite.world.biome.features;

import kmerrill285.trewrite.world.biome.features.arctic.ArcticShrubFeature;
import kmerrill285.trewrite.world.biome.features.arctic.FreezeWaterTopFeature;
import kmerrill285.trewrite.world.biome.features.conifer.EvergreenTreeFeature;
import kmerrill285.trewrite.world.biome.features.corruption.CorruptionSpireFeature;
import kmerrill285.trewrite.world.biome.features.icespikes.IceSpikesFeature;
import kmerrill285.trewrite.world.biome.features.mesa.MesaShrubFeature;
import kmerrill285.trewrite.world.biome.features.mushroom.MassiveMushroomFeature;
import kmerrill285.trewrite.world.biome.features.purity.LivingwoodForestTreeFeature;
import kmerrill285.trewrite.world.biome.features.purity.LivingwoodTreeFeature;
import kmerrill285.trewrite.world.biome.features.purity.ShrubFeature;
import kmerrill285.trewrite.world.biome.features.purity.ShrublandTreeFeature;
import kmerrill285.trewrite.world.biome.features.savanna.BaobabTreeFeature;
import kmerrill285.trewrite.world.biome.features.savanna.JackalberryTreeFeature;
import kmerrill285.trewrite.world.biome.features.savanna.SavannaShrubFeature;
import kmerrill285.trewrite.world.biome.features.savanna.TermiteMoundFeature;
import kmerrill285.trewrite.world.biome.features.taiga.SpruceTreeFeature;
import kmerrill285.trewrite.world.biome.features.taiga.TaigaRocksFeature;
import kmerrill285.trewrite.world.biome.features.underworld.StalagmiteFeature;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class TerrariaFeatures {
	public static TreeFeature TREES = new TreeFeature(NoFeatureConfig::deserialize);
	public static ForestTreeFeature FOREST_TREES = new ForestTreeFeature(NoFeatureConfig::deserialize);
	public static ShrubFeature FOREST_SHRUBS = new ShrubFeature(NoFeatureConfig::deserialize);
	public static ArcticShrubFeature ARCTIC_SHRUBS = new ArcticShrubFeature(NoFeatureConfig::deserialize);
	public static SpruceTreeFeature SPRUCE_TREES = new SpruceTreeFeature(NoFeatureConfig::deserialize);
	public static TaigaRocksFeature TAIGA_ROCKS = new TaigaRocksFeature(NoFeatureConfig::deserialize);
	public static ShrublandTreeFeature SHRUBLAND_TREES = new ShrublandTreeFeature(NoFeatureConfig::deserialize);
	public static MassiveMushroomFeature MASSIVE_MUSHROOM = new MassiveMushroomFeature(NoFeatureConfig::deserialize);
	public static PalmTreeFeature PALM_TREES = new PalmTreeFeature(NoFeatureConfig::deserialize);

	public static JackalberryTreeFeature JACKALBERRY_TREE = new JackalberryTreeFeature(NoFeatureConfig::deserialize);
	public static TermiteMoundFeature TERMITE_MOUND = new TermiteMoundFeature(NoFeatureConfig::deserialize);
	public static SavannaShrubFeature SAVANNA_SHRUB = new SavannaShrubFeature(NoFeatureConfig::deserialize);
	public static BaobabTreeFeature BAOBAB_TREE = new BaobabTreeFeature(NoFeatureConfig::deserialize);
	public static LivingwoodTreeFeature LIVINGWOOD_TREE = new LivingwoodTreeFeature(NoFeatureConfig::deserialize);
	public static LivingwoodForestTreeFeature LIVINGWOOD_FOREST_TREE = new LivingwoodForestTreeFeature(NoFeatureConfig::deserialize);
	public static EvergreenTreeFeature EVERGREEN_TREE = new EvergreenTreeFeature(NoFeatureConfig::deserialize);

	public static MesaShrubFeature MESA_SHRUB = new MesaShrubFeature(NoFeatureConfig::deserialize);
	public static IceSpikesFeature ICE_SPIKES = new IceSpikesFeature(NoFeatureConfig::deserialize);

	public static TerrariaOreFeature ORES = new TerrariaOreFeature(TerrariaOreFeatureConfig::deserialize);
	public static CorruptionSpireFeature CORRUPTION_SPIRE = new CorruptionSpireFeature(NoFeatureConfig::deserialize);
	public static LifeCrystalFeature LIFE_CRYSTAL = new LifeCrystalFeature(NoFeatureConfig::deserialize);
	public static StalagmiteFeature STALAGMITES = new StalagmiteFeature(NoFeatureConfig::deserialize);
	public static PotFeature POTS = new PotFeature(NoFeatureConfig::deserialize);
	public static LootBlockFeature LOOT_BLOCKS = new LootBlockFeature(NoFeatureConfig::deserialize);
	public static HellforgeFeature HELLFORGE = new HellforgeFeature(NoFeatureConfig::deserialize);
	
	public static final WorldCarver<ProbabilityConfig> CAVE = new TerrariaCaveCarver(ProbabilityConfig::deserialize, 256);
	public static final Feature FREEZE_TOP_LAYER = new FreezeWaterTopFeature(NoFeatureConfig::deserialize);
	
	public static final Feature DUNGEON = new DungeonFeature(NoFeatureConfig::deserialize);
	public static final Feature RUINED_HOUSE = new RuinedHouseFeature(NoFeatureConfig::deserialize);
	public static final Feature SKY_ISLAND = new SkyIsandFeature(NoFeatureConfig::deserialize);
	public static final Feature UNDERGROUND_HOUSE = new UndergroundHouseFeature(NoFeatureConfig::deserialize);

}
