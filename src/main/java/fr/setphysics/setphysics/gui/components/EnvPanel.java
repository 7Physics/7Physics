package fr.setphysics.setphysics.gui.components;

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import fr.setphysics.renderer.Scene3D;

@SuppressWarnings("serial")
public class EnvPanel extends JTabbedPane {
    private final JPanel ongletObjet;
    private JLabel descObj;



    public EnvPanel(Scene3D scene) {
	    /* **************************************************************** *
	     * Affichage et contenu de l'onglet "Paramètres de l'environnement" *
	     * **************************************************************** */
        JPanel ongletEnvironnement = new JPanel();
        ongletEnvironnement.setBorder(new EmptyBorder(30, 30, 30, 30));
            ongletEnvironnement.setBackground(new Color(93, 129, 156));
        this.addTab("Environnement", ongletEnvironnement);
        JLabel descEnv = new JLabel("Paramètres de l'environnement");
        ongletEnvironnement.setLayout(new GridBagLayout());
        ongletEnvironnement.add(descEnv);



        /* ******************************************************** *
         * Affichage et contenu de l'onglet "Paramètres de l'objet" *
         * ******************************************************** */
        // Création du contenant
        ongletObjet = new JPanel();
        ongletObjet.setBackground(new Color(93, 129, 156));
        ongletObjet.setLayout(new CardLayout());
        this.addTab("Objet", ongletObjet);

        // Création du contenu
        descObj = new JLabel("Paramètres de l'objet", SwingConstants.CENTER);
        ongletObjet.add(descObj, BorderLayout.CENTER);
	}


    /**
     * Renvoie le Panel "ongletObjet"
     * @return JPanel
     */
    public JPanel getOngletObjet() {
        return ongletObjet;
    }
}
