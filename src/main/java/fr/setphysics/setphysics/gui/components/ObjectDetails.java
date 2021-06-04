package fr.setphysics.setphysics.gui.components;

import fr.setphysics.common.geom.Vec3;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ObjectDetails extends JPanel {

    public ObjectDetails(String name, ObjectPanel objectPanel, Scene3D scene, Object3D object, PhysicObject physicObject) {
        Color colorBackground = new Color(93, 129, 156);
        setPreferredSize(new Dimension(333, 0));
        this.setBackground(colorBackground);
        this.setLayout(new BorderLayout());

        // topPanel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(colorBackground);
        topPanel.setLayout(new BorderLayout());

        // Name
        JPanel namePanel = new JPanel();
        namePanel.setBackground(colorBackground);
        JLabel nameLabel = new JLabel("Nom : ");
        JLabel nameObject = new JLabel(name);
        namePanel.add(nameLabel);
        namePanel.add(nameObject);
        topPanel.add(namePanel, BorderLayout.NORTH);

        // DeleteButton
        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(colorBackground);
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setBackground(new Color(252, 91, 91));
        deletePanel.add(deleteButton);
        topPanel.add(deletePanel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Suppression du contenu de
                ObjectDetails.this.removeAll();
                ObjectDetails.this.revalidate();
                ObjectDetails.this.repaint();
                JPanel ongletObjet = new JPanel();
                ongletObjet.setBackground(new Color(93, 129, 156));
                ongletObjet.setLayout(new BorderLayout());
                JLabel descObj = new JLabel("Paramètres de l'objet", SwingConstants.CENTER);
                ongletObjet.add(descObj, BorderLayout.CENTER);
                ObjectDetails.this.add(ongletObjet, SwingConstants.CENTER);

                // Suppression de la carte de l'objet
                GUI.getInstance().getObjectListPanel().removeObjectPanel(objectPanel);

                // Suppression de l'objet de la scene
                scene.removeObject(object);
            }
        });

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(colorBackground);

        // centerPanel en 2
        JPanel propPanel = new JPanel();
        JPanel forcesPanel = new JPanel();
        propPanel.setLayout(new BorderLayout());
        forcesPanel.setLayout(new BorderLayout());
        propPanel.setBackground(colorBackground);
        forcesPanel.setBackground(colorBackground);

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

        DefaultTableModel forcesTable = new DefaultTableModel(new String[]{"x", "y", "z"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Double.class;
            }
        };
        forcesTable.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                System.out.println("ça change");
                if (tableModelEvent.getType() == TableModelEvent.UPDATE) {
                System.out.println("ça update");
                    Vector<Double> values = forcesTable.getDataVector().<Vector<Double>>get(tableModelEvent.getFirstRow());
                    Vec3 force = physicObject.getForces().get(tableModelEvent.getFirstRow());
                    force.setX(values.get(0));
                    force.setY(values.get(1));
                    force.setZ(values.get(2));
                }
            }
        });
        JTable table = new JTable(forcesTable);
        table.setBackground(Color.YELLOW);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(Color.GREEN);
        scroll.getViewport().setBackground(Color.BLACK);
        forcesPanel.add(scroll, BorderLayout.CENTER);

        // Boutons de la table
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                physicObject.addForce(new Vec3(0, 0, 0));
                forcesTable.addRow(new Double[]{0d, 0d, 0d});
            }
        });

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                physicObject.getForces().remove(table.getSelectedRow());
                forcesTable.removeRow(table.getSelectedRow());
            }
        });

        // add global
        centerPanel.add(propPanel, BorderLayout.NORTH);
        centerPanel.add(forcesPanel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}
