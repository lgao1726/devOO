package vue;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import modele.Livraison;
import modele.Plan;
import controleur.Controleur;

/**
 * Classe Fenetre qui integre la vue textuelle et la vue graphique
 * @author H4101 Internal Corp
 * 
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame
{
	public static final String CHARGER_LIVRAISON = "Charger livraison";
	public static final String CHARGER_PLAN = "Charger plan";
	public static final String GENERE = "Feuille de route";
	public static final String UNDO = "Annuler";
	public static final String REDO = "Rétablir";
	public static final String CALCULER_TOURNEE = "Calculer trounée";
	public static final String ABOUT = "A propos";
	public static final String EXIT = "Quitter";
	
	@SuppressWarnings("unused")
	private Controleur controleur;
	private EcouteurDeSouris ecouteurDeSouris;
	private EcouterDeMvtSouris ecouteurDeMvtSouris;
	private EcouteurDeBoutons ecouteurDeBoutons;
	
	// Variables declaration - do not modify      
    /* Bar de menu */
    private JToolBar barOutil;
    
    private JButton btnChargerLivraison;
    private JButton btnChargerPlan;
    private JButton btnGenerer;
    private JButton btnRedo;
    private JButton btnUndo;
    
    /* Menu d'application */
    private JMenuBar menuBar;
    
    private JMenu menuEdition;
    private JMenu menuFichier;
    private JMenu menuGenere;
    private JMenu menuAide;
    
    /* Boutton de menu */
    private JMenuItem menuChargerLivraison;
    private JMenuItem menuChargerPlan;
    private JMenuItem menuQuitter;
    private JMenuItem menuGenerer;
    private JMenuItem menuTournee;
    private JMenuItem menuRedo;
    private JMenuItem menuUndo;
    private JMenuItem menuAbout;
    
    /* Boite de dialogue */
    private JLabel cardreMessage;
    private JPanel messagePanel;
    
    private VueGraphique vueGraphique;
    private JScrollPane scrollPane;
    private VueTextuelle vueTextuelle;

    /**
     * Constructeur de la fenêtre
     * @param plan le plan
     * @param controleur le controleur 
     */
    public Fenetre(Plan plan, Controleur controleur) 
    {
    	this.controleur = controleur;
        ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
    	
    	// Look and feel pour afficher les composants SWING en style de l'OS
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	
    	creerMenus();
        creerBarOutils();
        creerVues(plan, controleur);    
        creerBoiteDialogue();

        /* Proprité de la fenêtre */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Optimod'Lyon");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFocusCycleRoot(false);
        
        btnChargerPlan.setEnabled(true);
        
        ajouterComposants();
        pack();
        
        setVisible(true);
    }
    
    /**
     * Méthode qui active les boutons de modification
     */
	public void activerModification()
    {
    	vueTextuelle.activerModification();
    }

    
    /**
	 * Affiche un message box à l'utilisateur
	 * @param String message à afficher
	 */
	public void afficheMessageBox(String message) 
	{
		JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Méthode qui affiche une boite de dialogue à l'utlisateur pour confirmé un traitement
	 * @param String message à affichier
	 * @return
	 */
	public boolean afficherMessageConfirmation(String message)
	{
		int dialogButton = JOptionPane.YES_NO_OPTION;
		dialogButton = JOptionPane.showConfirmDialog (null, message,"Confirmation",dialogButton);
        
        return dialogButton == JOptionPane.YES_OPTION;
	}

    /**
     * Méthode ajouter les composants vue graphique, textuelle, barre d'outils et boite de dialogue aux bonne endroits  
     */
    private void ajouterComposants() 
    {
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(barOutil, GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(messagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vueTextuelle, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barOutil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane)
                    .addComponent(vueTextuelle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
	}
    
    /**
     * Méthode creer les deux vues textuelle et graphique
     * @param plan
     * @param controleur
     */
    private void creerVues(Plan plan, Controleur controleur) 
    {
    	vueGraphique = new VueGraphique(plan, 1, this);
    	vueTextuelle = new VueTextuelle(plan, controleur);
    	
        scrollPane = new JScrollPane(vueGraphique);
        
        ecouteurDeMvtSouris = new EcouterDeMvtSouris(controleur, vueGraphique);
        ecouteurDeSouris = new EcouteurDeSouris(controleur,vueGraphique);
        
		vueGraphique.getAccessibleContext().setAccessibleParent(scrollPane);
        vueGraphique.addMouseListener(ecouteurDeSouris);
        vueGraphique.addMouseMotionListener(ecouteurDeMvtSouris);
        
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(vueGraphique);
        scrollPane.setPreferredSize(new Dimension(200, 200));	
	}

    /**
     * Méthode créér la boite de dialogue de l'utilisateur
     */
	private void creerBoiteDialogue() 
    {
    	messagePanel = new JPanel();
        cardreMessage = new JLabel();
        
        messagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Boite de dialogue"));

        cardreMessage.setFont(new java.awt.Font("Tahoma", 2, 11));
        cardreMessage.setText("Pour commancer : charger un plan");
        
        GroupLayout messagePanelLayout = new GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, messagePanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(cardreMessage, GroupLayout.PREFERRED_SIZE, 847, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardreMessage)
                .addContainerGap(22, Short.MAX_VALUE))
        );
	}
	
	/**
	 * Méthode qui le menu dans la fenêtre
	 */
	private void creerMenus() 
    {
    	menuBar = new JMenuBar();
        menuFichier = new JMenu("Fichier");
        menuEdition = new JMenu("Edition");
        menuGenere = new JMenu("Générer");
        menuAide = new JMenu("Aide");
        
        menuChargerPlan = new JMenuItem(CHARGER_PLAN);
        menuChargerLivraison = new JMenuItem(CHARGER_LIVRAISON);
        menuQuitter = new JMenuItem(EXIT);
        menuUndo = new JMenuItem(UNDO);
        menuRedo = new JMenuItem(REDO);
        menuTournee = new JMenuItem(CALCULER_TOURNEE);
        menuGenerer = new JMenuItem(GENERE);
        menuAbout = new JMenuItem(ABOUT);
        
        JMenu[] menu = {menuFichier, menuEdition, menuGenere, menuAide};
        JMenuItem[][] menuItem = {{menuChargerPlan, menuChargerLivraison, menuQuitter}, 
        						  {menuUndo, menuRedo}, 
        						  {menuTournee, menuGenerer}, 
        						  {menuAbout}};

        int[][] keys = {{KeyEvent.VK_P, KeyEvent.VK_D, KeyEvent.VK_F}, 
        				{KeyEvent.VK_Z, KeyEvent.VK_Y},
        				{KeyEvent.VK_T, KeyEvent.VK_G},
        				{KeyEvent.VK_B}};
        		
        // Créé les menu item de chaque menu
        for (int i=0; i < menu.length; i++)
        
        	for (int j=0; j < menuItem[i].length; j++)
        	{
        		menuItem[i][j].setAccelerator(KeyStroke.getKeyStroke(keys[i][j], java.awt.event.InputEvent.CTRL_MASK));
        		menuItem[i][j].addActionListener(ecouteurDeBoutons);
        		menu[i].add(menuItem[i][j]);
        	}
        
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        menuBar.add(menuGenere);
        menuBar.add(menuAide);
        
        setJMenuBar(menuBar);
	}

	/**
	 * Méthode qui crée la bar d'outils
	 */
	private void creerBarOutils() 
    {
    	barOutil = new JToolBar();
    	
        btnChargerPlan = new JButton(CHARGER_PLAN);
        btnChargerLivraison = new JButton(CHARGER_LIVRAISON);
        btnUndo = new JButton(UNDO);
        btnRedo = new JButton(REDO);
        btnGenerer = new JButton(GENERE);
    	
    	barOutil.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        barOutil.setRollover(true);
        barOutil.setAlignmentX(0.0F);
        
        JButton[] listBoutons = {btnChargerPlan, btnChargerLivraison, btnUndo, btnRedo, btnGenerer};
        String[] icons = {"add_map.png", "add_delivery.png", "undo.png", "redo.png", "generate.png"};
        
        for (int i=0; i < listBoutons.length; i++)
        {
	        listBoutons[i].setIcon(new ImageIcon(getClass().getResource("/vue/icons/" + icons[i]))); 
	        listBoutons[i].setAlignmentX(5.0F);
	        listBoutons[i].setAlignmentY(5.0F);
	        listBoutons[i].setFocusable(false);
	        listBoutons[i].setEnabled(false);
	        listBoutons[i].setHorizontalTextPosition(SwingConstants.CENTER);
	        listBoutons[i].setMargin(new java.awt.Insets(5, 5, 5, 5));
	        listBoutons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
	        listBoutons[i].addActionListener(ecouteurDeBoutons);
	        
	        barOutil.add(listBoutons[i]);
	        
	        if (i != listBoutons.length - 1)
	        	
	        	barOutil.add(new JToolBar.Separator());
        }

        
	}          
    
    /**
	 * Affiche message dans la fenetre de dialogue avec l'utilisateur
	 * @param String message à affichier
	 */
	public void afficheMessage(String message) {
		cardreMessage.setText(message);
	}
    
	/**
	 * Getteur de l'échelle de la vue graphique
	 * @return int echelle de la vueGraphique
	 */
    public int getEchelle()
    {
		return vueGraphique.getEchelle();
	}
	
    /**
     * Méthode qui change le cuseur de la souris en HAND si on le passe sur un noeud dans le plan
     */
	public void sourisSurNoeud()
	{
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Méthode qui rétablit mle curseur de la souris à l'état normale si le cuseur est n'est sur un noeud
	 */
	public void sourisPasSurNoeud()
	{
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
     
	/**
	 * Méthode qui sélectionne une livraison dans la vue textuelle
	 * @param Livraison 
	 */
	public void selectionnerLivraisonTextuelle(Livraison liv)
	{
		vueTextuelle.selectionnerLivraisonTextuelle(liv);
	}
	
	/**
	 * Méthode qui active/déactive le bouton valider
	 * @param state true designe activé le bouton, false déactivé
	 */
    public void changerValider(boolean state)
    {
    	vueTextuelle.changerValider(state);
    }
    
    /**
     * Méthode qui active le bouton chargement livraison
     */
	public void activerChargementLivraison() 
	{
		btnChargerLivraison.setEnabled(true);
	}
	
	/**
	 * Méthode qui active les boutons quand la tournée est calculée
	 */
	public void activerUndoRedoGenerer() 
	{
		btnUndo.setEnabled(true);
		btnRedo.setEnabled(true);
		btnGenerer.setEnabled(true);
	}
	
	/**
	 * Méthode qui active les boutons Undo/Redo/Generer
	 */
	public void desacactiverUndoRedoGenerer() 
	{
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnGenerer.setEnabled(false);
	}
}
