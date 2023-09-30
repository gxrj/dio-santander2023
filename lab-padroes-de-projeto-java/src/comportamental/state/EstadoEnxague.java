package comportamental.state;

import static java.lang.System.out;

public class EstadoEnxague extends Estado {

    public EstadoEnxague( LavaRoupas lavaRoupas ) {
        super( lavaRoupas );
        executarEnxague();
    }

    private void executarEnxague() {
        out.println( "Enxaguando as roupas" );
    }

    @Override
    public void avancarEstado() {
        lavaRoupas.setEstado( new EstadoCentrifugacao( lavaRoupas ) );
    }
    
}