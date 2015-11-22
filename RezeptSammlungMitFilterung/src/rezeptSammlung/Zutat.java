package rezeptSammlung;

public class Zutat {
	
	//Instanzvariablen, damit sind Zutaten auch in Zutatentabelle der DB abelegt
	private int ZutatID;
	private String ZutatName;
	
	public Zutat(){
		
	}
	
	public Zutat(int zutatID, String zutatName) {
		super();
		ZutatID = zutatID;
		ZutatName = zutatName;
	}

	public int getZutatID() {
		return ZutatID;
	}

	public void setZutatID(int zutatID) {
		ZutatID = zutatID;
	}

	public String getZutatName() {
		return ZutatName;
	}

	public void setZutatName(String zutatName) {
		ZutatName = zutatName;
	}

	@Override
	public String toString() {
		return "Zutat [ZutatID=" + ZutatID + ", ZutatName=" + ZutatName + "]";
	}
	
	

}
