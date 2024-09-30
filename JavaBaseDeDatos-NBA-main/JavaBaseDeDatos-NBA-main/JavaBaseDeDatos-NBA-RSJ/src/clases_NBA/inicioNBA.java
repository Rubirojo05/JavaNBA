package clases_NBA;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class inicioNBA extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLayeredPane layeredPane;
    private int currentContentIndex = 0;
    private String[] contents = {"Equipos", "Jugadores", "Estadísticas"};
    private JButton equiposButton;
    private JButton jugadoresButton;
    private JButton estadisticasButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inicioNBA frame = new inicioNBA();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public inicioNBA() {
        setTitle("Portal NBA");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 100, 1100, 650);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LayeredPane para superponer componentes
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1084, 621);
        contentPane.add(layeredPane);

        // -------------- BOTONES ------------------

        // --------Botón izquierdo----------
        JPanel btn1 = new JPanel();
        layeredPane.setLayer(btn1, 101);
        btn1.setBounds(100, 220, 50, 170);
        btn1.setOpaque(false); // UTILIZAR CUANDO IMPLEMENTE IMAGEN
        layeredPane.add(btn1);

        try {
            // Carga la imagen utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("imagenes\\\\izquierda.png"));

            // Escala la imagen para que se ajuste al tamaño del JPanel
            Image scaledBgImage = bgIcon.getImage().getScaledInstance(btn1.getWidth(), btn1.getHeight(), Image.SCALE_SMOOTH);

            // Crea un JLabel para mostrar la imagen escalada como fondo
            JLabel bgLabel = new JLabel(new ImageIcon(scaledBgImage));
            bgLabel.setBounds(0, 0, btn1.getWidth(), btn1.getHeight());

            // Agrega el JLabel al LayeredPane en una capa inferior
            btn1.add(bgLabel, JLayeredPane.PALETTE_LAYER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnLeft = new JButton();
        btnLeft.setBounds(100, 220, 50, 170);
        btnLeft.setContentAreaFilled(false);
        btnLeft.setBorderPainted(false);
        btnLeft.setOpaque(false);
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousContent();
            }
        });
        layeredPane.add(btnLeft, JLayeredPane.PALETTE_LAYER);

        // --------Botón derecho------------
        JPanel btn2 = new JPanel();
        layeredPane.setLayer(btn2, 101);
        btn2.setBounds(930, 220, 50, 170);
        btn2.setOpaque(false); // UTILIZAR CUANDO IMPLEMENTE IMAGEN
        layeredPane.add(btn2);

        try {
            // Carga la imagen utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("imagenes\\\\derecha.png"));

            // Escala la imagen para que se ajuste al tamaño del JPanel
            Image scaledBgImage = bgIcon.getImage().getScaledInstance(btn2.getWidth(), btn2.getHeight(), Image.SCALE_SMOOTH);

            // Crea un JLabel para mostrar la imagen escalada como fondo
            JLabel bgLabel = new JLabel(new ImageIcon(scaledBgImage));
            bgLabel.setBounds(0, 0, btn2.getWidth(), btn2.getHeight());

            // Agrega el JLabel al LayeredPane en una capa inferior
            btn2.add(bgLabel, JLayeredPane.PALETTE_LAYER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnRight = new JButton();
        btnRight.setBounds(930, 220, 50, 170);
        btnRight.setContentAreaFilled(false);
        btnRight.setBorderPainted(false);
        btnRight.setOpaque(false);
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextContent();
            }
        });
        layeredPane.add(btnRight, JLayeredPane.PALETTE_LAYER);

        // ----------- IMAGEN FONDO --------------
        try {
            // Carga la imagen utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("imagenes\\\\image.png"));

            // Escala la imagen para que se ajuste al tamaño del JPanel
            Image scaledBgImage = bgIcon.getImage().getScaledInstance(layeredPane.getWidth(), layeredPane.getHeight(), Image.SCALE_SMOOTH);

            // Crea un JLabel para mostrar la imagen escalada como fondo
            JLabel bgLabel = new JLabel(new ImageIcon(scaledBgImage));
            bgLabel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());

            // Agrega el JLabel al LayeredPane en una capa inferior
            layeredPane.add(bgLabel, JLayeredPane.DEFAULT_LAYER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Botón para Equipos
        equiposButton = new JButton();
        equiposButton.setBounds(230, 120, 629, 379);
        equiposButton.setOpaque(false);
        equiposButton.setBorderPainted(false);
        equiposButton.setContentAreaFilled(false); // Hacer el fondo transparente
        equiposButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                equipos equipos;
				try {
					equipos = new equipos();
					equipos.setVisible(true);
					dispose(); // Cerrar la ventana
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// abre equipos
                
            }
        });

        // Botón para Jugadores
        jugadoresButton = new JButton();
        jugadoresButton.setBounds(230, 120, 629, 379);
        jugadoresButton.setOpaque(false);
        jugadoresButton.setBorderPainted(false);
        jugadoresButton.setContentAreaFilled(false); // Hacer el fondo transparente
        jugadoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jugadores jugadores = new jugadores();// abre jugadores
                jugadores.setVisible(true);
                dispose(); // Cerrar la ventana
            }
        });

        // Botón para Estadísticas
        estadisticasButton = new JButton();
        estadisticasButton.setBounds(230, 120, 629, 379);
        estadisticasButton.setOpaque(false);
        estadisticasButton.setBorderPainted(false);
        estadisticasButton.setContentAreaFilled(false); // Hacer el fondo transparente
        estadisticasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultasPredefinidas consultas = new consultasPredefinidas();// abre consultas
                consultas.setVisible(true);
                dispose(); // Cerrar la ventana
            }
        });

        showContent();
    }

    // Método para mostrar el contenido actual
    private void showContent() {
        System.out.println(contents[currentContentIndex]);
        layeredPane.revalidate();
        layeredPane.repaint();

        // Limpiar solo la capa PALETTE_LAYER
        Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.PALETTE_LAYER);
        for (Component component : components) {
            if (component instanceof JPanel) {
                layeredPane.remove(component);
            }
        }

        // Eliminar todos los botones específicos
        layeredPane.remove(equiposButton);
        layeredPane.remove(jugadoresButton);
        layeredPane.remove(estadisticasButton);

        //-----------------EQUIPOS------------------------
        if (contents[currentContentIndex].equals("Equipos")) {
            JPanel equiposPanel = new JPanel(new BorderLayout());
            equiposPanel.setBounds(230, 120, 629, 379);
            equiposPanel.setOpaque(false); // Hace que el fondo del JPanel sea transparente
            layeredPane.add(equiposPanel, JLayeredPane.PALETTE_LAYER);

            // Agregar el botón solo si estamos en Equipos
            layeredPane.add(equiposButton, JLayeredPane.PALETTE_LAYER);

            try {
                // Carga la imagen de equipos utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
                ImageIcon equiposIcon = new ImageIcon(getClass().getResource("imagenes\\\\equipos.png"));

                int scaledWidth = 635; // Ancho deseado
                int scaledHeight = 635; // Alto deseado
                Image scaledImage = equiposIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Crea un JLabel con un icono transparente
                JLabel imgLabel = new JLabel(scaledIcon);
                imgLabel.setOpaque(false); // Hace que el JLabel sea transparente
                imgLabel.setHorizontalAlignment(JLabel.CENTER); // Centra la imagen horizontalmente
                imgLabel.setVerticalAlignment(JLabel.CENTER); // Centra la imagen verticalmente

                // Agrega el JLabel al JPanel
                equiposPanel.add(imgLabel, BorderLayout.CENTER);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //-----------------JUGADORES------------------------
        else if (contents[currentContentIndex].equals("Jugadores")) {
            JPanel jugadoresPanel = new JPanel(new BorderLayout());
            jugadoresPanel.setBounds(230, 120, 629, 379);
            jugadoresPanel.setOpaque(false); // Hace que el fondo del JPanel sea transparente
            layeredPane.add(jugadoresPanel, JLayeredPane.PALETTE_LAYER);

            // Agregar el botón solo si estamos en Jugadores
            layeredPane.add(jugadoresButton, JLayeredPane.PALETTE_LAYER);

            try {
                // Carga la imagen de jugadores utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
                ImageIcon jugadoresIcon = new ImageIcon(getClass().getResource("imagenes\\jugadores.png"));

                int scaledWidth = 635; // Ancho deseado
                int scaledHeight = 635; // Alto deseado
                Image scaledImage = jugadoresIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Crea un JLabel con un icono transparente
                JLabel imgLabel = new JLabel(scaledIcon);
                imgLabel.setOpaque(false); // Hace que el JLabel sea transparente
                imgLabel.setHorizontalAlignment(JLabel.CENTER); // Centra la imagen horizontalmente
                imgLabel.setVerticalAlignment(JLabel.CENTER); // Centra la imagen verticalmente

                // Agrega el JLabel al JPanel
                jugadoresPanel.add(imgLabel, BorderLayout.CENTER);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //-----------------ESTADISTICAS------------------------
        else if (contents[currentContentIndex].equals("Estadísticas")) {
            JPanel estadisticasPanel = new JPanel(new BorderLayout());
            estadisticasPanel.setBounds(230, 120, 629, 379);
            estadisticasPanel.setOpaque(false); // Hace que el fondo del JPanel sea transparente
            layeredPane.add(estadisticasPanel, JLayeredPane.PALETTE_LAYER);

            // Agregar el botón solo si estamos en Estadísticas
            layeredPane.add(estadisticasButton, JLayeredPane.PALETTE_LAYER);

            try {
                // Carga la imagen de estadísticas utilizando la ruta relativa al proyecto (puede variar según cómo esté organizado tu proyecto)
                ImageIcon estadisticasIcon = new ImageIcon(getClass().getResource("imagenes\\\\consultas.png"));

                int scaledWidth = 650; // Ancho deseado
                int scaledHeight = 650; // Alto deseado
                Image scaledImage = estadisticasIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Crea un JLabel con un icono transparente
                JLabel imgLabel = new JLabel(scaledIcon);
                imgLabel.setOpaque(false); // Hace que el JLabel sea transparente
                imgLabel.setHorizontalAlignment(JLabel.CENTER); // Centra la imagen horizontalmente
                imgLabel.setVerticalAlignment(JLabel.CENTER); // Centra la imagen verticalmente

                // Agrega el JLabel al JPanel
                estadisticasPanel.add(imgLabel, BorderLayout.CENTER);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private void previousContent() {
        currentContentIndex = (currentContentIndex - 1 + contents.length) % contents.length;
        showContent();
    }

    private void nextContent() {
        currentContentIndex = (currentContentIndex + 1) % contents.length;
        showContent();
    }
}
