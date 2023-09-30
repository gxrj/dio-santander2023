package comportamental.state;

public abstract class Estado {
    protected LavaRoupas lavaRoupas;

    public Estado( LavaRoupas lavaRoupas ) {
        this.lavaRoupas = lavaRoupas;
    }

    abstract void avancarEstado();

    void desligar() {
        if( !( lavaRoupas.getEstado() instanceof EstadoDesligado ) )
            lavaRoupas.setEstado( new EstadoDesligado( lavaRoupas ) );
    }
}
