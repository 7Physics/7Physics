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
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class CubePanel extends JPanel {
	public CubePanel(Scene3D scene) {
        this.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel cubePaneLeft = new JPanel();
        cubePaneLeft.setBackground(new Color(93, 129, 156));
        this.add(cubePaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel cubePaneRight = new JPanel();
        cubePaneRight.setBackground(new Color(78, 104, 138));
        this.add(cubePaneRight, BorderLayout.CENTER);

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
        JButton addCubeButton = new JButton(GUI.PLUS);
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

                Object3D cube = new Object3D(new Position(x, y, z),
                            new Cuboid(w, l, h),
                            new Color(128,128,128,128),
                            Color.WHITE);
                
                scene.addObject(cube);

                ObjectPanel objectPanel = new ObjectPanel(scene, cube, null);

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
	}
}
