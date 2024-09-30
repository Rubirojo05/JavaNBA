package clases_NBA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.*;


public class consultasPredefinidas extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel originalMenuPanel; // Panel original del menú
    private JScrollPane scrollPane; // JScrollPane que contiene el panel del menú
    

    private String[] preguntasConsultas = {
        "1. Equipo y ciudad de jugadores españoles de la NBA. ",
        "2. Equipos que comiencen por H y terminen en S. ",
        "3. Puntos por partido de 'Pau Gasol' en toda su carrera. ",
        "4. Equipos que hay en la conferencia oeste 'west'",
        "5. Jugadores de Arizona que pesan más de 100 kilos y midan más de 1.82m (6 pies o más)",
        "6. Puntos por partido de los jugadores de los 'cavaliers'.",
        "7. Jugadores cuya tercera letra de su nombre sea la v",
        "8. Número de jugadores que tiene cada equipo de la conferencia oeste 'West",
        "9. Número de jugadores argentinos de la NBA",
        "10. Máxima media de puntos de 'Lebrón James' en su carrera",
        "11. Asistencia por partido de 'Jose Calderon' en la temporada '07/08'",
        "12. Puntos por partido de 'Lebron James' en las temporadas de 03/04 al 05/06",
        "13. Número de jugadores que tiene cada equipo de la conferencia este 'East",
        "14. Tapones por partido de los jugadores de los 'Trail Blazers'",
        "15. Media de rebotes de los jugadores de la conferencia Este 'East'",
        "16. Rebotes por partido de los jugadores de los equipos de Los Angeles",
        "17. Numero de jugadores que tiene cada equipo de la división NorthWest",
        "18. Número de jugadores de España y Francia en la NBA",
        "19. Número de pivots 'C' que tiene cada equipo",
        "20. ¿Cuanto mide el pivot más alto de la NBA?",
        "21. ¿Cuanto pesa en libras y en Kilos el pivot más alto de la NBA?",
        "22. Número de Jugadores que empiezan por 'Y'",
        "23. Jugadores que no metieron ningún punto en alguna temporada?",
        "24. Número total de jugadores de cada división",
        "25. Peso medio en kilos y en libras de los jugadores de los 'Raptors'",
        "26. Mostrar el listado de los jugadores con el formato Nombre(Equipo) en una sola columna",
        "27. Puntuación más baja en un partido de la NBA",
        "28. Primeros 10 jugadores por orden alfabético",
        "29. Temporada con más puntos por partido de 'Kobe Bryant",
        "30. Número de bases 'G' que tiene cada equipo de la conferencia este 'East",
        "31. Número de equipos que tiene cada conferencia",
        "32. Nombre de las divisiones de la conferencia Este",
        "33. Máximo reboteador de los 'Suns'",
        "34. Máximo anotador de toda la base de datos en una temporada",
        "35. Sacar cuantas letras tiene el nombre de cada jugador de los 'grizzlies' (usar funcion LENGTH)",
        "36. ¿Cuantas letras tiene el equipo con nombre más largo de la NBA (Ciudad y Nombre)?",
        "37. Número de bases que son más pesados que algún pivot",		
    };

 // Variable para almacenar la posición del scroll
    private int scrollPosition = 0;

    public consultasPredefinidas() {
        setTitle("Consultas NBA");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 100, 1100, 650);

        int buttonWidth = 230;
        int buttonHeight = 230;

        originalMenuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        originalMenuPanel.setBackground(Color.GRAY);
        int totalButtonHeight = 10 * (buttonHeight + 33);
        originalMenuPanel.setPreferredSize(new Dimension(buttonWidth, totalButtonHeight));

        scrollPane = new JScrollPane(originalMenuPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(buttonHeight + 30);

        add(scrollPane, BorderLayout.CENTER);

        for (int i = 1; i <= preguntasConsultas.length; i++) {
            JButton button = new JButton("Consulta " + i);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Guardar la posición del scroll antes de cambiar el contenido
                    scrollPosition = verticalScrollBar.getValue();

                    scrollPane.getViewport().setViewPosition(new Point(0, 0));

                    int index = Integer.parseInt(((JButton) e.getSource()).getText().split(" ")[1]) - 1;
                    String pregunta = preguntasConsultas[index];
                    
                    originalMenuPanel.removeAll();
                    originalMenuPanel.revalidate();
                    originalMenuPanel.repaint();
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                    JPanel newPanel = new JPanel(null);
                    newPanel.setPreferredSize(new Dimension(1000, 540));
                    newPanel.setBackground(Color.gray);
                    
                    //texto 1
                    JLabel label = new JLabel(pregunta);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.BOLD, 14));
                    newPanel.add(label);
                    
                    // Llamada "automatizada" de las consultas
                    String salidaMET = "";
                    consultas consul = new consultas(); // OBJETO CONSULTAS (LA CLASE CON 1 METODO POR CONSULTA)

                    try {
                    	String nombreMetodo;
                    	if (index==0) {
                    		nombreMetodo = "consulta_1";
						}else {
							nombreMetodo = "consulta_" + (index+1);
						}
                    	
                        Method metodo = consultas.class.getMethod(nombreMetodo);
                        // Capturar la salida del método en la variable salidaMET
                        salidaMET = invocarMetodoConSalida(metodo, consul);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    
                    
                  //texto 2
                    JLabel label2 = new JLabel("<html>" + salidaMET.replaceAll("\n", "<br>") + "</html>");//------------
                    label2.setForeground(Color.WHITE);
                    label2.setFont(new Font("Arial", Font.BOLD, 14));
                    label2.setVisible(false);

                 // Crear un JPanel que contenga label2 con un FlowLayout
                    JPanel label2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                 // Establecer el color de fondo del JPanel que contiene label2
                    label2Panel.setBackground(Color.gray);

                    label2Panel.add(label2);


                    // Obtener las dimensiones preferidas del contenido dentro del label2
                    Dimension contentSize = label2.getPreferredSize();

                    
                    
                    // Crear el JScrollPane y ajustar sus dimensiones al contenido
                    JScrollPane scrollPaneForLabel2 = new JScrollPane(label2Panel);
                    scrollPaneForLabel2.setPreferredSize(new Dimension(contentSize.width + 20, contentSize.height + 20)); // Ajustar con un pequeño margen extra
                 // Establecer el borde del JScrollPane como null para hacerlo invisible
                    scrollPaneForLabel2.setBorder(null);

                    

                    // Añadir el JScrollPane al panel
                    newPanel.add(scrollPaneForLabel2);


                    scrollPaneForLabel2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPaneForLabel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPaneForLabel2.setBounds(500 - 250, 270 - 150, 500, 300);
                    newPanel.add(scrollPaneForLabel2);

                    
                    
                    // Centrar el texto 1 y el texto 2 en el panel
                    Dimension size1 = label.getPreferredSize();
                    Dimension size2 = label2.getPreferredSize();
                    label.setBounds(500 - size1.width / 2, 270 - size1.height / 2, size1.width, size1.height);
                    label2.setBounds(500 - size2.width / 2, 270 - size2.height / 2, size2.width, size2.height);

                    JButton backButton = new JButton("back");
                    backButton.setBounds(900, 20, 80, 30);
                    backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            resetToInitialState();
                        }
                    });
                    newPanel.add(backButton);
                    
                    
                    JButton rightButton = new JButton(">");
                    rightButton.setBounds(960, 220, 30, 100);
                    newPanel.add(rightButton);

                    JButton leftButton = new JButton("<");
                    leftButton.setBounds(20, 220, 30, 100);
                    leftButton.setVisible(false); // Empieza invisible
                    newPanel.add(leftButton);
                    
                    
                    // CLICK derecho
                    rightButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            label.setVisible(false);
                            label2.setVisible(true);
                            rightButton.setVisible(false); // Oculta el botón derecho
                            leftButton.setVisible(true); // Muestra el botón izquierdo
                        }
                    });

                    // CLICK izquierdo
                    leftButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            label.setVisible(true);
                            label2.setVisible(false);
                            leftButton.setVisible(false); // Oculta el botón izquierdo
                            rightButton.setVisible(true); // Muestra el botón derecho
                        }
                    });


                    originalMenuPanel.add(newPanel);
                    originalMenuPanel.revalidate();
                    originalMenuPanel.repaint();
                }
            });

            originalMenuPanel.add(button);
        }
        
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
    }

    private void resetToInitialState() {
        scrollPane.getVerticalScrollBar().setValue(scrollPosition); // Restaurar la posición del scroll
        originalMenuPanel.removeAll(); // Limpiar el panel actual
        originalMenuPanel.revalidate(); // Revalidar el panel para reflejar los cambios
        originalMenuPanel.repaint(); // Repintar el panel
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Restaurar la política de la barra de desplazamiento
        for (int i = 1; i <= preguntasConsultas.length; i++) { // Recrear los botones del menú
            JButton button = new JButton("Consulta " + i);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setHorizontalTextPosition(JButton.CENTER);
            int buttonWidth = 230;
            int buttonHeight = 230;
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Guardar la posición del scroll antes de cambiar el contenido
                    scrollPosition = scrollPane.getVerticalScrollBar().getValue();
                    scrollPane.getViewport().setViewPosition(new Point(0, 0));
                    int index = Integer.parseInt(((JButton) e.getSource()).getText().split(" ")[1]) - 1;
                    String pregunta = preguntasConsultas[index];
                    originalMenuPanel.removeAll();
                    originalMenuPanel.revalidate();
                    originalMenuPanel.repaint();
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                    JPanel newPanel = new JPanel(null);
                    newPanel.setPreferredSize(new Dimension(1000, 540));
                    newPanel.setBackground(Color.gray);
                    JLabel label = new JLabel(pregunta);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Arial", Font.BOLD, 14));
                    newPanel.add(label);
                    // Llamada "automatizada" de las consultas
                    String salidaMET = "";
                    consultas consul = new consultas(); // OBJETO CONSULTAS (LA CLASE CON 1 METODO POR CONSULTA)
                    try {
                        String nombreMetodo;
                        if (index == 0) {
                            nombreMetodo = "consulta_1";
                        } else {
                            nombreMetodo = "consulta_" + (index + 1);
                        }
                        Method metodo = consultas.class.getMethod(nombreMetodo);
                        // Capturar la salida del método en la variable salidaMET
                        salidaMET = invocarMetodoConSalida(metodo, consul);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                  //texto 2
                    JLabel label2 = new JLabel("<html>" + salidaMET.replaceAll("\n", "<br>") + "</html>");//------------
                    label2.setForeground(Color.WHITE);
                    label2.setFont(new Font("Arial", Font.BOLD, 14));
                    label2.setVisible(false);

                 // Crear un JPanel que contenga label2 con un FlowLayout
                    JPanel label2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                 // Establecer el color de fondo del JPanel que contiene label2
                    label2Panel.setBackground(Color.gray);

                    label2Panel.add(label2);


                    // Obtener las dimensiones preferidas del contenido dentro del label2
                    Dimension contentSize = label2.getPreferredSize();

                    
                    
                    // Crear el JScrollPane y ajustar sus dimensiones al contenido
                    JScrollPane scrollPaneForLabel2 = new JScrollPane(label2Panel);
                    scrollPaneForLabel2.setPreferredSize(new Dimension(contentSize.width + 20, contentSize.height + 20)); // Ajustar con un pequeño margen extra
                 // Establecer el borde del JScrollPane como null para hacerlo invisible
                    scrollPaneForLabel2.setBorder(null);

                    

                    // Añadir el JScrollPane al panel
                    newPanel.add(scrollPaneForLabel2);


                    scrollPaneForLabel2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPaneForLabel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPaneForLabel2.setBounds(500 - 250, 270 - 150, 500, 300);
                    newPanel.add(scrollPaneForLabel2);
                    // Centrar el texto 1 y el texto 2 en el panel
                    Dimension size1 = label.getPreferredSize();
                    Dimension size2 = label2.getPreferredSize();
                    label.setBounds(500 - size1.width / 2, 270 - size1.height / 2, size1.width, size1.height);
                    label2.setBounds(500 - size2.width / 2, 270 - size2.height / 2, size2.width, size2.height);
                    JButton backButton = new JButton("back");
                    backButton.setBounds(900, 20, 80, 30);
                    backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            resetToInitialState();
                        }
                    });
                    newPanel.add(backButton);
                    
                    
                    JButton rightButton = new JButton(">");
                    rightButton.setBounds(960, 220, 30, 100);
                    newPanel.add(rightButton);

                    JButton leftButton = new JButton("<");
                    leftButton.setBounds(20, 220, 30, 100);
                    leftButton.setVisible(false); // Empieza invisible
                    newPanel.add(leftButton);

                    // CLICK derecho
                    rightButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            label.setVisible(false);
                            label2.setVisible(true);
                            rightButton.setVisible(false); // Oculta el botón derecho
                            leftButton.setVisible(true); // Muestra el botón izquierdo
                        }
                    });

                    // CLICK izquierdo
                    leftButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            label.setVisible(true);
                            label2.setVisible(false);
                            leftButton.setVisible(false); // Oculta el botón izquierdo
                            rightButton.setVisible(true); // Muestra el botón derecho
                        }
                    });

                    originalMenuPanel.add(newPanel);
                    originalMenuPanel.revalidate();
                    originalMenuPanel.repaint();
                }
            });
            originalMenuPanel.add(button);
        }
    }

    
    // Método para invocar un método y capturar su salida en un string
    private String invocarMetodoConSalida(Method metodo, Object objeto) throws IllegalAccessException, InvocationTargetException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // Invocar el método
        metodo.invoke(objeto);

        System.out.flush();
        System.setOut(oldOut);

        return baos.toString();
    }

    public static void main(String[] args) {
        consultasPredefinidas consultas = new consultasPredefinidas();
        consultas.setVisible(true);
    }
}
