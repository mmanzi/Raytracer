package utils;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class VectorUtils {
	public static int counta,countb;
	public static Vector3f scaleVector(Vector3f vec, float scaleFactor) {
		Vector3f scaledVector = new Vector3f(vec);
		scaledVector.scale(scaleFactor);
		return scaledVector;
	}

	public static Vector3f createVectorAB(Point3f pointA, Point3f pointB) {
		Vector3f vectorAB = new Vector3f(pointB);
		vectorAB.sub(pointA);
		return vectorAB;
	}
	
	public static Vector3f createCrossVector(Vector3f vectorA, Vector3f vectorB) {
		Vector3f crossVector = new Vector3f();
		crossVector.cross(vectorA, vectorB);
		return crossVector;
	}
	
}
