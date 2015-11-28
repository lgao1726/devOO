package controleur;

import modele.Livraison;
import modele.Plan;

public class EtatLivraisonSelectionnee extends EtatDefaut{
	
	Livraison livraison;
	Livraison livraison2;
	public EtatLivraisonSelectionnee(){
		// TODO Auto-generated constructor stub
	}
	public void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes){
		listeDeCdes.ajoute(new CommandeSupprimer(plan, livraison, livraison.getHeureDebut(), livraison.getHeureFin()));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
	}
	
	public void setLivraison(Livraison liv){
		this.livraison = liv;
	}
	
	
	public void selectionnerLivraison(Livraison liv){
		this.livraison2 = liv;
	}
	
	public void echangerLivraison(Plan plan,ListeCommandes listeDeCdes){
		listeDeCdes.ajoute(new CommandeEchanger(plan, livraison.getAdresse().getId(), livraison2.getAdresse().getId()));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
	}

}
