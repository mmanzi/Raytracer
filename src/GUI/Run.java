package GUI;

import Utility.RGBColor;
import World.PresentationScene1;
import World.World;

public class Run {

	/**
	 * The main class of the raytracer.
	 */

	public static void main(String[] args) {
		World myWorld = new PresentationScene1();

		RGBColor[][] rendered_image = myWorld.render_scene();
		new GUI(rendered_image);
	}

}
