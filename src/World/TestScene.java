package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import GeometricObjects.*;
import Light.*;
import Material.*;
import Utility.*;
import Camera.*;
import Tracers.*;
/**
 * A simple test scene
 * @author mmanzi
 *
 */
public class TestScene extends World{

	
	protected void build(Tracer t){
		//define image resolution
		hres = 1000;
		vres = 1000;
		
		//define camera
		camera = new PinholeCamera(new Point3f(0.f, 5.f, 20.f), 		// eye
										new Point3f(0.f, 0.f, 0.f),  // look-at
										new Vector3f(0.f, 1.f, 0.f),	//up
										hres,						//horizontal resolution
										vres,						//vertical resolution
										Math.PI / 4); 						
		
		//define background color
		background_color = new RGBColor(0.5f,0.5f,0.1f);
		
		//define used ray tracer
		rt = new MultipleObjects(this);
		
		//Add objects
		Material redMat = new PhongMaterial(new RGBColor(1f, 0.5f, 0f), new RGBColor(0.5f, 0.5f, 1f), new RGBColor(0f, 1f, 0.5f), 30f);

		Sphere sphere = new Sphere(redMat, new Point3f(0f, 2f, 0.0f), 2f);
		objects.add(sphere);

		Sphere sphere2 = new Sphere(redMat, new Point3f(0f, 2f, -20.0f), 2f);
		objects.add(sphere2);
		
		Sphere sphere3 = new Sphere(redMat, new Point3f(0f, 2f, 10.0f), 2f);
		objects.add(sphere3);
		
		//Material blueMat = new DiffuseMaterial(new RGBColor(0.0f, 0.0f, 1.f));
		//Plane bluePlane = new Plane(blueMat, new Point3f(0.f, 0f,0.f), new Vector3f(0.f, 1.f, 0.f));

//		Rectangle rectangle1 = new Rectangle(redMat, new Point3f(-1.0f, 0.0f, 0.0f), new Vector3f(0.f, 1.f, 0.f), new Vector3f(0.f, 0.f, -1.f), 4.0f, 2.0f);
//		objects.add(rectangle1);
		
//		Cuboid cuboid = new Cuboid(redMat, new Point3f(-1.0f, 0.0f, 0.0f), new Vector3f(1.f, 0.f, 0.f), 4f, 2f, new Vector3f(0.f, 0.f, -1.f), 3f);
//		objects.add(cuboid);
		
//		Rectangle rectangle2 = new Rectangle(redMat, new Point3f(-3.0f, 1.0f, -12.0f), new Vector3f(0.f, 0.f, 1.f), new Vector3f(1.f, 0.f, 0.f), 4.0f, 2.0f);
//		objects.add(rectangle2);
		
		//Add light sources
		//Light light = new MovableLight(new Vector3f(0.f,0.f,-2.5f),new RGBColor(0.2f,0.2f,0.8f));
		//lights.add(light);
		Light lightd = new DirectionalLight(new Vector3f(0.f,1.f,1.f),new RGBColor(0.8f,0.8f,0.8f));
		lights.add(lightd);
	}
}
