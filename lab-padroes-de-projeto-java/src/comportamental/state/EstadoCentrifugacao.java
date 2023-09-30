package comportamental.state;

import static java.lang.System.out;

public class EstadoCentrifugacao extends Estado {

    public EstadoCentrifugacao( LavaRoupas lavaRoupas ) {
        super( lavaRoupas );
        executarCentrifugacao();
    }

    private void executarCentrifugacao() {
        out.println( "Centrifugando as roupas" );
    }

    @Override
    public void avancarEstado() {
        desligar();
    }
    
}