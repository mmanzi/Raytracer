package GeometricObjects;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Material.Material;
import Utility.HitRecord;
import Utility.Ray;

public class Cuboid extends AbstractGeometricObject {

	Point3f originPoint;
	float depth, width, height;
	Rectangle[] faces = new Rectangle[6];
	Vector3f widthDirection, depthDirection, heightDirection;

	public Cuboid(Material mat, Point3f originPoint, Vector3f widthDirection,
			float width, float height, Vector3f depthDirection, float depth) {
		super(mat);
		if(widthDirection.dot(depthDirection) != 0) {
			throw new IllegalArgumentException("The depth direction and the width direction have to be orthogonal");
		}
		
		this.originPoint = originPoint;
		this.depth = depth;
		this.width = width;
		this.height = height;
		this.widthDirection = widthDirection;
		this.widthDirection.normalize();

		this.depthDirection = depthDirection;
		this.depthDirection.normalize();

		this.heightDirection = VectorUtils.createCrossVector(
				widthDirection, depthDirection);
		
		
		
		setUpFaces();
	}

	private void setUpFaces() {
		Vector3f backNormal = new Vector3f(depthDirection);
		Vector3f frontNormal = new Vector3f(depthDirection);
		frontNormal.negate();

		Vector3f rightNormal = new Vector3f(widthDirection);
		Vector3f leftNormal = new Vector3f(widthDirection);
		leftNormal.negate();

		Vector3f topNormal = new Vector3f(heightDirection);
		Vector3f bottomNormal = new Vector3f(heightDirection);
		bottomNormal.negate();

		Point3f rightOriginPoint = new Point3f(originPoint);
		rightOriginPoint.add(VectorUtils.scaleVector(widthDirection, width));

		Point3f backOriginPoint = new Point3f(rightOriginPoint);
		backOriginPoint.add(VectorUtils.scaleVector(depthDirection, depth));

		Point3f leftOriginPoint = new Point3f(originPoint);
		leftOriginPoint.add(VectorUtils.scaleVector(depthDirection, depth));

		Point3f topOriginPoint = new Point3f(backOriginPoint);
//		topOriginPoint.add(VectorUtils.scaleVector(heightDirection, height));

		// front
		faces[0] = new Rectangle(mat, originPoint, frontNormal, widthDirection,
				width, height);
//
//		// back
		faces[1] = new Rectangle(mat, backOriginPoint, backNormal, leftNormal,
				width, height);
//
//		// left
		faces[2] = new Rectangle(mat, leftOriginPoint, leftNormal, frontNormal,
				depth, height);
//
//		// right
		faces[3] = new Rectangle(mat, rightOriginPoint, rightNormal,
				depthDirection, depth, height);
//
//		// bottom
		faces[4] = new Rectangle(mat, originPoint, bottomNormal,
				depthDirection, depth, width);
//		// top
		faces[5] = new Rectangle(mat, topOriginPoint, topNormal, frontNormal,
				depth, width);
	}

	@Override
	public HitRecord hit(Ray ray) {
		HitRecord hitRecord = new HitRecord();
		HitRecord closestHitRecord = null;
		
		for (int i = 0; i < faces.length; i++) {
			HitRecord faceHitRecord = faces[i].hit(ray);
			final float faceHitDist = faceHitRecord.getHitDist();
			
			if(((closestHitRecord == null) || closestHitRecord.getHitDist() > faceHitDist) && faceHitDist > 0 && faceHitDist > tmin) {
				closestHitRecord = faceHitRecord;
			}
		}
		
		if(hitRecord != null) {
			hitRecord.recordHit(this, ray, closestHitRecord.getNormal(), closestHitRecord.getHitPos(), closestHitRecord.getHitDist());
		}
		
		return hitRecord;
	}
}
