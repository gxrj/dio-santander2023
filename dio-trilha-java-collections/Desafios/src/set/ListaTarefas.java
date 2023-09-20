package set;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Set;
import java.util.TreeSet;

public class ListaTarefas {
    
	Set<Tarefa> tarefas;
	
	ListaTarefas() {
		tarefas = new TreeSet<>();
	}
	
	public static void main( String... args ) {
		var t = new ListaTarefas();
		
		try{
			t.adicionarTarefa( "Cozinhar" );
            t.adicionarTarefa( "Lavar louças" );
            t.adicionarTarefa( "Lavar roupas" );
            t.adicionarTarefa( "Limpar cozinha" );
            t.adicionarTarefa( "Lavar banheiro" );
            t.adicionarTarefa( "Cortar grama" );
            t.adicionarTarefa( "Lavar garagem" );
            t.adicionarTarefa( "Varrer calçada" );
            t.adicionarTarefa( "Capinar mato" );
			
			t.exibirTarefas();
			
			t.marcarTarefaConcluida( "Cozinhar" );
			t.marcarTarefaConcluida( "Lavar louças" );
			t.marcarTarefaConcluida( "Lavar roupas" );
			t.marcarTarefaConcluida( "Limpar cozinha" );
			t.marcarTarefaConcluida( "Lavar banheiro" );
			out.println();

			out.println( "Tarefas concluidas:" );
			t.obterTarefasConcluidas().forEach( out::print );
			out.println();

			out.println( "Tarefas pendentes:" );
			t.obterTarefasPendentes().forEach( out::print );
			out.println();

			t.marcarTarefaPendente( "Lavar roupas" );
			t.removerTarefa( "Varrer calçada" );
			
			t.exibirTarefas();
			t.limparListaTarefas();
			t.exibirTarefas();
			
		} catch (RuntimeException e) {
			err.println( "Falha na insercao ou remocao de elementos! \nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarTarefa( String descricao ) throws RuntimeException {
		tarefas.add( new Tarefa( descricao ) );
	}
	
	void removerTarefa( String descricao ) throws RuntimeException {
		var tarefa = buscarTarefa( descricao );

		if( tarefa != null ) {
			tarefas.remove( tarefa );
			out.println( " tarefa removida!" );
		}
	}
	
	void exibirTarefas() {
		if( tarefas.size() == 0 )
			out.println( "Nao ha tarefas!" );
		else {
			out.println( String.format( "%n%-20s %-9s", "Tarefa", "Conclusao" ) );	
			tarefas.forEach( out::print );
			out.println( "Total de tarefas: " + contarTarefas() + "\n" );
		}
	}
	
	int contarTarefas() {
		return tarefas.size();
	}
	
	Set<Tarefa> obterTarefasConcluidas() {
		var set = new TreeSet<Tarefa>();
		for( var tarefa : tarefas )
			if( tarefa.conclusao )
					set.add( tarefa );
		return set;
	}
	
	Set<Tarefa> obterTarefasPendentes() {
		var set = new TreeSet<Tarefa>();
		for( var tarefa : tarefas )
			if( !tarefa.conclusao )
					set.add( tarefa );
		return set;
	}
	
	void marcarTarefaConcluida( String descricao ) {
		out.print( "Detectado pedido para marcar tarefa como concluida! " );
		var tarefa = buscarTarefa( descricao );
		if( tarefa != null ) {
			tarefa.conclusao = true;
			out.printf( "%s foi marcada como concluida! %n", descricao );
		}
	}
	
	void marcarTarefaPendente( String descricao ) {
		out.print( "Detectado pedido para marcar tarefa como pendente! " );
		var tarefa = buscarTarefa( descricao );
		if( tarefa != null ) { 
			tarefa.conclusao = false;
			out.printf( "%s foi marcada como pendente! %n", descricao );
		}
	}
	
	void limparListaTarefas() throws RuntimeException {
		out.print( "Removendo todas as tarefas... " );
		tarefas.removeAll( tarefas );
		out.println( " concluido!" );
	}
	
	private Tarefa buscarTarefa( String descricao ) {
		out.printf( "Procurando por %s... ", descricao );
		for( var tarefa : tarefas )
			if( tarefa.descricao.equalsIgnoreCase( descricao ) ) return tarefa;

		out.println( "tarefa nao encontrada!" );
		return null;
	}
	
	class Tarefa implements Comparable<Tarefa>{
		String descricao;
		boolean conclusao;
		
		Tarefa( String descricao ) {
			this.descricao = descricao;
			this.conclusao = false;
		}

		@Override public int compareTo( Tarefa t ) {
			return descricao.compareTo( t.descricao );
		}
		
		@Override public String toString() {
			return String.format( "%-20s %-9s %n", descricao, conclusao ? "Concluida" : "Pendente" );
		}
	}
}
