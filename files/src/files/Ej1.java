package files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Crea un programa que permita al usuario crear un archivo y un directorio. El programa debe solicitar al usuario el nombre del archivo y del directorio y luego crear ambos en el sistema de archivos.

public class Ej1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime el nombre del directorio");
		String nombreFolder = sc.next();
		System.out.println("Dime el nombre del archivo");
		String nombreFile = sc.next();

		File folder = new File(nombreFolder);
		File file = new File(nombreFolder+"/"+nombreFile);
		if (folder.mkdir()) {
			System.out.println("Se ha creado el directorio");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se ha podido crear el escritorio");
		}
	}
}