package ej1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {
	// esta clase tendrá un solo parámetro de tipo Connection que se inicializará a
	// null.
	private Connection conn = null;

	// constructor de la clase donde le daremos los datos al objeto connection
	public ConectorBD() {
		try {
			// Cargar el driver JDBC para MySQL desde JDBC 6 no es necesario escribir este
			// bloque de código
			// pero al hacerlo podemos incluir información en el caso de fallo.
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no instalado correctamente");
		}

		// Datos de conexión a la base de datos,
		// El puerto, el nombre de la base de datos "tema9", el login y el password
		// pueden cambiar
		// dependiendo de tu configuración
		String url = "jdbc:mysql://localhost:3306/tema9";
		String login = "root";
		String password = "";

		try {
			// Establecer la conexión
			conn = DriverManager.getConnection(url, login, password);
			if (conn != null) {
				System.out.println("Conexión a base de datos tema9 lista.");
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void desconectar() {
		conn=null;
	}
}