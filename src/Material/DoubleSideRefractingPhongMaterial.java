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
public  class DoubleSideRefractingPhongMaterial extends Material{

	RGBColor diffusecolor,ambientcolor,specularcolor;
	float shininess;
	Tracer trace;
	
	public DoubleSideRefractingPhongMaterial(RGBColor dcolor,RGBColor acolor,RGBColor scolor,float s,float r,Tracer t,float ref,float refra){
		super();
		this.diffusecolor = dcolor;
		this.ambientcolor = acolor;
		this.specularcolor = scolor;
		this.shininess = s;
		this.reflectivity=r;//Todo
		this.trace=t;
		this.refractionindex=ref;//todo
		this.refractivity=refra;//todo
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
		diffusec.mult(Math.abs(n.dot(theta)));
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
		erg.mult(1 - reflectivity-refractivity);
		
		return erg;
	}

	@Override
	public RGBColor mirrorshade(HitRecord hit,Tracer t) {
		if(reflectivity==0f)return new RGBColor(0f,0f,0f);
		Vector3f v=new Vector3f(hit.getNormal());
		v.scale((-2*(hit.getRay().direction).dot(hit.getNormal())));
		v.add(hit.getRay().direction);
		Ray ray=new Ray(hit.getHitPos(),v, hit.getRay().dept+1);
		RGBColor q = new RGBColor(t.trace(ray));
		q.mult(reflectivity);
		return q;
	}
	
	@Override
	public RGBColor refractionshade(HitRecord hit, Tracer t) {
		if(refractivity==0f)return new RGBColor(0f,0f,0f);
		float n1=hit.getRay().refractionindex;
		float n2=n1!=refractionindex?refractionindex:1;
		Vector3f lookingdir = new Vector3f(hit.getRay().direction);
		lookingdir.negate();
		lookingdir.normalize();
		float ctheta1=(float)lookingdir.dot(hit.getNormal());
		float ctheta2=(float)Math.sqrt(1-(n1/n2)*(n1/n2)*(1-ctheta1*ctheta1));
		lookingdir.negate();
		lookingdir.scale(n1/n2);
		Vector3f zvec =new Vector3f(hit.getNormal());
		zvec.scale((float)(n1/n2*ctheta1-ctheta2));
		zvec.add(lookingdir);
		
		/*float  n1 =hit.getRay().refractionindex;
		float n2=(n1!=refractionindex? this.refractionindex : 1);
		Vector3f dir = new Vector3f( hit.getRay().direction );
		dir.normalize();
		dir.negate();
		
		float theta1 = (float) Math.acos(dir.dot(hit.getNormal()));
		float theta2 = (float) Math.asin(Math.sin(theta1)*n2/n1);
		
		dir.negate();
		Vector3f r = new Vector3f(dir);
		r.scale(n1/n2);
		
		Vector3f s = new Vector3f(hit.getNormal());
		s.scale((float)((n1/n2)*Math.cos(theta1)+Math.cos(theta2)));
		r.add(s);*/
		
		Ray ray=new Ray(hit.getHitPos(),zvec,n2, hit.getRay().dept+1);
		//Ray ray=new Ray(hit.getHitPos(),hit.getRay().direction,n2,hit.getRay().dept+1);
		RGBColor q = new RGBColor(t.trace(ray));
		q.mult(refractivity);
		return q;
		
	}


}