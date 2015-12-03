package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controleur.Controleur;

/**
 * Classe EcouteurDeBoutons qui attend un évenement se génére sur les bouttons 
 * @author H4101 International Corp
 *
 */
public class EcouteurDeBoutons implements ActionListener 
{
	private Controleur controleur;

	/**
	 * Constructeur d'objet
	 * @param controleur
	 */
	public EcouteurDeBoutons(Controleur controleur)
	{
		this.controleur = controleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{ 
		// Methode appelee par l'ecouteur de boutons a chaque fois qu'un bouton est clique
		// Envoi au controleur du message correspondant au bouton clique
		switch (e.getActionCommand())
		{
		case Fenetre.CHARGER_PLAN:
			controleur.chargerPlan();
		break;
		case Fenetre.CHARGER_LIVRAISON:
			controleur.chargerLivraison();
		break;
		case Fenetre.CALCULER_TOURNEE:
			controleur.calculerTournee();
		break;
		case Fenetre.UNDO:
			controleur.undo();
		break;
		case Fenetre.REDO:
			controleur.redo();
		break;
		case Fenetre.GENERE:
			controleur.genererFeuilleDeRoute();
		break;
		case Fenetre.EXIT:
			controleur.quitter();
		break;
		case Fenetre.ABOUT:
			controleur.apropos();
		break;
		}
	}
}
