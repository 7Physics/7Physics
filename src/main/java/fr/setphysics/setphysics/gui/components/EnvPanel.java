package fr.setphysics.setphysics.gui.components;

import java.awt.*;
import java.util.Dictionary;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.setphysics.common.geom.Vec3;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Scene3D;

@SuppressWarnings("serial")
public class EnvPanel extends JTabbedPane {
    private final JPanel ongletObjet;
    private JLabel descObj;
    private boolean isGravity;


    public EnvPanel(Scene3D scene, World world) {
        Color colorBackground = new Color(93, 129, 156);


	    /* **************************************************************** *
	     * Affichage et contenu de l'onglet "Paramètres de l'environnement" *
	     * **************************************************************** */
        JPanel ongletEnvironnement = new JPanel(new BorderLayout());
        ongletEnvironnement.setBackground(colorBackground);
        this.addTab("Environnement", ongletEnvironnement);

        JPanel splitTopPanel = new JPanel(new BorderLayout());
        splitTopPanel.setBackground(colorBackground);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(colorBackground);
        JPanel checkGravityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkGravityPanel.setBackground(colorBackground);
        JPanel sliderValuePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sliderValuePanel.setBackground(colorBackground);
        JPanel sliderGravityPanel = new JPanel();
        sliderGravityPanel.setBackground(colorBackground);

        // Gestion du label et checkbox
        JLabel gravityTitle = new JLabel("Gravité :");
        JCheckBox gravityCheckbox = new JCheckBox();
        gravityCheckbox.setBackground(colorBackground);
        checkGravityPanel.add(gravityTitle);
        checkGravityPanel.add(gravityCheckbox);

        // Gestion du slider
        JSlider gravitySlider = new JSlider(JSlider.HORIZONTAL, 0, 2000, 981);
        gravitySlider.setBackground(colorBackground);
        Dictionary<Integer,JLabel> pointDico = new Hashtable<Integer,JLabel>() {};
        pointDico.put(0,new JLabel("0"));
        pointDico.put(500,new JLabel("5"));
        pointDico.put(1000,new JLabel("10"));
        pointDico.put(1500,new JLabel("15"));
        pointDico.put(2000,new JLabel("20"));
        gravitySlider.setLabelTable(pointDico);
        gravitySlider.setMajorTickSpacing(500);
        gravitySlider.setMinorTickSpacing(0);
        gravitySlider.setPaintTicks(true);
        gravitySlider.setPaintLabels(true);

        // Affichage dynamique de la force
        JLabel valueSlider = new JLabel("9.81 m/s²");
        final double[] finalValueSlider = {9.81};
        gravitySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double tempValue = ((JSlider)e.getSource()).getValue();
                finalValueSlider[0] = tempValue/100;
                valueSlider.setText(finalValueSlider[0] + " m/s²");
                world.setGravity(new Vec3(0, -finalValueSlider[0], 0));
            }
        });
        sliderGravityPanel.add(gravitySlider);
        sliderValuePanel.add(valueSlider);

        // Ajout aux panels
        splitTopPanel.add(emptyPanel, BorderLayout.NORTH);
        splitTopPanel.add(checkGravityPanel, BorderLayout.WEST);
        splitTopPanel.add(sliderValuePanel, BorderLayout.EAST);
        ongletEnvironnement.add(splitTopPanel, BorderLayout.NORTH);
        ongletEnvironnement.add(sliderGravityPanel, BorderLayout.CENTER);

        // Gestion de l'action de la checkbox
        gravityCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				EnvPanel.this.isGravity = !EnvPanel.this.isGravity;
				if (EnvPanel.this.isGravity) {
				    revalidate();
					world.setGravity(new Vec3(0, -finalValueSlider[0], 0));
                    System.out.println(-finalValueSlider[0]);
				} else {
					world.deleteGravity();
				}
			}
        });



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



    /**
     * Indique si la gravité est présente ou non
     * @return true si la gravité est activée.
     */
    public boolean isGravityEnabled() {
    	return isGravity;
    }
}
