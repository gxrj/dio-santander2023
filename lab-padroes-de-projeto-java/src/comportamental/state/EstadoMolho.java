package comportamental.state;

import static java.lang.System.out;

public class EstadoMolho extends Estado {

    public EstadoMolho( LavaRoupas lavaRoupas ) {
        super( lavaRoupas );
        executarMolho();
    }

    private void executarMolho() {
        out.println( "Colocando as roupas de molho" );
    }

    @Override
    public void avancarEstado() {
        lavaRoupas.setEstado( new EstadoLavagem( lavaRoupas ) );
    }
    
}