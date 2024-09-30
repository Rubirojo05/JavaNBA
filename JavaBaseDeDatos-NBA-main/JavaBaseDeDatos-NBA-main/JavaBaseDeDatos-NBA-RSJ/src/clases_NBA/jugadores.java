package clases_NBA;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class jugadores extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel originalMenuPanel; // Panel original del menú

    public jugadores() {
        setTitle("Consultas NBA");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 100, 1100, 650);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Cerrando...");
                dispose(); // Cerrar la ventana de snake

                // Crear e inicializar la ventana del portal
                inicioNBA inicio = new inicioNBA();
                inicio.setVisible(true); // Mostrar la ventana del portal
            }
        });

        // Valores de tamaño preferido de los paneles
        int panelWidth = 150; // Reducido el ancho del panel
        int panelHeight = 150; // Reducido la altura del panel

        // Calculamos la altura necesaria del panel original del menú para 432 paneles
        int totalPanelHeight = (panelHeight + 28) * 44; // 36 filas de paneles
        originalMenuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        originalMenuPanel.setBackground(Color.GRAY);
        originalMenuPanel.setPreferredSize(new Dimension(panelWidth * 7, totalPanelHeight * 2)); // 8 columnas de paneles

        // Creamos un JScrollPane y le agregamos el panel original del menú
        JScrollPane scrollPane = new JScrollPane(originalMenuPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Ajustamos la política de desplazamiento
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30); // Incremento al desplazar
        verticalScrollBar.setBlockIncrement(panelHeight + 30); // Incremento al hacer clic en las flechas

        // Agregamos el JScrollPane al frame
        add(scrollPane, BorderLayout.CENTER);

        // Creamos 432 paneles y los añadimos al panel original del menú
        for (int i = 1; i <= 432; i++) {
            // Creamos un panel en blanco como separador
            JPanel separatorPanel = new JPanel();
            separatorPanel.setPreferredSize(new Dimension(panelWidth / 50, panelHeight)); // Tamaño del separador
            originalMenuPanel.add(separatorPanel);

            // Creamos un panel para la consulta y lo añadimos al panel original del menú
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(panelWidth, panelHeight)); // Establecemos el tamaño preferido del panel
            panel.setBorder(BorderFactory.createLineBorder(Color.black)); // Añadimos un borde para que se vean los paneles
            panel.setLayout(new GridBagLayout()); // Usamos GridBagLayout para centrar el texto

            // Llamada "automatizada" de las consultas
            consultas consul = new consultas();
            String salidaMET = i + ". " + consul.consulta_jugadores(i);

            // Creamos el JLabel y establecemos el texto usando la variable resultadoConsulta
            JLabel label = new JLabel(salidaMET, SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            panel.add(label);

            originalMenuPanel.add(panel);
        }
    }

    public static void main(String[] args) {
        jugadores j = new jugadores();
        j.setVisible(true);
    }
}
