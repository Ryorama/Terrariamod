package kmerrill285.trewrite.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class EntityMixin extends Entity {

	public EntityMixin(EntityType<?> type, World world){
        super(type, world);
    }

    //Up to 100
    public float deathRemovalCooldown;

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        if (!isAlive()) {
            deathRemovalCooldown += 1;
        }
    }

    @Overwrite
    public void onDeath(DamageSource cause) {
        if (this.deathRemovalCooldown == 120) {
            this.remove();
            System.out.println("Death");
        }
    }
}