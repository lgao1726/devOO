//package outils;

import java.io.File;

import xml.ExceptionXML;
import xml.OuvreurDeFichierXML;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		OuvreurDeFichierXML ouvreurDeFichierXML = OuvreurDeFichierXML.getInstance();
		try {
			File fichierSelectionne =  ouvreurDeFichierXML.ouvre(true);
			
			if(!ouvreurDeFichierXML.accept(fichierSelectionne)) System.out.println("Mauvaise extension de fichier ! ");
			else System.out.println("OK !");
			
		} catch (ExceptionXML e) {
			e.printStackTrace();
		}
		
	}

}
