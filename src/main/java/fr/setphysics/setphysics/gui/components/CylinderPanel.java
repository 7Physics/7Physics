package fr.setphysics.setphysics.gui.components;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Cylinder;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CylinderPanel extends JPanel {
    public CylinderPanel(Scene3D scene, World world) {

        this.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel cylinderPaneLeft = new JPanel();
        cylinderPaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        cylinderPaneLeft.setBackground(new Color(93, 129, 156));
        this.add(cylinderPaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel cylinderPaneRight = new JPanel();
        cylinderPaneRight.setBackground(new Color(78, 104, 138));
        this.add(cylinderPaneRight, BorderLayout.CENTER);

        // Initialisation du GridLayout
        GridLayout cylinderLayout = new GridLayout(6, 2);
        cylinderPaneLeft.setLayout(cylinderLayout);
        cylinderLayout.setHgap(-1);
        cylinderLayout.setVgap(15);

        // Paramètre "x"
        JLabel textParamCylinderX = new JLabel("x :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderX);
        JTextField paramCylinderX = new JTextField();
        paramCylinderX.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderX);

        // Paramètre "y"
        JLabel textParamCylinderY = new JLabel("y :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderY);
        JTextField paramCylinderY = new JTextField();
        paramCylinderY.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderY);

        // Paramètre "z"
        JLabel textParamCylinderZ = new JLabel("z :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderZ);
        JTextField paramCylinderZ = new JTextField();
        paramCylinderZ.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderZ);

        // Paramètre "width"
        JLabel textParamCylinderRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderRadius);
        JTextField paramCylinderRadius = new JTextField();
        paramCylinderRadius.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderRadius);

        // Paramètre "height"
        JLabel textParamCylinderHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderHeight);
        JTextField paramCylinderHeight = new JTextField();
        paramCylinderHeight.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderHeight);


        // Configuration de la partie droite
        cylinderPaneRight.setLayout(new BorderLayout());
        JPanel previewCylinder = new JPanel();
        JPanel addCylinderButtonPanel = new JPanel();
        addCylinderButtonPanel.setLayout(new BorderLayout());
        previewCylinder.setBackground(new Color(78, 104, 138));
        addCylinderButtonPanel.setBackground(new Color(78, 104, 138));
        cylinderPaneRight.add(previewCylinder, BorderLayout.CENTER);
        cylinderPaneRight.add(addCylinderButtonPanel, BorderLayout.SOUTH);
        JPanel addCylinderEmptyPanel = new JPanel();
        addCylinderEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addCylinderButton = new JButton(GUI.PLUS);
        addCylinderButtonPanel.add(addCylinderEmptyPanel, BorderLayout.CENTER);
        addCylinderButtonPanel.add(addCylinderButton, BorderLayout.EAST);

        // Gestion du bouton "+"
        addCylinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = Double.parseDouble(paramCylinderX.getText());
                double y = Double.parseDouble(paramCylinderY.getText());
                double z = Double.parseDouble(paramCylinderZ.getText());
                double r = Double.parseDouble(paramCylinderRadius.getText());
                double h = Double.parseDouble(paramCylinderHeight.getText());

                Logger.info("Création d'une cylinder. X: " + x + ", Y: " + y + ", Z: " + z
                        + ", Rayon: " + r + ", Height: " + h);

                Cylinder s = new Cylinder(r, h);
                Position pos = new Position(x, y, z);
                Object3D cylinder = new Object3D(s, pos, new Color(0,128,128,128), Color.WHITE);
                PhysicObject po = new PhysicObject(s, pos);

                scene.addObject(cylinder);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, cylinder, null);

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
    }
}
