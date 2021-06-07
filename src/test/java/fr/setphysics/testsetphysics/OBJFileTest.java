package fr.setphysics.testsetphysics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.setphysics.common.geom.Vec3;
import fr.setphysics.setphysics.file.OBJFile;

public class OBJFileTest {
	static OBJFile objFile;
	static File f1;
	static File f2;
	static File f3;
	static Map<String, List<Vec3>> results;
	
	@BeforeAll
	public static void setUp() {
		f1 = new File(OBJFileTest.class.getResource("/test.obj").getPath());
		f2 = new File(OBJFileTest.class.getResource("/test2.obj").getPath());
		f3 = new File(OBJFileTest.class.getResource("/test3.obj").getPath());
	}
	
	@BeforeEach
	public void setOBJobject() {
		objFile = new OBJFile();
	}
	
	@Test
	public void testSmallFile() {
		objFile.readFile(f1);
		results = objFile.getForms();
		assertEquals(1, results.keySet().size(), "Le fichier contient 1 forme : un cube");
		assertEquals(36, results.get("Cube").size());
	}
	
	@Test
	public void testMediumFile() {
		objFile.readFile(f2);
		results = objFile.getForms();
		assertEquals(3, results.keySet().size(), "Le fichier contient 3 formes : un cube, pyramide et icosphere");
		assertEquals(36, results.get("Cube.004").size());
		assertEquals(12, results.get("Solid.002").size());
		assertEquals(240, results.get("Icosphere.002").size());
	}
	
	@Test
	public void testBigFile() {
		objFile.readFile(f3);
		results = objFile.getForms();
		assertEquals(5, results.keySet().size(), "Le fichier contient 5 formes : un cylindre, pyramide, icosphere, bouée et suzanne");
		assertEquals(3456, results.get("Torus").size());
		assertEquals(372, results.get("Cylinder").size());
		assertEquals(2904, results.get("Suzanne").size());
		assertEquals(12, results.get("Solid.002").size());
		assertEquals(240, results.get("Icosphere.002").size());
	}

}
