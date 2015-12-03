package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controleur.Controleur;

/**
 * Classe EcouteurDeMvtSouris qui attend un évenement se génére sur les mouvements de la souris
 * @author H4101 International Corp
 *
 */
public class EcouterDeMvtSouris extends MouseAdapter 
{
	private Controleur controleur;
	private VueGraphique vueGraphique;

	/**
	 * Constructeur d'objet
	 * @param controleur
	 * @param vueGraphique
	 */
	public EcouterDeMvtSouris(Controleur controleur, VueGraphique vueGraphique)
	{
		this.controleur = controleur;
		this.vueGraphique = vueGraphique;
	}

	/**
	 * Souris bouger
	 * @param evt MouseEvent 
	 */
	public void mouseMoved(MouseEvent evt) 
	{
		// Methode appelee a chaque fois que la souris est bougee
		// Envoie au controleur les coordonnees de la souris.
		if (controleur.getNoeud(evt.getPoint().x*vueGraphique.getEchelle(), 
								evt.getPoint().y*vueGraphique.getEchelle(), vueGraphique.getRayonLivraison()) != null)
		{
			controleur.sourisSurNoeud(); 
		}
		else
		{			
			controleur.sourisPasSurNoeud();
		}
	}
}
