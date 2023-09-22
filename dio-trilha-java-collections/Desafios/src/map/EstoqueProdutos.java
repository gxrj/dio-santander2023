package map;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.HashMap;
import java.util.Map;

class EstoqueProdutos {
	
	Map<Long, Produto> produtos;
	
	EstoqueProdutos() {
		produtos = new HashMap<>();
	}

	public static void main( String... args ) {
		var estoque = new EstoqueProdutos();
		
		try {
			estoque.exibirProdutos();
			estoque.adicionarProduto( 4375L, "Produto 1", 5, 8.75 );
			estoque.adicionarProduto( 7901L, "Produto 2", 7, 2.23 );
			estoque.adicionarProduto( 8456L, "Produto 3", 9, 1.75 );
			estoque.adicionarProduto( 3497L, "Produto 4", 2, 12.05 );
			estoque.adicionarProduto( 1532L, "Produto 5", 4, 6.89 );
			estoque.exibirProdutos();
			out.printf( "Total em estoque: %.2f reais %n", estoque.calcularValorTotalEstoque() );
			var maisCaro = estoque.obterProdutoMaisCaro();
			out.printf( "%nItem mais caro: %nNome: %s %nPreco und.: %.2f reais %n", maisCaro.nome(), maisCaro.preco() );
			var maiorQtdVal = estoque.obterProdutoMaiorQuantidadeValorTotalNoEstoque();
			out.printf( 
				"%nItem de maior valor x qtd:%nNome: %s%nPreco und.: %.2f reais%nQtd: %d%nValor Acumulado: %.2f reais%n", 
				maiorQtdVal.nome(), 
				maiorQtdVal.preco(),
				maiorQtdVal.quantidade(),
				maiorQtdVal.preco() * maiorQtdVal.quantidade() 
			);
			
		} catch( RuntimeException e ) {
			err.println( "Falha na adicao de produto!\nDetalhes: " + e.getMessage() );
		}
	}
	
	void adicionarProduto( long cod, String nome, int quantidade, double preco ) throws RuntimeException {
		out.printf( "Adicionando produto: %s... %n", nome );
		produtos.put( cod, new Produto( nome, quantidade, preco ) );
	}
	
	void exibirProdutos() {
		if( produtos.size() == 0 ) {
			out.println( "Estoque vazio!" );
			return;
		}
		
		out.printf( "%n%-20s %-5s %s %n", "Produto", "Qtd", "Preco" );
		for( var produto : produtos.entrySet() ) {
			var item = produto.getValue();
			out.printf( "%-20s %-5d %.2f %n", item.nome(), item.quantidade(), item.preco() );
		}
		
		out.println();
	}
	
	double calcularValorTotalEstoque() {
		var total = 0d;
		
		for( var produto : produtos.entrySet() ) {
			var item = produto.getValue();
			total += item.preco() * item.quantidade();
		}
		
		return total;
	}
	
	Produto obterProdutoMaisCaro() {
		Produto itemMaisCaro = null;
		for( var item : produtos.entrySet() ) 
			if( itemMaisCaro == null ) 
				itemMaisCaro = item.getValue();
			else
				itemMaisCaro = retornaMaisCaro( itemMaisCaro, item.getValue() );

		return itemMaisCaro;
	}
	
	Produto obterProdutoMaiorQuantidadeValorTotalNoEstoque() {
		Produto itemMaiorQtdValorTotal = null;
		for( var item : produtos.entrySet() )
			if( itemMaiorQtdValorTotal == null ) 
				itemMaiorQtdValorTotal = item.getValue();
			else
				itemMaiorQtdValorTotal = retornaProdutoMaiorQtdValTotal( itemMaiorQtdValorTotal, item.getValue() );

		return itemMaiorQtdValorTotal;
	}
	
	private Produto retornaMaisCaro( Produto produto, Produto outro ) {
		return produto.preco() >= outro.preco() ? produto : outro;
	}
	
	private Produto retornaProdutoMaiorQtdValTotal( Produto produto, Produto outro ) {
		var qtdValTotal_1 = produto.preco() * produto.quantidade();
		var qtdValTotal_2 = outro.preco() * outro.quantidade();

		return qtdValTotal_1 >= qtdValTotal_2 ? produto : outro;
	}
	
	record Produto( String nome, int quantidade, double preco ) {}
}