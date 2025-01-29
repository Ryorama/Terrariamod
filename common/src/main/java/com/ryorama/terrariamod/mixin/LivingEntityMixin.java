package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.entities.impl.CustomBuffAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, CustomBuffAccessor {

    public List<BuffT> activeBuffs = new ArrayList<>();
    public List<ResourceLocation> activeBuffsIcons = new ArrayList<>();

    public boolean calledBuffIconRenderer = false;

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo info) {
        LivingEntity thisEntity = ((LivingEntity) (Object) this);

        if (thisEntity instanceof Player) {
            Player player = (Player) thisEntity;
            Level playerWorld = player.level();

            if (playerWorld.isClientSide()) {
                if (player.isLocalPlayer()) {
                    if (!calledBuffIconRenderer) {
                        BuffT.renderIcon(thisEntity);
                        calledBuffIconRenderer = true;
                    }
                }
            }
        }

        for (int b = 0; b < activeBuffs.size(); b++) {
            activeBuffs.get(b).tick(thisEntity);
            if (activeBuffs.get(b).getDuration() <= 0) {
                RemoveBuff(activeBuffs.get(b));
                return;
            }
        }
    }

    @Override
    public List<BuffT> GetActiveBuffs() {
        return activeBuffs;
    }

    @Override
    public List<ResourceLocation> GetActiveBuffIcons() {
        return activeBuffsIcons;
    }

    @Override
    public void SetActiveBuffs(List<BuffT> buffs) {
        activeBuffs = buffs;
    }

    @Override
    public void AddBuff(float duration, BuffT buff) {
        buff.setDuration(duration);
        activeBuffs.add(buff);

        for (int b = 0; b < BuffsT.buffs.size(); b++) {
            if (BuffsT.buffs.get(b).equals(buff)) {
                activeBuffsIcons.add(BuffT.buffIcons.get(b));
            }
        }
    }

    public void RemoveBuff(BuffT buff) {
        activeBuffs.remove(buff);

        for (int b = 0; b < BuffsT.buffs.size(); b++) {
            if (BuffsT.buffs.get(b).equals(buff)) {
                activeBuffsIcons.remove(BuffT.buffIcons.get(b));
            }
        }
    }
}
