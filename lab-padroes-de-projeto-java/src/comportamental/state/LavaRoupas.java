package comportamental.state;

import static java.lang.System.out;

/**
 * De acordo com o padrão State esta classe é o Context
 */
public class LavaRoupas {
    
    private Estado estado;

    public LavaRoupas() {
        estado = new EstadoDesligado( this );
    }

    public LavaRoupas( Estado estado ) {
        this.estado = estado;
    }

    public void avancarEtapa() {
        out.println( "Avancando etapa" );
        estado.avancarEstado();
    }

    public void ligar() {
        if( estado instanceof EstadoDesligado ) {
            out.println( "Ligando o lava roupas" );
            estado = new EstadoMolho( this );
        }
    }

    public void desligar() {
        estado.desligar();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado( Estado estado ) {
        this.estado = estado;
    }    
}
