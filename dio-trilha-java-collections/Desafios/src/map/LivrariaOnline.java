package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class LivrariaOnline {

	record Livro( String titulo, String autor, double preco ) {}
	Map<String, Livro> map;

	LivrariaOnline() {
		map = new TreeMap<>();
	}

	public static void main( String... args ) {
		var livraria = new LivrariaOnline();

		try {
			livraria.adicionarLivro( "link 1", "Livro 1", "Autor 1", 275.69 );
			livraria.adicionarLivro( "link 2", "Livro 1a", "Autor 3", 135.23 );
			livraria.adicionarLivro( "link 3", "Livro 1b", "Autor 1", 412.87 );
			livraria.adicionarLivro( "link 4", "Livro s", "Autor 2", 75.56 );
			livraria.adicionarLivro( "link 5", "Livro d1", "Autor 1", 147.84 );
			livraria.adicionarLivro( "link 6", "Livro f0", "Autor 2", 325.91 );
			livraria.exibirLivrosOrdenadosPorPreco();

			var maisCaro = livraria.obterLivroMaisCaro();
			out.printf( 
				maisCaro == null ? 
				"Nao ha livros! %n" 
				: "O livro mais caro e: %s que custa: %.2f reais!%n", maisCaro.titulo(), maisCaro.preco() );

			var maisBarato = livraria.exibirLivroMaisBarato();
			out.printf( 
				maisBarato == null ? 
				"Nao ha livros! %n" 
				: "O livro mais barato e: %s que custa: %.2f reais!%n", maisBarato.titulo(), maisBarato.preco() );

			livraria.pesquisarLivrosPorAutor( "Autor 1" )
					.stream()
					.map( el -> String.format("%-20s - %.2f reais", el.titulo(), el.preco() ) )
					.forEach( out::println );

			livraria.removerLivro( "Livro 1b" );
			livraria.exibirLivrosOrdenadosPorPreco();

			maisCaro = livraria.obterLivroMaisCaro();
			out.printf( 
				maisCaro == null ? 
				"Nao ha livros! %n" 
				: "O livro mais caro e: %s que custa: %.2f reais!%n", maisCaro.titulo(), maisCaro.preco() );
	
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao ou remocao de livros!\nDetalhes: " + e.getMessage() );
		}
	}

	void adicionarLivro( String link, String titulo, String autor, double preco ) throws RuntimeException {
		out.printf( "Adicionando \"%s\" de \"%s\"... %n", titulo, autor );
		map.put( link, new Livro( titulo, autor, preco ) );
	}

	void removerLivro( String titulo ) throws RuntimeException {
		out.printf( "Buscando pelo livro \"%s\"... ", titulo );
		var chave = obterChave( titulo );
		
		if( chave == null ) {
			out.println( " livro nao econtrado!" );
			return;
		}

		map.remove( chave );
		out.println(" livro removido!" );
	}

	void exibirLivrosOrdenadosPorPreco() {
		out.print( "\nListando livros por ordem de preco..." );
		if( map.size() == 0 ) {
			out.println( "Nao ha livros!" );
			return;
		}

		var mapOrdenado = obterMapOrdenadoPorPreco();

		out.printf( "%n%-20s %-20s %s %n", "Titulo", "Autor", "Preco" );
		for( var entry : mapOrdenado.entrySet() ) {
			var livro = entry.getValue();
			out.printf( "%-20s %-20s %.2f reais %n", livro.titulo(), livro.autor(), livro.preco() );
		}
		out.println();
	}

	List<Livro> pesquisarLivrosPorAutor( String autor ) {
		out.printf( "%nPesquisando livros do autor \"%s\"...%n", autor );

		return map.entrySet()
			.stream()
			.filter( entry -> entry.getValue().autor().equalsIgnoreCase( autor ) )
			.map( entry -> entry.getValue() )
			.toList();
	}

	Livro obterLivroMaisCaro() {
		return obterPrimeiroDoMapOrdenado( obterComparadorDePreco().reversed() );
	}

	Livro exibirLivroMaisBarato() {
		return obterPrimeiroDoMapOrdenado( obterComparadorDePreco() );
	}

	private Map<String, Livro> obterMapOrdenadoPorPreco() {
		var mapOrdenado = new TreeMap<String, Livro>( obterComparadorDePreco() );
		mapOrdenado.putAll( map );

		return mapOrdenado;
	}

	private Comparator<String> obterComparadorDePreco() {
		Comparator<Livro> comparadorLivrosPorPreco = ( l1, l2 ) -> Double.compare( l1.preco(),  l2.preco() );
		return Comparator.comparing( map::get , comparadorLivrosPorPreco );
	}

	private Livro obterPrimeiroDoMapOrdenado( Comparator<String> comparator ) {
		if( map.size() == 0 ) 
			return null;

		var mapOrdenado = new TreeMap<String, Livro>( comparator );
		mapOrdenado.putAll( map );

		return mapOrdenado.firstEntry().getValue();
	}

	private String obterChave( String titulo ) {
		for( var entry : map.entrySet() )
			if( entry.getValue().titulo().equalsIgnoreCase( titulo ) )
				return entry.getKey();

		return null;
	}
}