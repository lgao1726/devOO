package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

public class EtatModeSuppression extends EtatDefaut{
	
	Livraison livraison;
	ListeCommandes listeDeCdes;
	Plan plan;
	
	@Override
	public void valider(Fenetre fenetre){
		fenetre.activerUndoRedoGenerer();
		if(plan.getDemandeLivraisons().getTournee().getLivraisonPrecedente(livraison)!=null)
		{
			listeDeCdes.ajoute(new CommandeSupprimer(plan, livraison));
			fenetre.afficheMessage("La livraison à  l'adresse ( " + livraison.getAdresse().getId() + ") a été supprimée" ); 
		}
		else
		{
			fenetre.afficheMessageBox("Vous essayez de supprimer l'entrepot");
			fenetre.afficheMessage("");
		}
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);

	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) {
		this.plan=plan;
		this.listeDeCdes=listeDeCdes;
		this.livraison=livraison;
		fenetre.afficheMessage("Valider pour supprimer la livraison à  l'adresse ( " + livraison.getAdresse().getId() + ")." ); 
	}

	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.activerUndoRedoGenerer();
		fenetre.afficheMessage("");
	}
	
	/**@Override
	public void deselectionner(Fenetre fenetre){
		livraison=null;
	}**/

	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		fenetre.afficheMessage("Selectionner une livraison.");
	}
	
	public void setLivraison(Livraison liv){
			this.livraison = liv;
		}	
}
