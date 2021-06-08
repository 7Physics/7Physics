package fr.setphysics.setphysics.gui.components;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Pyramid;
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
public class PyramidPanel extends JPanel {
    private boolean isSquareBase;

    public PyramidPanel(Scene3D scene, World world) {
        this.isSquareBase = false;
        this.setLayout(new BorderLayout());

        // Initialisation de la partie gauche
        JPanel pyramidPaneLeft = new JPanel();
        pyramidPaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        pyramidPaneLeft.setBackground(new Color(93, 129, 156));
        this.add(pyramidPaneLeft, BorderLayout.WEST);

        // Initialisation de la partie droite
        JPanel pyramidPaneRight = new JPanel();
        pyramidPaneRight.setBackground(new Color(78, 104, 138));
        pyramidPaneRight.setPreferredSize(new Dimension(170, 50));
        this.add(pyramidPaneRight, BorderLayout.CENTER);

        // Initialisation du GridLayout
        GridLayout pyramidLayout = new GridLayout(6, 2);
        pyramidPaneLeft.setLayout(pyramidLayout);
        pyramidLayout.setHgap(-1);
        pyramidLayout.setVgap(15);

        // Paramètre "x"
        JLabel textParamPyramidX = new JLabel("x :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidX);
        JTextField paramPyramidX = new JTextField();
        paramPyramidX.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidX);

        // Paramètre "y"
        JLabel textParamPyramidY = new JLabel("y :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidY);
        JTextField paramPyramidY = new JTextField();
        paramPyramidY.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidY);

        // Paramètre "z"
        JLabel textParamPyramidZ = new JLabel("z :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidZ);
        JTextField paramPyramidZ = new JTextField();
        paramPyramidZ.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidZ);

        // Paramètre "width"
        JLabel textParamPyramidWidth = new JLabel("Largeur :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidWidth);
        JTextField paramPyramidWidth = new JTextField();
        paramPyramidWidth.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidWidth);

        // Paramètre "height"
        JLabel textParamPyramidHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidHeight);
        JTextField paramPyramidHeight = new JTextField();
        paramPyramidHeight.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidHeight);

        // Configuration de la partie droite
        pyramidPaneRight.setLayout(new BorderLayout());
        JPanel previewPyramid = new JPanel();
        JPanel addPyramidButtonPanel = new JPanel();
        addPyramidButtonPanel.setLayout(new BorderLayout());
        previewPyramid.setBackground(new Color(78, 104, 138));
        addPyramidButtonPanel.setBackground(new Color(78, 104, 138));
        pyramidPaneRight.add(previewPyramid, BorderLayout.CENTER);
        pyramidPaneRight.add(addPyramidButtonPanel, BorderLayout.SOUTH);
        JPanel addPyramidEmptyPanel = new JPanel();
        addPyramidEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addPyramidButton = new JButton(GUI.PLUS);
        addPyramidButtonPanel.add(addPyramidEmptyPanel, BorderLayout.CENTER);
        addPyramidButtonPanel.add(addPyramidButton, BorderLayout.EAST);

        // Gestion du bouton "+"
        addPyramidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = Double.parseDouble(paramPyramidX.getText());
                double y = Double.parseDouble(paramPyramidY.getText());
                double z = Double.parseDouble(paramPyramidZ.getText());
                double w = Double.parseDouble(paramPyramidWidth.getText());
                double h = Double.parseDouble(paramPyramidHeight.getText());

                Logger.info("Création d'un pyramid. X: " + x + ", Y: " + y + ", Z: " + z
                            + ", Width: " + w + ", Height: " + h);

                Position pos = new Position(x, y, z);
                Pyramid pyramid;
                if (isSquareBase) {
                    pyramid = new Pyramid(w, w, h);
                } else {
                    pyramid = new Pyramid(w, h);
                }
                PhysicObject po = new PhysicObject(pyramid, pos);
                Object3D obj = new Object3D(pos, pyramid, new Color(128, 128, 128, 128), Color.WHITE);
                scene.addObject(obj);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, obj, po);

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
    }
}
