import ConsultorioMedico.controlador.ConsultorioMedicoControlador;
import ConsultorioMedico.vista.ConsultorioMedicoVista;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        javax.swing.UIManager.put("defaultFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        ConsultorioMedicoVista vista = new ConsultorioMedicoVista();
        ConsultorioMedicoControlador controlador = new ConsultorioMedicoControlador(vista);

        controlador.iniciar();
    }

}