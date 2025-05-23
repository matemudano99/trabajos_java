package actividadBasicos;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectorDB {
	// unico parametro
	private Connection conn = null;

	// constructor
	public ConectorDB() {
		String url = "jdbc:mysql://localhost:3306/agenda";
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

	public boolean insertarPersona(Persona p) {
		boolean exito = false;

		try {
			Statement orden = conn.createStatement();
			String sql = "INSERT INTO Persona (nombre, apellido1, apellido2, correo) VALUES (" + "'" + p.getNombre()
					+ "', " + "'" + p.getApellido1() + "', " + "'" + p.getApellido2() + "', " + "'" + p.getCorreo()
					+ "')";
			int filas = orden.executeUpdate(sql);
			exito = filas > 0;
			orden.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar persona: " + e.getMessage());
		}

		return exito;
	}

	public void consultarTodos() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from persona";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Personas: ");
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido1 = rs.getString("apellido1");
				String apellido2 = rs.getString("apellido2");
				String correo = rs.getString("correo");
				System.out.println(nombre + " - " + apellido1 + " - " + apellido2 + " - " + correo);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean buscarPorCorreo(String correo) {
		boolean exito = false;
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from persona where correo = '" + correo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido1 = rs.getString("apellido1");
				String apellido2 = rs.getString("apellido2");
				System.out.println(nombre + " - " + apellido1 + " - " + apellido2 + " - " + correo);
				exito = true;
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exito;
	}

	public boolean modificarPorId(String id, Persona persona) {
		boolean exito = false;
		try {
			Statement stmt = conn.createStatement();

			String sql = "UPDATE persona SET " + "nombre = '" + persona.getNombre() + "', " + "apellido1 = '"
					+ persona.getApellido1() + "', " + "apellido2 = '" + persona.getApellido2() + "', " + "correo = '"
					+ persona.getCorreo() + "' " + "WHERE id = '" + id + "'";

			int filasAfectadas = stmt.executeUpdate(sql);
			exito = filasAfectadas > 0;

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exito;
	}

	public int eliminarPorId(int id) {
		int exito = -1;

		try {
			Statement stmt = conn.createStatement();
			exito = stmt.executeUpdate("delete from persona where id='" + id + "'");

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}

	// get para devolver la conexión
	public Connection getConn() {
		return conn;
	}

	// método para cerrar la conexión
	public void desconectar() {
		conn = null;
	}
}