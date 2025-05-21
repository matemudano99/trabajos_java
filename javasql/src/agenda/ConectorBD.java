package agenda;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectorBD {
	// unico parametro
	private Connection conn = null;

	// constructor
	public ConectorBD() {
		String url = "jdbc:mysql://localhost:3306/Agenda";
		String login = "root";
		String password = "";

		try {
			conn = DriverManager.getConnection(url, login, password);
			if (conn != null) {
				System.out.println("Conexión a base de datos biblioteca lista.");
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
		}
	}

	// get para devolver la conexión
	public Connection getConn() {
		return conn;
	}

	// método para cerrar la conexión
	public void desconectar() {
		conn = null;
	}

	// si tenemos un método para desconectar lo normal será tener otro para volver a
	// conectar.
	public void reconectar() {
		if (conn == null) {
			String url = "jdbc:mysql://localhost:3306/Agenda";
			String login = "root";
			String password = "";
			try {
				conn = DriverManager.getConnection(url, login, password);
				System.out.println("Reconexión exitosa.");
			} catch (SQLException e) {
				System.out.println("Error al reconectar: " + e.getMessage());
			}
		}
	}

	public int crearTablaPersona() {
		int resultado = -1;
		if (conn != null) {
			try {
				Statement orden = conn.createStatement();
				resultado = orden.executeUpdate("""
						CREATE TABLE IF NOT EXISTS Persona(
						id INT AUTO_INCREMENT PRIMARY KEY,
						nombre VARCHAR(30) NOT NULL,
						apellido1 VARCHAR(30) NOT NULL,
						apellido2 VARCHAR(30) NOT NULL,
						correo VARCHAR(30) NOT NULL
						);
						""");
			} catch (SQLException e) {
				System.out.println("Error");
			}
		}
		return resultado;
	}

	// clase ConectorBD
	public int contarRegistroPersona() {
		int resultado = 0;
		if (conn != null) {
			try {
				Statement orden = conn.createStatement();
				ResultSet res = orden.executeQuery("Select max(id) from persona;");
				if (res.next()) {
					resultado = res.getInt(1);
				}
				res.close();
				orden.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	// clase ConectorBD
	public int insertarRegistroMuestra() {
		int resultado = 0;
		if (conn != null) {
			try {
				Statement orden;
				orden = conn.createStatement();
				String consulta = "INSERT INTO persona (nombre, apellido1, apellido2, correo) VALUES "
						+ "('Ana', 'Gómez', 'López', 'ana.gomez@mail.com'), "
						+ "('Luis', 'Pérez', 'Martínez', 'luis.perez@mail.com'), "
						+ "('Carla', 'Sánchez', 'Ruiz', 'carla.sanchez@mail.com');";

				resultado = orden.executeUpdate(consulta);
				orden.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	// clase conectorBD
	public void mostrarRegistrosPersona() {
		if (conn != null) {
			try {
				Statement orden = conn.createStatement();
				ResultSet rs = orden.executeQuery("SELECT id, nombre, apellido1, apellido2, correo FROM persona");

				while (rs.next()) {
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String apellido1 = rs.getString("apellido1");
					String apellido2 = rs.getString("apellido2");
					String correo = rs.getString("correo");
					
					System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido 1: " + apellido1
							+ ", Apellido 2: " + apellido2 + ", Correo: " + correo);
				}
				rs.close();
				orden.close();
			} catch (SQLException e) {
				System.out.println("Error al consultar los registros: " + e.getMessage());
			}
		} else {
			System.out.println("La conexión está cerrada o no disponible.");
		}
	}
}