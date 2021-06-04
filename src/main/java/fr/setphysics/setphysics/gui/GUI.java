package fr.setphysics.setphysics.gui;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import fr.setphysics.common.geom.Position;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.components.CamPanel;
import fr.setphysics.setphysics.gui.components.EnvPanel;
import fr.setphysics.setphysics.gui.components.ObjectListPanel;
import fr.setphysics.setphysics.gui.components.PreviewPanel;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    /*
	 * *********************************** * Définition des variables/constantes *
	 * ***********************************
	 */
	// Définition des ImageIcon
	public static final ImageIcon ICONPROJET = new ImageIcon(GUI.class.getResource("/images/projet.png"));
    public static final ImageIcon PLAY = new ImageIcon(GUI.class.getResource("/images/play.png"));
    public static final ImageIcon PLUS = new ImageIcon(GUI.class.getResource("/images/plus.png"));
    public static final ImageIcon MINUS = new ImageIcon(GUI.class.getResource("/images/minus.png"));
    public static final ImageIcon ZOOMIN = new ImageIcon(GUI.class.getResource("/images/zoom-in.png"));
    public static final ImageIcon ZOOMOUT = new ImageIcon(GUI.class.getResource("/images/zoom-out.png"));
    public static final ImageIcon CUBE = new ImageIcon(GUI.class.getResource("/images/cube.png"));
    public static final ImageIcon SPHERE = new ImageIcon(GUI.class.getResource("/images/sphere.png"));
    public static final ImageIcon PYRAMID = new ImageIcon(GUI.class.getResource("/images/pyramid.png"));
    public static final ImageIcon CONE = new ImageIcon(GUI.class.getResource("/images/cone.png"));



    /* *************************** *
     * Initialisation du Singleton *
     * *************************** */
    private static final GUI gui = new GUI();

    public static GUI getInstance() {
        return gui;
    }



    /* ***************************** *
     * Création des différents Panel *
     * ***************************** */
    private JSplitPane verticalSplitPanel;
    private JSplitPane leftSplitPanel;
    private JSplitPane rightSplitPanel;
    private JPanel topPanelLeft;
    private JPanel bottomPanelLeft;
    private JPanel topPanelRight;
    private JPanel bottomPanelRight;
    private ObjectListPanel contentBottomPanelLeft;
    private EnvPanel ongletsBottom;



    /* ***************************************** *
     * Création du MenuBar en haut de la fenêtre *
     * ***************************************** */
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem boutonMenuQuitter = new JMenuItem("Quitter");



    /* ********************************************* *
     * Création des différents JTabbedPane (onglets) *
     * ********************************************* */
    private JTabbedPane ongletsTop;
    //private JTabbedPane ongletsBottom;



    /**
     * Construction de la fenêtre
     */
    private GUI() {
        /* *********************************** *
         * Définition de la fenêtre principale *
         * *********************************** */
        super("7Physics");
        this.setPreferredSize(new Dimension(1200, 700));
        this.setMinimumSize(new Dimension(1200, 700));
        this.setResizable(false);



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
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		Position position = new Position(-3.5, 0.3, 0);
		Camera cam = new Camera(position);
		final Scene3D scene = new Scene3D(cam);
		final World world = new World();

		glcanvas.addGLEventListener(scene);
		glcanvas.addKeyListener(scene.getKeyListener());
		glcanvas.addMouseMotionListener(scene);
		glcanvas.addMouseWheelListener(scene);

		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);

		animator.start();
		topPanelLeft.add(glcanvas, BorderLayout.CENTER);



        /* ****************************************** *
         * Création du Panel des boutons de la caméra *
         * ****************************************** */
        JPanel cameraPanel = new CamPanel(cam);
        cameraPanel.setBackground(new Color(49, 66, 106));
        this.add(cameraPanel);
        cameraPanel.setBounds(20, 70, 29, 68);



        /* ******************************************** *
         * Gestion du contenu du Panel en bas à gauche  *
         * La gestion des objets présents dans la scene *
         * ******************************************** */
        bottomPanelLeft.setLayout(new BorderLayout());
        contentBottomPanelLeft = new ObjectListPanel(scene, world);
        bottomPanelLeft.add(contentBottomPanelLeft, BorderLayout.CENTER);



        /* ******************************************** *
         * Gestion du contenu du Panel en haut à droite *
         * Ajout et création d'objets                   *
         * ******************************************** */
        topPanelRight.setLayout(new BorderLayout());
        ongletsTop = new PreviewPanel(scene, world);
        topPanelRight.add(ongletsTop, BorderLayout.CENTER);



        /* ******************************************* *
         * Gestion du contenu du Panel en bas à droite *
         * Onglets des paramètres des objets           *
         * ******************************************* */
        bottomPanelRight.setLayout(new BorderLayout());
        ongletsBottom = new EnvPanel(scene);
        bottomPanelRight.add(ongletsBottom, BorderLayout.CENTER);



        /* ************** *
         * ActionListener *
         * ************** */
        ActionListener quitter = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                close();
            }
        };



        /* ******************* *
         * Afficher le MenuBar *
         * ******************* */
        menuBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(menuBar);
        boutonMenuQuitter.addActionListener(quitter);
        menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        menu.add(boutonMenuQuitter);
        menuBar.add(menu);



        /* ******************************************* *
         * Ajout du contenu dans les différents Panels *
         * ******************************************* */
        this.getContentPane().add(verticalSplitPanel);



        /* ************************************************ *
         * Gestion de fermeture de la fenêtre avec la croix *
         * ************************************************ */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /* *********************** *
         * Affichage de la fenêtre *
         * *********************** */
        this.setIconImage(ICONPROJET.getImage());
        //this.pack();
        //this.setVisible(true);
    }


    /**
     * Renvoie le Panel "contentBottomPanelLeft"
     * @return ObjectListPanel
     */
    public ObjectListPanel getObjectListPanel() {
        return contentBottomPanelLeft;
    }



    /**
     * Renvoie le Panel "ongletsBottom"
     * @return EnvPanel
     */
    public EnvPanel getOngletsBottom() {
        return ongletsBottom;
    }



    /**
     * Quitter l'application
     */
    public void close() {
        System.out.println("Fermeture de l'application...");
        System.exit(0);
    }
}