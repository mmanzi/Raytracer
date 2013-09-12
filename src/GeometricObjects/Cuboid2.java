package GeometricObjects;

import javax.vecmath.Matrix3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Cuboid2 extends AbstractGeometricObject {

	Rectangle2 r1,r2,r3,r4,r5,r6;
	
	
	public Cuboid2(Material mat, Point3f a, Point3f b, Point3f c,Point3f d) {
		super(mat);
		
		
		/*
								  f+--------+e
								  /|       /|
								 / |      / |
							   g+--------+c |
								|  |     |  |
								| h+-----|--+b
								| /      | /
								|/       |/
							   d+--------+a
		 
		                          g+--------+f
								  /        /|
								 /        / |
							   c+--------+e |
								|        |  |
								| d      |  +h
								|        | /
								|        |/
							   a+--------+b
		 */
		
		
		
		
		
		
		/*t1 = new Triangle(mat,a,b,c);
		// defined b,c,d for Triangle2
		Vector3f rich = new Vector3f(new Point3f(c));
		rich.sub(a);
		Point3f d = new Point3f(b);
		d.add(rich);
		t2 = new Triangle(mat,b,d,c);*/
		//----Define e------
		Vector3f richac = new Vector3f(new Point3f(c));
		richac.sub(a);
		Point3f e = new Point3f(b);
		e.add(richac);
		
		//---Define h----
		Vector3f richad = new Vector3f(new Point3f(d)); 
		richad.sub(a);
		Point3f h = new Point3f(b);
		h.add(richad);
		
		//---Define f---
		Point3f f = new Point3f(e);
		f.add(richad);
		
		r1 = new Rectangle2(mat,a,b,c);
		r2 = new Rectangle2(mat,a,c,d);
		r3 = new Rectangle2(mat,b,h,e);
		r4 = new Rectangle2(mat,e,f,c);
		r5 = new Rectangle2(mat,a,b,d);
		r6 = new Rectangle2(mat,h,f,d);
		
		
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit1 = r1.hit(ray);
		HitRecord hit2 = r2.hit(ray);
		HitRecord hit3 = r3.hit(ray);
		HitRecord hit4 = r4.hit(ray);
		HitRecord hit5 = r5.hit(ray);
		HitRecord hit6 = r6.hit(ray);
		
		
		
		
		if(hit1.getHitDist()<hit2.getHitDist() && hit1.getHitDist()<hit3.getHitDist() && hit1.getHitDist()<hit4.getHitDist() && hit1.getHitDist()<hit5.getHitDist() && hit1.getHitDist()<hit6.getHitDist()){
			return hit1;
		}else if(hit2.getHitDist()<hit1.getHitDist() && hit2.getHitDist()<hit3.getHitDist() && hit2.getHitDist()<hit4.getHitDist() && hit2.getHitDist()<hit5.getHitDist() && hit2.getHitDist()<hit6.getHitDist()){
			return hit2;
		}else if(hit3.getHitDist()<hit1.getHitDist() && hit3.getHitDist()<hit2.getHitDist() && hit3.getHitDist()<hit4.getHitDist() && hit3.getHitDist()<hit5.getHitDist() && hit3.getHitDist()<hit6.getHitDist()){
			return hit3;
		}else if(hit4.getHitDist()<hit1.getHitDist() && hit4.getHitDist()<hit2.getHitDist() && hit4.getHitDist()<hit3.getHitDist() && hit4.getHitDist()<hit5.getHitDist() && hit4.getHitDist()<hit6.getHitDist()){
			return hit4;
		} else if(hit5.getHitDist()<hit1.getHitDist() && hit5.getHitDist()<hit2.getHitDist() && hit5.getHitDist()<hit3.getHitDist() && hit5.getHitDist()<hit4.getHitDist() && hit5.getHitDist()<hit6.getHitDist()){
			return hit5;
		} else if(hit6.getHitDist()<hit1.getHitDist() && hit6.getHitDist()<hit2.getHitDist() && hit6.getHitDist()<hit3.getHitDist() && hit6.getHitDist()<hit4.getHitDist() && hit6.getHitDist()<hit5.getHitDist()){
			return hit6;
		}else {
			return hit1;
		}
		
	
		
		
		
		
	}

}
