package criacional.factorymethod;

public class GeradorContaCorrente extends GeradorConta {

    @Override
    public ContaCorrente criarConta( String titular, Double saldo ) {
        return new ContaCorrente( titular, saldo );
    }

}
