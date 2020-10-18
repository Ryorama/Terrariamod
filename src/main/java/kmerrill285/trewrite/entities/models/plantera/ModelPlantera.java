package kmerrill285.trewrite.entities.models.plantera;

import kmerrill285.trewrite.entities.models.ModelEyeOfCthulhuPhase1;
import kmerrill285.trewrite.entities.models.ModelEyeOfCthulhuPhase2;
import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import kmerrill285.trewrite.entities.monsters.bosses.Plantera;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;

public class ModelPlantera extends EntityModel<Plantera> {
	
	EntityRendererManager render;
	
	ModelPlanteraPhase1 phase1 = new ModelPlanteraPhase1();
	
	ModelPlanteraPhase2 phase2 = new ModelPlanteraPhase2();
	
	 public void render(Plantera entity, float f, float f1, float f2, float f3, float f4, float f5) {
	    	  if (Plantera.phase == 1) {
	    		  this.phase1.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	    	  if (Plantera.phase == 2) {
	    		  this.phase2.render(entity, f, f1, f2, f3, f4, f5);
	    	  }
	 }

}
