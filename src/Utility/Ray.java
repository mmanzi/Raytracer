package Utility;
/**
 * This class represents a ray consisting of a direction and an origin
 * @author manzi
 *
 */
import javax.vecmath.*;

public class Ray {
	public Point3f origin;
	public Vector3f direction;
	public float refractionindex;
	
	/**
	 * default constructor
	 */
	public Ray(){
		this.origin = new Point3f(0.f,0.f,0.f);
		this.direction = new Vector3f(0.f,0.f,0.f);
		this.refractionindex=1;
	}
	
	/**
	 * constructor
	 */
	public Ray(Point3f o, Vector3f d){
		this.origin = o;
		this.direction = d;
		this.refractionindex=1;
	}
	
	/**
	 * constructor with refractionindex
	 */
	public Ray(Point3f o, Vector3f d,float r){
		this.origin = o;
		this.direction = d;
		this.refractionindex=r;
	}
	
	/**
	 * copy constructor
	 */
	public Ray(Ray charles){
		this.origin = charles.origin;
		this.direction = charles.direction;
	}
	
	
	
}
