package Material;

import javax.vecmath.Vector3f;

import Light.Light;
import Tracers.Tracer;
import Utility.HitRecord;
import Utility.RGBColor;
import Utility.Ray;

/**
 *  A trivial unicolor material
 */
public  class MirrorPhongMaterial extends Material{

	RGBColor diffusecolor,ambientcolor,specularcolor;
	float shininess;
	Tracer trace;
	
	public MirrorPhongMaterial(RGBColor dcolor,RGBColor acolor,RGBColor scolor,float s,float r,Tracer t){
		this.diffusecolor = dcolor;
		this.ambientcolor = acolor;
		this.specularcolor = scolor;
		this.shininess = s;
		this.reflectivity=r;
		this.trace=t;
	}
	
	/**
	 * Trivial simply returns the materials color
	 **/
	@Override
	public RGBColor shade(HitRecord hit, Light l) {
		RGBColor erg=new RGBColor(0,0,0);
		
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
		erg.mult(1 - reflectivity);
		return erg;
	}

	@Override
	public RGBColor mirrorshade(HitRecord hit,Tracer t) {
		
		Vector3f v=new Vector3f(hit.getNormal());
		v.scale((-2*(hit.getRay().direction).dot(hit.getNormal())));
		v.add(hit.getRay().direction);
		Ray ray=new Ray(hit.getHitPos(),v);
		RGBColor q = new RGBColor(t.trace(ray));
		q.mult(reflectivity);
		return q;
	}

}

