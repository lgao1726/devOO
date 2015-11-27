package controleur;

import modele.Livraison;
import modele.Plan;

public class EtatLivraisonSelectionnee extends EtatDefaut{
	
	Livraison livraison;
	
	public EtatLivraisonSelectionnee(Livraison livraison) {
		// TODO Auto-generated constructor stub
		this.livraison=livraison;
	}
	public void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes){
		listeDeCdes.ajoute(new CommandeSupprimer(plan, livraison, livraison.getHeureDebut(), livraison.getHeureFin()));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
	}

}
