package mvcInSeparatenKlassen;



public class Model {
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

