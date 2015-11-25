package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Livraison;
import modele.Plan;
import modele.Visiteur;

public class VueTextuelle extends JPanel implements Observer, Visiteur
{
	private String texte;
	private Plan plan;

	/**
	 * Cree une vue textuelle de plan dans fenetre
	 * @param plan
	 * @param fenetre
	 */
	public VueTextuelle(Plan plan, Fenetre fenetre){
		super();
		//setBorder(BorderFactory.createTitledBorder(" La demande des livraisons "));
		/*this.setVerticalTextPosition(TOP);
		this.setVerticalAlignment(TOP);
		fenetre.getContentPane().add(this);*/
		plan.addObserver(this);
		
		// Ajout table grid
		
		
		this.plan = plan;
	}
	
	/**
	 * Methode appelee par les objets observes par this a chaque fois qu'ils ont ete modifies
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		
	}
	
	/**
	 * Methode appelee par l'objet visite (un livraison) a chaque fois qu'il recoit le message accepte
	 */
	@Override
	public void visite(Livraison liv) 
	{
		
	}
}
