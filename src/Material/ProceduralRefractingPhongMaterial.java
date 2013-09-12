package Material;

import javax.vecmath.Vector3f;

import Light.Light;
import ProceduralTexture.ProceduralTexture;
import Utility.HitRecord;
import Utility.RGBColor;

/**
 *  A trivial unicolor material
 */
public  class ProceduralRefractingPhongMaterial extends RefractingPhongMaterial{
	ProceduralTexture ptex;
	
	public ProceduralRefractingPhongMaterial(RGBColor diffusecolor,RGBColor ambientcolor,RGBColor specularcolor,float shininess,float reflectivity,float refractionindex,float refractivity,ProceduralTexture tex){
		super(diffusecolor,ambientcolor,specularcolor, shininess, reflectivity, refractionindex, refractivity);
		ptex=tex;
	}
	
	public ProceduralRefractingPhongMaterial(RGBColor diffusecolor,RGBColor ambientcolor,RGBColor specularcolor,float shininess,float reflectivity,ProceduralTexture tex){
		super(diffusecolor,ambientcolor,specularcolor, shininess, reflectivity);
		ptex=tex;
	}
	
	public ProceduralRefractingPhongMaterial(RGBColor diffusecolor,RGBColor ambientcolor,RGBColor specularcolor,float shininess,ProceduralTexture tex){
		super(diffusecolor,ambientcolor,specularcolor, shininess);
		ptex=tex;
	}
	
	public ProceduralRefractingPhongMaterial(RGBColor diffusecolor,RGBColor ambientcolor,ProceduralTexture tex){
		super(diffusecolor,ambientcolor);
		ptex=tex;
	}
	
	public ProceduralRefractingPhongMaterial(RGBColor diffusecolor,ProceduralTexture tex){
		super(diffusecolor);
		ptex=tex;
	}
	
	public ProceduralRefractingPhongMaterial(ProceduralTexture tex){
		super();
		ptex=tex;
	}
	
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
		erg.set(ptex.getColor(erg,new Vector3f(hit.getHitPos())));
		
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
		erg.mult(1 - reflectivity-refractivity);
		
		return erg;
	}

}

