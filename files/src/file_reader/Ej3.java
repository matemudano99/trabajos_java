package file_reader;

import java.io.*;

public class Ej3 {
	public static void main(String[] args) {
		
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("uno.txt"))){

				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						bw.write("1");
					}
					bw.newLine();
				}
				bw.close();

				BufferedWriter bw2 = new BufferedWriter(new FileWriter("dos.txt"));
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						bw2.write("2");
					}
					bw2.newLine();
				}
				bw2.close();

				BufferedReader br1 = new BufferedReader(new FileReader("uno.txt"));
				BufferedReader br2 = new BufferedReader(new FileReader("dos.txt"));
				BufferedWriter bw3 = new BufferedWriter(new FileWriter("mezcla.txt"));

				String linea1, linea2;
				while ((linea1 = br1.readLine()) != null && (linea2 = br2.readLine()) != null) {
					bw3.write(linea1);
					bw3.newLine();
					bw3.write(linea2);
					bw3.newLine();
				}

			}catch(IOException e) {
				System.err.println("Error");
			};
		}
	}

