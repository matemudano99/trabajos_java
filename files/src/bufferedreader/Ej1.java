package bufferedreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej1 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num1, num2, num3, numLineas = 0;
		String texto = "";
		while (true) {
			String linea = "";
			try {
			linea = br.readLine();
			} catch (IOException e) {
			e.printStackTrace();
			}
			if (linea.equalsIgnoreCase("fin")) {
				break;
			}
			numLineas++;
			texto += linea + " ";
		}
		String[] palabras = texto.split("\\s+");
		
		System.out.println("Lineas: " + numLineas);
		System.out.println("Palabras: " + palabras.length);
		System.out.println("Caracteres: " + texto.replaceAll("\\s", "").length());
	}
}
