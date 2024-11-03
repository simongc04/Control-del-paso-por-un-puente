package puentethreadsafe;

public class Persona implements Runnable {

    private final String idPersona;
    private final int tiempoPaso;
    private final int pesoPersona;
    private final Puente puente;
    private final String direccion;

    public Persona(String idPersona, int tiempoPaso, int pesoPersona, Puente puente, String direccion) {
        this.idPersona = idPersona;
        this.tiempoPaso = tiempoPaso;
        this.pesoPersona = pesoPersona;
        this.puente = puente;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        try {
            puente.entrar(this);  // Intenta entrar al puente
            Thread.sleep(tiempoPaso * 100);  // Simula el tiempo de cruce
            puente.salir(this);  // Sale del puente
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getters
    public int getPesoPersona() {
        return pesoPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdPersona() {
        return idPersona;
    }
}
