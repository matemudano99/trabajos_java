package ej1Libros;

public class Libro {
	private String titulo;
	private String autor;
	private int año;
	private String genero;

	public Libro(String titulo, String autor, int año, String genero) {
		this.titulo = titulo;
		this.autor = autor;
		this.año = año;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", año=" + año + ", genero=" + genero + "]";
	}

	// Método para mostrar la información del libro
	public void mostrarInformacion() {
		System.out.println("Título: " + titulo);
		System.out.println("Autor: " + autor);
		System.out.println("Año: " + año);
		System.out.println("Género: " + genero);
	}
}
