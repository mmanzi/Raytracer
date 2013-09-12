package GUI;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Utility.RGBColor;
import World.PresentationScene1;
import World.World;

public class MultithreadingGui extends JFrame {
	private static final long serialVersionUID = 1L;

	public void run() {
		World myWorld = new PresentationScene1();

		RGBColor[][] img = new RGBColor[myWorld.getHres()][myWorld.getVres()];

		int w = img.length;
		int h = img[1].length;

		// setup frame
		setTitle("Raytracer");
		setSize(w, h);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		int processorCount = Runtime.getRuntime().availableProcessors();
		int pixelsPerProcessor = img.length / processorCount;
		int rest = img.length % processorCount;

		List<Thread> workers = new LinkedList<Thread>();
		int min = 0, max = 0;
		for (int i = 1; i <= processorCount; i++) {
			min += max;
			max += pixelsPerProcessor;
			if (i == processorCount) {
				max += rest;
			}
			Runnable runnable = new RenderRunnable(myWorld, img, min, max);
			Thread worker = new Thread(runnable);
			worker.setName(String.valueOf(i));
			worker.start();
			workers.add(worker);
		}

		int running = 0;
		BufferedImage bufferedImg;
		do {
			running = 0;
			for (Thread thread : workers) {
				if (thread.isAlive()) {
					running++;
				}
			}
			// write image data into bufferedImage
			bufferedImg = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					// conversion from float RGB to int value in range [0,255]
					// required. For now clamp vlaues that are too high!
					if (img[i][j] != null) {
						Color pix_color = new Color((int) (255 * Math.min(1,
								img[i][j].r)), (int) (255 * Math.min(1,
								img[i][j].g)), (int) (255 * Math.min(1,
								img[i][j].b)));
						bufferedImg.setRGB(i, j, pix_color.getRGB());
					}
				}
			}
			// add bufferedImage to JFrame
			ImageIcon ic = new ImageIcon(bufferedImg);
			JLabel jp = new JLabel(ic);
			add(jp);
		} while (running > 0);

		String type = "png";
		File file = new File("renderedImage.".concat(type));
		try {
			ImageIO.write(bufferedImg, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class RenderRunnable implements Runnable {
		public final World world;
		public final RGBColor[][] img;
		public final int min, max;

		RenderRunnable(World world, RGBColor[][] img, int min, int max) {
			this.world = world;
			this.img = img;
			this.min = min;
			this.max = max;
		}

		@Override
		public void run() {
			for (int x = min; x < max; x++) {
				for (int y = 0; y < img[0].length; y++) {
					System.out.println("X: " + x + " Y: " + y);
					img[x][y] = world.renderPixel(x, y);
				}
			}
		}
	}
}
