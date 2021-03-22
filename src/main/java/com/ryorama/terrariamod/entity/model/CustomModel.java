package com.ryorama.terrariamod.entity.model;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public abstract class CustomModel<T extends Entity> extends EntityModel<T> {
	public List<CubePart> parts = new ArrayList<CubePart>();
	
	public List<ModelPart> model_parts = new ArrayList<ModelPart>();
	
	protected final int textureWidth;
	protected final int textureHeight;
	
	public CustomModel(int textureWidth, int textureHeight) {
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}
	
	public TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		
		for (int i = 0; i < parts.size(); i++) {
			if (i > 0) {
				CubePart part = parts.get(i);
				ModelTransform transform = ModelTransform.of((float)part.rotationPoint.x, (float)part.rotationPoint.y, (float)part.rotationPoint.z, (float)part.rotateAngleX, (float)part.rotateAngleY, (float)part.rotateAngleZ);
				modelPartData.addChild("part"+i, ModelPartBuilder.create().uv((int)part.textureX, (int)part.textureY).cuboid((float)part.position.x, (float)part.position.y, (float)part.position.z, (float)part.size.x, (float)part.size.y, (float)part.size.z),
						transform);
				model_parts.add(modelPartData.getChild("part"+i).createPart(textureWidth, textureHeight));
			} else {
				CubePart part = parts.get(i);

				ModelTransform transform = ModelTransform.of((float)part.rotationPoint.x, (float)part.rotationPoint.y, (float)part.rotationPoint.z, (float)part.rotateAngleX, (float)part.rotateAngleY, (float)part.rotateAngleZ);
				modelPartData.addChild("part"+i, ModelPartBuilder.create().uv((int)part.textureX, (int)part.textureY).cuboid((float)part.position.x, (float)part.position.y - (float)part.size.y * 0.4f, (float)part.position.z, (float)part.size.x, (float)part.size.y, (float)part.size.z),
						transform);
				model_parts.add(modelPartData.getChild("part"+i).createPart(textureWidth, textureHeight));
			}
		}
		TexturedModelData data = TexturedModelData.of(modelData, textureWidth, textureHeight);
		//model_parts.add(data.createModel());
		return data;
	}
	
	
	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
			float headPitch) {
		
	}
	
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green,
			float blue, float alpha) {
		model_parts.forEach((modelPart) -> {
			modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		});
	}
}
