package GUI;

import GUI.MultithreadingGui;


public class Run {

	/**
	 * The main class of the raytracer.
	 */

	public static void main(String[] args) {
//		World myWorld = new PresentationScene1();
//		RGBColor[][] rendered_image = myWorld.render_scene();
//		new GUI(rendered_image);
		
		MultithreadingGui gui = new MultithreadingGui();
		gui.run();
	}

}
