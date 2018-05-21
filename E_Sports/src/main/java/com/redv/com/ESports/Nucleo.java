package com.redv.com.ESports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nucleo {
    /**
     * author: Eric Mtz
     *
     * @param equipos
     * @return ArrayList<Calendario></> //Devuelve un array con todos los partidos que se jugarán en la temporada (En el orden en el que serán jugados)
     */
    public static ArrayList<Calendario> OrdenarEnfrentamientos(ArrayList<Equipo> equipos) {

        //GENERADOR DE NÚMEROS ALEATORIOS.
        Random r = new Random();

        //ARRAY DE EQUIPOS DESORDENADOR. (SE TRANSFIEREN DEL ARRAY "EQUIPOS" PERO GENERANDO UN ORDEN ALEATORIO)
        List<Equipo> torneo = new ArrayList<>();

        //TAMAÑO ARRAY (NO PODEMOS UTILIZAR EL .SIZE  PORQUE VAMOS BORRANDO LOS DATOS SEGÚN LOS COPIAMOS PARA AHORRAR ESPACIO)
        int tamañoArrays = 0;

        /*
          ESTE BUCLE REPASA TODAS LAS POSICIONES DEL ARRAY PARA SABER LA CANTIDAD DE DATOS ALMACENADOS.
          COMO YA DIJE EN EL ANTERIOR COMENTARIO EN ESTE CÓDIGO NO PODEMOS EMPLEAR EL ".SIZE"
         */

        for (int numeroEquipos = 0; numeroEquipos < equipos.size(); numeroEquipos++) {
            tamañoArrays++;
        }

        /*
          ESTA ES LA PARTE DEL CODIGO QUE ALEATORIZA LOS DATOS DEL ARRAY EQUIPOS Y LOS VA INTRODUCIENDO EN EL ARRAY TORNEO.
          AL MISMO TIEMPO LOS VA BORRANDO DEL ARRAY EQUIPOS DE FORMA QUE NO DUPLICAMOS LOS DATOS.
         */
        for (int i = 0; i < tamañoArrays; i++) {
            int aleatorio = r.nextInt(equipos.size());
            torneo.add(equipos.get(aleatorio));
            equipos.remove(aleatorio);
        }

        ArrayList<Calendario> calendario = new ArrayList<>();
        List<Calendario> calendarioSinAleatorizar = new ArrayList<>();

        tamañoArrays = 0;

        for (int i = 0; i < torneo.size(); i++) {
            tamañoArrays = i;
            for (tamañoArrays = i + 1; tamañoArrays < torneo.size(); tamañoArrays++) {
                calendarioSinAleatorizar.add(new Calendario(torneo.get(i), torneo.get(tamañoArrays)));
            }
        }

        //INICIALIZAMOS TAMAÑOARRAYS A CERO PARA PODER REUTILIZARLO PARA LA MISMA FUNCION DE ARRIBA CON EQUIPOS.
        tamañoArrays = 0;
        for (int numeroEquipos = 0; numeroEquipos < calendarioSinAleatorizar.size(); numeroEquipos++) {
            tamañoArrays++;
        }

        //fecha temporal (se debe hacer un cálculo aleatorio)
        Date fecha;

        //AL IGUAL QUE AL DESORDENAR EQUIPOS, TRANSFERIMOS LOS DATOS DE CALENDARIOSINALEATORIZAR A CALENDARIO, EN DESORDEN TOTAL.
        for (int i = 0; i < tamañoArrays; i++) {
            if (i % 2 == 0) {
                fecha = new Date(118, i, 9);
            } else {
                fecha = new Date(118, i, 18);
            }
            int aleatorio = r.nextInt(calendarioSinAleatorizar.size());
            calendarioSinAleatorizar.get(aleatorio).setJornada(i + 1);
            calendarioSinAleatorizar.get(aleatorio).setFecha(fecha);//DE AÑADIR LA FECHA SE AÑADIRÁ EN ESTA LÍNEA Y BAJO EL VALOR ALEATORIO COMO ESTÁ ESCRITO
            calendario.add(calendarioSinAleatorizar.get(aleatorio));
            calendarioSinAleatorizar.remove(aleatorio);
        }

        return calendario;
    }//Genera la temporada.




    /*
    Analisis de PuntuarEquipos
    Utilidad: Una vez guardadas las victorias/derrotas en el Array de Partidos, si llamamos a esta funcion actualizará el Array de Equipos añadiendole los puntos.

    ArrayList<Partido> "enfrentamientos" debe de salir de la última generación de "OrdenarEnfrentamientos" (Es decir la última temporada generada aleatoriamente)
    ArrayList<Equipo> "equiposParticipantes" debe de salir del mismo Array que se introduce a "OrdenarEnfrentamientos" con el orden de Equipo (Es decir la lista con todos los equipos que participarán en la temporada)

    RETURN: ArrayList<Equipos> Devolverá un ArrayList de equipos como el introducido al llamar a la función, pero con los datos de puntuación guardados.
     */

    public static ArrayList<Equipo> PuntuarEquipos(ArrayList<Calendario> enfrentamientos, ArrayList<Equipo> equiposParticipantes) {

        for (int i = 0; i < enfrentamientos.size(); i++) {

            switch (enfrentamientos.get(i).getResultado()) {
                case 1:

                    for (int j = 0; j < equiposParticipantes.size(); j++) {

                        if (equiposParticipantes.get(j).getNombre_equipo().equalsIgnoreCase(enfrentamientos.get(i).getEquipo1().getNombre_equipo())) {
                            equiposParticipantes.get(j).setPuntuación(+1);
                        } else if (equiposParticipantes.get(j).getNombre_equipo().equalsIgnoreCase(enfrentamientos.get(i).getEquipo2().getNombre_equipo())) {
                            equiposParticipantes.get(j).setPuntuación(-1);
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < equiposParticipantes.size(); j++) {

                        if (equiposParticipantes.get(j).getNombre_equipo().equalsIgnoreCase(enfrentamientos.get(i).getEquipo1().getNombre_equipo())) {
                            equiposParticipantes.get(j).setPuntuación(-1);
                        } else if (equiposParticipantes.get(j).getNombre_equipo().equalsIgnoreCase(enfrentamientos.get(i).getEquipo2().getNombre_equipo())) {
                            equiposParticipantes.get(j).setPuntuación(+1);
                        }
                    }
            }

        }

        return equiposParticipantes;
    }
}
