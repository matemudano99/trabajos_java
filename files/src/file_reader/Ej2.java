package file_reader;
import java.io.*;

public class Ej2 {

    public static boolean esPrimo(int numero) {
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public static void escribirPrimos(String nombreArchivo) {
        try {
            FileWriter writer = new FileWriter(nombreArchivo);
            for (int i = 1; i <= 500; i++) {
                if (esPrimo(i)) {
                    writer.write(i + "\n");
                }
            }
            writer.close();
            System.out.println("Números primos escritos en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void leerPrimos(String nombreArchivo) {
        try {
            FileReader reader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;
            System.out.println("Números primos leídos del archivo:");
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.print(linea + " ");
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String archivo = "primos.dat";
        escribirPrimos(archivo);
        leerPrimos(archivo);
    }
}
