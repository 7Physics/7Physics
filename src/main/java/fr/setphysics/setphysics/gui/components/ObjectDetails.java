package fr.setphysics.setphysics.gui.components;

import fr.setphysics.engine.PhysicObject;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObjectDetails extends JPanel {

    public ObjectDetails(Object3D object, PhysicObject physicObject) {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        // Name
        JLabel name = new JLabel("Nom");
        this.add(name, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.MAGENTA);

        // centerPanel en 2
        JPanel propPanel = new JPanel();
        JPanel forcesPanel = new JPanel();
        propPanel.setLayout(new BorderLayout());
        forcesPanel.setLayout(new BorderLayout());
        propPanel.setBackground(Color.CYAN);
        forcesPanel.setBackground(Color.RED);

        // Propriétés
        JLabel prop = new JLabel("Propriétés");
        propPanel.add(prop, BorderLayout.NORTH);

        // Table des forces
        JPanel headForce = new JPanel(new GridLayout(1, 3));
        headForce.setBackground(Color.ORANGE);
        JLabel forces = new JLabel("Forces");
        JButton plusButton = new JButton(GUI.PLUS);
        JButton minusButton = new JButton(GUI.MINUS);
        headForce.add(forces);
        headForce.add(plusButton);
        headForce.add(minusButton);
        forcesPanel.add(headForce, BorderLayout.NORTH);

        DefaultTableModel forcesTable = new DefaultTableModel(new String[]{"x", "y", "z"}, 0);
        JTable table = new JTable(forcesTable);
        table.setBackground(Color.YELLOW);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(Color.GREEN);
        scroll.getViewport().setBackground(Color.BLACK);
        forcesPanel.add(scroll, BorderLayout.CENTER);

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                forcesTable.addRow(new Object[]{});
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                forcesTable.removeRow(forcesTable.getRowCount()-1);
            }
        });

        // add global
        centerPanel.add(propPanel, BorderLayout.NORTH);
        centerPanel.add(forcesPanel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}
