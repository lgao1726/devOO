package vue;

import java.awt.Choice;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import modele.Livraison;
import modele.Plan;
import controleur.Controleur;
public class Fenetre extends JFrame
{
	
	
	private static final long serialVersionUID = 1L;
	private Controleur controleur;
	private EcouteurDeSouris ecouteurDeSouris;
	private EcouterDeMvtSouris ecouteurDeMvtSouris;
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
    private TextuelleView vueTextuelle;

    /**
     * Creates new form Fenetre
     */
    public Fenetre(Plan p, Controleur controleur) 
    {
    	
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
    	
    	/* Bar d'outils */
        creerBarOutils();
        
        /* Menu */
        creerMenus();
        
        this.controleur = controleur;
        
        vueGraphique = new VueGraphique(p, 1, this);
		ecouteurDeMvtSouris = new EcouterDeMvtSouris(controleur, vueGraphique, this);
		vueGraphique.addMouseMotionListener(ecouteurDeMvtSouris);
        
        /* Vue graphique */
        scrollPane = new JScrollPane(vueGraphique);
        
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setAutoscrolls(true);
        scrollPane.setViewportView(vueGraphique);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        
        vueGraphique.getAccessibleContext().setAccessibleParent(scrollPane);
        
        /* Vue textuelle */
        vueTextuelle = new TextuelleView(p,controleur);
        
        ecouteurDeSouris = new EcouteurDeSouris(controleur,vueGraphique,this);
        vueGraphique.addMouseListener(ecouteurDeSouris);
        
        vueGraphique.getAccessibleContext().setAccessibleParent(scrollPane);
        
        
        /* Boite de dialogue */
        creerBoiteDialogue();

        /* Proprit� de la fen�tre */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion des livraisons");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFocusCycleRoot(false);
        setName("iFrame"); // NOI18N
        
        ajouterComposants();

        pack();
        
    	setVisible(true);
    }
    
    public void activerModification()
    {
    	vueTextuelle.activerModification();
    }

    
    /**
	 * Affiche un message box à l'utilisateur
	 * @param message
	 */
	public void afficheMessageBox(String message) 
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	public boolean afficherMessageConfirmation(String message)
	{
		int dialogButton = JOptionPane.YES_NO_OPTION;
		dialogButton = JOptionPane.showConfirmDialog (null, message,"Confirmation",dialogButton);
        
        return dialogButton == JOptionPane.YES_OPTION;
	}
    

    
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

	private void creerBoiteDialogue() 
    {
    	messagePanel = new JPanel();
        cardreMessage = new JLabel();
        
        messagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Boite de dialogue"));

        cardreMessage.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
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
	 * Definit la taille du cadre et de ses composants en fonction de la taille de la vue
	 * @param largeurVue
	 * @param hauteurVue
	 */
	
	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}

	private void creerMenus() 
    {
    	menuBar = new JMenuBar();
        menuFichier = new JMenu();
        menuChargerPlan = new JMenuItem();
        menuChargerLivraison = new JMenuItem();
        menuQuitter = new JMenuItem();
        menuEdition = new JMenu();
        menuUndo = new JMenuItem();
        menuRedo = new JMenuItem();
        menuGenere = new JMenu();
        menuTournee = new JMenuItem();
        menuGenerer = new JMenuItem();
        menuAide = new JMenu();
        menuAbout = new JMenuItem();
        
        menuFichier.setText("Fichier");

        menuChargerPlan.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menuChargerPlan.setText("Charger Plan");
        menuChargerPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				controleur.chargerPlan();
			}
		});
        menuFichier.add(menuChargerPlan);

        menuChargerLivraison.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuChargerLivraison.setText("Charger Demande de Livraison");
        menuChargerLivraison.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) 
            {
            	controleur.chargerLivraison();
            }
        });
        menuFichier.add(menuChargerLivraison);

        menuQuitter.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuQuitter.setText("Quitter");
        menuQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				if (afficherMessageConfirmation("Voulez-vous vraiment quittez l'application ?"))
					
					System.exit(EXIT_ON_CLOSE);
			}
		});
        menuFichier.add(menuQuitter);

        menuBar.add(menuFichier);

        menuEdition.setText("Edition");

        menuUndo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuUndo.setText("Annuler");
        menuUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controleur.undo();
			}
		});
        menuEdition.add(menuUndo);

        menuRedo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        menuRedo.setText("R�tablir");
        menuRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.redo();
			}
		});
        
        menuEdition.add(menuRedo);

        menuBar.add(menuEdition);

        menuTournee.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menuTournee.setText("Calculer Tourn�e");
        menuTournee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				controleur.calculerTournee();
			}
		});
        
        menuGenere.setText("G�n�rer");
        menuGenere.add(menuTournee);

        menuGenerer.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        menuGenerer.setText("G�n�rer Feuille de route");
        menuGenere.add(menuGenerer);
        menuGenere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.genererFeuilleDeRoute();
			}
		});

        menuBar.add(menuGenere);

        menuAide.setText("Aide");

        menuAbout.setText("A propos");
        menuAide.add(menuAbout);

        menuBar.add(menuAide);

        setJMenuBar(menuBar);
		
	}

	private void creerBarOutils() 
    {
    	barOutil = new JToolBar();
        btnChargerPlan = new JButton();
        btnChargerLivraison = new JButton();
        btnUndo = new JButton();
        btnRedo = new JButton();
        btnGenerer = new JButton();
    	
    	barOutil.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        barOutil.setRollover(true);
        barOutil.setAlignmentX(0.0F);

        btnChargerPlan.setIcon(new ImageIcon(getClass().getResource("/vue/icons/add_map.png"))); // NOI18N
        btnChargerPlan.setText("Charger Plan");
        btnChargerPlan.setAlignmentX(5.0F);
        btnChargerPlan.setAlignmentY(5.0F);
        btnChargerPlan.setFocusable(false);
        btnChargerPlan.setHorizontalTextPosition(SwingConstants.CENTER);
        btnChargerPlan.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnChargerPlan.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        btnChargerPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controleur.chargerPlan();
			}
		});
        
        barOutil.add(btnChargerPlan);
        barOutil.add(new JToolBar.Separator());

        btnChargerLivraison.setIcon(new ImageIcon(getClass().getResource("/vue/icons/add_delivery.png"))); // NOI18N
        btnChargerLivraison.setText("Charger Livraisons");
        btnChargerLivraison.setAlignmentX(5.0F);
        btnChargerLivraison.setAlignmentY(5.0F);
        btnChargerLivraison.setFocusable(false);
        btnChargerLivraison.setHorizontalTextPosition(SwingConstants.CENTER);
        btnChargerLivraison.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnChargerLivraison.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnChargerLivraison.setEnabled(false);
        
        btnChargerLivraison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controleur.chargerLivraison();
			}
		});
        
        barOutil.add(btnChargerLivraison);
        barOutil.add(new JToolBar.Separator());

        btnUndo.setIcon(new ImageIcon(getClass().getResource("/vue/icons/undo.png"))); // NOI18N
        btnUndo.setText("Annuler");
        btnUndo.setAlignmentX(5.0F);
        btnUndo.setAlignmentY(5.0F);
        btnUndo.setFocusable(false);
        btnUndo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUndo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnUndo.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnUndo.setEnabled(false);
        btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controleur.undo();
			}
		});
        barOutil.add(btnUndo);
        barOutil.add(new JToolBar.Separator());

        btnRedo.setIcon(new ImageIcon(getClass().getResource("/vue/icons/redo.png"))); // NOI18N
        btnRedo.setText("R�tablir");
        btnRedo.setAlignmentX(5.0F);
        btnRedo.setAlignmentY(5.0F);
        btnRedo.setEnabled(false);
        btnRedo.setFocusable(false);
        btnRedo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRedo.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnRedo.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controleur.redo();
			}
		});
        barOutil.add(btnRedo);
        barOutil.add(new JToolBar.Separator());

        btnGenerer.setIcon(new ImageIcon(getClass().getResource("/vue/icons/generate.png"))); // NOI18N
        btnGenerer.setText("G�n�rer");
        btnGenerer.setAlignmentX(5.0F);
        btnGenerer.setAlignmentY(5.0F);
        btnGenerer.setFocusable(false);
        btnGenerer.setHorizontalTextPosition(SwingConstants.CENTER);
        btnGenerer.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnGenerer.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnGenerer.setEnabled(false);
        barOutil.add(btnGenerer);
	}
	
	public int getVerticalScrollBar()
	{
		return scrollPane.getVerticalScrollBar().getValue();
	}
	
	public int getHorizontalScrollBar()
	{
		return scrollPane.getHorizontalScrollBar().getValue();
	}

	public static void getInstance()
    {
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void lancer() 
    {
    	
    }             
    
    /**
	 * Affiche message dans la fenetre de dialogue avec l'utilisateur
	 * @param message
	 */
	public void afficheMessage(String message) {
		cardreMessage.setText(message);
	}
    
    public int getEchelle(){
		return vueGraphique.getEchelle();
	}
	
	public void sourisSurNoeud(){
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void sourisPasSurNoeud(){
		vueGraphique.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
                                      
    
	public void activerChargementLivraison() {
		btnChargerLivraison.setEnabled(true);
	}
	
	public void activerUndoRedoGenerer() {
		btnUndo.setEnabled(true);
		btnRedo.setEnabled(true);
		btnGenerer.setEnabled(true);
	}
	
	public void desacactiverUndoRedoGenerer() {
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		btnGenerer.setEnabled(false);

	}
	
	public void selectionnerLivraisonTextuelle(Livraison liv)
	{
		System.out.println("dans fenetre");
		vueTextuelle.selectionnerLivraisonTextuelle(liv);
	}
	
    
    // End of variables declaration        
}
