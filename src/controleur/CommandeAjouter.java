package controleur;

import modele.Livraison;
import modele.Plan;

/**
 * Classe commande aouter livriason
 * @author H4101 International Corp
 *
 */
public class CommandeAjouter implements Commande 
{
	private Plan plan;
	private Livraison livraison;
	private Livraison livraisonPrecedente;

	/**
	 * Contructeur d'objet
	 * @param plan
	 * @param livraison
	 * @param livraisonPrecedente
	 */
	public CommandeAjouter(Plan plan,Livraison livraison, Livraison livraisonPrecedente)
	{
		this.plan = plan;
		this.livraison = livraison;
		this.livraisonPrecedente=livraisonPrecedente;

	}
	
	@Override
	public void executer()
	{
		System.out.println(plan);
		plan.getDemandeLivraisons().ajouterLivraison(livraison, livraisonPrecedente);
		plan.updatePlan();
	}
	
	@Override
	public void undo()
	{
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
		plan.updatePlan();
	}
	
}
