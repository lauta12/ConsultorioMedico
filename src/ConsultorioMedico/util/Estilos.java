package ConsultorioMedico.util;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

public class Estilos {
    public static final Color COLOR_FONDO = new Color(40, 44, 52); // gris oscuro
    public static final Color COLOR_TEXTO = new Color(230, 230, 230);
    public static final Color COLOR_PRINCIPAL = new Color(100, 149, 237); // azul suave
    public static final Color COLOR_BORDE = new Color(70, 70, 70);
    private static final Color COLOR_FONDO_OSCURO = new Color(45, 45, 45);
    private static final Color COLOR_BOTON = new Color(60, 60, 60);
    private static final Color COLOR_BOTON_HOVER = new Color(90, 90, 90);
    private static final Color COLOR_SELECCION = new Color(0, 120, 215);

    public static void limpiarTextFields(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField textField) {
                textField.setText("");
            } else if (c instanceof Container childContainer) {
                // Si el panel tiene sub-paneles, entro recursivamente
                limpiarTextFields(childContainer);
            }
        }
    }

    public static void aplicarEstiloCalendario(JDateChooser dateChooser) {
        Component editor = dateChooser.getDateEditor().getUiComponent();

        if (editor instanceof JTextField txt) {
            Color fondo = new Color(60, 63, 65);
            Color texto = Color.WHITE;

            txt.setBackground(fondo);
            txt.setForeground(texto);
            txt.setCaretColor(texto);
            txt.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90)));
            txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));

            //  Evita que cambie el color al ganar/perder foco
            txt.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    txt.setForeground(texto);
                    txt.setBackground(fondo.darker());
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    txt.setForeground(texto);
                    txt.setBackground(fondo);
                }
            });

            // Evita que cambie el color al seleccionar fecha desde el calendario
            dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
                txt.setForeground(texto);
                txt.setBackground(fondo);
            });
        }

        // Calendario interno
        JCalendar calendar = dateChooser.getJCalendar();
        calendar.setBackground(new Color(43, 43, 43));
        calendar.setForeground(Color.WHITE);
        calendar.setDecorationBackgroundColor(new Color(255, 255, 255));
        calendar.setWeekdayForeground(Color.LIGHT_GRAY);
        calendar.setSundayForeground(new Color(255, 99, 99));
        calendar.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        for (Component c : calendar.getDayChooser().getDayPanel().getComponents()) {
            if (c instanceof JButton btn) {
                btn.setBackground(new Color(43, 43, 43));
                btn.setForeground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            }
        }

        // Botón del calendario
        dateChooser.getCalendarButton().setBackground(new Color(60, 63, 65));
        dateChooser.getCalendarButton().setForeground(Color.WHITE);
    }


    public static void aplicarEstiloTitulo(Component... components) {
        for(Component c : components) {
            if(c instanceof JLabel) {
                c.setFont(new Font("Sugoe UI", Font.BOLD, 46));
            }
        }

    }
}
