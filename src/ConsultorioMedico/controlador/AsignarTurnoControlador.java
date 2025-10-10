package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.dao.DoctorDAO;
import ConsultorioMedico.dao.TurnoDAO;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.vista.AsignarTurnoPanel;

public class AsignarTurnoControlador {

    private AsignarTurnoPanel vista;
    private PacienteDAO pacienteDAO;
    private TurnoDAO turnoDAO;
    private DoctorDAO doctorDAO;
    //private

    public AsignarTurnoControlador(AsignarTurnoPanel vista) {
        this.vista = vista;
        this.pacienteDAO = new PacienteDAO();
        this.turnoDAO = new TurnoDAO();
        this.doctorDAO = new DoctorDAO();

        cargarCombos();
        //configurarEventos();

    }
    private void cargarCombos() {
        // LLenar el comboBox de pacientes
        vista.getComboPacientes().removeAllItems();
        for(Paciente p : pacienteDAO.listarTodos()) {
            vista.getComboPacientes().addItem(p.getNombre() + " " + p.getApellido() + " (" + p.getDni() + ")");
        }

        // Llenar el comboBox de Doctores
        vista.getComboDoctores().removeAllItems();
        /*
        for(Doctor d : doctorDAO.listarTodos()) {
            vista.getComboDoctores().addItem(d.getNombre() + " - " + d.getEspecialidad());
        }
         */
    }
}

