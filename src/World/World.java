package World;

import java.util.Iterator;
import java.util.LinkedList;

import Camera.*;
import GeometricObjects.*;
import Light.*;
import Tracers.*;
import Utility.*;

/**
 * Representation of the scene. The whole scene setup (eg. camera setup, geometry, light-sources is defined here)
 * @author manzi
 *
 */
public abstract class World {

	int hres;							//horizontal resolution
	int vres;							//vertical resolution
	RGBColor background_color;			//color of the background (returned color for a ray who intersects nothing)
	Tracer rt;							//the ray-tracer that is used to render the scene
	LinkedList<AbstractGeometricObject> objects;//a list of all objects in the scene
	LinkedList<Light> lights;			//a list of all light-sources in the scene
	Camera camera;						//the camera from which one we see the scene, the camera also generates the rays
	
	/**
	 * This abstract method should be used in subclasses to setup the scene!
	 */
	protected abstract void build();
	
	/**
	 * constructor, called by main-method in Run. 
	 * Note that only the resolution must be passed, all the rest is done in the build() method
	 */
	public World(){
		rt=new MultipleObjects(this);
		objects = new LinkedList<AbstractGeometricObject>();
		lights = new LinkedList<Light>();
		build();
	}
	
	/**
	 * Calls the user-defined camera to render the scene using the user-defined tracer 
	 * @return the rendered image
	 */
	public RGBColor[][] render_scene(){
		RGBColor[][] img = new RGBColor[hres][vres];
		if(camera!=null)
			return camera.renderScene(img, rt);
		return img;
	}
	
	public RGBColor renderPixel(int x,int y) {
		RGBColor rgbColor = new RGBColor();
		if(camera!=null)
			rgbColor = camera.renderPixel(x, y, rt);
		return rgbColor;
	}
	
	/**
	 * getter functions for different world content
	 */
	public Iterator<AbstractGeometricObject> getObjectIterator(){
		return objects.iterator();
	}	
	public Iterator<Light> getLightIterator(){
		return lights.iterator();
	}
	public RGBColor getBackgroundColor(){
		return background_color;
	}
}
