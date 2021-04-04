package com.eliotlash.particlelib.particles.components.rate;

import com.eliotlash.particlelib.particles.components.BedrockComponentBase;
import com.eliotlash.particlelib.particles.emitter.BedrockEmitter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.eliotlash.particlelib.particles.components.IComponentParticleRenderBase;
import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.eliotlash.molang.expressions.MolangValue;
import com.eliotlash.mclib.math.Constant;

public class BedrockComponentRateSteady extends BedrockComponentRate implements IComponentParticleRenderBase
{
	public static final MolangExpression DEFAULT_PARTICLES = new MolangValue(null, new Constant(50));

	public MolangExpression spawnRate = MolangParser.ONE;

	public BedrockComponentRateSteady()
	{
		this.particles = DEFAULT_PARTICLES;
	}

	public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException
	{
		if (!elem.isJsonObject()) return super.fromJson(elem, parser);

		JsonObject element = elem.getAsJsonObject();

		if (element.has("spawn_rate")) this.spawnRate = parser.parseJson(element.get("spawn_rate"));
		if (element.has("max_particles")) this.particles = parser.parseJson(element.get("max_particles"));

		return super.fromJson(element, parser);
	}

	@Override
	public JsonElement toJson()
	{
		JsonObject object = new JsonObject();

		if (!MolangExpression.isOne(this.spawnRate)) object.add("spawn_rate", this.spawnRate.toJson());
		if (!MolangExpression.isConstant(this.particles, 50)) object.add("max_particles", this.particles.toJson());

		return object;
	}

	@Override
	public void preRender(BedrockEmitter emitter, float partialTicks)
	{}

	@Override
	public void postRender(BedrockEmitter emitter, float partialTicks)
	{
		if (emitter.playing)
		{
			double particles = emitter.getAge(partialTicks) * this.spawnRate.get();
			double diff = particles - emitter.spawnedParticles;
			double spawn = Math.ceil(diff);

			if (spawn > 0)
			{
				emitter.setEmitterVariables(partialTicks);

				for (int i = 0; i < spawn; i++)
				{
					if (emitter.particles.size() < this.particles.get())
					{
						emitter.spawnParticle();
					}
				}

				emitter.spawnedParticles += spawn;
			}
		}
	}

	@Override
	public int getSortingIndex()
	{
		return 10;
	}
}
