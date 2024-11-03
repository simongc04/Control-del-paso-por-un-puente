package puentethreadsafe;

import java.util.Random;

public class PuenteThreadSafe {

    public static void main(String[] args) {
        final int MIN_TIEMPO_LLEGADA = 1;
        final int MAX_TIEMPO_LLEGADA = 30;
        final int MIN_TIEMPO_PASO = 10;
        final int MAX_TIEMPO_PASO = 50;
        final int MIN_PESO_PERSONA = 40;
        final int MAX_PESO_PERSONA = 120;

        Puente puente = new Puente();
        int numeroPersona = 0;

        // Bucle para generar personas 
        while (true) {
            numeroPersona++;
            String idPersona = "Persona" + numeroPersona;
            int tiempoPaso = numeroAleatorio(MIN_TIEMPO_PASO, MAX_TIEMPO_PASO);
            int pesoPersona = numeroAleatorio(MIN_PESO_PERSONA, MAX_PESO_PERSONA);
            String direccion = numeroPersona % 2 == 0 ? "NORTE" : "SUR";  // Alterna dirección NORTE y SUR

            System.out.printf("Creando %s (%s), peso: %d kg, tiempo paso: %d s.\n",
                    idPersona, direccion, pesoPersona, tiempoPaso);

            // Crear y arrancar el hilo de la persona
            Persona persona = new Persona(idPersona, tiempoPaso, pesoPersona, puente, direccion);
            new Thread(persona).start();

            // Esperar el tiempo de llegada de la siguiente persona
            try {
                Thread.sleep(numeroAleatorio(MIN_TIEMPO_LLEGADA, MAX_TIEMPO_LLEGADA) * 100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Método para generar un número aleatorio en el rango dado
    public static int numeroAleatorio(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }
}
