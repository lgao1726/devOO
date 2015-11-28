package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Tournee;

import org.junit.Before;
import org.junit.Test;

public class DemandeLivraisonTest {
	
	SimpleDateFormat formater;
	
	@Before
	public void setUp() {
		formater = new SimpleDateFormat("HH:mm:ss");
	}

	@Test
	public void testGetFenetre() {
		
		DemandeLivraison dLivraison = new DemandeLivraison();
		
		try {
			FenetreLivraison fenetreTest = new FenetreLivraison(formater.parse("13:00:00"), formater.parse("15:00:00"));
			dLivraison.ajouterFenetre(new FenetreLivraison(formater.parse("08:00:00"), formater.parse("12:00:00")));
			dLivraison.ajouterFenetre(fenetreTest);
			dLivraison.ajouterFenetre(new FenetreLivraison(formater.parse("15:00:00"), formater.parse("17:00:00")));
			assertEquals(dLivraison.getFenetre(formater.parse("13:00:00"), formater.parse("15:00:00")), fenetreTest);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAjouterLivraison(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testSupprimerLivraison(){
		fail("Not yet implemented");
	}

	@Test
	public void testCalculTournee() {
		fail("Not yet implemented");
	}
}
