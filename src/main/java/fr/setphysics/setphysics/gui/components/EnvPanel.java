package fr.setphysics.setphysics.gui.components;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

import fr.setphysics.renderer.Scene3D;

@SuppressWarnings("serial")
public class EnvPanel extends JTabbedPane {
    private final JPanel ongletObjet;



    public EnvPanel(Scene3D scene) {
	    /* **************************************************************** *
	     * Affichage et contenu de l'onglet "Paramètres de l'environnement" *
	     * **************************************************************** */
        JPanel ongletEnvironnement = new JPanel();
        this.addTab("Environnement", ongletEnvironnement);
        JLabel descEnv = new JLabel("Paramètres de l'environnement");
        ongletEnvironnement.setLayout(new GridBagLayout());
        ongletEnvironnement.add(descEnv);



        /* ******************************************************** *
         * Affichage et contenu de l'onglet "Paramètres de l'objet" *
         * ******************************************************** */
        // Création du contenant
        ongletObjet = new JPanel();
        ongletObjet.setLayout(new CardLayout());
        this.addTab("Objet", ongletObjet);

        // Création du contenu
        /*JLabel descObj = new JLabel("Paramètres de l'objet");
        ongletObjet.setLayout(new GridBagLayout());
        ongletObjet.add(descObj);*/
        //testAffichage();
	}

    public JPanel getOngletObjet() {
        return ongletObjet;
    }

    public void testAffichage() {
        ongletObjet.removeAll();
        ongletObjet.repaint();
        ongletObjet.revalidate();

        Random rand = new Random();
        ongletObjet.setBackground(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
    }
}
