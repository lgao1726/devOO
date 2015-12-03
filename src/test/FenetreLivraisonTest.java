package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;

import org.junit.Before;
import org.junit.Test;

public class FenetreLivraisonTest {
	
	SimpleDateFormat formater;
	
	Calendar dateDebut = new GregorianCalendar();
	Calendar dateFin = new GregorianCalendar();
	
	@Before
	public void setUp() {
		formater = new SimpleDateFormat("HH:mm:ss");
	}

	@Test
	public void testAppartient1() {

		try {
			dateDebut.setTime(formater.parse("08:00:00"));
			dateFin.setTime(formater.parse("12:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FenetreLivraison fLivraison = new FenetreLivraison(dateDebut, dateFin);
		Livraison livraison1 = new Livraison(0, new Noeud(0, 5, 8), 100, dateFin, dateFin);
		Livraison livraison2 = new Livraison(1, new Noeud(1, 8, 6), 101, dateFin, dateFin);
		Livraison livraison3 = new Livraison(2, new Noeud(2, 10, 10), 102, dateFin, dateFin);
		
		fLivraison.ajouterLivraison(livraison1);
		fLivraison.ajouterLivraison(livraison2);
		fLivraison.ajouterLivraison(livraison3);
		
		assert(fLivraison.appartient(livraison2));	
	}
	
	@Test
	public void testAppartient2() {
		
		try {
			dateDebut.setTime(formater.parse("08:00:00"));
			dateFin.setTime(formater.parse("12:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FenetreLivraison fLivraison = new FenetreLivraison(dateDebut, dateFin);
		Livraison livraison1 = new Livraison(0, new Noeud(0, 5, 8), 100, dateDebut, dateDebut);
		Livraison livraison2 = new Livraison(1, new Noeud(1, 8, 6), 101, dateDebut, dateDebut);
		Livraison livraison3 = new Livraison(2, new Noeud(2, 10, 10), 102, dateDebut, dateDebut);
		
		fLivraison.ajouterLivraison(livraison1);
		fLivraison.ajouterLivraison(livraison2);
		fLivraison.ajouterLivraison(livraison3);
		
		Livraison livraisonTest = new Livraison(3, new Noeud(3, 5, 5), 103, dateDebut, dateDebut);
		
		assertFalse(fLivraison.appartient(livraisonTest));
	}

}
