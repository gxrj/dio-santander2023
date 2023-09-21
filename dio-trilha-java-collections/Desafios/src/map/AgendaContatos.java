package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.HashMap;
import java.util.Map;

class AgendaContatos {
	
	Map<String, Integer> map;
	
	AgendaContatos() {
		map = new HashMap<>();
	}
	
	public static void main( String... args ) {
		var agenda = new AgendaContatos();
		
		try {
			agenda.adicionarContato( "Maria", 99999 );
			agenda.adicionarContato( "Sara", 20348 );
			agenda.adicionarContato( "Agata", 12093 );
			agenda.adicionarContato( "Fernanda", 55655 );
			agenda.adicionarContato( "Carol", 82387 );
			agenda.exibirContatos();
			agenda.pesquisarPorNome( "Carol" );
			agenda.removerContato( "Fernanda" );
			agenda.exibirContatos();
			agenda.pesquisarPorNome( "Fernanda" );
			
		} catch ( RuntimeException e ) {
			err.println( "Falha na adicao do contato!\nDescricao: " + e.getMessage() );
		}
	}
	
	void adicionarContato( String nome, Integer telefone ) throws RuntimeException {
		map.put( nome, telefone );
	}

	void removerContato( String nome ) throws RuntimeException {
		out.printf( "Removendo %s... ", nome );
		out.println( map.remove( nome ) != null ? "Contato removido!" : "Contato inexistente!" );
	}
	
	void exibirContatos() {
		if( map.size() == 0 ) {
			out.println( "Nao ha map!" );
			return;
		}
		out.printf( "%n%-20s %s %n", "Nome", "Telefone" );

		for( var contato : map.entrySet() )
			out.printf( "%-20s %d %n", contato.getKey(), contato.getValue() );

		out.println();
	}
	
	Integer pesquisarPorNome( String nome ) {
		out.printf( "Pesquisando o telefone de " + nome + "... " );
		var telefone = map.get( nome );

		if( telefone == null ) {
			out.println( "Contato nao encontrado!" );
			return null;
		}
		out.println( "Contato encontrado!Telefone: " + telefone );
		return telefone;
	}
}