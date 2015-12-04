package controleur;

import modele.Livraison;
import modele.Plan;

/**
 * Classe commande supprimer livraison
 * @author H4101 International Corp
 *
 */
public class CommandeSupprimer implements Commande 
{
	private Plan plan;
	private Livraison livraison;
	private Livraison livraisonPrecedente;
	
	/**
	 * Constructeur d'objet
	 * @param plan
	 * @param livraison
	 */
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
