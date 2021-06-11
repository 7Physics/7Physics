package fr.setphysics.setphysics.gui.components;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.geom.shape.Pyramid;
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
public class PyramidPanel extends JPanel {
    private boolean isSquareBase;


    public PyramidPanel(Scene3D scene, World world) {
        this.isSquareBase = false;
        this.setLayout(new BorderLayout());



        /* ********************************** *
         * Initialisation de la partie gauche *
         * ********************************** */
        JPanel pyramidPaneLeft = new JPanel();
        pyramidPaneLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
        pyramidPaneLeft.setBackground(new Color(93, 129, 156));
        this.add(pyramidPaneLeft, BorderLayout.WEST);



        /* ********************************** *
         * Initialisation de la partie droite *
         * ********************************** */
        JPanel pyramidPaneRight = new JPanel();
        pyramidPaneRight.setBackground(new Color(78, 104, 138));
        pyramidPaneRight.setPreferredSize(new Dimension(170, 50));
        this.add(pyramidPaneRight, BorderLayout.CENTER);



        /* **************************** *
         * Initialisation du GridLayout *
         * **************************** */
        GridLayout pyramidLayout = new GridLayout(6, 2);
        pyramidPaneLeft.setLayout(pyramidLayout);
        pyramidLayout.setHgap(-1);
        pyramidLayout.setVgap(15);



        /* ************* *
         * Paramètre "x" *
         * ************* */
        JLabel textParamPyramidX = new JLabel("x :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidX);
        JSpinner paramPyramidX = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramPyramidX.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidX);



        /* ************* *
         * Paramètre "y" *
         * ************* */
        JLabel textParamPyramidY = new JLabel("y :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidY);
        JSpinner paramPyramidY = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramPyramidY.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidY);



        /* ************* *
         * Paramètre "z" *
         * ************* */
        JLabel textParamPyramidZ = new JLabel("z :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidZ);
        JSpinner paramPyramidZ = new JSpinner(new SpinnerNumberModel(0, -20, 20, 0.1));
        paramPyramidZ.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidZ);



        /* ******************* *
         * Paramètre "Largeur" *
         * ******************* */
        JLabel textParamPyramidWidth = new JLabel("Largeur :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidWidth);
        JSpinner paramPyramidWidth = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramPyramidWidth.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidWidth);



        /* ******************* *
         * Paramètre "Hauteur" *
         * ******************* */
        JLabel textParamPyramidHeight = new JLabel("Hauteur :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidHeight);
        JSpinner paramPyramidHeight = new JSpinner(new SpinnerNumberModel(0, 0, 20, 0.1));
        paramPyramidHeight.setPreferredSize(new Dimension(50, 20));
        pyramidPaneLeft.add(paramPyramidHeight);



        /* ******************************* *
         * Paramètre de forme pour la base *
         * ******************************* */
        JLabel textParamPyramidSquareBase = new JLabel("BaseCarré :", SwingConstants.RIGHT);
        pyramidPaneLeft.add(textParamPyramidSquareBase);
        JCheckBox paramPyramidSquareBaseCheckbox = new JCheckBox();
        paramPyramidSquareBaseCheckbox.setBackground(new Color(93, 129, 156));
        pyramidPaneLeft.add(paramPyramidSquareBaseCheckbox);

        // Action de la checkbox
        paramPyramidSquareBaseCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                PyramidPanel.this.isSquareBase = !PyramidPanel.this.isSquareBase;
                if (PyramidPanel.this.isSquareBase) {
                    System.out.println("check");
                    isSquareBase = true;
                } else {
                    System.out.println("not check");
                    isSquareBase = false;
                }
            }
        });



        /* ********************************* *
         * Configuration de la partie droite *
         * ********************************* */
        pyramidPaneRight.setLayout(new BorderLayout());

        JPanel previewPyramid = new JPanel();
        previewPyramid.setBackground(new Color(78, 104, 138));
        JPanel addPyramidButtonPanel = new JPanel(new BorderLayout());
        addPyramidButtonPanel.setBackground(new Color(78, 104, 138));

        pyramidPaneRight.add(previewPyramid, BorderLayout.CENTER);
        pyramidPaneRight.add(addPyramidButtonPanel, BorderLayout.SOUTH);

        // Partie haute du Panel avec la preview
        previewPyramid.setLayout(new BorderLayout());

        Camera previewCam = new Camera(new Position(-1.2, 1.3, -1.2));
        previewCam.setAngles(45*Math.PI/180, -23*Math.PI/180);
        final Scene3D previewScene = new Scene3D(previewCam);
        final World previewWorld = new World();

        previewPyramid.add(previewScene, BorderLayout.CENTER);
        previewScene.repaint();
        previewScene.revalidate();

        Pyramid previewPyramidObj1 = new Pyramid(0.4, 0.4, 0.5);
        Position previewPos1 = new Position(0.2, 0.5, -0.2);
        PhysicObject previewPhObj1 = new PhysicObject(previewPyramidObj1, previewPos1);
        Object3D previewObj1 = new Object3D(previewPyramidObj1, previewPos1, new Color(128, 128, 128, 128), Color.WHITE);
        previewScene.addObject(previewObj1);
        previewWorld.addPhysicObject(previewPhObj1);

        Pyramid previewPyramidObj2 = new Pyramid(0.4, 0.5);
        Position previewPos2 = new Position(-0.2, 0.5, 0.2);
        PhysicObject previewPhObj2 = new PhysicObject(previewPyramidObj2, previewPos2);
        Object3D previewObj2 = new Object3D(previewPyramidObj2, previewPos2, new Color(128, 128, 128, 128), Color.WHITE);
        previewScene.addObject(previewObj2);
        previewWorld.addPhysicObject(previewPhObj2);

        // Partie basse du Panel avec le bouton
        JPanel addPyramidEmptyPanel = new JPanel();
        addPyramidEmptyPanel.setBackground(new Color(78, 104, 138));
        JButton addPyramidButton = new JButton(GUI.PLUS);
        addPyramidButtonPanel.add(addPyramidEmptyPanel, BorderLayout.CENTER);
        addPyramidButtonPanel.add(addPyramidButton, BorderLayout.EAST);



        /* ********************* *
         * Gestion du bouton "+" *
         * ********************* */
        addPyramidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double x = (double) paramPyramidX.getValue();
                double y = (double) paramPyramidY.getValue();
                double z = (double) paramPyramidZ.getValue();
                double w = (double) paramPyramidWidth.getValue();
                double h = (double) paramPyramidHeight.getValue();

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
                Object3D obj = new Object3D(pyramid, pos, new Color(128, 128, 128, 128), Color.WHITE);
                scene.addObject(obj);
                world.addPhysicObject(po);

                ObjectPanel objectPanel = new ObjectPanel(scene, world, obj, po, "");

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        });
    }
}
