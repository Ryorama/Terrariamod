package kmerrill285.trewrite.entities.monsters;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDrownedT extends DrownedEntity implements IHostile {
   public int money;

   public EntityDrownedT(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityDrownedT(World world) {
      super(EntityType.DROWNED, world);
   }

   public void dropLoot(DamageSource source, boolean b) {
      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
      }

      if (source.getImmediateSource() instanceof PlayerEntity) {
         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
         }
      }

      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)(this.rand.nextInt(10) + 35));
      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue((double)(this.rand.nextInt(3) + 4));
      this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(this.rand.nextDouble() * 0.1D + 0.5D);
      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)(this.rand.nextInt(7) + 12));
      this.money = this.rand.nextInt(20) + 65;
   }
}
