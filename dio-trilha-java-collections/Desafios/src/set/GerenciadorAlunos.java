package set;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class GerenciadorAlunos {

	Set<Aluno> alunos ;
	
	GerenciadorAlunos() {
		alunos = new TreeSet<>();
	}
	
	public static void main( String... args ) {
		var gerenciadorAlunos = new GerenciadorAlunos();
		
		try{
			gerenciadorAlunos.adicionarAluno( "Maria", 93403L, 7.5 );
			gerenciadorAlunos.adicionarAluno( "Agata", 49201L, 9.8 );
			gerenciadorAlunos.adicionarAluno( "Sara", 82340L, 5.0 );
			gerenciadorAlunos.adicionarAluno( "Carol", 53893L, 8.2 );
			gerenciadorAlunos.adicionarAluno( "Pedro", 79236L, 4.3 );
			gerenciadorAlunos.exibirAlunos();
			
			gerenciadorAlunos.exibirAlunosPorNome();
			gerenciadorAlunos.removerAluno( 79236L );
			gerenciadorAlunos.exibirAlunosPorNota();
			
		} catch( RuntimeException e ) {
			err.println( "Falha na insercao ou remocao de aluno!\nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarAluno( String nome, Long matricula, double media ) throws RuntimeException {
		alunos.add( new Aluno( nome, matricula, media ) );
	} 
	
	void removerAluno( long matricula ) throws RuntimeException {
		out.printf( "Buscando aluno pela matricula: %s... ", matricula );
		for( var aluno : alunos ) 
			if( aluno.matricula() == matricula ) {
				alunos.remove( aluno );
				out.println( String.format( "o cadastro do aluno(a) %s foi removido!", aluno.nome() ) );
				return;
			}
		out.println( "Aluno(a) nao encontrado(a)!" );
	}
	
	void exibirAlunosPorNome() {
		Comparator<Aluno> ordenacaoPorNome = ( a1, a2 ) -> a1.nome().compareTo( a2.nome() );
		var alunosPorNome = new TreeSet<Aluno>( ordenacaoPorNome );
		out.println( "Listando alunos por nome... " );
		alunosPorNome.addAll( alunos );
		listarAlunos( alunosPorNome );
	}
	
	void exibirAlunosPorNota() {
		Comparator<Aluno> ordenacaoPorNota = ( a1, a2 ) -> Double.compare( a1.media(), a2.media() );
		var alunosPorNota = new TreeSet<Aluno>( ordenacaoPorNota );
		out.println( "Listando alunos por nota... " );
		alunosPorNota.addAll( alunos );
		listarAlunos( alunosPorNota );
	}
	
	void exibirAlunos() {
		listarAlunos( alunos );
	}
	
	private void listarAlunos( Set<Aluno> cadastros ) {
		if( cadastros.size() == 0 )
			out.println( "Nao ha alunos cadastrados!" );
		else {
			out.printf( "%n%-10s %-20s %-6s %n", "Matricula", "Nome", "Media" );
			cadastros.forEach( out::print );
			out.println();
		}
	}
	
	record Aluno( String nome, Long matricula, double media ) implements Comparable<Aluno> {
	
		@Override public int compareTo( Aluno a ) {
			return Long.compare( matricula, a.matricula() );
		}
		@Override public String toString() {
			return String.format( "%-10s %-20s %-6.2f %n", matricula, nome, media );
		}
	}
}