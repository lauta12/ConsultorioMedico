package ConsultorioMedico.controlador.menu;

import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

import javax.swing.table.DefaultTableModel;

public class MenuGestionarPacienteControlador {
    private ConsultorioMedicoVista vista;
    private Validacion validador;

    public MenuGestionarPacienteControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.validador = new Validacion(vista);

        // botón registrar paciente.
        vista.getMenuGestionarPacientePanel().getBtnRegistrarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("registrarPaciente");
        });

        // botón modificar paciente
        vista.getMenuGestionarPacientePanel().getBtnModificarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("modificarPaciente");

            DefaultTableModel modelo = vista.getModificarPacientePanel().getModelo();
            modelo.setRowCount(0);

            validador.cargarPacientesEnTabla();
        });

    }
}

