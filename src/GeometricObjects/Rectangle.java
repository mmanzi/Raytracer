package GeometricObjects;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Rectangle extends AbstractGeometricObject {

	Point3f originPoint;
	Vector3f normal, lengthDirection;
	Vector3f a, b;

	public Rectangle(Material mat, Point3f originPoint, Vector3f normal,
			Vector3f lengthDirection, float length, float width) {
		super(mat);
		this.originPoint = originPoint;
		this.normal = normal;
		this.normal.normalize(); // make sure the passed value is normalized!
		this.lengthDirection = lengthDirection; // the direction in which the
												// rectangle points
		this.lengthDirection.normalize();

		a = VectorUtils.scaleVector(lengthDirection, length);

		b = VectorUtils.createCrossVector(a, normal);
		b.normalize();
		b.scale(width);

	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hit = new HitRecord();
		// (point-ray.origin)*normal
		Vector3f vectorEyeOP = VectorUtils.createVectorAB(ray.origin,
				originPoint);
		float origDotN = vectorEyeOP.dot(normal);
		// ray.direciton * normal
		float dirDotN = ray.direction.dot(normal);
		float t = origDotN / dirDotN;

		if (t > tmin) {
			// ray.origin + t*ray.direction
			Point3f hitpos = new Point3f(ray.direction);
			hitpos.scale(t);
			hitpos.add(ray.origin);

			// "Ray Tracing from the Ground Up" page 371
			Vector3f vectorOPHP = VectorUtils.createVectorAB(originPoint,
					hitpos);

			float OPHPDotA = vectorOPHP.dot(a);
			float OPHPDotB = vectorOPHP.dot(b);

			if ((OPHPDotA >= 0.0f && OPHPDotA <= a.dot(a))
					&& (OPHPDotB >= 0.0f && OPHPDotB <= b.dot(b))) {
				// record hit
				hit.recordHit(this, ray, normal, hitpos, t);
			}
		}
		return hit;
	}

}
