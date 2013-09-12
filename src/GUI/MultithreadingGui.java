package GUI;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Tracers.Tracer;
import Utility.RGBColor;
import World.PresentationScene1;
import World.World;

public class MultithreadingGui extends JFrame {
	private static final long serialVersionUID = 1L;

	public void run() {
		World myWorld = new PresentationScene1();

		RGBColor[][] img = myWorld.render_scene();
		
		int w = img.length;
		int h = img[1].length;

		// setup frame
		setTitle("Raytracer");
		setSize(w, h);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		int running = 0;
		do {
			// write image data into bufferedImage
			BufferedImage bufferedImg = new BufferedImage(w, h,
					BufferedImage.TYPE_3BYTE_BGR);
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					// conversion from float RGB to int value in range [0,255]
					// required. For now clamp vlaues that are too high!
					Color pix_color = new Color((int) (255 * Math.min(1,
							img[i][j].r)), (int) (255 * Math.min(1, img[i][j].g)),
							(int) (255 * Math.min(1, img[i][j].b)));
					bufferedImg.setRGB(i, j, pix_color.getRGB());
				}
				// add bufferedImage to JFrame
				ImageIcon ic = new ImageIcon(bufferedImg);
				JLabel jp = new JLabel(ic);
				add(jp);
			}
			

			String type = "png";
			File file = new File("renderedImage.".concat(type));
			try {
				ImageIO.write(bufferedImg, type, file);
			} catch (IOException e) {
				e.printStackTrace();
			}


		} while(running > 0);
	}

	public class RenderRunnable implements Runnable {
		public final World world;
		public final RGBColor[][] img;
		
		RenderRunnable(World world, RGBColor[][] img) {
			this.world = world;
			this.img = img;
		}

		@Override
		public void run() {
			for (int x = 0; x < img.length; x++) {
				for (int y = 0; y < img[0].length; y++) {
					System.out.println("X: " + x + " Y: " + y);
					img[x][y] = world.renderPixel(x, y);
				}
			}
		}
	}
}