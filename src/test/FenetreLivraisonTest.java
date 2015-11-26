package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;

import org.junit.Test;

public class FenetreLivraisonTest {
	
	SimpleDateFormat formater;
	
	public void setUp() {
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
	}

	@Test
	public void testAppartient1() {
		FenetreLivraison fLivraison = new FenetreLivraison(new Date(formater.parse("08:00:00")), new Date(formater.parse("12:00:00")));
		Livraison livraison1 = new Livraison(0, new Noeud(0, 5, 8), 100);
		Livraison livraison2 = new Livraison(1, new Noeud(1, 8, 6), 101);
		Livraison livraison3 = new Livraison(2, new Noeud(2, 10, 10), 102);
		
		fLivraison.ajouterLivraison(livraison1);
		fLivraison.ajouterLivraison(livraison2);
		fLivraison.ajouterLivraison(livraison3);
		
		assert(fLivraison.appartient(livraison2));	
	}
	
	@Test
	public void testAppartient2() {
		
	}

}
