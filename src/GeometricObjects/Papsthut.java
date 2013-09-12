package GeometricObjects;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Papsthut extends AbstractGeometricObject {

	Vector3f normal;
	Point3f a, b,c;

	public Papsthut(Material mat, Point3f a, Point3f b, Point3f c) {
		super(mat);
		this.a=a;
		this.b=b;
		this.c=c;
		Vector3f ab=new Vector3f(b),ac=new Vector3f(c);
		ab.sub(a);
		ac.sub(a);
		normal=new Vector3f(VectorUtils.createCrossVector(ab,ac));
		normal.normalize();
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit = new HitRecord();
		Vector3f v= new Vector3f(a);
		v.sub(ray.origin);
		float t= v.dot(normal)/ray.direction.dot(normal);
		if(t<tmin)return hit;
		Point3f intersc =new Point3f(ray.direction);
		intersc.scale(t);
		intersc.add(ray.origin);
		Vector3f ab =new Vector3f(b),ac=new Vector3f(c),bc=new Vector3f(c),at=new Vector3f(intersc),bt=new Vector3f(intersc),ct=new Vector3f(intersc);
		ab.sub(a);
		ac.sub(a);
		bc.sub(b);
		at.sub(a);
		bt.sub(b);
		ct.sub(c);
		
		
		if(ab.length() < at.length() || ac.length() < at.length() || ab.length() < bt.length() || bc.length() < bt.length() || ac.length() < ct.length() || bc.length() < ct.length())
			return hit;
		hit.recordHit(this, ray, normal, intersc, t);
		return hit;
	}

}
