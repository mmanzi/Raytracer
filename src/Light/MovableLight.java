package Light;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;




import Utility.HitRecord;
import Utility.RGBColor;
import Utility.Ray;

/**
 * It is assumed this light is at the origin (0,0,0) and is white
 * @author Manzi
 *
 */

public class MovableLight extends Light{

	Point3f location;
	RGBColor color;
	
	public MovableLight(Point3f p, RGBColor c){
		location = p;
		color = c;	
	}
	
	@Override
	public Vector3f getIncomingRay(Point3f p) {
		Vector3f v = new Vector3f(p);
		v.sub(location);
		return v;
	}
	
	@Override
	public RGBColor getColor() {
		return new RGBColor(color);
	}

	@Override
	/*
	 * TODO: this should attenuate with something like 1/(dist*dist)
	 */
	public float getAttenuation(float d) {
		return 1.f;
	}

	@Override
	public Ray getRayFromObject(Point3f p) {
		Vector3f v = new Vector3f(location);
		v.sub(p);
		return new Ray(new Point3f(p),new Vector3f(v));
	}

	public boolean isBetweenCameraAndHit(Point3f p, HitRecord h) {
		Vector3f a=new Vector3f(p),b=new Vector3f(location);
		
		a.sub(h.getHitPos());
		b.sub(h.getHitPos());
		return a.length()<b.length();
	}

}
