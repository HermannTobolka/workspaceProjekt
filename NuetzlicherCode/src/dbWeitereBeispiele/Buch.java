package dbWeitereBeispiele;

public class Buch {
	private String isbn;
	private String thema;
	private String titel;
	private String author;
	private int preis;
	public Buch(String isbn, String thema, String titel, String author,
			int preis) {
		super();
		this.isbn = isbn;
		this.thema = thema;
		this.titel = titel;
		this.author = author;
		this.preis = preis;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	

}
