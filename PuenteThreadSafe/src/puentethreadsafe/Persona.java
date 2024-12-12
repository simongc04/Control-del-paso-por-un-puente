package puentethreadsafe;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona implements Runnable {
    // Atributos.
    private final String idPersona;
    private final int tiempoPaso;
    private final int pesoPersona;
    private final Puente puente;
    
    // Constructor.
    public Persona(String idPersona, int tiempoPaso, int pesoPersona, Puente puente) {
        this.idPersona = idPersona;
        this.tiempoPaso = tiempoPaso;
        this.pesoPersona = pesoPersona;
        this.puente = puente;
    }

    // Getters y Setters.
    public String getIdPersona() {
        return idPersona;
    }
    public int getPesoPersona() {
        return pesoPersona;
    }
    public int getTiempoPaso() {
        return tiempoPaso;
    }    
    
    // M�todo run.
    @Override
    public void run() {
        System.out.printf(">>> La %s con %d kilos quiere cuzar en %d segundos.\n" +
                          "    Estado del puente: %d personas, %d kilos.\n",
                          idPersona, pesoPersona, tiempoPaso, puente.getNumeroPersonas(), puente.getPesoPersonas());
        // Entrar.
        try {
            puente.entrar(this);
        } catch (InterruptedException ex) {
        }
        // Cruzar.
        try {
            Thread.sleep(tiempoPaso * 100);
        } catch (InterruptedException ex) {
        }
        // Salir.
        puente.salir(this);
    }

}


