package MVCBeispiele;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MVC { 
	
	public static void main(String[] args) {
		// initialize the model
		Model model = new Model();
		// initialize the controller
		Controller controller = new Controller(model);
		@SuppressWarnings("unused")
		// initialize the view
		View view = new View(controller);
		// give start order to controller
		controller.start();
	}

}

class Model {
	@SuppressWarnings("unused")
	private Controller controller;
	
	public void initialize() { }
	
	/**
	 * Keep reference to the controller
	 * @param c
	 */
	public void registerController(Controller c) { controller = c; }

}

class Controller {
	
	private Model model;
	private View view;
	
	/**
	 * Controller; keep the model reference and let the model register the controller
	 * @param m
	 */
	public Controller(Model m) {
		model = m;
		model.registerController(this);
	}
	
	/**
	 * Initialize model and view. Display the frame
	 */
	public void start() {
		model.initialize();
		view.initialize();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                view.showFrame();
            }
        });
	}

	/**
	 * keep reference to the view
	 * @param v
	 */
	public void registerView(View v) { view = v; }

}

class View {
	private Controller controller;
	private JFrame frame;

	/**
	 * Constructor; keep reference to the controller and have controller register the view.
	 * Create the application frame
	 * @param c
	 */
	public View(Controller c) {
		controller = c;
		controller.registerView(this);
		// create contents of frame
		frame = new JFrame("MVC");
	}

	public void initialize() { }
	/**
	 * show the frame with proper size and position
	 */
	public void showFrame() {
		frame.pack();
		frame.setSize(frame.getSize().width * 3, frame.getSize().height);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width)/2, 
				(d.height - frame.getSize().height)/2);
		frame.setVisible(true);
	}

}