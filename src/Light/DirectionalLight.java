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

public class DirectionalLight extends Light{

	Vector3f direction;
	RGBColor color;
	
	public DirectionalLight(Vector3f p, RGBColor c){
		direction = p;
		direction.normalize();
		color = c;	
	}
	
	@Override
	public Vector3f getIncomingRay(Point3f p) {
		
		return direction;
	}
	
	@Override
	public RGBColor getColor() {
		return new RGBColor(color);
	}

	@Override
	public float getAttenuation(float d) {
		return 1.f;
	}

	@Override
	public Ray getRayFromObject(Point3f p) {
		return new Ray(new Point3f(p),new Vector3f(direction));
	}

	@Override
	public boolean isBetweenCameraAndHit(Point3f p, HitRecord h) {
		return true;
	}

}
