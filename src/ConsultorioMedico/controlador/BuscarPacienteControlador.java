package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.BuscarPacientePanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;
import ConsultorioMedico.vista.RegistrarPacientePanel;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BuscarPacienteControlador {
    private ConsultorioMedicoVista vista;
    private PacienteDAO pacienteDAO;
    private BuscarPacientePanel panelBuscar;
    private Validacion validador;
    private RegistrarPacientePanel panelRegistrar;

    public BuscarPacienteControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.panelBuscar = vista.getBuscarPacientePanel();
        this.pacienteDAO = new PacienteDAO();
        this.validador = new Validacion(vista);


        // boton Buscar
        vista.getBuscarPacientePanel().getBtnBuscar().addActionListener(e -> {
            String dni = vista.getBuscarPacientePanel().getDni().trim();

            if(dni.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese un DNI para buscar.",
                        "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Paciente paciente = pacienteDAO.buscarPorDni(vista.getBuscarPacientePanel().getDni());
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

        // Panel BuscarPaciente. Botón cancelar
        vista.getBuscarPacientePanel().getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(vista.getBuscarPacientePanel());
        });

        // Panel BuscarPaciente. Boton Actualizar
        vista.getBuscarPacientePanel().getBtnActualizar().addActionListener(e -> {
            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
            modelo.setRowCount(0);
            validador.cargarPacientesEnTabla();
            Estilos.limpiarTextFields(panelBuscar);
        });

        // Panel Buscar Paciente. Boton Eliminar
        vista.getBuscarPacientePanel().getBtnEliminar().addActionListener(e -> {
            int[] filasSeleccionadas = vista.getBuscarPacientePanel().getTablaPacientes().getSelectedRows();

            if(filasSeleccionadas.length == 0) {
                JOptionPane.showMessageDialog(null, "Selecciona al menos un paciente para eliminar.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Seguro que querés eliminar " + filasSeleccionadas.length + " paciente(s)?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();

                for(int i = filasSeleccionadas.length - 1; i >=0; i--) {
                    String dni = (String) modelo.getValueAt(filasSeleccionadas[i], 0);
                    pacienteDAO.eliminarPorDni(dni);
                }

                JOptionPane.showMessageDialog(null, "Paciente(s) eliminado(s) correctamente.");
                validador.cargarPacientesEnTabla();
            }
        });


        //botón Editar
        vista.getBuscarPacientePanel().getBtnEditar().addActionListener(e -> {
            int filaSeleccionada = vista.getBuscarPacientePanel().getTablaPacientes().getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(panelBuscar, "Seleccione un paciente para editar.");
                return;
            }

            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();

            String dni = (String) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            String apellido = (String) modelo.getValueAt(filaSeleccionada, 2);
            String telefono = (String) modelo.getValueAt(filaSeleccionada, 3);

            JTextField txtNombre = new JTextField(nombre);
            JTextField txtApellido = new JTextField(apellido);
            JTextField txtTelefono = new JTextField(telefono);

            // tiene que ser array de objetos porque JOptionPane pide un array de objetos
            Object[] campos = {
                    "DNI: ", dni,
                    "Nombre: ", txtNombre,
                    "Apellido: ", txtApellido,
                    "Teléfono:", txtTelefono
            };

            int opcion = JOptionPane.showConfirmDialog(
                    panelBuscar,
                    campos,
                    "Editar paciente",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (opcion == JOptionPane.OK_OPTION) {
                String nuevoNombre = txtNombre.getText();
                String nuevoApellido = txtApellido.getText();
                String nuevoTelefono = txtTelefono.getText();

                if(!validador.validarDatosBasicos(nuevoNombre, nuevoApellido, nuevoTelefono, panelBuscar)) {
                    return;
                }

                // si es valido:
                modelo.setValueAt(validador.capitalizarNombre(nuevoNombre), filaSeleccionada, 1);
                modelo.setValueAt(validador.capitalizarNombre(nuevoApellido), filaSeleccionada, 2);
                modelo.setValueAt(nuevoTelefono, filaSeleccionada, 3);

                // llama al controlador para actualizar el paciente en la base de datos
                pacienteDAO.modificarDatosPaciente(dni, txtNombre.getText(), txtApellido.getText(), txtTelefono.getText());

                JOptionPane.showMessageDialog(panelBuscar, "Se ha modificado correctamente");
            }
        });

    }
}
