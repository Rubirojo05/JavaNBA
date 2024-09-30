-- Consulta 1
/*Equipo y ciudad de jugadores espaÃ±oles de la NBA*/
select e.nombre, e.ciudad
from jugadores j, equipos e
where procedencia='Spain'
        and
        j.nombre_equipo=e.nombre;


-- Consulta 2
/*Equipos que comiencen por H y terminen en S*/
select nombre
from equipos
where substr(nombre,1,1)='H'
        and
        substr(nombre,-1,1)='s';


-- Consulta 3
/*Puntos por partido de 'Pau Gasol' en toda su carrera*/
select e.puntos_por_partido
from jugadores j, estadisticas e
where j.nombre='Pau Gasol'
        and
        j.codigo=e.jugador;



-- Consulta 4
/*Equipos que hay en la conferencia oeste 'west*/
select nombre
from equipos
where conferencia='West';


-- Consulta 5
/*Jugadores de Arizona que pesan mÃ¡s de 100 kilos y midan mÃ¡s de 1.82m (6 pies o mÃ¡s)*/
select nombre
from jugadores
where procedencia='Arizona'
        and
        peso>100
        and
        altura>='6-1';

-- Consulta 6
/*Puntos por partido de los jugadores de los 'cavaliers'*/
select e.puntos_por_partido
from jugadores j, estadisticas e
where nombre_equipo='Cavaliers'
        and
        j.codigo=e.jugador;


-- Consulta 7
/*Jugadores cuya tercera letra de su nombre sea la v*/
select nombre
from jugadores
where substr(nombre,3,1)='v';


-- Consulta 8
/*NÃºmero de jugadores que tiene cada equipo de la conferencia oeste 'West'*/
select e.nombre,count(*) as "CONTAR"
from equipos e, jugadores j
where e.conferencia='West'
        and
        e.nombre=j.nombre_equipo
group by e.nombre
having count(*) in (select count(*)
                    from equipos e, jugadores j
                    where e.conferencia='West'
                            and
                            e.nombre=j.nombre_equipo
                    group by e.nombre);

-- Consulta 9
/*NÃºmero de jugadores argentinos de la NBA*/
select count(*) AS CONTAR
from jugadores
where procedencia='Argentina';


-- Consulta 10
/*MÃ¡xima media de puntos de 'LebrÃ³n James' en su carrera*/
select max(e.puntos_por_partido) AS MAXPUNTOS
from jugadores j, estadisticas e
where nombre='LeBron James'
        and
        j.codigo=e.jugador;


-- Consulta 11
/*Asistencia por partido de 'Jose Calderon' en la temporada '07/08'*/
select e.asistencias_por_partido
from jugadores j, estadisticas e
where nombre='Jose Calderon'
        and
        j.codigo=e.jugador
        and
        e.temporada='07/08';


-- Consulta 12
/*Puntos por partido de 'Lebron James' en las temporadas de 03/04 al 05/06*/
select e.puntos_por_partido
from jugadores j, estadisticas e
where nombre='LeBron James'
        and
        j.codigo=e.jugador
        and
        e.temporada in ('03/04','05/06' , '04/05');


-- Consulta 13
/*NÃºmero de jugadores que tiene cada equipo de la conferencia este 'East'*/
select e.nombre, count(*) AS CANTIDAD
from equipos e, jugadores j
where conferencia='East'
        and
        e.nombre=j.nombre_equipo
group by e.nombre
having count(*) in (select count(*)
                    from equipos e, jugadores j
                    where conferencia='East'
                            and
                            e.nombre=j.nombre_equipo
                    group by e.nombre);

-- Consulta 14
/*Tapones por partido de los jugadores de los 'Trail Blazers'*/
select e.tapones_por_partido
from jugadores j, estadisticas e
where j.nombre_equipo='Trail Blazers'
        and
        j.codigo=e.jugador;


-- Consulta 15
/*Media de rebotes de los jugadores de la conferencia Este 'East'*/
select ROUND(avg(est.rebotes_por_partido)) as "media de rebotes"
from equipos e, jugadores j, estadisticas est
where e.conferencia='East'
        and
        e.nombre=j.nombre_equipo
        and
        est.jugador=j.codigo;


-- Consulta 16
/*Rebotes por partido de los jugadores de los equipos de Los Angeles*/
select est.rebotes_por_partido
from equipos e, jugadores j, estadisticas est
where ciudad='Los Angeles'
        and
        e.nombre=j.nombre_equipo
        and
        est.jugador=j.codigo;


-- Consulta 17
/*Numero de jugadores que tiene cada equipo de la divisiÃ³n NorthWest*/
select e.nombre, count(*) AS CONTADOR
from equipos e, jugadores j
WHERE division='NorthWest'
        and
        e.nombre=j.nombre_equipo
group by e.nombre
having count(*) in (select count(*)
                    from equipos e, jugadores j
                    WHERE division='NorthWest'
                            and
                            e.nombre=j.nombre_equipo
                    group by e.nombre);


-- Consulta 18
/*NÃºmero de jugadores de EspaÃ±a y Francia en la NBA*/
select procedencia, count(*) AS CONTADOR
from jugadores
where procedencia in ('France', 'Spain')
group by procedencia
having count(*) in (select count(*)
                    from jugadores
                    where procedencia in ('France', 'Spain')
                    group by procedencia);


-- Consulta 19
/*NÃºmero de pivots 'C' que tiene cada equipo*/
select nombre_equipo, count(*) AS CONTADOR
from jugadores
where posicion='C'
group by nombre_equipo
having count(*) in (select count(*)
                    from jugadores
                    where posicion='C'
                    group by nombre_equipo);

-- Consulta 20
/*Â¿Cuanto mide el pivot mÃ¡s alto de la NBA?*/
select MAX(altura) AS MAXALTURA
from jugadores
where posicion='C';


-- Consulta 21
/*Â¿Cuanto pesa en libras y en Kilos el pivot mÃ¡s alto de la NBA?*/
select peso as "peso lb", round(peso/2.2) as "peso kg"
from jugadores
where posicion='C'
        and
        altura = (select max(altura)
                    from jugadores
                    where posicion='C');


-- Consulta 22
/*NÃºmero de Jugadores que empiezan por 'Y'*/
select count(*) AS CONTADOR
from jugadores
where substr(nombre, 1,1)='Y';


-- Consulta 23
/*Jugadores que no metieron ningÃºn punto en alguna temporada?*/
select nombre
from jugadores
where codigo in (select jugador
                    from estadisticas
                    where puntos_por_partido=0);



-- Consulta 24
/*NÃºmero total de jugadores de cada divisiÃ³n*/
select e.division, count(*) AS CONTADOR
from equipos e, jugadores j
where e.nombre=j.nombre_equipo
group by division
having count(*) in (select count(*)
                    from equipos e, jugadores j
                    where e.nombre=j.nombre_equipo
                    group by division);


-- Consulta 25
/*Peso medio en kilos y en libras de los jugadores de los 'Raptors'*/
select round(avg(peso)) as "media peso lb", round(avg(peso/2.2)) as "media peso kg"
from jugadores
where nombre_equipo='Raptors';


-- Consulta 26
/*Mostrar el listado de los jugadores con el formato Nombre(Equipo) en una sola columna*/
select nombre||'('||(nombre_equipo)||')' as "Nombre(Equipo)"
from jugadores;


-- Consulta 27
/*PuntuaciÃ³n mÃ¡s baja en un partido de la NBA*/
select min(puntos_por_partido) AS MINPUNTOS
from estadisticas;


-- Consulta 28
/*Primeros 10 jugadores por orden alfabÃ©tico*/
select nombre
from jugadores
order by nombre asc
fetch first 10 row only;


-- Consulta 29
/*Temporada con mÃ¡s puntos por partido de 'Kobe Bryant*/
select e.temporada
from jugadores j, estadisticas e
where nombre='Kobe Bryant'
        and
        j.codigo=e.jugador
        and
        e.puntos_por_partido = (select max(e.puntos_por_partido)
                                from jugadores j, estadisticas e
                                where nombre='Kobe Bryant'
                                        and
                                        j.codigo=e.jugador);


-- Consulta 30
/*NÃºmero de bases 'G' que tiene cada equipo de la conferencia este 'East*/
select e.nombre, count(*) AS CONTADOR
from jugadores j, equipos e
where posicion='G'
        and
        j.nombre_equipo=e.nombre
        and
        e.conferencia='East'
group by e.nombre
having count(*) in (select count(*)
                    from jugadores j, equipos e
                    where posicion='G'
                            and
                            j.nombre_equipo=e.nombre
                            and
                            e.conferencia='East'
                    group by e.nombre);


-- Consulta 31
/*NÃºmero de equipos que tiene cada conferencia*/
select conferencia, count(*) AS CONTADOR
from equipos
group by conferencia
having count(*) in (select count(*)
                    from equipos
                    group by conferencia);

-- Consulta 32
/*Nombre de las divisiones de la conferencia Este*/
select DISTINCT division
from equipos
where conferencia='East';

-- Consulta 33
/*MÃ¡ximo reboteador de los 'Suns'*/
select j.nombre, e.rebotes_por_partido
from jugadores j, estadisticas e
where nombre_equipo='Suns'
        and
        j.codigo=e.jugador
        and
        e.rebotes_por_partido=(select max(e.rebotes_por_partido)
                                from jugadores j, estadisticas e
                                where nombre_equipo='Suns'
                                        and
                                        j.codigo=e.jugador);

-- Consulta 34
/*MÃ¡ximo anotador de toda la base de datos en una temporada*/
select j.nombre, e.puntos_por_partido
from estadisticas e, jugadores j
where j.codigo=e.jugador
        and
        e.puntos_por_partido=(select max(puntos_por_partido)
                                from estadisticas);

-- Consulta 35
/*Sacar cuantas letras tiene el nombre de cada jugador de los 'grizzlies' (usar funcion LENGTH)*/
select nombre, LENGTH(nombre) AS LONGITUD
from jugadores
where nombre_equipo='Grizzlies';

-- Consulta 36
/*Â¿Cuantas letras tiene el equipo con nombre mÃ¡s largo de la NBA (Ciudad y Nombre)?*/
select nombre, LENGTH(nombre) AS LONGITUD, ciudad, LENGTH(ciudad) AS LONGI
from equipos
where LENGTH(nombre)=(select max(LENGTH(nombre))
                        from equipos);

-- Consulta 37
/*NÃºmero de bases que son mÃ¡s pesados que algÃºn pivot*/
select count(*) AS CONTADOR
from jugadores
where posicion='G'
        and
        peso > (select min(peso)
                from jugadores
                where posicion='C');
