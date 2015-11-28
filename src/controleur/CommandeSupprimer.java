package controleur;

import java.util.Calendar;
import java.util.Date;

import modele.Livraison;
import modele.Plan;

public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	private Livraison livraisonPrecedente;
	
	public CommandeSupprimer(Plan plan,Livraison livraison){
		this.plan = plan;
		this.livraison = livraison;
	}
	
	@Override
	public void executer() {
		livraisonPrecedente=plan.getDemandeLivraisons().getTournee().getLivraisonPrecedente(livraison);
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
		plan.updatePlan();
	}

	@Override
	public void undo() {
		
		plan.getDemandeLivraisons().ajouterLivraison(livraison, livraisonPrecedente);
		plan.updatePlan();
		
	}

}