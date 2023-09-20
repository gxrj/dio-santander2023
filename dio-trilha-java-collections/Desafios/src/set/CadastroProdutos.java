package set;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class CadastroProdutos {
	Set<Produto> produtos;
	
	CadastroProdutos() {
		produtos = new TreeSet<>();
	}
	
	public static void main( String... args ) {
		var catalogo = new CadastroProdutos();
		
		try{
			catalogo.adicionarProduto( 79491, "Arroz 5kg-T1 ", 35.30, 2 );
            catalogo.adicionarProduto( 10239, "Feijao Branco 1kg ", 7.29, 2 );
            catalogo.adicionarProduto( 24305, "Patinho moído 1kg", 25.60, 2 );
            catalogo.adicionarProduto( 50960, "Frango sassami 1kg", 15.75, 3 );
            catalogo.adicionarProduto( 45086, "Lagarto redondo 1kg", 35.69, 2 );
			catalogo.exibirProdutos( catalogo.produtos );
			catalogo.exibirProdutosPorNome();
			catalogo.exibirProdutosPorPreco();
		} 
		catch( NullPointerException e ) {
			err.println( "Falha na exibicao de produtos! \nDetalhes: " + e.getMessage() );
		}
		catch( RuntimeException e ) {
			err.println( "Falha na adicao de produto! \nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarProduto( long cod, String nome, double preco, int quantidade ) throws RuntimeException {
		produtos.add( new Produto( cod, nome, preco, quantidade ) );
	}
	
	void exibirProdutosPorNome() throws NullPointerException {
		Comparator<Produto> ordenacaoPorNome = ( p1, p2 ) -> p1.nome().compareTo( p2.nome() );
		out.println( "\nOrdenando produtos por nome..." );
		var acervo = new TreeSet<Produto> ( ordenacaoPorNome );
		acervo.addAll( produtos );
		exibirProdutos( acervo );
	}
	
	void exibirProdutosPorPreco() throws NullPointerException {
		Comparator<Produto> ordenacaoPorPreco = ( p1, p2 ) -> Double.compare( p1.preco(), p2.preco() );
		out.println( "\nOrdenando produtos por preco..." );
		var acervo = new TreeSet<Produto>( ordenacaoPorPreco );
		acervo.addAll( produtos );
		exibirProdutos( acervo );
	}

	private void exibirProdutos( Set<Produto> acervoProdutos ) {
		if( acervoProdutos.size() != 0 ) {
			out.printf( String.format( "%-6s %-20s %-6s %-5s %n", "Cod", "Nome", "Preco", "Qtd" ) );
			acervoProdutos.forEach( out::print );
		}
		else
			out.println( "Nao ha produtos cadastrados!" );
	}
	
	record Produto( long cod, String nome, double preco, int quantidade ) implements Comparable<Produto> {
		@Override public int compareTo( Produto p ) {
			return Long.compare( cod, p.cod() );
		}
		@Override public String toString() {
			return String.format( "%-6s %-20s %-6.2f %d %n", cod(), nome(), preco(), quantidade() );
		}
	}
}