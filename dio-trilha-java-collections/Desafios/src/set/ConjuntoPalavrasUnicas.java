package set;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Set;
import java.util.TreeSet;

class ConjuntoPalavrasUnicas {

	Set<String> palavras;
	
	ConjuntoPalavrasUnicas() {
		palavras = new TreeSet<>();
	}
	
	public static void main( String... args ) {
		var conjunto = new ConjuntoPalavrasUnicas();
		try{
			conjunto.adicionarPalavra( "Java" );
			conjunto.adicionarPalavra( "Javascript" );
			conjunto.adicionarPalavra( "C#" );
			conjunto.adicionarPalavra( "Dart" );
			conjunto.adicionarPalavra( "Jakarta" );
			conjunto.adicionarPalavra( "Spring" );
			conjunto.adicionarPalavra( "Vert.x" );
			
			conjunto.removerPalavra( "Dart" );
			out.println( conjunto.verificarPalavra( "Dart" ) ? "Palavra encontrada!" : "Palavra nao encontrada!" );
			
			conjunto.removerPalavra( "Javascript" );
			out.println( conjunto.verificarPalavra( "Javascript" ) ? "Palavra encontrada!" : "Palavra nao encontrada!" );
			
			conjunto.removerPalavra( "C#" );
			out.println( conjunto.verificarPalavra( "C#" ) ? "Palavra encontrada!" : "Palavra nao encontrada!" );
			
			out.println( conjunto.verificarPalavra( "Vert.x" ) ? "Palavra encontrada!" : "Palavra nao encontrada!" );
		} catch( ClassCastException | NullPointerException e ) {
			err.println( "Falha ao buscar elemento no conjunto!\n Detalhes do erro: " + e.getMessage() );
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao ou remocao do elemento no conjunto!\n Detalhes do erro: " + e.getMessage() );
		}				
	}
	
	void adicionarPalavra( String palavra ) throws RuntimeException {
		palavras.add( palavra );
	}

	void removerPalavra( String palavra ) throws RuntimeException {
		out.print( "Buscando a ocorrencia da palavra: " + palavra + " para remocao... " );
		for( var elemento : palavras )
			if( elemento.equalsIgnoreCase( palavra ) ) {
				palavras.remove( elemento );
				out.println( palavra + " foi removido!" );
				return;
			}
		out.println( "Nenhuma ocorrencia de " + palavra + " foi encontrada!" );
	}
	
	boolean verificarPalavra( String palavra ) throws ClassCastException, NullPointerException {
		out.print( "Buscando a ocorrencia da palavra: " + palavra + " no conjunto... " );
		return palavras.contains( palavra );
	}
	
	void exibirPalavrasUnicas() {
		out.print( "Lista de palavras:\n" );
		palavras.stream().forEach( out::println );
	}
}