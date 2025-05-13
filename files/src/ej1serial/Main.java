package ej1serial;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Main {
	static ArrayList<Libro> libros = new ArrayList<>();
	private static final String ARCHIVO = "libros.obj";

	public static void main(String[] args) {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		cargarDatos();
		
		Libro maxLibro = null;
		if(!libros.isEmpty()) {
			System.out.println("\nLibros registrados: \n");
			for (Libro libro: libros) {
			  	if(maxLibro==null||libro.getId()>maxLibro.getId()) {
					maxLibro=libro;
				}
				System.out.println(libro);
				System.out.println("---------");
			}
			Libro.setContador(maxLibro.getId()+1);
		} else {
			System.out.println("No hay libros registrados.");
		}
		
		try {
			System.out.println("\nTitulo: ");
			String titulo = sc.readLine();
			System.out.println("Autor: ");
			String autor = sc.readLine();
			System.out.println("Año: ");
			int año = Integer.parseInt(sc.readLine());
			System.out.println("Genero:");
			String genero = sc.readLine();
			libros.add(new Libro(titulo, autor, año, genero));

			guardarDatos();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarDatos() {
		try {
			FileInputStream fis = new FileInputStream(ARCHIVO);
			ObjectInputStream ois = new ObjectInputStream(fis);
			libros = (ArrayList<Libro>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al cargar datos: " + e.getMessage());
		}
	}
	
	private static void guardarDatos() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(ARCHIVO);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(libros);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        } 
    }

}
