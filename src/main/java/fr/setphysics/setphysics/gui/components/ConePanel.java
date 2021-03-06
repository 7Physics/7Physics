package fr.setphysics.setphysics.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Cone;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.ColorUtils;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class ConePanel extends JPanel {
    public ConePanel(Scene3D scene, World world) {
        this.setLayout(new BorderLayout());



        /* ********************************** *
         * Initialisation de la partie gauche *
         * ********************************** */
        JPanel conePaneLeft = new JPanel();
        conePaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        conePaneLeft.setBackground(new Color(93, 129, 156));
        this.add(conePaneLeft, BorderLayout.WEST);



        /* ********************************** *
         * Initialisation de la partie droite *
         * ********************************** */
        JPanel conePaneRight = new JPanel();
        conePaneRight.setBackground(new Color(78, 104, 138));
        this.add(conePaneRight, BorderLayout.CENTER);



        /* **************************** *
         * Initialisation du GridLayout *
         * **************************** */
        GridLayout coneLayout = new GridLayout(6, 2);
        conePaneLeft.setLayout(coneLayout);
        coneLayout.setHgap(-1);
        coneLayout.setVgap(15);



        /* ************* *
         * Paramètre "x" *
         * ************* */
        JLabel textParamConeX = new JLabel("x :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeX);
        JSpinner paramConeX = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramConeX.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeX);



        /* ************* *
         * Paramètre "y" *
         * ************* */
        JLabel textParamConeY = new JLabel("y :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeY);
        JSpinner paramConeY = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramConeY.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeY);



        /* ************* *
         * Paramètre "z" *
         * ************* */
        JLabel textParamConeZ = new JLabel("z :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeZ);
        JSpinner paramConeZ = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramConeZ.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeZ);



        /* ***************** *
         * Paramètre "Rayon" *
         * ***************** */
        JLabel textParamConeRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeRadius);
        JSpinner paramConeRadius = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramConeRadius.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeRadius);



        /* ******************* *
         * Paramètre "Hauteur" *
         * ******************* */
        JLabel textParamConeHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        conePaneLeft.add(textParamConeHeight);
        JSpinner paramConeHeight = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramConeHeight.setPreferredSize(new Dimension(50, 20));
        conePaneLeft.add(paramConeHeight);



        /* ********************************* *
         * Configuration de la partie droite *
         * ********************************* */
        conePaneRight.setLayout(new BorderLayout());

        JPanel previewCone = new JPanel();
        previewCone.setBackground(new Color(78, 104, 138));
        JPanel addConeButtonPanel = new JPanel(new BorderLayout());
        addConeButtonPanel.setBackground(new Color(78, 104, 138));

        conePaneRight.add(previewCone, BorderLayout.CENTER);
        conePaneRight.add(addConeButtonPanel, BorderLayout.SOUTH);

        // Partie haute du Panel avec la preview
        previewCone.setLayout(new BorderLayout());

        Camera previewCam = new Camera(new Position(-1.2, 1.3, -1.2));
        previewCam.setAngles(45*Math.PI/180, -23*Math.PI/180);
        final Scene3D previewScene = new Scene3D(previewCam);

        previewCone.add(previewScene, BorderLayout.CENTER);
        previewScene.repaint();
        previewScene.revalidate();

        Cone previewConeObj = new Cone(0.2, 0.5);
        Position previewPos = new Position(0, 0.5, 0);
        Color[] colors = ColorUtils.getTwoDistinctColors();
        Object3D previewObj = new Object3D(previewConeObj)
                .setPosition(previewPos)
                .setColor(colors[0], colors[1]);
        previewScene.addObject(previewObj);

        previewScene.stopAnimation();

        // Partie basse du Panel avec le bouton
        JPanel addConeEmptyPanel = new JPanel();
        addConeEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addConeButton = new JButton(GUI.PLUS);
        addConeButtonPanel.add(addConeEmptyPanel, BorderLayout.CENTER);
        addConeButtonPanel.add(addConeButton, BorderLayout.EAST);



        /* ********************* *
         * Gestion du bouton "+" *
         * ********************* */
        addConeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = (double) paramConeX.getValue();
                double y = (double) paramConeY.getValue();
                double z = (double) paramConeZ.getValue();
                double r = (double) paramConeRadius.getValue();
                double h = (double) paramConeHeight.getValue();

                Logger.info("Création d'une cone. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Rayon: " + r + ", Height: " + h);

                Cone s = new Cone(r, h);
                Position pos = new Position(x, y, z);

                Color[] colors = ColorUtils.getTwoDistinctColors();
                Object3D cone = new Object3D(s).setPosition(pos).setColor(colors[0], colors[1]);

                PhysicObject po = new PhysicObject(s, pos);

                scene.addObject(cone);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, world, cone, po, "");

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
	}

}
