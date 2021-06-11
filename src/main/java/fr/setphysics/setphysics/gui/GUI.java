package fr.setphysics.setphysics.gui;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.geom.Shape;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.file.OBJFile;
import fr.setphysics.setphysics.gui.components.CamPanel;
import fr.setphysics.setphysics.gui.components.EnvPanel;
import fr.setphysics.setphysics.gui.components.FileExportation;
import fr.setphysics.setphysics.gui.components.FileImportation;
import fr.setphysics.setphysics.gui.components.ObjectListPanel;
import fr.setphysics.setphysics.gui.components.PreviewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Map;
import java.util.List;

public class GUI extends JFrame {

    /* *********************************** *
     * Définition des variables/constantes *
     * *********************************** */
    // Définition des ImageIcon
    public static final ImageIcon ICONPROJET = new ImageIcon(GUI.class.getResource("/images/projet.png"));
    public static final ImageIcon PLAY = new ImageIcon(GUI.class.getResource("/images/play.png"));
    public static final ImageIcon PLUS = new ImageIcon(GUI.class.getResource("/images/plus.png"));
    public static final ImageIcon MINUS = new ImageIcon(GUI.class.getResource("/images/minus.png"));
    public static final ImageIcon TRASH = new ImageIcon(GUI.class.getResource("/images/trash.png"));
    public static final ImageIcon UNKNOWN = new ImageIcon(GUI.class.getResource("/images/unknown.png"));
    public static final ImageIcon ZOOMIN = new ImageIcon(GUI.class.getResource("/images/zoom-in.png"));
    public static final ImageIcon ZOOMOUT = new ImageIcon(GUI.class.getResource("/images/zoom-out.png"));
    public static final ImageIcon CUBE = new ImageIcon(GUI.class.getResource("/images/cube.png"));
    public static final ImageIcon BIGCUBE = new ImageIcon(GUI.class.getResource("/images/big-cube.png"));
    public static final ImageIcon SPHERE = new ImageIcon(GUI.class.getResource("/images/sphere.png"));
    public static final ImageIcon BIGSPHERE = new ImageIcon(GUI.class.getResource("/images/big-sphere.png"));
    public static final ImageIcon PYRAMID = new ImageIcon(GUI.class.getResource("/images/pyramid.png"));
    public static final ImageIcon BIGPYRAMID = new ImageIcon(GUI.class.getResource("/images/big-pyramid.png"));
    public static final ImageIcon CONE = new ImageIcon(GUI.class.getResource("/images/cone.png"));
    public static final ImageIcon BIGCONE = new ImageIcon(GUI.class.getResource("/images/big-cone.png"));
    public static final ImageIcon CYLINDER = new ImageIcon(GUI.class.getResource("/images/cylinder.png"));
    public static final ImageIcon BIGCYLINDER = new ImageIcon(GUI.class.getResource("/images/big-cylinder.png"));


    /* *************************** *
     * Initialisation du Singleton *
     * *************************** */
    private static final GUI gui = new GUI();

    public static GUI getInstance() {
        return gui;
    }



    /* ********************************************** *
     * Définition des principaux Panels de la fenêtre *
     * ********************************************** */
    private JPanel verticalSplitPanel;
    private JPanel leftSplitPanel;
    private JPanel rightSplitPanel;
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
    JMenu mainMenu = new JMenu("Menu");
    JMenuItem buttonMenuImport = new JMenuItem("Importer un projet");
    JMenuItem buttonMenuExport = new JMenuItem("Exporter le projet");
    JMenuItem buttonMenuQuit = new JMenuItem("Quitter");



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



        /* *************************************** *
         * Initialisation des Panels de la fenêtre *
         * *************************************** */
        verticalSplitPanel = new JPanel();
        leftSplitPanel = new JPanel();
        rightSplitPanel = new JPanel();
        topPanelLeft = new JPanel();
        bottomPanelLeft = new JPanel();
        topPanelRight = new JPanel();
        bottomPanelRight = new JPanel();



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
        verticalSplitPanel.setLayout(new BorderLayout());
        verticalSplitPanel.add(leftSplitPanel, BorderLayout.CENTER);
        verticalSplitPanel.add(rightSplitPanel, BorderLayout.EAST);

        // Découpage horizontal du Panel de gauche en deux
        leftSplitPanel.setLayout(new BorderLayout());
        leftSplitPanel.add(topPanelLeft, BorderLayout.CENTER);
        leftSplitPanel.add(bottomPanelLeft, BorderLayout.SOUTH);

        // Découpage horizontal du Panel de droite en deux
        rightSplitPanel.setLayout(new BorderLayout());
        rightSplitPanel.add(topPanelRight, BorderLayout.NORTH);
        rightSplitPanel.add(bottomPanelRight, BorderLayout.CENTER);
        rightSplitPanel.setBorder(BorderFactory.createBevelBorder(1));



        /* ******************************************** *
         * Gestion du contenu du Panel en haut à gauche *
         * La scene de rendu 3D                         *
         * ******************************************** */
        topPanelLeft.setLayout(new BorderLayout());

        Camera cam = new Camera(new Position(-2, .5, 0));
        final Scene3D scene = new Scene3D(cam);
        final World world = new World();
//        PhysicObject ground = new PhysicObject(new Cuboid(10, 10, 2), new Position(0, -1, 0));
//        ground.setDynamic(false);
//        world.addPhysicObject(ground);

        JLabel loadingLabel = new JLabel("Chargement...");
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanelLeft.add(loadingLabel, BorderLayout.CENTER);

        // Comme le canvas OpenGL met du temps à se créer on effectue sa création en parallèle
        new Thread(() -> {
            topPanelLeft.remove(loadingLabel);
            topPanelLeft.add(scene, BorderLayout.CENTER);

            scene.repaint();
            scene.revalidate();
        }).start();



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
        ongletsBottom = new EnvPanel(scene, world);
        bottomPanelRight.add(ongletsBottom, BorderLayout.CENTER);



        /* ************** *
         * ActionListener *
         * ************** */
        ActionListener importProject = new FileImportation(scene, world);

        ActionListener exportProject = new FileExportation(scene);

        ActionListener quitter = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                close();
            }
        };



        /* ******************* *
         * Afficher le MenuBar *
         * ******************* */
        this.setJMenuBar(menuBar);
        buttonMenuImport.addActionListener(importProject);
        buttonMenuExport.addActionListener(exportProject);
        buttonMenuQuit.addActionListener(quitter);
        mainMenu.add(buttonMenuImport);
        mainMenu.add(buttonMenuExport);
        mainMenu.add(new JSeparator());
        mainMenu.add(buttonMenuQuit);
        menuBar.add(mainMenu);

        menuBar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                revalidate();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                revalidate();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                revalidate();
                repaint();
            }
        });



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
        this.pack();
        this.setLocationRelativeTo(null);
    }



    /**
     * Renvoie le Panel "contentBottomPanelLeft"
     *
     * @return ObjectListPanel
     */
    public ObjectListPanel getObjectListPanel() {
        return contentBottomPanelLeft;
    }



    /**
     * Renvoie le Panel "ongletsBottom"
     *
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