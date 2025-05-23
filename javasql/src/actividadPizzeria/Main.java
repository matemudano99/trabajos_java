package actividadPizzeria;

public class Main {
	public static void main(String[] args) {
		ConectorDB db = new ConectorDB();
		System.out.println("Mayor sueldo: "+db.obtenerMayorSueldo());
		db.mostrarEmpleados();
	}
}
