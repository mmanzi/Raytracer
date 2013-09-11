package Tracers;

import Utility.*;
import World.World;

/**
 * The abstract class for Tracer. Tracer subclasses need to implement a trace method that returns a RGB Color
 * @author manzi
 */

public abstract class Tracer {

	protected World world_ptr;
	
	public Tracer(World w){
		world_ptr = w;
	}

	public abstract RGBColor trace(Ray ray);
}
