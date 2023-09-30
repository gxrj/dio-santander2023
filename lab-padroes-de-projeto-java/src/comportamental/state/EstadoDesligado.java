package comportamental.state;

import static java.lang.System.out;

public class EstadoDesligado extends Estado {

    public EstadoDesligado( LavaRoupas lavaRoupas ) {
        super( lavaRoupas );
        out.println( "Lava roupas desligado" );
    }

    @Override
    public void avancarEstado() {
        lavaRoupas.setEstado( new EstadoMolho( lavaRoupas ) );
    }
    
}
