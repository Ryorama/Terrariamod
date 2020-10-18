package kmerrill285.trewrite.core.client;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PlayerSaveData implements Serializable {
   private static final long serialVersionUID = 1L;
   public int hairStyle = 0;
   public Color eyeColor = new Color(0, 0, 0);
   public Color hairColor = new Color(0, 0, 0);
   public Color shirtColor = new Color(0, 0, 0);
   public Color undershirtColor = new Color(0, 0, 0);
   public Color skinColor = new Color(0, 0, 0);
   public Color shoeColor = new Color(0, 0, 0);
   public Color pantsColor = new Color(0, 0, 0);
   public String playername;

   public PlayerSaveData(String playername) {
      this.playername = playername;
   }

   public static void save(PlayerSaveData saveData) {
      try {
         FileOutputStream file = new FileOutputStream("saves/players/" + saveData.playername + ".character");
         ObjectOutputStream oos = new ObjectOutputStream(file);
         oos.writeObject(saveData);
         oos.close();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static PlayerSaveData load(String playername) {
      PlayerSaveData saveData = null;

      try {
         FileInputStream file = new FileInputStream("saves/players/" + playername + ".character");
         ObjectInputStream ois = new ObjectInputStream(file);
         saveData = (PlayerSaveData)ois.readObject();
         ois.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return saveData;
   }
}
