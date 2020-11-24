package kmerrill285.trewrite.core.client;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class WidgetBase {

	private static final Map<Class<? extends WidgetBase>, Point> coordinates = new HashMap<>();
	  
	  public int screenWidth;
	  
	  public int screenHeight;
	  
	  public int width;
	  
	  public int height;
	  
	  public float x;
	  
	  public float y;
	  	  
	  protected Runnable remove;
	  
	  public void initWidget(int width, int height) {
	    this.screenWidth = width;
	    this.screenHeight = height;
	    loadCoordinates();
	  }
	  
	  public void drawWidget(float partialTicks, Point mouse) {
	    renderFrame(partialTicks, mouse);
	  }
	  
	  public void renderFrame(float partialTicks, Point mouse) {}
	  
	  public void mouseReleased(int button) {}
	  
	  public void mouseDrag(Point mouse, int button) {}
	  
	  public void mouseClick(Point mouse, int button) {}
	  
	  public void updateWidget() {}
	  
	  public final void saveCoordinates() {
	    Point p = new Point();
	    p.setLocation((this.x * 100000.0F), (this.y * 100000.0F));
	    coordinates.put(getClass(), p);
	  }
	  
	  public final void loadCoordinates() {
	    Point p = coordinates.get(getClass());
	    if (p != null) {
	      this.x = p.x / 100000.0F;
	      this.y = p.y / 100000.0F;
	    } 
	  }
	  
	  public float getActualX() {
	    return this.x * this.screenWidth;
	  }
	  
	  public float getActualY() {
	    return this.y * this.screenHeight;
	  }
	  
}
