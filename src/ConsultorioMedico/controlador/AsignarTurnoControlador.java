package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.dao.DoctorDAO;
import ConsultorioMedico.dao.TurnoDAO;
import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.vista.AsignarTurnoPanel;
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
    private Turno turno;
    private JDateChooser dateChooser;

    public AsignarTurnoControlador(AsignarTurnoPanel vistaAsignarTurnoPanel, ConsultorioMedicoVista vista) {
        this.vistaAsignarTurnoPanel = vistaAsignarTurnoPanel;
        this.pacienteDAO = new PacienteDAO();
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        this.doctorDAO = new DoctorDAO();
        this.turno = new Turno();
        this.dateChooser = new JDateChooser();

        cargarCombos();

        // botón Cancelar
        vistaAsignarTurnoPanel.getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(vistaAsignarTurnoPanel);
        });

        // botón Guardar turno
        vista.getAsignarTurnoPanel().getBtnAsignar().addActionListener(e -> {
            String dniPaciente = turno.getDniPaciente();
            int idDoctor = turno.getIdDoctor();

            Doctor doctorSeleccionado = (Doctor) vistaAsignarTurnoPanel.getComboDoctores().getSelectedItem();
            Paciente pacienteSeleccionado = (Paciente) vistaAsignarTurnoPanel.getComboPacientes().getSelectedItem();

            if(doctorSeleccionado == null || pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(vistaAsignarTurnoPanel, "Seleccione un doctor y un paciente.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            Date fechaSeleccionada = vista.getAsignarTurnoPanel().getFechaSeleccionada();
            LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime hora = LocalTime.parse(vistaAsignarTurnoPanel.getHoraSeleccionada(), DateTimeFormatter.ofPattern("HH:mm"));

            if(fecha == null || hora == null) {
                JOptionPane.showMessageDialog(vistaAsignarTurnoPanel,
                        "Seleccione una fecha y una hora.",
                        "Error", JOptionPane.ERROR_MESSAGE
                );
                return;
            }



            Turno turno = new Turno(dniPaciente, idDoctor, fecha, hora);
            TurnoDAO dao = new TurnoDAO();

            if (dao.guardarTurno(turno)) {
                JOptionPane.showMessageDialog(vista, "Turno asignado correctamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al asignar el turno.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private void cargarCombos() {
        // LLenar el comboBox de pacientes
        vistaAsignarTurnoPanel.getComboPacientes().removeAllItems();
        for(Paciente p : pacienteDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboPacientes().addItem(p.toString());
        }

        // Llenar el comboBox de Doctores
        vistaAsignarTurnoPanel.getComboDoctores().removeAllItems();
        for(Doctor d : doctorDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboDoctores().addItem(d.toString());
        }
    }

}

