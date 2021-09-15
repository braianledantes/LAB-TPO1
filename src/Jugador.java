import caballero.ArmaduraDecorator;
import caballero.Caballero;
import utiles.Aleatorio;

public class Jugador implements Runnable {
    private String nombre;
    private final int turno;
    private int oro;
    private Caballero caballero;
    /**
     * Hay que inicializar el jugador oponente despues de crear este jugador.
     */
    private Jugador jugadorOponente = null;

    private Batalla batalla;
    private Tienda tienda;

    private boolean tieneEspeda = false, tieneArmadura = false, tieneEscudo = false;

    public Jugador(String nombre, int turno, int oro, Caballero caballero, Batalla batalla, Tienda tienda) {
        this.nombre = nombre;
        this.turno = turno;
        this.oro = oro;
        this.caballero = caballero;
        this.batalla = batalla;
        this.tienda = tienda;
    }

    public void setJugadorOponente(Jugador jugadorOponente) {
        this.jugadorOponente = jugadorOponente;
    }

    public void incrementarOro() {
        this.oro += Aleatorio.intAleatorio(30, 80);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 5) {
            System.out.println(this);
            batalla.resolverBatalla(turno, caballero, jugadorOponente.caballero);

            irATienda();

            jugadorOponente.incrementarOro();

            if (!this.caballero.estaVivo()) {
                tienda.curarCaballero(this.caballero);
                jugadorOponente.incrementarOro();
            }
            i++;
        }
    }

    public void irATienda() {
        switch (Aleatorio.intAleatorio(1, 3)) {
            case 1 -> {
                if (!tieneEspeda) {
                    this.caballero = tienda.comprarEspada(this.oro, this.caballero);
                    tieneEspeda = true;
                }
            }
            case 2 -> {
                if (!tieneEscudo) {
                    this.caballero = tienda.comprarEscudo(this.oro, this.caballero);
                    tieneEscudo = true;
                }
            }
            case 3 -> {
                if (!tieneArmadura) {
                    this.caballero = tienda.comprarArmadura(this.oro, this.caballero);
                    tieneArmadura = true;

                }
            }
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", oro=" + oro +
                '}';
    }
}
