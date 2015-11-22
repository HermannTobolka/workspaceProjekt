import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class TestTester {
	
	static class Helper{
		private int data = 5;
		public void bump (int inc){
			inc++;
			data = data + inc;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Helper h = new Helper();
		int data =2;
		h.bump(data);
		System.out.println(h.data+ " " + data);
		 
		
	}
		

}
	

	 

