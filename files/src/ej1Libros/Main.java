package ej1Libros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String contenido = "", linea = "";
		Scanner sc = new Scanner(System.in);
		File file = new File("libros.dat");

		System.out.println(" titulo: ");
		String titulo = sc.next();
		System.out.println(" autor: ");
		String autor = sc.next();
		System.out.println(" año: ");
		int año = sc.nextInt();
		System.out.println(" genero: ");
		String genero = sc.next();

		Libro libro = new Libro(titulo, autor, año, genero);

		try (BufferedReader rd = new BufferedReader(new FileReader(file))) {
			while ((linea = rd.readLine()) != null) {
				contenido += linea;
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
		} catch (IOException e1) {
			System.out.println("ERROR");
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(contenido);
			bw.newLine();
			bw.write(libro.toString());
		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}
}
