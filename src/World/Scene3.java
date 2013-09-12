package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Camera.PinholeCamera;
import GeometricObjects.Cuboid2;
import GeometricObjects.Plane;
import Light.Light;
import Light.MovableLight;
import Material.Material;
import Material.RefractingPhongMaterial;
import Utility.RGBColor;
/**
 * A simple test scene
 * @author mmanzi
 *
 */
public class Scene3 extends World {

	
	protected void build() {
		
		//define image resolution
		hres = 500;
		vres = 500;
		
		//define camera
		camera = new PinholeCamera(new Point3f(0.f, 4.f, 4.f), 		// eye
										new Point3f(0f, 0f, -10.f),  // look-at
										new Vector3f(0.f, 1.f,0.f),	//up
										hres,						//horizontal resolution
										vres,						//vertical resolution
										Math.PI / 4); 						
		
		//define background color
		background_color = new RGBColor(0.f,1f,0.f);
		
		//define used ray tracer
		
		//Add objects


//		Material redMat = new PhongMaterial(new RGBColor(0.5f, 0.5f, 1f), new RGBColor(0.0f, 0.01f, 0.0f), new RGBColor(0f, 1f, 0.5f), 300.f);
		Material redMat = new RefractingPhongMaterial(new RGBColor(1f, 1.f, 1f));
			

	//	Material redMat = new DiffuseMaterial(new RGBColor(1.f, 0.5f, 0f));
	//	Triangle tri = new Triangle(redMat,new Point3f(0f, -1f, -5f),new Point3f(0f, 1f, -5f),new Point3f(2f, -1f, -5f));
		
		//Rectangle2 tri = new Rectangle2(redMat,new Point3f(0f, -1f, -5f),new Point3f(0f, 1f, -5f),new Point3f(2f, -1f, -5f));

		/*
								  f+--------+e
								  /        /|
								 /        / |
							   g+--------+c |
								|        |  |
								| h      |  +b
								|        | /
								|        |/
							   d+--------+a
		 *//*
                      g+--------+f
					  /        /|
					 /        / |
				   c+--------+e |
					|        |  |
					| d      |  +h
					|        | /
					|        |/
				   a+--------+b
				   
				  //Point description
				   Cuboid2(redMat,a,c,b,d);
*/
		
		
		Cuboid2 cub = new Cuboid2(redMat,new Point3f(0f, -1f, -5f),new Point3f(0f, 1f, -5f),new Point3f(2f, -1f, -5f),new Point3f(2f, -1f, -7f));
		//Cuboid thingy = new Cuboid(redMat, new Point3f(-4f, 4f, 0f), new Vector3f(1f, 0f, 0f), 2f, 4f, new Vector3f(0f, 0f, -1f), 6f);
//		Rectangle thingy = new Rectangle(redMat, new Point3f(-1f, 1f, -5f), new Vector3f(0f, 1f, 0f), new Vector3f(0f, 0f, -1f), 2f, 2f);
		//objects.add(thingy);
		objects.add(cub);
	//	Sphere sphere = new Sphere(redMat, new Point3f(0f, 1f, -8f), 0.5f);
		//objects.add(sphere);
		

		//Material blueMat = new DiffuseMaterial(new RGBColor(0.0f, 0.0f, 1.f));
		Material blueMat = new RefractingPhongMaterial(new RGBColor(1f, 1f, 1f));
		Plane blue2Plane = new Plane(blueMat, new Point3f(0.f,-2f,0.f), new Vector3f(0.f, 1.f, 0.f));
		objects.add(blue2Plane);


//		Plane bluePlane = new Plane(blueMat, new Point3f(0.f,2.f,0.f), new Vector3f(0.f, -1.f, 0.f));
		//objects.add(bluePlane);
		
		//Add light sources
//		Light light = new MovableLight(new Point3f(7f,7f,0f),new RGBColor(1f,1f,1f));
//		lights.add(light);
//		Light light2 = new MovableLight(new Point3f(-7f,7f,0f),new RGBColor(0.3f,0.3f,0.3f));
//		lights.add(light2);
		Light light3 = new MovableLight(new Point3f(-2.f, 4.f, -3f),new RGBColor(1f,1f,1f));
		lights.add(light3);
		Light light4 = new MovableLight(new Point3f(2.f, 5.f, -3f),new RGBColor(1f,0f,0f));
		lights.add(light4);
//		Light lightd = new DirectionalLight(new Vector3f(0.f,-1.f,0.f),new RGBColor(0.8f,0.8f,0.8f));
///		lights.add(lightd);
	}
}
