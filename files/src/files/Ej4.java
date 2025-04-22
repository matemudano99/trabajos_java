package files;

import java.io.File;
import java.util.Scanner;

//Crea un programa que pida al usuario el nombre de la carpeta padre y el nombre de la carpeta hija, con el método mkdirs() crea las dos carpetas en una sola sola sentencia. 

public class Ej4 {
	public static void main(String[] args) {
		
		while (true) {
			
		Scanner sc = new Scanner(System.in);
		System.out.println("Creador multiple de carpetas\n");
		
		System.out.println("Dime el nombre de la carpeta padre a crear: ");
		String padreName = sc.next();
		
		System.out.println("Dime el nombre de la carpeta hija: ");
		String hijoName = sc.next();
		
		File hijo = new File(padreName + "/" +hijoName);
		
		if(hijo.mkdirs()) {
			System.out.println("Carpetas creadas exitosamente");
		} else {
			System.out.println("No se han podido crear las carpetas");
		}
		
		}
	}
}
