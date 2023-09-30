import static java.lang.System.out;

import comportamental.state.*;

import criacional.factorymethod.*;

import estrutural.proxy.ContaBancaria;
import estrutural.proxy.CartaoDebito;

public class Cliente {
    public static void main( String... args ) {
        // Factory Method
        out.println( "Testando componente que aplica o padrao FactoryMethod" );
        executarFactoryMethod();        

        // Proxy
        out.println( "\nTestando componente que aplica o padrao Proxy" );
        executarProxy();

        // State
        out.println( "\nTestando componente que aplica o padrao State" );
        executarState();

    }

    /**
     *  Instancia os geradores de cada tipo de conta em seguida cada gerador 
     * produz um tipo de conta, testa o tipo de cada conta e por fim imprime 
     * cada instância criada.
     */
    public static void executarFactoryMethod() {
        var geradorContaCorrente = new GeradorContaCorrente();
        var geradorContaDigital = new GeradorContaDigital();
        var geradorContaEmpresarial = new GeradorContaEmpresarial();

        var contaCorrente = geradorContaCorrente.criarConta( "Maria", 2000d );
        var contaDigital = geradorContaDigital.criarConta( "Joao", 750d );
        var contaEmpresarial = geradorContaEmpresarial.criarConta( "Sara", 100000d );

        assert contaCorrente instanceof ContaCorrente : "Erro";
        assert contaDigital instanceof ContaDigital : "Erro";
        assert contaEmpresarial instanceof ContaEmpresarial : "Erro";

        out.printf( 
            "%s contem titular: %s saldo: %.2f", 
            contaCorrente.toString(), 
            contaCorrente.getTitular(), 
            contaCorrente.getSaldo() );

        out.printf( 
            "%s contem titular: %s saldo: %.2f", 
            contaDigital.toString(), 
            contaDigital.getTitular(), 
            contaDigital.getSaldo() );

        out.printf( 
            "%s contem titular: %s saldo: %.2f", 
            contaEmpresarial.toString(), 
            contaEmpresarial.getTitular(), 
            contaEmpresarial.getSaldo() );
    }

    /**
     * Instancia a ContaBancaria como <i>ServiceImplementation</i>, instancia o CartaoDebito como <i>Proxy</i>,
     * imprime os valores da ContaBancaria, em seguida efetua a operação de pagamento através do 
     * CartaoDebito e por fim, ao exibir os valores da ContaBancaria, comprova que o débito no 
     * proxy afetou o saldo da ContaBancaria.
     */
    public static void executarProxy() {
        var conta = new ContaBancaria( "Jose", 397d );
        var cartaoDeDebito = new CartaoDebito( conta );

        out.printf( "Titular da conta: %s %nSaldo: %.2f %n", conta.getTitular(), conta.getSaldo() );
        
        cartaoDeDebito.pagar( 200d );

        out.printf( 
            "Debitando 200 rais no cartao de debito...\nTitular da conta: %s %nSaldo: %.2f %n", 
            conta.getTitular(), 
            conta.getSaldo() );
    }

    /**
     * Demonstra a manipulação dos estados de uma máquina de lavar roupas
     * que foi projetada usando o <i>pattern <b>State</b></i>
     */
    public static void executarState() {
        var maquinaLavaRoupas = new LavaRoupas(); // Estado: Desligada

        maquinaLavaRoupas.avancarEtapa(); // Estado: Molho
        maquinaLavaRoupas.avancarEtapa(); // Estado: Executando Lavagem
        maquinaLavaRoupas.avancarEtapa(); // Estado: Enxaguando
        maquinaLavaRoupas.avancarEtapa(); // Estado: Centrifugando
        maquinaLavaRoupas.avancarEtapa(); // Estado: Desligada automaticamente

        maquinaLavaRoupas.ligar(); // Estado: Molho

        maquinaLavaRoupas.setEstado( new EstadoLavagem( maquinaLavaRoupas ) ); // Pulando para a lavagem

        maquinaLavaRoupas.desligar(); // Desligando manualmente
    }
}
