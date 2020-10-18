package kmerrill285.trewrite.config;

import java.util.ArrayList;
import java.util.Iterator;

public class TerrariamodConfigLoader {

	public static ArrayList configs = new ArrayList();
	
	public static TerrariaModConfiguration loadConfig(boolean location) {
			
		Iterator var1 = configs.iterator();
		
		TerrariaModConfiguration config;

		do {
	         if (!var1.hasNext()) {
	            return null;
	         }

	         config = (TerrariaModConfiguration)var1.next();
	      } while(!config.stacked_dimensions == location);

	      return config;
	   }
}
