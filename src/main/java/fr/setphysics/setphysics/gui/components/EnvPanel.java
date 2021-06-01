package fr.setphysics.setphysics.gui.components;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import fr.setphysics.renderer.Scene3D;

@SuppressWarnings("serial")
public class EnvPanel extends JTabbedPane {
	public EnvPanel(Scene3D scene) {
        // Affichage et contenu de l'onglet "Paramètres de l'environnement"
        JPanel ongletEnvironnement = new JPanel();
        ongletEnvironnement.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.addTab("Environnement", ongletEnvironnement);
        JLabel descEnv = new JLabel("Paramètres de l'environnement");
        ongletEnvironnement.setLayout(new GridBagLayout());
        ongletEnvironnement.add(descEnv);

        // Affichage et contenu de l'onglet "Paramètres de l'objet"
        JPanel ongletObjet = new JPanel();
        this.addTab("Objet", ongletObjet);
        JLabel descObj = new JLabel("Paramètres de l'objet");
        ongletObjet.setLayout(new GridBagLayout());
        ongletObjet.add(descObj);
	}
}
