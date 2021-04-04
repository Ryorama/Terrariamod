package com.eliotlash.particlelib.particles.components.meta;

import com.eliotlash.particlelib.particles.components.BedrockComponentBase;
import com.eliotlash.particlelib.particles.components.IComponentParticleInitialize;
import com.eliotlash.particlelib.particles.emitter.BedrockEmitter;
import com.eliotlash.particlelib.particles.emitter.BedrockParticle;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;

public class BedrockComponentLocalSpace extends BedrockComponentBase implements IComponentParticleInitialize
{
	public boolean position;
	public boolean rotation;

	public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException
	{
		if (!elem.isJsonObject()) return super.fromJson(elem, parser);

		JsonObject element = elem.getAsJsonObject();

		if (element.has("position")) this.position = element.get("position").getAsBoolean();
		if (element.has("rotation")) this.rotation = element.get("rotation").getAsBoolean();

		return super.fromJson(element, parser);
	}

	@Override
	public JsonElement toJson()
	{
		JsonObject object = new JsonObject();

		if (this.position) object.addProperty("position", true);
		if (this.rotation) object.addProperty("rotation", true);

		return object;
	}

	@Override
	public void apply(BedrockEmitter emitter, BedrockParticle particle)
	{
		particle.relativePosition = this.position;
		particle.relativeRotation = this.rotation;

		particle.setupMatrix(emitter);
	}

	@Override
	public int getSortingIndex()
	{
		return 1000;
	}
}
