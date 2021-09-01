package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.integrated.IntegratedServer;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	/*
	throws IOException {
	
	File spashTexts = new File(Resources.toString(TerrariaMod.class.getResource("resources/" + TerrariaMod.modid + "/splash_texts.txt"), Charsets.UTF_8));
	System.out.println(spashTexts.getPath());
	*/
	
	/*
	public String randomizeTitleMessage(File f) throws FileNotFoundException
	  {
	    String result = null;
	    Random rand = new Random();
	    int n = 1;
	    for(Scanner sc = new Scanner(f); sc.hasNext();)
	    {
		    ++n;
		    String line = sc.nextLine();
		   	if(rand.nextInt(n) == 0)
		   		result = line;         
	    }
	    return result;      
	}
	*/
}
