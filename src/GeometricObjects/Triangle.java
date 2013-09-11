package GeometricObjects;

import javax.vecmath.Matrix3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Triangle extends AbstractGeometricObject {

	Vector3f normal;
	Point3f a, b,c;
	Vector3f ab=new Vector3f(),ac=new Vector3f();
	public Triangle(Material mat, Point3f a, Point3f b, Point3f c) {
		super(mat);
		this.a=a;
		this.b=b;
		this.c=c;
		//Vector3f ab=new Vector3f(b),ac=new Vector3f(c);
		ab.set(b); 
		ac.set(c);
		ab.sub(a);
		ac.sub(a);
		normal=new Vector3f(VectorUtils.createCrossVector(ac,ab));
		normal.normalize();
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit = new HitRecord();
		Matrix3f m =new Matrix3f(	ab.x,ac.x,-ray.direction.x, 
									ab.y,ac.y,-ray.direction.y, 
									ab.z,ac.z,-ray.direction.z);
		
		Vector3f n = new Vector3f(	ray.origin.x-a.x,
									ray.origin.y-a.y,
									ray.origin.z-a.z);
		if(Math.abs(m.determinant())<0.001f)
			return hit;
		m.invert();
		m.transform(n);
		if(n.z<=tmin || n.x<=0 || n.y <=0 || n.x+n.y>=1)
			{VectorUtils.counta++;return hit;}
		VectorUtils.countb++;
		Point3f intersc =new Point3f(ray.direction);
		intersc.scale(n.z);
		intersc.add(ray.origin);
		hit.recordHit(this, ray, normal, intersc, n.z);
		return hit;
	}

}
