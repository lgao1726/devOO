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
		fenetre.afficheMessage("Elle sera livr�e apr�s la livraison � l'adresse: "+ livraisonPrecedente.getAdresse().getId()+". Validez!");
		fenetre.changerValider(true);
		fenetre.selectionnerLivraisonTextuelle(livraisonPrecedente);
	}
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		fenetre.afficheMessage("Adresse de la livraison: " + noeud.getId() + ". Selectionnez la livraison apr�s laquelle vous voulez l'ins�rer");

	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
		fenetre.activerUndoRedoGenerer();
	}
	
	@Override
	public void valider(Fenetre fenetre)
	{
		Livraison livraison = new Livraison (0, noeud, 0 , null, null);
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);

		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		plan.getDemandeLivraisons().getTournee().afficherListeItineraires();
		fenetre.afficheMessage("Ajout termin�");
		fenetre.activerUndoRedoGenerer();
		noeud=null;
		livraisonPrecedente=null;
	}
}
