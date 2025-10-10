package ConsultorioMedico.controlador;

import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

import javax.swing.table.DefaultTableModel;

public class MenuControlador {
    private ConsultorioMedicoVista vista;
    private Validacion validador;

    public MenuControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.validador = new Validacion(vista);


        // Panel menu. Boton Registrar paciente
        vista.getMenuPanel().getBtnRegistrarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("registrar");
        });

        // Panel menu. Boton Buscar paciente
        vista.getMenuPanel().getBtnBuscarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("buscar");

            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
            modelo.setRowCount(0);

            validador.cargarPacientesEnTabla();
        });

        // Panel menu. Boton Asignar Turno
        vista.getMenuPanel().getBtnAsignarTurno().addActionListener(e -> {
            vista.mostrarPantalla("turno");
        });



    }

}
