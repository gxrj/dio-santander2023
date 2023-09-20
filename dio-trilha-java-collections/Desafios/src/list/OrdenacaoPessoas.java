package list;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenacaoPessoas {
	
	List<Pessoa> pessoas;
	
	OrdenacaoPessoas() {
		pessoas = new ArrayList<>();
	}
	
	public static void main( String... args ) {
		var grupo = new OrdenacaoPessoas();
		try {
			grupo.adicionarPessoa( "Maria", 12, 1.52 );
			grupo.adicionarPessoa( "Joana", 27, 1.63 );
			grupo.adicionarPessoa( "Agata", 11, 1.58 );
			grupo.adicionarPessoa( "Pedro", 47, 1.72 );
			grupo.adicionarPessoa( "Suzana", 23, 1.65 );
			grupo.adicionarPessoa( "Allan", 50, 1.85 );
		
			out.println( "Pessoas: "  );
			grupo.pessoas.forEach( out::println );

			grupo.ordenarPorIdade();
			out.println( "Pessoas ordenadas por idade: " );
			grupo.pessoas.forEach( out::println );

			grupo.ordenarPorAltura();
			out.println( "Pessoas ordenadas por altura: " );
			grupo.pessoas.forEach( out::println );

		} catch( RuntimeException e ) {
			err.println( "Erro: " + e.getMessage() );
		}
	}

	void adicionarPessoa( String nome, int idade, double altura ) throws RuntimeException {
		pessoas.add( new Pessoa( nome, idade, altura ) );
	}
	
	void ordenarPorIdade() {
		Collections.sort( pessoas );
	}
	void ordenarPorAltura() {
		Comparator<Pessoa> comparacaoPorAltura = ( p1, p2 ) -> Double.compare( p1.altura(), p2.altura() );
		pessoas.sort( comparacaoPorAltura );
	}

	record Pessoa( String nome, int idade, double altura ) implements Comparable<Pessoa> {
		@Override public int compareTo( Pessoa pessoa ) {
			return idade - pessoa.idade();
		}
		@Override public String toString() {
			return String.format( "%-15s %-3d anos\t %-2.2f metros", nome, idade, altura );
		}
	}
}