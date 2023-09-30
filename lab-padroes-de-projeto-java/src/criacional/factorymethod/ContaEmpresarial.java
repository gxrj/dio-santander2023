package criacional.factorymethod;

/**
 * Complexidade removida com o objetivo de apresentar o Factory Method
 */
public class ContaEmpresarial extends ContaBancaria {

    public ContaEmpresarial( String titular, Double saldo ) {
        super( titular, saldo );
    }

    // métodos que executam funcionalidades exclusivas para ContaEmpresarial 
}
