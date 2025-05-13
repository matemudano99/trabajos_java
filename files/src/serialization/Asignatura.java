package serialization;

import java.io.Serializable;

public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private int creditos;
	private transient Profesor profesor;

	public Asignatura(String codigo, String nombre, int creditos, Profesor profesor) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.profesor = profesor;
	}

	public void verDatos() {
		System.out.println("Código: " + codigo);
		System.out.println("Nombre: " + nombre);
		System.out.println("Créditos: " + creditos);

		if (profesor != null) {
			profesor.mostrarInfo();
		} else {
			System.out.println("Profesor: (no disponible, no serializado)");
		}
	}

}
