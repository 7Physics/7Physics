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

    public ObjectPanel(Scene3D scene, Object3D object, PhysicObject body) {
        this.object = object;
        this.body = body;

        index++;
        String formName = object.getShape().getClass().getSimpleName();
        final String name = formName + index;

        JPanel buttonPanel = new JPanel(new BorderLayout());

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

        JLabel titleForm = new JLabel(name, SwingConstants.CENTER);
        titleForm.setFont(new Font("Arial", Font.BOLD, 10));

        buttonPanel.add(displayForm, BorderLayout.CENTER);
        buttonPanel.add(titleForm, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(65, 65));
        this.setBackground(new Color(52, 68, 90));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.add(buttonPanel, BorderLayout.CENTER);

        currentObjectPane = new ObjectDetails(name, this, scene, object, body);
        GUI.getInstance().getOngletsBottom().getOngletObjet().add(currentObjectPane, name);
        final CardLayout cl = (CardLayout) GUI.getInstance().getOngletsBottom().getOngletObjet().getLayout();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(GUI.getInstance().getOngletsBottom().getOngletObjet(), name);
                System.out.println(name);
            }
        });
    }

    public PhysicObject getBody() {
        return body;
    }

    public Object3D getObject() {
        return object;
    }
}
