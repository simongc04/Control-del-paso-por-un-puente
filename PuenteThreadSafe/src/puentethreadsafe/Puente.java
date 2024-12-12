package puentethreadsafe;

/*
    Objeto compartido entre los hilos.
*/
public class Puente {
    // Constantes.
    private static final int MAXIMO_PERSONAS = 3;
    private static final int MAXIMO_PESO = 200;
    
    // Variables. 
    //N�mero de personas en el puente.
    private int numeroPersonas = 0;
    // Peso total de las personas en el puente.
    private int pesoPersonas = 0;

    // Constructor.
    public Puente() {    
    }

    // Getters y setters.
    synchronized public int getNumeroPersonas() {
        return numeroPersonas;
    }
    synchronized public int getPesoPersonas() {
        return pesoPersonas;
    }
    
    // Cruzar.
    public void entrar(Persona persona) throws InterruptedException {
        synchronized(this) {
            while ((numeroPersonas >= MAXIMO_PERSONAS) || (pesoPersonas + persona.getPesoPersona() > MAXIMO_PESO)) {
                 System.out.printf("*** %s debe esperar.\n", persona.getIdPersona());
                 this.wait();
            }
            this.numeroPersonas++;
            this.pesoPersonas += persona.getPesoPersona();
            System.out.printf("*** %s entra. Estado del puente: %d personas, %d kilos.\n", 
                    persona.getIdPersona(), numeroPersonas, pesoPersonas);
        }        
    }
    //Salir.
    public void salir(Persona persona) {
        synchronized(this) {
            this.numeroPersonas--;
            this.pesoPersonas -= persona.getPesoPersona();
            this.notifyAll();
            System.out.printf("*** %s sale. Estado del puente: %d personas, %d kilos.\n", 
                    persona.getIdPersona(), numeroPersonas, pesoPersonas);
        }
    }
}

