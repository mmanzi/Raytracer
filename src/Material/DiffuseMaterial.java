package Material;

import javax.vecmath.Vector3f;

import Light.Light;
import Utility.HitRecord;
import Utility.RGBColor;

/**
 *  A trivial unicolor material
 */
public  class DiffuseMaterial extends Material{

	RGBColor color;
	public float reflectivity=0f;
	
	public DiffuseMaterial(RGBColor color){
		this.color = color;
		this.reflectivity=0f;
	}
	
	/**
	 * Trivial simply returns the materials color
	 **/
	@Override
	public RGBColor shade(HitRecord hit, Light l) {
		RGBColor lc = new RGBColor(l.getColor());
		RGBColor mc = new RGBColor(color);
		Vector3f n = new Vector3f(hit.getNormal());
		Vector3f theta = new Vector3f(l.getIncomingRay(hit.getHitPos()));
		theta.negate();
		mc.mult(l.getAttenuation(theta.length()));
		theta.normalize();
		mc.mult(lc);
		mc.mult(Math.max(n.dot(theta),0));
		return mc;
	}

}

