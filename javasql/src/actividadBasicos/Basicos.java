package actividadBasicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Basicos {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void esperar(int n) {
		long tiempo0, tiempo1;
		tiempo0 = System.currentTimeMillis();
		do {
			tiempo1 = System.currentTimeMillis();
		} while ((tiempo1 - tiempo0) < (n * 1000));
	}

	public static int leerEntero(String pregunta) {
		int numero = 0;
		boolean valido = false;

		while (!valido) {
			try {
				System.out.print(pregunta + " ");
				String linea = reader.readLine();
				numero = Integer.parseInt(linea);
				valido = true;
			} catch (NumberFormatException e) {
				System.out.println("Por favor, ingresa un número entero válido.");
			} catch (IOException e) {
				System.out.println("Error al leer la entrada. Intenta nuevamente.");
			}
		}
		return numero;
	}

	public static String leerCadena(String pregunta) {
		String linea = "Error";
		boolean valido = false;

		while (!valido) {
			try {
				System.out.print(pregunta + " ");
				linea = reader.readLine();
				valido = true;
			} catch (IOException e) {
				System.out.println("Error al leer la entrada. Intenta nuevamente.");
			}
		}
		return linea;
	}

	public static void insertarPersona() {
		String nombre = Basicos.leerCadena("Introduce el nombre:");
		String apellido1 = Basicos.leerCadena("Introduce el primer apellido:");
		String apellido2 = Basicos.leerCadena("Introduce el segundo apellido:");
		String correo = Basicos.leerCadena("Introduce el correo electrónico:");

		// En la base de datos el id es autoincremental por tanto no podemos pasarlo
		// deberíamos crear un constructor sin id para crear una Persona sin id
		// pero debemos mantener el que incorpora el id porque cuando recuperemos de la
		// base de datos lo necesitamos.
		Persona persona = new Persona(nombre, apellido1, apellido2, correo);

		// el conector es nuestro conector que hemos declarado al comienzo del main
		boolean registros = Main.db.insertarPersona(persona);

		if (registros) {
			System.out.println("Persona insertada correctamente.");
		} else {
			System.out.println("Error al insertar la persona.");
		}
	}

	public static void consultarTodos() {
		Main.db.consultarTodos();
	}

	public static void buscarPorCorreo() {
		String correo = Basicos.leerCadena("Introduce el correo electrónico:");

		boolean exito = Main.db.buscarPorCorreo(correo);
		if (!exito) {
			System.out.println("No se ha encontrado la persona");
		}
	}

	public static void modificarPorId() {
		String id = Basicos.leerCadena("Introduce el ID de la persona a modificar:");

		String nombre = Basicos.leerCadena("Nuevo nombre:");
		String apellido1 = Basicos.leerCadena("Nuevo primer apellido:");
		String apellido2 = Basicos.leerCadena("Nuevo segundo apellido:");
		String correo = Basicos.leerCadena("Nuevo correo:");

		Persona personaModificada = new Persona(nombre, apellido1, apellido2, correo);

		boolean exito = Main.db.modificarPorId(id, personaModificada);

		if (exito) {
			System.out.println("Persona modificada correctamente.");
		} else {
			System.out.println("No se ha podido modificar la persona (puede que no exista el ID).");
		}
	}

	public static void eliminarPorId() {
		int id = Basicos.leerEntero("Introduce el ID: ");
		int resultado = Main.db.eliminarPorId(id);
		if (resultado == 0) {
			System.out.println("No existe ninguna persona con ese id");
		} else if (resultado == 1) {
			System.out.println("Se ha eliminado exitosamente");
		}
	}
}
