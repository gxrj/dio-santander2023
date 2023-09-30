package criacional.factorymethod;

public class GeradorContaEmpresarial extends GeradorConta {

    @Override
    public ContaEmpresarial criarConta( String titular, Double saldo ) {
        return new ContaEmpresarial( titular, saldo );
    }
    
}
