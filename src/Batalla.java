import caballero.Caballero;

public class Batalla {
    private Turno turno;

    public Batalla(Turno turno) {
        this.turno = turno;
    }

    public synchronized void resolverBatalla(int turnoJugador, Caballero caballeroAtacante, Caballero caballeroAtacado) {
        try {
            while (!turno.esTurno(turnoJugador)) {
                wait();
            }
            //System.out.println(Thread.currentThread().getName() + " atacando, turno: " + turno.getTurnoActual() + ", turnoJugador: " + turnoJugador);
            System.out.println(caballeroAtacante.toString() + " VS " + caballeroAtacado.toString());
            caballeroAtacante.atacar(caballeroAtacado);

            turno.siguienteTurno();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

}
