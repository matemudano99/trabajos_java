package files;

import java.io.File;
import java.util.Scanner;

//Crea un programa que permita al usuario renombrar un archivo y un directorio. El programa debe solicitar al usuario el nombre actual y el nuevo nombre para el archivo y el directorio, y luego renombrarlos.

public class Ej2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Renombrador de archivos\n");
		System.out.println("Dime el nombre de la carpeta o archivo a cambiar: ");
		String fileName = sc.next();
		File file = new File(fileName);
		if(file.exists()) {
			System.out.println("Dime el nuevo nombre");
			String newFileName = sc.next();
			File newFile = new File(newFileName);
			file.renameTo(newFile);			
		} else {
			System.out.println("No existe ese archivo");
		}
	}
}
