package criacional.factorymethod;

/**
 *  Esta classe é o Creator, suas classes filhas são Concrete Creators
 */

public abstract class GeradorConta {
    
    abstract ContaBancaria criarConta( String titular, Double saldo );
}
