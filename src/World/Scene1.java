package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import GeometricObjects.*;
import Light.*;
import Material.*;
import Utility.*;
import Camera.*;
/**
 * A simple test scene
 * @author mmanzi
 *
 */
public class Scene1 extends World{

	
	protected void build(){
		
		//define image resolution
		hres = 1000;
		vres = 1000;
		
		//define camera
		camera = new PinholeCamera(new Point3f(0.f, 0.f, 0.f), 		// eye
										new Point3f(0.f, 0.f, -1.f),  // look-at
										new Vector3f(0.f, 1.f,0.f),	//up
										hres,						//horizontal resolution
										vres,						//vertical resolution
										Math.PI / 4); 						
		
		//define background color
		background_color = new RGBColor(0.1f,0.1f,0.1f);
		
		//define used ray tracer
		
		//Add objects
		//Material mirrorMat = new MirrorPhongMaterial(new RGBColor(0.f, 0.f, 0.f), new RGBColor(0.f, 0.f, 0.f), new RGBColor(0.f, 0.f, 0.f), 300.f,0.5f,t);
//		Material redMat = new RefractingPhongMaterial(new RGBColor(1f, 0.f, 0f), new RGBColor(0.0f, 0.01f, 0.0f), new RGBColor(0f, 1f, 0.5f), 300.f);
			
	//	Material redMat = new DiffuseMaterial(new RGBColor(1.f, 0.5f, 0.5f));
		
		
		
		
		//Sphere sphere2 = new Sphere(redMat, new Point3f(0f, -3.f, -6.5f), 0.5f);
		//objects.add(sphere2);
		
		//Material blueMat = new DiffuseMaterial(new RGBColor(1.0f, 0.5f, 0.5f));
		
		Material blueMat = new RefractingPhongMaterial(new RGBColor(0.5f, 1.f, 0.5f), new RGBColor(0.2f, 0.2f, 0.2f), new RGBColor(1.0f, 0.2f, 1.0f), 300.f,0.0f);
		
		Plane bluePlane = new Plane(blueMat, new Point3f(0.f,0.6f,0.f), new Vector3f(0.f, -1.f, 0.f));
		objects.add(bluePlane);
		
		Plane bluePlane1 = new Plane(blueMat, new Point3f(0.f,-0.6f,0.f), new Vector3f(0.f, 1.f, 0.f));
		objects.add(bluePlane1);
		//Add light sources
		Light light = new MovableLight(new Point3f(0f, 0.2f,-6.f),new RGBColor(1f,1f,1f));
		lights.add(light);
		//Light lightd = new DirectionalLight(new Vector3f(0.f,1.f,0f),new RGBColor(0.8f,0.8f,0.8f));
		//lights.add(lightd);
		RefractingPhongMaterial rpm = new RefractingPhongMaterial(new RGBColor(0.f, 0.f, 0.f), new RGBColor(0.0f, 0.0f, 0.0f), new RGBColor(0.0f, 0.f, 0.0f), 300.f,0.f,1.5f,0.0f);
		Sphere sphere = new Sphere(rpm, new Point3f(0f, -0.f, -6.0f), .5f);
		objects.add(sphere);
		
//		Sphere sphere3 = new Sphere(redMat, new Point3f(0.7f, -0.f, -7.0f), 0.4f);
//		objects.add(sphere3);
	}
}
