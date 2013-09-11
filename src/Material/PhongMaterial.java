package Material;

import javax.vecmath.Vector3f;

import Light.Light;
import Utility.HitRecord;
import Utility.RGBColor;

/**
 *  A trivial unicolor material
 */
public  class PhongMaterial extends Material{

	RGBColor diffusecolor,ambientcolor,specularcolor;
	float shininess;
	public float reflectivity=0f;
	
	public PhongMaterial(RGBColor dcolor,RGBColor acolor,RGBColor scolor,float s){
		this.diffusecolor = dcolor;
		this.ambientcolor = acolor;
		this.specularcolor = scolor;
		this.shininess = s;
		this.reflectivity=0f;
	}
	
	/**
	 * Trivial simply returns the materials color
	 **/
	@Override
	public RGBColor shade(HitRecord hit, Light l) {
		RGBColor erg=new RGBColor(0f,0f,0f);
		
		RGBColor lc = new RGBColor(l.getColor());
		RGBColor diffusec = new RGBColor(diffusecolor);
		Vector3f n = new Vector3f(hit.getNormal());
		Vector3f theta = new Vector3f(l.getIncomingRay(hit.getHitPos()));
		theta.negate();
		diffusec.mult(l.getAttenuation(theta.length()));
		theta.normalize();
		diffusec.mult(lc);
		diffusec.mult(Math.max(n.dot(theta),0));
		erg.add(diffusec);
		
		RGBColor specularc = new RGBColor(specularcolor);
		Vector3f Lightdir = new Vector3f(l.getIncomingRay(hit.getHitPos()));
		Lightdir.negate();
		Vector3f Camdir = new Vector3f(hit.getRay().direction);
		Camdir.negate();
		
		Vector3f h=new Vector3f(Camdir);
		h.add(Lightdir);	
		h.normalize();
		
		specularc.mult((float)Math.pow(h.dot(n),shininess));
		specularc.mult(lc);
		
		erg.add(specularc);
		
		erg.add(ambientcolor);
		
		
		return erg;
	}

}

