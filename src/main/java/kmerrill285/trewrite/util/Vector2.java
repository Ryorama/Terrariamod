package kmerrill285.trewrite.util;

public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0.0F, 0.0F);

    public final float x;

    public final float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return length(this.x, this.y);
    }

    public static float length(float x, float y) {
        return (float)Math.sqrt((x * x + y * y));
    }

    public static double length(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static float lengthSq(float x, float y) {
        return x * x + y * y;
    }

    public static double lengthSq(double x, double y) {
        return x * x + y * y;
    }

    public Vector2 add(Vector2 vec) {
        return new Vector2(this.x + vec.x, this.y + vec.y);
    }

    public Vector2 subtract(Vector2 vec) {
        return new Vector2(this.x - vec.x, this.y - vec.y);
    }

    public Vector2 mult(float v) {
        return new Vector2(this.x * v, this.y * v);
    }

    public Vector2 mult(Vector2 vec) {
        return new Vector2(this.x * vec.x, this.y * vec.y);
    }
}