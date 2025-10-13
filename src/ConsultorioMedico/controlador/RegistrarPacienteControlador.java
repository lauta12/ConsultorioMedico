package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.ConsultorioMedicoVista;
import ConsultorioMedico.vista.RegistrarPacientePanel;

import javax.swing.*;

public class RegistrarPacienteControlador {
   private ConsultorioMedicoVista vista;
   private RegistrarPacientePanel panelRegistrar;
   private Validacion validador = new Validacion(vista);
   private PacienteDAO pacienteDAO = new PacienteDAO();

    public RegistrarPacienteControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.panelRegistrar = vista.getRegistrarPacientePanel();

        // panel registrar paciente. Boton guardar.
        vista.getRegistrarPacientePanel().getBtnGuardar().addActionListener(e -> {

            if(validador.validarDatos(panelRegistrar)) {
                Paciente paciente = new Paciente(
                        panelRegistrar.getNombre(),
                        panelRegistrar.getApellido(),
                        panelRegistrar.getDni(),
                        panelRegistrar.getObraSocialSeleccionada(),
                        panelRegistrar.getTelefono()
                );

                pacienteDAO.guardarPaciente(paciente);

                // limpia los campos después de guardar los datos
                Estilos.limpiarTextFields(panelRegistrar);
            } else {
                JOptionPane.showMessageDialog(vista, "Hay errores en el forumlario. " +
                        "Revise nuevamente los campos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            }

        });


        // Botón cancelar
        vista.getRegistrarPacientePanel().getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(panelRegistrar);
            panelRegistrar.getComboObraSocial().setSelectedIndex(0);
        });
    }

}
