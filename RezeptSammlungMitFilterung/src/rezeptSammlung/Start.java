package rezeptSammlung;

 



public class Start {

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
