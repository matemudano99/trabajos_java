package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;

public class VistaMain extends JFrame {
	private ArrayList<Personal> personalList = new ArrayList<>();
	private ArrayList<Paciente> pacientesList = new ArrayList<>();
	private ArrayList<Infraestructura> salasList = new ArrayList<>();

	private JTabbedPane tabbedPane;
	private JPanel adminPanel, medicoPanel, enfermeroPanel, administrativoPanel, mantenimientoPanel;

	public VistaMain() {
		setTitle("Hospital Carlos Haya - Sistema de Gestión");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		tabbedPane = new JTabbedPane();
		adminPanel = createAdminPanel();
		medicoPanel = createMedicoPanel();
		enfermeroPanel = createEnfermeroPanel();
		administrativoPanel = createAdministrativoPanel();
		mantenimientoPanel = createMantenimientoPanel();

		tabbedPane.addTab("Administrador", adminPanel);
		tabbedPane.addTab("Médico", medicoPanel);
		tabbedPane.addTab("Enfermero", enfermeroPanel);
		tabbedPane.addTab("Administrativo", administrativoPanel);
		tabbedPane.addTab("Mantenimiento", mantenimientoPanel);

		add(tabbedPane);
		setVisible(true);
	}

	private JPanel createAdminPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
		Administrador admin = new Administrador("12345678A", "Admin", "Principal", Genero.HOMBRE, Turnos.MAÑANA,
				new Infraestructura(TipoInfraestructura.CONSULTORIO, 1, true), "Jefe");

		// Registrar Personal
		JButton btnRegistrarPersonal = new JButton("Registrar Personal");
		btnRegistrarPersonal.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Registrar Personal", true);
			dialog.setLayout(new GridLayout(8, 2));
			dialog.setSize(400, 300);

			JTextField dniField = new JTextField();
			JTextField nombreField = new JTextField();
			JTextField apellidosField = new JTextField();
			JComboBox<Genero> generoCombo = new JComboBox<>(Genero.values());
			JComboBox<Turnos> turnoCombo = new JComboBox<>(Turnos.values());
			JComboBox<TipoInfraestructura> salaCombo = new JComboBox<>(TipoInfraestructura.values());
			JTextField numeroSalaField = new JTextField();
			JTextField rolField = new JTextField();

			dialog.add(new JLabel("DNI:"));
			dialog.add(dniField);
			dialog.add(new JLabel("Nombre:"));
			dialog.add(nombreField);
			dialog.add(new JLabel("Apellidos:"));
			dialog.add(apellidosField);
			dialog.add(new JLabel("Género:"));
			dialog.add(generoCombo);
			dialog.add(new JLabel("Turno:"));
			dialog.add(turnoCombo);
			dialog.add(new JLabel("Tipo Sala:"));
			dialog.add(salaCombo);
			dialog.add(new JLabel("Número Sala:"));
			dialog.add(numeroSalaField);
			dialog.add(new JLabel("Rol:"));
			dialog.add(rolField);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					Infraestructura sala = new Infraestructura((TipoInfraestructura) salaCombo.getSelectedItem(),
							Integer.parseInt(numeroSalaField.getText()), true);
					boolean success = false;
					String rol = rolField.getText().toLowerCase();
					if (rol.contains("administrador")) {
						success = admin.registrarAdministrador(dniField.getText(), nombreField.getText(),
								apellidosField.getText(), (Genero) generoCombo.getSelectedItem(),
								(Turnos) turnoCombo.getSelectedItem(), sala, rol, personalList);
					} else if (rol.contains("medico")) {
						success = admin.registrarMedico(dniField.getText(), nombreField.getText(),
								apellidosField.getText(), (Genero) generoCombo.getSelectedItem(),
								(Turnos) turnoCombo.getSelectedItem(), sala, rol, personalList);
					} else if (rol.contains("enfermero")) {
						success = admin.registrarEnfermero(dniField.getText(), nombreField.getText(),
								apellidosField.getText(), (Genero) generoCombo.getSelectedItem(),
								(Turnos) turnoCombo.getSelectedItem(), sala, rol, personalList);
					} else if (rol.contains("mantenimiento")) {
						success = admin.registrarMantenimiento(dniField.getText(), nombreField.getText(),
								apellidosField.getText(), (Genero) generoCombo.getSelectedItem(),
								(Turnos) turnoCombo.getSelectedItem(), sala, rol, personalList);
					}
					JOptionPane.showMessageDialog(dialog, success ? "Personal registrado" : "Error al registrar");
					dialog.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(dialog, "Número de sala inválido");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Registrar Paciente
		JButton btnRegistrarPaciente = new JButton("Registrar Paciente");
		btnRegistrarPaciente.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Registrar Paciente", true);
			dialog.setLayout(new GridLayout(9, 2));
			dialog.setSize(400, 350);

			JTextField dniField = new JTextField();
			JTextField nombreField = new JTextField();
			JTextField apellidosField = new JTextField();
			JComboBox<Genero> generoCombo = new JComboBox<>(Genero.values());
			JTextField telefonoField = new JTextField();
			JTextField emailField = new JTextField();
			JTextField obraSocialField = new JTextField();
			JComboBox<TipoInfraestructura> salaCombo = new JComboBox<>(TipoInfraestructura.values());
			JTextField numeroSalaField = new JTextField();

			dialog.add(new JLabel("DNI:"));
			dialog.add(dniField);
			dialog.add(new JLabel("Nombre:"));
			dialog.add(nombreField);
			dialog.add(new JLabel("Apellidos:"));
			dialog.add(apellidosField);
			dialog.add(new JLabel("Género:"));
			dialog.add(generoCombo);
			dialog.add(new JLabel("Teléfono:"));
			dialog.add(telefonoField);
			dialog.add(new JLabel("Email:"));
			dialog.add(emailField);
			dialog.add(new JLabel("Obra Social:"));
			dialog.add(obraSocialField);
			dialog.add(new JLabel("Tipo Sala:"));
			dialog.add(salaCombo);
			dialog.add(new JLabel("Número Sala:"));
			dialog.add(numeroSalaField);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					Infraestructura sala = new Infraestructura((TipoInfraestructura) salaCombo.getSelectedItem(),
							Integer.parseInt(numeroSalaField.getText()), true);
					boolean success = admin.registrarPaciente(dniField.getText(), nombreField.getText(),
							apellidosField.getText(), (Genero) generoCombo.getSelectedItem(),
							Integer.parseInt(telefonoField.getText()), emailField.getText(), obraSocialField.getText(),
							sala, false, pacientesList);
					JOptionPane.showMessageDialog(dialog, success ? "Paciente registrado" : "Error al registrar");
					dialog.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(dialog, "Teléfono o número de sala inválido");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Eliminar Persona
		JButton btnEliminarPersona = new JButton("Eliminar Persona");
		btnEliminarPersona.addActionListener(e -> {
			String dni = JOptionPane.showInputDialog("Ingrese DNI de la persona a eliminar:");
			boolean success = admin.eliminarPersona(dni, personalList, pacientesList);
			JOptionPane.showMessageDialog(this, success ? "Persona eliminada" : "Persona no encontrada");
		});

		// Modificar Personal
		JButton btnModificarPersonal = new JButton("Modificar Personal");
		btnModificarPersonal.addActionListener(e -> {
			String dni = JOptionPane.showInputDialog("Ingrese DNI del personal a modificar:");
			Personal personal = personalList.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
			if (personal != null) {
				admin.modificarPersonal(salasList, personal);
				JOptionPane.showMessageDialog(this, "Personal modificado");
			} else {
				JOptionPane.showMessageDialog(this, "Personal no encontrado");
			}
		});

		// Modificar Paciente
		JButton btnModificarPaciente = new JButton("Modificar Paciente");
		btnModificarPaciente.addActionListener(e -> {
			String dni = JOptionPane.showInputDialog("Ingrese DNI del paciente a modificar:");
			Paciente paciente = pacientesList.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
			if (paciente != null) {
				admin.modificarPaciente(salasList, paciente);
				JOptionPane.showMessageDialog(this, "Paciente modificado");
			} else {
				JOptionPane.showMessageDialog(this, "Paciente no encontrado");
			}
		});

		// Añadir Sala
		JButton btnAnadirSala = new JButton("Añadir Sala");
		btnAnadirSala.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Añadir Sala", true);
			dialog.setLayout(new GridLayout(3, 2));
			dialog.setSize(300, 150);

			JComboBox<TipoInfraestructura> tipoCombo = new JComboBox<>(TipoInfraestructura.values());
			JTextField numeroField = new JTextField();

			dialog.add(new JLabel("Tipo Sala:"));
			dialog.add(tipoCombo);
			dialog.add(new JLabel("Número:"));
			dialog.add(numeroField);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					boolean success = admin.anadirSala(salasList, (TipoInfraestructura) tipoCombo.getSelectedItem(),
							Integer.parseInt(numeroField.getText()));
					JOptionPane.showMessageDialog(dialog, success ? "Sala añadida" : "Error al añadir sala");
					dialog.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(dialog, "Número de sala inválido");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Eliminar Sala
		JButton btnEliminarSala = new JButton("Eliminar Sala");
		btnEliminarSala.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Eliminar Sala", true);
			dialog.setLayout(new GridLayout(3, 2));
			dialog.setSize(300, 150);

			JComboBox<TipoInfraestructura> tipoCombo = new JComboBox<>(TipoInfraestructura.values());
			JTextField numeroField = new JTextField();

			dialog.add(new JLabel("Tipo Sala:"));
			dialog.add(tipoCombo);
			dialog.add(new JLabel("Número:"));
			dialog.add(numeroField);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					boolean success = admin.eliminarSala(salasList, (TipoInfraestructura) tipoCombo.getSelectedItem(),
							Integer.parseInt(numeroField.getText()));
					JOptionPane.showMessageDialog(dialog, success ? "Sala eliminada" : "Sala no encontrada");
					dialog.dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(dialog, "Número de sala inválido");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		panel.add(btnRegistrarPersonal);
		panel.add(btnRegistrarPaciente);
		panel.add(btnEliminarPersona);
		panel.add(btnModificarPersonal);
		panel.add(btnModificarPaciente);
		panel.add(btnAnadirSala);
		panel.add(btnEliminarSala);
		return panel;
	}

	private JPanel createMedicoPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
		Medico medico = new Medico("12345678B", "Dr. Juan", "Pérez", Genero.HOMBRE, Turnos.MAÑANA,
				new Infraestructura(TipoInfraestructura.CONSULTORIO, 2, true), "Cardiólogo");

		// Crear Diagnóstico
		JButton btnCrearDiagnostico = new JButton("Crear Diagnóstico");
		btnCrearDiagnostico.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Crear Diagnóstico", true);
			dialog.setLayout(new GridLayout(6, 2));
			dialog.setSize(400, 250);

			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			JComboBox<TipoHistorial> tipoCombo = new JComboBox<>(TipoHistorial.values());
			JTextField fechaField = new JTextField("2025-05-26");
			JTextField diagnosticoField = new JTextField();
			JTextField tratamientoField = new JTextField();

			dialog.add(new JLabel("Paciente:"));
			dialog.add(pacienteCombo);
			dialog.add(new JLabel("Tipo Historial:"));
			dialog.add(tipoCombo);
			dialog.add(new JLabel("Fecha (YYYY-MM-DD):"));
			dialog.add(fechaField);
			dialog.add(new JLabel("Diagnóstico:"));
			dialog.add(diagnosticoField);
			dialog.add(new JLabel("Tratamiento:"));
			dialog.add(tratamientoField);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					Paciente paciente = (Paciente) pacienteCombo.getSelectedItem();
					boolean success = medico.crearDiagnostico((TipoHistorial) tipoCombo.getSelectedItem(),
							Date.valueOf(fechaField.getText()), diagnosticoField.getText(), tratamientoField.getText(),
							paciente);
					JOptionPane.showMessageDialog(dialog,
							success ? "Diagnóstico creado" : "Error al crear diagnóstico");
					dialog.dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(dialog, "Fecha inválida");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Consultar Historial
		JButton btnConsultarHistorial = new JButton("Consultar Historial");
		btnConsultarHistorial.addActionListener(e -> {
			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			int result = JOptionPane.showConfirmDialog(this, pacienteCombo, "Seleccionar Paciente",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				Paciente paciente = (Paciente) pacienteCombo.getSelectedItem();
				medico.consultarHistorialMedico(paciente);
			}
		});

		// Consultar Pacientes
		JButton btnConsultarPacientes = new JButton("Consultar Pacientes Asignados");
		btnConsultarPacientes.addActionListener(e -> medico.consultarPacientes());

		panel.add(btnCrearDiagnostico);
		panel.add(btnConsultarHistorial);
		panel.add(btnConsultarPacientes);
		return panel;
	}

	private JPanel createEnfermeroPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
		Enfermero enfermero = new Enfermero("12345678C", "Ana", "Gómez", Genero.MUJER, Turnos.TARDE,
				new Infraestructura(TipoInfraestructura.HABITACION, 3, true), "Enfermera General");

		// Asignar Habitación
		JButton btnAsignarHabitacion = new JButton("Asignar Habitación");
		btnAsignarHabitacion.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Asignar Habitación", true);
			dialog.setLayout(new GridLayout(3, 2));
			dialog.setSize(300, 150);

			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			JComboBox<Infraestructura> salaCombo = new JComboBox<>(salasList.toArray(new Infraestructura[0]));

			dialog.add(new JLabel("Paciente:"));
			dialog.add(pacienteCombo);
			dialog.add(new JLabel("Sala:"));
			dialog.add(salaCombo);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				boolean success = enfermero.asignarHabitacion((Infraestructura) salaCombo.getSelectedItem(),
						(Paciente) pacienteCombo.getSelectedItem());
				JOptionPane.showMessageDialog(dialog, success ? "Habitación asignada" : "Habitación no disponible");
				dialog.dispose();
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Dar Alta
		JButton btnDarAlta = new JButton("Dar Alta");
		btnDarAlta.addActionListener(e -> {
			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			int result = JOptionPane.showConfirmDialog(this, pacienteCombo, "Seleccionar Paciente",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				boolean success = enfermero.darAlta((Paciente) pacienteCombo.getSelectedItem());
				JOptionPane.showMessageDialog(this, success ? "Paciente dado de alta" : "Paciente ya estaba de alta");
			}
		});

		panel.add(btnAsignarHabitacion);
		panel.add(btnDarAlta);
		return panel;
	}

	private JPanel createAdministrativoPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
		Administrativo administrativo = new Administrativo("12345678D", "María", "López", Genero.MUJER, Turnos.NOCHE,
				new Infraestructura(TipoInfraestructura.CONSULTORIO, 4, true), "Secretaria");

		// Consultar Salas
		JButton btnConsultarSalas = new JButton("Consultar Salas");
		btnConsultarSalas.addActionListener(e -> administrativo.consultarSalas(salasList));

		// Consultar Personal
		JButton btnConsultarPersonal = new JButton("Consultar Personal");
		btnConsultarPersonal.addActionListener(e -> administrativo.consultarPersonal(personalList));

		// Consultar Pacientes
		JButton btnConsultarPacientes = new JButton("Consultar Pacientes");
		btnConsultarPacientes.addActionListener(e -> administrativo.consultarPacientes(pacientesList));

		// Asignar Paciente
		JButton btnAsignarPaciente = new JButton("Asignar Paciente a Médico");
		btnAsignarPaciente.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Asignar Paciente", true);
			dialog.setLayout(new GridLayout(3, 2));
			dialog.setSize(300, 150);

			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			JComboBox<Medico> medicoCombo = new JComboBox<>(
					personalList.stream().filter(p -> p instanceof Medico).toArray(Medico[]::new));

			dialog.add(new JLabel("Paciente:"));
			dialog.add(pacienteCombo);
			dialog.add(new JLabel("Médico:"));
			dialog.add(medicoCombo);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				boolean success = administrativo.asignarPaciente((Paciente) pacienteCombo.getSelectedItem(),
						(Medico) medicoCombo.getSelectedItem());
				JOptionPane.showMessageDialog(dialog, success ? "Paciente asignado" : "Error al asignar paciente");
				dialog.dispose();
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		// Crear Cita
		JButton btnCrearCita = new JButton("Crear Cita");
		btnCrearCita.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "Crear Cita", true);
			dialog.setLayout(new GridLayout(4, 2));
			dialog.setSize(300, 200);

			JComboBox<Paciente> pacienteCombo = new JComboBox<>(pacientesList.toArray(new Paciente[0]));
			JComboBox<Medico> medicoCombo = new JComboBox<>(
					personalList.stream().filter(p -> p instanceof Medico).toArray(Medico[]::new));
			JTextField fechaField = new JTextField("2025-05-26");
			JComboBox<TipoHistorial> tipoCombo = new JComboBox<>(TipoHistorial.values());

			dialog.add(new JLabel("Paciente:"));
			dialog.add(pacienteCombo);
			dialog.add(new JLabel("Médico:"));
			dialog.add(medicoCombo);
			dialog.add(new JLabel("Fecha (YYYY-MM-DD):"));
			dialog.add(fechaField);
			dialog.add(new JLabel("Tipo Cita:"));
			dialog.add(tipoCombo);

			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(ev -> {
				try {
					boolean success = administrativo.crearCita((Paciente) pacienteCombo.getSelectedItem(),
							Date.valueOf(fechaField.getText()), (Medico) medicoCombo.getSelectedItem(),
							(TipoHistorial) tipoCombo.getSelectedItem());
					JOptionPane.showMessageDialog(dialog, success ? "Cita creada" : "Error al crear cita");
					dialog.dispose();
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(dialog, "Fecha inválida");
				}
			});
			dialog.add(btnConfirm);
			dialog.setVisible(true);
		});

		panel.add(btnConsultarSalas);
		panel.add(btnConsultarPersonal);
		panel.add(btnConsultarPacientes);
		panel.add(btnAsignarPaciente);
		panel.add(btnCrearCita);
		return panel;
	}

	private JPanel createMantenimientoPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
		Mantenimiento mantenimiento = new Mantenimiento("12345678E", "Pedro", "Martínez", Genero.HOMBRE, Turnos.NOCHE,
				new Infraestructura(TipoInfraestructura.HABITACION, 5, true), "Técnico");

		// Cambiar Disponibilidad Habitación
		JButton btnCambiarDisponibilidad = new JButton("Cambiar Disponibilidad Habitación");
		btnCambiarDisponibilidad.addActionListener(e -> {
			JComboBox<Infraestructura> salaCombo = new JComboBox<>(salasList.toArray(new Infraestructura[0]));
			int result = JOptionPane.showConfirmDialog(this, salaCombo, "Seleccionar Sala",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				boolean success = mantenimiento
						.cambioDisponibilidadHabitacion((Infraestructura) salaCombo.getSelectedItem());
				JOptionPane.showMessageDialog(this,
						success ? "Disponibilidad cambiada" : "Error al cambiar disponibilidad");
			}
		});

		panel.add(btnCambiarDisponibilidad);
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VistaMain());
	}
}