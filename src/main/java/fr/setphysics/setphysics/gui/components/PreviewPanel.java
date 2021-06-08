package fr.setphysics.setphysics.gui.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import fr.setphysics.engine.World;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class PreviewPanel extends JTabbedPane{
	public PreviewPanel(Scene3D scene, World world) {
		super(SwingConstants.TOP);
        // Gestion de l'ajout du cube
        JPanel ongletCube = new JPanel();
        this.addTab("", ongletCube);
        JPanel cubePane = new CubePanel(scene, world);
        ongletCube.setLayout(new BorderLayout());
        ongletCube.add(cubePane, BorderLayout.CENTER);

        // Gestion de l'ajout de la sphere
        JPanel ongletSphere = new JPanel();
        this.addTab("", ongletSphere);
        JPanel spherePane = new SpherePanel(scene, world);
        ongletSphere.setLayout(new BorderLayout());
        ongletSphere.add(spherePane, BorderLayout.CENTER);

        // Gestion de l'ajout de la pyramide
        JPanel ongletPyramid = new JPanel();
        this.addTab("", ongletPyramid);
        JPanel pyramidPane = new PyramidPanel(scene, world);
        //JLabel descPyramid = new JLabel("Pyramide");
        ongletPyramid.setLayout(new BorderLayout());
        ongletPyramid.add(pyramidPane, BorderLayout.CENTER);

        // Gestion de l'ajout du cone
        JPanel ongletCone = new JPanel();
        this.addTab("", ongletCone);
        JPanel conePane = new ConePanel(scene, world);
        //JLabel descCone = new JLabel("Cone");
        ongletCone.setLayout(new BorderLayout());
        ongletCone.add(conePane, BorderLayout.CENTER);

        // Gestion de l'ajout du cylindre
        JPanel ongletCylinder = new JPanel();
        this.addTab("", ongletCylinder);
        JPanel cylinderPane = new CylinderPanel(scene, world);
        //JLabel descCylinder = new JLabel("Cylinder");
        ongletCylinder.setLayout(new BorderLayout());
        ongletCylinder.add(cylinderPane, BorderLayout.CENTER);

        // Gestion des icons des onglets
        this.setIconAt(0, GUI.CUBE);
        this.setIconAt(1, GUI.SPHERE);
        this.setIconAt(2, GUI.PYRAMID);
        this.setIconAt(3, GUI.CONE);
        this.setIconAt(4, GUI.MINUS);
	}
}
