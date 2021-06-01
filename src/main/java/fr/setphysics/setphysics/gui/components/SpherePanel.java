package fr.setphysics.setphysics.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Sphere;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class SpherePanel extends JPanel {
	public SpherePanel(Scene3D scene) {

        this.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel spherePaneLeft = new JPanel();
        spherePaneLeft.setBackground(new Color(87, 115, 153));
        this.add(spherePaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel spherePaneRight = new JPanel();
        spherePaneRight.setBackground(new Color(78, 104, 138));
        this.add(spherePaneRight, BorderLayout.CENTER);

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
        JButton addSphereButton = new JButton(GUI.PLUS);
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

	}
}
