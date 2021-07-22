package kmerrill285.trewrite.items.vanilla;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import kmerrill285.trewrite.util.DimensionTeleporter;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.dimension.DimensionType;

public class OverworldTeleporter extends ItemT {

    public OverworldTeleporter(Properties properties, String name) {
        super(properties, name);
        this.setMaxStack(1);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if (context.getWorld().getDimension().getType() == DimensionType.byName(Trewrite.overworld)) {
            if (context.getPlayer() instanceof ServerPlayerEntity) {
                DimensionTeleporter.TeleportToOverworld((ServerPlayerEntity) context.getPlayer());
            }
        } else {
            if (context.getPlayer() instanceof ServerPlayerEntity) {
                DimensionTeleporter.TeleportToTerraria((ServerPlayerEntity) context.getPlayer());
            }
        }

        return ActionResultType.PASS;
    }

}
