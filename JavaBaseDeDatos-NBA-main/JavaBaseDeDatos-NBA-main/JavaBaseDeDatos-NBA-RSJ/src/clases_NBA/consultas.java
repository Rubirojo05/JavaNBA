package clases_NBA;
import java.sql.*;
public class consultas {
	
	private String connectionUrl = "jdbc:oracle:thin:@localhost:1521/xe"; //conexion con la base de datos
	
	public consultas() {
		//constructor vacio
	}
	
	public void consulta_1() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.nombre, e.ciudad\r\n"
				+ "from jugadores j, equipos e\r\n"
				+ "where procedencia='Spain'\r\n"
				+ "        and\r\n"
				+ "        j.nombre_equipo=e.nombre");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("ciudad"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void consulta_2() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from equipos\r\n"
				+ "where substr(nombre,1,1)='H'\r\n"
				+ "        and\r\n"
				+ "        substr(nombre,-1,1)='s'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void consulta_3() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.puntos_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where j.nombre='Pau Gasol'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador");
		
		while (rs.next()) {
			System.out.println (rs.getString("puntos_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_4() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from equipos\r\n"
				+ "where conferencia='West'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_5() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where procedencia='Arizona'\r\n"
				+ "        and\r\n"
				+ "        peso>100\r\n"
				+ "        and\r\n"
				+ "        altura>='6-1'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_6() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.puntos_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre_equipo='Cavaliers'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador");
		
		while (rs.next()) {
			System.out.println (rs.getString("puntos_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_7() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where substr(nombre,3,1)='v'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_8() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.nombre,count(*) as \"CONTAR\"\r\n"
				+ "from equipos e, jugadores j\r\n"
				+ "where e.conferencia='West'\r\n"
				+ "        and\r\n"
				+ "        e.nombre=j.nombre_equipo\r\n"
				+ "group by e.nombre\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from equipos e, jugadores j\r\n"
				+ "                    where e.conferencia='West'\r\n"
				+ "                            and\r\n"
				+ "                            e.nombre=j.nombre_equipo\r\n"
				+ "                    group by e.nombre)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTAR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_9() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select count(*) AS CONTAR\r\n"
				+ "from jugadores\r\n"
				+ "where procedencia='Argentina'");
		
		while (rs.next()) {
			System.out.println (rs.getString("CONTAR"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_10() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select max(e.puntos_por_partido) AS MAXPUNTOS\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre='LeBron James'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador");
		
		while (rs.next()) {
			System.out.println (rs.getString("MAXPUNTOS"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_11() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.asistencias_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre='Jose Calderon'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador\r\n"
				+ "        and\r\n"
				+ "        e.temporada='07/08'");
		
		while (rs.next()) {
			System.out.println (rs.getString("asistencias_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_12() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.puntos_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre='LeBron James'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador\r\n"
				+ "        and\r\n"
				+ "        e.temporada in ('03/04','05/06' , '04/05')");
		
		while (rs.next()) {
			System.out.println (rs.getString("puntos_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_13() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.nombre, count(*) AS CANTIDAD\r\n"
				+ "from equipos e, jugadores j\r\n"
				+ "where conferencia='East'\r\n"
				+ "        and\r\n"
				+ "        e.nombre=j.nombre_equipo\r\n"
				+ "group by e.nombre\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from equipos e, jugadores j\r\n"
				+ "                    where conferencia='East'\r\n"
				+ "                            and\r\n"
				+ "                            e.nombre=j.nombre_equipo\r\n"
				+ "                    group by e.nombre)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("CANTIDAD"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_14() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.tapones_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where j.nombre_equipo='Trail Blazers'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador");
		
		while (rs.next()) {
			System.out.println (rs.getString("tapones_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_15() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select ROUND(avg(est.rebotes_por_partido)) as \"media de rebotes\"\r\n"
				+ "from equipos e, jugadores j, estadisticas est\r\n"
				+ "where e.conferencia='East'\r\n"
				+ "        and\r\n"
				+ "        e.nombre=j.nombre_equipo\r\n"
				+ "        and\r\n"
				+ "        est.jugador=j.codigo");
		
		while (rs.next()) {
			System.out.println (rs.getString("media de rebotes"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_16() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select est.rebotes_por_partido\r\n"
				+ "from equipos e, jugadores j, estadisticas est\r\n"
				+ "where ciudad='Los Angeles'\r\n"
				+ "        and\r\n"
				+ "        e.nombre=j.nombre_equipo\r\n"
				+ "        and\r\n"
				+ "        est.jugador=j.codigo");
		
		while (rs.next()) {
			System.out.println (rs.getString("rebotes_por_partido"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_17() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.nombre, count(*) AS CONTADOR\r\n"
				+ "from equipos e, jugadores j\r\n"
				+ "WHERE division='NorthWest'\r\n"
				+ "        and\r\n"
				+ "        e.nombre=j.nombre_equipo\r\n"
				+ "group by e.nombre\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from equipos e, jugadores j\r\n"
				+ "                    WHERE division='NorthWest'\r\n"
				+ "                            and\r\n"
				+ "                            e.nombre=j.nombre_equipo\r\n"
				+ "                    group by e.nombre)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_18() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select procedencia, count(*) AS CONTADOR\r\n"
				+ "from jugadores\r\n"
				+ "where procedencia in ('France', 'Spain')\r\n"
				+ "group by procedencia\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from jugadores\r\n"
				+ "                    where procedencia in ('France', 'Spain')\r\n"
				+ "                    group by procedencia)");
		
		while (rs.next()) {
			System.out.print (rs.getString("procedencia"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_19() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre_equipo, count(*) AS CONTADOR\r\n"
				+ "from jugadores\r\n"
				+ "where posicion='C'\r\n"
				+ "group by nombre_equipo\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from jugadores\r\n"
				+ "                    where posicion='C'\r\n"
				+ "                    group by nombre_equipo)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre_equipo"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_20() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select MAX(altura) AS MAXALTURA\r\n"
				+ "from jugadores\r\n"
				+ "where posicion='C'");
		
		while (rs.next()) {
			System.out.println (rs.getString("MAXALTURA"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_21() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select peso as \"peso lb\", round(peso/2.2) as \"peso kg\"\r\n"
				+ "from jugadores\r\n"
				+ "where posicion='C'\r\n"
				+ "        and\r\n"
				+ "        altura = (select max(altura)\r\n"
				+ "                    from jugadores\r\n"
				+ "                    where posicion='C')");
		
		while (rs.next()) {
			System.out.print (rs.getString("peso lb"));
			System.out.print (" | ");
			System.out.print (rs.getString("peso kg"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_22() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select count(*) AS CONTADOR\r\n"
				+ "from jugadores\r\n"
				+ "where substr(nombre, 1,1)='Y'");
		
		while (rs.next()) {
			System.out.println (rs.getString("CONTADOR"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_23() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where codigo in (select jugador\r\n"
				+ "                    from estadisticas\r\n"
				+ "                    where puntos_por_partido=0)");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_24() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.division, count(*) AS CONTADOR\r\n"
				+ "from equipos e, jugadores j\r\n"
				+ "where e.nombre=j.nombre_equipo\r\n"
				+ "group by division\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from equipos e, jugadores j\r\n"
				+ "                    where e.nombre=j.nombre_equipo\r\n"
				+ "                    group by division)");
		
		while (rs.next()) {
			System.out.print (rs.getString("division"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void consulta_25() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select round(avg(peso)) as \"media peso lb\", round(avg(peso/2.2)) as \"media peso kg\"\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Raptors'");
		
		while (rs.next()) {
			System.out.print (rs.getString("media peso lb"));
			System.out.print (" | ");
			System.out.print (rs.getString("media peso kg"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_26() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre||'('||(nombre_equipo)||')' as \"Nombre(Equipo)\"\r\n"
				+ "from jugadores");
		
		while (rs.next()) {
			System.out.println (rs.getString("Nombre(Equipo)"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_27() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select min(puntos_por_partido) AS MINPUNTOS\r\n"
				+ "from estadisticas");
		
		while (rs.next()) {
			System.out.println (rs.getString("MINPUNTOS"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_28() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "order by nombre asc\r\n"
				+ "fetch first 10 row only");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_29() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.temporada\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre='Kobe Bryant'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador\r\n"
				+ "        and\r\n"
				+ "        e.puntos_por_partido = (select max(e.puntos_por_partido)\r\n"
				+ "                                from jugadores j, estadisticas e\r\n"
				+ "                                where nombre='Kobe Bryant'\r\n"
				+ "                                        and\r\n"
				+ "                                        j.codigo=e.jugador)");
		
		while (rs.next()) {
			System.out.println (rs.getString("temporada"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_30() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select e.nombre, count(*) AS CONTADOR\r\n"
				+ "from jugadores j, equipos e\r\n"
				+ "where posicion='G'\r\n"
				+ "        and\r\n"
				+ "        j.nombre_equipo=e.nombre\r\n"
				+ "        and\r\n"
				+ "        e.conferencia='East'\r\n"
				+ "group by e.nombre\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from jugadores j, equipos e\r\n"
				+ "                    where posicion='G'\r\n"
				+ "                            and\r\n"
				+ "                            j.nombre_equipo=e.nombre\r\n"
				+ "                            and\r\n"
				+ "                            e.conferencia='East'\r\n"
				+ "                    group by e.nombre)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_31() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select conferencia, count(*) AS CONTADOR\r\n"
				+ "from equipos\r\n"
				+ "group by conferencia\r\n"
				+ "having count(*) in (select count(*)\r\n"
				+ "                    from equipos\r\n"
				+ "                    group by conferencia)");
		
		while (rs.next()) {
			System.out.print (rs.getString("conferencia"));
			System.out.print (" | ");
			System.out.print (rs.getString("CONTADOR"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_32() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select DISTINCT division\r\n"
				+ "from equipos\r\n"
				+ "where conferencia='East'");
		
		while (rs.next()) {
			System.out.println (rs.getString("division"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_33() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select j.nombre, e.rebotes_por_partido\r\n"
				+ "from jugadores j, estadisticas e\r\n"
				+ "where nombre_equipo='Suns'\r\n"
				+ "        and\r\n"
				+ "        j.codigo=e.jugador\r\n"
				+ "        and\r\n"
				+ "        e.rebotes_por_partido=(select max(e.rebotes_por_partido)\r\n"
				+ "                                from jugadores j, estadisticas e\r\n"
				+ "                                where nombre_equipo='Suns'\r\n"
				+ "                                        and\r\n"
				+ "                                        j.codigo=e.jugador)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("rebotes_por_partido"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_34() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select j.nombre, e.puntos_por_partido\r\n"
				+ "from estadisticas e, jugadores j\r\n"
				+ "where j.codigo=e.jugador\r\n"
				+ "        and\r\n"
				+ "        e.puntos_por_partido=(select max(puntos_por_partido)\r\n"
				+ "                                from estadisticas)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("puntos_por_partido"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_35() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre, LENGTH(nombre) AS LONGITUD\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Grizzlies'");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("LONGITUD"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_36() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre, LENGTH(nombre) AS LONGITUD, ciudad, LENGTH(ciudad) AS LONGI\r\n"
				+ "from equipos\r\n"
				+ "where LENGTH(nombre)=(select max(LENGTH(nombre))\r\n"
				+ "                        from equipos)");
		
		while (rs.next()) {
			System.out.print (rs.getString("nombre"));
			System.out.print (" | ");
			System.out.print (rs.getString("LONGITUD"));
			System.out.print (" | ");
			System.out.print (rs.getString("ciudad"));
			System.out.print (" | ");
			System.out.print (rs.getString("LONGI"));
			System.out.println (" ");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_37() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select count(*) AS CONTADOR\r\n"
				+ "from jugadores\r\n"
				+ "where posicion='G'\r\n"
				+ "        and\r\n"
				+ "        peso > (select min(peso)\r\n"
				+ "                from jugadores\r\n"
				+ "                where posicion='C')");
		
		while (rs.next()) {
			System.out.println (rs.getString("CONTADOR"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String consulta_jugadores(int num) {
	    try {
	        Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
	        Statement s = con.createStatement();
	        ResultSet rs = s.executeQuery("SELECT nombre FROM jugadores");

	        // Mover el cursor al registro num
	        for (int i = 1; i < num; i++) {
	            rs.next();
	        }

	        if (rs.next()) {
	            return rs.getString("nombre");
	        } else {
	            return "No se encontró el registro " + num;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return connectionUrl;
	}
	

	public void consulta_38() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Clippers'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_39() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Lakers'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_40() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Hawks'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_41() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Nets'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_42() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Hornets'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_43() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Bulls'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_44() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Cavaliers'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_45() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Mavericks'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_46() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Nuggets'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_47() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Pistons'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_48() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Warriors'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_49() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Rockets'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_50() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Pacers'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_51() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Grizzlies'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_52() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Heat'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_53() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Timberwolves'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_54() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Nuggets'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_55() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Knicks'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_56() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Spurs'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_57() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Magic'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_58() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='76ers'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_59() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Suns'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_60() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Bucks'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_61() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Kings'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void consulta_62() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Kings'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_63() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Jazz'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_64() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Bucks'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_65() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Supersonics'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consulta_66() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Raptors'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void consulta_67() {
		try {
		Connection con = DriverManager.getConnection(connectionUrl, "NBA", "NBA");
		
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery ("select nombre\r\n"
				+ "from jugadores\r\n"
				+ "where nombre_equipo='Raptors'");
		
		while (rs.next()) {
			System.out.println (rs.getString("nombre"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
