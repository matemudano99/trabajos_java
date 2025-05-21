package actividadPizzeria;

public class Main {
	public static void main(String[] args) {
		ConectorDB db = new ConectorDB();
		System.out.println(db.obtenerMayorSueldo());
		db.mostrarEmpleados();
	}
}
