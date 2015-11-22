package mvcInSeparatenKlassen;

import javax.swing.SwingUtilities;


public class Controller {

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
	
	/**
	 * Request calculation of arithmetic expression
	 * 
	 * @param o1 - operand1
	 * @param o - operator
	 * @param o2 - operand2
	 * @return - result of calculation
	 */
	public double calculate(double o1, char o, double o2) {
		return model.calculate(o1, o, o2);
	}

}