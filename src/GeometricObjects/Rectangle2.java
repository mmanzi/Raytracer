package GeometricObjects;

import javax.vecmath.Matrix3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Rectangle2 extends AbstractGeometricObject {

	Triangle t1,t2;
	
	
	public Rectangle2(Material mat, Point3f a, Point3f b, Point3f c) {
		super(mat);
		t1 = new Triangle(mat,a,b,c);
		// defined b,c,d for Triangle2
		Vector3f rich = new Vector3f(new Point3f(c));
		rich.sub(a);
		Point3f d = new Point3f(b);
		d.add(rich);
		t2 = new Triangle(mat,b,d,c);
		
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit1 = t1.hit(ray);
		HitRecord hit2 = t2.hit(ray);
		if(hit1.getHitDist()>hit2.getHitDist()){
			return hit2;
		} else {
			return hit1;
		}
		
		
	}

}
