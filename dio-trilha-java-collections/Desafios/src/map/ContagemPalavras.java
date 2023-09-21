package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;

class ContagemPalavras {

	Map<String, Integer> entries;

	ContagemPalavras() {
		entries = new TreeMap<>();
	}
	public static void main( String... args ) {
		var contagem = new ContagemPalavras();

		try{
			contagem.exibirContagemPalavras();
			contagem.adicionarPalavra("Palavra 1", 2 );
			contagem.adicionarPalavra("Palavra 2", 7 );
			contagem.adicionarPalavra("Palavra 3", 6 );
			contagem.adicionarPalavra("Palavra 4", 8 );
			contagem.adicionarPalavra("Palavra 5", 3 );
			contagem.adicionarPalavra("Palavra 6", 1 );
			contagem.exibirContagemPalavras();
			contagem.removerPalavra( "Palavra 12" );
			contagem.removerPalavra( "Palavra 4" );
			contagem.exibirContagemPalavras();
			var palavraMaisUsada = contagem.encontrarPalavraMaisFrequente();

			out.println( 
				palavraMaisUsada == null ? 
					"Nao ha palavras!" : "Palavra mais usada: \"" + palavraMaisUsada.getKey() 
					+ "\"\nFrequencia: " + palavraMaisUsada.getValue() );

		} catch ( RuntimeException e ) {
			err.println( "Falha na adicao ou premocao de itens!\nDetalhes:" + e.getMessage() );
		}
	}

	void adicionarPalavra( String palavra, Integer contagem ) throws RuntimeException {
		out.println( "Inserindo \"" + palavra + "\" frequencia: " + contagem + "... " );
		entries.put( palavra, contagem );
	}

	void removerPalavra( String palavra ) throws RuntimeException {
		out.printf( "Buscando por \'%s\'... ", palavra );
		out.println( entries.remove( palavra ) != null ? " palavra removida!" : " palavra nao encontrada!" );
	}

	void exibirContagemPalavras() {

		if( entries.size() == 0 ) {
			out.println( "Nao ha palavras!" );
			return;
		}
		out.printf( "%n%-20s %s %n", "Palavra", "Frequencia" );
		entries.entrySet()
				.stream()
				.map( entry -> String.format( "%-20s %d", entry.getKey(), entry.getValue() ) )
				.forEach( out::println );
		out.println();
	}

	Entry<String, Integer> encontrarPalavraMaisFrequente() {
		if( entries.size() == 0 ) {
			out.println( "Nao ha palavras!" );
			return null;
		}

		var chave = "";

		for( var item : entries.keySet() )
			chave = chave.length() == 0 ? item : obterPalavraMaisFrequente( chave , item );

		return Map.entry( chave, entries.get( chave ) );
	}

	private String obterPalavraMaisFrequente( String palavra_1, String palavra_2 ) {
		return entries.get( palavra_1 ) >= entries.get( palavra_2 ) ? palavra_1 : palavra_2;
	}
}