package com.ryorama.terrariamod.entity.model;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class CubePart {
	public double rotateAngleX, rotateAngleY, rotateAngleZ;
	public Vec3d rotationPoint = new Vec3d(0, 0, 0);
	public Vec3d position = new Vec3d(0, 0, 0);
	public Vec3d size = new Vec3d(1, 1, 1);
	
	public double textureX, textureY;
	
	public List<CubePart> children = new ArrayList<CubePart>();
	
	public CustomModel parent;
	public CubePart(CustomModel parent) {
		this.parent = parent;
		parent.parts.add(this);
	}
	
	public CubePart setRotationPoint(double x, double y, double z) {
		rotationPoint = new Vec3d(x, y, z);
		return this;
	}
	
	public CubePart setTextureOffset(double x, double y) {
		textureX = x;
		textureY = y;
		return this;
	}
	
	public CubePart addChild(CubePart c) {
		children.add(c);
		return this;
	}
	
	public CubePart addBox(double x, double y, double z, double width, double height, double length, double unused, boolean unused_2) {
		position = new Vec3d(x, y, z);
		size = new Vec3d(width, height, length);
		return this;
	}
}
