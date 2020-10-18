package kmerrill285.trewrite.world.dimension;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.world.gen.SimplexNoiseGenerator;

public class VoronoiTest {
	public int[][] pixels = new int[500][500];
	private final SharedSeedRandom randNoise;
	private final SimplexNoiseGenerator genNoise;

	private JFrame frame;
	private BufferedImage image;
	private Image image2;
	
	public VoronoiTest() {
		this.randNoise = new SharedSeedRandom(new Random().nextLong() + 4L);

        this.genNoise = new SimplexNoiseGenerator(this.randNoise);
        
	}
	
	public VoronoiTest(boolean update) {
	    this.randNoise = new SharedSeedRandom(new Random().nextLong() + 4L);

        this.genNoise = new SimplexNoiseGenerator(this.randNoise);
	      
        frame = new JFrame("island shape tester");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		image = (BufferedImage)frame.createImage(500, 500);
		image2 = frame.createImage(500, 500);
		
		
		while (true) {
			update(50);
		}
	}
	
	 public int voronoiSize = 100;
	
	double xx = 0;
	double yy = 0;
	public void update(int distortion) {
//		xx+=50f;
//		yy+=50f;
		
		voronoiSize = 100;
		for (int x1 = 0; x1 < pixels.length; x1++) {
			for (int y1 = 0; y1 < pixels[0].length; y1++) {
				image.setRGB(x1, y1, 0);
			}
		}
		int B = distortion;
		for (int x1 = 0; x1 < pixels.length; x1++) {
			int x = (int)(x1 + xx);
			
			for (int y1 = 0; y1 < pixels[0].length; y1++) {
				int y = (int)(y1 + yy);
				
				Point p = this.getPointAt(x, y, voronoiSize / 2, 50.0f);
				x -= p.x;
				y -= p.y;
				
				x += p.x;
				y += p.y;
				
				int height = (int)getVoronoiAt(x, y, voronoiSize, 5f);

				double height2 = getVoronoiAt(x, y, voronoiSize / 2, 50.0f) - 25;				
				
				boolean lessn10 = false;
				int count = 0;
				A:
				for (int xx = -1; xx < 2; xx++) {
					for (int yy = -1; yy < 2; yy++) {
						double C = getVoronoiAt(x + xx * B, y + yy * B, voronoiSize / 2, 50.0f) - 25;
						if (C <= -10) {
							count++;
							if (count > 4) {
								lessn10 = true;
								break A;
							}
						}
					}
				}
				
				if (!lessn10)
				if (height2 > -10) {
					int ex = x1;
					int yh = y1;
					
					
					if (ex > 0 && yh > 0 && ex < 500 && yh < 500) {
					pixels[ex][yh] = height;
						if (pixels[ex][yh] > 0) {
							pixels[ex][yh] = 0xff;
							image.setRGB(ex, yh, 0xff);
						}
						else {
							pixels[ex][yh] = 0;
							image.setRGB(ex, yh, 0);
						}
					}
					
				} else {
					pixels[x1][y1] = 0;
					image.setRGB(x1, y1, 0);
				}
				if (x == p.x && y == p.y) {
					image.setRGB(x1, y1, 0xffcc00);
				}
			}
		}
		
		Graphics g = image2.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		g.drawImage(image, 0, 0, 500, 500, null);
		g = frame.getGraphics();
		g.drawImage(image2, 0, 0, frame.getWidth(), frame.getHeight(), null);
		g.dispose();
	}
	   
	   public double getVoronoiAt(int x, int y, int size, double m) {
		   double dist = Integer.MAX_VALUE;
		   double height = 0;
			int w = 3;
			int h = 3;
			
			Point[] ps = new Point[w * h];
			for (int X = 0; X < w; X++) {
				for (int Z = 0; Z < h; Z++) {
					Point p2 = getVoronoiPoint(x + (X - w / 2) * size, y + (Z - h / 2) * size, size);
					ps[X + Z * h] = p2;
				}
			}
			for (Point p : ps) {
				double d = Point.distance(x + this.genNoise.getValue(x / 25.0, y / 25.0) * 10, y + this.genNoise.getValue(y / 25.0, x / 25.0) * 10, p.x, p.y);
				if (d <= dist) {
					dist = d;
					height = getVoronoiHeight(p.x, p.y, size, m);
				}
			}
			return height;
	   }
	   
	   public Point getPointAt(int x, int y, int size, double m) {
		   double dist = Integer.MAX_VALUE;
		   Point pt = new Point(0, 0);
			int w = 3;
			int h = 3;
			
			Point[] ps = new Point[w * h];
			for (int X = 0; X < w; X++) {
				for (int Z = 0; Z < h; Z++) {
					Point p2 = getVoronoiPoint(x + (X - w / 2) * size, y + (Z - h / 2) * size, size);
					ps[X + Z * h] = p2;
				}
			}
			for (Point p : ps) {
				double d = Point.distance(x + this.genNoise.getValue(x / 25.0, y / 25.0) * 10, y + this.genNoise.getValue(y / 25.0, x / 25.0) * 10, p.x, p.y);
				if (d <= dist) {
					dist = d;
					pt = p;
				}
			}
			return pt;
	   }
		
	   public Point getVoronoiPoint(int x, int z, int size) {
		   Point p = new Point((x / size) * size + (size / 2), (z / size) * size + (size / 2));
		   p.x += (int)(this.genNoise.getValue(p.y, p.x) * ((double)size / 2.0));
		   p.y += (int)(this.genNoise.getValue(p.y, p.x) * ((double)size / 2.0));
		   return p;
	   }
	   
	   public double getVoronoiHeight(int x, int z, int size, double m) {
		   return (this.genNoise.getValue(x / size, z / size) * m);
	   }
	
	public static void main(String[] args) {
		new VoronoiTest(true);
		
		
	}
}
