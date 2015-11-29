package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;
import controleur.ListeCommandes;

public class EcouteurDeBoutons implements ActionListener {
	
	private Controleur controleur;

	public EcouteurDeBoutons(Controleur controleur){
		this.controleur = controleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		// Methode appelee par l'ecouteur de boutons a chaque fois qu'un bouton est clique
		// Envoi au controleur du message correspondant au bouton clique
		switch (e.getActionCommand()){
		case Fenetre.CHARGER_PLAN: controleur.chargerPlan(); break;
		case Fenetre.CHARGER_LIVRAISON: controleur.chargerLivraison(); break;
		case Fenetre.CALCULER_TOURNEE: controleur.calculerTournee(); break;
		case Fenetre.SUPPRIMER_LIVRAISON: controleur.supprimerLivraison(); break;
		case Fenetre.ECHANGER_LIVRAISON: controleur.echangerLivraison();break;
		case Fenetre.AJOUTER_LIVRAISON: controleur.ajouterLivraison();break;
		case Fenetre.UNDO: controleur.undo();break;
		case Fenetre.REDO: controleur.redo();break;
		case Fenetre.ANNULER: controleur.annuler();break;
		
		}
	}
}
