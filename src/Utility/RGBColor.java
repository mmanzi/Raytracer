package Utility;
/**
 * A simple class who represents RGB colors as 3-tuples of float values. 
 * @author mmanzi
 *
 */
public class RGBColor {
	public float r;
	public float g;
	public float b;
	
	/**
	 * default constructor
	 */
	public RGBColor(){
		this.r=0; 
		this.g=0; 
		this.b=0;
	}
	
	/**
	 * copy constructor
	 */
	public RGBColor(RGBColor c){
		this.r = c.r;
		this.g = c.g;
		this.b = c.b;
	}
	
	/**
	 * constructor
	 */
	public RGBColor(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * some basic arithmetic operations. don't be afraid to add some more if needed :-)
	 */
	public void mult(float a){
		r*=a; g*=a; b*=a;
	}
	
	public void mult(RGBColor c){
		r*=c.r; g*=c.g; b*=c.b;
	}
	
	public void add(RGBColor c){
		r+=c.r; g+=c.g; b+=c.b;
	}
	

}
