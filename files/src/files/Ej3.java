package files;

import java.io.File;
import java.util.Scanner;

//Crea un programa que permita al usuario eliminar un archivo y un directorio.
//El programa debe solicitar al usuario el nombre del archivo y del directorio a eliminar, y luego eliminarlos.

import java.io.File;
import java.util.Scanner;

public class Ej3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Eliminar directorio");
            System.out.println("2. Eliminar archivo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    eliminarDirectorio(sc);
                    break;
                case 2:
                    eliminarArchivo(sc);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void eliminarDirectorio(Scanner sc) {
        System.out.println("\nEliminar directorio");
        System.out.print("Dime el directorio a eliminar: ");
        String folderName = sc.next();
        File folder = new File(folderName);

        if (folder.exists() && folder.isDirectory()) {
            if (folder.delete()) {
                System.out.println("Directorio eliminado");
            } else {
                System.out.println("El directorio no se puede eliminar, puede que contenga archivos dentro.");
            }
        } else {
            System.out.println("El directorio no existe.");
        }
    }

    public static void eliminarArchivo(Scanner sc) {
        System.out.println("\nEliminar archivo");
        System.out.print("Dime el archivo a eliminar: ");
        String fileName = sc.next();
        File file = new File(fileName);

        if (file.delete()) {
            System.out.println("Archivo eliminado");
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}