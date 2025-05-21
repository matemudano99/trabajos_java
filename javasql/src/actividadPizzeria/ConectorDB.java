package actividadPizzeria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorDB {
	private Connection conn = null;

	public ConectorDB() {
		String url = "jdbc:mysql://localhost:3306/pizzeria";
		String login = "root";
		String password = "";
		try {
			conn = DriverManager.getConnection(url, login, password);
			if (conn != null) {
				System.out.println("Conexion a 'pizzeria' lista");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int crearRegistrosPizza() {
		int resultado = 0;
		if (conn != null) {
			try {
				Statement statement = conn.createStatement();
				String consulta = " INSERT INTO Personal (nombre,apellido,id,sueldo,telefono,correo) VALUES ('Carlos', 'Ramírez', 101, 28000, '612345678', 'carlos.ramirez@example.com'),('Lucía', 'Fernández', 102, 32000, '613456789', 'lucia.fernandez@example.com'),('Miguel', 'Santos', 103, 30000, '614567890', 'miguel.santos@example.com'), ('Andrea', 'López', 104, 35000, '615678901', 'andrea.lopez@example.com'), ('Javier', 'Martínez', 105, 29000, '616789012', 'javier.martinez@example.com');  ";
				resultado = statement.executeUpdate(consulta);
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public int obtenerMayorSueldo() {
		int resultado = 0;
		if (conn != null) {
			try {
				Statement statement = conn.createStatement();
				ResultSet resultset = statement.executeQuery("SELECT max(sueldo) from personal");
				if (resultset.next()) {
					resultado = resultset.getInt(1);
				}
				statement.close();
				resultset.close();

			} catch (SQLException e) {
				System.out.println("Error");
			}
		}
		return resultado;
	}

	public void mostrarEmpleados() {
		if (conn != null) {
			try {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT nombre,telefono from personal");
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					int telefono = rs.getInt("telefono");
					System.out.println("Nombre: " + nombre);
					System.out.println("Telefono: " + telefono);
				}
				statement.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error");
			}
		}
	}

}
