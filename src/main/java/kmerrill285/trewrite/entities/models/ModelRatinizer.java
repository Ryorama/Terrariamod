package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.twins.Ratinizer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;

public class ModelRatinizer extends EntityModel<Ratinizer> {
	
	EntityRendererManager render;
	
	ModelRatinizerPhase1 phase1 = new ModelRatinizerPhase1();
	
	ModelRetinazerPhase2 phase2 = new ModelRetinazerPhase2();
	
	 public void render(Ratinizer entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    	  if (Ratinizer.phase == 1) {
	    		  this.phase1.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	    	  if (Ratinizer.phase == 2) {
	    		  this.phase2.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	 }

}
