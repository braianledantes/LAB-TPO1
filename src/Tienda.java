import caballero.ArmaduraDecorator;
import caballero.Caballero;
import caballero.EscudoDecorator;
import caballero.EspadaDecorator;

/**
 * UNA CLASE que simula ser una tienda
 * TIENE RECURSOS ILIMITADOS POR LO CUAL NO VAN A SER SINCRONIZADOS
 * SE HACE CON UNA ESTRUCTURA DE CASES DONDE LAS DIFERENTES OPCIONES SON DIFERENTES OBJETOS
 * O ACCIONES QUE QUIERE CADA JUGADOR
 * LOS JUGADORES VAN A INTERACTUAR CON ESTA PARA COMPRAR LOS
 * ACCESORIOS (DECORADORES) PARA SUS CABALLEROS.
 * EN LA TIENDA TAMBIEN PODRIAN RESTAURAR O "REVIVIR" A SUS CABALLEROS MUERTOS.
 */
public class Tienda {
    private final int costoEspada;
    private final int costoArmadura;
    private final int costoEscudo;

    public Tienda() {
        this.costoEspada = 120;
        this.costoArmadura = 120;
        this.costoEscudo = 120;
    }

    public Caballero comprarEspada(int oroJugador, Caballero caballero) {
        if (costoEspada < oroJugador)
            return new EspadaDecorator(caballero);
        else
            return caballero;
    }

    public Caballero comprarEscudo(int oroJugador, Caballero caballero) {
        if (costoEscudo < oroJugador)
            return new EscudoDecorator(caballero);
        else
            return caballero;
    }

    public Caballero comprarArmadura(int oroJugador, Caballero caballero) {
        if (costoArmadura < oroJugador)
            return new ArmaduraDecorator(caballero);
        else
            return caballero;
    }

    public void curarCaballero(Caballero caballero) {
        caballero.curar();
    }

}
