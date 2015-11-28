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
		DemandeLivraison demandeLivraison=plan.getDemandeLivraisons();
		int idLivraison=demandeLivraison.getFenetre(livraisonPrecedente).getNbLivraisons();
		System.out.println("idLivraison: "+idLivraison);
		int idClient=demandeLivraison.getFenetre(livraisonPrecedente).getLivraisons().get(idLivraison-1).getClient()+1;
		System.out.println("idClient: "+idClient);
		Livraison livraison = new Livraison (idLivraison, noeud, idClient , livraisonPrecedente.getHeureDebut(), livraisonPrecedente.getHeureFin());
		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("Ajout terminé");
	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}
	
}
