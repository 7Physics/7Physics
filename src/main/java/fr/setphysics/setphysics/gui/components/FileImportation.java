package fr.setphysics.setphysics.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.Shape;
import fr.setphysics.common.geom.Vec3;
import fr.setphysics.engine.PhysicObject;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Object3D;
import fr.setphysics.renderer.Scene3D;
import fr.setphysics.setphysics.file.OBJFile;
import fr.setphysics.setphysics.gui.GUI;

public class FileImportation implements ActionListener{
	private Scene3D scene;
	private World world;
	
	public FileImportation(Scene3D scene, World world) {
		this.scene = scene;
		this.world = world;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            OBJFile objFile = new OBJFile();
            objFile.readFile(selectedFile);
            Map<String,List<Vec3>> map = objFile.getShapes();
            
            for(String sh : map.keySet()) {
            	Position pos = getPosition(objFile.getVerticesByShape().get(sh));
            	Shape s = new Shape(map.get(sh));
            	PhysicObject po = new PhysicObject(s, pos);
            	Object3D obj = new Object3D(s,pos);
            	scene.addObject(obj);
            	world.addPhysicObject(po);
            	ObjectPanel objectPanel = new ObjectPanel(scene, obj, po);

                GUI.getInstance().getObjectListPanel().addObjectPanel(objectPanel);
            }
        }
	}
	
	public Position getPosition(List<Vec3> vertices) {
		int nbVertices = vertices.size();
		Vec3 vec = new Vec3(0,0,0);
		vertices.forEach(v -> vec.add(v));
		return new Position(vec.getX()/nbVertices,vec.getY()/nbVertices,vec.getZ()/nbVertices);
	}

}
