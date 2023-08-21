import java.util.NoSuchElementException;
import java.util.Scanner;

public class Contador {
	public static void main( String[] args ) {
		Scanner terminal = new Scanner( System.in );

		int parametroUm = capturarParametro( terminal, "Digite o primeiro parâmetro" );
		int parametroDois = capturarParametro( terminal, "Digite o segundo parâmetro" );

		terminal.close();

		try {
			//chamando o método contendo a lógica de contagem
			contar( parametroUm, parametroDois );
		} 
        catch( ParametrosInvalidosException exception ) {
			//imprimir a mensagem: O segundo parâmetro deve ser maior que o primeiro
            System.out.println( "O segundo parâmetro deve ser maior que o primeiro" );
		}
		
	}

    static void contar( int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		//validar se parametroUm é MAIOR que parametroDois e lançar a exceção
		if( parametroUm > parametroDois ) 
            throw new ParametrosInvalidosException();

		int contagem = parametroDois - parametroUm;
		//realizar o for para imprimir os números com base na variável contagem
        for( int i = 1; i <= contagem; i++ ) 
            System.out.print( "Imprimindo o número " + i + "... \n" );
	}

    static int capturarParametro( Scanner terminal, String mensagemPrompt ) {
        int parametro = 0;
        boolean excecaoDetectada = false;
        do {
            try{
                System.out.println( mensagemPrompt );
                parametro = Integer.parseInt( terminal.nextLine() );
                excecaoDetectada = false;
            } 
            catch( NoSuchElementException | NumberFormatException e ) {
                if( e.getClass() == NoSuchElementException.class )
                    System.out.println( "Parâmetro vazio inválido!\nPor favor, tente novamente..." );
                else
                    System.out.println( "Parâmetro deve ser número inteiro!\nPor favor, tente novamente..." );
                
                excecaoDetectada = true;
            } 
            catch( Exception e ) {
                System.out.println( "Erro inexperado" );
                exibirStackTracePersonalizado( e );
                excecaoDetectada = true;
            }
        } while( excecaoDetectada );
        return parametro;
    }

    static void exibirStackTracePersonalizado( Exception e ) {
        StackTraceElement[] traceElements = e.getStackTrace();
        System.err.println( "Arquivo\tClasse\tMetodo\tLinha" );
        for( StackTraceElement el : traceElements ) 
            System.err.println( 
                el.getFileName() + "\t" + el.getClassName() + "\t" + 
                el.getMethodName() + "\t" + el.getLineNumber() );
    }
}