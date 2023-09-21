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
	}

	void adicionarEvento( LocalDate data, String nome, String atracao ) {
		map.put( data, new Evento( nome, atracao ) );
	}

	void exibirAgenda() {
		var mapOrdenado = obterMapOrdenadoPorData();

		if( mapOrdenado == null ) return;
	
		for( var entry : mapOrdenado.entrySet() ) {
			var evento = entry.getValue();
			out.printf( "%-6s %-15s %s", entry.getKey(), evento.nome(), evento.atracao() );
		}
	}

	Evento obterProximoEvento() {
		var mapOrdenado = obterMapOrdenadoPorData();

		for( var entry : mapOrdenado.entrySet() )
			if( LocalDate.now().isBefore( entry.getKey() ) )
				return entry.getValue();
		
		out.println( "Nao ha proximos eventos!" );
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