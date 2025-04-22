package files;

import java.io.File;
import java.util.Scanner;

//Crea un programa que permita al usuario eliminar un archivo y un directorio.
//El programa debe solicitar al usuario el nombre del archivo y del directorio a eliminar, y luego eliminarlos.
public class Ej3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Eliminar directorio\n");
		System.out.println("Dime el directorio a eliminar: ");
		String folderName = sc.next();
		File folder = new File(folderName);
		if(folder.exists()) {
			if(folder.delete()) {
				System.out.println("Directorio eliminado");				
			} else {
				System.out.println("El directorio no se puede eliminar, tiene archivos dentro");
			}
		} else {
			System.out.println("El directorio no existe");
		}
		System.out.println("Eliminar archivo\n");		
		System.out.println("Dime el archivo a eliminar: ");
		String fileName = sc.next();
		File file = new File(fileName);
		if(file.delete()) {
			System.out.println("Archivo eliminado");
		} else {
			System.out.println("El archivo no existe");
		}
	}
}
