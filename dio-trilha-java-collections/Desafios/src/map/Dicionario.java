package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.TreeMap;
import java.util.Map;

class Dicionario {
	
	Map<String, String> palavras;
	
	Dicionario() {
		palavras = new TreeMap<>();
	}

	public static void main( String... args ) {
		var dicionario = new Dicionario();
		try{
			dicionario.adicionarPalavra( "Palavra 1", "Definicao 1" );
			dicionario.adicionarPalavra( "Palavra 2", "Definicao 2" );
			dicionario.adicionarPalavra( "Palavra 3", "Definicao 3" );
			dicionario.adicionarPalavra( "Palavra 4", "Definicao 4" );
			dicionario.adicionarPalavra( "Palavra 5", "Definicao 5" );
			dicionario.exibirPalavras();
			dicionario.pesquisarPorPalavra( "Palavra 3" );
			dicionario.pesquisarPorPalavra( "Palavra 6" );
			dicionario.removerPalavra( "Palavra 3" );
			dicionario.exibirPalavras();
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao ou remocao de palavras! \nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarPalavra( String palavra, String definicao ) throws RuntimeException {
		palavras.put( palavra, definicao );
	}
	
	void removerPalavra( String palavra ) throws RuntimeException {
		out.printf( "Removendo %s... ", palavra );
		out.println( palavras.remove( palavra ) != null ? "Palavra removida!" : "Palavra nao encontrada!");
	}
	
	void exibirPalavras() {
		if( palavras.size() == 0 ) {
			out.println( "Dicionario vazio!" );
			return;
		}
		out.printf( "%n%-20s %s %n", "Palavra", "Definicao" );
		palavras.entrySet()
				.stream()
				.map( el -> String.format( "%-20s %s", el.getKey(), el.getValue() ) )
				.forEach( out::println );
				
		out.println();
	}
	
	String pesquisarPorPalavra( String palavra ) {
		out.printf( "Pesquisando pela palavra \"" + palavra + "\"... " );
		var definicao = palavras.get( palavra );
		
		if( definicao == null ) {
			out.println( "Palavra nao encontrada!" );
			return null;
		}

		out.println( "Palavra encontrado! Definicao: " + definicao );
		return definicao;
	}
}