package actividadBasicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static ConectorDB db = new ConectorDB();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ DE AGENDA ---");
            System.out.println("1. Añadir nuevo contacto");
            System.out.println("2. Consultar todos los contactos");
            System.out.println("3. Buscar contacto por correo");
            System.out.println("4. Modificar contacto por ID");
            System.out.println("5. Eliminar contacto por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(reader.readLine());

                switch (opcion) {
                    case 1:
                        Basicos.insertarPersona();
                        break;
                    case 2:
                         Basicos.consultarTodos();
                        break;
                    case 3:
                         Basicos.buscarPorCorreo();
                        break;
                    case 4:
                         Basicos.modificarPorId();
                        break;
                    case 5:
                         Basicos.eliminarPorId();
                        break;
                    case 0:
                        db.desconectar();
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }

            } catch (IOException e) {
                System.out.println("Error al leer la entrada: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Debe introducir un número válido.");
            }

        } while (opcion != 0);
    }
}
