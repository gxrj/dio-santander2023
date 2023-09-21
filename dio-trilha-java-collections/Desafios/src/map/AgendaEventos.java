package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class AgendaEventos {

	Map<LocalDate, Evento> map; 

	AgendaEventos() {
		map = new TreeMap<>();
	}

	public static void main( String... args ) {
		var agenda = new AgendaEventos();

		try {
			agenda.exibirAgenda();
			agenda.adicionarEvento( LocalDate.parse("2023-09-19"), "Lancamento do java 21", "Lorem ipsum" );
			agenda.adicionarEvento( LocalDate.parse("2023-10-12"), "Dia das criancas", "Foo" );
			agenda.adicionarEvento( LocalDate.parse("2023-10-15"), "Dia do professor", "Bar" );
			agenda.adicionarEvento( LocalDate.parse("2023-10-22"), "Fim do bootcamp", "Baz" );
			agenda.adicionarEvento( LocalDate.now().plusDays( 5L ), "Springbreak em 5 dias", "O tempo passar" );
			out.println();
			agenda.exibirAgenda();
			var evento = agenda.obterProximoEvento();
			out.println( evento != null ? "\nProximo evento sera: " + evento.nome() : "Nao ha proximos eventos!"  );
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao de eventos!\nDetalhes: " + e.getMessage() );
		}
	}

	void adicionarEvento( LocalDate data, String nome, String atracao ) throws RuntimeException {
		out.printf( "%nAdicionando evento \"%s\"... ", nome );
		map.put( data, new Evento( nome, atracao ) );
	}

	void exibirAgenda() {
		out.println( "\nListando eventos... " );
		var mapOrdenado = obterMapOrdenadoPorData();

		if( mapOrdenado == null ) return;
		out.printf( "%-12s %-25s %s %n", "Data", "Evento", "Atracao" );
		for( var entry : mapOrdenado.entrySet() ) {
			var evento = entry.getValue();
			out.printf( "%-12s %-25s %s %n", entry.getKey(), evento.nome(), evento.atracao() );
		}
	}

	Evento obterProximoEvento() {
		var mapOrdenado = obterMapOrdenadoPorData();

		for( var entry : mapOrdenado.entrySet() )
			if( LocalDate.now().isBefore( entry.getKey() ) )
				return entry.getValue();

		return null;
	}

	private Map<LocalDate, Evento> obterMapOrdenadoPorData() {
		if( map.size() == 0 ) {
			out.println( "Nao ha eventos!" );
			return null;
		}	
		Comparator<LocalDate> comparadorPorData = ( e1, e2 ) -> e1.compareTo( e2 );
		var mapOrdenado = new TreeMap<LocalDate, Evento>( comparadorPorData );
		mapOrdenado.putAll( map );
		return mapOrdenado;
	}

	record Evento( String nome, String atracao ) {}
}