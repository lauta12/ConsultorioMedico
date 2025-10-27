package ConsultorioMedico.controlador.turno;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.dao.DoctorDAO;
import ConsultorioMedico.dao.TurnoDAO;
import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.turno.AsignarTurnoPanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AsignarTurnoControlador {
    private AsignarTurnoPanel vistaAsignarTurnoPanel;
    private ConsultorioMedicoVista vista;
    private PacienteDAO pacienteDAO;
    private TurnoDAO turnoDAO;
    private DoctorDAO doctorDAO;
    private JDateChooser dateChooser;

    public AsignarTurnoControlador(AsignarTurnoPanel vistaAsignarTurnoPanel, ConsultorioMedicoVista vista) {
        this.vistaAsignarTurnoPanel = vistaAsignarTurnoPanel;
        this.pacienteDAO = new PacienteDAO();
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        this.doctorDAO = new DoctorDAO();

        cargarCombos();

        // botón Cancelar
        vistaAsignarTurnoPanel.getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menuGestionarTurno");
            Estilos.limpiarTextFields(vistaAsignarTurnoPanel);
        });


        // botón Guardar turno
        vista.getAsignarTurnoPanel().getBtnAsignar().addActionListener(e -> {
            Paciente pacienteSeleccionado = (Paciente) vistaAsignarTurnoPanel.getComboPacientes().getSelectedItem();
            Doctor doctorSeleccionado = (Doctor) vistaAsignarTurnoPanel.getComboDoctores().getSelectedItem();
            Date fechaSeleccionadaDate = vistaAsignarTurnoPanel.getDateChooser().getDate();
            String horaSeleccionada = (String) vistaAsignarTurnoPanel.getComboHora().getSelectedItem();

            // validaciones
            if (pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (doctorSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un doctor.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fechaSeleccionadaDate == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (horaSeleccionada == null || horaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una hora.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // convierte fecha y hora
            LocalDate fechaSeleccionada = fechaSeleccionadaDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalTime hora;

            try {
                hora = LocalTime.parse(horaSeleccionada, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato de hora inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean datosValidos = Validacion.validarTurno(vistaAsignarTurnoPanel, pacienteSeleccionado, doctorSeleccionado, fechaSeleccionada, hora);
            if (!datosValidos) {
                return;
            }

            // verifica si ya existe el turno
            boolean turnoExiste = turnoDAO.existeTurno(doctorSeleccionado, fechaSeleccionada, hora);
            if (turnoExiste) {
                JOptionPane.showMessageDialog(null, "El turno ya está asignado en ese horario.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // crea y guarda el turno
            Turno nuevoTurno = new Turno(
                    pacienteSeleccionado.getDni(),
                    doctorSeleccionado.getId_doctor(),
                    fechaSeleccionada,
                    hora
            );
            turnoDAO.guardarTurno(nuevoTurno);

            JOptionPane.showMessageDialog(null, "Turno asignado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

    }

    private void cargarCombos() {
        // LLenar el comboBox de pacientes
        vistaAsignarTurnoPanel.getComboPacientes().removeAllItems();
        for(Paciente p : pacienteDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboPacientes().addItem(p);
        }

        // Llenar el comboBox de Doctores
        vistaAsignarTurnoPanel.getComboDoctores().removeAllItems();
        for(Doctor d : doctorDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboDoctores().addItem(d);
        }
    }

}

