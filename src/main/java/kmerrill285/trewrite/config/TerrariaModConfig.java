package kmerrill285.trewrite.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import kmerrill285.trewrite.util.Util;
import net.minecraft.util.ResourceLocation;

public class TerrariaModConfig {

	public static void loadConifg() {
		File file = new File("config/terrariamod.config");
	      if (!file.exists()) {
	         try {
	            file.createNewFile();
	            FileWriter writer = new FileWriter(file);
	            writer.write("stacked_dimensions=false");
	            writer.close();
	         } catch (IOException var17) {
	            var17.printStackTrace();
	         }
	      }
	      
	      try {
	          Scanner scanner = new Scanner(file);
	          ArrayList lines = new ArrayList();

	          while(scanner.hasNext()) {
	             lines.add(scanner.nextLine());
	          }

	          Iterator var3 = lines.iterator();

	          while(true) {
	             String s;
	             do {
	                if (!var3.hasNext()) {
	                   return;
	                }

	                s = (String)var3.next();
	             } while(s.startsWith("#"));
	             	
	             boolean stacked_dimensions = false;
	             String[] s2 = s.split(" ");
	             String[] var11 = s2;
	             int var12 = s2.length;
	             
	             

	             for(int var13 = 0; var13 < var12; ++var13) {
	                String s3 = var11[var13];
	                String[] s4 = s3.split("=");
	                if (s4.length != 0) {
	                   String location;
	                   if (s4[0].equals("stacked_dimensions") && s4.length > 1) {
	                      location = s4[1].replace("\"", "");
	                      stacked_dimensions = Boolean.parseBoolean(new ResourceLocation(location).toString());
	                   }
	                }
	             }
	             
	             if (stacked_dimensions == true) {
	            	 Util.stacked_dimensions = true;
	             }
	             if (stacked_dimensions == false) {
	            	 Util.stacked_dimensions = false;
	             }
	             
	             if (stacked_dimensions != true && stacked_dimensions != false) {
	            	 System.out.println("Terrariamod config file corrupted!");
	             }
	          }
	          
	      } catch (Exception var18) {
	          System.err.println("Could not load config file!");
	          var18.printStackTrace();
	          System.exit(1);
	       }
	    }
}
