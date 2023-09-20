package set;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Set;
import java.util.TreeSet;

class AgendaContatos {

	Set<Contato> contatos;
	
	AgendaContatos() {
		contatos = new TreeSet<>();
	}
	
	public static void main( String... args ) {
		var agenda = new AgendaContatos();
		try {
			agenda.adicionarContato( "Maria", 9999 );
			agenda.adicionarContato( "Sara", 6509 );
			agenda.adicionarContato( "Agata", 4049 );
			agenda.adicionarContato( "Allan", 3945 );
			agenda.adicionarContato( "Carol", 2380 );
			agenda.adicionarContato( "Jocimar", 3400 );
			
			agenda.exibirContatos();
			
			var contato = "Maria";
			var numero = 5555;
			agenda.atualizarNumeroContato( contato, numero );

			agenda.exibirContatos();
			agenda.pesquisarPorNome( "Mario" );
			
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao de contatos!\nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarContato( String nome, int numero ) throws RuntimeException {
		contatos.add( new Contato( nome, numero ) ); 
	}

	void exibirContatos() {
		out.println( String.format( "%n%-20s - %s", "Contato", "Telefone" ) );
		contatos.stream()
				.map( el -> String.format( "%-20s - %d", el.getNome(), el.getNumero() ) )
				.forEach( out::println );
	}

	Contato pesquisarPorNome( String nome ) {
		var iterator = contatos.iterator();
		out.printf( "Pesquisando por: %s... ", nome );
		while( iterator.hasNext() ) {
			var contato = iterator.next();
			if( contato.getNome().equalsIgnoreCase( nome ) ) {
				out.printf( "Encontrado(a) %s, numero: %d%n", contato.getNome(), contato.getNumero() );
				return contato;
			}
		}

		out.println( "Contato nao encontrado!" );
		return null;
	}

	void atualizarNumeroContato( String nome, int novoNumero ) { 
		var contato = pesquisarPorNome( nome );

		if( contato != null && contato.getNome().equalsIgnoreCase( nome ) ) {
			out.print( 
				String.format( 
					"%nAtualizando o numero do contato( %s ) de %d para %d... ", 
					contato.getNome(), contato.getNumero(), novoNumero 
				) 
			);
			contato.setNumero( novoNumero );
			out.println( "Contato atualizado!" );
	    }	
	}
	
}