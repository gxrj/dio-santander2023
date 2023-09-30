package criacional.factorymethod;

/**
 * Complexidade removida com o objetivo de apresentar o Factory Method
 */
public class ContaDigital extends ContaBancaria {

    public ContaDigital( String titular, Double saldo ) {
        super( titular, saldo );
    }

    // métodos que executam funcionalidades exclusivas para ContaDigital 
}
