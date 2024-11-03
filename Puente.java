package puentethreadsafe;

public class Puente {
    private static final int MAX_PERSONAS = 4;
    private static final int MAX_PESO = 300;
    private static final int MAX_PERSONAS_DIRECCION = 3;

    private int personasEnPuente = 0;
    private int pesoTotal = 0;
    private int personasNorte = 0;
    private int personasSur = 0;

    // Método para que una persona intente entrar al puente.
    public synchronized void entrar(Persona persona) throws InterruptedException {
        // Espera hasta que se cumplan las restricciones para entrar
        while (personasEnPuente >= MAX_PERSONAS ||
               pesoTotal + persona.getPesoPersona() > MAX_PESO ||
               (persona.getDireccion().equals("NORTE") && personasNorte >= MAX_PERSONAS_DIRECCION) ||
               (persona.getDireccion().equals("SUR") && personasSur >= MAX_PERSONAS_DIRECCION)) {
            System.out.printf("%s espera.\n", persona.getIdPersona());
            wait();  // La persona espera hasta que se cumplan las condiciones
        }

        // La persona puede entrar
        personasEnPuente++;
        pesoTotal += persona.getPesoPersona();
        if (persona.getDireccion().equals("NORTE")) {
            personasNorte++;
        } else {
            personasSur++;
        }
        System.out.printf("%s cruza.\n", persona.getIdPersona());
    }

    // Método para que una persona salga del puente.
    public synchronized void salir(Persona persona) {
        personasEnPuente--;
        pesoTotal -= persona.getPesoPersona();
        if (persona.getDireccion().equals("NORTE")) {
            personasNorte--;
        } else {
            personasSur--;
        }
        System.out.printf("%s sale.\n", persona.getIdPersona());
        notifyAll();  // Notifica a las personas en espera que revisen las condiciones
    }
}

