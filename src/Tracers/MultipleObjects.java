package Tracers;

import java.util.Iterator;

import GeometricObjects.AbstractGeometricObject;
import Light.Light;
import Utility.HitRecord;
import Utility.RGBColor;
import Utility.Ray;
import World.World;

public class MultipleObjects extends Tracer {

	public MultipleObjects(World w) {
		super(w);
	}
	static int dept=0;
	/**
	 * This simple tracer iterates over all objects and light sources
	 * 
	 * @param ray
	 *            : The ray that is traced
	 * @return The shading color of the closest intersection (if there was any)
	 */
	public HitRecord gethit(Ray ray) {
		HitRecord hit = new HitRecord();

		Iterator<AbstractGeometricObject> objItr = world_ptr
				.getObjectIterator();
		while (objItr.hasNext()) {
			HitRecord new_hit = objItr.next().hit(ray);
			if (hit.getHitDist() > new_hit.getHitDist())
				hit = new_hit;
		}
		return hit;
	}

	public RGBColor trace(Ray ray) {
		// tracing
		HitRecord hit = gethit(ray);

		// shading
		if (hit.anyHit()) {
			RGBColor color = new RGBColor(0f,0f,0f);
			Iterator<Light> lightItr = world_ptr.getLightIterator();
			while (lightItr.hasNext())
			{
				Light lightsource = lightItr.next();
				HitRecord lighthit = gethit(lightsource.getRayFromObject(hit
						.getHitPos()));
				if (!lighthit.anyHit()|| !lightsource.isBetweenCameraAndHit(hit.getHitPos(),lighthit))
				{
					color.add(hit.shade(lightsource));
				}
			}
			
			++dept;
			if(dept<=3)
				color.add(new RGBColor(hit.mirrorshade(this)));
			if(dept<=3)
				color.add(new RGBColor(hit.refrectionshade(this)));
			--dept;
			return color;
		} else {
			return world_ptr.getBackgroundColor();
		}
	}
}
