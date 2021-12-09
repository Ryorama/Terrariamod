package com.ryorama.terrariamod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.minecraft.util.math.MathHelper;

public class Resources {
	
	public static void makeFieldAccessible(Field field) throws Exception {
		Field modifiers = Field.class.getDeclaredField("modifiers");
		modifiers.setAccessible(true);
		try {
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.PROTECTED);
			modifiers.setInt(field, field.getModifiers() | Modifier.PUBLIC);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static float getDistanceFromDeltas(double dx, double dy, double dz)
    {
        return MathHelper.sqrt((float) (dx * dx + dy * dy + dz * dz));
    }
	
	public static float lerp(float a, float b, float lerp)
	{
	    return a + lerp * (b - a);
	}

	public static Field findField(Class<?> c, String... names) throws NoSuchFieldException {
		Field f = null;

		for (String s : names) {
			try {
				f = c.getDeclaredField(s);
				f.setAccessible(true);
				break;
			} catch (Exception e) {}
		}

		if (f == null) {
			throw new NoSuchFieldException("No field found matching one of the given names: " + names);
		}

		return f;
	}

	public static Method findMethod(Class<?> c, String deobName, String obName, Class<?>... args) throws NoSuchMethodException {
		Method m = null;

		try {
			m = c.getDeclaredMethod(deobName, args);
			m.setAccessible(true);
		} catch (Exception e) {}
		try {
			m = c.getDeclaredMethod(obName, args);
			m.setAccessible(true);
		} catch (Exception e) {}

		if (m == null) {
			throw new NoSuchMethodException("No method found matching one of the given names or arguments: " + deobName + ", " + obName);
		}

		return m;
	}
}
