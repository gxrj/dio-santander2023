import java.util.Scanner;

public class ContaTerminal {

    public static void main( String... args ) throws Exception {
        Scanner input = new Scanner( System.in );

        String agencia = obterAgencia( input );
        int numero = obterNumeroConta( input );
        String nomeCliente = obterNomeCliente( input );
        float saldo = obterSaldo( input );

        input.close();

        String msg = "Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta: %d. Seu saldo é de %.2f reais";
        msg += ( saldo > 0 ) ? " e já está disponível para saque. %n" : ". %n";

        System.out.printf( msg, nomeCliente, agencia, numero, saldo );
    }

    private static String obterAgencia( Scanner input ) {
        System.out.println( "Por favor, digite o número da Agência !" );
        String agencia;
        
        do {
            System.out.print( "Agência (apenas números e hífen caso possua dígito): " );
            agencia = input.nextLine();
        } while( !agencia.matches( "[0-9-]*" ) || agencia.length() == 0 );

        return agencia;
    }
    
    private static int obterNumeroConta( Scanner input ) {
        System.out.println( "Por favor, digite o número da Conta !" );
        int numero = 0;
        boolean excecaoDetectada;

        do{
            try{
                System.out.print( "Número da conta: " );
                numero = Integer.parseInt( input.nextLine() );
                excecaoDetectada = false;
            }
            catch( Exception e ) {
                System.err.println( "Inválido, use apenas números" );
                excecaoDetectada = true;
            }
        } while( excecaoDetectada );

        numero = ( numero < 0 ) ? -numero : numero;  
        
        return numero;
    }

    private static String obterNomeCliente( Scanner input ) {
        System.out.println( "Por favor, informe o Nome do Titular !" );
        String nomeCliente;

        do {
            System.out.print( "Nome do titular(apenas letras são permitidas): " );
            nomeCliente = input.nextLine();
        } while( !nomeCliente.matches( "[A-Za-z\\s]*" ) || nomeCliente.length() == 0 );

        return nomeCliente;
    }

    private static float obterSaldo( Scanner input ) {
        
        System.out.println( "Por favor, informe o valor do Saldo !" );
        float saldo = 0;
        boolean excecaoDetectada;

        do {
            try{
                System.out.print( "Saldo: " );
                saldo = Float.parseFloat( input.nextLine().replace( ",", "." ) ); 
                excecaoDetectada = false;
            }
            catch( Exception e ) {
                System.err.println( "Inválido, use vírgulas para valores decimais" );
                excecaoDetectada = true;
            }
        } while( excecaoDetectada );

        return saldo;
    }

}