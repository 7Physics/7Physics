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
        JPanel spherePane = new SpherePanel(scene);
        ongletSphere.setLayout(new BorderLayout());
        ongletSphere.add(spherePane, BorderLayout.CENTER);

        // Gestion de l'ajout de la pyramide
        /*JPanel ongletPyramid = new JPanel();
        ongletsTop.addTab("", ongletPyramid);
        JLabel descPyramid = new JLabel("Pyramide");
        ongletPyramid.setLayout(new GridBagLayout());
        ongletPyramid.add(descPyramid);*/

        // Gestion de l'ajout du cone
        /*JPanel ongletCone = new JPanel();
        ongletsTop.addTab("", ongletCone);
        JLabel descCone = new JLabel("Cone");
        ongletCone.setLayout(new GridBagLayout());
        ongletCone.add(descCone);*/

        // Gestion des icons des onglets
        this.setIconAt(0, GUI.CUBE);
        this.setIconAt(1, GUI.SPHERE);
        /*ongletsTop.setIconAt(2, PYRAMID);
        ongletsTop.setIconAt(3, CONE);*/
	}
}
