package ProceduralTexture;

import javax.vecmath.Vector3f;

import Utility.RGBColor;

public abstract class ProceduralTexture {

	public abstract RGBColor getColor(RGBColor g, Vector3f v);
}
