package outils;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		OuvreurDeFichierXML ouvreurDeFichierXML = new OuvreurDeFichierXML();
		try {
			File fichierSelectionne =  ouvreurDeFichierXML.ouvre(true);
			
			if(!ouvreurDeFichierXML.accept(fichierSelectionne)) System.out.println("Mauvaise extension de fichier ! ");
			else System.out.println("OK !");
			
		} catch (ExceptionXML e) {
			e.printStackTrace();
		}
		
	}

}
