package vue;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.Plan;
import vue.VueGraphique;

public class Fenetre extends JFrame 
{
	
	// Intitules des boutons de la fenetre
	protected final static String CHARGER_PLAN = "Charger Plan";
	protected final static String CHARGER_LIVRAISON = "Charger Livraison";
	protected final static String CALCULER_TOURNEE = "Calculer Tournee";
	protected static final String SUPPRIMER_LIVRAISON = "Supprimer livraison";
	protected static final String ECHANGER_LIVRAISON = "Echanger livraison";
	protected static final String REDO = "Redo";
	protected static final String UNDO = "Undo";
	protected static final String AJOUTER_LIVRAISON = "Ajouter livraison";
	protected static final String ANNULER = "Annuler";
	private ArrayList<JButton> boutons;
	private JLabel cadreMessages;
	private VueGraphique vueGraphique;
	//private VueTextuelle vueTextuelle;
	private EcouteurDeBoutons ecouteurDeBoutons;
	private EcouteurDeSouris ecouteurDeSouris;
	private EcouterDeMvtSouris ecouteurDeMvtSouris;
	//private EcouteurDeClavier ecouteurDeClavier;
	
	private final String[] intitulesBoutons = new String[]{CHARGER_PLAN,CHARGER_LIVRAISON, CALCULER_TOURNEE, SUPPRIMER_LIVRAISON,ECHANGER_LIVRAISON,AJOUTER_LIVRAISON, UNDO, REDO, ANNULER};
	private final int hauteurBouton = 40;
	private final int largeurBouton = 150;
	private final int hauteurCadreMessages = 80;
	private final int largeurVueTextuelle = 400;
	private final int echelleFenetre = 1;

	
	/**
	 * Cree une fenetre avec des boutons, une zone graphique pour dessiner le plan p avec l'echelle e, 
	 * un cadre pour afficher des messages, une zone textuelle decrivant les formes de p,
	 * et des ecouteurs de boutons, de clavier et de souris qui envoient des messages au controleur c
	 * @param p le plan
	 * @param e l'echelle
	 * @param controleur le controleur
	 */
	public Fenetre(Plan p, Controleur controleur)
	{
		setLayout(null);
		creeBoutons(controleur);
		cadreMessages = new JLabel();
		cadreMessages.setBorder(BorderFactory.createTitledBorder("Messages..."));
		getContentPane().add(cadreMessages);
		vueGraphique = new VueGraphique(p, echelleFenetre, this);
		//vueTextuelle = new VueTextuelle(p, this);
		ecouteurDeSouris = new EcouteurDeSouris(controleur,vueGraphique,this);
		ecouteurDeMvtSouris = new EcouterDeMvtSouris(controleur, vueGraphique, this);
		addMouseListener(ecouteurDeSouris);
		addMouseMotionListener(ecouteurDeMvtSouris);
		//ecouteurDeClavier = new EcouteurDeClavier(controleur);
		//addKeyListener(ecouteurDeClavier);
		setTailleFenetre();
		setVisible(true);
	}

	/**
	 * Cree les boutons correspondant aux intitules contenus dans intitulesBoutons
	 * cree un ecouteur de boutons qui ecoute ces boutons
	 * @param controleur
	 */
	private void creeBoutons(Controleur controleur){
		ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
		boutons = new ArrayList<JButton>();
		for (int i=0; i<intitulesBoutons.length; i++){
			JButton bouton = new JButton(intitulesBoutons[i]);
			boutons.add(bouton);
			bouton.setSize(largeurBouton,hauteurBouton);
			bouton.setLocation(0,(boutons.size()-1)*hauteurBouton);
			bouton.setFocusable(false);
			bouton.setFocusPainted(false);
			bouton.addActionListener(ecouteurDeBoutons);
			getContentPane().add(bouton);	
		}
	}
	
	/**
	 * Definit la taille du cadre et de ses composants en fonction de la taille de la vue
	 * @param largeurVue
	 * @param hauteurVue
	 */
	private void setTailleFenetre() {
		int hauteurBoutons = hauteurBouton*intitulesBoutons.length;
		int hauteurFenetre = Math.max(vueGraphique.getHauteur(),hauteurBoutons)+hauteurCadreMessages;
		int largeurFenetre = vueGraphique.getLargeur()+largeurBouton+largeurVueTextuelle+10;
		setSize(largeurFenetre, hauteurFenetre);
		//cadreMessages.setSize(largeurFenetre,60);
		//cadreMessages.setLocation(0,hauteurFenetre-hauteurCadreMessages);
		vueGraphique.setLocation(largeurBouton, 0);
		cadreMessages.setSize(100,hauteurFenetre-hauteurCadreMessages);
		cadreMessages.setLocation(10+vueGraphique.getLargeur()+largeurBouton,0);
		//vueTextuelle.setSize(largeurVueTextuelle,hauteurFenetre-hauteurCadreMessages);
		//vueTextuelle.setLocation(10+vueGraphique.getLargeur()+largeurBouton,0);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Affiche message dans la fenetre de dialogue avec l'utilisateur
	 * @param message
	 */
	public void afficheMessage(String message) {
		cadreMessages.setText(message);
	}
	
	/**
	 * Affiche un message box à l'utilisateur
	 * @param message
	 */
	public void afficheMessageBox(String message) 
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Active les boutons si b = true, les desactive sinon
	 * @param b
	 */
	public void autoriseBoutons(Boolean b) {
		Iterator<JButton> it = boutons.iterator();
		while (it.hasNext()){
			JButton bouton = it.next();
			bouton.setEnabled(b);
		}
	}
	
	public int getEchelle(){
		return vueGraphique.getEchelle();
	}
	
	public void setEchelle(int echelle){
		vueGraphique.setEchelle(echelle);
		setTailleFenetre();
	}
	
	public void sourisSurNoeud(){
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void sourisPasSurNoeud(){
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
