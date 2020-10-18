package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;

public class EyeOfCthulhuPhaseModel extends EntityModel<EntityEyeOfCthulhu> {
	
	EntityRendererManager render;
	
	ModelEyeOfCthulhuPhase1 phase1 = new ModelEyeOfCthulhuPhase1();
	
	ModelEyeOfCthulhuPhase2 phase2 = new ModelEyeOfCthulhuPhase2();
	
	 public void render(EntityEyeOfCthulhu entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    	  if (EntityEyeOfCthulhu.phase == 1) {
	    		  this.phase1.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	    	  if (EntityEyeOfCthulhu.phase == 2) {
	    		  this.phase2.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	 }

}
