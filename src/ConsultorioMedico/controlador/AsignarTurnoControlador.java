package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.dao.DoctorDAO;
import ConsultorioMedico.dao.TurnoDAO;
import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.vista.AsignarTurnoPanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class AsignarTurnoControlador {
    private AsignarTurnoPanel vistaAsignarTurnoPanel;
    private ConsultorioMedicoVista vista;
    private PacienteDAO pacienteDAO;
    private TurnoDAO turnoDAO;
    private DoctorDAO doctorDAO;

    public AsignarTurnoControlador(AsignarTurnoPanel vistaAsignarTurnoPanel, ConsultorioMedicoVista vista) {
        this.vistaAsignarTurnoPanel = vistaAsignarTurnoPanel;
        this.pacienteDAO = new PacienteDAO();
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        this.doctorDAO = new DoctorDAO();

        cargarCombos();
        //configurarEventos();

        // boton Cancelar
        vistaAsignarTurnoPanel.getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
        });

    }
    private void cargarCombos() {
        // LLenar el comboBox de pacientes
        vistaAsignarTurnoPanel.getComboPacientes().removeAllItems();
        for(Paciente p : pacienteDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboPacientes().addItem(p.getNombre() + " " + p.getApellido() + " (" + p.getDni() + ")");
        }

        // Llenar el comboBox de Doctores
        vistaAsignarTurnoPanel.getComboDoctores().removeAllItems();

        for(Doctor d : doctorDAO.listarTodos()) {
            vistaAsignarTurnoPanel.getComboDoctores().addItem(d.getNombre() + " - " + d.getEspecialidad());
        }

    }
}

