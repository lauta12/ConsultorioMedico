package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.BuscarPacientePanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;
import ConsultorioMedico.vista.RegistrarPacientePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsultorioMedicoControlador {
    private PacienteDAO dao = new PacienteDAO();
    private Validacion validador = new Validacion();

    private ConsultorioMedicoVista vista;
    private RegistrarPacientePanel panelRegistrar;
    private BuscarPacientePanel panelBuscar;

    // constructor
    public ConsultorioMedicoControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.panelRegistrar = vista.getRegistrarPacientePanel();
        this.panelBuscar = vista.getBuscarPacientePanel();

        // Panel menu. Boton Registrar paciente
        vista.getMenuPanel().getBtnRegistrarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("registrar");
        });

        // Panel menu. Boton Buscar paciente
        vista.getMenuPanel().getBtnBuscarPaciente().addActionListener(e -> {
            vista.mostrarPantalla("buscar");

            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
            modelo.setRowCount(0);

            cargarPacientesEnTabla();
        });

        // Panel menu. Boton Asignar Turno
        vista.getMenuPanel().getBtnAsignarTurno().addActionListener(e -> {
            vista.mostrarPantalla("turno");
        });

        // Panel BuscarPaciente. Boton buscar por DNI
        vista.getBuscarPacientePanel().getBtnBuscar().addActionListener(e -> {
            String dni = vista.getBuscarPacientePanel().getDni().trim();

            if(dni.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese un DNI para buscar.",
                        "Atencion", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Paciente paciente = dao.buscarPorDni(vista.getBuscarPacientePanel().getDni());
            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();

            modelo.setRowCount(0);

            if(paciente != null) {
                Object[] fila = {
                        paciente.getDni(),
                        paciente.getNombre(),
                        paciente.getApellido(),
                        paciente.getTelefono(),
                        paciente.getObraSocial()
                };
                modelo.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró paciente con ese DNI",
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        // Panel BuscarPaciente. Boton cancelar
        vista.getBuscarPacientePanel().getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(vista.getBuscarPacientePanel());
        });

        // Panel BuscarPaciente. Boton Actualizar
        vista.getBuscarPacientePanel().getBtnActualizar().addActionListener(e -> {
            //DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
            //modelo.setRowCount(0);

            cargarPacientesEnTabla();
            Estilos.limpiarTextFields(panelBuscar);
        });

        // Panel Buscar Paciente. Boton Eliminar
        vista.getBuscarPacientePanel().getBtnEliminar().addActionListener(e -> {
            int filaSeleccionada = vista.getBuscarPacientePanel().getTablaPacientes().getSelectedRow();

            if(filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un paciente para eliminar.");
                return;
            }

            String dni = (String) vista.getBuscarPacientePanel().getModelo().getValueAt(filaSeleccionada, 0);

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Seguro que queres eliminar al paciente con DNI: " + dni + "?",
                    "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);


            if(confirm == JOptionPane.YES_OPTION) {
                dao.eliminarPorDni(dni);
                JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");

            }
            cargarPacientesEnTabla();
        });

        // Panel Registrar Paciente. Boton cancelar
        vista.getRegistrarPacientePanel().getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(panelRegistrar);
        });

        // panel registrar paciente. Boton guardar.
        vista.getRegistrarPacientePanel().getBtnGuardar().addActionListener(e -> {
            panelRegistrar.resetBorders();

            if(validador.validarDatos(panelRegistrar)) {
                Paciente paciente = new Paciente(
                        panelRegistrar.getNombre(),
                        panelRegistrar.getApellido(),
                        panelRegistrar.getDni(),
                        panelRegistrar.getObraSocialSeleccionada(),
                        panelRegistrar.getTelefono()
                );

                dao.guardarPaciente(paciente);

                // limpia los campos despues de guardar los datos
                Estilos.limpiarTextFields(panelRegistrar);
            } else {
                JOptionPane.showMessageDialog(vista, "Hay errores en el forumlario. " +
                        "Revise nuevamente los campos.", "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }

        });


    }

    private void cargarPacientesEnTabla() {
        DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
        modelo.setRowCount(0);

        for (Paciente p : dao.listarTodos()) {
            Object[] fila = {
                    p.getDni(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getTelefono(),
                    p.getObraSocial()
            };
            modelo.addRow(fila);
        }
    }

}
