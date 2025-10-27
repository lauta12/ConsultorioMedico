package ConsultorioMedico.controlador;

import ConsultorioMedico.controlador.menu.MenuControlador;
import ConsultorioMedico.controlador.paciente.ModificarPacienteControlador;
import ConsultorioMedico.controlador.paciente.RegistrarPacienteControlador;
import ConsultorioMedico.controlador.turno.AsignarTurnoControlador;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class ConsultorioMedicoControlador {

    private ConsultorioMedicoVista vista;

    // Controladores secundarios
    private MenuControlador menuControlador;
    private RegistrarPacienteControlador registrarControlador;
    private ModificarPacienteControlador modificarPacienteControlador;
    private AsignarTurnoControlador turnoControlador;

    public ConsultorioMedicoControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;

        // Crear los controladores secundarios y pasar la vista principal
        this.menuControlador = new MenuControlador(vista);
        this.registrarControlador = new RegistrarPacienteControlador(vista);
        this.modificarPacienteControlador = new ModificarPacienteControlador(vista);
        this.turnoControlador = new AsignarTurnoControlador(vista.getAsignarTurnoPanel(), vista);
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.mostrarPantalla("menuPanel"); // muestra el panel inicial
    }
}
