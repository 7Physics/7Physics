package fr.setphysics.setphysics.gui;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import fr.setphysics.common.geom.Position;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.components.CamPanel;
import fr.setphysics.setphysics.gui.components.EnvPanel;
import fr.setphysics.setphysics.gui.components.PreviewPanel;

import java.awt.*;
import java.awt.event.*;

public class GUI {

    /* *********************************** *
     * Définition des variables/constantes *
     * *********************************** */
    // Définition des ImageIcon
	public static final ImageIcon ICONPROJET = new ImageIcon(GUI.class.getResource("/images/projet.png"));
    public static final ImageIcon PLAY = new ImageIcon(GUI.class.getResource("/images/play.png"));
    public static final ImageIcon PLUS = new ImageIcon(GUI.class.getResource("/images/plus.png"));
    public static final ImageIcon ZOOMIN = new ImageIcon(GUI.class.getResource("/images/zoom-in.png"));
    public static final ImageIcon ZOOMOUT = new ImageIcon(GUI.class.getResource("/images/zoom-out.png"));
    public static final ImageIcon CUBE = new ImageIcon(GUI.class.getResource("/images/cube.png"));
    public static final ImageIcon SPHERE = new ImageIcon(GUI.class.getResource("/images/sphere.png"));
    public static final ImageIcon PYRAMID = new ImageIcon(GUI.class.getResource("/images/pyramid.png"));
    public static final ImageIcon CONE = new ImageIcon(GUI.class.getResource("/images/cone.png"));


    /* ************************************* *
     * Création des variables Frame et Panel *
     * ************************************* */
    // Fenêtre principale
    private JFrame fenetre;

    // Panels de la fenêtre
    private JSplitPane verticalSplitPanel;
    private JSplitPane leftSplitPanel;
    private JSplitPane rightSplitPanel;
    private JPanel topPanelLeft;
    private JPanel bottomPanelLeft;
    private JPanel topPanelRight;
    private JPanel bottomPanelRight;

    /* ***************************************** *
     * Création du MenuBar en haut de la fenêtre *
     * ***************************************** */
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem boutonMenuQuitter = new JMenuItem("Quitter");

    /* ******************************************************************* *
     * Création des onglets dans la partie inférieure droite de la fenêtre *
     * ******************************************************************* */
    private JTabbedPane ongletsBottom;

    /* ******************************************************************* *
     * Création des onglets dans la partie inférieure droite de la fenêtre *
     * ******************************************************************* */
    private JTabbedPane ongletsTop;

    /**
     * Construction de la fenêtre
     */
    public GUI() {
        /* *********************************** *
         * Définition de la fenêtre principale *
         * *********************************** */
        fenetre = new JFrame("7Physics");
        fenetre.setPreferredSize(new Dimension(1200, 700));
        fenetre.setMinimumSize(new Dimension(1200, 700));
        fenetre.setResizable(false);



        /* *************************************** *
         * Initialisation des Panels de la fenêtre *
         * *************************************** */
        verticalSplitPanel = new JSplitPane();
        leftSplitPanel = new JSplitPane();
        rightSplitPanel = new JSplitPane();
        topPanelLeft = new JPanel();
        bottomPanelLeft = new JPanel();
        topPanelRight = new JPanel();
        bottomPanelRight = new JPanel();



        /* **************************** *
         * Empêche le resize des Panels *
         * **************************** */
        verticalSplitPanel.setEnabled(false);
        leftSplitPanel.setEnabled(false);
        rightSplitPanel.setEnabled(false);



        /* ******************** *
         * Gestion des couleurs *
         * ******************** */
        bottomPanelLeft.setBackground(new Color(85, 130, 139));
        topPanelRight.setBackground(new Color(87, 115, 153));
        bottomPanelRight.setBackground(new Color(78, 104, 138));



        /* ******************** *
         * Découpage des Panels *
         * ******************** */
        // Découpage vertical de la Frame en deux
        verticalSplitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        verticalSplitPanel.setDividerLocation(880);
        verticalSplitPanel.setLeftComponent(leftSplitPanel);
        verticalSplitPanel.setRightComponent(rightSplitPanel);

        // Découpage horizontal du Panel de gauche en deux
        leftSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        leftSplitPanel.setDividerLocation(510);
        leftSplitPanel.setTopComponent(topPanelLeft);
        leftSplitPanel.setBottomComponent(bottomPanelLeft);

        // Découpage horizontal du Panel de droite en deux
        rightSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPanel.setDividerLocation(240);
        rightSplitPanel.setTopComponent(topPanelRight);
        rightSplitPanel.setBottomComponent(bottomPanelRight);



        /* ******************************************** *
         * Gestion du contenu du Panel en haut à gauche *
         * La scene de rendu 3D                         *
         * ******************************************** */
        topPanelLeft.setLayout(new BorderLayout());

		final GLProfile profile = GLProfile.get( GLProfile.GL2 );
		GLCapabilities capabilities = new GLCapabilities( profile );

		// The canvas
        //final GLCanvas glcanvas = new GLCanvas(capabilities);
        final GLJPanel glcanvas = new GLJPanel(capabilities);
		Position position = new Position(-3.5,0.3,0);
		Camera cam = new Camera(position);
		final Scene3D scene = new Scene3D(cam);

		glcanvas.addGLEventListener(scene);
		glcanvas.addKeyListener(scene.getKeyListener());
		glcanvas.addMouseMotionListener(scene);
		glcanvas.addMouseWheelListener(scene);

		final FPSAnimator animator = new FPSAnimator(glcanvas, 300,true);

		animator.start();
		topPanelLeft.add(glcanvas, BorderLayout.CENTER);



        /* ******************** *
         * Gestion de la caméra *
         * ******************** */
        // Création du Panel des boutons
        JPanel gestionCam = new CamPanel(cam);
        gestionCam.setBackground(new Color(49, 66, 106));
        topPanelLeft.add(gestionCam, BorderLayout.WEST);


        /* ******************************************** *
         * Gestion du contenu du Panel en bas à gauche  *
         * La gestion des objets présents dans la scene *
         * ******************************************** */
        bottomPanelLeft.setLayout(new BorderLayout());

        // Gestion du bouton de lancement de simulation
        JButton buttonPlay = new JButton(PLAY);
        buttonPlay.setBackground(new Color(189, 213, 234));
        bottomPanelLeft.add(buttonPlay, BorderLayout.NORTH);
        buttonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Lancement de la simulation !");
            }
        });

        // Texte décoratif
        JLabel descObje = new JLabel("Gestion des objets", SwingConstants.CENTER);
        bottomPanelLeft.add(descObje, BorderLayout.CENTER);


        /* ******************************************** *
         * Gestion du contenu du Panel en haut à droite *
         * Ajout et création d'objets                   *
         * ******************************************** */
        ongletsTop = new PreviewPanel(scene);
        topPanelRight.setLayout(new BorderLayout());


        /* ******************************************* *
         * Gestion du contenu du Panel en bas à droite *
         * Onglets des paramètres des objets           *
         * ******************************************* */
        ongletsBottom = new EnvPanel(scene);
        bottomPanelRight.setLayout(new BorderLayout());


        /* ************** *
         * ActionListener *
         * ************** */
        ActionListener quitter = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                close();
            }
        };



        /* ************ *
         * MouseAdapter *
         * ************ */



        /* ******************* *
         * Afficher le MenuBar *
         * ******************* */
        fenetre.setJMenuBar(menuBar);
        boutonMenuQuitter.addActionListener(quitter);
        menu.add(boutonMenuQuitter);
        menuBar.add(menu);



        /* ************************************************ *
         * Gestion de fermeture de la fenêtre avec la croix *
         * ************************************************ */
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /* ******************************************* *
         * Ajout du contenu dans les différents Panels *
         * ******************************************* */
        topPanelRight.add(ongletsTop, BorderLayout.CENTER);
        bottomPanelRight.add(ongletsBottom, BorderLayout.CENTER);
        fenetre.getContentPane().add(verticalSplitPanel);



        /* *********************** *
         * Affichage de la fenêtre *
         * *********************** */
        fenetre.setIconImage(ICONPROJET.getImage());
        //fenetre.pack();
        fenetre.setVisible(true);
    }


    /**
     * Quitter l'application
     */
    public void close() {
        System.out.println("Fermeture de l'application...");
        System.exit(0);
    }
}