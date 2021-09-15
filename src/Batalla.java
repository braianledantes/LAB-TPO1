import caballero.Caballero;

public class Batalla {
    private Turno turno;

    class Resultado {
        private int oroJugador1;
        private int oroJugador2;

        public Resultado(int oroJugador1, int oroJugador2) {
            this.oroJugador1 = oroJugador1;
            this.oroJugador2 = oroJugador2;
        }

        public int getOroJugador1() {
            return oroJugador1;
        }

        public int getOroJugador2() {
            return oroJugador2;
        }
    }

    public Batalla(Turno turno) {
        this.turno = turno;
    }

    public synchronized void resolverBatalla(int turnoJugador, Caballero caballeroAtacante, Caballero caballeroAtacado) {
        try {
            while (!turno.esTurno(turnoJugador)) {
                wait();
            }
            caballeroAtacante.atacar(caballeroAtacado);
            System.out.println(Thread.currentThread().getName() + " atacando a: " + caballeroAtacado);
            //System.out.println(caballeroAtacante.toString() + " VS " + caballeroAtacado.toString());

            turno.siguienteTurno();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

}
