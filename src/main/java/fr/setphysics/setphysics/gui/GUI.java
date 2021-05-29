package fr.setphysics.setphysics.gui;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.geom.shape.Sphere;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    /* *********************************** *
     * Définition des variables/constantes *
     * *********************************** */
    // Définition des ImageIcon
	private static final ImageIcon ICONPROJET = new ImageIcon(GUI.class.getResource("/images/projet.png"));
    private static final ImageIcon PLAY = new ImageIcon(GUI.class.getResource("/images/play.png"));
    private static final ImageIcon PLUS = new ImageIcon(GUI.class.getResource("/images/plus.png"));
    private static final ImageIcon ZOOMIN = new ImageIcon(GUI.class.getResource("/images/zoom-in.png"));
    private static final ImageIcon ZOOMOUT = new ImageIcon(GUI.class.getResource("/images/zoom-out.png"));
    private static final ImageIcon CUBE = new ImageIcon(GUI.class.getResource("/images/cube.png"));
    private static final ImageIcon SPHERE = new ImageIcon(GUI.class.getResource("/images/sphere.png"));
    private static final ImageIcon PYRAMID = new ImageIcon(GUI.class.getResource("/images/pyramid.png"));
    private static final ImageIcon CONE = new ImageIcon(GUI.class.getResource("/images/cone.png"));


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
    private JPanel ongletEnvironnement;
    private JPanel ongletObjet;


    /* ******************************************************************* *
     * Création des onglets dans la partie inférieure droite de la fenêtre *
     * ******************************************************************* */
    private JTabbedPane ongletsTop;
    private JPanel ongletCube;
    private JPanel ongletSphere;
    private JPanel ongletPyramid;
    private JPanel ongletCone;



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
        JPanel gestionCam = new JPanel();
        gestionCam.setBackground(new Color(49, 66, 106));
        topPanelLeft.add(gestionCam, BorderLayout.WEST);

        // Initialisation du GridLayout
        GridLayout testLayout = new GridLayout(12, 1);
        gestionCam.setLayout(testLayout);
        testLayout.setHgap(-1);
        testLayout.setVgap(15);

        // Remplissage de vide
        JLabel vide1 = new JLabel();
        gestionCam.add(vide1);

        // Gestion du bouton de zoom
        JButton zoomInButton = new JButton(ZOOMIN);
        zoomInButton.setPreferredSize(new Dimension(20, 20));
        zoomInButton.setOpaque(false);
        zoomInButton.setContentAreaFilled(false);
        zoomInButton.setBorderPainted(false);
        gestionCam.add(zoomInButton);
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cam.zoom(-2.0);
            }
        });

        // Gestion du bouton de dézoom
        JButton zoomOutButton = new JButton(ZOOMOUT);
        zoomOutButton.setPreferredSize(new Dimension(20, 20));
        zoomOutButton.setOpaque(false);
        zoomOutButton.setContentAreaFilled(false);
        zoomOutButton.setBorderPainted(false);
        gestionCam.add(zoomOutButton);
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cam.dezoom();
            }
        });



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
        ongletsTop = new JTabbedPane(SwingConstants.TOP);
        topPanelRight.setLayout(new BorderLayout());

        // _____________________________
        // Gestion de l'ajout du cube
        ongletCube = new JPanel();
        ongletsTop.addTab("", ongletCube);
        JPanel cubePane = new JPanel();
        ongletCube.setLayout(new BorderLayout());
        ongletCube.add(cubePane, BorderLayout.CENTER);
        cubePane.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel cubePaneLeft = new JPanel();
        cubePaneLeft.setBackground(new Color(87, 115, 153));
        cubePane.add(cubePaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel cubePaneRight = new JPanel();
        cubePaneRight.setBackground(new Color(78, 104, 138));
        cubePane.add(cubePaneRight, BorderLayout.CENTER);

        // Initialisation du GridLayout
        GridLayout cubeLayout = new GridLayout(6, 2);
        cubePaneLeft.setLayout(cubeLayout);
        cubeLayout.setHgap(-1);
        cubeLayout.setVgap(15);

        // Paramètre "x"
        JLabel textParamCubeX = new JLabel("x :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeX);
        JTextField paramCubeX = new JTextField();
        paramCubeX.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeX);

        // Paramètre "y"
        JLabel textParamCubeY = new JLabel("y :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeY);
        JTextField paramCubeY = new JTextField();
        paramCubeY.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeY);

        // Paramètre "z"
        JLabel textParamCubeZ = new JLabel("z :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeZ);
        JTextField paramCubeZ = new JTextField();
        paramCubeZ.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeZ);

        // Paramètre "width"
        JLabel textParamCubeWidth = new JLabel("Largeur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeWidth);
        JTextField paramCubeWidth = new JTextField();
        paramCubeWidth.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeWidth);

        // Paramètre "length"
        JLabel textParamCubeLength = new JLabel("Longueur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeLength);
        JTextField paramCubeLength = new JTextField();
        paramCubeLength.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeLength);

        // Paramètre "height"
        JLabel textParamCubeHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeHeight);
        JTextField paramCubeHeight = new JTextField();
        paramCubeHeight.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeHeight);

        // Configuration de la partie droite
        cubePaneRight.setLayout(new BorderLayout());
        JPanel previewCube = new JPanel();
        JPanel addCubeButtonPanel = new JPanel();
        addCubeButtonPanel.setLayout(new BorderLayout());
        previewCube.setBackground(new Color(78, 104, 138));
        addCubeButtonPanel.setBackground(new Color(78, 104, 138));
        cubePaneRight.add(previewCube, BorderLayout.CENTER);
        cubePaneRight.add(addCubeButtonPanel, BorderLayout.SOUTH);
        JPanel addCubeEmptyPanel = new JPanel();
        addCubeEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addCubeButton = new JButton(PLUS);
        addCubeButtonPanel.add(addCubeEmptyPanel, BorderLayout.CENTER);
        addCubeButtonPanel.add(addCubeButton, BorderLayout.EAST);

        // Gestion du bouton "+"
        addCubeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = Double.parseDouble(paramCubeX.getText());
                double y = Double.parseDouble(paramCubeY.getText());
                double z = Double.parseDouble(paramCubeZ.getText());
                double w = Double.parseDouble(paramCubeWidth.getText());
                double l = Double.parseDouble(paramCubeLength.getText());
                double h = Double.parseDouble(paramCubeHeight.getText());
                
                Logger.info("Création d'un cube. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Width: " + w + ", Length: " + l + ", Height: " + h);
                
                scene.addObject(new Object3D(new Position(x, y, z),
                		new Cuboid(w, l, h),
                		new Color(128,128,128,128),
                		Color.WHITE));
            }
        });

        // __________________________________
        // Gestion de l'ajout de la sphere
        ongletSphere = new JPanel();
        ongletsTop.addTab("", ongletSphere);
        JPanel spherePane = new JPanel();
        ongletSphere.setLayout(new BorderLayout());
        ongletSphere.add(spherePane, BorderLayout.CENTER);
        spherePane.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel spherePaneLeft = new JPanel();
        spherePaneLeft.setBackground(new Color(87, 115, 153));
        spherePane.add(spherePaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel spherePaneRight = new JPanel();
        spherePaneRight.setBackground(new Color(78, 104, 138));
        spherePane.add(spherePaneRight, BorderLayout.CENTER);

        // Initialisation du GridLayout
        GridLayout sphereLayout = new GridLayout(6, 2);
        spherePaneLeft.setLayout(sphereLayout);
        sphereLayout.setHgap(-1);
        sphereLayout.setVgap(15);

        // Paramètre "x"
        JLabel textParamSphereX = new JLabel("x :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereX);
        JTextField paramSphereX = new JTextField();
        paramSphereX.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereX);

        // Paramètre "y"
        JLabel textParamSphereY = new JLabel("y :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereY);
        JTextField paramSphereY = new JTextField();
        paramSphereY.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereY);

        // Paramètre "z"
        JLabel textParamSphereZ = new JLabel("z :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereZ);
        JTextField paramSphereZ = new JTextField();
        paramSphereZ.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereZ);

        // Paramètre "width"
        JLabel textParamSphereRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereRadius);
        JTextField paramSphereRadius = new JTextField();
        paramSphereRadius.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereRadius);

        // Configuration de la partie droite
        spherePaneRight.setLayout(new BorderLayout());
        JPanel previewSphere = new JPanel();
        JPanel addSphereButtonPanel = new JPanel();
        addSphereButtonPanel.setLayout(new BorderLayout());
        previewSphere.setBackground(new Color(78, 104, 138));
        addSphereButtonPanel.setBackground(new Color(78, 104, 138));
        spherePaneRight.add(previewSphere, BorderLayout.CENTER);
        spherePaneRight.add(addSphereButtonPanel, BorderLayout.SOUTH);
        JPanel addSphereEmptyPanel = new JPanel();
        addSphereEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addSphereButton = new JButton(PLUS);
        addSphereButtonPanel.add(addSphereEmptyPanel, BorderLayout.CENTER);
        addSphereButtonPanel.add(addSphereButton, BorderLayout.EAST);

        // Gestion du bouton "+"
        addSphereButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = Double.parseDouble(paramSphereX.getText());
                double y = Double.parseDouble(paramSphereY.getText());
                double z = Double.parseDouble(paramSphereZ.getText());
                double r = Double.parseDouble(paramSphereRadius.getText());

                Logger.info("Création d'une sphere. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Rayon: " + r);
                
                scene.addObject(new Object3D(new Position(x, y, z),
                		new Sphere(r, 3),
                		new Color(0,128,128,128),
                		new Color(0,0,0,0)));
            }
        });

        // ____________________________________
        // Gestion de l'ajout de la pyramide
        /*ongletPyramid = new JPanel();
        ongletsTop.addTab("", ongletPyramid);
        JLabel descPyramid = new JLabel("Pyramide");
        ongletPyramid.setLayout(new GridBagLayout());
        ongletPyramid.add(descPyramid);*/

        // _____________________________
        // Gestion de l'ajout du cone
        /*ongletCone = new JPanel();
        ongletsTop.addTab("", ongletCone);
        JLabel descCone = new JLabel("Cone");
        ongletCone.setLayout(new GridBagLayout());
        ongletCone.add(descCone);*/

        // Gestion des icons des onglets
        ongletsTop.setIconAt(0, CUBE);
        ongletsTop.setIconAt(1, SPHERE);
        /*ongletsTop.setIconAt(2, PYRAMID);
        ongletsTop.setIconAt(3, CONE);*/



        /* ******************************************* *
         * Gestion du contenu du Panel en bas à droite *
         * Onglets des paramètres des objets           *
         * ******************************************* */
        ongletsBottom = new JTabbedPane(SwingConstants.TOP);
        bottomPanelRight.setLayout(new BorderLayout());

        // Affichage et contenu de l'onglet "Paramètres de l'environnement"
        ongletEnvironnement = new JPanel();
        ongletsBottom.addTab("Environnement", ongletEnvironnement);
        JLabel descEnv = new JLabel("Paramètres de l'environnement");
        ongletEnvironnement.setLayout(new GridBagLayout());
        ongletEnvironnement.add(descEnv);

        // Affichage et contenu de l'onglet "Paramètres de l'objet"
        ongletObjet = new JPanel();
        ongletsBottom.addTab("Objet", ongletObjet);
        JLabel descObj = new JLabel("Paramètres de l'objet");
        ongletObjet.setLayout(new GridBagLayout());
        ongletObjet.add(descObj);



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