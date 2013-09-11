package Utility;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import GeometricObjects.AbstractGeometricObject;
import Light.Light;
import Tracers.Tracer;

/**
 * This class stores all relevant information of an intersection for further use.
 * @author mmanzi
 *
 */
public class HitRecord {

	private float dist;
	private Ray ray;
	private Point3f position;
	private Vector3f normal;
	private AbstractGeometricObject obj;
	private boolean anyHit;
	
	/**
	 * constructor
	 * Initially we assume there was no hit!
	 */
	public HitRecord(){
		dist = Float.MAX_VALUE;
		anyHit = false;
	}
	
	/**
	 * This method records a ray-object intersection.
	 * @param obj		the object who was intersected
	 * @param charles 	the ray who intersected the object
	 * @param n			the normal of the objects surface at the intersection point
	 * @param p			the position of the intersection point in world space
	 * @param t			the distance between the rays origin and the intersection point
	 */
	public void recordHit(AbstractGeometricObject obj, Ray charles, Vector3f n, Point3f p, float t){
		this.obj = obj;
		this.ray = charles;
		this.normal = n;
		this.dist = t;
		this.position = p;
		anyHit = true;
	}
	
	/**
	 * This method is usually called from a ray tracer to shade a point.
	 * It calls the shading method of the material of the intersected object. 
	 */
	public RGBColor shade(Light l){
		return obj.getMaterial().shade(this, l);
	}
	
	public RGBColor mirrorshade(Tracer t){
		return obj.getMaterial().mirrorshade(this,t);
	}
	
	public RGBColor refrectionshade(Tracer t){
		return obj.getMaterial().refractionshade(this,t);
	}
	
	/**
	 * was anything hit?
	 */
	public boolean anyHit() {
		return anyHit;
	}
	
	/**
	 * getters for different data
	 */
	public float getHitDist(){
		return dist;
	}
	
	public Point3f getHitPos(){
		return position;
	}
	
	public Vector3f getNormal(){
		return normal;
	}
	
	public Ray getRay(){
		return ray;
	}

	

}
