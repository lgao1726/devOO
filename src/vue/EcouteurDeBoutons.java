package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;

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
		}
	}
}
