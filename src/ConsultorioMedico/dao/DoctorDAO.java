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

}
