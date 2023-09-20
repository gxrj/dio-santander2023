package list;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CatalogoLivros {
    
	List<Livro> livros;
	
	CatalogoLivros() {
		livros = new ArrayList<>();
	}
	
	public static void main( String... args ) {
		var catalogo = new CatalogoLivros();
		
		try {
			catalogo.adicionarLivro( "O alquimista", "Paulo Coelho", 1988 );
			catalogo.adicionarLivro( "O diario de um mago", "Paulo Coelho", 1987 );
			catalogo.adicionarLivro( "The mythical man-month", "Fred Brooks", 1975 );
			catalogo.adicionarLivro( "Java: How to program", "Deitel", 2020 );
			catalogo.adicionarLivro( "Java: How to program", "Deitel", 2011 );
			catalogo.adicionarLivro( "OCP Java SE 17 Developer Exam 1Z0-829", "Mughal; Strelnokov", 2023 );
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao do livro... \nErro: " + e.getMessage() );
		}

		out.println( "\nCatalogo: " );
		exibirTituloTabelado();
		catalogo.livros.stream().forEach( out::println );

		// Ordenacao
		Comparator<Livro> ordenacaoPorAutor = ( livro1, livro2 ) -> livro1.autor().compareTo( livro2.autor() );
		Comparator<Livro> ordenacaoPorAno = ( livro1, livro2 ) -> livro1.anoPublicacao() - livro2.anoPublicacao();
		Comparator<Livro> ordenacaoPorTitulo = ( livro1, livro2 ) -> livro1.titulo().compareTo( livro2.titulo() );
		// Prioridade de ordenacao: autor > ano > titulo
		var ordenacao = ordenacaoPorAutor.thenComparing( ordenacaoPorAno ).thenComparing( ordenacaoPorTitulo );						 
		catalogo.livros.sort( ordenacao ); 

		out.println( "\nCatalogo ordenado por autor: " );
		exibirTituloTabelado();
		catalogo.livros.stream().forEach( out::println );
		
		var resultado = catalogo.pesquisarPorAutor( "Paulo Coelho" );
		listarResultado( resultado );
		
		resultado = catalogo.pesquisarPorAutor( "Deitel" );
		listarResultado( resultado );
		
		resultado = catalogo.pesquisarPorAutor( "Monteiro Lobato" );
		listarResultado( resultado );
		
		resultado = catalogo.pesquisarPorIntervaloAnos( 2010, 2023 );
		listarResultado( resultado );
		
		resultado = catalogo.pesquisarPorIntervaloAnos( 1930, 1970 );
		listarResultado( resultado );
		
		var livro = catalogo.pesquisarPorTitulo( "O primo Basilio" );
		out.println( livro != null ? livro : " Nenhum livro encontado!" );
		
		livro = catalogo.pesquisarPorTitulo( "O alquimista" );
		out.println( livro != null ? livro : " Nenhum livro encontado!" );
	}
	
	boolean adicionarLivro( String titulo, String autor, int anoPublicacao ) {
		return livros.add( new Livro( titulo, autor, anoPublicacao ) );
	}
	
	List<Livro> pesquisarPorAutor( String autor ) throws RuntimeException {
		out.print( "\nPesquisando livros de " + autor + "... " );
		var lista = new ArrayList<Livro>();
		for( var livro : livros )
			if( livro.autor().equalsIgnoreCase( autor ) )
				lista.add( livro );
			
		return lista;
	}
	
	List<Livro> pesquisarPorIntervaloAnos( int anoInicial, int anoFinal ) {
		out.printf( String.format( "%nPesquisando publicacoes entre %d e %d... ", anoInicial, anoFinal ) );
		var lista = new ArrayList<Livro>();
		for( var livro : livros )
			if( anoInicial <= livro.anoPublicacao() && livro.anoPublicacao() <= anoFinal )
				lista.add( livro );
			
		return lista;
	}
	
	Livro pesquisarPorTitulo( String titulo ) {
		out.printf( String.format( "%nPesquisando por obra titulada de: %s... ", titulo ) );
		for( var livro : livros )
			if( livro.titulo().equalsIgnoreCase( titulo ) ) {
				exibirTituloTabelado();
				return livro;
			}
		return null;
	}

	private static void exibirTituloTabelado() {
		out.println( String.format( "%n%-40s %-20s %s", "Titulo", "Autor", "Ano de Publicacao" ) );
	}

	private static void listarResultado( List<Livro> livros ) {
		if( livros.size() > 0 ) {
			exibirTituloTabelado();
			livros.stream().forEach( out::println );
		}
		else 
			out.println( " Nenhum livro encontado!" );
	}

	record Livro( String titulo, String autor, int anoPublicacao ) {
		
		@Override 
		public String toString() {
			return String.format( "%-40s %-20s %d", titulo(), autor(), anoPublicacao() );
		}
	}
}
