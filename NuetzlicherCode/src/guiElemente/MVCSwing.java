package guiElemente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MVCSwing {

	public static void main(String[] args) {
		new MVCSwing().start();
	}
	
	private void start() {
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


	class Model {
		@SuppressWarnings("unused")
		private Controller controller;

		public void initialize() { }

		/**
		 * Keep reference to the controller
		 * @param c
		 */
		public void registerController(Controller c) { controller = c; }
		
		/**
		 * Perform calculation of arithmetic expression
		 * 
		 * @param o1 - operand1
		 * @param o - operator
		 * @param o2 - operand2
		 * @return - result of calculation
		 */
		public double calculate(double o1, char o, double o2) {
			double res = 0;
			switch(o) {
			case '+':
				res = o1 + o2;
				break;
			case '-':
				res = o1 - o2;
				break;
			case '*':
				res = o1 * o2;
				break;
			case '/':
				if (o2 != 0)
					res = o1 / o2;
			}
			return res;
		}
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

	class View {
		private Controller controller;
		private JFrame frame;
		private JTextField operand1;
		private JTextField operand2;
		private JComboBox<String> operator;
		private JTextField result;

		/**
		 * Constructor; keep reference to the controller and have controller register the view.
		 * Create the application frame
		 * @param c
		 */
		public View(Controller c) {
			controller = c;
			controller.registerView(this);
			//Create and set up the window.
			frame = new JFrame("MVC Rechner");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new GridLayout(0,2,20,20));
			Container contentPane = frame.getContentPane();
			JLabel label1 = new JLabel("Operand 1");
			contentPane.add(label1);
			operand1 = new JTextField();
			contentPane.add(operand1);
			JLabel labelo = new JLabel("Operator");
			contentPane.add(labelo);
			String[] operators = {"+", "-", "*", "/"}; 
			operator = new JComboBox<String>(operators);
			contentPane.add(operator);
			JLabel label2 = new JLabel("Operand 2");
			contentPane.add(label2);
			operand2 = new JTextField();
			contentPane.add(operand2);
			JButton calc = new JButton("Berechnen");
			calc.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						double o1 = Double.parseDouble(operand1.getText());
						double o2 = Double.parseDouble(operand2.getText());
						double res = controller.calculate(o1, operator.getSelectedItem().toString().charAt(0), o2);	
						result.setText(Double.toString(res));
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(frame, "Fehlerhafte Eingabe");
					}
				} });
			contentPane.add(calc);
			contentPane.add(new JLabel());
			JLabel labelr = new JLabel("Resultat");
			contentPane.add(labelr);
			result = new JTextField();
			contentPane.add(result);
		}

		public void initialize() { }
		/**
		 * show the frame with proper size and position
		 */
		public void showFrame() {
			frame.pack();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((d.width - frame.getSize().width)/2, 
					(d.height - frame.getSize().height)/2);
			frame.setVisible(true);
		}
	}
}