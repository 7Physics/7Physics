package fr.setphysics.setphysics.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.file.OBJFile;

/**
 * 
 * Classe représentant le comportement effectué lors de l'exporation d'un
 * fichier *.obj.
 */
public class FileExportation implements ActionListener {
	private Scene3D scene;

	public FileExportation(Scene3D scene) {
		this.scene = scene;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Propose à l'utilisateur de choisir l'emplacement et le nom du fichier *.obj
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Exporter un projet");

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			//Création du fichier *.obj représentant le projet en cours.
			OBJFile objFile = new OBJFile();
			objFile.createFile(scene,selectedFile.getAbsolutePath());
		}
	}
}
