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
	public int dept;
	/**
	 * default constructor
	 */
	public Ray(){
		this.origin = new Point3f(0.f,0.f,0.f);
		this.direction = new Vector3f(0.f,0.f,0.f);
		this.refractionindex=1;
		dept=0;
	}
	
	/**
	 * constructor
	 */
	public Ray(Point3f o, Vector3f d){
		this.origin = o;
		this.direction = d;
		this.refractionindex=1;
		dept=0;
	}
	
	/**
	 * constructor with refractionindex
	 */
	public Ray(Point3f o, Vector3f d,float r){
		this.origin = o;
		this.direction = d;
		this.refractionindex=r;
		dept=0;
	}
	
	/**
	 * constructor with refractionindex
	 */
	public Ray(Point3f o, Vector3f d,float r,int de){
		this.origin = o;
		this.direction = d;
		this.refractionindex=r;
		dept=de;
	}
	
	/**
	 * constructor with refractionindex
	 */
	public Ray(Point3f o, Vector3f d,int de){
		this.origin = o;
		this.direction = d;
		this.dept=de;
	}
	
	/**
	 * copy constructor
	 */
	public Ray(Ray charles){
		this.origin = charles.origin;
		this.direction = charles.direction;
	}
	
	
	
}
