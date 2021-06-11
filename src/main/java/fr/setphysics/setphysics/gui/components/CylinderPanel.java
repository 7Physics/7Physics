package fr.setphysics.setphysics.gui.components;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.geom.shape.Cylinder;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
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



        /* ********************************** *
         * Initialisation de la partie gauche *
         * ********************************** */
        JPanel cylinderPaneLeft = new JPanel();
        cylinderPaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        cylinderPaneLeft.setBackground(new Color(93, 129, 156));
        this.add(cylinderPaneLeft, BorderLayout.WEST);



        /* ********************************** *
         * Initialisation de la partie droite *
         * ********************************** */
        JPanel cylinderPaneRight = new JPanel();
        cylinderPaneRight.setBackground(new Color(78, 104, 138));
        this.add(cylinderPaneRight, BorderLayout.CENTER);



        /* **************************** *
         * Initialisation du GridLayout *
         * **************************** */
        GridLayout cylinderLayout = new GridLayout(6, 2);
        cylinderPaneLeft.setLayout(cylinderLayout);
        cylinderLayout.setHgap(-1);
        cylinderLayout.setVgap(15);



        /* ************* *
         * Paramètre "x" *
         * ************* */
        JLabel textParamCylinderX = new JLabel("x :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderX);
        JSpinner paramCylinderX = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCylinderX.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderX);



        /* ************* *
         * Paramètre "y" *
         * ************* */
        JLabel textParamCylinderY = new JLabel("y :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderY);
        JSpinner paramCylinderY = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCylinderY.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderY);



        /* ************* *
         * Paramètre "z" *
         * ************* */
        JLabel textParamCylinderZ = new JLabel("z :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderZ);
        JSpinner paramCylinderZ = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramCylinderZ.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderZ);



        /* ***************** *
         * Paramètre "Rayon" *
         * ***************** */
        JLabel textParamCylinderRadius = new JLabel("Rayon :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderRadius);
        JSpinner paramCylinderRadius = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramCylinderRadius.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderRadius);



        /* ******************* *
         * Paramètre "Hauteur" *
         * ******************* */
        JLabel textParamCylinderHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        cylinderPaneLeft.add(textParamCylinderHeight);
        JSpinner paramCylinderHeight = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramCylinderHeight.setPreferredSize(new Dimension(50, 20));
        cylinderPaneLeft.add(paramCylinderHeight);



        /* ********************************* *
         * Configuration de la partie droite *
         * ********************************* */
        cylinderPaneRight.setLayout(new BorderLayout());

        JPanel previewCylinder = new JPanel();
        previewCylinder.setBackground(new Color(78, 104, 138));
        JPanel addCylinderButtonPanel = new JPanel(new BorderLayout());
        addCylinderButtonPanel.setBackground(new Color(78, 104, 138));

        cylinderPaneRight.add(previewCylinder, BorderLayout.CENTER);
        cylinderPaneRight.add(addCylinderButtonPanel, BorderLayout.SOUTH);

        // Partie haute du Panel avec la preview
        previewCylinder.setLayout(new BorderLayout());

        Camera previewCam = new Camera(new Position(-1.2, 1.3, -1.2));
        previewCam.setAngles(45*Math.PI/180, -23*Math.PI/180);
        final Scene3D previewScene = new Scene3D(previewCam);
        final World previewWorld = new World();

        previewCylinder.add(previewScene, BorderLayout.CENTER);
        previewScene.repaint();
        previewScene.revalidate();

        Cylinder previewCylinderObj = new Cylinder(0.2, 0.5);
        Position previewPos = new Position(0, 0.5, 0);
        PhysicObject previewPhObj = new PhysicObject(previewCylinderObj, previewPos);
        Object3D previewObj = new Object3D(previewCylinderObj, previewPos, new Color(0,128,128,128), Color.WHITE);
        previewScene.addObject(previewObj);
        previewWorld.addPhysicObject(previewPhObj);

        // Partie basse du Panel avec le bouton
        JPanel addCylinderEmptyPanel = new JPanel();
        addCylinderEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addCylinderButton = new JButton(GUI.PLUS);
        addCylinderButtonPanel.add(addCylinderEmptyPanel, BorderLayout.CENTER);
        addCylinderButtonPanel.add(addCylinderButton, BorderLayout.EAST);



        /* ********************* *
         * Gestion du bouton "+" *
         * ********************* */
        addCylinderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = (double) paramCylinderX.getValue();
                double y = (double) paramCylinderY.getValue();
                double z = (double) paramCylinderZ.getValue();
                double r = (double) paramCylinderRadius.getValue();
                double h = (double) paramCylinderHeight.getValue();

                Logger.info("Création d'une cylinder. X: " + x + ", Y: " + y + ", Z: " + z
                        + ", Rayon: " + r + ", Height: " + h);

                Cylinder s = new Cylinder(r, h);
                Position pos = new Position(x, y, z);
                Object3D cylinder = new Object3D(s, pos, new Color(0,128,128,128), Color.WHITE);
                PhysicObject po = new PhysicObject(s, pos);

                scene.addObject(cylinder);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, cylinder, null, "");

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
    }
}
