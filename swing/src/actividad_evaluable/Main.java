package actividad_evaluable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

	static ArrayList<Usuario> usuarios = new ArrayList<>();

	public static void main(String[] args) {
		// Ventana principal
		JFrame frame = new JFrame("Formulario de Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Checkbox para cambiar entre modo claro y oscuro
		JCheckBox modoOscuroCheck = new JCheckBox("🌓");
		panel.add(modoOscuroCheck);

		// Colores que se usan segun el modo
		Color[] fondo = { Color.WHITE };
		Color[] letra = { Color.BLUE };

		// Creacion de los componentes del formulario
		JTextField nombreField = new JTextField(15);
		JLabel nombreLabel = new JLabel("Nombre:");

		JTextField apellidosField = new JTextField(15);
		JLabel apellidosLabel = new JLabel("Apellidos (Dos):");

		JComboBox<Genero> generoBox = new JComboBox<>(Genero.values()); // ComboBox para hacer una lista desplegable
		JLabel generoLabel = new JLabel("Genero:");

		JTextField userField = new JTextField(15);
		JLabel userLabel = new JLabel("Usuario:");

		JPasswordField passField = new JPasswordField(15);
		JLabel passLabel = new JLabel("Contrasena:");

		JTextField telefonoField = new JTextField(10);
		JLabel telefonoLabel = new JLabel("Telefono:");

		// Lista de destinos disponibles
		String[] destinos = { "Elija su destino", "España", "Italia", "Francia", "Alemania", "Portugal", "Japon",
				"Brasil", "Canada", "Australia", "Egipto" };
		JComboBox<String> destinoBox = new JComboBox<>(destinos);
		JLabel destinoLabel = new JLabel("Destino:");

		// Checkboxes de vacunas
		JCheckBox vac1 = new JCheckBox("COVID-19");
		JCheckBox vac2 = new JCheckBox("Fiebre Amarilla");
		JCheckBox vac3 = new JCheckBox("Tetanos");
		JCheckBox vac4 = new JCheckBox("Hepatitis B");
		JCheckBox[] vacunas = { vac1, vac2, vac3, vac4 };
		JLabel vacunasLabel = new JLabel("Vacunas (Minimo 3):");

		// Botones de accion
		JButton enviarBtn = new JButton("Enviar Datos");
		JButton borrarBtn = new JButton("Borrar Datos");

		// Agrupar etiquetas y componentes para agregarlos al panel
		JLabel[] etiquetas = { nombreLabel, apellidosLabel, generoLabel, userLabel, passLabel, telefonoLabel,
				destinoLabel, vacunasLabel };
		JTextField[] campos = { nombreField, apellidosField, userField, telefonoField };
		JComponent[] componentes = { nombreField, apellidosField, generoBox, userField, passField, telefonoField,
				destinoBox };

		// Agregar campos al panel
		for (int i = 0; i < etiquetas.length; i++) {
			panel.add(etiquetas[i]);
			if (i < componentes.length) {
				panel.add(componentes[i]);
			}
		}
		panel.add(vacunasLabel);
		for (JCheckBox vac : vacunas) {
			panel.add(vac);
		}
		panel.add(enviarBtn);
		panel.add(borrarBtn);

		frame.add(panel);
		frame.setVisible(true);

		// Cambia los colores cuando se activa/desactiva el modo oscuro
		modoOscuroCheck.addActionListener(e -> {
			if (modoOscuroCheck.isSelected()) {
				fondo[0] = Color.DARK_GRAY;
				letra[0] = Color.WHITE;
			} else {
				fondo[0] = Color.WHITE;
				letra[0] = Color.BLUE;
			}

			panel.setBackground(fondo[0]);

			for (JLabel l : etiquetas) {
				l.setForeground(letra[0]);
			}
			for (JComponent c : componentes) {
				c.setBackground(fondo[0]);
				c.setForeground(letra[0]);
			}
			for (JCheckBox vac : vacunas) {
				vac.setBackground(fondo[0]);
				vac.setForeground(letra[0]);
			}
		});

		// Aplica los colores al iniciar
		if (modoOscuroCheck.isSelected()) {
			fondo[0] = Color.DARK_GRAY;
			letra[0] = Color.WHITE;
		} else {
			fondo[0] = Color.WHITE;
			letra[0] = Color.BLUE;
		}
		panel.setBackground(fondo[0]);
		for (JLabel l : etiquetas) {
			l.setForeground(letra[0]);
		}
		for (JComponent c : componentes) {
			c.setBackground(fondo[0]);
			c.setForeground(letra[0]);
		}
		for (JCheckBox vac : vacunas) {
			vac.setBackground(fondo[0]);
			vac.setForeground(letra[0]);
		}

		// Accion al hacer clic en el boton enviar
		enviarBtn.addActionListener(e -> {
			String nombre = nombreField.getText().trim();
			String apellidos = apellidosField.getText().trim();
			String usuario = userField.getText().trim();
			String telefono = telefonoField.getText().trim();
			String destino = (String) destinoBox.getSelectedItem();
			char[] password = passField.getPassword();

			// Contar vacunas seleccionadas
			int vacunasSeleccionadas = 0;
			ArrayList<String> vacunasList = new ArrayList<>();
			for (JCheckBox vac : vacunas) {
				if (vac.isSelected()) {
					vacunasSeleccionadas++;
					vacunasList.add(vac.getText());
				}
			}

			// Validaciones
			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "El nombre no puede estar vacio.");
				return;
			}
			if (!apellidos.matches("\\w+\\s\\w+")) {
				JOptionPane.showMessageDialog(frame, "Introduce dos apellidos separados por espacio.");
				return;
			}
			if (usuario.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "El usuario no puede estar vacio.");
				return;
			}
			if (password.length == 0) {
				JOptionPane.showMessageDialog(frame, "La contrasena no puede estar vacia.");
				return;
			}
			if (!telefono.matches("\\d+")) {
				JOptionPane.showMessageDialog(frame, "El telefono solo debe contener numeros.");
				return;
			}
			if (destino.equals("Elija su destino")) {
				JOptionPane.showMessageDialog(frame, "Debe elegir un destino valido.");
				return;
			}
			if (vacunasSeleccionadas < 3) {
				JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos 3 vacunas.");
				return;
			}

			// Crear nuevo usuario y mostrar resumen
			Usuario nuevoUsuario = new Usuario(nombre, apellidos, (Genero) generoBox.getSelectedItem(), usuario,
					telefono, destino, vacunasList);
			usuarios.add(nuevoUsuario);

			frame.setVisible(false);

			System.out.println(nuevoUsuario);
		});

		// Accion para limpiar todos los campos del formulario
		borrarBtn.addActionListener(e -> {
			for (JTextField campo : campos) {
				campo.setText("");
				passField.setText("");
				destinoBox.setSelectedIndex(0);
				generoBox.setSelectedIndex(0);
			}
			for (JCheckBox vac : vacunas) {
				vac.setSelected(false);
			}
		});

	}
}
