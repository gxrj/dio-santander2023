import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class SomaNumeros {
    
    List<Integer> numeros;
	
	SomaNumeros() {
		numeros = new ArrayList<>();
	}
	
	public static void main( String... args ) {
		var somatorio = new SomaNumeros();
		List.of( 10, 8, 9, 1, 4, 2, 3, 5, 7, 6 )
			.forEach( num -> somatorio.adicionarNumero( num ) );
		
		out.println( "Numeros: " + somatorio.numeros +"\nSoma:" + somatorio.calcularSoma() );
		
		try {
			out.println( "Maior numero: " + somatorio.encontrarMaiorNumero() );
			out.println( "Menor numero: " + somatorio.encontrarMenorNumero() );
		} catch( ClassCastException e ) {
			err.println( "Erro, lista não homogenea!" );
		}
		catch( NoSuchElementException e ) {
			err.println( "Erro, lista vazia!" );
		}
		
		Collections.sort( somatorio.numeros );
		out.println( "Numeros ordenados: " + somatorio.exibirNumeros() );
	}
	
	void adicionarNumero( int numero ) {
		try{
			numeros.add( numero );
		} catch ( RuntimeException e ) {
			err.println( "Falha na insercao de numero na lista! \nErro: " + e.getMessage() );
		}
	}
	
	int calcularSoma() {
		var total = 0;
		for( var numero : numeros )
			total += numero;
		return total;
	}
	
	int encontrarMaiorNumero() throws ClassCastException, NoSuchElementException {
		return Collections.max( numeros );
	}
	
	int encontrarMenorNumero() throws ClassCastException, NoSuchElementException {
		return Collections.min( numeros );
	}
	
	List<Integer> exibirNumeros() {
		return numeros;
	}
}
