package serialization;

import java.io.*;
import java.util.ArrayList;

public class Ejemplo {
    private static final String ARCHIVO = "asignaturas.txt";
    private static ArrayList<Asignatura> asignaturas = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Cargar datos si existen (deserializar)
        cargarDatos();

        // Mostrar asignaturas actuales
        if (!asignaturas.isEmpty()) {
            System.out.println("\n📚 Asignaturas registradas:");
            for (Asignatura a : asignaturas) {
                System.out.println("-----------");
                a.verDatos();
            }
        } else {
            System.out.println("No hay asignaturas registradas.");
        }

        // Agregar nuevas asignaturas
        try {
            System.out.println("\n¿Deseas añadir nuevas asignaturas? (s/n)");
            String respuesta = br.readLine();

            while (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Código: ");
                String codigo = br.readLine();

                System.out.print("Nombre: ");
                String nombre = br.readLine();

                System.out.print("Créditos: ");
                int creditos = Integer.parseInt(br.readLine());

                System.out.print("Nombre del profesor: ");
                String nombreProf = br.readLine();

                System.out.print("Email del profesor: ");
                String emailProf = br.readLine();

                Profesor profe = new Profesor(nombreProf, emailProf);
                Asignatura nueva = new Asignatura(codigo, nombre, creditos, profe);
                asignaturas.add(nueva);

                System.out.println("Asignatura añadida.\n¿Deseas añadir otra? (s/n)");
                respuesta = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        // Guardar al salir (serialización)
        guardarDatos();
        System.out.println("✅ Datos guardados. ¡Hasta la próxima!");
    }

    // Cargar datos desde archivo
    private static void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            asignaturas = (ArrayList<Asignatura>) ois.readObject();
        } catch (FileNotFoundException e) {
            // El archivo no existe todavía
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    // Guardar datos al archivo
    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(asignaturas);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}
