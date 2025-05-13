package file_reader;

import java.io.*;

public class Ej1 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		int opt = 0;

		while (flag) {
			try {
				System.out.println("Gestor de personas\n1: Cargar datos  2: Leer datos  3: Salir");
				opt = Integer.parseInt(br.readLine());

				switch (opt) {
					case 1:
						String contenido = "";
						File archivo = new File("datos.dat");
						if (archivo.exists()) {
							BufferedReader reader = new BufferedReader(new FileReader(archivo));
							String linea;
							while ((linea = reader.readLine()) != null) {
								contenido = contenido + linea + "\n";
							}
							reader.close();
						}

						System.out.println("Registrar persona");
						System.out.print("DNI: ");
						String dni = br.readLine();
						System.out.print("Nombre: ");
						String nombre = br.readLine();
						System.out.print("Apellido: ");
						String apellido = br.readLine();
						System.out.print("Edad: ");
						int edad = Integer.parseInt(br.readLine());

						Persona p = new Persona(dni, nombre, apellido, edad);

						contenido = contenido + p.toString() + "\n";

						BufferedWriter writer = new BufferedWriter(new FileWriter("datos.dat"));
						writer.write(contenido);
						writer.close();
						break;

					case 2:
						System.out.println("Personas registradas:");
						BufferedReader lector = new BufferedReader(new FileReader("datos.dat"));
						String linea;
						while ((linea = lector.readLine()) != null) {
							System.out.println(linea);
						}
						lector.close();
						break;

					case 3:
						System.out.println("Saliendo...");
						flag = false;
						break;

					default:
						System.out.println("Opción no válida.");
				}
			} catch (IOException e) {
				System.out.println("Error");
			}
		}
	}
}
