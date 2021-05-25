package fr.setphysics.setphysics.ihm;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ihm {

    /* *********************************** *
     * Définition des variables/constantes *
     * *********************************** */
    // Définition des ImageIcon
    private static final ImageIcon ICONPROJET = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/projet.png");
    private static final ImageIcon PLAY = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/play.png");
    private static final ImageIcon CUBE = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/cube.png");
    private static final ImageIcon SPHERE = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/sphere.png");
    private static final ImageIcon PYRAMID = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/pyramid.png");
    private static final ImageIcon CONE = new ImageIcon("src/main/java/fr/setphysics/setphysics/ihm/resources/cone.png");


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
    private final JMenuItem boutonMenuQuitter = new JMenuItem("Quitter");


    /* ******************************************************************* *
     * Création des onglets dans la partie inférieure droite de la fenêtre *
     * ******************************************************************* */
    private JTabbedPane onglets;
    private JPanel ongletEnvironnement;
    private JPanel ongletObjet;



    /**
     * Construction de la fenêtre
     */
    public ihm() {
        /* *********************************** *
         * Définition de la fenêtre principale *
         * *********************************** */
        fenetre = new JFrame("7Physics");
        fenetre.setPreferredSize(new Dimension(1400, 800));
        fenetre.setMinimumSize(new Dimension(1400, 800));
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
        topPanelLeft.setBackground(new Color(46, 76, 143));
        bottomPanelLeft.setBackground(new Color(85, 130, 139));
        topPanelRight.setBackground(new Color(87, 115, 153));
        bottomPanelRight.setBackground(new Color(78, 104, 138));



        /* ******************** *
         * Découpage des Panels *
         * ******************** */
        // Découpage vertical de la Frame en deux
        verticalSplitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        verticalSplitPanel.setDividerLocation(1050);
        verticalSplitPanel.setLeftComponent(leftSplitPanel);
        verticalSplitPanel.setRightComponent(rightSplitPanel);

        // Découpage horizontal du Panel de gauche en deux
        leftSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        leftSplitPanel.setDividerLocation(600);
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
        JLabel descScene = new JLabel("Scene 3D");
        topPanelLeft.setLayout(new GridBagLayout());
        topPanelLeft.add(descScene);



        /* ******************************************** *
         * Gestion du contenu du Panel en bas à gauche  *
         * La gestion des objets présents dans la scene *
         * ******************************************** */
        bottomPanelLeft.setLayout(new BorderLayout());

        // Gestion du bouton de lancement de simulation
        JButton buttonPlay = new JButton(PLAY);
        buttonPlay.setBackground(new Color(189, 213, 234));
        bottomPanelLeft.add(buttonPlay, BorderLayout.NORTH);
        buttonPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        topPanelRight.setLayout(new BorderLayout());

        // Initialisation de la partie haute
        JPanel panelTopAddForm = new JPanel();
        panelTopAddForm.setBackground(new Color(85, 130, 139));
        topPanelRight.add(panelTopAddForm, BorderLayout.NORTH);

        // Initialisation de la partie basse à gauche
        panelTopAddForm.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Gestion de l'ajout du cube
        JButton buttonAddCube = new JButton(CUBE);
        buttonAddCube.setBackground(new Color(189, 213, 234));
        panelTopAddForm.add(buttonAddCube);
        buttonAddCube.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Affichage d'un cube !");
            }
        });

        // Gestion de l'ajout de la sphere
        JButton buttonAddSphere = new JButton(SPHERE);
        buttonAddSphere.setBackground(new Color(189, 213, 234));
        panelTopAddForm.add(buttonAddSphere);
        buttonAddSphere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Affichage d'une sphere !");
            }
        });

        // Gestion de l'ajout de la pyramide
        JButton buttonAddPyramid = new JButton(PYRAMID);
        buttonAddPyramid.setBackground(new Color(189, 213, 234));
        if (buttonAddPyramid.getModel().isPressed()) {
            System.out.println("true");
        }
        panelTopAddForm.add(buttonAddPyramid);
        buttonAddPyramid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Affichage d'une pyramide !");
            }
        });

        // Gestion de l'ajout du cone
        JButton buttonAddCone = new JButton(CONE);
        buttonAddCone.setBackground(new Color(189, 213, 234));
        panelTopAddForm.add(buttonAddCone);
        buttonAddCone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Affichage d'un cone !");
            }
        });

        // Initialisation de la partie basse
        JPanel panelBottomPreviewForm = new JPanel();
        topPanelRight.add(panelBottomPreviewForm, BorderLayout.CENTER);
        panelBottomPreviewForm.setLayout(new BorderLayout());

        // Initialisation de la partie gauche du bas
        JPanel panelPreviewParam = new JPanel();
        panelPreviewParam.setBackground(new Color(87, 115, 153));
        panelBottomPreviewForm.add(panelPreviewParam, BorderLayout.WEST);

        // Initialisation de la partie droite du bas
        JPanel panelPreviewForm = new JPanel();
        panelPreviewForm.setBackground(new Color(78, 104, 138));
        panelBottomPreviewForm.add(panelPreviewForm, BorderLayout.CENTER);

        // Affichage du texte dans la partie gauche du bas
        panelPreviewParam.add(new JLabel("Rayon : 3.00 m"));



        /* ******************************************* *
         * Gestion du contenu du Panel en bas à droite *
         * Onglets des paramètres des objets           *
         * ******************************************* */
        onglets = new JTabbedPane(SwingConstants.TOP);
        bottomPanelRight.setLayout(new BorderLayout());

        // Affichage et contenu de l'onglet "Paramètres de l'environnement"
        ongletEnvironnement = new JPanel();
        onglets.addTab("Environnement", ongletEnvironnement);
        JLabel descEnv = new JLabel("Paramètres de l'environnement");
        ongletEnvironnement.setLayout(new GridBagLayout());
        ongletEnvironnement.add(descEnv);

        // Affichage et contenu de l'onglet "Paramètres de l'objet"
        ongletObjet = new JPanel();
        onglets.addTab("Objet", ongletObjet);
        JLabel descObj = new JLabel("Paramètres de l'objet");
        ongletObjet.setLayout(new GridBagLayout());
        ongletObjet.add(descObj);



        /* ******************** *
         * Gestion de la caméra *
         * ******************** */
        /*JButton testButton = new JButton("test");
        fenetre.add(testButton);
        testButton.setBounds(1000, 400, 80, 80);*/



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
        bottomPanelRight.add(onglets, BorderLayout.CENTER);
        fenetre.getContentPane().add(verticalSplitPanel);



        /* *********************** *
         * Affichage de la fenêtre *
         * *********************** */
        fenetre.setIconImage(ICONPROJET.getImage());
        fenetre.setVisible(true);
    }


    /**
     * Quitter l'application
     */
    public void close() {
        System.out.println("Fermeture de l'application...");
        System.exit(0);
    }

    public static void main(String [] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ihm();
            }
        });
    }
}