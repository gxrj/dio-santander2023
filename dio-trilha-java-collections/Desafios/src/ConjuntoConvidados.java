import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Set;
import java.util.TreeSet;

class ConjuntoConvidados {
	
	Set<Convidado> convidados;
	
	ConjuntoConvidados() {
		convidados = new TreeSet<>();
	}

	public static void main( String... args ) {
		var cerimonia = new ConjuntoConvidados();
		
		try {
			cerimonia.adicionarConvidado( "Maria", 10239 );
			cerimonia.adicionarConvidado( "Allan", 84030 );
			cerimonia.adicionarConvidado( "Jocimar", 24305 );
			cerimonia.adicionarConvidado( "Agata", 45086 );
			cerimonia.adicionarConvidado( "Sara", 50960 );
			cerimonia.adicionarConvidado( "Carol", 79491 );
			cerimonia.adicionarConvidado( "Arnald", 30295 );
			
			cerimonia.exibirConvidados();
			out.println( "\nQtd convidados: " + cerimonia.contarConvidados() );
			
			cerimonia.removerConvidadoPorCodigoConvite( 30295 );
			cerimonia.removerConvidadoPorCodigoConvite( 84030 );
			cerimonia.removerConvidadoPorCodigoConvite( 24305 );
			
			cerimonia.exibirConvidados();
			out.println( "\nQtd convidados: " + cerimonia.contarConvidados() );
			
			cerimonia.removerConvidadoPorCodigoConvite( 84030 );

		} catch( RuntimeException e ) {
			err.println( "Falha na insercao ou remocao de convidado no conjunto!\nErro: " + e.getMessage() );
		}
	}
	
	void adicionarConvidado( String nome, int codigoConvite ) throws RuntimeException {
		out.printf( "Adicionando %s... %n", nome );
		convidados.add( new Convidado( nome, codigoConvite ) );
	}
	
	void removerConvidadoPorCodigoConvite( int codigoConvite ) throws RuntimeException {
		out.printf( "Buscando convidado com codigo de convite: %d... ", codigoConvite );
		for( var convidado : convidados ) 
			if( convidado.codigoConvite() == codigoConvite ) {
				convidados.remove( convidado );
				out.printf( " ==>  %s foi removido(a) dos convidados!%n", convidado.nome() );
				return;
			}
		out.println( "Convidado nao encontrado!" );
	}
	
	int contarConvidados() {
		return convidados.size();
	}
	
	void exibirConvidados() {
		var iterator = convidados.iterator();
		out.println( 
			iterator.hasNext() ? 
				String.format( "%n%-20s %-7s", "Nome convidado", "Convite" ) 
				: "Nao ha convidado!" 
		);
		while( iterator.hasNext() ) {
			var convidado = iterator.next();
			out.printf( "%-20s %-7d %n", convidado.nome(), convidado.codigoConvite() );
		}
	}
	
	public record Convidado( String nome, int codigoConvite ) implements Comparable<Convidado> {
		@Override public int compareTo( Convidado convidado ) {
			var comparacaoPorNome = nome().compareTo( convidado.nome() );

			if( comparacaoPorNome != 0 ) 
				return comparacaoPorNome;

			return codigoConvite() - convidado.codigoConvite();
		}

		@Override public String toString() {
			return String.format( "%-5d - %-20s", codigoConvite, nome );
		}
	}
}