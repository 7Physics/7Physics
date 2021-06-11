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



        /* ******** *
         * topPanel *
         * ******** */
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorBackground);



        /* **** *
         * Name *
         * **** */
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(colorBackground);
        JPanel splitTopPanel = new JPanel(new BorderLayout());
        splitTopPanel.setBackground(colorBackground);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(colorBackground);
        JLabel nameLabel = new JLabel("Nom : ");
        JLabel nameObject = new JLabel(name);
        namePanel.add(nameLabel);
        namePanel.add(nameObject);



        /* ************ *
         * DeleteButton *
         * ************ */
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deletePanel.setBackground(colorBackground);
        JButton deleteButton = new JButton(GUI.TRASH);
        deleteButton.setBackground(new Color(252, 91, 91));
        deletePanel.add(deleteButton);



        /* ****************** *
         * Gestion des panels *
         * ****************** */
        splitTopPanel.add(emptyPanel, BorderLayout.NORTH);
        splitTopPanel.add(namePanel, BorderLayout.WEST);
        splitTopPanel.add(deletePanel, BorderLayout.EAST);
        topPanel.add(splitTopPanel, BorderLayout.NORTH);
        this.add(topPanel, BorderLayout.NORTH);



        /* ******************************** *
         * Gestion du bouton de suppression *
         * ******************************** */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Suppression du contenu du Panel de détails
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



        /* ************* *
         * Panel central *
         * ************* */
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(colorBackground);



        /* ****************************** *
         * Séparation du centerPanel en 2 *
         * ****************************** */
        JPanel propPanel = new JPanel(new BorderLayout());
        propPanel.setBackground(colorBackground);
        JPanel forcesPanel = new JPanel(new BorderLayout());
        forcesPanel.setBackground(colorBackground);



        /* ********** *
         * Propriétés *
         * ********** */
        JPanel splitPropPanel = new JPanel(new BorderLayout());
        splitPropPanel.setBackground(colorBackground);
        JPanel topPropPanel = new JPanel();
        topPropPanel.setBackground(colorBackground);

        JPanel proprietePanel = new JPanel();
        proprietePanel.setBackground(colorBackground);
        proprietePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //JLabel prop = new JLabel("Propriétés :");
        //proprietePanel.add(prop);

        splitPropPanel.add(topPropPanel, BorderLayout.NORTH);
        splitPropPanel.add(proprietePanel, BorderLayout.CENTER);
        propPanel.add(splitPropPanel, BorderLayout.NORTH);



        /* **************** *
         * Table des forces *
         * **************** */
        JPanel slipTopForcePanel = new JPanel(new BorderLayout());
        slipTopForcePanel.setBackground(colorBackground);
        JPanel titleForcePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleForcePanel.setBackground(colorBackground);
        JPanel buttonForcePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonForcePanel.setBackground(colorBackground);

        JLabel titleForce = new JLabel("Forces :");
        titleForcePanel.add(titleForce);

        JButton plusButton = new JButton(GUI.PLUS);
        JButton minusButton = new JButton(GUI.MINUS);
        buttonForcePanel.add(plusButton);
        buttonForcePanel.add(minusButton);

        slipTopForcePanel.add(titleForcePanel, BorderLayout.WEST);
        slipTopForcePanel.add(buttonForcePanel, BorderLayout.EAST);
        forcesPanel.add(slipTopForcePanel, BorderLayout.NORTH);

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
                    Vector<Double> values = (Vector<Double>) forcesTable.getDataVector().get(tableModelEvent.getFirstRow());
                    Vec3 force = physicObject.getForces().get(tableModelEvent.getFirstRow());
                    force.setX(values.get(0));
                    force.setY(values.get(1));
                    force.setZ(values.get(2));
                }
            }
        });
        JTable table = new JTable(forcesTable);
        table.setBackground(new Color(129, 150, 179));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(colorBackground);
        scroll.getViewport().setBackground(colorBackground);
        forcesPanel.add(scroll, BorderLayout.CENTER);



        /* ******************* *
         * Boutons de la table *
         * ******************* */
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
            	int row = table.getSelectedRow();
            	int index = GUI.getInstance().getOngletsBottom().isGravityEnabled() ? row + 1 : row;
                physicObject.removeForce(index);
                forcesTable.removeRow(table.getSelectedRow());
            }
        });



        /* **************** *
         * Ajout des panels *
         * **************** */
        centerPanel.add(propPanel, BorderLayout.NORTH);
        centerPanel.add(forcesPanel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}
