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
import javax.swing.border.EmptyBorder;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Cone;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class ConePanel extends JPanel {
    	public ConePanel(Scene3D scene, World world) {

        this.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel conePaneLeft = new JPanel();
        conePaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        conePaneLeft.setBackground(new Color(93, 129, 156));
        this.add(conePaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel conePaneRight = new JPanel();
        conePaneRight.setBackground(new Color(78, 104, 138));
        this.add(conePaneRight, BorderLayout.CENTER);

        // Initialisation du GridLayout
        GridLayout coneLayout = new GridLayout(6, 2);
        conePaneLeft.setLayout(coneLayout);
        coneLayout.setHgap(-1);
        coneLayout.setVgap(15);

        // Paramètre "x"
        JLabel textParamConeX = new JLabel("x :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeX);
        JTextField paramConeX = new JTextField();
        paramConeX.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeX);

        // Paramètre "y"
        JLabel textParamConeY = new JLabel("y :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeY);
        JTextField paramConeY = new JTextField();
        paramConeY.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeY);

        // Paramètre "z"
        JLabel textParamConeZ = new JLabel("z :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeZ);
        JTextField paramConeZ = new JTextField();
        paramConeZ.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeZ);

        // Paramètre "width"
        JLabel textParamConeRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeRadius);
        JTextField paramConeRadius = new JTextField();
        paramConeRadius.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeRadius);

        // Paramètre "height"
        JLabel textParamConeHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeHeight);
        JTextField paramConeHeight = new JTextField();
        paramConeHeight.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeHeight);


            // Configuration de la partie droite
        conePaneRight.setLayout(new BorderLayout());
        JPanel previewCone = new JPanel();
        JPanel addConeButtonPanel = new JPanel();
        addConeButtonPanel.setLayout(new BorderLayout());
        previewCone.setBackground(new Color(78, 104, 138));
        addConeButtonPanel.setBackground(new Color(78, 104, 138));
        conePaneRight.add(previewCone, BorderLayout.CENTER);
        conePaneRight.add(addConeButtonPanel, BorderLayout.SOUTH);
        JPanel addConeEmptyPanel = new JPanel();
        addConeEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addConeButton = new JButton(GUI.PLUS);
        addConeButtonPanel.add(addConeEmptyPanel, BorderLayout.CENTER);
        addConeButtonPanel.add(addConeButton, BorderLayout.EAST);

        // Gestion du bouton "+"
        addConeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = Double.parseDouble(paramConeX.getText());
                double y = Double.parseDouble(paramConeY.getText());
                double z = Double.parseDouble(paramConeZ.getText());
                double r = Double.parseDouble(paramConeRadius.getText());
                double h = Double.parseDouble(paramConeHeight.getText());

                Logger.info("Création d'une cone. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Rayon: " + r + ", Height: " + h);

                Cone s = new Cone(r, h);
                Position pos = new Position(x, y, z);
                Object3D cone = new Object3D(s, pos, new Color(0,128,128,128), Color.WHITE);
                PhysicObject po = new PhysicObject(s, pos);

                scene.addObject(cone);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, cone, null);

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
	}

}
