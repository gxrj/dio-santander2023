import static java.lang.System.out;
import static java.lang.System.err;

import java.util.List;
import java.util.ArrayList;

public class ListaTarefas {

    private static List<Tarefa> tarefas = new ArrayList<>();

    public static void main( String[] args ) {
        try{
            adicionarTarefa( "Cozinhar" );
            adicionarTarefa( "Lavar louças" );
            adicionarTarefa( "Lavar roupas" );
            adicionarTarefa( "Limpar cozinha" );
            adicionarTarefa( "Lavar banheiro" );
            adicionarTarefa( "Cortar grama" );
            adicionarTarefa( "Lavar garagem" );
            adicionarTarefa( "Varrer calçada" );
            adicionarTarefa( "Capinar mato" );

            out.printf( "Tarefas: %s %n", obterDescricoesTarefas() ); 
            out.printf( "Quantidade: %d %n", obterNumeroTotalTarefas() ); 
        
            // Remove Lavar banheiro
            out.println( 
                removerTarefa( "Lavar banheiro" ) ? "Tarefa removida com sucesso!" : "Falha na remoçao!" );

            // Remove Lavar garagem
            out.println( 
                removerTarefa( "Lavar garagem" ) ? "Tarefa removida com sucesso!" : "Falha na remoçao!" );

        } catch ( RuntimeException e ) {
            err.printf( "Erro: %s %n", e.getMessage() );
        }

        out.printf( "Tarefas: %s %n", obterDescricoesTarefas() ); 
        out.printf( "Quantidade: %d %n", obterNumeroTotalTarefas() );
    }

    private static boolean adicionarTarefa( String descricao ) throws RuntimeException {
        return tarefas.add( new Tarefa( descricao ) );
    }

    private static boolean removerTarefa( String descricao ) throws RuntimeException {
        out.printf( "Buscando tarefa: %s... ", descricao );
        for( var tarefa : tarefas ) 
            if( tarefa.descricao().equalsIgnoreCase(descricao) ) 
                return tarefas.remove( tarefa );

        return false;
    }

    private static int obterNumeroTotalTarefas() {
        return tarefas.size();
    }

    private static List<String> obterDescricoesTarefas() {
        return tarefas.stream().map( t -> t.descricao() ).toList();

        /** Alternativa
        List<String> descricoes = new ArrayList<>();

        for( var tarefa : tarefas )
            descricoes.add( tarefa.descricao() );
        
        return descricoes;
        */
    }

    public record Tarefa( String descricao ) implements Comparable<Tarefa> {
        @Override
        public int compareTo( Tarefa tarefa ) {
            return descricao().compareToIgnoreCase( tarefa.descricao() );
        }
    }
}
