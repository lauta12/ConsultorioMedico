package ConsultorioMedico.controlador.menu;

import ConsultorioMedico.controlador.turno.ModificarTurnoControlador;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class MenuGestionarTurnoControlador {

    private ConsultorioMedicoVista vista;

    public MenuGestionarTurnoControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;

        // botón Asignar turno
        vista.getMenuGestionarTurnoPanel().getBtnAsignarTurno().addActionListener(e -> {
            vista.mostrarPantalla("asignarTurno");

        });
        // boton Volver
        vista.getMenuGestionarTurnoPanel().getBtnVolver().addActionListener(e -> {
            vista.mostrarPantalla("menuPanel");
        });

        vista.getMenuGestionarTurnoPanel().getBtnModificarTurno().addActionListener(e -> {
            vista.mostrarPantalla("modificarTurno");
        });
    }

}
