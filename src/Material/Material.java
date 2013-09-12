package Material;

import Light.Light;
import Tracers.Tracer;
import Utility.HitRecord;
import Utility.RGBColor;
/**
 * Material for now all a material must do is shade 
 * @author mmanzi
 *
 */

public abstract class Material {
	
	public float reflectivity=0.0f,refractionindex=0.0f,refractivity=0.0f;
	
	public RGBColor shade(HitRecord hit, Light l)
	{
		return new RGBColor(0f,0f,0f);
	}
	
	public RGBColor mirrorshade(HitRecord hit,Tracer t) {
		return new RGBColor(0f,0f,0f);
	}
	
	public RGBColor refractionshade(HitRecord hit, Tracer t) {
		return new RGBColor(0f,0f,0f);
	}

}
