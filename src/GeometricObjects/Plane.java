package GeometricObjects;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Plane extends AbstractGeometricObject{

	Point3f point;
	Vector3f normal;
	
	public Plane(Material mat, Point3f point, Vector3f normal) {
		super(mat);
		this.point = point;
		this.normal = normal;
		this.normal.normalize(); //make sure the passed value is normalized!
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit = new HitRecord();
		//(point-ray.origin)*normal
		Vector3f temp = new Vector3f(point);
		temp.sub(ray.origin);
		float origDotN = temp.dot(normal);
		//ray.direciton * normal
		temp.set(ray.direction);
		float dirDotN = temp.dot(normal);
		float t = origDotN / dirDotN;
		
		if (t>tmin){
			//ray.origin + t*ray.direction
			Point3f hitpos = new Point3f(ray.direction);
			hitpos.scale(t);
			hitpos.add(ray.origin);
			//record hit
			hit.recordHit(this, ray, normal, hitpos, t);
		}
		return hit;
	}

}
