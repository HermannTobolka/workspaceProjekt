package rezeptSammlung;

import java.util.ArrayList;

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
	 * Aufruf Zutat anlegen aus Model
	 * @param zName
	 */
	public void zutatAnlegen(String zName){
		model.zutatAnlegen(zName);
	}
	
	
	/**
	 * Aufruf Filter anlegen aus Model  
	 * @param fName
	 */
	public void filterAnlegen(String fName, ArrayList<String> rezeptKategorie , ArrayList<String> zutatenJa , ArrayList<String> zutatenNein){
		model.filterAnlegen(fName, rezeptKategorie, zutatenJa, zutatenNein);
	}
	
	/**
	 * Aufruf Filter loeschen aus Model
	 * @param fName
	 */
	
	public void filterLoeschen(String fName){
		model.filterLoeschen(fName);
		
	}
	
	/**
	 * Aufruf Filter auflisten aus Model  
	 *  
	 * @return fArray
	 */
	
	public String[] filterAuflisten(){
		return model.filterAuflisten();
		 
	}
	
	/**
	 * Filter anwenden aufrufen
	 * @param fName
	 * @return String [] mit RezeptNamen nach Filter Suche
	 */
	public String[] filterAnwenden (String fName){
		return model.filterAnwenden(fName);
	}
	

	/**
	 * Aufruf Zutat auflisten aus Model  
	 * 
	 * @return fArray
	 */
	
	public String[] zutatenAuflisten (){
		return model.zutatenAuflisten();
		 
	}
	
	
	
	
	/**
	 * 
	 * @param rName
	 * @return
	 */
	public String rezeptSuchenName(String rName){
		return model.rezeptSuchenName(rName);
	}
	
	/**
	 * Aufruf rezepteAuflisten aus Model 
	 * @param rName
	 * @return rArray
	 */
	public String[] rezepteAuflisten (String rName){
		return model.rezepteAuflisten(rName);
	}

	public String[] rezeptSucheFilter (ArrayList<String> rezeptKategorie, ArrayList<String> zutatenJa , ArrayList<String> zutatenNein){
		return model.rezeptSucheFilter(rezeptKategorie, zutatenJa, zutatenNein);
	}
	
	/**
	 * Rezept ANZEIGEN liefer Rezeptobjekt für GUI Rezept Anzeigen
	 * @param rName
	 * @return 
	 *    
	 */
	public  Rezept rezeptAnzeigen(String rName){
		return   model.rezeptAnzeigen(rName);
	}
	 
	/**
	 * Rezept Anlegen - schreibt Rezept + Zutaten in Tabelle Rezepte und Rezeptzutat 
	 * @param rName
	 * @param lc
	 * @param kh
	 * @param veg
	 * @param kochAnweisung
	 * @param zutatenNamenList
	 * @param mengenList
	 */
	
	public void rezeptAnlegen(String rName, boolean lc, boolean kh, boolean veg, String kochAnweisung, ArrayList<String> zutatenNamenList, ArrayList<String> mengenList){
		 model.rezeptAnlegen(rName, lc, kh, veg, kochAnweisung, zutatenNamenList, mengenList);
	}
	/**
	 * Aufruf Rezept Anlegen -- NOCH LEER
	 * @param rName
	 */
 public void rezeptAnlegen(String rName){
//		model.rezeptAnlegen(rName);
 	}

 // Ende Controller 
}