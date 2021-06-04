package fr.setphysics.setphysics.gui.components;

import fr.setphysics.engine.PhysicObject;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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

        Random rand = new Random();
        this.setPreferredSize(new Dimension(65, 65));
        this.setBackground(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
        this.add(new JLabel(formName));

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
