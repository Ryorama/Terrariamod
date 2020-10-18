package kmerrill285.trewrite.world.biome.features.LSystem;

public class LSystemVec {
	public double x, y, z;
	public LSystemVec(double x, double y, double z) {
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
	
	public String toString() {
		return x + ", " + y + ", " + z;
	}
}
