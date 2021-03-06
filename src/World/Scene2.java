package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Camera.LensCamera;
import GeometricObjects.Plane;
import GeometricObjects.Sphere;
import Light.Light;
import Light.MovableLight;
import Material.Material;
import Material.ProceduralRefractingPhongMaterial;
import Material.RefractingPhongMaterial;
import ProceduralTexture.ProceduralTexture;
import Utility.RGBColor;
/**
 * A simple test scene
 * @author mmanzi
 *
 */
public class Scene2 extends World {

	
	protected void build() {
		
		//define image resolution
		hres = 1000;
		vres = 1000;
		
		//define camera
		camera = new LensCamera(new Point3f(0.f, 0.f, 14.f), 		// eye
										new Point3f(1f, 1f, -7.f),  // look-at
										new Vector3f(0.f, 1.f,0.f),	//up
										hres,						//horizontal resolution
										vres,						//vertical resolution
										Math.PI / 4, 
										0.3f,						// lens radius
										20.2f, 						// focal length
										100); 						// rays per pixel
		
//		camera = new PinholeCamera(new Point3f(0.f, 0.f, 14.f), 		// eye
//				new Point3f(0f, 0f, 0.f),  // look-at
//				new Vector3f(0.f, 1.f,0.f),	//up
//				hres,						//horizontal resolution
//				vres,						//vertical resolution
//				Math.PI / 4); 						// rays per pixel
//		
		//define background color
		background_color = new RGBColor(0.3f,0.3f,0.3f);


//		Material redMat = new PhongMaterial(new RGBColor(0.5f, 0.5f, 1f), new RGBColor(0.0f, 0.01f, 0.0f), new RGBColor(0f, 1f, 0.5f), 300.f);
//		Material redMat = new RefractingPhongMaterial(new RGBColor(1f, 1.f, 1f));
//		Material red2Mat = new RefractingPhongMaterial(new RGBColor(0.f, 0.f, 0.f), new RGBColor(0.0f, 0.0f, 0.0f), new RGBColor(0f, 0.f, 0.f), 300.f,0.0f,1f,0.0f);
		Material redparamMat = new ProceduralRefractingPhongMaterial(new RGBColor(0.f, 0.f, 0.f), new RGBColor(0.0f, 0.0f, 0.0f), new RGBColor(0f, 0.f, 0.f), 300.f,0.0f,1f,0.0f,new ProceduralTexture(){
			  public RGBColor getColor(RGBColor g, Vector3f v){
				 				return Math.floor(v.x*4)%2==0?new RGBColor(0f,0f,0f):new RGBColor(0.5f,.5f,.5f);
				 				}
		});
		
		//Rectangle2 tri = new Rectangle2(redMat,new Point3f(0f, -2f, -10f),new Point3f(0f, 2f, -10f),new Point3f(4f, -2f, -10f));
//		Triangle tri = new Triangle(redMat,new Point3f(0f, -2f, -10f),new Point3f(0f, 2f, -10f),new Point3f(4f, -2f, -10f));
//		Triangle tri2 = new Triangle(red2Mat,new Point3f(-1f, -1f, -4f),new Point3f(-1f, 1f, -4.1f),new Point3f(1f, -1f, -4f));
		//Cuboid thingy = new Cuboid(redMat, new Point3f(-4f, 4f, 0f), new Vector3f(1f, 0f, 0f), 2f, 4f, new Vector3f(0f, 0f, -1f), 6f);
 //		Rectangle thingy = new Rectangle(redMat, new Point3f(-1f, 1f, -5f), new Vector3f(0f, 1f, 0f), new Vector3f(0f, 0f, -1f), 2f, 2f);
		//objects.add(thingy);
//		objects.add(tri);
//		objects.add(tri2);
		Sphere sphere = new Sphere(redparamMat, new Point3f(1f, 1f, -7f), 2f);
		objects.add(sphere);
		

		//Material blueMat = new DiffuseMaterial(new RGBColor(0.0f, 0.0f, 1.f));
		Material blueMat = new RefractingPhongMaterial(new RGBColor(0.f, 0.f, 1f), new RGBColor(0.0f, 0.0f, 0.0f), new RGBColor(0f, 0.f, 0.f), 300.f,0.8f,1f,0.0f);
		Plane blue2Plane = new Plane(blueMat, new Point3f(0.f,-2f,0.f), new Vector3f(0.f, 1.f, 0.f));
		objects.add(blue2Plane);


//		Plane bluePlane = new Plane(blueMat, new Point3f(0.f,2.f,0.f), new Vector3f(0.f, -1.f, 1f));
		//objects.add(bluePlane);
		
		//Add light sources
		Light light3 = new MovableLight(new Point3f(-2.f, 4.f, -3f),new RGBColor(1f,1f,1f));
		lights.add(light3);
		Light light4 = new MovableLight(new Point3f(2.f, 4.f, -3f),new RGBColor(1f,0f,0f));
		lights.add(light4);
	}
}
