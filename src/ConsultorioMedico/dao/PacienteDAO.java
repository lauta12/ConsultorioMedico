package ConsultorioMedico.dao;

import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PacienteDAO {

    public void guardarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nombre, apellido, dni, obra_social, telefono) VALUES (?,?,?,?,?)";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setString(3, paciente.getDni());
            stmt.setString(4, paciente.getObraSocial());
            stmt.setString(5, paciente.getTelefono());

            stmt.executeUpdate();

            System.out.println("Paciente guardado en la base de datos");

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void eliminarPorDni(String dni) {
        String sql = "DELETE FROM pacientes WHERE dni = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();

            System.out.println("Paciente eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public Paciente buscarPorDni(String dni) {
        String sql = "SELECT * FROM pacientes WHERE dni = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            var rs = stmt.executeQuery();

            if(rs.next()) {
                return new Paciente(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("obra_social"),
                        rs.getString("telefono")
                );
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellido, telefono, obra_social FROM pacientes";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("obra_social"),
                        rs.getString("telefono")
                );
                lista.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
