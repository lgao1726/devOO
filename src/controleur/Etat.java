package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

/**
 * Interface implemente par toutes les etats
 * @author H4101 International Corp
 *
 */
public interface Etat 
{
	/**
	 * Méthode charger plan
	 * @param planDeVille
	 * @param fenetre
	 */
	public abstract void chargerPlan(Plan planDeVille, Fenetre fenetre);
	
	/**
	 * Méthode charger Demandes 
	 * @param plan
	 * @param fenetre
	 */
	public abstract void chargerDemandes(Plan plan, Fenetre fenetre);
	
	/**
	 * Méthode calculer tournée
	 * @param plan
	 * @param fenetre
	 */
	public abstract void calculerTournee(Plan plan, Fenetre fenetre);
	
	/**
	 * Méthode selectionner livriason
	 * @param plan
	 * @param livraison
	 * @param listeDeCdes
	 * @param fenetre
	 */
	public abstract void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre);
	
	/**
	 * Méthode supprimer livraison
	 * @param plan
	 * @param fenetre
	 */
	public abstract void supprimerLivraison(Plan plan, Fenetre fenetre);
	
	/**
	 * Méthode echanger livraison
	 * @param plan
	 * @param listeDeCdes
	 * @param fenetre
	 */
	public abstract void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre);

	/**
	 * Méthode uno qui ajoute l'action courante dans la liste
	 * @param listeDeCdes
	 */
	public abstract void undo(ListeCommandes listeDeCdes);
	
	/**
	 * Méthode redo qui execute la dérinière commande dans la liste des commandes 
	 * @param listeDeCdes
	 */
	public abstract void redo(ListeCommandes listeDeCdes);

	/**
	 * Méthode qui selectionne un noeud dans le noyau
	 * @param plan
	 * @param noeud
	 * @param fenetre
	 */
	public abstract void selectionnerNoeud(Plan plan,Noeud noeud, Fenetre fenetre);

	/**
	 * Méthode ajouter livraison
	 * @param plan
	 * @param fenetre
	 */
	public abstract void ajouterLivraison(Plan plan, Fenetre fenetre);
		
	/**
	 * Méthode annuler une action
	 * @param fenetre
	 */
	public abstract void annuler(Fenetre fenetre);

	/**
	 * Méthode valider une action
	 * @param fenetre
	 */
	public abstract void valider(Fenetre fenetre);

	/**
	 * Méthode qui déselectionne une livraison dans le noyau
	 * @param fenetre
	 */
	public abstract void deselectionner(Fenetre fenetre);
	
	/**
	 * Méthode qui génére une feuille de route
	 * @param plan
	 */
	public abstract void genererFeuilleDeRoute(Plan plan);
}
