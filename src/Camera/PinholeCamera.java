package Camera;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Tracers.Tracer;
import Utility.RGBColor;
import Utility.Ray;

/**
 * trivial Camera a pinhole camera positioned at the origin and looking towards
 * the negative z axis
 **/
public class PinholeCamera extends Camera {

	float t, r;

	public PinholeCamera(Point3f position, Point3f lookAt, Vector3f up,
			int horizontalResolution, int verticalResolution,
			double verticalFieldOfViewRad) {
		super(position, lookAt, up, horizontalResolution, verticalResolution);
		this.t = (float) Math.tan(verticalFieldOfViewRad / 2);
		this.r = this.t * (horizontalResolution / verticalResolution);
	}

	@Override
	public RGBColor[][] renderScene(RGBColor[][] img, Tracer rt) {
		Ray ray = new Ray();
		for (int x = 0; x < horizontalResolution; x++)
			for (int y = 0; y < verticalResolution; y++) {
				ray = generateRay(x, y);
				img[x][y] = rt.trace(ray);
			}
		return img;
	}

	private Ray generateRay(int x, int y) {
		Ray ray = new Ray();
		ray.origin = new Point3f(position);
		
		float horizontalDistanceToCenter = -r
				+ (2 * r * (float) (x + 0.5) / (float) horizontalResolution);
		
		float verticalDistanceToCenter = -t
				+ (2 * t * (float) (y + 0.5) / (float) verticalResolution);
		
		Point3f p = new Point3f(horizontalDistanceToCenter, -verticalDistanceToCenter, -1);
		transformationMatrix.transform(p);
		
		ray.direction = VectorUtils.createVectorAB(position, p);
		return ray;
	}
}
