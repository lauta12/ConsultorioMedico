package ConsultorioMedico.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/consultorio?useSSL=false&serverTimezone=UTC";
    private static final String USER = "lautaro";
    private static final String PASSWORD = "gus123";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
