package puentethreadsafe;

import java.util.Random;

/*
    Sistema que controla el paso de personas por un puente:
        - Siempre pasan en la misma direcci�n.
        - No pueden pasar m�s de tres personas a la vez.
        - No puede haber m�s de 200 kg de peso en ning�n momento.
        - El tiempo entre la llegada de dos personas es aleatorio entre 1 y 30 segundos.
        - El tiempo en atravesar el puente es aleatorio, entre 10 y 50 segundos.
        - Las personas tienen un peso aleatorio entre 40 y 120 kg.
*/
public class PuenteThreadSafe {

    public static void main(String[] args) {
        // Constantes.
        final int minimoTiempoLlegada = 1;
        final int maximoTiempoLlegada = 30;
        final int minimoTiempoPaso = 10;
        final int maximoTiempoPaso = 50;
        final int minimoPesoPersona = 40;
        final int maximoPesoPersona = 120;
        
        // Variables.
        final Puente puente = new Puente();
        int tiempoLlegada = 0;
        int tiempoPaso = 0;
        int pesoPersona = 0;
        String idPersona = "";
        
        // Bucle infinito creando personas para cruzar el puente.
        int numeroPersona = 0;
        while (true) {
            // Crear persona.
            numeroPersona ++;
            idPersona = "Persona " + numeroPersona;
            tiempoLlegada = numeroAleatorio(minimoTiempoLlegada, maximoTiempoLlegada);
            tiempoPaso = numeroAleatorio(minimoTiempoPaso, maximoTiempoPaso);
            pesoPersona = numeroAleatorio(minimoPesoPersona, maximoPesoPersona);
            System.out.printf("La %s llegara en %d segundos, pesa %d kilos y tardara en cruzar %d segundos.\n",
                    idPersona, tiempoLlegada, pesoPersona, tiempoPaso);
            Thread hiloPersona = new Thread(new Persona(idPersona, tiempoPaso, pesoPersona, puente));
            // Esperar a que llegue.
            try {
                // Esperar un tiempo.
                Thread.sleep(tiempoLlegada * 100);
            } catch (InterruptedException ex) {
            }
            // Cruzar.
            hiloPersona.start();
        }
    }
    
    public static int numeroAleatorio(int valorMinimo, int valorMaximo) {
        Random r = new Random();
        return valorMinimo + r.nextInt(valorMaximo - valorMinimo + 1);
    }
}
    
