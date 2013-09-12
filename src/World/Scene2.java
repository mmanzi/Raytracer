package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Camera.LensCamera;
import Camera.PinholeCamera;
import GeometricObjects.Plane;
import GeometricObjects.Rectangle2;
import GeometricObjects.Sphere;
import GeometricObjects.Triangle;
import Light.Light;
import Light.MovableLight;
import Material.DiffuseMaterial;
import Material.Material;
import Material.MirrorPhongMaterial;
import Material.PhongMaterial;
import Material.RefractingPhongMaterial;
import Material.RefractingToonMaterial;
import Tracers.Tracer;
import Utility.RGBColor;
/**
 * A simple test scene
 * @author mmanzi
 *
 */
public class Scene2 extends World {

	
	protected void build(Tracer t) {
		
		//define image resolution
		hres = 1000;
		vres = 1000;
		
		//define camera
//		camera = new LensCamera(new Point3f(0.f, 0.f, 14.f), 		// eye
//										new Point3f(0f, 0f, 0.f),  // look-at
//										new Vector3f(0.f, 1.f,0.f),	//up
//										hres,						//horizontal resolution
//										vres,						//vertical resolution
//										Math.PI / 4, 
//										0.3f,						// lens radius
//										20f, 						// focal length
//										1000); 						// rays per pixel
		
		camera = new PinholeCamera(new Point3f(0.f, 0.f, 14.f), 		// eye
				new Point3f(0f, 0f, 0.f),  // look-at
				new Vector3f(0.f, 1.f,0.f),	//up
				hres,						//horizontal resolution
				vres,						//vertical resolution
				Math.PI / 4); 						// rays per pixel
		
		//define background color
		background_color = new RGBColor(.8f,.8f,.8f);
		
		//define background color
		background_color = new RGBColor(0.f,1f,0.f);
		
		//define used ray tracer
		
		//Add objects


//		Material redMat = new PhongMaterial(new RGBColor(0.5f, 0.5f, 1f), new RGBColor(0.0f, 0.01f, 0.0f), new RGBColor(0f, 1f, 0.5f), 300.f);
		Material redMat = new DiffuseMaterial(new RGBColor(1f, 1.f, 1f));
		Material red2Mat = new RefractingPhongMaterial(new RGBColor(0.5f, 0.5f, 1f), new RGBColor(0.0f, 0.01f, 0.0f), new RGBColor(0f, 1f, 0.5f), 300.f,0.0f,t,1.5f);
		
		//Rectangle2 tri = new Rectangle2(redMat,new Point3f(0f, -2f, -10f),new Point3f(0f, 2f, -10f),new Point3f(4f, -2f, -10f));
		Triangle tri = new Triangle(redMat,new Point3f(0f, -2f, -10f),new Point3f(0f, 2f, -10f),new Point3f(4f, -2f, -10f));
		Triangle tri2 = new Triangle(red2Mat,new Point3f(-1f, -1f, -6f),new Point3f(-1f, 1f, -7f),new Point3f(1f, -1f, -8f));
		//Cuboid thingy = new Cuboid(redMat, new Point3f(-4f, 4f, 0f), new Vector3f(1f, 0f, 0f), 2f, 4f, new Vector3f(0f, 0f, -1f), 6f);
 //		Rectangle thingy = new Rectangle(redMat, new Point3f(-1f, 1f, -5f), new Vector3f(0f, 1f, 0f), new Vector3f(0f, 0f, -1f), 2f, 2f);
		//objects.add(thingy);
		objects.add(tri);
		objects.add(tri2);
		Sphere sphere = new Sphere(redMat, new Point3f(1f, 1f, -7f), 0.5f);
		objects.add(sphere);
		

		//Material blueMat = new DiffuseMaterial(new RGBColor(0.0f, 0.0f, 1.f));
		Material blueMat = new DiffuseMaterial(new RGBColor(0.3f, 0.3f, 0.3f));
		Plane blue2Plane = new Plane(blueMat, new Point3f(0.f,-2f,0.f), new Vector3f(0.f, 1.f, 0.f));
		objects.add(blue2Plane);


		Plane bluePlane = new Plane(blueMat, new Point3f(0.f,2.f,0.f), new Vector3f(0.f, -1.f, 0.f));
		//objects.add(bluePlane);
		
		//Add light sources
		Light light3 = new MovableLight(new Point3f(-2.f, 4.f, -3f),new RGBColor(1f,1f,1f));
		lights.add(light3);
		Light light4 = new MovableLight(new Point3f(2.f, 4.f, -3f),new RGBColor(1f,0f,0f));
		lights.add(light4);
	}
}
