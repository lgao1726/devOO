package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Plan;

public class EtatLivraisonSelectionneeEchange extends EtatDefaut{
	
	Livraison livraison;
	Livraison livraison2;
	public EtatLivraisonSelectionneeEchange(){
		// TODO Auto-generated constructor stub
	}
	
	public void setLivraison(Livraison liv){
		this.livraison = liv;
	}
	
	
	public void selectionnerLivraison(Plan plan, Livraison liv, ListeCommandes listeDeCdes, Fenetre fenetre){
		this.livraison2 = liv;
		listeDeCdes.ajoute(new CommandeEchanger(plan, livraison.getAdresse().getId(), livraison2.getAdresse().getId()));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
	}

}
