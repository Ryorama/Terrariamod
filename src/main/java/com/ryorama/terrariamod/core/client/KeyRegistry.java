package com.ryorama.terrariamod.core.client;

import java.lang.reflect.Field;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.util.Util;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyRegistry {


	   public static KeyBinding openInventory;
	   public static KeyBinding swapHotbars;
	   public static KeyBinding autoHeal;
	   public static KeyBinding autoBuff;
	   public static KeyBinding autoMana;
	   public static KeyBinding autoMount;
	   public static KeyBinding autoGrapple;
	   public static KeyBinding hotbar0;
	   public static KeyBinding trash;
	   public static KeyBinding drop;
	   public static KeyBinding hotbar1;
	   public static KeyBinding hotbar2;
	   public static KeyBinding hotbar3;
	   public static KeyBinding hotbar4;
	   public static KeyBinding hotbar5;
	   public static KeyBinding hotbar6;
	   public static KeyBinding hotbar7;
	   public static KeyBinding hotbar8;
	   public static KeyBinding hotbar9;
	   public static KeyBinding clearSummons;

	   public static void registerKeys() {
	      try {
	         Field f = GameSettings.class.getDeclaredField(TerrariaMod.DEBUG ? "keyBindInventory" : "field_151445_Q");
	         Util.makeFieldAccessible(f);
	         f.set(Minecraft.getInstance().gameSettings, registerKeybinding(new KeyBinding("key.terrariamod.openvanilla.desc", 78, "key.terrariamod.category")));
	      } catch (Exception var1) {
	         System.exit(1);
	      }

	      openInventory = registerKeybinding(new KeyBinding("key.terrariamod.open.desc", 69, "key.terrariamod.category"));
	      swapHotbars = registerKeybinding(new KeyBinding("key.terrariamod.swap.desc", 77, "key.terrariamod.category"));
	      autoHeal = registerKeybinding(new KeyBinding("key.terrariamod.heal.desc", 72, "key.terrariamod.category"));
	      autoBuff = registerKeybinding(new KeyBinding("key.terrariamod.buff.desc", 66, "key.terrariamod.category"));
	      autoMana = registerKeybinding(new KeyBinding("key.terrariamod.mana.desc", 74, "key.terrariamod.category"));
	      autoMount = registerKeybinding(new KeyBinding("key.terrariamod.mount.desc", 82, "key.terrariamod.category"));
	      autoGrapple = registerKeybinding(new KeyBinding("key.terrariamod.grapple.desc", 71, "key.terrariamod.category"));
	      hotbar0 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarzero.desc", 48, "key.terrariamod.category"));
	      hotbar1 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarone.desc", 49, "key.terrariamod.category"));
	      hotbar2 = registerKeybinding(new KeyBinding("key.terrariamod.hotbartwo.desc", 50, "key.terrariamod.category"));
	      hotbar3 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarthree.desc", 51, "key.terrariamod.category"));
	      hotbar4 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarfour.desc", 52, "key.terrariamod.category"));
	      hotbar5 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarfive.desc", 53, "key.terrariamod.category"));
	      hotbar6 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarsix.desc", 54, "key.terrariamod.category"));
	      hotbar7 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarseven.desc", 55, "key.terrariamod.category"));
	      hotbar8 = registerKeybinding(new KeyBinding("key.terrariamod.hotbareight.desc", 56, "key.terrariamod.category"));
	      hotbar9 = registerKeybinding(new KeyBinding("key.terrariamod.hotbarnine.desc", 57, "key.terrariamod.category"));
	      trash = registerKeybinding(new KeyBinding("key.terrariamod.trash.desc", 340, "key.terrariamod.category"));
	      drop = registerKeybinding(new KeyBinding("key.terrariamod.drop.desc", 81, "key.terrariamod.category"));
	      clearSummons = registerKeybinding(new KeyBinding("key.terrariamod.clearsummons.desc", 75, "key.terrariamod.category"));
	   }

	   private static KeyBinding registerKeybinding(KeyBinding key) {
	      ClientRegistry.registerKeyBinding(key);
	      return key;
	   }
}