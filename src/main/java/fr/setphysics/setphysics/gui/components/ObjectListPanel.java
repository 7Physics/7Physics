package fr.setphysics.setphysics.gui.components;

import fr.setphysics.engine.World;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;

@SuppressWarnings("serial")
public class ObjectListPanel extends JPanel {
    private final JPanel objectListContent;

	public ObjectListPanel(Scene3D scene, World world) {

        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 120));


        /* ******************************************** *
         * Gestion du bouton de lancement de simulation *
         * ******************************************** */
        JButton buttonPlay = new JButton(GUI.PLAY);
        buttonPlay.setBackground(new Color(189, 213, 234));
        this.add(buttonPlay, BorderLayout.NORTH);
        buttonPlay.addActionListener(new SimulationLauncher(world));



        /* *************************** *
         * Liste du contenu des objets *
         * *************************** */
        // Initialisation du Panel
        objectListContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        objectListContent.setBackground(new Color(85, 130, 139));

        // Gestion de la ScrollBar
        JScrollPane scrollBar = new JScrollPane(objectListContent);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Ajout du Panel à son parent
        this.add(scrollBar, BorderLayout.CENTER);
	}


    /**
     * Ajout d'un objet dans objectListContent
     * @param obj ObjectPanel
     */
    public void addObjectPanel(ObjectPanel obj) {
        objectListContent.add(obj);
        objectListContent.repaint();
        objectListContent.revalidate();
    }


    /**
     * Ajout d'un objet dans objectListContent
     * @param obj ObjectPanel
     */
    public void removeObjectPanel(ObjectPanel obj) {
        objectListContent.remove(obj);
        objectListContent.repaint();
        objectListContent.revalidate();
    }
}
