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
public class LensCamera extends Camera {

	float t, r, lensRadius, focalLength;
	Vector3f viewingDirection;
	int raysPerPixel;

	public LensCamera(Point3f position, Point3f lookAt, Vector3f up,
			int horizontalResolution, int verticalResolution,
			double verticalFieldOfViewRad, float lensRadius, float focalLength,
			int raysPerPixel) {
		super(position, lookAt, up, horizontalResolution, verticalResolution);
		this.t = (float) Math.tan(verticalFieldOfViewRad / 2);
		this.r = this.t * (horizontalResolution / verticalResolution);
		this.focalLength = focalLength;
		this.lensRadius = lensRadius;
		this.raysPerPixel = raysPerPixel;

		this.viewingDirection = VectorUtils.createVectorAB(position, lookAt);
		this.viewingDirection.normalize();
	}

	@Override
	public RGBColor[][] renderScene(RGBColor[][] img, Tracer rt) {
		for (int x = 0; x < horizontalResolution; x++) {
			for (int y = 0; y < verticalResolution; y++) {
				System.out.println("X: " + x + " Y: " + y);
				img[x][y] = renderPixel(x, y, rt);
			}
		}
		return img;
	}

	@Override
	public RGBColor renderPixel(int x, int y, Tracer rt) {
		Ray ray;
		RGBColor rgbColor = new RGBColor();
		Point3f focalPoint = generateFocalPoint(x, y);
		for (int i = 0; i < raysPerPixel; i++) {
			ray = generateRay(x, y, focalPoint);
			RGBColor color = rt.trace(ray);
			rgbColor.add(color);
		}
		rgbColor.div(raysPerPixel);
		return rgbColor;
	}

	private Ray generateRay(int x, int y, Point3f p) {
		Ray ray = new Ray();

		ray.origin = randomizeOriginPoint();

		ray.direction = VectorUtils.createVectorAB(ray.origin, p);
		ray.direction.normalize();
		return ray;
	}

	private Point3f generateFocalPoint(int x, int y) {
		float horizontalDistanceToCenter = -r
				+ (2 * r * (float) (x + 0.5) / (float) horizontalResolution);

		float verticalDistanceToCenter = -t
				+ (2 * t * (float) (y + 0.5) / (float) verticalResolution);

		Point3f p = new Point3f(horizontalDistanceToCenter,
				-verticalDistanceToCenter, -1);

		transformationMatrix.transform(p);

		Vector3f direction = VectorUtils.createVectorAB(position, p);
		direction.normalize();

		float angleRad = direction.angle(viewingDirection);
		float t = (float) (focalLength / Math.cos(angleRad));
		// float t = focalLength;

		Point3f onFocalPlane = new Point3f(position);
		onFocalPlane.add(VectorUtils.scaleVector(direction, t));

		// transformationMatrix.transform(onFocalPlane);

		return onFocalPlane;
	}

	private Point3f randomizeOriginPoint() {
		Point3f randomPoint = new Point3f(position);

		float randomScale = randFloat((-1) * lensRadius, lensRadius);
		Vector3f direction = new Vector3f(up);
		if (Math.random() < 0.5f) {
			direction.cross(direction, viewingDirection);
		}

		randomPoint.add(VectorUtils.scaleVector(direction, randomScale));

		return randomPoint;
	}

	private static float randFloat(float min, float max) {
		return min + (float) (Math.random() * (max - min));
	}

}
