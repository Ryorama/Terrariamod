package kmerrill285.trewrite.world.biome.features.mesa;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.LSystem.LSystemPos;
import kmerrill285.trewrite.world.biome.features.LSystem.LSystemVec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MesaShrubFeature extends Feature<NoFeatureConfig> {
	
		
	
	   public MesaShrubFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		   if (rand.nextInt(25) <= 20)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.getBlockState(blockpos.down()) == BlocksT.RED_SAND.getDefaultState()) {
	        	 int rad = 2;
	        	 tree(pos, worldIn, rand);
	        	 
	        	 break;
	         }
	      }

	      return true;
	   }
	   
	   Color[][][] stuff = new Color[64][64][64];
	   
	   public void tree(BlockPos pos, IWorld worldIn, Random r) {
		   stuff = new Color[64][64][64];
			Random rand = new Random(r.nextLong());
			LSystemPos lpos = new LSystemPos(32, 0, 32);
			int height = rand.nextInt(2);
			for (int y = 0; y < height; y++) {
				stuff[32][y][32] = Color.BLACK;
			}
			
			lpos.y += height;
			
			LSystem system = new LSystem(lpos, 2, true, 0.5, "splitrand", "place");
	   	 	system.run(rand, 5);
//	   	 	LSystem stem = new LSystem(system.system.get(system.system.size() - 1).pos, 4, true, "place", "place", "splitrand");
//		 	stem.run(rand, 10); 
		 	
		 	boolean swap = rand.nextBoolean();
		 	
		 	for (int x = 0; x < 64; x++) {
		 		for (int y = 0; y < 64; y++) {
		 			for (int z = 0; z < 64; z++) {
			 			if (stuff[x][y][z] == Color.BLACK) {
			 				int X = x;
			 				int Z = z;
			 				if (swap)
			 				{
			 					X = z;
			 					Z = x;
			 				}
			 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 1, pos.getZ() + Z - 32);
			 				if (worldIn.isAreaLoaded(pos2, 1))
			 				worldIn.setBlockState(pos2, BlocksT.LIVING_WOOD.getDefaultState(), 2);
			 			}
			 			if (stuff[x][y][z] == Color.GREEN) {
			 				int X = x;
			 				int Z = z;
			 				if (swap)
			 				{
			 					X = z;
			 					Z = x;
			 				}
			 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 1, pos.getZ() + Z - 32);
			 				if (worldIn.isAreaLoaded(pos2, 1))
			 				worldIn.setBlockState(pos2, BlocksT.LEAVES.getDefaultState(), 2);
			 			}
		 			}
		 		}
		 	}
		}
		
		

		   private class LSystem {
			   public ArrayList<L> system = new ArrayList<L>();
			   
			   public boolean leaves;
			   
			   public LSystem(LSystemPos pos, int maxIterations, boolean leaves, double size, String...system) {
				   this.system.add(new L(pos, this, system, maxIterations, size, true));
				   this.leaves = leaves;
			   }
			   
			   public void run(Random rand, int iterations) {
				   
				   for (int i = 0; i < iterations; i++) {
					   int A = system.size();
					   for (int a = 0; a < A; a++) {
						   system.get(a).runFunction(rand);
					   }
				   }
			   }
		   }
		   
		   private class L {
			   public LSystemPos pos = new LSystemPos(0, 0, 0);
			   public boolean xy;
			   public int dir;
			   
			   public boolean main = false;
			   
			   //UP, RIGHT, DOWN, LEFT
			   public LSystemVec[] FACES = new LSystemVec[]{new LSystemVec(0, 1, 0), new LSystemVec(1, 0, 0), new LSystemVec(0, -1, 0), new LSystemVec(-1, 0, 0)};
			   
			   public String[] system;
			   
			   public int last = 0;
			   
			   public LSystem parent;
			   
			   public int iterations = 0;
			   
			   public double size = 1.0f;
			   
			   
			   public L(LSystemPos pos, LSystem parent, String[] system, int iterations, double size, boolean main, LSystemVec...vecs) {
				   this.pos = pos;
				   if (vecs.length == 4) FACES = vecs;
				   this.system = system;
				   this.iterations = iterations;
				   this.parent = parent;
				   xy = new Random().nextBoolean();
				   this.size = size;
			   }
			   
			   public void runFunction(Random rand) {
				   if (iterations <= 0) return;
				   if (main) iterations++;
				   else
					   iterations--;
				   
				   for (String function : system) {
					   run(function, rand);
				   }
			   }
			   
			   private void run(String function, Random rand) {
				  
				   if (function.contentEquals("place")) place(rand);
				   if (function.contentEquals("up")) move(0); 
				   if (function.contentEquals("down")) move(2);
				   if (function.contentEquals("left")) move(3);
				   if (function.contentEquals("right")) move(1);
				   if (function.contentEquals("rotright")) rot(1);
				   if (function.contentEquals("rotleft")) rot(-1);
				   if (function.contentEquals("splitright")) splitRight(rand);
				   if (function.contentEquals("splitleft")) splitLeft(rand);
				   if (function.contentEquals("splitrandzy")) if(rand.nextBoolean() == true) splitRight(rand); else splitLeft(rand);
				   if (function.contentEquals("splitrandxy")) if(rand.nextBoolean() == true) splitFront(rand); else splitBack(rand);
				   if (function.contentEquals("splitrand")) if(rand.nextBoolean() == true) { if (rand.nextBoolean()) splitFront(rand); else splitBack(rand); } else { if (rand.nextBoolean()) splitLeft(rand); else splitRight(rand); };

			   }
			  
			   public void splitFront(Random rand) {
				   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations + 1, size * 0.5,false, cloneVecArray(FACES));
				   l.rot(-1);
				   l.place(rand);
				   l.xy = true;
				   parent.system.add(l);
			   }
			   
			   public void splitBack(Random rand) {
				   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations + 1, size * 0.5,false, cloneVecArray(FACES));
				   l.rot(1);
				   l.place(rand);
				   l.xy = true;
				   parent.system.add(l);
			   }
			   
			   
			   public void splitLeft(Random rand) {
				   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations + 1, size * 0.5,false, cloneVecArray(FACES));
				   l.rot(-1);
				   l.place(rand);
				   l.xy = false;
				   parent.system.add(l);
			   }
			   
			   public void splitRight(Random rand) {
				   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations + 1, size * 0.5,false, cloneVecArray(FACES));
				   l.rot(1);
				   l.place(rand);
				   l.xy = false;
				   parent.system.add(l);
			   }
			   
			   public LSystemVec[] cloneVecArray(LSystemVec[] a) {
				   LSystemVec[] b = new LSystemVec[a.length];
				   for (int i = 0; i < a.length; i++) {
					   b[i] = cloneVec(a[i]);
				   }
				   return b;
			   }
			   
			   public LSystemVec cloneVec(LSystemVec a) {
				   return new LSystemVec(a.getX() + 0, a.getY() + 0, a.getZ() + 0);
			   }
			   
			   public void place(Random rand) {
				   int x = (int)pos.getX();
				   int y = (int)pos.getY();
				   int z = (int)pos.getZ();
				   
				   if (x >= 0 && y >= 0 && z >= 0 && x < 64 && y < 64 && z < 64) {
					   
					   stuff[x][y][z] = Color.BLACK;
					   if (iterations == 0) {
						   if (parent.leaves) {
							   placeLeaf(x + rand.nextInt(3) - 1, y + rand.nextInt(3) - 1, z + rand.nextInt(3) - 1);
							   placeLeaf(x + rand.nextInt(3) - 1, y + rand.nextInt(3) - 1, z + rand.nextInt(3) - 1);
							   placeLeaf(x + rand.nextInt(3) - 1, y + rand.nextInt(3) - 1, z + rand.nextInt(3) - 1);
							   placeLeaf(x + rand.nextInt(3) - 1, y + rand.nextInt(3) - 1, z + rand.nextInt(3) - 1);
						   }
					   }
				   }
//				   worldIn.setBlockState(pos, BlocksT.ICE.getDefaultState(), 2);
				   move(last);
			   }
			   
			   public void placeLeaf(int x, int y, int z) {
				   if (x >= 0 && y >= 0 && z >= 0 && x < 64 && y < 64 && z < 64) {
					   stuff[x][y][z] = Color.green;
				   }
			   }
			   
			   public void move(int face) {
				   pos = new LSystemPos(pos.getX() + FACES[face].getX() * size * (xy ? 1 : 0), pos.getY() + FACES[face].getY() * size, pos.getZ() + FACES[face].getX() * size * (xy ? 0 : 1));
				   last = face;
			   }
			   public void rot(int rot) {
				   if (rot == -1) {
					   for (int i = 0; i < this.FACES.length; i++) {
						   
						   if (this.FACES[i].x == -1 && this.FACES[i].y == 0)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y - 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == -1 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y - 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 0 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x - 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x - 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == 0)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y + 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y + 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 0 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x + 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == -1 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x + 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   
					   }
				   }
				   if (rot == 1) {
					   for (int i = 0; i < this.FACES.length; i++) {
						   if (this.FACES[i].x == -1 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y + 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == -1 && this.FACES[i].y == 0)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y + 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == -1 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x + 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 0 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x + 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == 1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y - 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == 0)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x, this.FACES[i].y - 1, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 1 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x - 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
						   if (this.FACES[i].x == 0 && this.FACES[i].y == -1)
						   {
							   this.FACES[i] = new LSystemVec(this.FACES[i].x - 1, this.FACES[i].y, this.FACES[i].z);
							   continue;
						   }
					   }
				   }
			   }
		   }
	   
	   
	}
