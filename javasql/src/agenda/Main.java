package agenda;

public class Main {
	public static void main(String[] args) {
		ConectorBD bd = new ConectorBD();
		System.out.println(bd.contarRegistroPersona());
		bd.mostrarRegistrosPersona();
	}
}
