package World;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Camera.LensCamera;
import GeometricObjects.Plane;
import GeometricObjects.Rectangle2;
import GeometricObjects.Sphere;
import Light.Light;
import Light.MovableLight;
import Material.DiffuseMaterial;
import Material.Material;
import Material.PhongMaterial;
import Tracers.Tracer;
import Utility.RGBColor;

public class PresentationScene1 extends World {

	@Override
	protected void build(Tracer t) {
		// define image resolution
		hres = 300;
		vres = 300;

		// define camera
		camera = new LensCamera(new Point3f(-3.f, 2.5f, 12.f), // eye
				new Point3f(0f, 2f, 0f), // look-at
				new Vector3f(0.f, 1.f, 0.f), // up
				hres, // horizontal resolution
				vres, // vertical resolution
				Math.PI / 4, 0.5f, // lens radius
				13f, // focal length
				500); // rays per pixel

		// define background color
		background_color = new RGBColor(.2f, .8f, 1f);

		Material planeMaterial = new PhongMaterial(
				new RGBColor(.8f, .8f, .8f), new RGBColor(0.2f,0.2f,0.2f),new RGBColor(0f,0f,0f), hres);
		Plane bottomPlane = new Plane(planeMaterial, new Point3f(0f, 0f, 0f),
				new Vector3f(0f, 1f, 0f));
		objects.add(bottomPlane);

		Material sphereMaterial = new DiffuseMaterial(new RGBColor(.122f, .341f,
				1f));
		Sphere sphere = new Sphere(sphereMaterial, new Point3f(0f, 3f, 1f), 0.54f);
		objects.add(sphere);

		Material leftRectangleMaterial = new PhongMaterial(new RGBColor(.722f, .18f, .0f), new RGBColor(.722f/3, .18f/3, .0f/3),new RGBColor(0f,0f,0f), 1);
		Material rightRectangleMaterial = new PhongMaterial(new RGBColor(.0f,.722f, .18f), new RGBColor(.0f/3,.722f/3, .18f/3),new RGBColor(0f,0f,0f), 1);
		Material rectangleMaterial = new PhongMaterial(new RGBColor(1f, 0.902f, .6f), new RGBColor(1f/3, 0.902f/3, .6f/3),new RGBColor(0f,0f,0f), 1);

		Rectangle2 topRectangle = new Rectangle2(rectangleMaterial,
				new Point3f(-2.5f, 5f, -2.5f), new Point3f(2.5f, 5f, -2.5f),
				new Point3f(-2.5f, 5f, 2.5f));
		Rectangle2 backRectangle = new Rectangle2(rectangleMaterial,
				new Point3f(-2.5f, 0f, -2.5f), new Point3f(2.5f, 0f, -2.5f),
				new Point3f(-2.5f, 5f, -2.5f));
		Rectangle2 leftRectangle = new Rectangle2(leftRectangleMaterial,
				new Point3f(-2.5f, 0f, 2.5f), new Point3f(-2.5f, 0f, -2.5f),
				new Point3f(-2.5f, 5f, 2.5f));
		Rectangle2 rightRectangle = new Rectangle2(rightRectangleMaterial,
				new Point3f(2.5f, 0f, -2.5f), new Point3f(2.5f, 0f, 2.5f),
				new Point3f(2.5f, 5f, -2.5f));
		objects.add(topRectangle);
		objects.add(backRectangle);
		objects.add(leftRectangle);
		objects.add(rightRectangle);

		Light light1 = new MovableLight(new Point3f(0f, 4.9f, 5f), new RGBColor(
				1f, 1f, 1f));
		lights.add(light1);
		
//		Light light2 = new MovableLight(new Point3f(0f, 4f, -2.5f), new RGBColor(
//				.6f, .6f, .6f));
//		lights.add(light2);
	}
}