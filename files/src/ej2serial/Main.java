package ej2serial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
	static ArrayList<Contacto> contactos = new ArrayList<>();
	static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	static File file = new File("contactos.dat");

	public static void main(String[] args) {
		try {
			cargarDatos();

			int opcion;
			do {
				System.out.println("\n--- AGENDA DE CONTACTOS ---");
				System.out.println("1. Añadir contacto");
				System.out.println("2. Ver todos los contactos");
				System.out.println("3. Buscar contacto por nombre");
				System.out.println("4. Modificar contacto");
				System.out.println("5. Salir");
				System.out.print("Elige una opción: ");
				opcion = Integer.parseInt(sc.readLine());

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

				guardarDatos();

			} while (opcion != 5);

			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarDatos() {
		try {
			if (file.exists() && file.length() > 0) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				contactos = (ArrayList<Contacto>) ois.readObject();
				ois.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al cargar datos");
		}
	}

	private static void guardarDatos() {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(contactos);
			oos.close();
		} catch (IOException e) {
			System.out.println("Error al guardar");
		}
	}

	private static void modificarContactos() {
		try {
			System.out.println("Nombre del contacto a modificar: ");
			String nombre = sc.readLine();
			for (Contacto contacto : contactos) {
				if (contacto.getNombre().equals(nombre)) {
					System.out.println("Nuevo nombre: ");
					String nuevoNombre = sc.readLine();
					System.out.println("Nuevo teléfono: ");
					String nuevoTelefono = sc.readLine();
					System.out.println("Nuevo email: ");
					String nuevoEmail = sc.readLine();

					contacto.setNombre(nuevoNombre);
					contacto.setTelefono(nuevoTelefono);
					contacto.setEmail(nuevoEmail);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void buscarContactos() {
		try {
			System.out.println("Nombre: ");
			String nombre = sc.readLine();
			for (Contacto contacto : contactos) {
				if (contacto.getNombre().contains(nombre)) {
					System.out.println(contacto);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarContactos() {
		if (contactos.isEmpty()) {
			System.out.println("No hay contactos.");
		} else {
			for (Contacto c : contactos) {
				System.out.println(c);
			}
		}
	}

	private static void agregarContacto() {
		try {
			System.out.println("Nombre: ");
			String nombre = sc.readLine();
			System.out.println("Telefono: ");
			String telefono = sc.readLine();
			System.out.println("Email: ");
			String email = sc.readLine();
			contactos.add(new Contacto(nombre, telefono, email));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
