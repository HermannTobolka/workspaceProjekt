package guiElemente;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
 //teskommentar zwecks e git

public class AnimatedMario {

	public static void main(String[] args) {
		AnimatedMarioF amf = new AnimatedMarioF();
		amf.showFrame();
	}
}

class AnimatedMarioF {

	private JFrame frame;
	private AnimatedSprite sprite;

	public AnimatedMarioF() {
		createFrame();
	}

	private void createFrame() {
		// Init the window
		frame = new JFrame("Walking Mario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Init the resources
		BufferedImage img = makeColorTransparent(".\\resources\\mario.png", new Color(0, 115, 0));
		BufferedImage[] imgs = splitImage(img, 6, 4);
		// Animate a sprite
		sprite = new AnimatedSprite(imgs);
		// Now add animations to it, these are the numbers of the mini-images.
		// They are held from left to right and top to bottom, increasing numbers
		// starting from 0.
		// This animation is the walking to the right one, if you look at the
		// spritesheet, you will see that 3-5 counting from 0, left to right top to bottom,
		// correspond with the backwards walking images.
		sprite.addNewAnimation("walk-forward", new int[] { 15, 16, 17 });
		sprite.addNewAnimation("walk-back", new int[] { 3, 4, 5 });
		sprite.addNewAnimation("walk-left", new int[] { 21, 22, 23 });
		sprite.addNewAnimation("walk-right", new int[] { 9, 10, 11 });
		// Now add user abilities
		frame.addKeyListener(new MarioKeyListener());
		// Set the position of the image
		sprite.setLocation(100 - imgs[0].getWidth() / 2, 100 - imgs[0].getHeight() / 2);
		sprite.setLoopMethod(AnimatedSprite.LOOP_REVERSE);
		// We pick the animation we want
		sprite.setAnimation("walk-forward");
	}

	public void showFrame() {
		// size the frame
		frame.setSize(200, 200);
		// position the frame
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, (d.height - frame.getSize().height)/2);
		// show the frame
		frame.setVisible(true);
		// Now we start animating using the game loop
		while (true) {
			Graphics2D g = (Graphics2D) frame.getGraphics();
			sprite.draw(g);
			g.dispose();
			try {
				Thread.sleep(280);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private BufferedImage makeColorTransparent(String s, Color color) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		BufferedImage dimg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(img, null, 0, 0);
		g2d.dispose();
		for (int i = 0; i < dimg.getHeight(); i++) {
			for (int j = 0; j < dimg.getWidth(); j++) {
				if (dimg.getRGB(j, i) == color.getRGB())
					dimg.setRGB(j, i, 0x8F1C1C);
			}
		}
		return dimg;
	}

	private BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {
		int w = img.getWidth() / cols;
		int h = img.getHeight() / rows;
		int num = 0;
		BufferedImage imgs[] = new BufferedImage[w * h];
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				imgs[num] = new BufferedImage(w, h, img.getType());
				Graphics2D g2d = imgs[num].createGraphics();
				g2d.drawImage(img, 0, 0, w, h, w * x, h * y, w* x + w, h * y + h, null);
				g2d.dispose();
				num++;
			}
		}
		return imgs;
	}

	class MarioKeyListener implements KeyListener {
		public MarioKeyListener() { }

		public void keyTyped(KeyEvent e) { }

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				sprite.setAnimation("walk-right");
			} 
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				sprite.setAnimation("walk-back");
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				sprite.setAnimation("walk-forward");
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				sprite.setAnimation("walk-left");
			}
		}

		public void keyReleased(KeyEvent e) { }

	}
}



class AnimatedSprite {
	/**
	 * This constant is for looping the animation backwards, so it would be animated, if the frames
	 * were "1,2,3" like this, "1,2,3,2,1" etc.
	 */
	public static final int LOOP_REVERSE = 2;
	/**
	 * This constant is for looping the animation to the beginning, so it would be animated, if the frames
	 * were "1,2,3" like this, "1,2,1,2,3" etc.
	 */
	public static final int LOOP_BEGINNING = 3;
	/**
	 * The loop method, will be one of the above
	 */
	private int loopMethod = LOOP_BEGINNING;
	/**
	 * Up is true, Down is false
	 */
	private boolean loopDir = true;

	/**
	 * The coordinates of the sprite, upper left
	 */
	private double x;
	private double y;

	/**
	 * The coordinates the sprite was at the previous frame
	 */
	private double lastX;
	private double lastY;

	/**
	 * This holds all the frames, named by a string key;
	 * for instance, you could enter the key 'walk' which would return 
	 * a frameset, frameset-walk-right, frameset-walk-left... etc.
	 */
	private HashMap<String, int[]> frames;

	/**
	 * All the images of this AnimatedSprite
	 */
	private BufferedImage[] images;

	/**
	 * The current 'state' or animation that the sprite is in
	 */
	private String currentAnim;

	/**
	 * The current frame being rendered, it's not the number of the frame, its the key for the array
	 * as in it's this number "frameset[currentframe] = 1;" not this number "frameset[0] = currentframe;"
	 */
	private int currentFrame;

	/**
	 * The current set of frames being used
	 */
	private int[] currentFrameSet;

	public AnimatedSprite(BufferedImage[] images) {
		this.images = images;
		frames = new HashMap<String, int[]>();
	}

	/**
	 * Lets you add a new animation set to the Sprite
	 * 
	 * @param name The name of the Sprite, this name will be used in setting the animation
	 * @param set The frames of the animation
	 */
	public void addNewAnimation(String name, int[] set) {
		frames.put(name, set);
		setAnimation(name);
	}

	/**
	 * Draws the current frame of the sprite
	 * @param g
	 */
	public void draw(Graphics2D g) {
//		System.out.println("lastX = " + lastX + " lastY = " + lastY + " x = " + x + " y = " + y);
		// Erase the last run
		g.clearRect((int) lastX-1, (int) lastY-1, getWidth()+1, getHeight()+1);

		// Now we need to get the current frame, from the current frameset
		int imgNum = currentFrameSet[currentFrame];
		g.drawImage(images[imgNum], null, (int) x, (int) y);

		// Increment the current frame
		if (currentFrame == currentFrameSet.length - 1) {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame = 0;
			} else {
				loopDir = false;
				currentFrame--;
			}
		} else {
			if (loopMethod == LOOP_BEGINNING) {
				currentFrame++;
			} else {
				if(currentFrame == 0) {
					loopDir = true;
				}
				if (loopDir) {
					currentFrame++;
				}
				if (!loopDir) {
					currentFrame--;
				}
			}
		}

		// Set these for next time we need to erase
		lastX = x;
		lastY = y;
	}

	/**
	 * Sets the current animation of the Sprite
	 * @param name
	 */
	public void setAnimation(String name) {
		if(frames.containsKey(name)) {
			currentAnim = name;
			currentFrameSet = frames.get(currentAnim);
			currentFrame = 0;
		}
	}

	/**
	 * Sets how the Sprite cycles through the animations.
	 * @param method  One of the constants LOOP_REVERSE or LOOP_BEGINNING
	 * 		LOOP_BEGINNING: Will start over when loop reaches the edn
	 * 		LOOP_REVERSE: Will start backwards when loop is over
	 */
	public void setLoopMethod(int method) {
		if(method == LOOP_REVERSE || method == LOOP_BEGINNING) {
			this.loopMethod = method;
		}
	}

	/**
	 * Sets the location of the sprite, it's upper left corner will be at the
	 * specified position
	 * @param x
	 * @param y
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X coordinate
	 * @return
	 */
	public double getX() { return x; }

	/**
	 * Set the X coordinate
	 * @param x
	 */
	public void setX(double x) { this.x = x; }

	/**
	 * Get the Y coordinate
	 * @return
	 */
	public double getY() { return y; }

	/**
	 * Set the Y coordinate
	 * @param y
	 */
	public void setY(double y) { this.y = y; }

	/**
	 * Gets the name of the current animation
	 * @return
	 */
	public String getCurrentAnim() { return currentAnim; }

	/**
	 * Gets the width of one frame
	 * @return
	 */
	public int getWidth() { return images[0].getWidth(); }

	/**
	 * Gets the height of one frame
	 * @return
	 */
	public int getHeight() { return images[0].getHeight(); }
}
