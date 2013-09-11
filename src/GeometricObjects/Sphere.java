package GeometricObjects;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import Material.Material;
import Utility.HitRecord;
import Utility.Ray;
import utils.VectorUtils;
public class Sphere extends AbstractGeometricObject {

	Point3f center;
	float radius;

	public Sphere(Material mat, Point3f center, float radius) {
		super(mat);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public HitRecord hit(Ray ray) {

		HitRecord hit = new HitRecord();
		float a = ray.direction.dot(ray.direction);

		Vector3f vectorCE = VectorUtils.createVectorAB(center, ray.origin);
		Vector3f doubleDirection = VectorUtils.scaleVector(ray.direction, 2);
		float b = doubleDirection.dot(vectorCE);

		float c = (float) (vectorCE.dot(vectorCE) - radius * radius);

		float discriminant = b * b - 4 * a * c;

		Float t = null;
		if (discriminant == 0) {
			t = -b / (2 * a);
		} else if (discriminant > 0) {
			float t1 = (-b - (float) Math.sqrt(discriminant)) / (2 * a);
			float t2 = (-b + (float) Math.sqrt(discriminant)) / (2 * a);

			if (t1 > 0 || t2 > 0) {
				float closerT = Math.min(t1, t2);
				if (closerT < 0) {
					closerT = Math.max(t1, t2);
				}
				t = closerT;
			}
		}

		if (t != null && t > tmin) {
			// ray.origin + t*ray.direction
			Point3f hitPoint = new Point3f(ray.direction);
			hitPoint.scale(t);
			hitPoint.add(ray.origin);
			
			// record hit
			Vector3f surfaceNormal = VectorUtils.createVectorAB(center, hitPoint);
			surfaceNormal.normalize();
			hit.recordHit(this, ray, surfaceNormal, hitPoint, t);
		}

		return hit;
	}

}
