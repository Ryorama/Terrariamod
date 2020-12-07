package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityCoinPortal;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.EntityRope;
import kmerrill285.trewrite.entities.EntityShadowOrb;
import kmerrill285.trewrite.entities.EntityStar;
import kmerrill285.trewrite.entities.models.boc.RenderBrainOfCthulhu;
import kmerrill285.trewrite.entities.models.boc.RenderCreeper;
import kmerrill285.trewrite.entities.models.boomerangs.RenderBoomerang;
import kmerrill285.trewrite.entities.models.flails.RenderBallOHurt;
import kmerrill285.trewrite.entities.models.golem.RenderGolemBody;
import kmerrill285.trewrite.entities.models.golem.RenderGolemHead;
import kmerrill285.trewrite.entities.models.moon_lord.RenderMoonLord;
import kmerrill285.trewrite.entities.models.pillars.RenderNebulaPillar;
import kmerrill285.trewrite.entities.models.pillars.RenderSolarPillar;
import kmerrill285.trewrite.entities.models.pillars.RenderStardustPillar;
import kmerrill285.trewrite.entities.models.pillars.RenderVortexPillar;
import kmerrill285.trewrite.entities.models.plantera.RenderPlantera;
import kmerrill285.trewrite.entities.models.plantera.RenderPlanteraSeed;
import kmerrill285.trewrite.entities.models.plantera.RenderSpore;
import kmerrill285.trewrite.entities.models.projectiles.RenderEyeLaser;
import kmerrill285.trewrite.entities.models.skeletron.RenderSkeletronArmLeft;
import kmerrill285.trewrite.entities.models.skeletron.RenderSkeletronArmLeft2;
import kmerrill285.trewrite.entities.models.skeletron.RenderSkeletronArmRight;
import kmerrill285.trewrite.entities.models.skeletron.RenderSkeletronArmRight2;
import kmerrill285.trewrite.entities.models.skeletron.RenderSkeletronHead;
import kmerrill285.trewrite.entities.models.summoning.RenderSummoningImp;
import kmerrill285.trewrite.entities.models.wall_of_flesh.RenderTheHungry;
import kmerrill285.trewrite.entities.models.wall_of_flesh.RenderWallOfFlesh;
import kmerrill285.trewrite.entities.models.wall_of_flesh.RenderWallOfFleshEye;
import kmerrill285.trewrite.entities.models.wall_of_flesh.RenderWallOfFleshMouth;
import kmerrill285.trewrite.entities.models.worms.RenderEowBody;
import kmerrill285.trewrite.entities.models.worms.RenderEowHead;
import kmerrill285.trewrite.entities.models.worms.RenderEowTail;
import kmerrill285.trewrite.entities.models.worms.RenderLeechBody;
import kmerrill285.trewrite.entities.models.worms.RenderLeechHead;
import kmerrill285.trewrite.entities.models.worms.RenderLeechTail;
import kmerrill285.trewrite.entities.models.worms.RenderWormBody;
import kmerrill285.trewrite.entities.models.worms.RenderWormHead;
import kmerrill285.trewrite.entities.models.worms.RenderWormTail;
import kmerrill285.trewrite.entities.models.wyvern.RenderWyvernBody;
import kmerrill285.trewrite.entities.models.wyvern.RenderWyvernHead;
import kmerrill285.trewrite.entities.models.wyvern.RenderWyvernTail;
import kmerrill285.trewrite.entities.monsters.EntityBat;
import kmerrill285.trewrite.entities.monsters.EntityBlackSlime;
import kmerrill285.trewrite.entities.monsters.EntityBlueSlime;
import kmerrill285.trewrite.entities.monsters.EntityCorruptSlime;
import kmerrill285.trewrite.entities.monsters.EntityCrimera;
import kmerrill285.trewrite.entities.monsters.EntityDemon;
import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
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
import kmerrill285.trewrite.entities.projectiles.EntityTekhairaProjectile;
import kmerrill285.trewrite.entities.projectiles.boomerangs.EntityEnchantedBoomerang;
import kmerrill285.trewrite.entities.projectiles.flails.EntityBallOHurt;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import kmerrill285.trewrite.entities.projectiles.magic_projectiles.VilethornProjectile;
import kmerrill285.trewrite.entities.summoning.EntitySummoningImp;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernBody;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernHead;
import kmerrill285.trewrite.entities.wyvern.EntityWyvernTail;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModelRegistry {
   @SubscribeEvent
   public static void registerAllModels(ModelRegistryEvent event) {
      RenderingRegistry.registerEntityRenderingHandler(EntityItemT.class, (manager) -> {
         return new RenderEntityItemT(manager, Minecraft.getInstance().getItemRenderer());
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityBlueSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "blueslime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityDemonEye.class, (manager) -> {
         return new RenderDemonEye(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEyeOfCthulhu.class, (manager) -> {
         return new RenderEOC(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityBunnyT.class, (manager) -> {
         return new RabbitRendererT(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityCoin.class, (manager) -> {
         return new RenderCoin(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityHeart.class, (manager) -> {
         return new RenderHeart(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityStar.class, (manager) -> {
         return new RenderStar(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityCoinPortal.class, (manager) -> {
         return new RenderCoinPortal(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityRope.class, (manager) -> {
         return new RenderRope(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWormHead.class, (manager) -> {
         return new RenderWormHead(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWormBody.class, (manager) -> {
         return new RenderWormBody(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWormTail.class, (manager) -> {
         return new RenderWormTail(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEowHead.class, (manager) -> {
         return new RenderEowHead(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEowBody.class, (manager) -> {
         return new RenderEowBody(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEowTail.class, (manager) -> {
         return new RenderEowTail(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityShadowOrb.class, (manager) -> {
         return new RenderShadowOrb(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityGuide.class, (manager) -> {
         return new RenderGuide(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(VilethornProjectile.class, (manager) -> {
         return new RenderVilethorn(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityBallOHurt.class, (manager) -> {
         return new RenderBallOHurt(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityTekhairaProjectile.class, (manager) -> {
         return new RenderTekhaira(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEnchantedBoomerang.class, (manager) -> {
         return new RenderBoomerang(manager, "enchanted_boomerang");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntitySummoningImp.class, (manager) -> {
         return new RenderSummoningImp(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWallOfFlesh.class, (manager) -> {
         return new RenderWallOfFlesh(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWallOfFleshEye.class, (manager) -> {
         return new RenderWallOfFleshEye(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityWallOfFleshMouth.class, (manager) -> {
         return new RenderWallOfFleshMouth(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityLeechHead.class, (manager) -> {
         return new RenderLeechHead(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityLeechBody.class, (manager) -> {
         return new RenderLeechBody(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityLeechTail.class, (manager) -> {
         return new RenderLeechTail(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEyeLaser.class, (manager) -> {
         return new RenderEyeLaser(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(TheHungryEntity.class, (manager) -> {
         return new RenderTheHungry(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityYellowSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "yellowslime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityRedSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "red_slime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityBlackSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "black_slime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityIceSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "ice_slime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityLavaSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "lava_slime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntitySandSlime.class, (manager) -> {
         return new RenderEntitySlime(manager, "sand_slime");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityPinky.class, (manager) -> {
         return new RenderEntitySlime(manager, "pinky");
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityEaterOfSouls.class, (manager) -> {
         return new RenderEaterOfSouls(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, (manager) -> {
         return new RenderDemon(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityHellbat.class, (manager) -> {
         return new RenderHellbat(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityBat.class, (manager) -> {
         return new RenderBat(manager);
      });
      RenderingRegistry.registerEntityRenderingHandler(EntityVoodooDemon.class, (manager) -> {
          return new RenderVoodooDemon(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityHarpy.class, (manager) -> {
          return new RenderHarpy(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityWyvernHead.class, (manager) -> {
          return new RenderWyvernHead(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityWyvernBody.class, (manager) -> {
          return new RenderWyvernBody(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityWyvernTail.class, (manager) -> {
          return new RenderWyvernTail(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityDestroyerHead.class, (manager) -> {
          return new RenderDestroyerHead(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityDestroyerBody.class, (manager) -> {
          return new RenderDestroyerBody(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityProbe.class, (manager) -> {
          return new RenderProbe(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityCorruptSlime.class, (manager) -> {
          return new RenderEntitySlime(manager, "corrupt_slime");
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityHallowSlime.class, (manager) -> {
          return new RenderEntitySlime(manager, "hallow_slime");
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityMerchant.class, (manager) -> {
          return new RenderMerchant(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityOldMan.class, (manager) -> {
          return new RenderOldMan(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletronHead.class, (manager) -> {
          return new RenderSkeletronHead(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletronLeftArm.class, (manager) -> {
          return new RenderSkeletronArmLeft(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletronRightArm.class, (manager) -> {
          return new RenderSkeletronArmRight(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletronLeftArm2.class, (manager) -> {
          return new RenderSkeletronArmLeft2(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntitySkeletronRightArm2.class, (manager) -> {
          return new RenderSkeletronArmRight2(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityBrainOfCthulhu.class, (manager) -> {
          return new RenderBrainOfCthulhu(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityCreeper.class, (manager) -> {
          return new RenderCreeper(manager);
       });
      
      RenderingRegistry.registerEntityRenderingHandler(Ratinizer.class, (manager) -> {
          return new RenderRatinizer(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(Spazmatism.class, (manager) -> {
          return new RenderSpazmatism(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(TwinsBinding.class, (manager) -> {
          return new RenderTwinsBinding(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(Plantera.class, (manager) -> {
          return new RenderPlantera(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(GolemHead.class, (manager) -> {
          return new RenderGolemHead(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(GolemBody.class, (manager) -> {
          return new RenderGolemBody(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(LunaticCultist.class, (manager) -> {
          return new RenderLunaticCultist(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(StardustPillar.class, (manager) -> {
          return new RenderStardustPillar(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(VortexPillar.class, (manager) -> {
          return new RenderVortexPillar(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(NebulaPillar.class, (manager) -> {
          return new RenderNebulaPillar(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(SolarPillar.class, (manager) -> {
          return new RenderSolarPillar(manager);
       }); 
      RenderingRegistry.registerEntityRenderingHandler(PlanteraSeed.class, (manager) -> {
           return new RenderPlanteraSeed(manager);
        });
      RenderingRegistry.registerEntityRenderingHandler(Spore.class, (manager) -> {
          return new RenderSpore(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityFlyingFish.class, (manager) -> {
          return new RenderFlyingFish(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityPossessedArmor.class, (manager) -> {
          return new RenderPossessedArmor(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityToxicSludge.class, (manager) -> {
          return new RenderToxicSludge(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(MoonLord.class, (manager) -> {
          return new RenderMoonLord(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityCrimera.class, (manager) -> {
          return new RenderCrimera(manager);
       });
      RenderingRegistry.registerEntityRenderingHandler(EntityFaceMonster.class, (manager) -> {
          return new RenderFaceMonster(manager);
       });
   }
}
