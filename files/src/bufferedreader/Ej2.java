package bufferedreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Ej2 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String linea;

        while (true) {
            System.out.println("Introduce una línea (o 'salir' para terminar):");
            linea = sc.readLine();
            if (linea == null || linea.trim().equalsIgnoreCase("salir")) {
                break;
            }

            Stack<Character> pila = new Stack<>();

            for (char c : linea.toCharArray()) {
                pila.push(c);
            }
            
            while (!pila.isEmpty()) {
                System.out.print(pila.pop());
            }
            System.out.println("\n");
        }
    }
}

