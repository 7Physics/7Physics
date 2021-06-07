package fr.setphysics.setphysics.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.logger.Logger;

/**
 * Classe permettant de manipuler des fichiers *.obj.
 *
 */
public class OBJFile {
	private List<Vec3> fileVertices = new ArrayList<Vec3>();
	private List<Vec3> fileFaces = new ArrayList<Vec3>();
	private Map<String, List<Vec3>> forms = new HashMap<String, List<Vec3>>();

	/**
	 * Lire un fichier et récupérer les informations nécessaires à la création des
	 * formes dans la scène 3D.
	 * 
	 * @param file : le fichier
	 */
	public void readFile(File file) {
//		File file = new File(url.getPath());
		FileReader fr;
		String line;
		BufferedReader buffer = null;
		StringBuffer sb = new StringBuffer();
		String currentForm = "";
		try {
			fr = new FileReader(file);
			buffer = new BufferedReader(fr);

			while ((line = buffer.readLine()) != null) {
				sb.append(line);
				sb.append("\n");

				// Une nouvelle forme est détectée
				if (line.startsWith("o ")) {
					currentForm = line.split(" ")[1];
					this.forms.put(currentForm, new ArrayList<Vec3>());
				}
				// On récupère la liste des sommets
				else if (line.startsWith("v ")) {
					String[] lContent = line.split(" ");
					this.fileVertices.add(new Vec3(Double.parseDouble(lContent[1]), Double.parseDouble(lContent[2]),
							Double.parseDouble(lContent[3])));
				}
				// On récupère la liste des triangles (formant les différentes faces)
				else if (line.startsWith("f ")) {
					String[] lContent = line.split(" ");
					for (int i = 1; i < lContent.length; i++) {
						String[] vertex = lContent[i].split("/");
						this.forms.get(currentForm).add(this.fileVertices.get(Integer.parseInt(vertex[0]) - 1));
						this.fileFaces.add(this.fileVertices.get(Integer.parseInt(vertex[0]) - 1));
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

	}

	/**
	 * Récupérer les différentes formes du fichier *.obj avec leur sommets.
	 * 
	 * @return un dictionnaire ayant pour clé le nom de la forme et pour valeur ses
	 *         sommets.
	 */
	public Map<String, List<Vec3>> getForms() {
		return this.forms;
	}

}
