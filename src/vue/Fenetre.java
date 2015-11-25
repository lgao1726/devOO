package vue;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

import controleur.Controleur;
import modele.Plan;
import vue.VueGraphique;

public class Fenetre extends JFrame 
{
	
	// Intitules des boutons de la fenetre
	protected final static String CHARGER_PLAN = "Charger Plan";
	protected final static String CHARGER_LIVRAISON = "Charger Livraison";
	//protected static final String REDO = "Redo";
	//protected static final String UNDO = "Undo";
	private ArrayList<JButton> boutons;
	private JLabel cadreMessages;
	private VueGraphique vueGraphique;
	private VueTextuelle vueTextuelle;
	private EcouteurDeBoutons ecouteurDeBoutons;
	//private EcouteurDeSouris ecouteurDeSouris;
	//private EcouteurDeClavier ecouteurDeClavier;
	
	private final String[] intitulesBoutons = new String[]{CHARGER_PLAN,CHARGER_LIVRAISON};
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
		creeBoutons(controleur);
		
		cadreMessages = new JLabel();
		cadreMessages.setBorder(BorderFactory.createTitledBorder("Messages..."));
		
		//getContentPane().add(cadreMessages);
		
		setLayout(new BorderLayout());
		
		vueGraphique = new VueGraphique(p, echelleFenetre, this);
		vueTextuelle = new VueTextuelle(p, this);
		
		add(vueGraphique, BorderLayout.WEST);
		//add(vueTextuelle, BorderLayout.EAST);
		add(cadreMessages, BorderLayout.SOUTH);
		
		//ecouteurDeSouris = new EcouteurDeSouris(controleur,vueGraphique,this);
		//addMouseListener(ecouteurDeSouris);
		//addMouseMotionListener(ecouteurDeSouris);
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
		
		JPanel panel;
		
		add(panel = new JPanel(), BorderLayout.NORTH);	
		panel.setSize(1024, 50);

		for (int i=0; i < intitulesBoutons.length; i++)
		{
			JButton bouton = new JButton(intitulesBoutons[i]);
			boutons.add(bouton);
			bouton.setSize(largeurBouton,hauteurBouton);
			bouton.setFocusable(false);
			bouton.setFocusPainted(false);
			bouton.addActionListener(ecouteurDeBoutons);
			
			panel.add(bouton);
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
		setSize(500, 500);
		cadreMessages.setSize(largeurFenetre+60,80);
		cadreMessages.setLocation(0,hauteurFenetre-hauteurCadreMessages);
		vueGraphique.setLocation(largeurBouton, 0);
		vueTextuelle.setSize(largeurVueTextuelle+60,hauteurFenetre-hauteurCadreMessages);
		vueTextuelle.setLocation(10+vueGraphique.getLargeur(),0);
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
}
