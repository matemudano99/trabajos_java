package datos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.regex.Pattern;

public class MiHospital {

	public static void main(String[] args) {
		mostrarLogin(); // Punto de entrada: se muestra la ventana de login
	}

	// Método que construye la interfaz de login
	public static void mostrarLogin() {
		JFrame frame = new JFrame("Hospital Carlos Haya - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Colores y fuentes personalizadas
		Color fondo = new Color(245, 245, 245);
		Color btnColor = new Color(66, 135, 245);
		Color txtBtn = Color.WHITE;
		Font fuente = new Font("Arial", Font.PLAIN, 14);
		Font tituloFont = new Font("Arial", Font.BOLD, 18);

		// Título superior
		JLabel titulo = new JLabel("Bienvenido al Hospital Carlos Haya", JLabel.CENTER);
		titulo.setFont(tituloFont);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		frame.add(titulo, BorderLayout.NORTH);

		// Panel con campos de entrada
		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panel.setBackground(fondo);

		// Etiquetas y campos
		JLabel usuarioLabel = new JLabel("Usuario ID:");
		JTextField usuarioField = new JTextField();
		JLabel passwordLabel = new JLabel("Contraseña:");
		JPasswordField passwordField = new JPasswordField();
		JLabel rolLabel = new JLabel("Tipo de usuario:");
		String[] roles = { "Administrador", "Administrativo", "Médico", "Enfermero", "Mantenimiento" };
		JComboBox<String> rolBox = new JComboBox<>(roles);

		// Aplicar estilo a los componentes
		aplicarEstilo(fuente,
				new Component[] { usuarioLabel, usuarioField, passwordLabel, passwordField, rolLabel, rolBox });

		// Añadir componentes al panel
		panel.add(usuarioLabel);
		panel.add(usuarioField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(rolLabel);
		panel.add(rolBox);
		panel.add(new JLabel()); // espacio vacío
		panel.add(new JLabel()); // espacio vacío

		frame.add(panel, BorderLayout.CENTER);

		// Botón de login
		JButton loginBtn = new JButton("Entrar");
		loginBtn.setFont(fuente);
		loginBtn.setBackground(btnColor);
		loginBtn.setForeground(txtBtn);

		JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		botonPanel.setBackground(fondo);
		botonPanel.add(loginBtn);
		frame.add(botonPanel, BorderLayout.SOUTH);

		// Acción al hacer clic en "Entrar"
		loginBtn.addActionListener(e -> {
			String usuario = usuarioField.getText();
			String password = new String(passwordField.getPassword());
			String rolSeleccionado = (String) rolBox.getSelectedItem();

			if (usuario.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Debe ingresar usuario y contraseña.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			frame.dispose(); // Cierra la ventana de login
			menuPrincipal(rolSeleccionado); // Abre el menú principal
			JOptionPane.showMessageDialog(null, "Acceso realizado con éxito.\nUsuario: " + usuario);
		});

		frame.getContentPane().setBackground(fondo);
		frame.setVisible(true); // Mostrar ventana
	}

	// Aplica una fuente a varios componentes
	private static void aplicarEstilo(Font fuente, Component[] comps) {
		for (int i = 0; i < comps.length; i++) {
			comps[i].setFont(fuente);
		}
	}

	// Menú principal según el rol
	public static void menuPrincipal(String rol) {
		JFrame frame = new JFrame("Menú Principal - " + rol);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 350);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		// Personalización visual
		Color fondo = new Color(250, 250, 250);
		Color btnColor = new Color(52, 152, 219);
		Color txtBtn = Color.WHITE;
		Font btnFont = new Font("Circular", Font.BOLD, 14);
		Font titleFont = new Font("Circular", Font.BOLD, 20);

		// Etiqueta de bienvenida
		JLabel bienvenida = new JLabel("Bienvenido, " + rol, JLabel.CENTER);
		bienvenida.setFont(titleFont);
		bienvenida.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		frame.add(bienvenida, BorderLayout.NORTH);

		// Botones de navegación
		JPanel opciones = new JPanel(new GridLayout(2, 2, 20, 20));
		opciones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		opciones.setBackground(fondo);

		JButton btnPacientes = new JButton("Gestión de Pacientes");
		JButton btnEmpleados = new JButton("Gestión de Empleados");
		JButton btnSalas = new JButton("Gestión de Salas");
		JButton btnSalir = new JButton("Cerrar sesión");

		// Aplicar estilo a todos los botones
		for (JButton b : new JButton[] { btnPacientes, btnEmpleados, btnSalas, btnSalir }) {
			b.setFont(btnFont);
			b.setBackground(btnColor);
			b.setForeground(txtBtn);
			opciones.add(b);
		}

		// Acciones de los botones
		btnPacientes.addActionListener(e -> {
			frame.dispose();
			gestionPacientes(rol);
		});
		btnEmpleados.addActionListener(e -> {
			frame.dispose();
			gestionGenerica("Gestión de Empleados", new String[] { "ID", "Nombre", "Puesto", "Departamento" }, rol);
		});
		btnSalas.addActionListener(e -> {
			frame.dispose();
			gestionGenerica("Gestión de Salas", new String[] { "Número", "Tipo", "Capacidad", "Disponibilidad" }, rol);
		});
		btnSalir.addActionListener(e -> {
			frame.dispose();
			mostrarLogin(); // Volver al login
		});

		frame.add(opciones, BorderLayout.CENTER);
		frame.getContentPane().setBackground(fondo);
		frame.setVisible(true);
	}

	// Ventana genérica de gestión (empleados, salas, etc.)
	public static void gestionGenerica(String titulo, String[] columnas, String rol) {
		JFrame frame = new JFrame(titulo + " - " + rol);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		Color fondo = new Color(245, 245, 245);

		// Tabla vacía con columnas personalizadas
		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		JTable tabla = new JTable(model);
		JScrollPane scroll = new JScrollPane(tabla);

		// Botones inferiores
		JPanel panelBotones = new JPanel();
		JButton btnAgregar = new JButton("Agregar");
		JButton btnEliminar = new JButton("Eliminar");
		JButton btnVolver = new JButton("Volver");

		panelBotones.add(btnAgregar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnVolver);

		frame.add(scroll, BorderLayout.CENTER);
		frame.add(panelBotones, BorderLayout.SOUTH);

		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});

		btnAgregar.addActionListener(e -> {
			// Muestra una ventana para añadir nuevos datos
			JTextField[] campos = new JTextField[columnas.length];
			JPanel panel = new JPanel(new GridLayout(columnas.length, 2));
			for (int i = 0; i < columnas.length; i++) {
				panel.add(new JLabel(columnas[i] + ":"));
				campos[i] = new JTextField();
				panel.add(campos[i]);
			}
			int result = JOptionPane.showConfirmDialog(frame, panel, "Agregar nuevo", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				String[] datos = new String[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					datos[i] = campos[i].getText();
				}
				model.addRow(datos);
			}
		});

		btnEliminar.addActionListener(e -> {
			int fila = tabla.getSelectedRow();
			if (fila != -1)
				model.removeRow(fila); // Elimina la fila seleccionada
		});

		frame.getContentPane().setBackground(fondo);
		frame.setVisible(true);
	}

	// Interfaz para la gestión específica de pacientes
	public static void gestionPacientes(String rol) {
		JFrame frame = new JFrame("Gestión de Pacientes - " + rol);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

		Color fondo = new Color(245, 245, 245);
		Font tabFont = new Font("SansSerif", Font.PLAIN, 14);

		// --- PANEL SUPERIOR con botón Volver alineado a la derecha ---
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topPanel.setBackground(fondo);
		JButton btnVolver = new JButton("Volver al menú principal");
		btnVolver.setFont(tabFont);
		btnVolver.addActionListener(e -> {
			frame.dispose();
			menuPrincipal(rol);
		});
		topPanel.add(btnVolver);
		frame.add(topPanel, BorderLayout.NORTH);

		// --- CREAR JTabbedPane ---
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(tabFont);
		tabs.setBackground(fondo);

		// Pestaña Registro
		JPanel registroPanel = new JPanel(new GridBagLayout());
		registroPanel.setBackground(fondo);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		String[] labels = { "Nombre:", "Apellidos:", "DNI:", "Teléfono:", "Email:", "Obra Social:" };
		JTextField[] fields = new JTextField[labels.length];
		for (int i = 0; i < labels.length; i++) {
			JLabel lbl = new JLabel(labels[i]);
			JTextField txt = new JTextField();
			fields[i] = txt;
			c.gridx = 0;
			c.gridy = i;
			registroPanel.add(lbl, c);
			c.gridx = 1;
			registroPanel.add(txt, c);
		}
		JButton btnReg = new JButton("Registrar");
		btnReg.setEnabled(rol.equals("Administrador") || rol.equals("Administrativo"));
		c.gridx = 1;
		c.gridy = labels.length;
		registroPanel.add(btnReg, c);
		tabs.addTab("Registro", registroPanel);

		// Pestaña Historial
		String[] cols = { "Fecha", "Tipo", "Detalle" };
		DefaultTableModel model = new DefaultTableModel(cols, 0);
		JTable tablaHist = new JTable(model);
		JScrollPane scrollHist = new JScrollPane(tablaHist);
		tabs.addTab("Historial", scrollHist);

		// Pestaña Habitaciones (sin el botón Volver aquí)
		JPanel habPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		habPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		habPanel.setBackground(fondo);
		habPanel.add(new JLabel("Paciente (DNI):"));
		habPanel.add(new JTextField());
		habPanel.add(new JLabel("Habitación nº:"));
		habPanel.add(new JComboBox<>(new String[] { "101", "102", "103", "104" }));
		JButton btnAsig = new JButton("Asignar");
		btnAsig.setEnabled(rol.equals("Administrador") || rol.equals("Administrativo"));
		habPanel.add(new JLabel());
		habPanel.add(btnAsig);
		tabs.addTab("Habitaciones", habPanel);

		// Pestaña Solicitudes
		String[] cols2 = { "Solicitud", "Estado" };
		DefaultTableModel modSol = new DefaultTableModel(cols2, 0);
		JTable tablaSol = new JTable(modSol);
		JScrollPane scrollSol = new JScrollPane(tablaSol);
		tabs.addTab("Solicitudes", scrollSol);

		// Añadir pestañas al centro del frame
		frame.add(tabs, BorderLayout.CENTER);

		// Barra de estado
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		statusBar.setBackground(Color.LIGHT_GRAY);
		JLabel lblStatus = new JLabel("Listo");
		statusBar.add(lblStatus, BorderLayout.WEST);
		frame.add(statusBar, BorderLayout.SOUTH);

		// Fila de ejemplo en historial
		model.addRow(new Object[] { "01/01/2025", "Consulta", "Revisión general" });

		frame.setVisible(true);
	}

}
