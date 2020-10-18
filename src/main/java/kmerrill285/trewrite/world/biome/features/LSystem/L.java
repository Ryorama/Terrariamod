package kmerrill285.trewrite.world.biome.features.LSystem;

import java.awt.Color;
import java.util.Random;


public class L {
	   public LSystemPos pos = new LSystemPos(0, 0, 0);
	   public boolean xy;
	   public int dir;
	   
	   public boolean main = false;
	   
	  
	   public String[] system;
	   
	   public int last = 0;
	   
	   public LSystem parent;
	   
	   public int iterations = 0;
	   
	   public double size = 1.0f;
	   private Color[][][] stuff;
	   private int SIZEX, SIZEY, SIZEZ;
	   
	   public double angle = 30;
	   
	   public L(LSystemPos pos, LSystem parent, String[] system, int iterations, double size, boolean main, Color[][][] stuff, int SIZEX, int SIZEY, int SIZEZ) {
		   this.pos = pos;
		   this.system = system;
		   this.iterations = iterations;
		   this.parent = parent;
		   this.size = size;
		   this.stuff = stuff;
		   this.SIZEX = SIZEX;
		   this.SIZEY = SIZEY;
		   this.SIZEZ = SIZEZ;
		   this.main = main;
	   }
	   
	   public void runFunction(Random rand) {
		   if (iterations <= 0) return;
		   iterations--;
		   
		   for (String function : system) {
			   run(function, rand);
		   }
	   }
	   
	   private void run(String function, Random rand) {
		  
		   if (function.contains("<")) {
			   if (function.split("<").length > 0) {
				   int r = rand.nextInt(100);
				   if (r >= Integer.parseInt(function.split("<")[1])) {
					   return;
				   }
				   function = function.split("<")[0];
			   }
		   }
		   if (function.contains(">")) {
			   if (function.split(">").length > 0) {
				   int r = rand.nextInt(100);
				   if (r <= Integer.parseInt(function.split(">")[1])) {
					   return;
				   }
				   function = function.split(">")[0];
			   }
		   }
		   
		   if (function.contentEquals("place")) place(rand);
		   
		   if (function.contentEquals("turnright")) rot3(1);
		   if (function.contentEquals("turnleft")) rot3(-1);
		   if (function.contentEquals("roteast")) rot(1);
		   if (function.contentEquals("rotwest")) rot(-1);
		   if (function.contentEquals("rotnorth")) rot2(1);
		   if (function.contentEquals("rotsouth")) rot2(-1);
		   
		   if (function.contentEquals("flip")) {
			   pos.rotationXZ += 180;
		   }
		   if (function.contentEquals("rotrand")) {
			   if (rand.nextBoolean()) {
				   if (rand.nextBoolean()) pos.rotationXY += angle * (rand.nextInt(3) - 1);
				   else pos.rotationXZ += angle * (rand.nextInt(3) - 1);
			   }
			   }
		   
		   
		   if (function.contentEquals("--")) iterations--;
		   if (function.contentEquals("++")) iterations++;
		   if (function.contentEquals("spliteast")) splitRight(rand);
		   if (function.contentEquals("splitwest")) splitLeft(rand);
		   if (function.contentEquals("splitnorth")) splitNorth(rand);
		   if (function.contentEquals("splitsouth")) splitSouth(rand);
		   if (function.contentEquals("casesplit"))if (rand.nextBoolean()) splitRand(rand);
		   if (function.contentEquals("splitrand")) splitRand(rand);
		   if (function.contentEquals("splitrand2d")) splitRand2d(rand);
		   if (function.contentEquals("end")) iterations = 0;
		   if (function.startsWith("#")) {
			   function = function.replace("#","");
			   if (!main)return;
		   }
		   if (function.startsWith("angle:")) {
			   String[] f = function.split(":");
			   int a = Integer.parseInt(f[1]);
			   this.angle = a;
		   }
		   if (function.contentEquals("skip")) {
			   move();
		   }

		   if (true) {
			   if (function.contentEquals("shrink")) {
				   size *=0.7;
			   }
			   if (function.contentEquals("grow")) {
				   size *=1.3;
			   }
		   }
		   
	   }
	   
	   public void splitRand2d(Random rand) {
		   if (rand.nextBoolean())
			   splitLeft(rand);
		   else
			   splitRight(rand);
	   }
	   public void splitRand(Random rand) {
//		   splitTrueRand(rand);
		   if (rand.nextBoolean()) {
			   if (rand.nextBoolean())
				   splitLeft(rand);
			   else
				   splitRight(rand);
		   } else {
			   if (rand.nextBoolean())
				   splitNorth(rand);
			   else
				   splitSouth(rand);
		   }
		   
	   }
	  double sizeDown = 0.8;
	   public void splitTrueRand(Random rand) {
		   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations+1, size*sizeDown,false,stuff,SIZEX,SIZEY,SIZEZ);
		   l.pos.rotationXY = pos.rotationXY;
		   l.pos.rotationXZ = pos.rotationXZ;
		   l.pos.rotationZY = pos.rotationZY;
		   l.angle = angle;
		   if (rand.nextBoolean())
			   l.pos.rotationXY += angle;
//		   else
//			   l.pos.rotationZY += (rand.nextInt(2) * 2 - 1) * angle;
		   l.pos.rotationXZ += (rand.nextInt(360)) * angle;
		   l.place(rand);
		   parent.system.add(l);
//		   iterations--;
	   }
	   
	   public void splitNorth(Random rand) {
		   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations+1, size*sizeDown,false,stuff,SIZEX,SIZEY,SIZEZ);
		   l.pos.rotationXY = pos.rotationXY;
		   l.pos.rotationXZ = pos.rotationXZ;
		   l.pos.rotationZY = pos.rotationZY - angle;
		   l.angle = angle;
		   l.place(rand);
		   parent.system.add(l);
//		   iterations--;
	   }
	   
	   public void splitSouth(Random rand) {
		   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations+1, size*sizeDown,false,stuff,SIZEX,SIZEY,SIZEZ);
		   l.pos.rotationXY = pos.rotationXY;
		   l.pos.rotationXZ = pos.rotationXZ;
		   l.pos.rotationZY = pos.rotationZY + angle;
		   l.angle = angle;
		   l.place(rand);
		   parent.system.add(l);
//		   iterations--;
	   }
	   
	   public void splitLeft(Random rand) {
		   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations+1, size*sizeDown,false,stuff,SIZEX,SIZEY,SIZEZ);
		   l.pos.rotationXY = pos.rotationXY - angle;
		   l.pos.rotationXZ = pos.rotationXZ;
		   l.pos.rotationZY = pos.rotationZY;
		   l.angle = angle;
		   l.place(rand);
		   parent.system.add(l);
//		   iterations--;
	   }
	   
	   public void splitRight(Random rand) {
		   L l = new L(new LSystemPos(pos.getX() + 0,pos.getY() + 0, pos.getZ() + 0), parent, system, iterations+1, size*sizeDown,false,stuff,SIZEX,SIZEY,SIZEZ);
		   l.pos.rotationXY = pos.rotationXY + angle;
		   l.pos.rotationXZ = pos.rotationXZ;
		   l.pos.rotationZY = pos.rotationZY;
		   l.angle = angle;
		   l.place(rand);
		   parent.system.add(l);
//		   iterations--;
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
//		   y = (SIZE-1) - y;
		   
		   for (int xx = (int)(- size); xx < size; xx++) {
			   for (int zz = (int)(- size); zz < size; zz++) {
				   for (int yy = (int)(-size); yy < size; yy++) {
					   double dist = Math.sqrt(xx * xx + zz * zz);
//					   double dist2 = Math.sqrt(xx * xx + zz * zz);
					   if (dist <= size) {
						   placeWood(xx + x, y + yy, zz + z, rand);
					   }
				   }
				   
			   }
		   }
		   
//		   worldIn.setBlockState(pos, BlocksT.ICE.getDefaultState(), 2);
		   move();
	   }
	   
	   public void placeWood(int x, int y, int z, Random rand) {
		   if (x >= 0 && y >= 0 && z >= 0 && x < SIZEX && y < SIZEY && z < SIZEZ) {
			   stuff[x][y][z] = Color.black;
			   if (iterations < parent.leaves) {
				   double size = parent.leafSize * this.size;
				   for (int xx = (int)(- size); xx < size; xx++) {
					   for (int zz = (int)(- size); zz < size; zz++) {
						   for (int yy = (int)(-size); yy < size; yy++) {
							   double dist = Math.sqrt(xx * xx + zz * zz + yy * yy);
//								   double dist2 = Math.sqrt(xx * xx + zz * zz);
							   if (dist <= size) {
								   if (rand.nextInt(10)<=3)
								   placeLeaf(xx + x, y + yy, zz + z);
							   }
						   }
						   
					   }
				   }
				   
//					   placeLeaf(x + rand.nextInt(3) - 1, y + rand.nextInt(3) - 1, z + rand.nextInt(3) - 1);
				   
			   }
		   }
	   }
	   
	   public void placeLeaf(int x, int y, int z) {
		   if (x >= 0 && y >= 0 && z >= 0 && x < SIZEX && y < SIZEY && z < SIZEZ) {
			   if (stuff[x][y][z] == null)
			   stuff[x][y][z] = Color.green;
		   }
	   }
	   
	   public void move() {
		   pos = pos.forwards(size);
	   }
	   public void rot(int rot) {
		   pos.rotationXY -= angle * rot;
	   }
	   public void rot2(int rot) {
		   pos.rotationZY -= angle * rot;
	   }
	   public void rot3(int rot) {
		   pos.rotationXZ -= angle * rot;
	   }
}