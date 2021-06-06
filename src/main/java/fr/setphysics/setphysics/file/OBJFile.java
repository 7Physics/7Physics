package fr.setphysics.setphysics.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import fr.setphysics.common.logger.Logger;

public class OBJFile {
	
	public static void main(String[] args) {
		readFile(OBJFile.class.getResource("/test.obj"));
	}
	
	public static void readFile(URL url) {
		File file = new File(url.getPath());
		FileReader fr;
		String line;
		BufferedReader buffer = null;
		StringBuffer sb = new StringBuffer();
		try {
			fr = new FileReader(file);
			buffer = new BufferedReader(fr);

			while((line = buffer.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			fr.close();
			System.out.println("Contenu du " + url + " :");
			System.out.println(sb.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.error("Impossible d'ouvrir le fichier " + url);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
