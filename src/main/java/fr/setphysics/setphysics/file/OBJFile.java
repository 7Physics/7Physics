package fr.setphysics.setphysics.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.gui.GUI;

/**
 * Classe permettant de manipuler des fichiers *.obj.
 *
 */
public class OBJFile {
	/* Sommets contenus dans le fichier *.obj */
	private List<Vec3> fileVertices = new ArrayList<Vec3>();
	/* Formes contenues dans le fichier *.obj avec leurs faces */
	private Map<String, List<Vec3>> shapes = new HashMap<String, List<Vec3>>();
	/* Formes contenues dans le fichier *.obj avec leurs sommets */
	private Map<String, List<Vec3>> vecByShape = new HashMap<String, List<Vec3>>();

	/**
	 * Lire un fichier et récupérer les informations nécessaires à la création des
	 * formes dans la scène 3D.
	 * 
	 * @param file : le fichier
	 */
	public void readFile(File file) {
		FileReader fr;
		String line;
		BufferedReader buffer = null;
		StringBuffer sb = new StringBuffer();
		String currentShape = "";

		if (file.getAbsolutePath().endsWith(".obj")) {

			try {
				fr = new FileReader(file);
				buffer = new BufferedReader(fr);

				while ((line = buffer.readLine()) != null) {
					sb.append(line);
					sb.append("\n");

					// Une nouvelle forme est détectée
					if (line.startsWith("o ")) {
						currentShape = line.split(" ")[1];
						this.shapes.put(currentShape, new ArrayList<Vec3>());
						this.vecByShape.put(currentShape, new ArrayList<Vec3>());
					}
					// On récupère la liste des sommets
					else if (line.startsWith("v ")) {
						String[] lContent = line.split(" ");
						Vec3 vec = new Vec3(Double.parseDouble(lContent[1]), Double.parseDouble(lContent[2]),
								Double.parseDouble(lContent[3]));
						this.fileVertices.add(vec);
						this.vecByShape.get(currentShape).add(vec);
					}
					// On récupère la liste des triangles (formant les différentes faces)
					else if (line.startsWith("f ")) {
						String[] lContent = line.split(" ");
						for (int i = 1; i < lContent.length; i++) {
							String[] vertex = lContent[i].split("/");
							this.shapes.get(currentShape)
									.add(this.fileVertices.get(Integer.parseInt(vertex[0]) - 1).clone());
						}
					}
				}
				fr.close();

			} catch (FileNotFoundException e) {
				Logger.error("Impossible d'ouvrir le fichier " + file.getAbsolutePath());
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane optionPane = new JOptionPane("Il faut un fichier *.obj !", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Erreur extension fichier");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}

	}

	/**
	 * Récupérer les différentes formes du fichier *.obj avec leurs faces.
	 * 
	 * @return un dictionnaire ayant pour clé le nom de la forme et pour valeur ses
	 *         faces (composées de 3 sommets).
	 */
	public Map<String, List<Vec3>> getShapes() {
		return this.shapes;
	}

	/**
	 * Récupérer les différentes formes du fichier *.obj avec leur sommets.
	 * 
	 * @return un dictionnaire ayant pour clé le nom de la forme et pour valeur ses
	 *         sommets.
	 */
	public Map<String, List<Vec3>> getVerticesByShape() {
		return this.vecByShape;
	}

	/**
	 * Créer un fichier *.obj à partir de son nom et de la scene3D actuelle.
	 * 
	 * @param scene    : représente les différents objets 3D du projet en cours
	 *                 d'exportation.
	 * @param fileName : représente le nom du fichier avec son emplacement.
	 */
	public void createFile(Scene3D scene, String fileName) {
		String extension = ".obj";
		Path path = Paths.get(fileName.endsWith(extension) ? fileName : (fileName + extension));
		String fileContent = "# Sauvegarde 7Physics\n";
		int cpt = 0;
		int offset = 0;
		// Récuperer chaque objet présent sur la scène
		for (Object3D o : scene) {
			// Création de la section pour la forme courante
			fileContent += "o Forme" + cpt + "\n";
			List<Vec3> faceVertices = o.getShape().getVertices();
			Set<Vec3> vertices = new HashSet<Vec3>(faceVertices);
			List<Vec3> verticesIndex = new ArrayList<Vec3>();
			cpt++;
			// Création de la section contenant les sommets de la forme
			for (Vec3 v : vertices) {
				fileContent += "v " + (v.getX() + o.getPosition().getX()) + " " + (v.getY() + o.getPosition().getY())
						+ " " + (v.getZ() + o.getPosition().getZ()) + "\n";
				verticesIndex.add(v);
			}
			int nbFaces = faceVertices.size();
			// Création de la section contenant les faces de la forme
			for (int i = 0; i < nbFaces; i += 3) {
				fileContent += "f " + (verticesIndex.indexOf(faceVertices.get(i)) + 1 + offset) + "// "
						+ (verticesIndex.indexOf(faceVertices.get(i + 1)) + 1 + offset) + "// "
						+ (verticesIndex.indexOf(faceVertices.get(i + 2)) + 1 + offset) + "// " + "\n";
			}
			offset += vertices.size();
		}
		byte[] b = fileContent.getBytes();
		try {
			// Ecriture du contenu du fichier
			Files.write(path, b);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
