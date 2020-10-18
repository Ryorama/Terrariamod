package kmerrill285.trewrite;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.config.TerrariaModConfig;
import kmerrill285.trewrite.core.commands.CommandsT;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.server.SPacketSendAccessories;
import kmerrill285.trewrite.core.sounds.TMusicTicker;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.RemoveMaxHealthLimit;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import kmerrill285.trewrite.events.EntityEvents;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.Armor;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.accessories.Accessory;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.ReflectionUtil;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.DimensionTypeT;
import kmerrill285.trewrite.world.EntitySpawner;
import kmerrill285.trewrite.world.TerrariaDimension;
import kmerrill285.trewrite.world.TerrariaWorldType;
import kmerrill285.trewrite.world.WorldStateHolder;
import kmerrill285.trewrite.world.dimension.Dimensions;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.PacketDistributor;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("trewrite")
public class Trewrite
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean DEBUG = false;
    
   
    
    public Trewrite() {
    	
    	
//    	FeatureScript.load();
    	try {
    		Field f = Chunk.class.getDeclaredField("field_76634_f");
    	} catch (Exception e) {
    		DEBUG = true;
    		System.out.println("DEBUG!");
    	}
    	
    	TerrariaModConfig.loadConifg();
    	    	
    	new StackedDimensions();
    	
    	new ItemModifier();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(OverlayEvents::handleOverlayEvent);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(EntityEvents.class);
//        MinecraftForge.EVENT_BUS.register(ScoreboardEvents.class);
        MinecraftForge.EVENT_BUS.register(WorldEvents.class);
        NetworkHandler.register();
        
        MinecraftForge.EVENT_BUS.addListener(Trewrite::onWorldTick);
        MinecraftForge.EVENT_BUS.addListener(Trewrite::onBlockUpdate);

        try {
        	Field f = DimensionType.class.getDeclaredField(DEBUG ? "OVERWORLD" : "field_223227_a_");
        	Util.makeFieldAccessible(f);
        	
        	DimensionType OVERWORLD = new DimensionTypeT(1, "", "", TerrariaDimension::new, true);
        	DimensionType type = Registry.register(Registry.DIMENSION_TYPE, OVERWORLD.getId(), "custom_overworld", OVERWORLD);
        	f.set(null, type);
        	//return Registry.register(Registry.DIMENSION_TYPE, type.id, key, type);

        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        WorldType type = WorldType.WORLD_TYPES[0];
        WorldType[] types2 = new WorldType[WorldType.WORLD_TYPES.length + 1];
        for (int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
        	types2[i + 1] = WorldType.WORLD_TYPES[i];
        }
        types2[0] = new TerrariaWorldType("Terraria-Style");
        WorldType.WORLD_TYPES = types2;
    }
    
    
    @SubscribeEvent
	public static void onBlockUpdate(NeighborNotifyEvent e) {
    	
    	
    	
    	if (e.getState().getBlock() == Blocks.STONE || e.getState().getBlock() == Blocks.COBBLESTONE) {
    		e.getWorld().setBlockState(e.getPos(), BlocksT.STONE_BLOCK.getDefaultState(), 0);
    	}
    	if (e.getState().getBlock() == Blocks.OBSIDIAN) {
    		e.getWorld().setBlockState(e.getPos(), BlocksT.OBSIDIAN.getDefaultState(), 0);
    	}
    	if (e.getState().getBlock() == Blocks.ICE) {
    		e.getWorld().setBlockState(e.getPos(), Blocks.WATER.getDefaultState(), 0);
    	}
    }
    
    public static int ticks = 0;
    public static boolean spawningEye = false;
    public static boolean spawningDestroyer = false;
    public static boolean oncePerDay = false;
    public static boolean meteoriteAttempt = false;
    
    private static boolean once = false;
    
	public static void onWorldTick(WorldTickEvent event)
	{
	
		if (!once) {
			if (Util.stacked_dimensions == true) {
				ResourceLocation overworld = new ResourceLocation("minecraft:custom_overworld");
				DimensionConfigs.configs.clear();
				DimensionConfigs.configs.add(new DimensionConfiguration(overworld, Dimensions.skyLocation,Dimensions.undergroundLocation, 0, 127));
				DimensionConfigs.configs.add(new DimensionConfiguration(Dimensions.skyLocation, null,Dimensions.undergroundLocation, 0, 127));
				DimensionConfigs.configs.add(new DimensionConfiguration(Dimensions.undergroundLocation, overworld, Dimensions.underworldLocation, 0, 127));
				DimensionConfigs.configs.add(new DimensionConfiguration(Dimensions.underworldLocation, Dimensions.undergroundLocation, null, 0, 127));
				once = true;
			}
			once = true;
		}

		
		StackedDimensions.onWorldTick(event);
		
//		if (DimensionManager.getWorld(event.world.getServer(), t, true, true) == null) {
//			DimensionManager.initWorld(event.world.getServer(), t);
//		}
		
		World world = event.world;
		
		WorldStateHolder holder = WorldStateHolder.get(world);
		holder.update(world, world.getDimension().getType());
		
		if (world.getDayTime() % 24000 >= 18000) {
			if (holder.meteoriteSpawn == true)
			if (!meteoriteAttempt) {
				meteoriteAttempt = true;
				int x = (world.getRandom().nextInt(2) * 2 - 1) * (world.getRandom().nextInt(3000) + 1000);
				int z = (world.getRandom().nextInt(2) * 2 - 1) * (world.getRandom().nextInt(3000) + 1000);
				
		    	world.getServer().getPlayerList().sendMessage(new StringTextComponent("A meteorite has landed around [" + (x + world.getRandom().nextInt(200) - 100) + ", " + (z + world.getRandom().nextInt(200) - 100) + "]!").applyTextStyles(TextFormatting.GREEN, TextFormatting.BOLD));
		    	
		    	holder.meteoritePositions.add(new BlockPos(x, 0, z));
		    	holder.meteoriteSpawn = false;
			}
		} else {
			meteoriteAttempt = false;
		}
		
		world.getGameRules().get(GameRules.DO_WEATHER_CYCLE).set(true, world.getServer());

		for (PlayerEntity player : world.getPlayers()) {
			
			for (BlockPos pos : holder.meteoritePositions) {
//				System.out.println(pos.getX() + ", " + pos.getZ());
				if (player.getPositionVec().distanceTo(new Vec3d(pos.getX(), player.getPositionVec().y, pos.getZ())) <= 100) {
					BlockPos p = world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
					BlockPos.MutableBlockPos mp = new BlockPos.MutableBlockPos(p);
					if (p != null) {
						if (world.isAreaLoaded(p, 40)) {
							for (int x = -20; x < 20; x++) {
								for (int y = -20; y < 20; y++) {
									for (int z = -20; z < 20; z++) {
										mp.setPos(p.getX() + x, p.getY() + y, p.getZ() + z);
										if (world.getBlockState(mp).isSolid() && world.getBlockState(mp) != Blocks.BEDROCK.getDefaultState()) {
											Block b = null;
											if (mp.withinDistance(p, 15)) {
												b = BlocksT.METEORITE;
											}
											if (mp.withinDistance(p, 10)) {
												b = Blocks.AIR;
											}
											if (b != null) {
												world.setBlockState(mp, b.getDefaultState());
											}
										}
									}
								}
							}
							holder.meteoritePositions.remove(pos);
						}
					}
					break;
				}
			}
			
			if (event.phase == TickEvent.Phase.END)
			if (event.type == TickEvent.Type.WORLD)
			if (!player.world.isRemote) {
				if (player.getServer() != null) {
					Scoreboard scoreboard = player.getWorldScoreboard();
					
					ScoreboardEvents.handleScoreboard(player, world, scoreboard);
				}
			}
			
			InventoryTerraria inventory = WorldEvents.getOrLoadInventory(player);

			int defense = 0;
			
			
			if (inventory != null) {
				
				for (int i = 0; i < 3; i++) {
					InventorySlot slot = inventory.armor[i];
					if (slot.stack != null) {
						if (slot.stack.item instanceof Armor) {
							Armor armor = (Armor)slot.stack.item;
							defense += armor.getDefense(inventory.armor);
						}
					}
				}
				
				for (int i = 0; i < inventory.accessory.length; i++) {
					InventorySlot slot = inventory.accessory[i];
					if (slot.stack != null) {
						if (slot.stack.item instanceof Accessory) {
							Accessory a = (Accessory)slot.stack.item;
							a.accessoryTick(player);
							
							ItemModifier modifier = ItemModifier.getModifier(slot.stack.modifier);
							if (modifier != null) {
								if (modifier.defense > 0) {
									defense += modifier.defense;
								}
							}
						}
					}
				}
			}
			
			float maxHealth = player.getMaxHealth();
			if (WorldStateHolder.get(world).demonAltarsDestroyed >= 1 && WorldStateHolder.get(world).hardmode == true) {
				if (!world.isRemote) {
					if (world.getDayTime() % 24000 >= 11000) {
						if (oncePerDay == false) {
							oncePerDay = true;
							if (world.rand.nextInt(10) == 0) {
								spawningDestroyer = true;
						    	world.getServer().getPlayerList().sendMessage(new StringTextComponent("You feel vibarations from deep below.").applyTextStyles(TextFormatting.BLUE, TextFormatting.BOLD));
							}
						}
					}
				}
			}
			
			if (maxHealth >= 200 && defense >= 2) {
				if (!world.isRemote) {
					if (world.getDayTime() % 24000 >= 11000) {
						if (oncePerDay == false) {
							oncePerDay = true;
							if (world.rand.nextInt(10) == 0) {
								spawningEye = true;
						    	world.getServer().getPlayerList().sendMessage(new StringTextComponent("You feel an evil presence watching you.").applyTextStyles(TextFormatting.BLUE, TextFormatting.BOLD));
							}
						}
					}
				}
			}
		}
		
		if (!world.isRemote) {
			if (world.getDayTime() % 24000 <= 1000) {
				oncePerDay = false;
			}
			if (spawningEye == true && world.getDayTime() % 24000 > 17500) {
				if (world.getPlayers().size() > 0) {
					PlayerEntity player = world.getPlayers().get(world.rand.nextInt(world.getPlayers().size()));
					float posX = 0, posY = world.rand.nextInt(20) - 10, posZ = 0;
		    		float rad = 20;
		    		
		    		float rotation = world.rand.nextInt(360);
		    		posX = (float) (Math.cos(Math.toDegrees(rotation)) * rad);
		    		posZ = (float) (Math.sin(Math.toDegrees(rotation)) * rad);
		    		
		    		EntityEyeOfCthulhu eye = EntitiesT.EYE_OF_CTHULHU.create(world, null, null, null, player.getPosition(), SpawnReason.EVENT, false, false);
					eye.setPosition(player.getPosition().getX() + posX, player.getPosition().getY() + posY, player.getPosition().getZ() + posZ);
					world.addEntity(eye);
					spawningEye = false;
				}
			}
			
			if (spawningDestroyer == true && world.getDayTime() % 24000 > 17500) {
				if (world.getPlayers().size() > 0) {
					PlayerEntity player = world.getPlayers().get(world.rand.nextInt(world.getPlayers().size()));
					float posX = 0, posY = world.rand.nextInt(20) - 10, posZ = 0;
		    		float rad = 20;
		    		
		    		float rotation = world.rand.nextInt(360);
		    		posX = (float) (Math.cos(Math.toDegrees(rotation)) * rad);
		    		posZ = (float) (Math.sin(Math.toDegrees(rotation)) * rad);
		    		
		    		EntityDestroyerHead eye = EntitiesT.DESTROYER_HEAD.create(world, null, null, null, player.getPosition(), SpawnReason.EVENT, false, false);
					eye.setPosition(player.getPosition().getX() + posX, player.getPosition().getY() + posY, player.getPosition().getZ() + posZ);
					world.addEntity(eye);
					spawningDestroyer = false;
				}
			}
			
			Trewrite.ticks++;
			if (Trewrite.ticks % 20 == 0) {
				
				new Thread() {
					public void run() {
						try {
							for (PlayerEntity player : event.world.getPlayers()) {
								SPacketSendAccessories packet = new SPacketSendAccessories(player);
								if (event.world instanceof ServerWorld)
								for (ServerPlayerEntity send : ((ServerWorld)event.world).getPlayers())
		    	    	 			NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> send), packet);
								
							}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
				
			}
			
			
			Util.minSpawnDistance = 15.0;
			Util.entitySpawnRate = 1.0/25.0;
			
			if (world.rand.nextInt(100) <= 10)
			if (world.rand.nextDouble() <= Util.starChance / 3.0) {
				if (world.getPlayers().size() > 0) {
					PlayerEntity player = world.getPlayers().get(world.rand.nextInt(world.getPlayers().size()));
					double x = player.posX + world.rand.nextInt(80) - 40, y = 255, z = player.posZ + world.rand.nextInt(80) - 40;
					
					EntityItemT item = EntitiesT.ITEM.create(world, null, null, null, new BlockPos(x, y, z), SpawnReason.EVENT, false, false);
					item.setItem(new ItemStackT(ItemsT.FALLEN_STAR, 1, null));
//					EntityItemT item = new EntityItemT(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), stack);
					item.pickupDelay = 0;
					world.addEntity(item);
					item.hitGround = false;
					
					
				}
			}
			
			

			
				
			
			A:
				if (world.rand.nextInt(100) <= 10)
			if (world.getPlayers().size() > 0) {
				PlayerEntity player = world.getPlayers().get(world.rand.nextInt(world.getPlayers().size()));
				int battle = ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.BATTLE).getScorePoints();
				if (world.rand.nextDouble() <= Util.entitySpawnRate * (battle > 0 ? 2 : 1)) {
				if (ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.CALMING).getScorePoints() > 0)
					if (world.rand.nextBoolean()) break A;
				double x = player.posX + world.rand.nextInt(80) - 40, y = player.posY + world.rand.nextInt(80) - 40, z = player.posZ + world.rand.nextInt(80) - 40;
				
				for (PlayerEntity p2 : world.getPlayers()) {
						if (p2.getPositionVec().distanceTo(new Vec3d(x, y, z)) >= Util.minSpawnDistance) {
							new Thread () {
								public void run() {
									EntitySpawner.spawnEntities(player, x, y, z);
								}
							}.start();
							
							break;
						}
					}
				}
			}
		}
	}

    private void setup(final FMLCommonSetupEvent event)
    {
    	System.out.println("TREWRITE MOD SETUP");
//    	MinecraftForge.EVENT_BUS.register(new EntityEvents());
//    	MinecraftForge.EVENT_BUS.register(new OverlayEvents());
//    	MinecraftForge.EVENT_BUS.register(new ScoreboardEvents());
//    	MinecraftForge.EVENT_BUS.register(new WorldEvents());
        // some preinit code
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    	
    	ReflectionUtil.getFieldByValue(SharedMonsterAttributes.class, null, SharedMonsterAttributes.MAX_HEALTH);
	        for (Field field : SharedMonsterAttributes.class.getDeclaredFields()) {
	          if (field.getType().isAssignableFrom(IAttribute.class)) {
	            field.setAccessible(true);
	            try {
	              if (field.get((Object)null) == SharedMonsterAttributes.MAX_HEALTH) {
	                ReflectionUtil.setStaticFinalField(field, RemoveMaxHealthLimit.MAX_HEALTH);
	              } 
	            } catch (IllegalAccessException err) {}
	          }
	    	
	    	try {
				Field musicTicker = Minecraft.class.getDeclaredField(DEBUG ? "musicTicker" : "field_147126_aw");
				musicTicker.setAccessible(true);
				musicTicker.set(Minecraft.getInstance(), new TMusicTicker(Minecraft.getInstance()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    }
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        CommandsT.register(event.getCommandDispatcher());
    }
    
    
}