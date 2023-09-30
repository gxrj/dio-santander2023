package comportamental.state;

import static java.lang.System.out;

public class EstadoLavagem extends Estado {

    public EstadoLavagem( LavaRoupas lavaRoupas ) {
        super( lavaRoupas );
        executarLavagem();
    }

    private void executarLavagem() {
        out.println( "Realizando a lavagem" );
    }

    @Override
    public void avancarEstado() {
        lavaRoupas.setEstado( new EstadoEnxague( lavaRoupas ) );
    }
    
}