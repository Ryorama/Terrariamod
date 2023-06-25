package com.ryorama.terrariamod.utils.math;

import java.util.Random;

public class ImprovedRandom {

    public final Random rand;

    public ImprovedRandom(Random rand) {
        this.rand = rand;
    }

    public int nextInt(int max) {
        return this.rand.nextInt(max);
    }

    public float nextFloat() {
        return this.rand.nextFloat();
    }

    public double nextDouble() {
        return this.rand.nextDouble();
    }

    public boolean nextBoolean() {
        return this.rand.nextBoolean();
    }

    public long nextLong() {
        return this.rand.nextLong();
    }

    public int nextInt(int min, int max) {
        if (max - min + 1 <= 0) {
            return min;
        }
        return min + nextInt(max - min + 1);
    }

    public float nextFloat(float min, float max) {
        return min + (max - min) * nextFloat();
    }

    public float nextFloat(float max) {
        return max * nextFloat();
    }

    public double nextDouble(double min, double max) {
        return min + (max - min) * nextDouble();
    }

    public double nextDouble(double max) {
        return nextDouble() * max;
    }

    public int next(int min, int max) {
        return nextInt(min, max);
    }

    public void setSeed(long seed) {
        this.rand.setSeed(seed);
    }

    private static final ThreadLocal<ImprovedRandom> RNG = ThreadLocal.withInitial(() -> new ImprovedRandom(new Random()));

    public static ImprovedRandom getThreadLocal(long seed) {
        ImprovedRandom ir = RNG.get();
        ir.setSeed(seed);
        return ir;
    }
}