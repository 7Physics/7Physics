package fr.setphysics.setphysics.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.Shape;
import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.logger.Logger;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.file.OBJFile;
import fr.setphysics.setphysics.gui.GUI;

/**
 * 
 * Classe représentant le comportement effectué lors de l'imporation d'un
 * fichier *.obj.
 *
 */
public class FileImportation implements ActionListener {
	private Scene3D scene;
	private World world;

	public FileImportation(Scene3D scene, World world) {
		this.scene = scene;
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Propose à l'utilisateur de sélectionner le fichier à importer dans le projet
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Importer un projet");

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			//Récupération du fichier
			File selectedFile = fileChooser.getSelectedFile();
			Logger.info(selectedFile.getAbsolutePath());
			
			//Traitement du fichier *.obj reçu
			OBJFile objFile = new OBJFile();
			objFile.readFile(selectedFile);
			Map<String, List<Vec3>> map = objFile.getShapes();

			//Création d'un objet 3D pour chaque forme présente dans le fichier
			for (String sh : map.keySet()) {
				Position pos = getPosition(map.get(sh));
				Shape s = new Shape(map.get(sh));
				PhysicObject po = new PhysicObject(s, pos);
				Object3D obj = new Object3D(s, pos);
				scene.addObject(obj);
				world.addPhysicObject(po);
				ObjectPanel objectPanel = new ObjectPanel(scene, obj, po, sh);

				GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
			}
		}
	}

	/**
	 * Trouve la position du centre de la forme et replace tous les sommets
	 * par rapport à celui-ci.
	 * @param vertices : les sommets de la forme
	 * @return la position du centre de la forme
	 */
	public Position getPosition(List<Vec3> vertices) {
		int nbVertices = vertices.size();
		Vec3 vec = new Vec3(0, 0, 0);
		vertices.forEach(v -> vec.add(v));
		Position center = new Position(vec.getX() / nbVertices, vec.getY() / nbVertices, vec.getZ() / nbVertices);

		for (Vec3 v : vertices) {
			v.addX(-center.getX());
			v.addY(-center.getY());
			v.addZ(-center.getZ());
		}
		return center;
	}

}
