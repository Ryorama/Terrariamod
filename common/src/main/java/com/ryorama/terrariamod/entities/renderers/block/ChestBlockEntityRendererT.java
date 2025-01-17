package com.ryorama.terrariamod.entities.renderers.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryorama.terrariamod.blocks.impl.ChestT;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Environment(EnvType.CLIENT)
public class ChestBlockEntityRendererT<T extends ChestBlockEntity> extends ChestRenderer<T> {

    private final ModelPart chestLid;
    private final ModelPart chestBottom;
    private final ModelPart chestLock;

    public ChestBlockEntityRendererT(BlockEntityRendererProvider.Context context) {
        super(context);

        ModelPart modelPart = context.bakeLayer(ModelLayers.CHEST);
        this.chestLid = modelPart.getChild("lid");
        this.chestBottom = modelPart.getChild("bottom");
        this.chestLock = modelPart.getChild("lock");
    }

    @Override
    public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        Level world = entity.getLevel();

        BlockState blockState = world != null ? entity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        Block block = blockState.getBlock();

        if (block instanceof ChestT) {
            ChestT chest = (ChestT)block;

            matrices.pushPose();
            float rotation = ((Direction)blockState.getValue(ChestBlock.FACING)).toYRot();
            matrices.translate(0.5D, 0.5D, 0.5D);
            matrices.mulPose(Direction.UP.getRotation().invert());
            matrices.translate(-0.5D, -0.5D, -0.5D);

            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> properties;
            if (world == null) {
                properties = DoubleBlockCombiner.Combiner::acceptNone;
            } else {
                properties = chest.combine(blockState, world, entity.getBlockPos(), true);
            }

            float g = ((Float2FloatFunction)properties.apply(ChestT.opennessCombiner((LidBlockEntity) entity))).get(tickDelta);
            g = 1.0F - g;
            g = 1.0F - g * g * g;
            int i = ((Int2IntFunction)properties.apply(new BrightnessCombiner<>())).applyAsInt(light);

            Material spriteIdentifier = new Material(Sheets.CHEST_SHEET, chest.getTexture());
            VertexConsumer vertexConsumer = spriteIdentifier.buffer(vertexConsumers, RenderType::entityCutout);

            handleModelRender(matrices, vertexConsumer, this.chestLid, this.chestLock, this.chestBottom, g, i, overlay);

            matrices.popPose();
        }
    }

    private static void handleModelRender(PoseStack matrices, VertexConsumer vertices, ModelPart lid, ModelPart latch, ModelPart base, float openFactor, int light, int overlay) {
        lid.xRot = -openFactor * 1.5707964F;
        latch.xRot = lid.xRot;
        lid.render(matrices, vertices, light, overlay);
        latch.render(matrices, vertices, light, overlay);
        base.render(matrices, vertices, light, overlay);
    }
}