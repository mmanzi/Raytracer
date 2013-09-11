package Material;


import Light.Light;
import Utility.HitRecord;
import Utility.RGBColor;

/**
 *  A trivial unicolor material
 */
public  class TrivialMaterial extends Material{

	RGBColor color;
	
	public TrivialMaterial(RGBColor color){
		this.color = color;
		this.reflectivity=0f;
	}
	
	/**
	 * Trivial simply returns the materials color
	 **/
	public RGBColor shade(HitRecord hit, Light l) {
		return color;
	}
	
}
