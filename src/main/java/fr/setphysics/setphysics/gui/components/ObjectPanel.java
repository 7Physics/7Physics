package fr.setphysics.setphysics.gui.components;

import fr.setphysics.engine.PhysicObject;
import fr.setphysics.renderer.Object3D;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ObjectPanel extends JButton {
    private final Object3D object;
    private final PhysicObject body;

    public ObjectPanel(Object3D object, PhysicObject body) {
        this.object = object;
        this.body = body;

        Random rand = new Random();
        setPreferredSize(new Dimension(65, 65));
        setBackground(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
        add(new JLabel(object.getShape().getClass().getSimpleName()));
    }

    public PhysicObject getBody() {
        return body;
    }

    public Object3D getObject() {
        return object;
    }
}
