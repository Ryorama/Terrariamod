package kmerrill285.trewrite.entities;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.entities.monsters.EntityBat;
import kmerrill285.trewrite.entities.monsters.EntityBlackSlime;
import kmerrill285.trewrite.entities.monsters.EntityBlueSlime;
import kmerrill285.trewrite.entities.monsters.EntityCorruptSlime;
import kmerrill285.trewrite.entities.monsters.EntityCrimera;
import kmerrill285.trewrite.entities.monsters.EntityDemon;
import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import kmerrill285.trewrite.entities.monsters.EntityDrownedT;
import kmerrill285.trewrite.entities.monsters.EntityEaterOfSouls;
import kmerrill285.trewrite.entities.monsters.EntityFaceMonster;
import kmerrill285.trewrite.entities.monsters.EntityFlyingFish;
import kmerrill285.trewrite.entities.monsters.EntityHallowSlime;
import kmerrill285.trewrite.entities.monsters.EntityHarpy;
import kmerrill285.trewrite.entities.monsters.EntityHellbat;
import kmerrill285.trewrite.entities.monsters.EntityIceSlime;
import kmerrill285.trewrite.entities.monsters.EntityLavaSlime;
import kmerrill285.trewrite.entities.monsters.EntityPinky;
import kmerrill285.trewrite.entities.monsters.EntityPossessedArmor;
import kmerrill285.trewrite.entities.monsters.EntityRedSlime;
import kmerrill285.trewrite.entities.monsters.EntitySandSlime;
import kmerrill285.trewrite.entities.monsters.EntityToxicSludge;
import kmerrill285.trewrite.entities.monsters.EntityVoodooDemon;
import kmerrill285.trewrite.entities.monsters.EntityYellowSlime;
import kmerrill285.trewrite.entities.monsters.EntityZombieT;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowBody;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowHead;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEowTail;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.LunaticCultist;
import kmerrill285.trewrite.entities.monsters.bosses.Plantera;
import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityBrainOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityCreeper;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerBody;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityProbe;
import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemBody;
import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemHead;
import kmerrill285.trewrite.entities.monsters.bosses.moon_lord.MoonLord;
import kmerrill285.trewrite.entities.monsters.bosses.plantera.PlanteraSeed;
import kmerrill285.trewrite.entities.monsters.bosses.plantera.Spore;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronHead;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronLeftArm;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronLeftArm2;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronRightArm;
import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronRightArm2;
import kmerrill285.trewrite.entities.monsters.bosses.twins.Ratinizer;
import kmerrill285.trewrite.entities.monsters.bosses.twins.Spazmatism;
import kmerrill285.trewrite.entities.monsters.bosses.twins.TwinsBinding;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityLeechBody;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityLeechHead;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityLeechTail;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFlesh;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshEye;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshMouth;
import kmerrill285.trewrite.entities.monsters.bosses.wof.TheHungryEntity;
import kmerrill285.trewrite.entities.monsters.worms.EntityWormBody;
import kmerrill285.trewrite.entities.monsters.worms.EntityWormHead;
import kmerrill285.trewrite.entities.monsters.worms.EntityWormTail;
import kmerrill285.trewrite.entities.npc.EntityGuide;
import kmerrill285.trewrite.entities.npc.EntityMerchant;
import kmerrill285.trewrite.entities.npc.EntityOldMan;
import kmerrill285.trewrite.entities.passive.EntityBunnyT;
import kmerrill285.trewrite.entities.pillars.NebulaPillar;
import kmerrill285.trewrite.entities.pillars.SolarPillar;
import kmerrill285.trewrite.entities.pillars.StardustPillar;
import kmerrill285.trewrite.entities.pillars.VortexPillar;
import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import kmerrill285.trewrite.entities.projectiles.EntityBullet;
import kmerrill285.trewrite.entities.projectiles.EntityMagicProjectile;
import kmerrill285.trewrite.entities.projectiles.EntitySummoningImpFireball;
import kmerrill285.trewrite.entities.projectiles.EntityTekhairaProjectile;
import kmerrill285.trewrite.entities.projectiles.EntityThrowingT;
import kmerrill285.trewrite.entities.projectiles.EntityVileSpit;
import kmerrill285.trewrite.entities.projectiles.boomerangs.EntityEnchantedBoomerang;
import kmerrill285.trewrite.entities.projectiles.flails.EntityBallOHurt;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import kmerrill285.trewrite.entities.projectiles.magic_projectiles.VilethornProjectile;
import kmerrill285.trewrite.entities.summoning.EntitySummoningImp;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernBody;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernHead;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernTail;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntitiesT {
   public static EntityType<EntityItemT> ITEM;
   public static EntityType<EntityBlueSlime> BLUE_SLIME;
   public static EntityType<EntityRedSlime> RED_SLIME;
   public static EntityType<EntityBlackSlime> BLACK_SLIME;
   public static EntityType<EntityIceSlime> ICE_SLIME;
   public static EntityType<EntitySandSlime> SAND_SLIME;
   public static EntityType<EntityLavaSlime> LAVA_SLIME;
   public static EntityType<EntityPinky> PINKY;
   public static EntityType<EntityYellowSlime> YELLOW_SLIME;
   public static EntityType<EntityEaterOfSouls> EOS;
   public static EntityType<EntityDemon> DEMON;
   public static EntityType<EntityHellbat> HELLBAT;
   public static EntityType<EntityBat> BAT;
   public static EntityType<EntityArrowT> ARROW;
   public static EntityType<EntityThrowingT> THROWING;
   public static EntityType<EntityDemonEye> DEMON_EYE;
   public static EntityType<EntityEyeOfCthulhu> EYE_OF_CTHULHU;
   public static EntityType<EntityZombieT> ZOMBIE;
   public static EntityType<EntityDrownedT> DROWNED;
   public static EntityType<EntityBunnyT> BUNNY;
   public static EntityType<EntityHeart> HEART;
   public static EntityType<EntityCoin> COIN;
   public static EntityType<EntityStar> STAR;
   public static EntityType<EntityCoinPortal> COIN_PORTAL;
   public static EntityType<EntityRope> ROPE;
   public static EntityType<EntityWormHead> WORM_HEAD;
   public static EntityType<EntityWormBody> WORM_BODY;
   public static EntityType<EntityWormTail> WORM_TAIL;
   public static EntityType<EntityEowHead> EOW_HEAD;
   public static EntityType<EntityEowBody> EOW_BODY;
   public static EntityType<EntityEowTail> EOW_TAIL;
   public static EntityType<EntityBullet> BULLET;
   public static EntityType<EntityShadowOrb> SHADOW_ORB;
   public static EntityType<EntityGuide> GUIDE;
   public static EntityType<EntityVileSpit> VILE_SPIT;
   public static EntityType<EntityMagicProjectile> MAGIC_PROJECTILE;
   public static EntityType<VilethornProjectile> VILETHORN;
   public static EntityType<EntityFlail> FLAIL;
   public static EntityType<EntityBallOHurt> BALL_O_HURT;
   public static EntityType<EntityTekhairaProjectile> TEKHAIRA_PROJECTILE;
   public static EntityType<EntityEnchantedBoomerang> ENCHANTED_BOOMERANG;
   public static EntityType<EntitySummoningImp> SUMMONING_IMP;
   public static EntityType<EntitySummoningImpFireball> SUMMONING_IMP_FIREBALL;
   public static EntityType<EntityWallOfFlesh> WALL_OF_FLESH;
   public static EntityType<EntityWallOfFleshEye> WALL_OF_FLESH_EYE;
   public static EntityType<EntityWallOfFleshMouth> WALL_OF_FLESH_MOUTH;
   public static EntityType<EntityLeechHead> LEECH_HEAD;
   public static EntityType<EntityLeechBody> LEECH_BODY;
   public static EntityType<EntityLeechTail> LEECH_TAIL;
   public static EntityType<EntityEyeLaser> EYE_LASER;
   public static EntityType<TheHungryEntity> THE_HUNGRY;
   public static EntityType<EntityVoodooDemon> VOODOO_DEMON;
   public static EntityType<EntityHarpy> HARPY;
   public static EntityType<EntityWyvernHead> WYVERN_HEAD;
   public static EntityType<EntityWyvernBody> WYVERN_BODY;
   public static EntityType<EntityWyvernTail> WYVERN_TAIL;
   public static EntityType<EntityDestroyerHead> DESTROYER_HEAD;
   public static EntityType<EntityDestroyerBody> DESTROYER_BODY;
   public static EntityType<EntityProbe> PROBE;
   public static EntityType<EntityCorruptSlime> CORRUPT_SLIME;
   public static EntityType<EntityHallowSlime> HALLOW_SLIME;
   public static EntityType<EntityMerchant> MERCHANT;
   public static EntityType<EntityOldMan> OLD_MAN;
   public static EntityType<EntitySkeletronHead> SKELETRON_HEAD;
   public static EntityType<EntitySkeletronLeftArm> SKELETRON_LEFT_ARM;
   public static EntityType<EntitySkeletronRightArm> SKELETRON_RIGHT_ARM;
   public static EntityType<EntitySkeletronLeftArm2> SKELETRON_LEFT_ARM2;
   public static EntityType<EntitySkeletronRightArm2> SKELETRON_RIGHT_ARM2;
   public static EntityType<EntityBrainOfCthulhu> BOC;
   public static EntityType<EntityCreeper> CREEPER;
   public static EntityType<Spazmatism> SPAZMATISM;
   public static EntityType<Ratinizer> RATINIZER;
   public static EntityType<TwinsBinding> TWINS_BINDING;
   public static EntityType<Plantera> PLANTERA;
   public static EntityType<GolemHead> GOLEM_HEAD;
   public static EntityType<GolemBody> GOLEM_BODY;
   public static EntityType<LunaticCultist> LUNATIC_CULSTIST;
   public static EntityType<StardustPillar> STARDUST_PILLAR;
   public static EntityType<VortexPillar> VORTEX_PILLAR;
   public static EntityType<NebulaPillar> NEBULA_PILLAR;
   public static EntityType<SolarPillar> SOLAR_PILLAR;
   public static EntityType<PlanteraSeed> PLANTERA_SEED;   
   public static EntityType<Spore> SPORE;  
   public static EntityType<EntityFlyingFish> FLYING_FISH;
   public static EntityType<EntityPossessedArmor> POSSESSED_ARMOR;
   public static EntityType<EntityToxicSludge> TOXIC_SLUDGE;
   public static EntityType<MoonLord> MOON_LORD;
   public static EntityType<EntityCrimera> CRIMERA;
   public static EntityType<EntityFaceMonster> FACE_MONSTER;


   
   @SuppressWarnings("unchecked")
@SubscribeEvent
   public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
      EntitiesT.ITEM = register("trewrite:entityitemt", Builder.<EntityItemT>create(EntityItemT::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
    	  	return new EntityItemT(world);
      }));
      EntitiesT.BLUE_SLIME = register("trewrite:entityblueslime", Builder.<EntityBlueSlime>create(EntityBlueSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBlueSlime(world);
      }));
      EntitiesT.ARROW = register("trewrite:entityarrow", Builder.<EntityArrowT>create(EntityArrowT::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityArrowT(world);
      }));
      EntitiesT.THROWING = register("trewrite:entitythrowing", Builder.<EntityThrowingT>create(EntityThrowingT::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityThrowingT(world);
      }));
      EntitiesT.DEMON_EYE = register("trewrite:entitydemoneye", Builder.<EntityDemonEye>create(EntityDemonEye::new, EntityClassification.MISC).size(0.75F, 0.75F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityDemonEye(world);
      }));
      EntitiesT.EYE_OF_CTHULHU = register("trewrite:entityeyeofcthulhu", Builder.<EntityEyeOfCthulhu>create(EntityEyeOfCthulhu::new, EntityClassification.MISC).size(4.0F, 4.0F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEyeOfCthulhu(world);
      }));
      EntitiesT.ZOMBIE = register("trewrite:zombie", Builder.<EntityZombieT>create(EntityZombieT::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityZombieT(world);
      }));
      EntitiesT.DROWNED = register("trewrite:drowned", Builder.<EntityDrownedT>create(EntityDrownedT::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityDrownedT(world);
      }));
      EntitiesT.BUNNY = register("trewrite:bunny", Builder.<EntityBunnyT>create(EntityBunnyT::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBunnyT(world);
      }));
      EntitiesT.HEART = register("trewrite:entityheart", Builder.<EntityHeart>create(EntityHeart::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityHeart(world);
      }));
      EntitiesT.COIN = register("trewrite:entitycoin", Builder.<EntityCoin>create(EntityCoin::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityCoin(world);
      }));
      EntitiesT.STAR = register("trewrite:entitystar", Builder.<EntityStar>create(EntityStar::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityStar(world);
      }));
      EntitiesT.COIN_PORTAL = register("trewrite:entitycoinportal", Builder.<EntityCoinPortal>create(EntityCoinPortal::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityCoinPortal(world);
      }));
      EntitiesT.ROPE = register("trewrite:entityrope", Builder.<EntityRope>create(EntityRope::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityRope(world);
      }));
      EntitiesT.WORM_HEAD = register("trewrite:entitywormhead", Builder.<EntityWormHead>create(EntityWormHead::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWormHead(world);
      }));
      EntitiesT.WORM_BODY = register("trewrite:entitywormbody", Builder.<EntityWormBody>create(EntityWormBody::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWormBody(world);
      }));
      EntitiesT.WORM_TAIL = register("trewrite:entitywormtail", Builder.<EntityWormTail>create(EntityWormTail::new, EntityClassification.MISC).size(0.5F, 0.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWormTail(world);
      }));
      EntitiesT.EOW_HEAD = register("trewrite:entityeowhead", Builder.<EntityEowHead>create(EntityEowHead::new, EntityClassification.MISC).size(4.0F, 4.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEowHead(world);
      }));
      EntitiesT.EOW_BODY = register("trewrite:entityeowbody", Builder.<EntityEowBody>create(EntityEowBody::new, EntityClassification.MISC).size(4.0F, 4.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEowBody(world);
      }));
      EntitiesT.EOW_TAIL = register("trewrite:entityeowtail", Builder.<EntityEowTail>create(EntityEowTail::new, EntityClassification.MISC).size(4.0F, 4.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEowTail(world);
      }));
      EntitiesT.BULLET = register("trewrite:entitybullet", Builder.<EntityBullet>create(EntityBullet::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBullet(world);
      }));
      EntitiesT.SHADOW_ORB = register("trewrite:entityshadoworb", Builder.<EntityShadowOrb>create(EntityShadowOrb::new, EntityClassification.MISC).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityShadowOrb(world);
      }));
      EntitiesT.VILE_SPIT = register("trewrite:vile_spit", Builder.<EntityVileSpit>create(EntityVileSpit::new, EntityClassification.MISC).size(0.5F, 0.5F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityVileSpit(world);
      }));
      EntitiesT.MAGIC_PROJECTILE = register("trewrite:magic_projectile", Builder.<EntityMagicProjectile>create(EntityMagicProjectile::new, EntityClassification.MISC).size(0.5F, 0.5F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityMagicProjectile(world);
      }));
      EntitiesT.VILETHORN = register("trewrite:vilethorn_projectile", Builder.<VilethornProjectile>create(VilethornProjectile::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new VilethornProjectile(world);
      }));
      EntitiesT.FLAIL = register("trewrite:flail", Builder.<EntityFlail>create(EntityFlail::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityFlail(world);
      }));
      EntitiesT.BALL_O_HURT = register("trewrite:ball_o_hurt", Builder.<EntityBallOHurt>create(EntityBallOHurt::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBallOHurt(world);
      }));
      EntitiesT.TEKHAIRA_PROJECTILE = register("trewrite:tekhaira_projectile", Builder.<EntityTekhairaProjectile>create(EntityTekhairaProjectile::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityTekhairaProjectile(world);
      }));
      EntitiesT.ENCHANTED_BOOMERANG = register("trewrite:enchanted_boomerang", Builder.<EntityEnchantedBoomerang>create(EntityEnchantedBoomerang::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEnchantedBoomerang(world);
      }));
      EntitiesT.SUMMONING_IMP = register("trewrite:summoning_imp", Builder.<EntitySummoningImp>create(EntitySummoningImp::new, EntityClassification.MISC).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntitySummoningImp(world);
      }));
      EntitiesT.SUMMONING_IMP_FIREBALL = register("trewrite:summoning_imp_fireball", Builder.<EntitySummoningImpFireball>create(EntitySummoningImpFireball::new, EntityClassification.MISC).size(100.0F, 100.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntitySummoningImpFireball(world);
      }));
      EntitiesT.WALL_OF_FLESH = register("trewrite:wall_of_flesh", Builder.<EntityWallOfFlesh>create(EntityWallOfFlesh::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setTrackingRange(50000).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWallOfFlesh(world);
      }));
      EntitiesT.WALL_OF_FLESH_EYE = register("trewrite:wall_of_flesh_eye", Builder.<EntityWallOfFleshEye>create(EntityWallOfFleshEye::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setTrackingRange(50000).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWallOfFleshEye(world);
      }));
      EntitiesT.WALL_OF_FLESH_MOUTH = register("trewrite:wall_of_flesh_mouth", Builder.<EntityWallOfFleshMouth>create(EntityWallOfFleshMouth::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setTrackingRange(50000).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityWallOfFleshMouth(world);
      }));
      EntitiesT.LEECH_HEAD = register("trewrite:leech_head", Builder.<EntityLeechHead>create(EntityLeechHead::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityLeechHead(world);
      }));
      EntitiesT.LEECH_BODY = register("trewrite:leech_body", Builder.<EntityLeechBody>create(EntityLeechBody::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityLeechBody(world);
      }));
      EntitiesT.LEECH_TAIL = register("trewrite:leech_tail", Builder.<EntityLeechTail>create(EntityLeechTail::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityLeechTail(world);
      }));
      EntitiesT.EYE_LASER = register("trewrite:eye_laser", Builder.<EntityEyeLaser>create(EntityEyeLaser::new, EntityClassification.MONSTER).size(1.0F, 1.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEyeLaser(world);
      }));
      EntitiesT.THE_HUNGRY = register("trewrite:the_hungry", Builder.<TheHungryEntity>create(TheHungryEntity::new, EntityClassification.MONSTER).size(2.0F, 2.0F).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
         return new TheHungryEntity(world);
      }));
      EntitiesT.RED_SLIME = register("trewrite:entityredlime", Builder.<EntityRedSlime>create(EntityRedSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityRedSlime(world);
      }));
      EntitiesT.BLACK_SLIME = register("trewrite:entityblackslime", Builder.<EntityBlackSlime>create(EntityBlackSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBlackSlime(world);
      }));
      EntitiesT.LAVA_SLIME = register("trewrite:entitylavaslime", Builder.<EntityLavaSlime>create(EntityLavaSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityLavaSlime(world);
      }));
      EntitiesT.ICE_SLIME = register("trewrite:entityiceslime", Builder.<EntityIceSlime>create(EntityIceSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityIceSlime(world);
      }));
      EntitiesT.SAND_SLIME = register("trewrite:entitysandslime", Builder.<EntitySandSlime>create(EntitySandSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntitySandSlime(world);
      }));
      EntitiesT.PINKY = register("trewrite:entitypinky", Builder.<EntityPinky>create(EntityPinky::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityPinky(world);
      }));
      EntitiesT.YELLOW_SLIME = register("trewrite:entityyellowslime", Builder.<EntityYellowSlime>create(EntityYellowSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityYellowSlime(world);
      }));
      EntitiesT.EOS = register("trewrite:entityeos", Builder.<EntityEaterOfSouls>create(EntityEaterOfSouls::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityEaterOfSouls(world);
      }));
      EntitiesT.DEMON = register("trewrite:entitydemon", Builder.<EntityDemon>create(EntityDemon::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityDemon(world);
      }));
      EntitiesT.HELLBAT = register("trewrite:entityhellbat", Builder.<EntityHellbat>create(EntityHellbat::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityHellbat(world);
      }));
      EntitiesT.BAT = register("trewrite:entitybat", Builder.<EntityBat>create(EntityBat::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
         return new EntityBat(world);
      }));
      EntitiesT.VOODOO_DEMON = register("trewrite:entityvoodoodemon", Builder.<EntityVoodooDemon>create(EntityVoodooDemon::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityVoodooDemon(world);
       }));
      EntitiesT.HARPY = register("trewrite:harpy", Builder.<EntityHarpy>create(EntityHarpy::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityHarpy(world);
       }));
      EntitiesT.WYVERN_HEAD = register("trewrite:wyvern_head", Builder.<EntityWyvernHead>create(EntityWyvernHead::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityWyvernHead(world);
       }));
      EntitiesT.WYVERN_BODY = register("trewrite:wyvern_body", Builder.<EntityWyvernBody>create(EntityWyvernBody::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityWyvernBody(world);
       }));
      EntitiesT.WYVERN_TAIL = register("trewrite:wyvern_tail", Builder.<EntityWyvernTail>create(EntityWyvernTail::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityWyvernTail(world);
       }));
      EntitiesT.DESTROYER_HEAD = register("trewrite:destroyer_head", Builder.<EntityDestroyerHead>create(EntityDestroyerHead::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
          return new EntityDestroyerHead(world);
       }));    
       EntitiesT.DESTROYER_BODY = register("trewrite:destroyer_body", Builder.<EntityDestroyerBody>create(EntityDestroyerBody::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
           return new EntityDestroyerBody(world);
        }));
       EntitiesT.PROBE = register("trewrite:probe", Builder.<EntityProbe>create(EntityProbe::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
           return new EntityProbe(world);
        }));
       EntitiesT.CORRUPT_SLIME = register("trewrite:entitycorruptslime", Builder.<EntityCorruptSlime>create(EntityCorruptSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
           return new EntityCorruptSlime(world);
        }));
       EntitiesT.HALLOW_SLIME = register("trewrite:entityhallowslime", Builder.<EntityHallowSlime>create(EntityHallowSlime::new, EntityClassification.MISC).size(1.5F, 1.5F).setCustomClientFactory((spawnEntity, world) -> {
           return new EntityHallowSlime(world);
        }));
       EntitiesT.SKELETRON_HEAD = register("trewrite:skeletron_head", Builder.<EntitySkeletronHead>create(EntitySkeletronHead::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
     	  	return new EntitySkeletronHead(world);
      }));
       EntitiesT.SKELETRON_LEFT_ARM = register("trewrite:skeletron_left_arm", Builder.<EntitySkeletronLeftArm>create(EntitySkeletronLeftArm::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
    	  	return new EntitySkeletronLeftArm(world);
     }));
       EntitiesT.SKELETRON_RIGHT_ARM = register("trewrite:skeletron_right_arm", Builder.<EntitySkeletronRightArm>create(EntitySkeletronRightArm::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
    	  	return new EntitySkeletronRightArm(world);
     }));
       EntitiesT.SKELETRON_LEFT_ARM2 = register("trewrite:skeletron_left_arm2", Builder.<EntitySkeletronLeftArm2>create(EntitySkeletronLeftArm2::new, EntityClassification.MISC).size(3, 6).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
   	  	return new EntitySkeletronLeftArm2(world);
    }));
      EntitiesT.SKELETRON_RIGHT_ARM2 = register("trewrite:skeletron_right_arm2", Builder.<EntitySkeletronRightArm2>create(EntitySkeletronRightArm2::new, EntityClassification.MISC).size(3, 6).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
   	  	return new EntitySkeletronRightArm2(world);
    }));
      EntitiesT.BOC = register("trewrite:brain_of_cthulhu", Builder.<EntityBrainOfCthulhu>create(EntityBrainOfCthulhu::new, EntityClassification.MISC).size(4, 4).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
     	  	return new EntityBrainOfCthulhu(world);
      }));
      EntitiesT.CREEPER = register("trewrite:creeper", Builder.<EntityCreeper>create(EntityCreeper::new, EntityClassification.MISC).size(1, 1).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
   	  	return new EntityCreeper(world);
    }));
      EntitiesT.RATINIZER = register("trewrite:retinizer", Builder.<Ratinizer>create(Ratinizer::new, EntityClassification.MISC).size(6, 6).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
   	  	return new Ratinizer(world);
    }));
    EntitiesT.SPAZMATISM = register("trewrite:spazmatism", Builder.<Spazmatism>create(Spazmatism::new, EntityClassification.MISC).size(6, 6).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new Spazmatism(world);
  }));
    EntitiesT.TWINS_BINDING = register("trewrite:twins_binding", Builder.<TwinsBinding>create(TwinsBinding::new, EntityClassification.MISC).size(1, 1).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new TwinsBinding(world);
  }));
    EntitiesT.PLANTERA = register("trewrite:plantera", Builder.<Plantera>create(Plantera::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new Plantera(world);
  }));
    EntitiesT.GOLEM_BODY = register("trewrite:golem_body", Builder.<GolemBody>create(GolemBody::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new GolemBody(world);
  }));
    EntitiesT.GOLEM_HEAD = register("trewrite:golem_head", Builder.<GolemHead>create(GolemHead::new, EntityClassification.MISC).size(3, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new GolemHead(world);
  }));
    EntitiesT.LUNATIC_CULSTIST = register("trewrite:lunatic_cultist", Builder.<LunaticCultist>create(LunaticCultist::new, EntityClassification.MISC).size(1, 1).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new LunaticCultist(world);
  }));
    EntitiesT.STARDUST_PILLAR = register("trewrite:stardust_pillar", Builder.<StardustPillar>create(StardustPillar::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new StardustPillar(world);
  }));
    EntitiesT.VORTEX_PILLAR = register("trewrite:vortex_pillar", Builder.<VortexPillar>create(VortexPillar::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new VortexPillar(world);
  }));
    EntitiesT.NEBULA_PILLAR = register("trewrite:nebula_pillar", Builder.<NebulaPillar>create(NebulaPillar::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new NebulaPillar(world);
  }));
    EntitiesT.SOLAR_PILLAR = register("trewrite:solar_pillar", Builder.<SolarPillar>create(SolarPillar::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new SolarPillar(world);
  }));
    EntitiesT.PLANTERA_SEED = register("trewrite:plantera_seed", Builder.<PlanteraSeed>create(PlanteraSeed::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new PlanteraSeed(world);
  }));
    EntitiesT.SPORE = register("trewrite:spore", Builder.<Spore>create(Spore::new, EntityClassification.MISC).size(5, 10).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new Spore(world);
  }));
   EntitiesT.FLYING_FISH = register("trewrite:flying_fish", Builder.<EntityFlyingFish>create(EntityFlyingFish::new, EntityClassification.MISC).size(0.3f, 0.3f).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
 	  	return new EntityFlyingFish(world);
  }));
   EntitiesT.POSSESSED_ARMOR = register("trewrite:posssessed_armor", Builder.<EntityPossessedArmor>create(EntityPossessedArmor::new, EntityClassification.MISC).size(1, 2).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
	  	return new EntityPossessedArmor(world);
 }));
     
   EntitiesT.TOXIC_SLUDGE = register("trewrite:toxic_sludge", Builder.<EntityToxicSludge>create(EntityToxicSludge::new, EntityClassification.MISC).size(1, 1).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
	  	return new EntityToxicSludge(world);
 }));
   EntitiesT.MOON_LORD = register("trewrite:moon_lord", Builder.<MoonLord>create(MoonLord::new, EntityClassification.MISC).size(50, 50).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
	  	return new MoonLord(world);
   }));
   EntitiesT.CRIMERA = register("trewrite:crimera", Builder.<EntityCrimera>create(EntityCrimera::new, EntityClassification.MISC).size(0.75F, 0.75F).setCustomClientFactory((spawnEntity, world) -> {
       return new EntityCrimera(world);
    }));
   EntitiesT.FACE_MONSTER = register("trewrite:face_monster", Builder.<EntityFaceMonster>create(EntityFaceMonster::new, EntityClassification.MISC).size(2, 3).immuneToFire().setCustomClientFactory((spawnEntity, world) -> {
	  	return new EntityFaceMonster(world);
   }));
     
      
   	  SpawnCondition.spawnConditions.put(EntitiesT.POSSESSED_ARMOR, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.BOG_GRASS, BlocksT.JUNGLE_GRASS, BlocksT.MUD, BlocksT.SAND, BlocksT.RED_SAND, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.BLUE_SLIME, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.FLYING_FISH, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.DEMON_EYE, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.BOG_GRASS, BlocksT.JUNGLE_GRASS, BlocksT.MUD, BlocksT.SAND, BlocksT.RED_SAND, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.BUNNY, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.DROWNED, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.ZOMBIE, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.BOG_GRASS, BlocksT.JUNGLE_GRASS, BlocksT.MUD, BlocksT.SAND, BlocksT.RED_SAND, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.DROWNED, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.BOG_GRASS, BlocksT.JUNGLE_GRASS, BlocksT.MUD, BlocksT.SAND, BlocksT.RED_SAND, BlocksT.PODZOL, Blocks.WATER));
      SpawnCondition.spawnConditions.put(EntitiesT.WORM_HEAD, new SpawnCondition(0, 255, SpawnCondition.RARE, BlocksT.STONE_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.YELLOW_SLIME, new SpawnCondition(0, 255, SpawnCondition.UNCOMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.TOXIC_SLUDGE, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.SAND_SLIME, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.SAND));
      SpawnCondition.spawnConditions.put(EntitiesT.ICE_SLIME, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.SNOW, BlocksT.ICE));
      SpawnCondition.spawnConditions.put(EntitiesT.LAVA_SLIME, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.ASH_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.RED_SLIME, new SpawnCondition(0, 255, SpawnCondition.UNCOMMON, BlocksT.DIRT_BLOCK, BlocksT.STONE_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.BLACK_SLIME, new SpawnCondition(0, 255, SpawnCondition.UNCOMMON, BlocksT.DIRT_BLOCK, BlocksT.STONE_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.PINKY, new SpawnCondition(0, 255, SpawnCondition.RARE, BlocksT.DIRT_BLOCK, BlocksT.GRASS_BLOCK, BlocksT.HIGHLANDS_GRASS, BlocksT.PODZOL));
      SpawnCondition.spawnConditions.put(EntitiesT.EOS, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.CORRUPT_GRASS, BlocksT.EBONSAND, BlocksT.EBONSTONE));
      SpawnCondition.spawnConditions.put(EntitiesT.DEMON, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.ASH_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.VOODOO_DEMON, new SpawnCondition(0, 255, SpawnCondition.UNCOMMON, BlocksT.ASH_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.HELLBAT, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.ASH_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.BAT, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.STONE_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.HARPY, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.STONE_BLOCK, BlocksT.DIRT_BLOCK, BlocksT.WOOD, BlocksT.AIR_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.CORRUPT_SLIME, new SpawnCondition(0, 255, SpawnCondition.COMMON, BlocksT.EBONSTONE, BlocksT.CORRUPT_GRASS, BlocksT.EBONSAND));
      SpawnCondition.spawnConditions.put(EntitiesT.WYVERN_HEAD, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.STONE_BLOCK, BlocksT.DIRT_BLOCK, BlocksT.WOOD, BlocksT.AIR_BLOCK));
      SpawnCondition.spawnConditions.put(EntitiesT.HALLOW_SLIME, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.PEARLSTONE, BlocksT.HALLOW_GRASS));
      SpawnCondition.spawnConditions.put(EntitiesT.CRIMERA, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.CRIMSON_GRASS, BlocksT.CRIMSTONE));
      SpawnCondition.spawnConditions.put(EntitiesT.FACE_MONSTER, new SpawnCondition(0, 255, SpawnCondition.VERY_COMMON, BlocksT.CRIMSON_GRASS, BlocksT.CRIMSTONE));
      
   }

   private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
	      return Registry.register(Registry.ENTITY_TYPE, id, builder.build(id));
   }
}
