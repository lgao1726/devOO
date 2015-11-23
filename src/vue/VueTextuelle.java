package vue;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import modele.Plan;

public class VueTextuelle extends JLabel implements Observer{

	private String texte;
	private Plan plan;

	/**
	 * Cree une vue textuelle de plan dans fenetre
	 * @param plan
	 * @param fenetre
	 */
	public VueTextuelle(Plan plan, Fenetre fenetre){
		super();
		setBorder(BorderFactory.createTitledBorder("Liste des formes :"));
		this.setVerticalTextPosition(TOP);
		this.setVerticalAlignment(TOP);
		fenetre.getContentPane().add(this);
		plan.addObserver(this); // this observe plan
		this.plan = plan;
	}
	
	/**
	 * Methode appelee par les objets observes par this a chaque fois qu'ils ont ete modifies
	 */
	@Override
	public void update(Observable o, Object arg) {
		
	}


}
