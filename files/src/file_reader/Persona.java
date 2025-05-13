package file_reader;

public class Persona {
	String dni;
	String nombre;
	String apellido;
	int edad;
	
	public Persona(String dni, String nombre, String apellido, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return dni + ", " + nombre + " " + apellido + ", " + edad;
	}
	
}
