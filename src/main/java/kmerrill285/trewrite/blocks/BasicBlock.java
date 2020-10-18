package kmerrill285.trewrite.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BasicBlock extends BlockT {

	public BasicBlock(Properties properties, float hardness, float difficulty, boolean pick, boolean axe, boolean hammer, boolean material, String name, String drop) {
		super(properties, hardness, difficulty, drop);
		this.pick = pick;
		this.axe = axe;
		this.hammer = hammer;
		this.material = material;
		this.setLocation(name);
	}

	
	public BasicBlock(Properties properties, float hardness, float difficulty, boolean pick, boolean axe, boolean hammer, boolean material, String name, int health, int mana, boolean consumable, String drop) {
		super(properties, hardness, difficulty, drop);
		this.pick = pick;
		this.axe = axe;
		this.hammer = hammer;
		this.material = material;
		this.setLocation(name);
		this.health = health;
		this.mana = mana;
		this.consumable = consumable;
	}
	
	private boolean fullCube = true;
	
	
	public boolean isFullCube(BlockState state) {
	      return fullCube;
	}
	
	public BasicBlock setFullCube(boolean fullCube) {
		this.fullCube = fullCube;
		return this;
	}
	
	
	
}
