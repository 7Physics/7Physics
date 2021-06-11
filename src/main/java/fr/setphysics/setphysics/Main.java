package fr.setphysics.setphysics;

import java.awt.EventQueue;

import fr.setphysics.common.logger.Logger;
import fr.setphysics.setphysics.gui.GUI;

import javax.swing.*;

public class Main {
      

    public static void main(String [] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI.getInstance().setVisible(true);
            }
        });
    }
}
