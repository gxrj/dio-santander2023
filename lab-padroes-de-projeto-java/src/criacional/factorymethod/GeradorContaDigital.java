package criacional.factorymethod;

public class GeradorContaDigital extends GeradorConta {

    @Override
    public ContaDigital criarConta( String titular, Double valor ) {
        return new ContaDigital( titular, valor );
    }

}
