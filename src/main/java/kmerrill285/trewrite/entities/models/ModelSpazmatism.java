package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.twins.Spazmatism;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;

public class ModelSpazmatism extends EntityModel<Spazmatism> {
	
	EntityRendererManager render;
	
	ModelSpatmatismPhase1 phase1 = new ModelSpatmatismPhase1();
	
	ModelSpatmatismPhase2 phase2 = new ModelSpatmatismPhase2();
	
	 public void render(Spazmatism entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    	  if (Spazmatism.phase == 1) {
	    		  this.phase1.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	    	  if (Spazmatism.phase == 2) {
	    		  this.phase2.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	 }

}
