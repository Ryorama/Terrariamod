package kmerrill285.trewrite.world.biome.features.LSystem;

import java.awt.Color;
import java.util.Random;

import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class LTree {
		
	public String[] tree = {};
	public BlockState[] growsOn = {};
	public BlockState trunk;
	public BlockState leaves;
	public BlockState fruit;
	public BlockState soil;
	public BlockState top;
	public int iterations;
	public int leafIterations;
	public double leafScale;
	public double treeScale;
	public double leafPercent;
	public double fruitPercent;
	public int vineLength;
	public int sizeX = 80, sizeY = 80, sizeZ = 80;

	/***
	 * <b>If a parameter is nullable, you can set it to null.  You don't have to have a top block, a soil block, etc.</b>
	 * @param treeFunction The function that is used to generate the tree (look at FUNCTIONS)
	 * 
	 * @FUNCTIONS (from String[] treeFunction)
	 * 	@place places the next section of the tree
	 * 	@rotnorth rotates the next section of the tree to the north 30 degrees
	 * 	@roteast rotates the next section of the tree to the east 30 degrees
	 * 	@rotsouth rotates the next section of the tree to the south 30 degrees
	 * 	@rotwest rotates the next section of the tree to the west 30 degrees
	 * 	@rotrand rotates the next section of the tree 30 degrees in a random direction
	 * 	@splitrand creates a branch and rotates it 30 degrees in a random direction
	 * 	@splitnorth creates a branch and rotates it to the north 30 degrees
	 * 	@spliteast creates a branch and rotates it to the north 30 degrees
	 * 	@splitsouth creates a branch and rotates it to the north 30 degrees
	 * 	@splitwest creates a branch and rotates it to the north 30 degrees
	 * 	@shrink shrinks the current section of the tree by 30%
	 * 	@grow grows the current section of the tree by 50%
	 *  @changing_iterations USE: "++" and "--" (increase and decrease iterations by 1)
	 *  @angle USE: "angle:number".  EXAMPLE: "angle:30" <-- Changes the angle of rotation for the tree.
	 *  @skip skips the current section of the tree.  Useful for if you want to make trees with floating parts.
	 *  @end ends the current branch of the tree.  Useful for making trees that look like they've been chopped down.
	 * 	@extra_function_1 "<NUMBER" and ">NUMBER" (only executes the function if a random number between 0 and 99 is less or greater than the input number.  For example: place<75 would only execute if a random number between 0 and 99 is less than 75.
	 * 	@extra_function_2 "#" put this at the start of a function string to make it only execute on the main branch of the tree. Example: {"angle:15", "#rotrand", "place", "angle:45", "splitnorth"}.  rotrand would only execute on the main branch.
	
	 * @EXAMPLE String[] tree = {"splitrand","splitrand","place","place"}; //this generates an evergreen tree.
	 * 
	 * 	@param growsOn The blockstates for blocks the tree is allowed to grow on
	 * 	@param trunk The blockstate that is used for the trunk of the tree
	 * 	@param leaves The blockstate that is used for the leaves
	 * 	@param fruit An optional blockstate for if you want the tree to have fruit growing on it
	 * 	@param soil The type of soil that appears under the tree.  EX: Podzol under trees in a taiga
	 *  @param top The type of block that generates on top of the tree.  EX: If you want snow on top, set it to snow.
	 * 	@param iterations The number of iterations the tree goes through (more iterations = more detail)
	 * 	@param leafIterations How many iterations can be left before leaves start generating (2-4 is usually good)
	 * 	@param leafScale The scale of the leaves (you should probably use something between 2.0 and 3.5)
	 * 	@param treeScale The scale of the tree
	 * 	@param leafPercent Percent of trees that grow with leaves on them (between 0 and 100)
	 */
   public LTree(String[] treeFunction, BlockState[] growsOn, BlockState trunk, BlockState leaves, int iterations, int leafIterations, double leafScale, double treeScale, double leafPercent) {
      this.tree = treeFunction;
      this.growsOn = growsOn;
      this.trunk = trunk;
      this.leaves = leaves;
      this.iterations = iterations;
      this.leafIterations = leafIterations;
      this.leafScale = leafScale;
      this.treeScale = treeScale;
      this.leafPercent = leafPercent;
   }
   
   public LTree setSize(int sizeX, int sizeY, int sizeZ) {
	   this.sizeX = sizeX;
	   this.sizeY = sizeY;
	   this.sizeZ = sizeZ;
	   return this;
   }
   
   public LTree setFruitBlock(BlockState fruit) {
	   this.fruit = fruit;
	   return this;
   }
   
   public LTree setTopBlock(BlockState top) {
	   this.top = top;
	   return this;
   }
   
   public LTree setSoilBlock(BlockState soil) {
	   this.soil = soil;
	   return this;
   }
   
   public LTree setVineLength(int vineLength) {
	   this.vineLength = vineLength;
	   return this;
   }

   public boolean placeInWorld(IWorld worldIn, Random rand, BlockPos pos) {
	   if (growsOn == null) {
		   if (worldIn.getBlockState(pos) != BlocksT.AIR_BLOCK.getDefaultState()) {
	        	 int rad = 2;
	        	 tree(pos, worldIn, rand);
	         } 
	   }
	   for (int i = 0; i < growsOn.length; i++) {
		   if (worldIn.getBlockState(pos) == growsOn[i]) {
	        	 int rad = 2;
	        	 tree(pos, worldIn, rand);
	       } 
	   }
     

      return true;
   }
	   
	   
	   
	   
   public void tree(BlockPos pos, IWorld worldIn, Random r) {
		Random rand = new Random(r.nextLong());
		LSystemPos lpos = new LSystemPos(32, 0, 32);
   	 	
   	 	LSystem stem = new LSystem(lpos, iterations, (rand.nextInt(100) <= leafPercent) ? leafIterations : 0, leafScale, treeScale,tree);
	 	stem.run(rand, iterations);
	 	
	 	boolean swap = rand.nextBoolean();
	 	Color[][][] stuff = stem.stuff;
	 	for (int x = 0; x < sizeX; x++) {
	 		for (int y = 0; y < sizeY; y++) {
	 			for (int z = 0; z < sizeZ; z++) {
	 				{
	 					int X = x;
		 				int Z = z;
		 				if (swap)
		 				{
		 					X = z;
		 					Z = x;
		 				}
		 				BlockPos pos1 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
		 				if (soil != null)
		 				if (worldIn.isAreaLoaded(pos1, 1))
		 					if (worldIn.getBlockState(pos1) != trunk && worldIn.getBlockState(pos1) != leaves && worldIn.getBlockState(pos1) != fruit && worldIn.getBlockState(pos1).isSolid()) {
		 						for(int i = 0; i < 64; i++) {
		 							if (stuff[x][i][z] != null) {
		 								worldIn.setBlockState(pos1, soil, 2);
		 								break;
		 							}
		 						}
		 					}
	 				}
		 			if (stuff[x][y][z] == Color.BLACK) {
		 				int X = x;
		 				int Z = z;
		 				if (swap)
		 				{
		 					X = z;
		 					Z = x;
		 				}
		 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
		 				if (worldIn.isAreaLoaded(pos2, 1))
		 				worldIn.setBlockState(pos2,trunk, 2);
		 			}
		 			if (stuff[x][y][z] == Color.GREEN) {
		 				int X = x;
		 				int Z = z;
		 				if (swap)
		 				{
		 					X = z;
		 					Z = x;
		 				}
		 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
		 				if (worldIn.isAreaLoaded(pos2, 1))
		 				worldIn.setBlockState(pos2, leaves, 2);
		 				
		 				if (worldIn.isAreaLoaded(pos2, 1)) {
		 					worldIn.setBlockState(pos2, leaves, 2);
		 					if (fruit != null && rand.nextInt(100) <= fruitPercent) {
		 						worldIn.setBlockState(pos2, fruit, 2);
		 					}
		 					if (y + 1 < sizeY) {
				 				if (stuff[x][y + 1][z] == null) {
				 					if (worldIn.isAreaLoaded(pos2.up(), 1))
				 						if (top != null)
						 				worldIn.setBlockState(pos2, top, 2);
				 				}
				 			}
		 				}
		 			}
	 			}
	 		}
	 	}
	}
	
	
   
   
}
