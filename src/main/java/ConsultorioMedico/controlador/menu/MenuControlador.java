package ConsultorioMedico.controlador.menu;

import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class MenuControlador {
    private ConsultorioMedicoVista vista;

    public MenuControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;

        // botón gestionar paciente.
        vista.getMenuPanel().getBtnGestionarPaciente().addActionListener(e -> {
            new MenuGestionarPacienteControlador(vista); // Crea el controlador solo cuando el usuario hace click. Así es más eficiente 😎.
            vista.mostrarPantalla("menuGestionarPaciente");
        });

        // botón gestionar turno.
        vista.getMenuPanel().getBtnGestionarTurno().addActionListener(e -> {
            new MenuGestionarTurnoControlador(vista);
            vista.mostrarPantalla("menuGestionarTurno");
        });

        // botón volver.
        vista.getMenuGestionarPacientePanel().getBtnVolver().addActionListener(e -> {
            vista.mostrarPantalla("menuPanel");
        });
    }

}