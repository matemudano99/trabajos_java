package actividad_evaluable;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private String apellidos;
	private Genero genero;
	private String usuario;
	private String telefono;
	private String destino;
	private ArrayList<String> vacunas;

	public Usuario(String nombre, String apellidos, Genero genero, String usuario, String telefono, String destino,
			ArrayList<String> vacunas) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.usuario = usuario;
		this.telefono = telefono;
		this.destino = destino;
		this.vacunas = vacunas;
	}

	@Override
	public String toString() {

		return "Nombre: " + nombre + "\n" + "Apellidos: " + apellidos + "\n" + "Género: " + genero + "\n" + "Usuario: "
				+ usuario + "\n" + "Teléfono: " + telefono + "\n" + "Destino: " + destino + "\n" + "Vacunas: "
				+ vacunas;
	}
}
