package clases_NBA;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class equipos extends JFrame {

    private static final long serialVersionUID = 1L;

    public equipos() throws IOException {
        setTitle("Equipos de la NBA");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 100, 1100, 650);

        int buttonWidth = 230;
        int buttonHeight = 230;
        int imageWidth = 200;
        int imageHeight = 200;

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panel.setBackground(Color.GRAY);
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

        for (int i = 1; i <= 30; i++) {
            String imagePath = "logo_equipos/eq_" + i + ".png";
            InputStream imgStream = getClass().getResourceAsStream(imagePath);

            if (imgStream != null) {
                BufferedImage img = ImageIO.read(imgStream);
                ImageIcon originalIcon = new ImageIcon(img);

                Image originalImage = originalIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JButton button = new JButton(scaledIcon);
                button.setBorder(new LineBorder(Color.BLACK, 2));
                button.setBorderPainted(true);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                button.setFocusable(false);

                int teamId = i;
                button.addActionListener(new ActionListener() {
                    private int clickCount = 0;
                    private JPanel currentPanel = null;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickCount++;
                        handleButtonClick(teamId, button);
                    }

                    private void handleButtonClick(int teamId, JButton button) {
                        switch (clickCount) {
                            case 1:
                                showTeamInfo(teamId, button);
                                break;
                            case 2:
                                showAdditionalPanel(button, teamId);
                                break;
                            case 3:
                                resetButton(button);
                                break;
                            default:
                                break;
                        }
                    }

                    private void showTeamInfo(int teamId, JButton button) {
                        JTextPane infoTextPane = new JTextPane();
                        infoTextPane.setEditable(false);
                        try {
                            infoTextPane.setText(getTeamInfo(teamId));
                            applyStylesToDocument(infoTextPane);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }

                        JScrollPane scrollPane = new JScrollPane(infoTextPane);
                        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                        Dimension buttonSize = button.getSize();
                        Dimension scrollPaneSize = new Dimension(buttonSize.width, buttonSize.height);

                        scrollPane.setPreferredSize(scrollPaneSize);

                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.setPreferredSize(buttonSize);

                        popupMenu.add(scrollPane);
                        popupMenu.show(button, 0, 0);
                    }

                    private void showAdditionalPanel(JButton button, int teamId) {
                        if (currentPanel != null) {
                            return;
                        }

                        String salidaMET = "";
                        consultas consul = new consultas(); // OBJETO CONSULTAS (LA CLASE CON 1 METODO POR CONSULTA)

                        try {
                            String nombreMetodo = "consulta_" + (37+teamId);
                            Method metodo = consultas.class.getMethod(nombreMetodo);
                            // Capturar la salida del método en la variable salidaMET
                            salidaMET = invocarMetodoConSalida(metodo, consul);
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e1) {
                            e1.printStackTrace();
                            salidaMET = "Error al obtener información del equipo.";
                        }

                        JPanel panel = new JPanel();
                        panel.setPreferredSize(new Dimension(button.getWidth(), button.getHeight()));
                        JTextArea textArea = new JTextArea(salidaMET);
                        textArea.setEditable(false);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        scrollPane.setPreferredSize(new Dimension(button.getWidth(), button.getHeight()));
                        panel.add(scrollPane);

                        currentPanel = panel;

                        JPopupMenu popupMenu = new JPopupMenu();
                        popupMenu.setPreferredSize(new Dimension(button.getWidth(), button.getHeight()));
                        popupMenu.add(panel);

                        popupMenu.show(button, 0, 0);
                    }

                    private void resetButton(JButton button) {
                        clickCount = 0;
                        currentPanel = null;
                    }
                });

                panel.add(button);
            } else {
                System.err.println("Couldn't find file: " + imagePath);
                JButton button = new JButton("Equipo " + i);
                button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                panel.add(button);
            }
        }

        int totalButtonHeight = 9 * (buttonHeight) + 40;
        int preferredPanelHeight = totalButtonHeight;
        panel.setPreferredSize(new Dimension(buttonWidth, preferredPanelHeight));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.BLACK);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void applyStylesToDocument(JTextPane textPane) throws BadLocationException {
        StyledDocument doc = textPane.getStyledDocument();
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style boldStyle = doc.addStyle("bold", defaultStyle);
        StyleConstants.setBold(boldStyle, true);

        String[] lines = textPane.getText().split("\n");
        doc.remove(0, doc.getLength());
        for (String line : lines) {
            int colonIndex = line.indexOf(":");
            if (colonIndex != -1) {
                String key = line.substring(0, colonIndex + 1);
                String value = line.substring(colonIndex + 1);
                try {
                    doc.insertString(doc.getLength(), key, boldStyle);
                    doc.insertString(doc.getLength(), value + "\n", defaultStyle);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    doc.insertString(doc.getLength(), line + "\n", defaultStyle);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    public static void main(String[] args) throws IOException {
        equipos e = new equipos();
        e.setVisible(true);
    }
    
    // Obtener información del equipo según su ID
    private String getTeamInfo(int teamId) {
        switch (teamId) {
            case 1:
                return "Equipo: Los Angeles Clippers\n" +
                        "Nombres Anteriores: Buffalo Braves (1970–1978), San Diego Clippers (1978–1984)\n" +
                        "Campeonatos NBA: 0\n" +
                        "Finales NBA Perdidas: 0\n" +
                        "Conferencia Actual: Oeste\n" +
                        "División Actual: Pacífico\n" +
                        "Record Histórico Temporada Regular: 1,854–2,380 (.438)\n" +
                        "Record Histórico Playoffs: 149–236 (.387)\n" +
                        "Mejor Temporada: 2013-2014\n" +
                        "Peor Temporada: 1986-1987\n" +
                        "Veces Clasificado para Playoffs: 17\n" +
                        "Años en los que no Clasificó para Playoffs: 20\n" +
                        "Colores de las Camisetas: Azul, Rojo, Blanco\n" +
                        "Salón de la Fama (Entrenadores): Ninguno\n" +
                        "Salón de la Fama (Jugadores):Ninguno\n";
            case 2: return "Equipo: Los Angeles Lakers\\n" +
            "Nombres Anteriores: Minneapolis Lakers (1947–1960)\n" +
            "Campeonatos NBA: 17\n" +
            "Finales NBA Perdidas: 15\n" +
            "Conferencia Actual: Oeste\n" +
            "División Actual: Pacífico\n" +
            "Record Histórico Temporada Regular: 3,456–2,210 (.610)\n" +
            "Record Histórico Playoffs: 493–316 (.609)\n" +
            "Mejor Temporada: 1971-1972\n" +
            "Peor Temporada: 1957-1958\n" +
            "Veces Clasificado para Playoffs: 61\n" +
            "Años en los que no Clasificó para Playoffs: 5\n" +
            "Colores de las Camisetas: Púrpura, Oro\n" +
            "Salón de la Fama (Entrenadores): Phil Jackson, Pat Riley\n" +
            "Salón de la Fama (Jugadores): Magic Johnson, Kareem Abdul-Jabbar, Kobe Bryant\n";
case 3: return "Equipo: Atlanta Hawks\n" +
            "Nombres Anteriores: Tri-Cities Blackhawks (1946–1951), Milwaukee Hawks (1951–1955), St. Louis Hawks (1955–1968), Atlanta Hawks (1968–present)\n" +
            "Campeonatos NBA: 1\n" +
            "Finales NBA Perdidas: 4\n" +
            "Conferencia Actual: Este\n" +
            "División Actual: Sureste\n" +
            "Record Histórico Temporada Regular: 2,891–3,536 (.450)\n" +
            "Record Histórico Playoffs: 187–253 (.425)\n" +
            "Mejor Temporada: 2014-2015\n" +
            "Peor Temporada: 2004-2005\n" +
            "Veces Clasificado para Playoffs: 49\n" +
            "Años en los que no Clasificó para Playoffs: 16\n" +
            "Colores de las Camisetas: Rojo, Blanco, Negro\n" +
            "Salón de la Fama (Entrenadores): Lenny Wilkens\n" +
            "Salón de la Fama (Jugadores): Bob Pettit, Dikembe Mutombo\n";
case 4: return "Equipo: Boston Celtics\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 17\n" +
"Finales NBA Perdidas: 4\n" +
"Conferencia Actual: Este\n" +
"División Actual: Atlántico\n" +
"Record Histórico Temporada Regular: 3,455–2,319 (.599)\n" +
"Record Histórico Playoffs: 389–279 (.582)\n" +
"Mejor Temporada: 1985-1986\n" +
"Peor Temporada: 1996-1997\n" +
"Veces Clasificado para Playoffs: 58\n" +
"Años en los que no Clasificó para Playoffs: 7\n" +
"Colores de las Camisetas: Verde, Blanco\n" +
"Salón de la Fama (Entrenadores): Red Auerbach, Bill Russell, Tom Heinsohn\n" +
"Salón de la Fama (Jugadores): Larry Bird, Bill Russell, Bob Cousy\n";
case 5: return "Equipo: Brooklyn Nets\n" +
"Nombres Anteriores: New Jersey Americans (1967–1968), New York Nets (1968–1977), New Jersey Nets (1977–2012)\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Este\n" +
"División Actual: Atlántico\n" +
"Record Histórico Temporada Regular: 1,469–1,754 (.456)\n" +
"Record Histórico Playoffs: 89–96 (.481)\n" +
"Mejor Temporada: 2001-2002 (New Jersey)\n" +
"Peor Temporada: 2009-2010 (New Jersey)\n" +
"Veces Clasificado para Playoffs: 23\n" +
"Años en los que no Clasificó para Playoffs: 20\n" +
"Colores de las Camisetas: Negro, Blanco\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 6: return "Equipo: Charlotte Hornets\n" +
"Nombres Anteriores: Charlotte Bobcats (2004–2014)\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 0\n" +
"Conferencia Actual: Este\n" +
"División Actual: Sureste\n" +
"Record Histórico Temporada Regular: 1,022–1,278 (.444)\n" +
"Record Histórico Playoffs: 17–31 (.354)\n" +
"Mejor Temporada: 2015-2016\n" +
"Peor Temporada: 2011-2012\n" +
"Veces Clasificado para Playoffs: 11\n" +
"Años en los que no Clasificó para Playoffs: 15\n" +
"Colores de las Camisetas: Teal, Morado, Blanco\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n" ;
case 7: return "Equipo: Chicago Bulls\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 6\n" +
"Finales NBA Perdidas: 4\n" +
"Conferencia Actual: Este\n" +
"División Actual: Central\n" +
"Record Histórico Temporada Regular: 2,241–2,220 (.503)\n" +
"Record Histórico Playoffs: 218–206 (.514)\n" +
"Mejor Temporada: 1995-1996\n" +
"Peor Temporada: 1998-1999\n" +
"Veces Clasificado para Playoffs: 35\n" +
"Años en los que no Clasificó para Playoffs: 11\n" +
"Colores de las Camisetas: Rojo, Negro\n" +
"Salón de la Fama (Entrenadores): Phil Jackson\n" +
"Salón de la Fama (Jugadores): Michael Jordan, Scottie Pippen\n";
case 8: return "Equipo: Cleveland Cavaliers\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 4\n" +
"Conferencia Actual: Este\n" +
"División Actual: Central\n" +
"Record Histórico Temporada Regular: 1,873–2,175 (.463)\n" +
"Record Histórico Playoffs: 142–177 (.445)\n" +
"Mejor Temporada: 2016-2017\n" +
"Peor Temporada: 2010-2011\n" +
"Veces Clasificado para Playoffs: 22\n" +
"Años en los que no Clasificó para Playoffs: 20\n" +
"Colores de las Camisetas: Vino, Oro, Negro\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): LeBron James\n";
case 9: return "Equipo: Dallas Mavericks\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Suroeste\n" +
"Record Histórico Temporada Regular: 1,704–1,684 (.503)\n" +
"Record Histórico Playoffs: 172–152 (.531)\n" +
"Mejor Temporada: 2010-2011\n" +
"Peor Temporada: 1992-1993\n" +
"Veces Clasificado para Playoffs: 22\n" +
"Años en los que no Clasificó para Playoffs: 9\n" +
"Colores de las Camisetas: Azul, Blanco, Negro\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): Dirk Nowitzki\n";
case 10: return "Equipo: Denver Nuggets\n" +
 "Nombres Anteriores: Denver Larks (1967), Denver Rockets (1967–1974)\n" +
 "Campeonatos NBA: 0\n" +
 "Finales NBA Perdidas: 0\n" +
 "Conferencia Actual: Oeste\n" +
 "División Actual: Noroeste\n" +
 "Record Histórico Temporada Regular: 2,048–2,329 (.467)\n" +
 "Record Histórico Playoffs: 137–195 (.413)\n" +
 "Mejor Temporada: 1974-1975 (Denver Rockets)\n" +
 "Peor Temporada: 1997-1998\n" +
 "Veces Clasificado para Playoffs: 38\n" +
 "Años en los que no Clasificó para Playoffs: 15\n" +
 "Colores de las Camisetas: Azul, Oro\n" +
 "Salón de la Fama (Entrenadores): ninguno\n" +
 "Salón de la Fama (Jugadores): ninguno\n";
case 11: return "Equipo: Detroit Pistons\n" +
 "Nombres Anteriores: Fort Wayne Pistons (1941–1957)\n" +
 "Campeonatos NBA: 3\n" +
 "Finales NBA Perdidas: 4\n" +
 "Conferencia Actual: Este\n" +
 "División Actual: Central\n" +
 "Record Histórico Temporada Regular: 2,705–2,843 (.488)\n" +
 "Record Histórico Playoffs: 189–212 (.471)\n" +
 "Mejor Temporada: 1988-1989\n" +
 "Peor Temporada: 1956-1957\n" +
 "Veces Clasificado para Playoffs: 43\n" +
 "Años en los que no Clasificó para Playoffs: 14\n" +
 "Colores de las Camisetas: Rojo, Azul, Blanco\n" +
 "Salón de la Fama (Entrenadores): Chuck Daly\n" +
 "Salón de la Fama (Jugadores): Isiah Thomas, Joe Dumars\n";
case 12: return "Equipo: Golden State Warriors\n" +
 "Nombres Anteriores: Philadelphia Warriors (1946–1962), San Francisco Warriors (1962–1971)\n" +
 "Campeonatos NBA: 7\n" +
 "Finales NBA Perdidas: 4\n" +
 "Conferencia Actual: Oeste\n" +
 "División Actual: Pacífico\n" +
 "Record Histórico Temporada Regular: 2,785–2,754 (.503)\n" +
 "Record Histórico Playoffs: 317–320 (.498)\n" +
 "Mejor Temporada: 2016-2017\n" +
 "Peor Temporada: 1952-1953 (Philadelphia)\n" +
 "Veces Clasificado para Playoffs: 36\n" +
 "Años en los que no Clasificó para Playoffs: 19\n" +
 "Colores de las Camisetas: Azul, Oro\n" +
 "Salón de la Fama (Entrenadores): Don Nelson\n" +
 "Salón de la Fama (Jugadores): Rick Barry, Wilt Chamberlain\n";
case 13: return "Equipo: Houston Rockets\n" +
"Nombres Anteriores: San Diego Rockets (1967–1971)\n" +
"Campeonatos NBA: 2\n" +
"Finales NBA Perdidas: 4\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Suroeste\n" +
"Record Histórico Temporada Regular: 2,312–2,376 (.493)\n" +
"Record Histórico Playoffs: 182–168 (.520)\n" +
"Mejor Temporada: 1993-1994\n" +
"Peor Temporada: 1967-1968\n" +
"Veces Clasificado para Playoffs: 34\n" +
"Años en los que no Clasificó para Playoffs: 10\n" +
"Colores de las Camisetas: Rojo, Blanco\n" +
"Salón de la Fama (Entrenadores): Rudy Tomjanovich\n" +
"Salón de la Fama (Jugadores): Hakeem Olajuwon\n";
case 14: return "Equipo: Indiana Pacers\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 1\n" +
"Conferencia Actual: Este\n" +
"División Actual: Central\n" +
"Record Histórico Temporada Regular: 1,771–1,844 (.490)\n" +
"Record Histórico Playoffs: 143–160 (.472)\n" +
"Mejor Temporada: 2013-2014\n" +
"Peor Temporada: 1980-1981\n" +
"Veces Clasificado para Playoffs: 34\n" +
"Años en los que no Clasificó para Playoffs: 10\n" +
"Colores de las Camisetas: Azul, Oro, Blanco\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 15: return "Equipo: Memphis Grizzlies\n" +
"Nombres Anteriores: Vancouver Grizzlies (1995–2001)\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 0\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Suroeste\n" +
"Record Histórico Temporada Regular: 917–1,201 (.433)\n" +
"Record Histórico Playoffs: 37–48 (.435)\n" +
"Mejor Temporada: 2012-2013\n" +
"Peor Temporada: 1995-1996 (Vancouver)\n" +
"Veces Clasificado para Playoffs: 11\n" +
"Años en los que no Clasificó para Playoffs: 20\n" +
"Colores de las Camisetas: Azul, Oro\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 16: return "Equipo: Miami Heat\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 3\n" +
"Finales NBA Perdidas: 3\n" +
"Conferencia Actual: Este\n" +
"División Actual: Sureste\n" +
"Record Histórico Temporada Regular: 1,276–1,256 (.504)\n" +
"Record Histórico Playoffs: 147–115 (.561)\n" +
"Mejor Temporada: 2012-2013\n" +
"Peor Temporada: 1988-1989\n" +
"Veces Clasificado para Playoffs: 24\n" +
"Años en los que no Clasificó para Playoffs: 10\n" +
"Colores de las Camisetas: Rojo, Negro, Blanco\n" +
"Salón de la Fama (Entrenadores): Pat Riley\n" +
"Salón de la Fama (Jugadores): Alonzo Mourning, Shaquille O'Neal, Dwyane Wade\n";
case 17: return "Equipo: Minnesota Timberwolves\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 0\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Noroeste\n" +
"Record Histórico Temporada Regular: 972–1,428 (.405)\n" +
"Record Histórico Playoffs: 21–35 (.375)\n" +
"Mejor Temporada: 2003-2004\n" +
"Peor Temporada: 2014-2015\n" +
"Veces Clasificado para Playoffs: 9\n" +
"Años en los que no Clasificó para Playoffs: 20\n" +
"Colores de las Camisetas: Azul, Verde\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 18: return "Equipo: New Orleans Pelicans\n" +
"Nombres Anteriores: New Orleans Hornets (2002–2013)\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 0\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Suroeste\n" +
"Record Histórico Temporada Regular: 743–883 (.457)\n" +
"Record Histórico Playoffs: 7–12 (.368)\n" +
"Mejor Temporada: 2007-2008 (New Orleans Hornets)\n" +
"Peor Temporada: 2012-2013 (New Orleans Hornets)\n" +
"Veces Clasificado para Playoffs: 8\n" +
"Años en los que no Clasificó para Playoffs: 11\n" +
"Colores de las Camisetas: Azul, Oro\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";

case 19: return "Equipo: New York Knicks\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 2\n" +
"Finales NBA Perdidas: 6\n" +
"Conferencia Actual: Este\n" +
"División Actual: Atlántico\n" +
"Record Histórico Temporada Regular: 2,803–2,902 (.491)\n" +
"Record Histórico Playoffs: 191–214 (.472)\n" +
"Mejor Temporada: 1969-1970\n" +
"Peor Temporada: 2014-2015\n" +
"Veces Clasificado para Playoffs: 42\n" +
"Años en los que no Clasificó para Playoffs: 18\n" +
"Colores de las Camisetas: Azul, Naranja, Blanco\n" +
"Salón de la Fama (Entrenadores): Red Holzman\n" +
"Salón de la Fama (Jugadores): Patrick Ewing, Walt Frazier\n";
case 20: return "Equipo: Oklahoma City Thunder\n" +
"Nombres Anteriores: Seattle SuperSonics (1967–2008)\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 3\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Noroeste\n" +
"Record Histórico Temporada Regular: 2,067–2,007 (.508)\n" +
"Record Histórico Playoffs: 135–125 (.519)\n" +
"Mejor Temporada: 2012-2013\n" +
"Peor Temporada: 2008-2009 (Seattle SuperSonics)\n" +
"Veces Clasificado para Playoffs: 21\n" +
"Años en los que no Clasificó para Playoffs: 5\n" +
"Colores de las Camisetas: Azul, Naranja\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): Gary Payton\n";
case 21: return "Equipo: Orlando Magic\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Este\n" +
"División Actual: Sureste\n" +
"Record Histórico Temporada Regular: 1,148–1,251 (.479)\n" +
"Record Histórico Playoffs: 52–68 (.433)\n" +
"Mejor Temporada: 1995-1996\n" +
"Peor Temporada: 1989-1990\n" +
"Veces Clasificado para Playoffs: 16\n" +
"Años en los que no Clasificó para Playoffs: 17\n" +
"Colores de las Camisetas: Azul, Negro, Blanco\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 22: return "Equipo: Philadelphia 76ers\n" +
"Nombres Anteriores: Syracuse Nationals (1946–1963)\n" +
"Campeonatos NBA: 3\n" +
"Finales NBA Perdidas: 6\n" +
"Conferencia Actual: Este\n" +
"División Actual: Atlántico\n" +
"Record Histórico Temporada Regular: 2,862–2,542 (.530)\n" +
"Record Histórico Playoffs: 183–193 (.487)\n" +
"Mejor Temporada: 1982-1983\n" +
"Peor Temporada: 1972-1973\n" +
"Veces Clasificado para Playoffs: 51\n" +
"Años en los que no Clasificó para Playoffs: 9\n" +
"Colores de las Camisetas: Azul, Rojo, Blanco\n" +
"Salón de la Fama (Entrenadores): Billy Cunningham, Alex Hannum\n" +
"Salón de la Fama (Jugadores): Allen Iverson, Julius Erving\n";
case 23: return "Equipo: Phoenix Suns\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Pacífico\n" +
"Record Histórico Temporada Regular: 2,319–1,972 (.541)\n" +
"Record Histórico Playoffs: 117–119 (.496)\n" +
"Mejor Temporada: 1992-1993\n" +
"Peor Temporada: 1968-1969\n" +
"Veces Clasificado para Playoffs: 30\n" +
"Años en los que no Clasificó para Playoffs: 8\n" +
"Colores de las Camisetas: Púrpura, Naranja\n" +
"Salón de la Fama (Entrenadores): Cotton Fitzsimmons\n" +
"Salón de la Fama (Jugadores): Charles Barkley, Steve Nash\n";
case 24: return "Equipo: Portland Trail Blazers\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Noroeste\n" +
"Record Histórico Temporada Regular: 2,127–1,960 (.520)\n" +
"Record Histórico Playoffs: 112–133 (.457)\n" +
"Mejor Temporada: 1990-1991\n" +
"Peor Temporada: 1971-1972\n" +
"Veces Clasificado para Playoffs: 36\n" +
"Años en los que no Clasificó para Playoffs: 9\n" +
"Colores de las Camisetas: Rojo, Negro\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): Bill Walton\n";
case 25: return "Equipo: Sacramento Kings\n" +
"Nombres Anteriores: Rochester Seagrams (1923–1945), Rochester Royals (1945–1957), Cincinnati Royals (1957–1972), Kansas City-Omaha Kings (1972–1975), Kansas City Kings (1975–1985), Sacramento Kings (1985–presente)\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 1\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Pacífico\n" +
"Record Histórico Temporada Regular: 2,735–3,327 (.451)\n" +
"Record Histórico Playoffs: 87–127 (.406)\n" +
"Mejor Temporada: 2001-2002\n" +
"Peor Temporada: 1957-1958 (Rochester Royals)\n" +
"Veces Clasificado para Playoffs: 30\n" +
"Años en los que no Clasificó para Playoffs: 15\n" +
"Colores de las Camisetas: Púrpura, Blanco\n" +
"Salón de la Fama (Entrenadores): ninguno\n" +
"Salón de la Fama (Jugadores): ninguno\n";
case 26: return "Equipo: San Antonio Spurs\n" +
"Nombres Anteriores: Dallas Chaparrals (1967–1970)\n" +
"Campeonatos NBA: 5\n" +
"Finales NBA Perdidas: 1\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Suroeste\n" +
"Record Histórico Temporada Regular: 2,228–1,554 (.589)\n" +
"Record Histórico Playoffs: 170–116 (.594)\n" +
"Mejor Temporada: 2015-2016\n" +
"Peor Temporada: 1973-1974 (Dallas Chaparrals)\n" +
"Veces Clasificado para Playoffs: 43\n" +
"Años en los que no Clasificó para Playoffs: 6\n" +
"Colores de las Camisetas: Negro, Plata\n" +
"Salón de la Fama (Entrenadores): Gregg Popovich\n" +
"Salón de la Fama (Jugadores): Tim Duncan, David Robinson, George Gervin\n";
case 27: return  "Equipo: Utah Jazz\n" +
"Nombres Anteriores: New Orleans Jazz (1974–1979)\n" +
"Campeonatos NBA: 0\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Noroeste\n" +
"Record Histórico Temporada Regular: 1,911–1,671 (.534)\n" +
"Record Histórico Playoffs: 129–153 (.457)\n" +
"Mejor Temporada: 1996-1997\n" +
"Peor Temporada: 1974-1975\n" +
"Veces Clasificado para Playoffs: 33\n" +
"Años en los que no Clasificó para Playoffs: 10\n" +
"Colores de las Camisetas: Azul, Verde\n" +
"Salón de la Fama (Entrenadores): Jerry Sloan\n" +
"Salón de la Fama (Jugadores): John Stockton, Karl Malone\n";
case 28: return "Equipo: Milwaukee Bucks\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 2\n" +
"Finales NBA Perdidas: 1\n" +
"Conferencia Actual: Este\n" +
"División Actual: Central\n" +
"Record Histórico Temporada Regular: 2,302–2,107 (.522)\n" +
"Record Histórico Playoffs: 152–141 (.519)\n" +
"Mejor Temporada: 1970-1971\n" +
"Peor Temporada: 2013-2014\n" +
"Veces Clasificado para Playoffs: 35\n" +
"Años en los que no Clasificó para Playoffs: 19\n" +
"Colores de las Camisetas: Verde, Blanco, Crema\n" +
"Salón de la Fama (Entrenadores): Don Nelson\n" +
"Salón de la Fama (Jugadores): Kareem Abdul-Jabbar, Oscar Robertson\n";
case 29: return "Equipo: Seattle SuperSonics\n" +
"Nombres Anteriores: Ninguno\n" +
"Campeonatos NBA: 1\n" +
"Finales NBA Perdidas: 2\n" +
"Conferencia Actual: Oeste\n" +
"División Actual: Noroeste\n" +
"Record Histórico Temporada Regular: 1,745–1,585 (.524)\n" +
"Record Histórico Playoffs: 107–110 (.493)\n" +
"Mejor Temporada: 1995-1996\n" +
"Peor Temporada: 2007-2008\n" +
"Veces Clasificado para Playoffs: 22\n" +
"Años en los que no Clasificó para Playoffs: 21\n" +
"Colores de las Camisetas: Verde, Dorado\n" +
"Salón de la Fama (Entrenadores): Lenny Wilkens\n" +
"Salón de la Fama (Jugadores): Gary Payton, Shawn Kemp\n";

case 30 : return "Equipo: Toronto Raptors\n" +
		"Nombres Anteriores: Ninguno\n" +
		"Campeonatos NBA: 1\n" +
		"Finales NBA Perdidas: 1\n" +
		"Conferencia Actual: Este\n" +
		"División Actual: Atlántico\n" +
		"Record Histórico Temporada Regular: 980–1,082 (.475)\n" +
		"Record Histórico Playoffs: 51–62 (.451)\n" +
		"Mejor Temporada: 2018-2019\n" +
		"Peor Temporada: 1997-1998\n" +
		"Veces Clasificado para Playoffs: 12\n" +
		"Años en los que no Clasificó para Playoffs: 8\n" +
		"Colores de las Camisetas: Rojo, Negro, Plata\n" +
		"Salón de la Fama (Entrenadores): ninguno\n" +
		"Salón de la Fama (Jugadores): ninguno\n";
           
            default:
                return "Equipo no encontrado";
        }
    }

}

