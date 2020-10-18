package kmerrill285.trewrite.world.biome.features.LSystem;

public class LSystemPos {
	public double x, y, z;

	public double rotationXY = 90;
	public double rotationZY = 90;
	public double rotationXZ;
	
	public LSystemPos(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}

	public LSystemPos forwards() {
		
		LSystemPos pos2 = new LSystemPos(Math.cos(Math.toRadians(rotationXY)) * Math.cos(Math.toRadians(rotationXZ)), Math.sin(Math.toRadians(rotationXY + rotationXZ)), Math.cos(Math.toRadians(rotationXZ)) * Math.cos(Math.toRadians(rotationXY)));
		pos2.rotationXY = rotationXY;
		pos2.rotationZY = rotationZY;
		pos2.rotationXZ = rotationXZ;
		pos2.x += getX();
		pos2.y += getY();
		pos2.z += getZ();
		return pos2;
	}
	
	public LSystemPos forwards(double s) {
		double size = s * 1.0;
		LSystemPos pos2 = new LSystemPos(Math.cos(Math.toRadians(rotationXY)) * size, Math.sin(Math.toRadians(rotationXY)) * size, 0);
		LSystemPos pos3 = new LSystemPos(0, Math.sin(Math.toRadians(rotationZY)) * size, Math.cos(Math.toRadians(rotationZY)) * size);
		
		pos2 = new LSystemPos(pos2.x, (pos2.y + pos3.y), pos3.z);
		
		pos2.x = pos2.x * Math.cos(Math.toRadians(rotationXZ)) + pos2.z * Math.sin(Math.toRadians(rotationXZ));
		pos2.z = pos2.z * Math.cos(Math.toRadians(rotationXZ)) + pos2.x * Math.sin(Math.toRadians(rotationXZ));
		
		pos2.rotationXY = rotationXY;
		pos2.rotationZY = rotationZY;
		pos2.rotationXZ = rotationXZ;
		
		pos2.y *= 0.8;
//		pos2.x *= 2;
//		pos2.z *= 2;
		
		pos2.x += getX();
		pos2.y += getY();
		pos2.z += getZ();
		return pos2;
	}
}
