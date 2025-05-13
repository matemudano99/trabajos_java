package file_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ej4 {
	public static void main(String[] args) {
		File file = new File("src/file_reader/Estudiante.java");
		String contenido = "", linea = "";

		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				while ((linea = br.readLine()) != null) {
					if (linea.contains("//")) {
						contenido += linea.substring(0, linea.indexOf("//")) + "\n";
					} else {
						contenido += linea + "\n";
					}
				}

			} catch (IOException e) {
				System.out.println("Error");
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				bw.write(contenido);

			} catch (IOException e) {
				System.out.println("Error");

			}

		}
	}
}
