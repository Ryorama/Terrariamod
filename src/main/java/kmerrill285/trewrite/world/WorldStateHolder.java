package kmerrill285.trewrite.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kmerrill285.trewrite.blocks.BlockAirT;
import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.inventory.InventoryChestTerraria;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.world.dimension.DimensionRegistry;
import kmerrill285.trewrite.world.dimension.Dimensions;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;

public class WorldStateHolder extends WorldSavedData {


	public enum WIND_DIR {
		NONE,
		NORTH,
		SOUTH,
		EAST,
		WEST,
	}

	private static final WorldStateHolder CLIENT_DUMMY = new WorldStateHolder();

	public IWorld world;

	public boolean slimeRain = false;
	public int mechBossesDowned = 0;
	public boolean eyeDefeated = false;
	public int shadowOrbsMined = 0;
	public boolean eaterOfWorldsDefeated = false;
	public boolean hardmode = false;
	public int demonAltarsDestroyed = 0;
	public boolean bloodmoon = false;
	public boolean solarEclipse = false;
	public boolean skeletronDefeated = false;
	public boolean twinsDefeated = false;
	public boolean destroyerDefeated = false;
	public boolean skeletronPrimeDefeated = false;
	public boolean planteraDefeated = false;
	public boolean golemDefeated = false;
	public boolean cultistDefeated = false;
	public boolean dukeFishronDefeated = false;
	public boolean solarPillarDefeated = false;
	public boolean vortexPillarDefeated = false;
	public boolean nebulaPillarDefeated = false;
	public boolean stardustPillarDefeated = false;
	public int solarEnemiesDefeated = 0;
	public int vortexEnemiesDefeated = 0;
	public int nebulaEnemiesDefeated = 0;
	public int stardustEnemiesDefeated = 0;
	public int invasionEnemiesDefeated = 0;
	public int creepersDefeated = 0;
	public boolean meteoriteSpawn = false;
	public boolean firstJoin = false;
	public Vec3d wind = new Vec3d(0, 0, 0);
	public Enum windDirection = WIND_DIR.NONE;
	public boolean isMonsoon = false;

	public HashMap<String, BlockPos> spawnPositions = new HashMap<String, BlockPos>();
	public HashMap<BlockPos, Integer> lights = new HashMap<BlockPos, Integer>();
	public HashMap<BlockPos, Integer> lights_sky = new HashMap<BlockPos, Integer>();
	public HashMap<BlockPos, Integer> lights_underground = new HashMap<BlockPos, Integer>();
	public HashMap<BlockPos, Integer> lights_underworld = new HashMap<BlockPos, Integer>();

	public ArrayList<BlockPos> meteoritePositions = new ArrayList<BlockPos>();

	public HashMap<String, InventoryChestTerraria> chests = new HashMap<String, InventoryChestTerraria>();

	public boolean EaterOfWorldsAlive;
	public int EaterOfWorldsSegments;

	public HashMap<String, InventoryTerraria> inventories = new HashMap<String, InventoryTerraria>();

	public class WorldState {

	}
	public WorldStateHolder() {
		super("trewrite:worldstate");
	}

	public WorldStateHolder(String name) {
		super(name);
	}

	// get the data from the world saved data manager, instantiating it first if it doesn't exist

	public static WorldStateHolder get(IWorld world)
	{
		if (!(world instanceof ServerWorld))
		{
			return CLIENT_DUMMY;
		}

		ServerWorld overworld = ((ServerWorld)world).getServer().getWorld(DimensionType.OVERWORLD);

		DimensionSavedDataManager storage = overworld.getSavedData();
		WorldStateHolder stateHolder = storage.getOrCreate(WorldStateHolder::new, "trewrite:worldstate");
		stateHolder.world = world;

		return stateHolder;
	}

	@Override
	public void read(CompoundNBT nbt) {
		solarEclipse = nbt.getBoolean("solarEclipse");
		mechBossesDowned = nbt.getInt("mechBossesDowned");
		creepersDefeated = nbt.getInt("creepersDefeated");
		eyeDefeated = nbt.getBoolean("eyeDefeated");
		shadowOrbsMined = nbt.getInt("shadowOrbsMined");
		hardmode = nbt.getBoolean("hardmode");
		demonAltarsDestroyed = nbt.getInt("demonAltarsDestroyed");
		bloodmoon = nbt.getBoolean("bloodmoon");
		skeletronDefeated = nbt.getBoolean("skeletronDefeated");
		twinsDefeated = nbt.getBoolean("twinsDefeated");
		destroyerDefeated = nbt.getBoolean("destroyerDefeated");
		skeletronPrimeDefeated = nbt.getBoolean("skeletronPrimeDefeated");
		planteraDefeated = nbt.getBoolean("planteraDefeated");
		golemDefeated = nbt.getBoolean("golemDefeated");
		cultistDefeated = nbt.getBoolean("cultistDefeated");
		solarPillarDefeated = nbt.getBoolean("solarPillarDefeated");
		vortexPillarDefeated = nbt.getBoolean("vortexPillarDefeated");
		nebulaPillarDefeated = nbt.getBoolean("nebulaPillarDefeated");
		stardustPillarDefeated = nbt.getBoolean("stardustPillarDefeated");
		solarEnemiesDefeated = nbt.getInt("solarEnemiesDefeated");
		vortexEnemiesDefeated = nbt.getInt("vortexEnemiesDefeated");
		nebulaEnemiesDefeated = nbt.getInt("nebulaEnemiesDefeated");
		stardustEnemiesDefeated = nbt.getInt("stardustEnemiesDefeated");
		invasionEnemiesDefeated = nbt.getInt("invasionEnemiesDefeated");
		eaterOfWorldsDefeated = nbt.getBoolean("eaterOfWorldsDefeated");
		meteoriteSpawn = nbt.getBoolean("meteoriteSpawn");
		firstJoin = nbt.getBoolean("firstJoin");
		wind = new Vec3d(nbt.getDouble("windX"), 0, nbt.getDouble("windZ"));
		slimeRain = nbt.getBoolean("slimeRain");

		int size = nbt.getInt("sposLength");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("spawnpos["+i+"]").trim();
			String[] data = s.split(",");
			String name = data[0];
			if (data[1].contentEquals("null")) {
				spawnPositions.put(name, null);
			} else {
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				spawnPositions.put(name, new BlockPos(x, y, z));
			}

		}

		size = nbt.getInt("inventories");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("inventories["+i+"]").trim();
			String[] data = s.split("!");
			if (data[1].contentEquals("null")) continue;
			if (data.length < 2) continue;
			String name = data[0];
			InventoryTerraria inventory = new InventoryTerraria();
			inventory.loadFromString(data[1]);
			System.out.println("NAME: " + name);
			inventories.put(name, inventory);

		}
		size = nbt.getInt("chests");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("chests["+i+"]").trim();
			String[] data = s.split("!");
			if (data[1].contentEquals("null")) continue;
			if (data.length < 2) continue;
			String name = data[0];
			InventoryChestTerraria chest = new InventoryChestTerraria();
			chest.loadFromString(data[1]);
			chests.put(name, chest);
		}
		size = nbt.getInt("lightsLength");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("lights["+i+"]".trim());
			String[] data = s.split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int z = Integer.parseInt(data[2]);
			int l = Integer.parseInt(data[3]);
			lights.put(new BlockPos(x, y, z), l);
		}
		size = nbt.getInt("lightsLength2");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("lights2["+i+"]".trim());
			String[] data = s.split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int z = Integer.parseInt(data[2]);
			int l = Integer.parseInt(data[3]);
			lights_sky.put(new BlockPos(x, y, z), l);
		}
		size = nbt.getInt("lightsLength3");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("lights3["+i+"]".trim());
			String[] data = s.split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int z = Integer.parseInt(data[2]);
			int l = Integer.parseInt(data[3]);
			lights_underground.put(new BlockPos(x, y, z), l);
		}
		size = nbt.getInt("lightsLength4");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("lights4["+i+"]".trim());
			String[] data = s.split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int z = Integer.parseInt(data[2]);
			int l = Integer.parseInt(data[3]);
			lights_underworld.put(new BlockPos(x, y, z), l);
		}
		size = nbt.getInt("meteoriteAmount");
		for (int i = 0; i < size; i++) {
			String s = nbt.getString("meteorites["+i+"]".trim());
			String[] data = s.split(",");
			int x = Integer.parseInt(data[0]);
			int y = Integer.parseInt(data[1]);
			int z = Integer.parseInt(data[2]);
			meteoritePositions.add(new BlockPos(x, y, z));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("solarEclipse", solarEclipse);
		compound.putInt("mechBossesDowned", mechBossesDowned);
		compound.putInt("creepersDefeated", creepersDefeated);
		compound.putBoolean("firstJoin", firstJoin);
		compound.putInt("demonAltarsDestroyed", demonAltarsDestroyed);
		compound.putBoolean("bloodmoon", bloodmoon);
		compound.putBoolean("eyeDefeated", eyeDefeated);
		compound.putInt("shadowOrbsMined", shadowOrbsMined);
		compound.putBoolean("hardmode", hardmode);
		compound.putBoolean("skeletronDefeated", skeletronDefeated);
		compound.putBoolean("twinsDefeated", twinsDefeated);
		compound.putBoolean("destroyerDefeated", destroyerDefeated);
		compound.putBoolean("skeletronPrimeDefeated", skeletronPrimeDefeated);
		compound.putBoolean("planteraDefeated", planteraDefeated);
		compound.putBoolean("golemDefeated", golemDefeated);
		compound.putBoolean("cultistDefeated", cultistDefeated);
		compound.putBoolean("solarPillarDefeated", solarPillarDefeated);
		compound.putBoolean("vortexPillarDefeated", vortexPillarDefeated);
		compound.putBoolean("nebulaPillarDefeated", nebulaPillarDefeated);
		compound.putBoolean("stardustPillarDefeated", stardustPillarDefeated);
		compound.putInt("solarEnemiesDefeated", solarEnemiesDefeated);
		compound.putInt("vortexEnemiesDefeated", vortexEnemiesDefeated);
		compound.putInt("nebulaEnemiesDefeated", nebulaEnemiesDefeated);
		compound.putInt("stardustEnemiesDefeated", stardustEnemiesDefeated);
		compound.putInt("invasionEnemiesDefeated", invasionEnemiesDefeated);
		compound.putBoolean("eaterOfWorldsDefeated", eaterOfWorldsDefeated);
		compound.putBoolean("meteoriteSpawn", meteoriteSpawn);
		compound.putInt("sposLength", spawnPositions.size());
		compound.putInt("inventories", inventories.size());
		compound.putInt("chests", chests.size());
		compound.putDouble("windX", wind.getX());
		compound.putDouble("windZ", wind.getZ());
		compound.putBoolean("slimeRain", slimeRain);

		int i = 0;

		for (String p : inventories.keySet()) {
			String savedata = inventories.get(p).getDataForSave();
			if (savedata == null) {
				compound.putString("inventories["+i+"]", p+"!null");
			} else {
				compound.putString("inventories["+i+"]", p+"!"+savedata);
			}
			i++;
		}
		i = 0;
		for (String p : chests.keySet()) {
			String savedata = chests.get(p).getDataForSave();
			if (savedata == null) {
				compound.putString("chests["+i+"]", p+"!null");
			} else {
				compound.putString("chests["+i+"]", p+"!"+savedata);
			}
			i++;
		}
		i = 0;
		for (String p : spawnPositions.keySet()) {
			BlockPos pos = spawnPositions.get(p);
			if (pos == null) {
				compound.putString("spawnpos["+i+"]", p+",null");
			} else {
				compound.putString("spawnpos["+i+"]", p+","+pos.getX()+","+pos.getY()+","+pos.getZ());
			}
			i++;
		}
		compound.putInt("lightsLength", lights.size());
		i = 0;
		for (BlockPos p : lights.keySet()) {
			int l = lights.get(p);
			compound.putString("lights["+i+"]", p.getX()+","+p.getY()+","+p.getZ()+","+l);
			i++;
		}
		compound.putInt("lightsLength2", lights_sky.size());
		i = 0;
		for (BlockPos p : lights_sky.keySet()) {
			int l = lights_sky.get(p);
			compound.putString("lights2["+i+"]", p.getX()+","+p.getY()+","+p.getZ()+","+l);
			i++;
		}
		compound.putInt("lightsLength3", lights_underground.size());
		i = 0;
		for (BlockPos p : lights_underground.keySet()) {
			int l = lights_underground.get(p);
			compound.putString("lights3["+i+"]", p.getX()+","+p.getY()+","+p.getZ()+","+l);
			i++;
		}
		compound.putInt("lightsLength4", lights_underworld.size());
		i = 0;
		for (BlockPos p : lights_underworld.keySet()) {
			int l = lights_underworld.get(p);
			compound.putString("lights4["+i+"]", p.getX()+","+p.getY()+","+p.getZ()+","+l);
			i++;
		}

		compound.putInt("meteoriteAmount", meteoritePositions.size());
		for (i = 0; i < meteoritePositions.size(); i++) {
			BlockPos pos = meteoritePositions.get(i);
			compound.putString("meteorites["+i+"]", pos.getX()+","+pos.getY()+","+pos.getZ());
		}

		return compound;
	}

	public void setLight(BlockPos pos, int light, DimensionType type) {
		DimensionType sky = DimensionManager.registerOrGetDimension(Dimensions.skyLocation, DimensionRegistry.skyDimension, null, true);
		DimensionType underground = DimensionManager.registerOrGetDimension(Dimensions.undergroundLocation, DimensionRegistry.undergroundDimension, null, true);
		DimensionType underworld = DimensionManager.registerOrGetDimension(Dimensions.underworldLocation, DimensionRegistry.underworldDimension, null, true);

		if (type == sky) {
			lights_sky.put(pos, light);
		}
		else if (type == underground) {
			lights_underground.put(pos, light);
		}
		else if (type == underworld) {
			lights_underworld.put(pos, light);
		} else {
			lights.put(pos, light);
		}
	}

	public void update(World world, DimensionType type) {
		int updated = 0;
		HashMap<BlockPos, Integer> lights = null;
		if (world.getWorld().getGameTime() % 5 == 0)
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				lights = this.lights;
				break;
			case 1:
				lights = lights_sky;
				break;
			case 2:
				lights = lights_underground;
				break;
			case 3:
				lights = lights_underworld;
				break;
			}
			for (BlockPos p : lights.keySet()) {
				updated++;
				if (updated > 100) {
					break;
				}
				lights.put(p, lights.get(p)-1);

				if (lights.get(p) <= 0) {
					if (world.getBlockState(p).getBlock() instanceof BlockT) {
						world.getWorld().setBlockState(p, world.getBlockState(p).with(BlockT.light, lights.get(p)));
					}
					if (world.getBlockState(p).getBlock() == BlocksT.AIR_BLOCK) {
						if (world.getBlockState(p).has(BlockAirT.WATERLOGGED)) {
							if (world.getBlockState(p).get(BlockAirT.WATERLOGGED).booleanValue()) {
								world.getWorld().setBlockState(p, Blocks.WATER.getDefaultState());
							}
						}
					}
					lights.remove(p);
					break;
				} else {
					if (world.getBlockState(p).getBlock() == Blocks.WATER && world.getBlockState(p).getFluidState().isSource()) {
						world.getWorld().setBlockState(p, BlocksT.AIR_BLOCK.getDefaultState().with(BlockT.light, lights.get(p)).with(BlockAirT.WATERLOGGED, true));
					}
					if (world.getBlockState(p).getBlock() instanceof AirBlock) {
						world.getWorld().setBlockState(p, BlocksT.AIR_BLOCK.getDefaultState().with(BlockT.light, lights.get(p)));
					} else if (world.getBlockState(p).getBlock() instanceof BlockT) {
						world.getWorld().setBlockState(p, world.getBlockState(p).with(BlockT.light, lights.get(p)));
					}
				}
			}

		}


		this.markDirty();
	}

	public Vec3d toWindVec3D(double y) {
		return new Vec3d(wind.x, y, wind.z);
	}

//	public long lastTick;
//	public void tick() {
//		World world = (World)this.world;
//		
//		this.markDirty();
//	}

}
