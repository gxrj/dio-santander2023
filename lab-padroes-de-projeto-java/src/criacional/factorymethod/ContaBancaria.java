package criacional.factorymethod;

/**
 * Esta classe é o Product, suas classes filhas são Concrete Products
 */
public abstract class ContaBancaria {
    private String titular;
    private Double saldo;

    public ContaBancaria() {}
    public ContaBancaria( String titular, Double saldo ) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }
    public void setTitular( String titular ) {
        this.titular = titular;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo( Double saldo ) {
        this.saldo = saldo;
    }
}
