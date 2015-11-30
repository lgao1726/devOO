package controleur;

import vue.Fenetre;
import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatNoeudSelectionne extends EtatDefaut{

	
	Noeud noeud;
	Livraison livraisonPrecedente;
	Plan plan;
	ListeCommandes listeDeCdes;
	
	public void setNoeud(Noeud noeud){
		this.noeud=noeud;
	}
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraisonPrecedente, ListeCommandes listeDeCdes, Fenetre fenetre){
		this.livraisonPrecedente=livraisonPrecedente;
		this.listeDeCdes=listeDeCdes;
		this.plan=plan;
		fenetre.afficheMessage("Elle sera livrée après la livraison à l'adresse: "+ livraisonPrecedente.getAdresse().getId()+". Validez!");
	}
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		plan.updatePlan();
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		fenetre.afficheMessage("Adresse de la livraison: " + noeud.getId() + ". Selectionnez la livraison après laquelle vous voulez l'insérer");

	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
		fenetre.activerUndoRedoGenerer();
	}
	
	@Override
	public void valider(Fenetre fenetre)
	{
		Livraison livraison = new Livraison (0, noeud, 0 , null, null);
		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		plan.getDemandeLivraisons().getTournee().afficherListeItineraires();
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("Ajout terminé");
		fenetre.activerUndoRedoGenerer();
		noeud=null;
		livraisonPrecedente=null;
	}
}
