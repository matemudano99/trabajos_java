package serialization;

public class Profesor {
	String nombre;
	String email;
	
	public Profesor(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public void mostrarInfo() {
		System.out.println("profesor!");
	}
}
