package controleur;

import java.util.Calendar;
import java.util.Date;

import modele.DemandeLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class CommandeAjouter implements Commande {
	private Plan plan;
	private Livraison livraison;
	private Livraison livraisonPrecedente;
	
	public CommandeAjouter(Plan plan,Livraison livraison, Livraison livraisonPrecedente){
		this.plan = plan;
		this.livraison = livraison;
		this.livraisonPrecedente=livraisonPrecedente;

	}
	
	public void executer(){
		plan.getDemandeLivraisons().ajouterLivraison(livraison, livraisonPrecedente);
		plan.updatePlan();
		}
	
	public void undo(){
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
		plan.updatePlan();
	}
	
}