package fr.setphysics.setphysics.gui.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.setphysics.renderer.Camera;
import fr.setphysics.setphysics.gui.GUI;


@SuppressWarnings("serial")
public class CamPanel extends JPanel {
	public CamPanel(Camera cam) {

        /* ************************* *
         * Gestion du bouton de zoom *
         * ************************* */
        JButton zoomInButton = new JButton(GUI.ZOOMIN);
        zoomInButton.setOpaque(false);
        zoomInButton.setContentAreaFilled(false);
        zoomInButton.setBorderPainted(false);
        this.add(zoomInButton);
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cam.zoom(-2.0);
            }
        });



        /* *************************** *
         * Gestion du bouton de dézoom *
         * *************************** */
        JButton zoomOutButton = new JButton(GUI.ZOOMOUT);
        zoomOutButton.setOpaque(false);
        zoomOutButton.setContentAreaFilled(false);
        zoomOutButton.setBorderPainted(false);
        this.add(zoomOutButton);
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cam.dezoom();
            }
        });
	}
}
