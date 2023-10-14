package me.dio.gxrj.desafiofinalbackenddiosantander2023.integration.tests.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Bairro;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Cidade;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Endereco;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Estado;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ExpedienteDiario;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.ItemCardapio;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.model.Restaurante;
import me.dio.gxrj.desafiofinalbackenddiosantander2023.domain.repository.RestauranteRepository;

@DataJpaTest
public class RestauranteRepositoryIntTest {
    
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RestauranteRepository repository;
    // Todo: injetar passwordEncoder

    @Test
    @DisplayName( "Testa o metodo findByEndereco_Bairro_NomeIgnoreCase" )
    void testa_findByEndereco_Bairro_NomeIgnoreCase () {
        var restaurantes = populaDatabase_RetornaEntidades();
        restaurantes = repository.saveAll( restaurantes );

        var restaurante = repository.findByEndereco_Bairro_NomeIgnoreCase( "vila augusta" );
        assertThat( restaurante.get(0).getEndereco().getBairro().getNome() ).isEqualTo( "Vila Augusta" );
    }

    @Test
    @DisplayName( "Testa o metodo findByEndereco_Bairro_Cidade_NomeIgnoreCase" )
    void testa_findByEndereco_Bairro_Cidade_NomeIgnoreCase () {
        var restaurantes = populaDatabase_RetornaEntidades();
        restaurantes = repository.saveAll( restaurantes );

        var restaurante = repository.findByEndereco_Bairro_Cidade_NomeIgnoreCase( "guaRuLhos" );
        assertThat( 
            restaurante.get(0).getEndereco().getBairro().getCidade().getNome() 
        )
        .isEqualTo( "Guarulhos" );
    }

    @Test
    @DisplayName( "Testa a busca de restaurante pela descricao de um item no cardapio" )
    void testa_findByByDescricaoItemCardapio() {
        var restaurantes = populaDatabase_RetornaEntidades();
        gerarCardapio( restaurantes );
        var cidadeGuarulhos = restaurantes.get(0).getEndereco().getBairro().getCidade();

        restaurantes = repository.findByDescricaoItemCardapio( cidadeGuarulhos, "churrasco" );
        assertThat( restaurantes.get(0).getNome() ).isEqualTo("Restaurante B" );

        restaurantes = repository.findByDescricaoItemCardapio( cidadeGuarulhos, "pIzZa Portuguesa" );
        assertThat( restaurantes.get(0).getNome() ).isEqualTo("Pizzaria A" );
    }

    @Test
    @DisplayName( "Testa a busca por restaurantes abertos" )
    void testa_findByAbertos() {
        var restaurantes = populaDatabase_RetornaEntidades();
        var cidadeGuarulhos = restaurantes.get(0).getEndereco().getBairro().getCidade();
        var dia = LocalDate.now().getDayOfWeek();
        var limite = Pageable.ofSize( 5 );
        restaurantes = repository.findByAbertos( cidadeGuarulhos, dia, LocalTime.of(1, 20), limite );
        assertThat( restaurantes ).hasSize(1);
        assertThat( restaurantes.get(0).getNome() ).isEqualTo("Pizzaria A" );

        restaurantes = repository.findByAbertos( cidadeGuarulhos, dia, LocalTime.of(2, 20), limite );
        assertThat( restaurantes ).hasSize(0);

        restaurantes = repository.findByAbertos( cidadeGuarulhos, dia, LocalTime.of(15, 00), limite );
        assertThat( restaurantes ).hasSize(2);
        assertThat( restaurantes.get(0).getNome() ).isEqualTo("Restaurante B" );

        restaurantes = repository.findByAbertos( cidadeGuarulhos, dia, LocalTime.of(16, 20), limite );
        assertThat( restaurantes ).hasSize(1);
        assertThat( restaurantes.get(0).getNome() ).isEqualTo("Lanchonete C" );
    }

    private void gerarCardapio( List<Restaurante> restaurantes ) {

        var itemBuilder = ItemCardapio.builder().emEstoque( true );

        var pizzaPortuesa = itemBuilder.descricao( "Pizza Portuguesa" )
                                .preco( 50d )
                                .restaurante( restaurantes.get(0) )
                                .build();

        var churrasco = itemBuilder.descricao( "churrasco" )
                            .preco( 20.42 )
                            .restaurante( restaurantes.get(1) )
                            .build();

        var hamburguer = itemBuilder.descricao( "hamburguer" )
                            .preco( 15.75 )
                            .restaurante( restaurantes.get(2) )
                            .build();

        itemBuilder = itemBuilder.descricao( "refrigerante 2L" );

        var refriPizzaria = itemBuilder.restaurante( restaurantes.get(0) ).preco( 7.80 ).build();
        var refriRestaurante = itemBuilder.restaurante( restaurantes.get(1) ).preco( 5.89 ).build();
        var refriLanchonete = itemBuilder.restaurante( restaurantes.get(2) ).preco( 3.20 ).build();
            
        entityManager.persist( pizzaPortuesa );
        entityManager.persist( churrasco );
        entityManager.persist( hamburguer );
        entityManager.persist( refriPizzaria );
        entityManager.persist( refriRestaurante );
        entityManager.persist( refriLanchonete );
    }

    private List<Restaurante> populaDatabase_RetornaEntidades() {
        var enderecos = gerarEnderecos();
        var nomes = List.of( "Pizzaria A", "Restaurante B", "Lanchonete C" );
        var logins = List.of( "user1", "user2", "user3" );
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

    private List<Endereco> gerarEnderecos() {
        var bairros = gerarBairros();

        var logradouros = List.of( "avenida 1", "rua 2", "rua c" );
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
