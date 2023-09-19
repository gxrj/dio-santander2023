import static java.lang.System.out;
import static java.lang.System.err;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CarrinhoDeCompras {

    private List<Item> carrinho;

    CarrinhoDeCompras() {
        carrinho = new ArrayList<>();
    }

    public static void main( String... args ) {
        var compras = new CarrinhoDeCompras();

        try{
            compras.adicionarItem( "Arroz 5kg-T1 ", 35.30, 2 );
            compras.adicionarItem( "Feijao Branco 1kg ", 7.29, 2 );
            compras.adicionarItem( "Patinho moído 1kg", 25.60, 2 );
            compras.adicionarItem( "Frango sassami 1kg", 15.75, 3 );
            compras.adicionarItem( "Lagarto redondo 1kg", 35.69, 2 );
            compras.exibirItens();
            out.println( "Total: " + compras.calcularValorTotal() + " reais" );
            // Remove o Patinho moído
            out.println( 
                compras.removerItem( "Patinho moído 1kg" ) ? "Item removido com sucesso!" : "Falha ao remover do carrinho!" );

            compras.exibirItens();
            out.println( "Total: " + compras.calcularValorTotal() + " reais" );
        }
        catch( NoSuchElementException e ) {
            err.printf( "Falha ao calcular total %nErro: %s %n", e.getMessage() );
        }
        catch( RuntimeException e ) {
            err.printf( "Falha na adicao ou remocao do item %nErro: %s %n", e.getMessage() );
        }
        
    }

    boolean adicionarItem( String nome, double preco, int quantidade ) throws RuntimeException {
        return carrinho.add( new Item( nome, preco, quantidade ) );
    }

    boolean removerItem( String nome ) throws RuntimeException {
        out.printf( "Buscando pelo item: %s... ", nome );
        for( var item : carrinho ) 
            if( item.nome().equalsIgnoreCase( nome ) )
                return carrinho.remove( item );

        return false;
    }

    double calcularValorTotal() throws NoSuchElementException {
        double total = 0;
        var iterator = carrinho.iterator();
        
        while( iterator.hasNext() ) {
            var item = iterator.next();
            total += item.preco() * item.quantidade();
        }

        return total;
    }

    void exibirItens() {

        out.println( "Itens no carrinho: " );
        carrinho.stream()
                .map( 
                    item -> 
                        String.format( "item: %-20s valor: %.2f reais \t quantidade: %d", item.nome(), item.preco(), item.quantidade() )
                )
                .forEach( out::println );
    }

    private record Item( String nome, double preco, int quantidade ) implements Comparable<Item> {
        @Override
        public int compareTo( Item item ) {
            return nome().compareToIgnoreCase( nome );
        }
    }
}
