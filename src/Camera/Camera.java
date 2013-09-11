package Camera;

import javax.vecmath.Matrix4f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import utils.VectorUtils;
import Tracers.Tracer;
import Utility.RGBColor;

/**
 * The abstract camera class, each derived class needs a render scene method
 * 
 * @author manzi
 * 
 */
public abstract class Camera {

	public abstract RGBColor[][] renderScene(RGBColor[][] img, Tracer rt);

	Point3f position;
	Point3f lookAt;
	Vector3f up;
	int horizontalResolution, verticalResolution;

	Vector3f uVector, vVector, wVector;
	Matrix4f transformationMatrix;

	public Camera(Point3f position, Point3f lookAt, Vector3f up, int horizontalResolution, int verticalResolution) {
		this.position = position;
		this.lookAt = lookAt;
		this.up = up;
		this.horizontalResolution = horizontalResolution;
		this.verticalResolution = verticalResolution;
		setupCoordSystem();
		transformationMatrix = createTransformationMatrix();
	}

	private void setupCoordSystem() {
		wVector = VectorUtils.createVectorAB(lookAt, position);
		wVector.normalize();

		uVector = VectorUtils.createCrossVector(up, wVector);
		uVector.normalize();

		vVector = VectorUtils.createCrossVector(wVector, uVector);
	}

	private Matrix4f createTransformationMatrix() {
		Matrix4f tranformationMatrix = new Matrix4f();
		tranformationMatrix.setColumn(0, uVector.x, uVector.y, uVector.z, 0);
		tranformationMatrix.setColumn(1, vVector.x, vVector.y, vVector.z, 0);
		tranformationMatrix.setColumn(2, wVector.x, wVector.y, wVector.z, 0);
		tranformationMatrix.setColumn(3, position.x, position.y, position.z, 1);
		return tranformationMatrix;
	}

}
