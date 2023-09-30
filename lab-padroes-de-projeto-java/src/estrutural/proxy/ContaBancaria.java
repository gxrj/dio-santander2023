package estrutural.proxy;

/* 
    Complexidade que envia o valor debitado para o destinatário foi ignorada para
    apenas demonstar a aplicação do pattern estrutural proxy.
*/
public class ContaBancaria implements ServicoDePagamento {
    private String titular;
    private Double saldo = 0d;

    public ContaBancaria( String titular, Double saldo ) {
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override
    public void pagar( Double debito ) {
        saldo -= debito;
        // Aciona serviço que envia o valor debitado para o destinatário
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo( Double valor ) {
        saldo = valor;
    }
}
