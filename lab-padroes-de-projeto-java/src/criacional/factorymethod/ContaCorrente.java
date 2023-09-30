package criacional.factorymethod;

/**
 * Complexidade removida com o objetivo de apresentar o Factory Method
 */
public class ContaCorrente extends ContaBancaria {

    public ContaCorrente( String titular, Double saldo ) {
        super( titular, saldo );
    }

    // métodos que executam funcionalidades exclusivas para ContaCorrente
}
