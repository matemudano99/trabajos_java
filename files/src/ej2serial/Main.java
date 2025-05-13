package ej2serial;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<Contacto> contactos = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		try {

			int opcion;
			do {
				System.out.println("\n--- AGENDA DE CONTACTOS ---");
				System.out.println("1. Añadir contacto");
				System.out.println("2. Ver todos los contactos");
				System.out.println("3. Buscar contacto por nombre");
				System.out.println("4. Modificar contacto");
				System.out.println("5. Salir");
				System.out.print("Elige una opción: ");
				opcion = Integer.parseInt(br.readLine());

				switch (opcion) {
				case 1:
					agregarContacto();
					break;
				case 2:
					mostrarContactos();
					break;
				case 3:
					buscarContactos();
					break;
				case 4:
					modificarContactos();
					break;
				case 5:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción no válida.");
				}
			} while (opcion != 5);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modificarContactos() {
		try {
			System.out.println("Nombre del contacto a modificar: ");
			String nombre = br.readLine();
			for (Contacto contacto : contactos) {
				if(contacto.getNombre().equals(nombre)) {
					System.out.println("Nuevo nombre: ");
					nombre = br.readLine();
					contacto.setNombre(nombre);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void buscarContactos() {
		try {
			System.out.println("Nombre: ");
			String nombre = br.readLine();
			for (Contacto contacto : contactos) {
				if(contacto.getNombre().contains(nombre)) {
					System.out.println(contacto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarContactos() {
		System.out.println(contactos);
	}

	private static void agregarContacto() {
		try {
			System.out.println("Nombre: ");
			String nombre = br.readLine();
			System.out.println("Telefono: ");
			String telefono = br.readLine();
			System.out.println("Email: ");
			String email = br.readLine();
			contactos.add(new Contacto(nombre, telefono, email));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
