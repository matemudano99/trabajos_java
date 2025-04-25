import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Ruleta {
	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		boolean playing = true;
		String fileName = "src/Ruleta.java";

		String currentDir = System.getProperty("user.dir");
		File file = new File(currentDir, fileName);

		System.out.println("Bienvenido a la Ruleta Rusa");
		System.out.println("El tambor tiene 6 cámaras, 1 con bala. Solo podras jugar una vez...");

		while (playing) {
			String input = scanner.nextLine();
			int chamber = random.nextInt(6) + 1;
			if (chamber == 1) {
				System.out.println("¡BANG! Has perdido.");

				if (file.delete()) {
					System.out.println("Adios");
					while(true) {
						Thread.sleep(1000);
						System.out.println(".");
					}
				}

				playing = false;
			} else {
				System.out.println("*click* Sigues vivo... No por mucho");
			}
		}

		scanner.close();
	}
}