package com.ryorama.terrariamod.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.items.ItemT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.util.Conversions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockT extends Block {

	public int buy, sell;
	public float difficulty = 1;
	public boolean pick, axe, hammer;
	public boolean material;
	public boolean harvest = true;
	public boolean consumable;
	
	public int potionSickness;
	
	public int health, mana;
	
	public String tooltip = "";
	
	public String drop;
	
	public String name;
	public String shape = "";
	
	protected ArrayList<String> allowed = new ArrayList<String>();

	public BlockT addAllowed(String... block) {
		for (String b : block)
				this.allowed.add(b);
		return this;
	}
	
	public static boolean test(Block block) {
		return BlocksT.BLOCKS.containsValue(block);
	}
	
	public BlockT(Properties properties, float hardness, float difficulty, String drop) {
		super(properties.hardnessAndResistance(hardness * 0.03f));
		this.difficulty = difficulty;
		this.drop = drop;
//		Block b;
//		b.onBlockHarvested(worldIn, pos, state, player);
	}
	
	public static final IntegerProperty light = IntegerProperty.create("lightlevel", 0, 15);
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
        builder.add(light);
    }
	
	public int getLightValue(BlockState state) {
		if (state.getBlock() instanceof BlockT && state != null && state.getBlock() != null)
			if (state.has(light))
		return Math.max(state.get(BlockT.light).intValue(), super.getLightValue(state));
		return super.getLightValue(state);
	}
	
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getBlock() instanceof BlockT && state != null && state.getBlock() != null)
			if (state.has(light))
		return Math.max(state.get(BlockT.light).intValue(), super.getLightValue(state, world, pos));
		return super.getLightValue(state, world, pos);
	}
	
	
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		
	}
	
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return true;
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return super.getShape(state, worldIn, pos, context);
	   }
	
 
	 public BlockT setShape(String shape) {
		   this.shape = shape;
		   return this;
		   
	 }
	
	public BlockT setConsumable() {
		this.consumable = true;
		return this;
	}
	
	public BlockT setPotionSickness(int n) {
		this.potionSickness = n;
		return this;
	}
	
	public float getMiningSpeed(ItemT item) {
		if (pick && item.pick >= difficulty) {
			return item.pick / difficulty;
		}
		
		if (axe && item.axe >= difficulty) {
			return item.axe / difficulty;
		}
		
		if (hammer && item.hammer >= difficulty) {
			return item.hammer / difficulty;
		}
		
		return -1;
	}
	
	public boolean canSupport(String name) {
	     return allowed.contains(name);
	}
	
	public BlockT setLocation(String name) {
		this.name = name;
		this.setRegistryName(new ResourceLocation("trewrite", name));
		BlocksT.BLOCKS.put(this.name, this);
		return this;
	}
	
	public BlockT setSell(int sell) {
		this.sell = sell;
		this.buy = Conversions.sellToBuy(sell);
		return this;
	}
	
	public BlockT setBuy(int buy) {
		this.sell = Conversions.buyToSell(buy);
		this.buy = buy;
		return this;
	}
	
	public BlockT setMaterial() {
		this.material = true;
		return this;
	}
	
	public BlockT setTooltip(String tooltip) {
		this.tooltip = tooltip;
		return this;
	}
}