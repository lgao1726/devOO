package controleur;

import vue.Fenetre;
import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatNoeudSelectionne extends EtatDefaut{

	
	Noeud noeud;
	
	public void setNoeud(Noeud noeud){
		this.noeud=noeud;
	}
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraisonPrecedente, ListeCommandes listeDeCdes, Fenetre fenetre){
		Livraison livraison = new Livraison (0, noeud, 0 , null, null);
		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("Ajout termin�");
	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}
	
}
