package actividadBasicos;

public class Persona {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String correo;

	public Persona(String nombre, String apellido1, String apellido2, String correo) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getCorreo() {
		return correo;

	}

}
