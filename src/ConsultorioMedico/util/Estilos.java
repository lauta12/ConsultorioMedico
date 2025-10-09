package ConsultorioMedico.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Estilos {

    public static void aplicarEstiloBoton(Component... componentes) {
        for(Component c : componentes) {
            if(c instanceof JButton boton) {
                boton.setFocusPainted(false);
                boton.setBackground(new Color(60, 63, 65)); // gris base
                boton.setForeground(Color.WHITE);
                boton.setFont(new Font("SansSerif", Font.BOLD, 20));
                boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Colores de interacción
                Color colorBase = new Color(60, 63, 65);
                Color colorHover = new Color(75, 78, 80);
                Color colorClick = new Color(90, 93, 95);

                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        boton.setBackground(colorHover);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        boton.setBackground(colorBase);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        boton.setBackground(colorClick);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        boton.setBackground(colorHover);
                    }
                });
            }
        }

    }

    public static void aplicarEstiloLabel(Component... componentes) {
        for(Component c : componentes) {
            if(c instanceof JLabel label) {
                label.setForeground(Color.WHITE); // Texto blanco
                label.setFont(new Font("SansSerif", Font.PLAIN, 20));
            }
        }
    }
    public static void aplicarEstiloTextField(Component... componentes) {
        Border bordeNormal = BorderFactory.createLineBorder(new Color(100, 100, 100), 1); // gris oscuro
        Border bordeFocus = BorderFactory.createLineBorder(new Color(100, 149, 237), 2); // azul clarito

        for (Component c : componentes) {
            if (c instanceof JTextField textField) {
                textField.setBackground(new Color(40, 40, 40)); // Fondo gris oscuro
                textField.setForeground(Color.WHITE);           // Texto blanco
                textField.setCaretColor(Color.WHITE);           // Cursor blanco
                textField.setFont(new Font("SansSerif", Font.PLAIN, 19));
                textField.setBorder(bordeNormal);

                // 🎯 Listener para resaltar borde en foco
                textField.addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        textField.setBorder(bordeFocus);
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        textField.setBorder(bordeNormal);
                    }
                });
            }
        }
    }

    public static void aplicarEstiloPanel(Component... componentes) {
        for(Component c : componentes) {
            if(c instanceof JPanel panel) {
                panel.setBackground(new Color(30, 30, 30)); // Gris oscuro
            }
        }
    }

    public static void aplicarEstiloComboBox(Component... componentes) {
        for(Component c : componentes) {
            if (c instanceof JComboBox<?> comboBox) {
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                comboBox.setBackground(new Color(60, 63, 65));   // Fondo oscuro
                comboBox.setForeground(Color.WHITE);            // Texto blanco
                comboBox.setFocusable(false);
                comboBox.setMaximumRowCount(5);                 // Cantidad de ítems visibles al desplegar
                comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Borde personalizado (opcional)
                comboBox.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
                comboBox.setUI(new BasicComboBoxUI() {
                    @Override
                    protected JButton createArrowButton() {
                        JButton boton = new JButton("▼");
                        boton.setBorder(BorderFactory.createEmptyBorder());
                        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
                        boton.setBackground(new Color(80, 80, 80));
                        boton.setForeground(Color.WHITE);
                        return boton;
                    }
                });

                // 🔽 Custom Scrollbar en el desplegable
                Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);
                if (comp instanceof JPopupMenu) {
                    JList<?> list = (JList<?>) ((JScrollPane) ((JPopupMenu) comp).getComponent(0)).getViewport().getView();
                    JScrollBar scrollBar = ((JScrollPane) ((JPopupMenu) comp).getComponent(0)).getVerticalScrollBar();

                    list.setBackground(new Color(50, 50, 50));
                    list.setForeground(Color.WHITE);
                    list.setCursor(new Cursor(Cursor.HAND_CURSOR));

                    scrollBar.setUI(new BasicScrollBarUI() {
                        @Override
                        protected void configureScrollBarColors() {
                            this.thumbColor = new Color(100, 100, 100);   // color del "botoncito"
                            this.trackColor = new Color(60, 63, 65);     // color del fondo
                        }

                        @Override
                        protected JButton createDecreaseButton(int orientation) {
                            return crearBotonVacio();
                        }

                        @Override
                        protected JButton createIncreaseButton(int orientation) {
                            return crearBotonVacio();
                        }

                        private JButton crearBotonVacio() {
                            JButton button = new JButton();
                            button.setPreferredSize(new Dimension(0, 0));
                            button.setVisible(false);
                            return button;
                        }
                    });
                }

                comboBox.setBorder(BorderFactory.createLineBorder(new Color(100,100,100), 1));
            }
        }
    }

    public static void aplicarEstiloTitulo(Component... componentes) {
        for(Component c : componentes) {
            if(c instanceof JLabel label) {
                label.setForeground(Color.WHITE);
                label.setFont(new Font("SansSerif", Font.BOLD, 35));
            }
        }
    }

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

    public static void aplicarEstiloTabla(JTable tabla) {
        // Fondo de la tabla y texto
        tabla.setBackground(new Color(40, 40, 40));   // gris oscuro
        tabla.setForeground(Color.WHITE);            // texto blanco
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Color de las filas seleccionadas
        tabla.setSelectionBackground(new Color(70, 70, 70));
        tabla.setSelectionForeground(Color.CYAN);

        // Grid (líneas entre celdas)
        tabla.setGridColor(new Color(80, 80, 80));

        // Header de la tabla
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setReorderingAllowed(false); // opcional: evita mover columnas
    }

    public static void aplicarEstiloScroll(JScrollPane scroll) {
        scroll.getViewport().setBackground(new Color(40, 40, 40));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
    }

}
