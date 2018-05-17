package com.redv.com.ESports;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nucleo {
    public static ArrayList<Partido> OrdenarEnfrentamientos(ArrayList<Equipo> equipos) {

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

        ArrayList<Partido> calendario = new ArrayList<>();
        List<Partido> calendarioSinAleatorizar = new ArrayList<>();

        tamañoArrays = 0;

        for (int i = 0; i < torneo.size(); i++) {

            tamañoArrays = i;
            for (tamañoArrays = i + 1; tamañoArrays < torneo.size(); tamañoArrays++) {
                calendarioSinAleatorizar.add(new Partido(torneo.get(i), torneo.get(tamañoArrays)));
            }
        }

        //INICIALIZAMOS TAMAÑOARRAYS A CERO PARA PODER REUTILIZARLO PARA LA MISMA FUNCION DE ARRIBA CON EQUIPOS.
        tamañoArrays = 0;
        for (int numeroEquipos = 0; numeroEquipos < calendarioSinAleatorizar.size(); numeroEquipos++) {
            tamañoArrays++;
        }

        //AL IGUAL QUE AL DESORDENAR EQUIPOS, TRANSFERIMOS LOS DATOS DE CALENDARIOSINALEATORIZAR A CALENDARIO, EN DESORDEN TOTAL.
        for (int i = 0; i < tamañoArrays; i++) {
            int aleatorio = r.nextInt(calendarioSinAleatorizar.size());
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

    public static ArrayList<Equipo> PuntuarEquipos (ArrayList<Partido> enfrentamientos, ArrayList<Equipo> equiposParticipantes){


        for (int i = 0; i < enfrentamientos.size(); i++) {

            switch (enfrentamientos.get(i).getResultado()){
                case 1:

                    for (int j = 0; j < equiposParticipantes.size(); j++) {

                        if (equiposParticipantes.get(j).getNombre_equipo() == enfrentamientos.get(i).getEquipoA().getNombre_equipo()){
                            equiposParticipantes.get(j).setPuntuación(+1);
                        }else if(equiposParticipantes.get(j).getNombre_equipo() == enfrentamientos.get(i).getEquipoB().getNombre_equipo()){
                            equiposParticipantes.get(j).setPuntuación(-1);
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < equiposParticipantes.size(); j++) {

                        if (equiposParticipantes.get(j).getNombre_equipo() == enfrentamientos.get(i).getEquipoA().getNombre_equipo()){
                            equiposParticipantes.get(j).setPuntuación(-1);
                        }else if(equiposParticipantes.get(j).getNombre_equipo() == enfrentamientos.get(i).getEquipoB().getNombre_equipo()){
                            equiposParticipantes.get(j).setPuntuación(+1);
                        }
                    }
            }

        }

        return  equiposParticipantes;
    }
}
