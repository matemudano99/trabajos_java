package ej1serial;

import java.io.Serializable;

public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int contador=1;
	private int id;
	private String titulo;
	private String autor;
	private int año;
	private String genero;
	
	public Libro(String titulo, String autor, int año, String genero) {
		this.id = contador;
		contador++;
		this.titulo = titulo;
		this.autor = autor;
		this.año = año;
		this.genero = genero;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public static void setContador(int contador) {
		Libro.contador = contador;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", año=" + año + ", genero=" + genero
				+ "]";
	}
	
	
}
