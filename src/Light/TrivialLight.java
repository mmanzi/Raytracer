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

public class TrivialLight extends Light{

	
	public TrivialLight(){
	}
	
	@Override
	public Vector3f getIncomingRay(Point3f p) {
		return new Vector3f(p);
	}
	
	
	
	@Override
	public RGBColor getColor() {
		return new RGBColor(1.f,1.f,1.f);
	}

	@Override
	public float getAttenuation(float d) {
		return 1.f;
	}

	@Override
	public Ray getRayFromObject(Point3f p) {
		return new Ray(new Point3f(0f,0f,0f),new Vector3f(p));
	}

	@Override
	public boolean isBetweenCameraAndHit(Point3f p, HitRecord h) {
		// TODO Auto-generated method stub
		return false;
	}

}
