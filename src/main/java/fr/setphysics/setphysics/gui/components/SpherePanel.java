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
import fr.setphysics.common.geom.shape.Sphere;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.ColorUtils;
import fr.setphysics.setphysics.gui.GUI;

@SuppressWarnings("serial")
public class SpherePanel extends JPanel {
	public SpherePanel(Scene3D scene, World world) {
        this.setLayout(new BorderLayout());



        /* ********************************** *
         * Initialisation de la partie gauche *
         * ********************************** */
        JPanel spherePaneLeft = new JPanel();
        spherePaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        spherePaneLeft.setBackground(new Color(93, 129, 156));
        this.add(spherePaneLeft, BorderLayout.WEST);



        /* ********************************** *
         * Initialisation de la partie droite *
         * ********************************** */
        JPanel spherePaneRight = new JPanel();
        spherePaneRight.setBackground(new Color(78, 104, 138));
        this.add(spherePaneRight, BorderLayout.CENTER);



        /* **************************** *
         * Initialisation du GridLayout *
         * **************************** */
        GridLayout sphereLayout = new GridLayout(6, 2);
        spherePaneLeft.setLayout(sphereLayout);
        sphereLayout.setHgap(-1);
        sphereLayout.setVgap(15);



        /* ************* *
         * Paramètre "x" *
         * ************* */
        JLabel textParamSphereX = new JLabel("x :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereX);
        JSpinner paramSphereX = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramSphereX.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereX);



        /* ************* *
         * Paramètre "y" *
         * ************* */
        JLabel textParamSphereY = new JLabel("y :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereY);
        JSpinner paramSphereY = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramSphereY.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereY);



        /* ************* *
         * Paramètre "z" *
         * ************* */
        JLabel textParamSphereZ = new JLabel("z :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereZ);
        JSpinner paramSphereZ = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramSphereZ.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereZ);



        /* ***************** *
         * Paramètre "Rayon" *
         * ***************** */
        JLabel textParamSphereRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        spherePaneLeft.add(textParamSphereRadius);
        JSpinner paramSphereRadius = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramSphereRadius.setPreferredSize(new Dimension(50, 20));
        spherePaneLeft.add(paramSphereRadius);



        /* ********************************* *
         * Configuration de la partie droite *
         * ********************************* */
        spherePaneRight.setLayout(new BorderLayout());

        JPanel previewSphere = new JPanel();
        previewSphere.setBackground(new Color(78, 104, 138));
        JPanel addSphereButtonPanel = new JPanel(new BorderLayout());
        addSphereButtonPanel.setBackground(new Color(78, 104, 138));

        spherePaneRight.add(previewSphere, BorderLayout.CENTER);
        spherePaneRight.add(addSphereButtonPanel, BorderLayout.SOUTH);

        // Partie haute du Panel avec la preview
        previewSphere.setLayout(new BorderLayout());

        Camera previewCam = new Camera(new Position(-1.2, 1.3, -1.2));
        previewCam.setAngles(45*Math.PI/180, -23*Math.PI/180);
        final Scene3D previewScene = new Scene3D(previewCam);

        previewSphere.add(previewScene, BorderLayout.CENTER);
        previewScene.repaint();
        previewScene.revalidate();

        Sphere previewSphereObj = new Sphere(0.25, 3);
        Position previewPos = new Position(0, 0.5, 0);

        Color[] colors = ColorUtils.getTwoDistinctColors();
        Object3D previewObj = new Object3D(previewSphereObj)
                .setPosition(previewPos)
                .setColor(colors[0], colors[1]);

        previewScene.addObject(previewObj);

        previewScene.stopAnimation();

        // Partie basse du Panel avec le bouton
        JPanel addSphereEmptyPanel = new JPanel();
        addSphereEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addSphereButton = new JButton(GUI.PLUS);
        addSphereButtonPanel.add(addSphereEmptyPanel, BorderLayout.CENTER);
        addSphereButtonPanel.add(addSphereButton, BorderLayout.EAST);



        /* ********************* *
         * Gestion du bouton "+" *
         * ********************* */
        addSphereButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = (double) paramSphereX.getValue();
                double y = (double) paramSphereY.getValue();
                double z = (double) paramSphereZ.getValue();
                double r = (double) paramSphereRadius.getValue();

                Logger.info("Création d'une sphere. X: " + x + ", Y: " + y + ", Z: " + z
                		+ ", Rayon: " + r);

                Sphere s = new Sphere(r, 3);
                Position pos = new Position(x, y, z);

                Color[] colors = ColorUtils.getTwoDistinctColors();

                Object3D sphere = new Object3D(s)
                        .setPosition(pos)
                        .setColor(colors[0], colors[1]);
                PhysicObject po = new PhysicObject(s, pos);

                scene.addObject(sphere);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, world, sphere, po, "");

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
	}
}
