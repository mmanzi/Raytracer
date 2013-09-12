package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Camera.PinholeCamera;
import GeometricObjects.Cuboid2;
import GeometricObjects.Plane;
import GeometricObjects.Rectangle2;
import Light.Light;
import Light.MovableLight;
import Material.Material;
import Material.RefractingPhongMaterial;
import Utility.RGBColor;

public class PresentationScene2 extends World {

	@Override
	protected void build() {
		// define image resolution
		hres = 500;
		vres = 500;

		// define camera
//		 camera = new LensCamera(new Point3f(-3.f, 2.5f, 12.f), // eye
//		 new Point3f(0f, 2f, 0f), // look-at
//		 new Vector3f(0.f, 1.f, 0.f), // up
//		 hres, // horizontal resolution
//		 vres, // vertical resolution
//		 Math.PI / 4, 0f, // lens radius
//		 13f, // focal length
//		 1); // rays per pixel

		camera = new PinholeCamera(new Point3f(-3.5f, 13.f, 27.f), // eye
				new Point3f(4.5f, 2.f, 0.f), // look-at
				new Vector3f(0.f, 1.f, 0.f), // up
				hres, // horizontal resolution
				vres, // vertical resolution
				Math.PI / 4);
		// define background color
		background_color = new RGBColor(0.878f, 1f, 1f);

		Material planeMaterial = new RefractingPhongMaterial(new RGBColor(.9f, .9f, .9f),
				new RGBColor(0.1f, 0.1f, 0.1f), new RGBColor(0f, 0f, 0f), hres);
		Plane bottomPlane = new Plane(planeMaterial, new Point3f(0f, 0f, 0f),
				new Vector3f(0f, 1f, 0f));
		objects.add(bottomPlane);

//		Material sphereMaterial = new RefractingPhongMaterial(new RGBColor(.122f,
//				.341f, 1f));
		Material sphere2Material = new RefractingPhongMaterial(new RGBColor(.722f, .18f,
				.0f), new RGBColor(.722f / 3, .18f / 3, .0f / 3), new RGBColor(
				0f, 0f, 0f), 1);

		// Sphere sphere = new Sphere(sphereMaterial, new Point3f(-2f, 3f, 0f),
		// 0.5f);
		// objects.add(sphere);
		//
		// Sphere sphere2 = new Sphere(sphere2Material, new Point3f(2f, 3f, 0f),
		// 0.5f);
		// objects.add(sphere2);

		Cuboid2 cuboid = new Cuboid2(sphere2Material, new Point3f(-2f, 3f, 2f),
				new Point3f(2f, 3f, 2f), new Point3f(-2f, 7f, 2f), new Point3f(
						-2f, 3f, -2f));
		objects.add(cuboid);
		
		Material mirrorMat = new RefractingPhongMaterial(new RGBColor(0.4f, 0.4f, 0.4f), new RGBColor(), new RGBColor(), 0f, 0.5f, 0f, 0f);
		
		Rectangle2 rectangle2 = new Rectangle2(mirrorMat, new Point3f(4f, 0f, -4f), new Point3f(7f, 0f, 6f), new Point3f(4f, 10f, -4f));
		objects.add(rectangle2);
		
		Light light1 = new MovableLight(new Point3f(0f, 8f, 10f),
				new RGBColor(1f, 1f, 1f));
		lights.add(light1);

		// Light light2 = new MovableLight(new Point3f(2.5f, 8f, 0f), new
		// RGBColor(
		// .6f, .6f, .6f));
		// lights.add(light2);
	}
}
