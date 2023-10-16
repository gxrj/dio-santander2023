package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cliente;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Endereco;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ExpedienteDiario;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemCardapio;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemPedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Pedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.StatusPedido;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Telefone;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.PedidoRepository;

@DataJpaTest
public class PedidoRepositoryIntTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PedidoRepository repository;

    @Test
    @DisplayName( "Testa a busca por pedidos a determinado restaurante em dado intervalo de tempo" )
    void testa_findByRestaurante_CnpjBetween() {
        popularDatabase();

        var pedidos = repository.findByRestaurante_CnpjBetween(
                "16516387000178", 
                LocalDateTime.now().minusDays( 5 ), 
                LocalDateTime.now().plusDays( 5 ) );

        assertThat( pedidos ).hasSize( 1 );
        assertThat( pedidos.get(0).getRestaurante().getNome() ).isEqualTo( "Restaurante B" );

        var itensPedido = pedidos.get(0).getItens();

        assertThat( itensPedido ).hasSize(2);
        assertThat( itensPedido.get(0).getItemCardapio().getDescricao() ).isEqualTo( "churrasco" );
        assertThat( itensPedido.get(1).getItemCardapio().getDescricao() ).isEqualTo( "refrigerante 2L" );
    }

    @Test
    @DisplayName( "Testa a busca por pedidos efetuados a determinado restaurante e com status pendente" )
    void testa_findPendentesByRestaurante_Cnpj() {
        popularDatabase();
        
        var pendentes = repository.findPendentesByRestaurante_Cnpj( "16516387000178" );

        assertThat( pendentes ).hasSize( 1 );
        assertThat( pendentes.get(0).getRestaurante().getNome() ).isEqualTo( "Restaurante B" );
        assertThat( pendentes.get(0).getStatus() ).isEqualTo( StatusPedido.PENDENTE );

        var itensPedido = pendentes.get(0).getItens();

        assertThat( itensPedido ).hasSize(2);
        assertThat( itensPedido.get(0).getItemCardapio().getDescricao() ).isEqualTo( "churrasco" );
        assertThat( itensPedido.get(1).getItemCardapio().getDescricao() ).isEqualTo( "refrigerante 2L" );
    }

    @Test
    @DisplayName( "Testa a busca por pedidos efetuados a determinado restaurante e com status concluido" )
    void testa_findConcluidosByRestaurante_CnpjBetween() {
        popularDatabase();

        var concluidos = repository.findConcluidosByRestaurante_CnpjBetween(
                "06452479000106",
                LocalDateTime.now().minusDays( 5 ), 
                LocalDateTime.now().plusDays( 5 ) );

        assertThat( concluidos ).hasSize( 1 );
        assertThat( concluidos.get(0).getRestaurante().getNome() ).isEqualTo( "Pizzaria A" );
        assertThat( concluidos.get(0).getStatus() ).isEqualTo( StatusPedido.CONCLUIDO );

        var itensPedido = concluidos.get(0).getItens();

        assertThat( itensPedido.get(0).getItemCardapio().getDescricao() ).isEqualTo( "Pizza Portuguesa" );
        assertThat( itensPedido.get(1).getItemCardapio().getDescricao() ).isEqualTo( "refrigerante 2L" );

        concluidos = repository.findConcluidosByRestaurante_CnpjBetween(
                "92021793000145",
                LocalDateTime.now().minusDays( 5 ), 
                LocalDateTime.now().plusDays( 5 ) );

        assertThat( concluidos ).hasSize( 1 );
        assertThat( concluidos.get(0).getRestaurante().getNome() ).isEqualTo( "Lanchonete C" );
        assertThat( concluidos.get(0).getStatus() ).isEqualTo( StatusPedido.CONCLUIDO );
        itensPedido = concluidos.get(0).getItens();

        assertThat( itensPedido.get(0).getItemCardapio().getDescricao() ).isEqualTo( "hamburguer" );
        assertThat( itensPedido.get(1).getItemCardapio().getDescricao() ).isEqualTo( "refrigerante 2L" );
    }

    private void popularDatabase() {
        var bairros = gerarBairros();
        var itens = gerarCardapio( gerarRestaurantes( bairros ) );
        var clientes = gerarClientes( bairros );

        var pedidos = new ArrayList<Pedido>();
        for( int i = 0; i < itens.size(); i+=2 ) {
            var status = itens.get(i).getRestaurante().getNome().equalsIgnoreCase( "Restaurante B" )  ? 
                        StatusPedido.PENDENTE 
                        : StatusPedido.CONCLUIDO;
            var pedido = Pedido.builder()
                                .cliente( clientes.get(i/2) )
                                .data( LocalDateTime.now() )
                                .itens( gerarItensPedido( List.of( itens.get(i), itens.get(i+1) ) ) )
                                .restaurante( itens.get(i).getRestaurante() )
                                .pagamentoConfirmado( true )
                                .status( status )
                                .build();
            pedidos.add( pedido );
        }

        repository.saveAll( pedidos );
    }
   
    /** Relaciona dois itens do cardapio para o pedido */
    private List<ItemPedido> gerarItensPedido( List<ItemCardapio> cardapios ) {
        var itensPedido = new ArrayList<ItemPedido>();
        for( int i = 0; i < cardapios.size(); i++ ) {
             var itemPedido = ItemPedido.builder()
                                        .quantidade(1)
                                        .itemCardapio( cardapios.get(i) )
                                        .precoUnd( cardapios.get(i).getPreco() )
                                        .build();
            itensPedido.add( itemPedido );
        }
        return itensPedido;
    }

    /** Gera o cardapio para o restaurante */
    private List<ItemCardapio> gerarCardapio( List<Restaurante> restaurantes ) {

        var itens = new ArrayList<ItemCardapio>();

        var itemBuilder = ItemCardapio.builder().emEstoque( true );
        var nomeItens = List.of( 
            "Pizza Portuguesa", "refrigerante 2L", "churrasco",  
            "refrigerante 2L","hamburguer", "refrigerante 2L" );

        var precos = List.of( 50d, 7.80, 20.42, 5.89, 15.75, 3.20 );

        for( int i = 0; i < nomeItens.size(); i++ ) {
            var item = itemBuilder
                            .descricao( nomeItens.get(i) )
                            .preco( precos.get(i) )
                            .restaurante( restaurantes.get(i/2) )
                            .build();

            item = entityManager.persist( item );
            itens.add( item );
        }
        return itens;
    }

    /** Cadastra restaurantes de acordo com a lista de bairros */
    private List<Restaurante> gerarRestaurantes( List<Bairro> bairros ) {
        var enderecos = gerarEnderecosParaRestaurantes( bairros );
        var nomes = List.of( "Pizzaria A", "Restaurante B", "Lanchonete C" );
        var logins = List.of( "user1", "userX", "userY" );
        var cnpjs = List.of( "06452479000106", "16516387000178", "92021793000145" );
        var expedientes = gerarExpedienteDosRestaurantes();

        var restaurantes = new ArrayList<Restaurante>();

        for( int i = 0; i < nomes.size(); i++ ) {
            var restaurante = Restaurante.builder()
                                        .cnpj( cnpjs.get(i) )
                                        .nome( nomes.get(i) )
                                        .senha( "123" )
                                        .login( logins.get(i) )
                                        .endereco( enderecos.get(i) )
                                        .expediente( expedientes.get(i) )
                                        .build();
            restaurante = entityManager.persist( restaurante );
            restaurantes.add( restaurante );
        }

        return restaurantes;
    }
    
    /** Produz o endereço dos restaurantes relacionando com os bairros fornecidos */
    private List<Endereco> gerarEnderecosParaRestaurantes( List<Bairro> bairros ) {

        var logradouros = List.of( "avenida 1", "estrada 2", "rodovia 3" );
        var numeros = List.of( "12", "1015", "627" );
        var ceps = List.of( "10213000", "1947512", "1501923" );
        var enderecos = new ArrayList<Endereco>();

        for( int i = 0; i < bairros.size(); i++ ) {
            var endereco = Endereco.builder()
                                .bairro( bairros.get(i) )
                                .logradouro( logradouros.get(i) )
                                .numero( numeros.get(i) )
                                .cep( ceps.get(i) )
                                .build();
            enderecos.add( endereco );
        }

        return enderecos;       
    }
    
    /**
     Pizzaria A ( 18:00 ~ 2:00 ) || Restaurante B ( 11:30 ~ 15:30 ) || Lanchonete C ( 8:30 ~ 21:20 )
    */ 
    private List<List<ExpedienteDiario>> gerarExpedienteDosRestaurantes() {
        var expedientes = new ArrayList<List<ExpedienteDiario>>();

        var aberturas = List.of( 
            LocalTime.of( 18, 00 ),
            LocalTime.of( 11, 30 ),
            LocalTime.of( 8, 30 ) 
        );
        var fechamentos = List.of(
            LocalTime.of( 2, 00 ),
            LocalTime.of( 15, 30 ),
            LocalTime.of( 21, 20 )
        );

        for( int i = 0; i < aberturas.size(); i++ ) 
            expedientes.add( gerarExpedienteSemanal( aberturas.get(i), fechamentos.get(i) ) );
        
        return expedientes;
    }

    /**Gera os dias de funcionamento e o horario de funcionamento para cada dia */
    private List<ExpedienteDiario> gerarExpedienteSemanal( LocalTime inicio, LocalTime fim ) {
        var diaria = new ArrayList<ExpedienteDiario>();
        var dias = DayOfWeek.values();
        for( int i = 0; i < dias.length; i++ ) {
            var funcionamento = ExpedienteDiario.builder()
                                    .dia( dias[i] )
                                    .inicio( inicio )
                                    .fim( fim )
                                    .build();
            diaria.add( funcionamento );
        }

        return diaria;
    }

    private List<Cliente> gerarClientes( List<Bairro> bairros ) {
        var nomes = List.of( "Ana", "Joao", "Maria" );
        var cpfs = List.of( "31864375094", "91349075350", "01294891547" );
        var logins = List.of( "userAna", "user2", "user3" );
        var telefoneBuilder = Telefone.builder().ddd( 11 );
        var telefones = List.of( 
                telefoneBuilder.numero( "988501274" ).build(),
                telefoneBuilder.numero( "999726100" ).build(),
                telefoneBuilder.numero( "997038653" ).build() 
        );
        var enderecos = gerarEnderecosParaClientes( bairros );
        var clientes = new ArrayList<Cliente>();
        
        for( int i = 0; i < nomes.size(); i++ ) {
            var cliente = Cliente.builder()
                            .cpf( cpfs.get(i) )
                            .login( logins.get(i) )
                            .nome( nomes.get(i) )
                            .telefones( List.of( telefones.get(i) ) )
                            .senha( "123" )
                            .endereco( enderecos.get(i) )
                            .build();
            cliente = entityManager.persist( cliente );
            clientes.add( cliente );            
        }
        
        return clientes;
    }

    private List<Endereco> gerarEnderecosParaClientes( List<Bairro> bairros ) {

        var logradouros = List.of( "rua x", "rua Y", "travessaz" );
        var numeros = List.of( "12", "1015", "627" );
        var ceps = List.of( "02233070", "02281070", "03707005" );
        var enderecos = new ArrayList<Endereco>();

        for( int i = 0; i < bairros.size(); i++ ) {
            var endereco = Endereco.builder()
                                .bairro( bairros.get(i) )
                                .logradouro( logradouros.get(i) )
                                .numero( numeros.get(i) )
                                .cep( ceps.get(i) )
                                .build();
            enderecos.add( endereco );
        }

        return enderecos;       
    }

    private List<Bairro> gerarBairros() {
        var cidade = Cidade.builder()
                        .nome( "Guarulhos" )
                        .estado( Estado.SP )
                        .build();

        cidade = entityManager.persist( cidade );

        var bairros = new ArrayList<Bairro>();
        var bairroBuilder = Bairro.builder().cidade( cidade );
        var nomesBairros = List.of( "Vila Augusta", "Vila Rosalia", "Vila Galvao" );

        for( var nome : nomesBairros ) {
            var bairro = bairroBuilder.nome( nome ).build();
            bairro = entityManager.persist( bairro );
            bairros.add( bairro );
        }

        return bairros;
    }
}
