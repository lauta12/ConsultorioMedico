package ConsultorioMedico.controlador;

import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class ConsultorioMedicoControlador {

    private ConsultorioMedicoVista vista;

    // Controladores secundarios
    private MenuControlador menuControlador;
    private RegistrarPacienteControlador registrarControlador;
    private BuscarPacienteControlador buscarControlador;
    private AsignarTurnoControlador turnoControlador;

    public ConsultorioMedicoControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;

        // Crear los controladores secundarios y pasar la vista principal
        this.menuControlador = new MenuControlador(vista);
        this.registrarControlador = new RegistrarPacienteControlador(vista);
        this.buscarControlador = new BuscarPacienteControlador(vista);
        this.turnoControlador = new AsignarTurnoControlador(vista.getAsignarTurnoPanel(), vista);
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.mostrarPantalla("menu"); // muestra el panel inicial
    }
}
