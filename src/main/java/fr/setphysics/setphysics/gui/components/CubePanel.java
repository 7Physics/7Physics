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
public class CubePanel extends JPanel {
	public CubePanel(Scene3D scene, World world) {
        this.setLayout(new BorderLayout());



        /* ********************************** *
         * Initialisation de la partie gauche *
         * ********************************** */
        JPanel cubePaneLeft = new JPanel();
        cubePaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        cubePaneLeft.setBackground(new Color(93, 129, 156));
        this.add(cubePaneLeft, BorderLayout.WEST);



        /* ********************************** *
         * Initialisation de la partie droite *
         * ********************************** */
        JPanel cubePaneRight = new JPanel();
        cubePaneRight.setBackground(new Color(78, 104, 138));
        cubePaneRight.setPreferredSize(new Dimension(170, 50));
        this.add(cubePaneRight, BorderLayout.CENTER);



        /* **************************** *
         * Initialisation du GridLayout *
         * **************************** */
        GridLayout cubeLayout = new GridLayout(6, 2);
        cubePaneLeft.setLayout(cubeLayout);
        cubeLayout.setHgap(-1);
        cubeLayout.setVgap(15);



        /* ************* *
         * Paramètre "x" *
         * ************* */
        JLabel textParamCubeX = new JLabel("x :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeX);
        JSpinner paramCubeX = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCubeX.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeX);



        /* ************* *
         * Paramètre "y" *
         * ************* */
        JLabel textParamCubeY = new JLabel("y :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeY);
        JSpinner paramCubeY = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCubeY.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeY);



        /* ************* *
         * Paramètre "z" *
         * ************* */
        JLabel textParamCubeZ = new JLabel("z :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeZ);
        JSpinner paramCubeZ = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCubeZ.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeZ);



        /* ******************* *
         * Paramètre "Largeur" *
         * ******************* */
        JLabel textParamCubeWidth = new JLabel("Largeur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeWidth);
        JSpinner paramCubeWidth = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramCubeWidth.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeWidth);



        /* ******************** *
         * Paramètre "Longueur" *
         * ******************** */
        JLabel textParamCubeLength = new JLabel("Longueur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeLength);
        JSpinner paramCubeLength = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramCubeLength.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeLength);



        /* ******************* *
         * Paramètre "Hauteur" *
         * ******************* */
        JLabel textParamCubeHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        cubePaneLeft.add(textParamCubeHeight);
        JSpinner paramCubeHeight = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramCubeHeight.setPreferredSize(new Dimension(50, 20));
        cubePaneLeft.add(paramCubeHeight);



        /* ********************************* *
         * Configuration de la partie droite *
         * ********************************* */
        cubePaneRight.setLayout(new BorderLayout());

        JPanel previewCube = new JPanel();
        previewCube.setBackground(new Color(78, 104, 138));
        JPanel addCubeButtonPanel = new JPanel(new BorderLayout());
        addCubeButtonPanel.setBackground(new Color(78, 104, 138));

        cubePaneRight.add(previewCube, BorderLayout.CENTER);
        cubePaneRight.add(addCubeButtonPanel, BorderLayout.SOUTH);

        // Partie haute du Panel avec la preview
        previewCube.setLayout(new BorderLayout());

        Camera previewCam = new Camera(new Position(-1.2, 1.3, -1.2));
        previewCam.setAngles(45*Math.PI/180, -23*Math.PI/180);
        final Scene3D previewScene = new Scene3D(previewCam);

        previewCube.add(previewScene, BorderLayout.CENTER);
        previewScene.repaint();
        previewScene.revalidate();

        Cuboid previewCubeObj = new Cuboid(0.5, 0.5, 0.5);
        Position previewPos = new Position(0, 0.5, 0);

        Color[] colors = ColorUtils.getTwoDistinctColors();
        Object3D previewObj = new Object3D(previewCubeObj)
                .setPosition(previewPos)
                .setColor(colors[0], colors[1]);

        previewScene.addObject(previewObj);

        previewScene.stopAnimation();

        // Partie basse du Panel avec le bouton
        JPanel addCubeEmptyPanel = new JPanel();
        addCubeEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addCubeButton = new JButton(GUI.PLUS);
        addCubeButtonPanel.add(addCubeEmptyPanel, BorderLayout.CENTER);
        addCubeButtonPanel.add(addCubeButton, BorderLayout.EAST);



        /* ********************* *
         * Gestion du bouton "+" *
         * ********************* */
        addCubeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = (double) paramCubeX.getValue();
                double y = (double) paramCubeY.getValue();
                double z = (double) paramCubeZ.getValue();
                double w = (double) paramCubeWidth.getValue();
                double l = (double) paramCubeLength.getValue();
                double h = (double) paramCubeHeight.getValue();
                
                Logger.info("Création d'un cube. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Width: " + w + ", Length: " + l + ", Height: " + h);

                Position pos = new Position(x, y, z);
                Cuboid cube = new Cuboid(w, l, h);
                PhysicObject po = new PhysicObject(cube, pos);

                Color[] colors = ColorUtils.getTwoDistinctColors();

                Object3D obj = new Object3D(cube)
                        .setPosition(pos)
                        .setColor(colors[0], colors[1]);
                scene.addObject(obj);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, world ,obj, po, "");

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
	}
}
