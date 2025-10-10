package ConsultorioMedico.util;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class Estilos {

    public static final Color COLOR_FONDO = new Color(40, 44, 52); // gris oscuro
    public static final Color COLOR_TEXTO = new Color(230, 230, 230);
    public static final Color COLOR_PRINCIPAL = new Color(100, 149, 237); // azul suave
    public static final Color COLOR_BORDE = new Color(70, 70, 70);

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

                // Listener para resaltar borde en foco
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

                // Custom Scrollbar en el desplegable
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


    //  Estilo para JTable (modo oscuro con filas tipo zebra)
    public static void aplicarEstiloTabla(JTable tabla) {
        //  Colores principales
        Color fondoTabla = new Color(30, 33, 38);     // fondo general (oscuro, tipo VSCode)
        Color fondoZebra = new Color(40, 44, 52);     // filas alternadas
        Color texto = new Color(220, 220, 220);       // texto suave
        Color seleccion = new Color(70, 110, 200);    // azul de selección
        Color borde = new Color(55, 60, 68);          // gris azulado para los bordes

        //  Configuración general de la tabla
        tabla.setBackground(fondoTabla);
        tabla.setForeground(texto);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabla.setRowHeight(28);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionBackground(seleccion);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setBorder(BorderFactory.createLineBorder(borde));

        //  Encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(45, 48, 54));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borde));
        header.setReorderingAllowed(false);
        header.setOpaque(true);

        //  Renderer personalizado (filas alternadas)
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? fondoTabla : fondoZebra);
                    c.setForeground(texto);
                }
                return c;
            }
        });
    }

    public static void aplicarEstiloScrollPane(JScrollPane scrollPane) {
        Color fondo = new Color(30, 33, 38);          // Fondo general
        Color borde = new Color(55, 60, 68);          // Borde del scroll
        Color thumbNormal = new Color(80, 85, 95);    // Reposo
        Color thumbHover = new Color(100, 105, 115);  // Hover
        Color thumbPressed = new Color(70, 110, 200); // Azul al arrastrar
        Color trackColor = new Color(40, 44, 52);     // Fondo del track

        scrollPane.getViewport().setBackground(fondo);
        scrollPane.setBorder(BorderFactory.createLineBorder(borde));
        scrollPane.getVerticalScrollBar().setBackground(trackColor);
        scrollPane.getHorizontalScrollBar().setBackground(trackColor);

        //  ScrollBar personalizado
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            private boolean dragging = false;
            private boolean hovering = false;

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = thumbNormal;
                this.trackColor = trackColor;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (!scrollbar.isEnabled() || thumbBounds.width > thumbBounds.height && scrollbar.getOrientation() == JScrollBar.VERTICAL)
                    return;

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color currentColor = dragging
                        ? thumbPressed
                        : (hovering ? thumbHover : thumbNormal);

                g2.setColor(currentColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 8, 8);
                g2.dispose();
            }

            @Override
            protected void installListeners() {
                super.installListeners();
                scrollbar.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hovering = true;
                        scrollbar.repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hovering = false;
                        scrollbar.repaint();
                    }

                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        dragging = true;
                        scrollbar.repaint();
                    }

                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent e) {
                        dragging = false;
                        scrollbar.repaint();
                    }
                });
            }
        });
    }


    public static void aplicarEstiloDateChooser(JDateChooser chooser) {
        chooser.getCalendarButton().setBackground(new Color(50, 50, 50));
        chooser.getCalendarButton().setForeground(Color.WHITE);
        chooser.setBackground(new Color(50, 50, 50));
        ((JTextField) chooser.getDateEditor().getUiComponent()).setBackground(new Color(50, 50, 50));
        ((JTextField) chooser.getDateEditor().getUiComponent()).setForeground(Color.WHITE);
    }


    private static JButton crearBotonInvisible() {
        JButton boton = new JButton();
        boton.setPreferredSize(new Dimension(0, 0));
        boton.setMinimumSize(new Dimension(0, 0));
        boton.setMaximumSize(new Dimension(0, 0));
        return boton;
    }

    public static void aplicarEstiloScroll(JScrollPane scroll) {
        scroll.getViewport().setBackground(new Color(40, 40, 40));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
    }

}
