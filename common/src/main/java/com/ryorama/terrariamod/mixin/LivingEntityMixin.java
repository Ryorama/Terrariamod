package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.buffs.CustomBuffAccessor;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, CustomBuffAccessor {

    public List<BuffT> activeBuffs = new ArrayList<>();
    public List<Identifier> activeBuffsIcons = new ArrayList<>();

    public Entity thisEntity;

    public LivingEntity thisLivingEntity;

    public boolean calledBuffIconRenderer = false;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo info) {
        thisEntity = this.getEntityWorld().getEntityById(this.getId());

        if (thisEntity != null) {
            if(thisEntity instanceof LivingEntity) {
                thisLivingEntity = (LivingEntity) this.getEntityWorld().getEntityById(this.getId());
            }
        }

        if (this.getEntityWorld().isClient) {
            if (!calledBuffIconRenderer) {
                BuffT.renderIcon(thisLivingEntity);
                calledBuffIconRenderer = true;
            }
        }

        for (int b = 0; b < activeBuffs.size(); b++) {
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
    public List<Identifier> GetActiveBuffIcons() {
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
