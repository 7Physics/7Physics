package fr.setphysics.setphysics.gui.components;

import fr.setphysics.engine.PhysicObject;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObjectPanel extends JButton {
    private final Object3D object;
    private final PhysicObject body;
    private static int index = 0;
    private JPanel currentObjectPane;
    private String nameShape;


    public ObjectPanel(Scene3D scene, Object3D object, PhysicObject body, String name) {
        this.object = object;
        this.body = body;

        index++;
        String formName = object.getShape().getClass().getSimpleName();
        if (name.equals("")) {
            nameShape = formName + index;
        } else {
            nameShape = name;
        }

        JPanel buttonPanel = new JPanel(new BorderLayout());



        /* ******************************* *
         * Affichage dynamique de la carte *
         * ******************************* */
        JPanel displayForm = new JPanel(new BorderLayout());
        JLabel display;
        switch(formName) {
            case "Cuboid":
                display = new JLabel(GUI.BIGCUBE);
                displayForm.add(display, BorderLayout.CENTER);
                break;
            case "Sphere":
                display = new JLabel(GUI.BIGSPHERE);
                displayForm.add(display, BorderLayout.CENTER);
                break;
            case "Pyramid":
                display = new JLabel(GUI.BIGPYRAMID);
                displayForm.add(display, BorderLayout.CENTER);
                break;
            case "Cone":
                display = new JLabel(GUI.BIGCONE);
                displayForm.add(display, BorderLayout.CENTER);
                break;
            case "Cylinder":
                display = new JLabel(GUI.BIGCYLINDER);
                displayForm.add(display, BorderLayout.CENTER);
                break;
            default:
                display = new JLabel(GUI.UNKNOWN);
                displayForm.add(display, BorderLayout.CENTER);
        }

        JLabel titleForm = new JLabel(nameShape, SwingConstants.CENTER);
        titleForm.setFont(new Font("Arial", Font.BOLD, 10));

        buttonPanel.add(displayForm, BorderLayout.CENTER);
        buttonPanel.add(titleForm, BorderLayout.SOUTH);



        /* ********************** *
         * Paramètres de la carte *
         * ********************** */
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(65, 65));
        this.setBackground(new Color(52, 68, 90));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.add(buttonPanel, BorderLayout.CENTER);



        /* ********************************** *
         * Gestion de l'affichage de l'onglet *
         * ********************************** */
        currentObjectPane = new ObjectDetails(nameShape, this, scene, object, body);
        GUI.getInstance().getOngletsBottom().getOngletObjet().add(currentObjectPane, nameShape);
        final CardLayout cl = (CardLayout) GUI.getInstance().getOngletsBottom().getOngletObjet().getLayout();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(GUI.getInstance().getOngletsBottom().getOngletObjet(), nameShape);
                System.out.println(nameShape);
            }
        });
    }

    public PhysicObject getBody() {
        return body;
    }

    public Object3D getObject() {
        return object;
    }

    public String getNameShape() {
        return nameShape;
    }
}
