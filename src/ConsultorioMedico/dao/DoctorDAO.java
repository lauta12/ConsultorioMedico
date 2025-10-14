package ConsultorioMedico.dao;

import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public List<Doctor> listarTodos() {
        List<Doctor> doctores = new ArrayList<>();

        String sql = "SELECT * FROM doctores ORDER BY nombre ASC";

        try(Connection conn = Conexion.getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Doctor d = new Doctor();
                d.setId_doctor(rs.getInt("id_doctor"));
                d.setNombre(rs.getString("nombre"));
                d.setEspecialidad(rs.getString("especialidad"));
                d.setTelefono(rs.getString("telefono"));
                d.setEmail(rs.getString("email"));
                doctores.add(d);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return doctores;
    }

    public boolean agregar(Doctor d) {
        String sql = "INSERT INTO doctores (nombre, especialidad, telefono, email) VALUES (?,?,?,?)";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getEspecialidad());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getEmail());

            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM doctores WHERE id_doctor = ?";

        try(Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Doctor d) {
        String sql = "UPDATE doctores SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id_doctor = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getEspecialidad());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getEmail());
            ps.setInt(5, d.getId_doctor());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Doctor buscarPorId(int id) {
        String sql = "SELECT * FROM doctores WHERE id_doctor = ?";
        Doctor d = null;

        try(Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                d = new Doctor();
                d.setId_doctor(rs.getInt("id_doctor"));
                d.setNombre(rs.getString("nombre"));
                d.setEspecialidad(rs.getString("especialidad"));
                d.setTelefono(rs.getString("telefono"));
                d.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return d;
    }


}
