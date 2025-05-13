package file_reader;
//sajdasjdasj

public class Estudiante {
	String nombre;
	String apellido;
	String dni;
//belen ponme un 10 🙏

	public Estudiante(String nombre, String apellido, String dni) { //xasdasd
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
//Barca 4-3 Madrid
	public String getNombre() {
		return nombre;
	}
//ASdhash
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//xd 😀
	//fa
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}

	public boolean dniValido() {
		return dni != null && dni.matches("\\d{8}[A-Za-z]");
	}

	public boolean esMismoEstudiante(Estudiante otro) {
		if (otro == null)
			return false;
		return this.dni.equals(otro.getDni());
	}

	public String nombreEnMayusculas() {
		return getNombreCompleto().toUpperCase();
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + "]";
	}

}
