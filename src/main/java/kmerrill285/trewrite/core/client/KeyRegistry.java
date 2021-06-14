package kmerrill285.trewrite.core.client;

import java.lang.reflect.Field;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.util.Util;
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
   public static KeyBinding favorite_item;
   public static KeyBinding autoEquip;

   public static void registerKeys() {
      try {
         Field f = GameSettings.class.getDeclaredField(Trewrite.DEBUG ? "keyBindInventory" : "field_151445_Q");
         Util.makeFieldAccessible(f);
         f.set(Minecraft.getInstance().gameSettings, registerKeybinding(new KeyBinding("key.trewrite.openvanilla.desc", 78, "key.trewrite.category")));
      } catch (Exception var1) {
         System.exit(1);
      }

      openInventory = registerKeybinding(new KeyBinding("key.trewrite.open.desc", 69, "key.trewrite.category"));
      swapHotbars = registerKeybinding(new KeyBinding("key.trewrite.swap.desc", 77, "key.trewrite.category"));
      autoHeal = registerKeybinding(new KeyBinding("key.trewrite.heal.desc", 72, "key.trewrite.category"));
      autoBuff = registerKeybinding(new KeyBinding("key.trewrite.buff.desc", 66, "key.trewrite.category"));
      autoMana = registerKeybinding(new KeyBinding("key.trewrite.mana.desc", 74, "key.trewrite.category"));
      autoMount = registerKeybinding(new KeyBinding("key.trewrite.mount.desc", 82, "key.trewrite.category"));
      autoGrapple = registerKeybinding(new KeyBinding("key.trewrite.grapple.desc", 71, "key.trewrite.category"));
      hotbar0 = registerKeybinding(new KeyBinding("key.trewrite.hotbarzero.desc", 48, "key.trewrite.category"));
      hotbar1 = registerKeybinding(new KeyBinding("key.trewrite.hotbarone.desc", 49, "key.trewrite.category"));
      hotbar2 = registerKeybinding(new KeyBinding("key.trewrite.hotbartwo.desc", 50, "key.trewrite.category"));
      hotbar3 = registerKeybinding(new KeyBinding("key.trewrite.hotbarthree.desc", 51, "key.trewrite.category"));
      hotbar4 = registerKeybinding(new KeyBinding("key.trewrite.hotbarfour.desc", 52, "key.trewrite.category"));
      hotbar5 = registerKeybinding(new KeyBinding("key.trewrite.hotbarfive.desc", 53, "key.trewrite.category"));
      hotbar6 = registerKeybinding(new KeyBinding("key.trewrite.hotbarsix.desc", 54, "key.trewrite.category"));
      hotbar7 = registerKeybinding(new KeyBinding("key.trewrite.hotbarseven.desc", 55, "key.trewrite.category"));
      hotbar8 = registerKeybinding(new KeyBinding("key.trewrite.hotbareight.desc", 56, "key.trewrite.category"));
      hotbar9 = registerKeybinding(new KeyBinding("key.trewrite.hotbarnine.desc", 57, "key.trewrite.category"));
      trash = registerKeybinding(new KeyBinding("key.trewrite.trash.desc", 340, "key.trewrite.category"));
      drop = registerKeybinding(new KeyBinding("key.trewrite.drop.desc", 81, "key.trewrite.category"));
      clearSummons = registerKeybinding(new KeyBinding("key.trewrite.clearsummons.desc", 75, "key.trewrite.category"));
      favorite_item = registerKeybinding(new KeyBinding("key.trewrite.favorite_item.desc", 342, "key.trewrite.category"));
      autoEquip = registerKeybinding(new KeyBinding("key.trewrite.auto_equip.desc", 341, "key.trewrite.category"));
   }

   private static KeyBinding registerKeybinding(KeyBinding key) {
      ClientRegistry.registerKeyBinding(key);
      return key;
   }
}
