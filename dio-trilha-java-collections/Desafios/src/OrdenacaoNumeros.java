import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenacaoNumeros {
    List<Integer> numeros;

    OrdenacaoNumeros() {
        numeros = new ArrayList<>();
	}

    public static void main( String... args ) {
		var ordenacao = new OrdenacaoNumeros();
		List.of( 10, 8, 9, 1, 4, 2, 3, 5, 7, 6 )
			.forEach( num -> ordenacao.adicionarNumero( num ) );

        out.println( "Numeros em ordem de insercao: " + ordenacao.numeros );
        ordenacao.ordenarAscendente();
        out.println( "Numeros em ordem ascendente: " + ordenacao.numeros );
        ordenacao.ordenarDescendente();
        out.println( "Numeros em ordem descendente: " + ordenacao.numeros );

    }

    void adicionarNumero( int numero ) {
		try{
			numeros.add( numero );
		} catch ( RuntimeException e ) {
			err.println( "Falha na insercao de numero na lista! \nErro: " + e.getMessage() );
		}
	}

    void ordenarAscendente() { Collections.sort( numeros ); }
    void ordenarDescendente() { Collections.reverse( numeros ); }

}
