package controleur;

import modele.Livraison;
import modele.Plan;
import vue.Fenetre;

public class EtatTourneeCalculee extends EtatDefaut{
	
	@Override
	public void selectionnerLivraison(Livraison livraison) {
		// TODO Auto-generated method stub
		Controleur.etatLivraisonSelectionnee.setLivraison(livraison);
		Controleur.setEtatCourant(Controleur.etatLivraisonSelectionnee);		
	}

}
