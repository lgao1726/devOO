package controleur;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

/**
 * Etat ou la tournee est calcule, l'utilisateur a le choix d'effectuer toutes
 * les modifications proposées par les entreprise
 * @author H4101
 *
 */
public class EtatTourneeCalculee extends EtatDefaut{
	
	
	Fenetre fenetre;
	/**
	 * Methode qui charge un plan
	 * @param Plan de ville
	 * @param Fenetre
	 * @throws ExceptionEtat
	 */
	@Override
	public void chargerPlan(Plan plan, Fenetre fenetre)
	{
		Controleur.etatInit.chargerPlan(plan, fenetre);
	}
	/**
	 * Methode qui charge les demande des livraision et qui passe vers l'etat LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		Controleur.etatPlanCharge.chargerDemandes(plan, fenetre);
	}
	
	@Override
	public void undo(ListeCommandes listeDeCdes) {
		fenetre.afficheMessage("");
		listeDeCdes.undo();
	}
	
	@Override
	public void redo(ListeCommandes listeDeCdes) {
		fenetre.afficheMessage("");
		listeDeCdes.redo();
	}
	
	@Override
	public void ajouterLivraison(Plan plan, Fenetre fenetre) {
		this.fenetre=fenetre;
		Controleur.setEtatCourant(Controleur.etatModeAjout);
		fenetre.afficheMessage("Cliquez sur un noeud pour selectionner le lieu de votre livraison");
		fenetre.desacactiverUndoRedoGenerer();
		fenetre.changerValider(false);
	}
	
	@Override
	public void supprimerLivraison(Plan plan, Fenetre fenetre) {
		this.fenetre=fenetre;
		Controleur.setEtatCourant(Controleur.EtatModeSuppresion);
		fenetre.afficheMessage("Cliquez sur la livraison � supprimer");
		fenetre.desacactiverUndoRedoGenerer();
		fenetre.changerValider(false);

	}
	
	@Override
	public void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre) {
		this.fenetre=fenetre;
		Controleur.setEtatCourant(Controleur.etatModeEchange);
		fenetre.afficheMessage("Cliquez sur la premi�re livraison");
		fenetre.desacactiverUndoRedoGenerer();
		fenetre.changerValider(false);

	}
	
	@Override
	public void genererFeuilleDeRoute(Plan plan){
		plan.getDemandeLivraisons().genererFeuilleDeRoute(plan);
		File f =new File ("FeuilleDeRoute.txt");
		try {
			Desktop.getDesktop().open(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		fenetre.selectionnerLivraisonTextuelle(livraison);
	}
	
}
