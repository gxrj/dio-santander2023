package estrutural.proxy;

/**
 * Esta classe é o proxy para a classe ContaBancaria
 */
public class CartaoDebito implements ServicoDePagamento {

    private ContaBancaria conta;

    public CartaoDebito( ContaBancaria conta ) {
        this.conta = conta;
    }

    @Override
    public void pagar( Double debito ) {
        // Algorítmo de validação para que o pagamento seja realizado 
        this.conta.pagar( debito ); // Trecho executado caso o cartão seja validado
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta( ContaBancaria conta ) {
        this.conta = conta;
    }
    
}
