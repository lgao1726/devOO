package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

/**
 * Etat au lancement de l'application
 * @author H4101
 *
 */
public class EtatDefaut implements Etat 
{

	@Override
	public void chargerPlan(Plan planDeVille, Fenetre fenetre)
	{}

	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre)
	{}


	@Override
	public void calculerTournee(Plan plan, Fenetre fenetre) 
	{}
	
	@Override
	public void selectionnerLivraison(Plan plan,Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) 
	{}
	
	@Override
	public void supprimerLivraison(Plan plan, Fenetre fenetre)
	{}

	@Override
	public void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre) 
	{}
	
	@Override
	public void undo(ListeCommandes listeDeCdes)
	{}

	public void redo(ListeCommandes listeDeCdes) 
	{}

	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre) 
	{}

	@Override
	public void ajouterLivraison(Plan plan, Fenetre fenetre) 
	{}

	@Override
	public void annuler(Fenetre fenetre) 
	{}

	@Override
	public void valider(Fenetre fenetre) 
	{}
	
	@Override
	public void deselectionner(Fenetre fenetre) 
	{}

	@Override
	public void genererFeuilleDeRoute(Plan plan) 
	{}
}
